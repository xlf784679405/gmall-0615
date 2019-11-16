package com.atguigu.gmall.sms.api.service;

import com.atguigu.gmall.sms.api.entity.SeckillSkuRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 秒杀活动商品关联
 *
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageVo queryPage(QueryCondition params);
}

