package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户客户端
 *
 * @author Guoty
 * @create 2019/02/25/23:03
 */
@FeignClient("tensquare-user")
public interface UserClient {
    /**
     * 增加粉丝数
     *
     * @param userId
     * @param x
     */
    @RequestMapping(value = "/user/incFans/{userId}/{x}", method = RequestMethod.POST)
    void incFansCount(@PathVariable("userId") String userId, @PathVariable("x") int x);

    /**
     * 增加关注数
     *
     * @param userId
     * @param x
     */
    @RequestMapping(value = "/user/incFollow/{userId}/{x}", method = RequestMethod.POST)
    void incFollowCount(@PathVariable("userId") String userId, @PathVariable("x") int x);
}
