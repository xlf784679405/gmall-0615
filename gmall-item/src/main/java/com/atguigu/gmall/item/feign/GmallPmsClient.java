package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-11 23:48
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
