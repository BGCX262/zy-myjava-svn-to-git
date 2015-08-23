package com.def.springmvc.web.session;

public class UserSessionException extends RuntimeException {
	public UserSessionException() {
	}

	public UserSessionException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserSessionException(String message) {
		super(message);
	}

	public UserSessionException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 0x9fa30e2b9c1072b6L;
}
