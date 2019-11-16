package com.atguigu.gmall.cart.controller;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.cart.service.CartService;
import com.atguigu.gmall.cart.vo.Cart;
import com.atguigu.gmall.cart.vo.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author feilong
 * @create 2019-11-14 13:58
 */
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("order")
    public Resp<List<CartItemVO>> queryCartItemVO(){
        List<CartItemVO> itemVOList = this.cartService.queryCartItemVO();
        return Resp.ok(itemVOList);
    }

    @PostMapping
    public Resp<Object> addCart(@RequestBody Cart cart){

        this.cartService.addCart(cart);
        return Resp.ok(null);
    }
    @GetMapping
    public Resp<List<Cart>> queryCarts(){

        List<Cart> carts = this.cartService.queryCarts();
        return Resp.ok(carts);
    }

    @PostMapping("update")
    public Resp<Object> updateCart(@RequestBody Cart cart){

        this.cartService.updateCart(cart);
        return Resp.ok(null);
    }

    @PostMapping("{skuId}")
    public Resp<Object> deleteCart(@PathVariable("skuId")Long skuId){
        this.cartService.deleteCart(skuId);
        return Resp.ok(null);
    }

    @PostMapping("check")
    public Resp<Object> checkCart(@RequestBody List<Cart> carts){

        this.cartService.checkCart(carts);
        return Resp.ok(null);
    }
}
