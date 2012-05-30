package com.lateblindcat.sid.framework.pages;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lateblindcat.sid.framework.ResponseCode;
import com.lateblindcat.sid.framework.StringExpression;

public class PageResponseImpl implements PageResponse {

	private InputStream data;
	private String mimeType;
	private ResponseCode status = ResponseCode.SC_OK;

	public PageResponseImpl(StringExpression data, String mimeType) {
		this.data = new ByteArrayInputStream(data.eval().getBytes());
		this.mimeType = mimeType;
	}

	@Deprecated
	public PageResponseImpl(InputStream data, String mimeType) {
		this.data = data;
		this.mimeType = mimeType;
	}
	
	public PageResponseImpl(ResponseCode status) {
		this.status = status;
	}

	@Override
	public InputStream getContent() {
		return data;
	}

	@Override
	public ResponseCode getStatus() {
		return this.status;
	}

	@Override
	public String getContentType() {
		return mimeType;
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
