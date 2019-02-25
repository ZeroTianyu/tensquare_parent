package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Guoty
 * @create 2019/02/15  16:49
 */
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    /**
     * 添加好友或添加非好友
     *
     * @param friendId
     * @param type
     * @return
     */
    @RequestMapping(value = "like/{friendId}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendId, @PathVariable String type) {
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("user_claims");

        if (claims == null) {
            //说明当前角色没有用户权限
            return new Result(true, StatusCode.LOGINERROR, "权限不足");
        }

        //得到当前用户id
        String userId = claims.getId();

        //判断是添加好友还是添加非好友
        if (!StringUtils.isEmpty(type)) {
            if ("1".equals(type)) {
                //添加好友
                int flag = friendService.addFriend(userId, friendId);

                if (flag == 0) {
                    return new Result(true, StatusCode.ERROR, "不能重复添加好友");
                }

                if (flag == 1) {
                    return new Result(true, StatusCode.OK, "添加成功");
                }
                return new Result(true, StatusCode.OK, "添加成功");
            } else if ("2".equals(type)) {
                //添加非好友
                return new Result(true, StatusCode.OK, "添加成功");
            } else {
                return new Result(true, StatusCode.ERROR, "参数异常");
            }

        } else {
            return new Result(true, StatusCode.ERROR, "参数异常");
        }

    }
}
