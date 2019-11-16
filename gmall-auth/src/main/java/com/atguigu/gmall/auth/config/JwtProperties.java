package com.atguigu.gmall.auth.config;

import com.atguigu.core.utils.RsaUtils;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
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

    private String privateKeyPath;

    private Integer expire;

    private String secret;

    private String cookieName;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @PostConstruct
    public void init(){

        //1.初始化公钥私钥文件
        try {
        File publicFile = new File(publicKeyPath);
        File privateFile = new File(privateKeyPath);
        //2.检查文件对象是否为空
        if (!publicFile.exists() || !privateFile.exists()) {
            RsaUtils.generateKey(publicKeyPath, privateKeyPath, secret);
        }
        //3.读取秘钥
        publicKey = RsaUtils.getPublicKey(publicKeyPath);
        privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥和私钥失败");
            e.printStackTrace();

        }
    }
}
