package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 交友数据访问层
 *
 * @author Guoty
 * @create 2019/02/15  16:35
 */
public interface FriendDao extends JpaRepository<Friend, String> {
    /**
     * 根据用户ID与被关注用户ID查询记录个数
     *
     * @param userId
     * @param friendId
     * @return
     */
    @Query("select count(f) from Friend f where f.userid=?1 and f.friendid =?2")
    int findByUserIdAndFriendId(String userId, String friendId);

    /**
     * 更新为互相喜欢
     *
     * @param userId
     * @param friendId
     * @param isLike
     */
    @Modifying
    @Query("update Friend  f set f.islike = ?3 where f.userid = ?1 and f.friendid = ?2")
    void updateLike(String userId, String friendId, String isLike);
}
