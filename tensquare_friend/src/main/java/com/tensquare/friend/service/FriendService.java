package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
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

    /**
     * 添加好友
     *
     * @param userId
     * @param friendid
     * @return
     */
    public int addFriend(String userId, String friendid) {
        //先判断userId到friendId方向是否有数据，有就是重复添加，返回0
        if (friendDao.findByUserIdAndFriendId(userId, friendid) > 0) {
            return 0;
        }
        //直接添加好友，让userId到friendId方向的type为0
        Friend friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        //friendId到userId方向是否有数据，如果有，就把双方的type都设置为1
        if (friendDao.findByUserIdAndFriendId(friendid, userId) > 0) {
            friendDao.updateLike(userId, friendid, "1");
            friendDao.updateLike(friendid, userId, "1");
        }
        return 1;
    }
}
