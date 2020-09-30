package com.rain.test.service;

import java.util.List;

import com.rain.test.pojo.User;


/**
 * 
* Title: UserService
* @author Rain
* @date 2020年9月30日
 */
public interface UserService {
	
	/**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    boolean createUser(User user);

    /**
     * 根据关键字进行全文搜索
     * @param searchContent
     * @return
     */
    List<User> searchContent(String searchContent);
    
    
    /**
     * 查询全部
     *
     * @return
     */
    List<User> searchAll();

    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<User> searchByName(String searchContent);


    /**
     * 年龄查询
     *
     * @param age
     * @return
     */
    List<User> searchByUserAge(Integer age);

    /**
     * 多条件查询
     *
     * @param searchContent
     * @return
     */
    List<User> esSearchByMore(String searchContent);

    /**
     * 多条件匹配查询
     *
     * @param name
     * @param description
     * @return
     */
    List<User> searchByMoreContent(String name, String description);

    /**
     * 使用searchSource多匹配查询
     *
     * @param searchContent
     * @return
     */
    List<User> searchByMoreUseSearch(String searchContent);

    /**
     * 根据范围查询
     *
     * @param age
     * @return
     */
    List<User> searchByRange(Integer age);
}
