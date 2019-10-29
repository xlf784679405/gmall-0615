package com.atguigu.gmall.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.oms.entity.RefundInfoEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 退款信息
 *
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:36:30
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageVo queryPage(QueryCondition params);
}

