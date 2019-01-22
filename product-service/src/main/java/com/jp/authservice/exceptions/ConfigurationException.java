package com.jp.authservice.exceptions;

public class ConfigurationException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String message;

	public ConfigurationException() {
	}

	public ConfigurationException(String msg) {
		setMessage(msg);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
