package com.moments.claw.domain.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
@SuppressWarnings({"unused"})
public class JwtUtil {

    //有效期为
//    public static final Long JWT_TTL = 60*60*1000*24*7L;// 7天
    public static final Long JWT_TTL = 60 * 60 * 1000 * 5L;// 5小时
    //设置秘钥明文
    public static final String JWT_KEY = "clawmoments";

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, JWT_TTL, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("claw-moments")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id id
     * @param subject subject
     * @param ttlMillis 过期时间 unit:ms
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkMTkyYzQ4NzY1ZDM0OTNhYjViMWQ5ODJlODNlN2E5NiIsInN1YiI6IjIiLCJpc3MiOiJjbGF3LW1vbWVudHMiLCJpYXQiOjE3MTExNzYxODIsImV4cCI6MTcxMTE3OTc4Mn0.1flMtA8tnb8AF9Bc_xnoQvebNVkV9lEC5BLCES_ig3U";
        Claims claims = parseJWT(token);
        System.out.println(claims.getExpiration());
        System.out.println(claims);
    }

    /**
     * 生成加密后的秘钥 secretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getUrlDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
    
    /**
     * 解析
     *
     * @param jwt jwt
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}