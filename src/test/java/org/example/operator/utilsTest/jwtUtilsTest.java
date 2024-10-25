package org.example.operator.utilsTest;

import io.jsonwebtoken.Claims;
import org.example.operator.common.utils.JwtUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class jwtUtilsTest {
    @Autowired
    JwtUtils jwtUtils;
    @Test
    public void test(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "faker");
        claims.put("password", "123456");
        String token = jwtUtils.createJwt(claims);
        Assertions.assertNotNull(token);
        System.out.println(token);
        Claims parsedClaims = jwtUtils.parseJwt(token);
        System.out.println(parsedClaims);
        Assertions.assertEquals(claims.get("username"), parsedClaims.get("username"));
        Assertions.assertEquals(claims.get("password"), parsedClaims.get("password"));
    }

}
