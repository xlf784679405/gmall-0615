package com.atguigu.gmall.cart.api;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.cart.vo.CartItemVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author feilong
 * @create 2019-11-15 20:42
 */
public interface GmallCartApi {

    @GetMapping("cart/order")
    public Resp<List<CartItemVO>> queryCartItemVO();
}
