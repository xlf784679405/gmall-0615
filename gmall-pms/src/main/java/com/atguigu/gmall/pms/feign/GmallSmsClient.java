package com.atguigu.gmall.pms.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-02 0:38
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {


}
