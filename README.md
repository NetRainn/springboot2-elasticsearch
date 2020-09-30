## 1. 前提
网上学习Elasticsearch教程很多，但是有的不是那么完善，会有很多坑，做一些记录，方便后面自己查找，回顾。附上代码可以正常运行。
### 1. 使用版本
 - Elasticsearch: 7.9.1
 - Kibana: 7.9.1
 - elasticsearch-head-master
 > 注：window上安装参考：[windows安装es+kibana](https://www.cnblogs.com/hualess/p/11540477.html)
### 2. 对比
| mysql | Elasticsearch  |
|--|--|
|database    |  Indices      |
|Tables    |Types     |
|rows |Documents    |
|columns|Fields|
### 3.常见问题
1. 报错：**Caused by: NoNodeAvailableException[None of the configured nodes are available: [{#transport#-1}{192.168.1.105}{192.168.1.105:9300}]]** 
这个问题就是版本不对，不管你怎么设置也好 ，始终就会出这个问题。
- **版本对应关系** 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200930160535604.png#pic_center)
2. Elasticsearch 和 kibana 版本要统一，不然无法启动
3. 配置文件中的name 要和Elasticsearch  中 yml文件的cluster-name对应
## 2. 代码说明
代码是用了多种方式实现查询功能，感兴趣的朋友可以继续往下写
1. **采用ElasticsearchRepository下的JPA实现查询功能，此方法不是很全，方法是继承ElasticsearchRepository<User, String>，然后写部分功能**
2. **采用SearchSourceBuilder实现查询**
	步骤：
	- 构建查询条件
	- SearchSourceBuilder进行封装
	- 创建并设置SearchRequest对象，SearchRequest searchRequest = new SearchRequest("userindex); 匹配索引 如果不指定 自动匹配
	- client进行查询
	- 使用JSON转换成实体，User user = JSON.parseObject(JSON.toJSONString(hit.getSourceAsMap()), User.class);
3.  **自定义查询方式，方式灵活多样。包括多种查询，单一匹配，多匹配，范围查询等**
4. **测试用例：**

```bash
{"id":1,"name":"张三","age":18,"description":"张三是java开发工程师","createtm": "2020-9-30 10:01:32"}
```
5. **请求示例：**

```bash
http://127.0.0.1:8281/test/searchByMoreUseSearch?searchContent=张
