package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Guoty
 * @create 2019/01/26/17:20
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
}
