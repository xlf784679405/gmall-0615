package com.atguigu.gmall.auth.feign;

import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-13 18:10
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {


}
