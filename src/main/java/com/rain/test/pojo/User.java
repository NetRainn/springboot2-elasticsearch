package com.rain.test.pojo;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;


/**
 * @author xiayu
 */
@Document(indexName = "userindex", type = "user")
public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 编号 */
	 private String id;
	 /** 姓名 */
	 private String name;
	 
	 /** 年龄 */
	 private Integer age;
	 
	 /** 描述 */  
	 private String description;
	 
	 /** 创建时间 */
	 private String createtm;
	 
	 
	 public User(){
	 }

	 
	 
	 
	public User(String id, String name, Integer age, String description, String createtm) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.description = description;
		this.createtm = createtm;
	}




	/**  
	 * 获取编号  
	 * @return  id  
	 */
	public String getId() {
		return id;
	}


	/**  
	 * 设置编号  
	 * @param  id
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**  
	 * 获取姓名  
	 * @return  name  
	 */
	public String getName() {
		return name;
	}


	/**  
	 * 设置姓名  
	 * @param  name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**  
	 * 获取年龄  
	 * @return  age  
	 */
	public Integer getAge() {
		return age;
	}


	/**  
	 * 设置年龄  
	 * @param  age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}


	/**  
	 * 获取描述  
	 * @return  description  
	 */
	public String getDescription() {
		return description;
	}


	/**  
	 * 设置描述  
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**  
	 * 获取创建时间  
	 * @return  createtm  
	 */
	public String getCreatetm() {
		return createtm;
	}


	/**  
	 * 设置创建时间  
	 * @param createtm
	 */
	public void setCreatetm(String createtm) {
		this.createtm = createtm;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", description=" + description + ", createtm="
				+ createtm + "]";
	}
	 

	
	

}
