package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul过滤器
 *
 * @author Guoty
 * @create 2019/02/26  23:24
 */
@Component
public class WebFilter extends ZuulFilter {
    /**
     * 前置过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器...");
        //向header中添加鉴权令牌
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取header
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            requestContext.addZuulRequestHeader("Authorization", authorization);
        }
        return null;
    }
}
