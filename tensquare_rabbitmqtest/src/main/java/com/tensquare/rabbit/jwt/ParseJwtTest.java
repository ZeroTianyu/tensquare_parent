package com.tensquare.rabbit.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Guoty
 * @create 2019/01/30  17:10
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        String
                token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1NDg4Mzk3MzUsImV4cCI6MTU0ODgzOTc5NX0.jcl1C6JuAUcxP_qhrKnaJTGo3ngHE2j2EBZnVzCO8Bo";
        Claims claims =
                Jwts.parser().setSigningKey("tensquare").parseClaimsJws(token).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("IssuedAt:" + claims.getIssuedAt());
    }

}
