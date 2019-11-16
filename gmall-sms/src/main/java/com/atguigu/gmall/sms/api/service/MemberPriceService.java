package com.atguigu.gmall.sms.api.service;

import com.atguigu.gmall.sms.api.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 商品会员价格
 *
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageVo queryPage(QueryCondition params);
}

