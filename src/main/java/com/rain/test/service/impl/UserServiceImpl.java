package com.rain.test.service.impl;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.rain.test.dao.UserDao;
import com.rain.test.pojo.User;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.rain.test.service.UserService;
import java.util.*;

/**
 * @author Rain
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userDao;

    @Autowired
    private RestHighLevelClient client;

	@Override
	public boolean createUser(User user) {
		boolean flag = false;
		try{
			userDao.save(user);
			flag=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<User> searchContent(String searchContent) {
		  QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
		  System.out.println("查询的语句:"+builder);
          Iterable<User> searchResult = userDao.search(builder);
          List<User> list= Lists.newArrayList(searchResult);
       return list;
	}
	
	@Override
	public List<User> searchAll() {

		return userDao.getAllBy();
	}
	

	@Override
	public List<User> searchByName(String searchContent) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", searchContent));
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<User> searchResult = userDao.search(functionScoreQueryBuilder);
        List<User> list= Lists.newArrayList(searchResult);
        return list;
	}

    @Override
    public List<User> searchByUserAge(Integer age) {
        return userDao.queryUserByAge(age);
    }

    @Override
    public List<User> esSearchByMore(String searchContent) {
        // 多个匹配
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(searchContent, "name", "description");
        System.out.println("查询的语句:" + multiMatchQueryBuilder.toString());
        Iterable<User> searchResult = userDao.search(multiMatchQueryBuilder);
        return Lists.newArrayList(searchResult);
    }

    @Override
    public List<User> searchByMoreContent(String name, String description) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("name", name))
                .must(QueryBuilders.termQuery("description", description));
        Iterable<User> userIterable = userDao.search(queryBuilder);
        return Lists.newArrayList(userIterable);
    }

    @Override
    public List<User> searchByMoreUseSearch(String searchContent) {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(searchContent, "name", "description");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(multiMatchQueryBuilder);
        // 创建并设置SearchRequest对象
        // SearchRequest searchRequest = new SearchRequest("userindex); 匹配索引 如果不指定 自动匹配
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<User> userList = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            User user = JSON.parseObject(JSON.toJSONString(hit.getSourceAsMap()), User.class);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> searchByRange(Integer age) {
        //包括下界 包括上界
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age")
                                            .from(20).to(age)
                                            .includeLower(true).includeUpper(false);
        Iterable<User> userIterable = userDao.search(rangeQueryBuilder);
        return Lists.newArrayList(userIterable);
    }
}
