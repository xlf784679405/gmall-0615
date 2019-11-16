package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-15 20:27
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {
}
