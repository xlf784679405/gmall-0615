package com.atguigu.gmall.wms.api;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author feilong
 * @create 2019-11-05 0:48
 */
public interface GmallWmsApi {

    @GetMapping("wms/waresku/{skuId}")
    public Resp<List<WareSkuEntity>> queryWareBySkuId(@PathVariable("skuId")Long skuId);
}
