package com.ws.fastfood.login.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ws.fastfood.login.Annotation.Repository1;
import com.ws.fastfood.login.Entity.UserVO;

@Mapper
@Repository1
public interface UserDao {

	@Select("SELECT * FROM user WHERE name=#{name};")
	public UserVO getUserByName(String name);
	
	public int insertUser(UserVO user);
	

}
