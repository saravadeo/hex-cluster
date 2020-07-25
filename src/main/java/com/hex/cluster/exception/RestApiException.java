package com.hex.cluster.exception;

public class RestApiException extends RuntimeException {

	private static final long serialVersionUID = -259232893433931511L;

	private int status;
	private String title;
	private String messageCode;
	private Object[] messageArgs;

	public RestApiException() {
	}

	public RestApiException(int status, String title, String messageCode, Object[] messageArgs) {
		super();
		this.status = status;
		this.title = title;
		this.messageCode = messageCode;
		this.messageArgs = messageArgs;
	}

	public RestApiException(int status, String title, String messageCode) {
		this(status, title, messageCode, null);
	}

	public RestApiException(Exception e, int status, String title, String messageCode) {
		super(e);
		this.status = status;
		this.title = title;
		this.messageCode = messageCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}
}
