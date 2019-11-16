package com.atguigu.gmall.sms.api.service;

import com.atguigu.gmall.sms.api.entity.SeckillPromotionEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 秒杀活动
 *
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageVo queryPage(QueryCondition params);
}

