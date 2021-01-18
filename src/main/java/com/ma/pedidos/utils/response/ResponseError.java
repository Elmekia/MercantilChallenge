package com.ma.pedidos.utils.response;

public class ResponseError {

	public ResponseError(String error) {
		this.error = error;
	}
	
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
