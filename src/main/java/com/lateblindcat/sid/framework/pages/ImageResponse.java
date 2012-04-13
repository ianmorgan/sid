package com.lateblindcat.sid.framework.pages;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lateblindcat.sid.framework.ResponseCode;

public class ImageResponse implements PageResponse {

	private InputStream imageData;
	public ImageResponse(byte[] image, String type){
		this.imageData = new ByteArrayInputStream(image);
	}
	
	public ImageResponse(InputStream image, String type){
		this.imageData = image;
	}
	
	@Override
	public InputStream getContent() {
		return imageData;
	}

	@Override
	public ResponseCode getStatus() {
		return ResponseCode.SC_OK;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "image/jpeg";
	}

	@Override
	public boolean isInline() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

}
