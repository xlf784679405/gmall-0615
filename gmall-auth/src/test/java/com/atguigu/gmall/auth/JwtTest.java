package com.atguigu.gmall.auth;

import com.atguigu.core.utils.JwtUtils;
import com.atguigu.core.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTest {
	private static final String pubKeyPath = "D:\\software\\ideal\\project-0615\\tmp\\rsa.pub";

    private static final String priKeyPath = "D:\\software\\ideal\\project-0615\\tmp\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "dasfa@acgaazAAA..**");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "11");
        map.put("username", "liuyan");
        // 生成token
        String token = JwtUtils.generateToken(map, privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6IjExIiwidXNlcm5hbWUiOiJsaXV5YW4iLCJleHAiOjE1NzM2MzQ1NDJ9.bzBSycWGbXTIcPwU-hB7jF5J0UMEUdVPnpkd56Ma4HMfXraKtJl8ET8Mi5rUMbEiEbNsUqAfanLjj0KS07CEqmtJ0bMuEgAF_Z3YjYe-NKL3r36XG54dQ202qeycMlF9cbj0zYRVF8zKWrARIyx3WNSHM7XN_mbWGjtcfUptfEVoTvQWWgp1DE158ZX_rar1bzymBTjXSaKXplnATziIDsIOQ9Kn1fRrXXjrbsallbaNI5nXyh_Uu9VlonH1zp9DjVPzycxZmztti2YU-X43Pg3JWH1hNHghjoJKnkgR0P6FBTGS9pSyxplFqcbubYrHxbmskFpFyaHQG4T2t-nRzg";

        // 解析token
        Map<String, Object> map = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + map.get("id"));
        System.out.println("userName: " + map.get("username"));
    }
}