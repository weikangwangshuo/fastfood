package com.ws.fastfood.login.Entity;


import lombok.Data;

/**
 * @author wangshuo
 *
 */
@Data
public class ResponseVO {
	private Integer code;//状态码
	private String message;//信息
	public ResponseVO(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	
	

}
