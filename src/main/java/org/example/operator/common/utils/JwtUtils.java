package org.example.operator.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.operator.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    @Autowired
    JwtProperties jwtProperties;

    /**
     * 创建jwt
     * 加密算法：HS256
     * 私钥使用jwtProperties中的userSecretKey
     * @param claims 载荷
     * @return jwt
     */
    public String createJwt(Map<String, Object>claims)
    {
        String userSecretKey = jwtProperties.getUserSecretKey();
        long userTtl = jwtProperties.getUserTtl();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date exp = new Date(nowMillis + userTtl);
        JwtBuilder builder = io.jsonwebtoken.Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, userSecretKey.getBytes())
                .setExpiration(exp);
        return builder.compact();
    }

    /**
     * 解密jwt
     * @param token jwt
     * @return jwt中的数据
     */
    public Claims parseJwt(String token)
    {
        //获取jwtProperties中的userSecretKey
        String userSecretKey = jwtProperties.getUserSecretKey();
        return Jwts.parser()
                .setSigningKey(userSecretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
