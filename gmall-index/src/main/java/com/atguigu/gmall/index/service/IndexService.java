package com.atguigu.gmall.index.service;

import com.alibaba.fastjson.JSON;
import com.atguigu.core.annotation.GmallCache;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.index.feign.GmallPmsClient;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.vo.CategoryVO;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author feilong
 * @create 2019-11-08 18:08
 */
@Service
public class IndexService {

    @Autowired
    private GmallPmsClient gmallPmsClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedissonClient redissonClient;


    private static final String KEY_PREFIX = "index:category";

    public List<CategoryEntity> queryLevel1Category() {

        Resp<List<CategoryEntity>> resp = this.gmallPmsClient.queryCategories(1, null);
        return resp.getData();
    }
    @GmallCache(prefix = KEY_PREFIX , timeout = 300000l ,random = 50000l)
    public List<CategoryVO> queryCategroyVO(Long pid) {
        //1.查询缓存，缓存中有的话直接返回
//        String cache = this.redisTemplate.opsForValue().get(KEY_PREFIX + pid);
//        if (StringUtils.isNotBlank(cache)){
//            return JSON.parseArray(cache , CategoryVO.class);
//        }

        //2.如果缓存中没有，查询数据库
        Resp<List<CategoryVO>> listResp = this.gmallPmsClient.queryCateGoryWithSub(pid);
        List<CategoryVO> categoryVOS = listResp.getData();
//        3.查询完成之后，放入缓存
//        this.redisTemplate.opsForValue().set(KEY_PREFIX +pid , JSON.toJSONString(categoryVOS) , 5 +(int) (Math.random() * 5) , TimeUnit.DAYS);
        return categoryVOS;
    }

    public String testLock() {

        RLock lock = this.redissonClient.getLock("lock");
        lock.lock();
        //获取到锁执行业务逻辑
        String numString = this.redisTemplate.opsForValue().get("num");
        if (StringUtils.isBlank(numString)) {
            return null;
        }
            int num = Integer.parseInt(numString);
            this.redisTemplate.opsForValue().set("num", String.valueOf(++num));

            //释放锁
            lock.unlock();
            return "已经增加成功";
        }
        public String testLock1 () {
            //所有请求，竞争锁
            String uuid = UUID.randomUUID().toString();
            Boolean lock = this.redisTemplate.opsForValue().setIfAbsent("lock", uuid, 10, TimeUnit.SECONDS);
            //获取到锁执行业务逻辑
            if (lock) {
                String numString = this.redisTemplate.opsForValue().get("num");
                if (StringUtils.isBlank(numString)) {
                    return null;
                }
                int num = Integer.parseInt(numString);
                this.redisTemplate.opsForValue().set("num", String.valueOf(++num));

                //释放锁
                Jedis jedis = null;
                try {
                    jedis = this.jedisPool.getResource();
                    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

                    //            this.redisTemplate.execute(new DefaultRedisScript<>(script) , Arrays.asList("lock") , uuid);
                    jedis.eval(script, Arrays.asList("lock"), Arrays.asList(uuid));
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
//            if (StringUtils.equals(uuid , this.redisTemplate.opsForValue().get("lock"))){
//                this.redisTemplate.delete("lock");

                //           }
            } else {
                //没有获取到锁的请求进行重试
                try {
                    TimeUnit.SECONDS.sleep(1);
                    testLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "已经增加成功";
        }

    public String testRead() {

        RReadWriteLock readWriteLock = this.redissonClient.getReadWriteLock("readWriteLock");
        readWriteLock.readLock().lock(10l , TimeUnit.SECONDS);
        String msg = this.redisTemplate.opsForValue().get("msg");
//        readWriteLock.readLock().unlock();
        return msg;

    }

    public String testWrite() {

        RReadWriteLock readWriteLock = this.redissonClient.getReadWriteLock("readWriteLock");
        readWriteLock.writeLock().lock(10l , TimeUnit.SECONDS);

        String msg = UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set("msg" , msg);
//        readWriteLock.writeLock().unlock();
        return "数据写入成功" + msg;

    }
}

