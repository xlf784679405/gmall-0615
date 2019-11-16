package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.SpuInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.SkuInfoEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * sku信息
 *
 * @author xinlongfei
 * @email lxf@atguigu.com
 * @date 2019-10-29 16:28:07
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageVo queryPage(QueryCondition params);


}

