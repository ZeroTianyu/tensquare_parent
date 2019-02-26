package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Guoty
 * @create 2019/02/15  17:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;


    @Autowired
    private UserClient userClient;


    /**
     * 添加好友
     *
     * @param userId
     * @param friendId
     * @return
     */
    public int addFriend(String userId, String friendId) {
        //先判断userId到friendId方向是否有数据，有就是重复添加，返回0
        if (friendDao.findByUserIdAndFriendId(userId, friendId) > 0) {
            return 0;
        }
        //直接添加好友，让userId到friendId方向的type为0
        Friend friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        friendDao.save(friend);

        //增加自己的关注数
        userClient.incFollowCount(userId, 1);
        //增加对方的粉丝数
        userClient.incFansCount(friendId, 1);


        //friendId到userId方向是否有数据，如果有，就把双方的type都设置为1
        if (friendDao.findByUserIdAndFriendId(friendId, userId) > 0) {
            friendDao.updateLike(userId, friendId, "1");
            friendDao.updateLike(friendId, userId, "1");
        }
        return 1;
    }

    /**
     * 向不喜欢列表中添加记录
     *
     * @param userId
     * @param friendId
     */
    public void addNoFriend(String userId, String friendId) {
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
    }

    /**
     * 删除好友
     *
     * @param userId
     * @param friendId
     */
    public void deleteFriend(String userId, String friendId) {
        friendDao.deleteFriend(userId, friendId);
        friendDao.updateLike(userId, friendId, "0");

        //减少自己的关注数
        userClient.incFollowCount(userId, -1);
        //减少对方的粉丝数
        userClient.incFansCount(friendId, -1);

        //向不喜欢表中添加记录
        addNoFriend(userId, friendId);
    }
}
