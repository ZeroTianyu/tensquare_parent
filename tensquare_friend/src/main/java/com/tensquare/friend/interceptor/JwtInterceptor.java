package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器类
 *
 * @author Guoty
 * @create 2019/01/30  21:54
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    if ("admin".equals(claims.get("roles"))) {//如果是管理员
                        request.setAttribute("admin_claims", token);
                    }
                    if ("user".equals(claims.get("roles"))) {//如果是用户
                        request.setAttribute("user_claims", claims);
                    }
                }
            } catch (ExpiredJwtException e) {
                throw new RuntimeException("令牌已过期,请重新登录!");
            } catch (Exception e) {
                throw new RuntimeException("令牌有误!");
            }
        }
        return true;
    }
}
