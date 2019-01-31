package com.tensquare.rabbit.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * token过期检测
 *
 * @author Guoty
 * @create 2019/01/30  17:16
 */
public class ParseJwtTest2 {
    public static void main(String[] args) {
        String
                compactJws = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDkwNTE4NTg2NDM2NDg5MjE2Iiwic3ViIjoi6YOt5aSp5a6HIiwiaWF0IjoxNTQ4OTAwNTU2LCJyb2xlcyI6InVzZXIiLCJleHAiOjE1NDg5MDQxNTZ9.Bf1fJUmXokY8STLyOgroTsMAIGevAZktqjapoTkHDi4";
        Claims claims =
                Jwts.parser().setSigningKey("itcast").parseClaimsJws(compactJws).getBody(
                );
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("签发时间:" + sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:" + sdf.format(claims.getExpiration()));
        System.out.println("当前时间:" + sdf.format(new Date()));
    }
}
