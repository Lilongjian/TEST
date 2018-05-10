package com.situ.mall.common.response;

import com.sun.net.httpserver.Authenticator.Success;

public enum ResponseCode {
	SUCCESS(0,"SUCCESS"),
	ERROR(1,"ERROR"),
	NEED_LOGIN(2,"NEED_LOGIN"),
	ILLEGAL_PERMISSION(3,"ILLEGA_PERMISSION");

	private int code;
	private String desc;
	
	private ResponseCode(int code,String desc){
		this.code = code;
		this.desc = desc;
		
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	
	

}
