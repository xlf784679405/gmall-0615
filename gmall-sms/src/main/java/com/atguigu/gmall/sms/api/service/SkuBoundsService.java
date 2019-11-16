package com.atguigu.gmall.sms.api.service;

import com.atguigu.gmall.sms.api.entity.SkuBoundsEntity;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import com.atguigu.gmall.sms.vo.SaleVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;

import java.util.List;


/**
 * 商品sku积分设置
 *
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
public interface SkuBoundsService extends IService<SkuBoundsEntity> {

    PageVo queryPage(QueryCondition params);

    void saveSale(SaleVO saleVO);

    List<ItemSaleVO> queryItemSaleVOs(Long skuId);
}

