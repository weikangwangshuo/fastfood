package com.ws.fastfood.login.Entity;


import lombok.Data;
/**
 * @author wangshuo
 *
 */

@Data
public class UserVO {
	private Long resourceId;//主键id
	private String name;//用户姓名
	private String nickname;//昵称
	private String phoneNum;//电话号码
	private String password;//用户密码

}