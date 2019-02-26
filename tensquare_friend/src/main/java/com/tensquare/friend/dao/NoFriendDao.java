package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 不喜欢列表数据访问层
 *
 * @author Guoty
 * @create 2019/02/25/22:48
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {


}
