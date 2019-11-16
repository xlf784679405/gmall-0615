package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-05 0:53
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
