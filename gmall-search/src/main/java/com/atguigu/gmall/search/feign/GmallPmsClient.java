package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-05 0:52
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {


}
