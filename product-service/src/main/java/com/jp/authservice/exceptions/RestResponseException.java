package com.jp.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class RestResponseException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String message;
	private HttpStatus status;

	public RestResponseException() {
	}

	public RestResponseException(String msg) {
		setMessage(msg);
		setStatus(HttpStatus.BAD_REQUEST);
	}

	public RestResponseException(String msg, HttpStatus status) {
		setMessage(msg);
		setStatus(status);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
