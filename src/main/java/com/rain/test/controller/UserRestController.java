package com.rain.test.controller;

import java.util.List;

import com.rain.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rain.test.service.UserService;


/**
 * 
* @Title: UserRestController
* @Description: 
* es基础学习
* @author Rain
* @date 2020年9月30日
 */
@RestController
@RequestMapping(value = "/test")
public class UserRestController {

	@Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public boolean createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/searchAll")
    public List<User> searchAll() {
		return userService.searchAll();
    }
    
    @GetMapping("/searchContent")
    public List<User> searchContent(@RequestParam(value = "searchContent") String searchContent) {
        return userService.searchContent(searchContent);
    }

    @GetMapping("/searchByName")
    public List<User> searchByName(@RequestParam(value = "searchContent") String searchContent) {
        return userService.searchByName(searchContent);
    }

    @GetMapping("/searchByUserAge")
    public List<User> searchByUserAge(@RequestParam(value = "age") Integer age) {
        return userService.searchByUserAge(age);
    }

    @GetMapping("/searchByMoreUseQuery")
    public List<User> searchByMoreUseQuery(@RequestParam(value = "searchContent") String searchContent) {
        return userService.esSearchByMore(searchContent);
    }

    @GetMapping("/searchByMoreUseSearch")
    public List<User> searchByMoreUseSearch(@RequestParam(value = "searchContent") String searchContent) {
        return userService.searchByMoreUseSearch(searchContent);
    }

    @GetMapping("/searchByMoreContent")
    public List<User> searchByMoreContent(@RequestParam(value = "name") String name,@RequestParam(value = "description") String description) {
        return userService.searchByMoreContent(name, description);
    }

    @GetMapping("/searchByRange")
    public List<User> searchByRange(Integer age) {
        return userService.searchByRange(age);
    }

}
