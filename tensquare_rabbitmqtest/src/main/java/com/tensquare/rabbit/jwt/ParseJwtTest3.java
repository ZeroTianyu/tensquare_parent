package com.tensquare.rabbit.jwt;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Guoty
 * @create 2019/01/30  17:26
 */
public class ParseJwtTest3 {
    public static void main(String[] args) {
        String
                compactJws = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDkwNTE4NTg2NDM2NDg5MjE2Iiwic3ViIjoi6YOt5aSp5a6HIiwiaWF0IjoxNTQ4OTIwODk4LCJyb2xlcyI6InVzZXIiLCJ1c2VyIjp7ImlkIjoiMTA5MDUxODU4NjQzNjQ4OTIxNiIsIm1vYmlsZSI6IjE1OTI4Mjk3MTk2Iiwibmlja25hbWUiOiLpg63lpKnlrociLCJzZXgiOiIxIiwiYmlydGhkYXkiOjE1NDcwOTc4ODAwMDAsImF2YXRhciI6Ii90b3V4aWFuZy5qcGciLCJlbWFpbCI6IjE1NjQ1NkBxcS5jb20iLCJyZWdkYXRlIjoxNTQ4ODM0ODY3MDAwLCJ1cGRhdGVkYXRlIjpudWxsLCJsYXN0ZGF0ZSI6MTU0ODgzNDg2NzAwMCwib25saW5lIjowLCJpbnRlcmVzdCI6IkxPTCIsInBlcnNvbmFsaXR5Ijoi5Liq5oCnIiwiZmFuc2NvdW50IjowLCJmb2xsb3djb3VudCI6MH0sImV4cCI6MTU0ODkyNDQ5OH0.BYSlBpfkbNMBfasTVWBmtYBSLYNQ2HHDITKPh3n9q5Q";
        Claims claims =
                Jwts.parser().setSigningKey("itcast").parseClaimsJws(compactJws).getBody(
                );
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("roles:" + claims.get("roles"));
        System.out.println("user:" + claims.get("user"));

        Object user =  claims.get("user");

        String s = JSON.toJSONString(user);
        UserRsp userRsp = JSON.parseObject(s, UserRsp.class);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("签发时间:" + sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:" + sdf.format(new Date()));
    }
}
