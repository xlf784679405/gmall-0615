package com.atguigu.gmall.order.service;

import com.atguigu.core.bean.Resp;
import com.atguigu.core.bean.UserInfo;
import com.atguigu.gmall.cart.vo.CartItemVO;
import com.atguigu.gmall.order.feign.*;
import com.atguigu.gmall.order.interceptor.LoginInterceptor;
import com.atguigu.gmall.order.vo.OrderConfirmVO;
import com.atguigu.gmall.order.vo.OrderItemVO;
import com.atguigu.gmall.pms.entity.SkuInfoEntity;
import com.atguigu.gmall.pms.entity.SkuSaleAttrValueEntity;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import com.atguigu.gmall.ums.entity.MemberReceiveAddressEntity;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author feilong
 * @create 2019-11-15 20:22
 */
@Service
public class OrderService {

    @Autowired
    private GmallPmsClient gmallPmsClient;

    @Autowired
    private GmallWmsClient gmallWmsClient;

    @Autowired
    private GmallUmsClient gmallUmsClient;

    @Autowired
    private GmallSmsClient gmallSmsClient;

    @Autowired
    private GmallCartClient gmallCartClient;



    public OrderConfirmVO confirm() {

        OrderConfirmVO orderConfirmVO = new OrderConfirmVO();
        //获取用户的登录信息
        UserInfo userInfo = LoginInterceptor.get();
        //查询用户的收货地址列表
        Resp<List<MemberReceiveAddressEntity>> addressResp = this.gmallUmsClient.queryAddressByUserId(userInfo.getUserId());
        orderConfirmVO.setAddresses(addressResp.getData());
        //获取购物车选中记录
        Resp<List<CartItemVO>> listResp = this.gmallCartClient.queryCartItemVO();
        List<CartItemVO> itemVOS = listResp.getData();
        if (CollectionUtils.isEmpty(itemVOS)){
            return null;
        }
        //把购物车选中记录转换为订货清单
        List<OrderItemVO> orderItems = itemVOS.stream().map(cartItemVO -> {
            OrderItemVO orderItemVO = new OrderItemVO();
            Resp<SkuInfoEntity> skuInfoEntityResp = this.gmallPmsClient.querySkuById(cartItemVO.getSkuId());
            SkuInfoEntity skuInfoEntity = skuInfoEntityResp.getData();
            Resp<List<SkuSaleAttrValueEntity>> saleAttrBySkuId = this.gmallPmsClient.querySaleAttrBySkuId(cartItemVO.getSkuId());
            List<SkuSaleAttrValueEntity> saleAttrValueEntities = saleAttrBySkuId.getData();
            orderItemVO.setSkuAttrValue(saleAttrValueEntities);
            orderItemVO.setTitle(skuInfoEntity.getSkuTitle());
            orderItemVO.setSkuId(cartItemVO.getSkuId());
            orderItemVO.setPrice(skuInfoEntity.getPrice());
            orderItemVO.setDefaultImage(skuInfoEntity.getSkuDefaultImg());
            orderItemVO.setCount(cartItemVO.getCount());
            Resp<List<ItemSaleVO>> saleResp = this.gmallSmsClient.queryItemSaleVOs(cartItemVO.getSkuId());
            List<ItemSaleVO> itemSaleVOS = saleResp.getData();
            orderItemVO.setSales(itemSaleVOS);
            Resp<List<WareSkuEntity>> storeResp = this.gmallWmsClient.queryWareBySkuId(cartItemVO.getSkuId());
            List<WareSkuEntity> wareSkuEntities = storeResp.getData();
            orderItemVO.setStore(wareSkuEntities.stream().anyMatch(wareSkuEntity -> wareSkuEntity.getStock() > 0));
            orderItemVO.setWeight(skuInfoEntity.getWeight());
            return orderItemVO;
        }).collect(Collectors.toList());
        orderConfirmVO.setAddresses(null);

        orderConfirmVO.setOrderItems(null);

        orderConfirmVO.setBounds(null);

        orderConfirmVO.setOrderToken(null);

        return orderConfirmVO;
    }
}
