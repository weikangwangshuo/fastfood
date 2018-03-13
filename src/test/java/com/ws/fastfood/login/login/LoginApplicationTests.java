package com.ws.fastfood.login.login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ws.fastfood.login.Dao.UserDao;
import com.ws.fastfood.login.Entity.UserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test() {
		UserVO user = new UserVO();
		user.setName("asd");
		userDao.insertUser(user);

	}
	
	

	
	

}
