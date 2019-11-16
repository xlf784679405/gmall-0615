package com.atguigu.gmall.sms.api.dao;

import com.atguigu.gmall.sms.api.entity.SeckillSkuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动商品关联
 * 
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
@Mapper
public interface SeckillSkuRelationDao extends BaseMapper<SeckillSkuRelationEntity> {
	
}
