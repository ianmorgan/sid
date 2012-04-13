package com.lateblindcat.sid.framework.pages;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lateblindcat.sid.framework.ResponseCode;

public class PageResponseImpl implements PageResponse {

	private String data;
	private String mimeType;
	public PageResponseImpl(String data, String mimeType){
		this.data = data;
		this.mimeType = mimeType;
	}
	
	@Override
	public InputStream getContent() {
		return new ByteArrayInputStream(data.getBytes());
	}

	@Override
	public ResponseCode getStatus() {
		return ResponseCode.SC_OK;
	}

	@Override
	public String getContentType() {
		return "text/html";
	}

	@Override
	public boolean isInline() {
		return true;
	}

	@Override
	public String getFileName() {
		return null;
	}

}
