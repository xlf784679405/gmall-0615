package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-11 23:48
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
