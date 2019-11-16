package com.atguigu.gmall.order.vo;

import com.atguigu.gmall.ums.entity.MemberReceiveAddressEntity;
import lombok.Data;

import java.util.List;

/**
 * @author feilong
 * @create 2019-11-15 19:59
 */
@Data
public class OrderConfirmVO {

    private List<MemberReceiveAddressEntity> addresses; //订单的收货地址列表

    private List<OrderItemVO> orderItems; //订单的送货信息

    private Integer bounds; //积分信息

    private String orderToken; //防止重复提交
}
