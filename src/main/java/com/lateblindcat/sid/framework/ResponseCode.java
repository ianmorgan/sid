package com.lateblindcat.sid.framework;

public enum ResponseCode {
	SC_OK (200);
	
	private int httpCode;
	ResponseCode(int value){
		this.httpCode = value;
	}
	
	public int getHttpCode(){
		return httpCode;
	}
}
