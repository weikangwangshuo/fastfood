package com.ws.fastfood.login.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.fastfood.login.Dao.UserDao;
import com.ws.fastfood.login.Entity.ResponseVO;
import com.ws.fastfood.login.Entity.UserVO;
import com.ws.fastfood.login.service.LoginService;

@SuppressWarnings("deprecation")
@Service
public class loginServiceImpl implements LoginService {
	
	private static final Logger log = LoggerFactory.getLogger(LoginService.class);
	@Autowired
	private static UserDao userDao;

	@Override
	public ResponseVO Login(UsernamePasswordToken token) {
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
		Factory<org.apache.shiro.mgt.SecurityManager> factory =  new IniSecurityManagerFactory("classpath:com/ws/fastfood/login/Shrio/shiro-realm.ini");  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    try {  
	        subject.login(token);
	        log.info(token.getUsername()+"尝试登录成功");
	        return new ResponseVO(200,"登录成功");  
	    } catch (AuthenticationException e) { 
	    	log.info(token.getUsername()+"尝试登录失败");
	    	return new ResponseVO(300,"登录失败");  
	    } 
	}

	
}
