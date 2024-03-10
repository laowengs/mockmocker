package com.laowengs.mocker.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class JwtTests {

    @Test
    public void testIoJsonWebToken() {
        String key = "cereshuzhitingnizhenbangcereshuzhitingnizhenbang";
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "get");
        map.put("name1", "get");
        map.put("name2", "get");
        map.put("name3", "get");
        map.put("name4", "get");
        long millis = System.currentTimeMillis();
        String jwt = Jwts.builder()
                .issuer("issuer") // 签发人 www.baidu.com
                .audience().add("1").and() // 受众
                .expiration(new Date(millis + 1800 * 1000))//失效时间
                .id("id")//唯一标识
                .issuedAt(new Date(millis))//签发时间
                .notBefore(new Date(millis))//生效时间
                .subject("subject")//主题
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))//秘钥
                .claims(map)//其他payload
                .compact();
        System.out.println(jwt); // eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZ2V0In0.RiKlMMNgv3XRDcZtxgukAEpOHv_Q9pQQjDPPuPa-Dw0
    }


    @Test
    public void parseJsonWebToken() {
        String key = "cereshuzhitingnizhenbangcereshuzhitingnizhenbang";
        Jws<Claims> jws = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(key.getBytes())).build().parseSignedClaims("eyJhbGciOiJIUzM4NCJ9.eyJuYW1lNCI6ImdldCIsIm5hbWUzIjoiZ2V0IiwibmFtZSI6ImdldCIsIm5hbWUyIjoiZ2V0IiwibmFtZTEiOiJnZXQifQ.OHYhQOlZbBSe7O4D3guDb-CTtAgdeulY4mls6iETUzhpJvyLsb_OqsFGtw0mVEBa");
        System.out.println(jws.getPayload());
        System.out.println(jws.getPayload().get("name"));
    }
}
