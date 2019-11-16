package com.atguigu.gmall.sms.api.service;

import com.atguigu.gmall.sms.api.entity.HomeAdvEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 首页轮播广告
 *
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageVo queryPage(QueryCondition params);
}

