package com.atguigu.gmall.order.config;

import com.atguigu.core.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author feilong
 * @create 2019-11-13 18:38
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {

    private String publicKeyPath;

    private String cookieName;

    private PublicKey publicKey;


    @PostConstruct
    public void init(){
        try {
        //3.读取秘钥
        publicKey = RsaUtils.getPublicKey(publicKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥和私钥失败");
            e.printStackTrace();

        }
    }
}
