package com.def.springmvc.web.interceptor;

/**
 * 用户的权限异常
 * @author shunh
 *
 */
public class AuthorityException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthorityException() {
	}

	public AuthorityException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorityException(String message) {
		super(message);
	}

	public AuthorityException(Throwable cause) {
		super(cause);
	}

}
