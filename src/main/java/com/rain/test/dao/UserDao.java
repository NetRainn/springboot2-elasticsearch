package com.rain.test.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.rain.test.pojo.User;
import java.util.*;


/**
 * @author Rain
 */
public interface UserDao extends ElasticsearchRepository<User, String>{

	List<User> getAllBy();

	List<User> queryUserByAge(Integer age);
}
