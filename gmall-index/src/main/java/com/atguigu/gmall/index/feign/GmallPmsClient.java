package com.atguigu.gmall.index.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-08 18:04
 */
@FeignClient ("pms-service")
public interface GmallPmsClient extends GmallPmsApi {

}
