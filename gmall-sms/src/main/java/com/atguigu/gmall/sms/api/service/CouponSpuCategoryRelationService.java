package com.atguigu.gmall.sms.api.service;

import com.atguigu.gmall.sms.api.entity.CouponSpuCategoryRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 优惠券分类关联
 *
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
public interface CouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelationEntity> {

    PageVo queryPage(QueryCondition params);
}

