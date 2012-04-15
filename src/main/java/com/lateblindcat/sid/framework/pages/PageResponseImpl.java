package com.lateblindcat.sid.framework.pages;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lateblindcat.sid.framework.ResponseCode;

public class PageResponseImpl implements PageResponse {

	private InputStream data;
	private String mimeType;

	public PageResponseImpl(String data, String mimeType) {
		this.data = new ByteArrayInputStream(data.getBytes());
		this.mimeType = mimeType;
	}

	public PageResponseImpl(InputStream data, String mimeType) {
		this.data = data;
		this.mimeType = mimeType;
	}

	@Override
	public InputStream getContent() {
		return data;
	}

	@Override
	public ResponseCode getStatus() {
		return ResponseCode.SC_OK;
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
