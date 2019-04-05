package util;

import com.alibaba.fastjson.JSON;
import entity.UserRsp;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Guoty
 * @create 2019/01/31  16:23
 */
public class TokenUtil {
    @Autowired
    private JwtUtil jwtUtil;

    public static Object decryptToken(String token) {
        JwtUtil jwtUtil = new JwtUtil();
        jwtUtil.setKey("itcast");


        UserRsp rsp = null;
        Claims claims = jwtUtil.parseJWT(token);
        String roles = (String) claims.get("roles");
        if (!StringUtils.isEmpty(roles) && "user".equals(roles)) {
            Object user = claims.get("user");
            String s = JSON.toJSONString(user);
            rsp = JSON.parseObject(s, UserRsp.class);

        }
        return rsp;
    }

    public static void main(String[] args) {
        Object o = decryptToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDkwNTE4NTg2NDM2NDg5MjE2Iiwic3ViIjoi6YOt5aSp5a6HIiwiaWF0IjoxNTQ4OTI0NDMzLCJyb2xlcyI6InVzZXIiLCJ1c2VyIjp7ImlkIjoiMTA5MDUxODU4NjQzNjQ4OTIxNiIsIm1vYmlsZSI6IjE1OTI4Mjk3MTk2Iiwibmlja25hbWUiOiLpg63lpKnlrociLCJzZXgiOiIxIiwiYmlydGhkYXkiOjE1NDcwOTc4ODAwMDAsImF2YXRhciI6Ii90b3V4aWFuZy5qcGciLCJlbWFpbCI6IjE1NjQ1NkBxcS5jb20iLCJyZWdkYXRlIjoxNTQ4ODM0ODY3MDAwLCJsYXN0ZGF0ZSI6MTU0ODgzNDg2NzAwMCwib25saW5lIjowLCJpbnRlcmVzdCI6IkxPTCIsInBlcnNvbmFsaXR5Ijoi5Liq5oCnIiwiZmFuc2NvdW50IjowLCJmb2xsb3djb3VudCI6MH0sImV4cCI6MTU0ODkyODAzM30.QX4vJpuzSIKwmnVE6CsA4NVIUTHQ5ZTfd7rootBdDnY");

        System.out.println(o);
    }
}
