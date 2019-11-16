package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-15 20:27
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
