package com.ws.fastfood.login.service;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.ws.fastfood.login.Entity.ResponseVO;

public interface LoginService {
	ResponseVO Login(UsernamePasswordToken token);

}
