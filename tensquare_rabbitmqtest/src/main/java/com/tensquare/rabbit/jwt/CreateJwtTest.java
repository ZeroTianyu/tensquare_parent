package com.tensquare.rabbit.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Guoty
 * @create 2019/01/30  17:09
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        /**
         * setIssuedAt用于设置签发时间
         * signWith用于设置签名秘钥
         */

        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "tensquare");
        System.out.println(builder.compact());
    }

}
