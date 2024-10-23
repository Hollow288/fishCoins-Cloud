package com.pond.build.config.utils;

import com.alibaba.fastjson2.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {


    //有效期为 AccessToken和RefreshToken
    public static final Long JWT_ACCESS_TTL = 60 * 60 *1000L;// 60 * 60 * 1000  一个小时
//    public static final Long JWT_ACCESS_TTL = 60 * 3 *1000L;// 60 * 60 * 1000  3分钟

    public static final Long JWT_REFRESH_TTL = 60 * 60 * 8 *1000L;// 60 * 60 * 8 * 1000  八个小时
//    public static final Long JWT_REFRESH_TTL = 60 * 60 * 1 *1000L;// 60 * 60 * 8 * 1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "thisIsFishInThePondAndWorkAndOFishInThePondAndWork";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jtw  jwt加密
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw  jwt加密
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }


    /**
     * 创建token jwt加密
     * @param id 可以指定的id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_ACCESS_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .id(uuid)              //唯一的ID
                .subject(subject)   // 主题  可以是JSON数据
                .issuer("sg")     // 签发者
                .issuedAt(now)      // 签发时间
                .signWith(secretKey, Jwts.SIG.HS256) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .expiration(expDate);
    }



    public  void main(String[] args) throws Exception {
        //jwt加密
        String jwt = createJWT("12345612");

        //jwt解密
        Claims claims = parseJWT(jwt);
        String subject = claims.getSubject();

        String accessToken = JwtUtil.createJWT("1", JwtUtil.JWT_ACCESS_TTL);
        String refreshToken = JwtUtil.createJWT("2", JwtUtil.JWT_REFRESH_TTL);


        System.out.println(subject);
        System.out.println(jwt);
        System.out.println(accessToken);
        System.out.println(refreshToken);


        System.out.println("=========================");
        Claims parseJAccessToken = parseJWT(accessToken);
        System.out.println(parseJAccessToken.getSubject());


    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        Config config = ConfigService.getConfig("fishAuthService"); // 获取默认 application 命名空间
        String key = config.getProperty("jwt.key", JWT_KEY);

        byte[] encodedKey = Base64.getDecoder().decode(key);
//        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    /**
     * jwt解密
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }



    /**
     * 验证 token 是否过期失效
     *
     * @param token
     * @return true 过期 false 未过期
     */
    public static Boolean isTokenExpired(String token) throws Exception {
        return getExpirationDate(token).before(new Date());
    }

    /**
     * 获取 token 失效时间
     *
     * @param token
     * @return
     */
    public static Date getExpirationDate(String token) throws Exception {
        return parseJWT(token).getExpiration();

    }
}