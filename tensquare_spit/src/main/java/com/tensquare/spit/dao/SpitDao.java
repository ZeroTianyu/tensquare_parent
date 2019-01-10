package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Guoty
 * @create 2019/01/09/23:32
 */
public interface SpitDao extends MongoRepository<Spit,String> {
}
