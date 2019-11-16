package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author feilong
 * @create 2019-11-11 23:48
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
