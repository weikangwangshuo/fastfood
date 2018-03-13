package com.ws.fastfood.login.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.fastfood.login.Entity.ResponseVO;
import com.ws.fastfood.login.Entity.UserVO;
import com.ws.fastfood.login.service.LoginService;

import lombok.NonNull;

/**
 * 
 * 项目名称：login 类名称：LoginController 类描述： 登录，注册 创建人：wangshuo 创建时间：2018年3月13日
 * 上午8:58:27 修改人： 修改时间： 修改备注：
 * 
 * @version
 * 
 */
@RestController
@RequestMapping(value = "/Login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public ResponseVO login(@NonNull @PathVariable UserVO user, HttpServletRequest request, HttpSession session) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		ResponseVO R = loginService.Login(token);
		if (R.getCode() == 200) {
			session.setAttribute("name", user.getName());
		}
		return R;

	}

}
