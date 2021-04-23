package com.phbook.exception;

public class NoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 516777048545738700L;

	public NoDataFoundException() {

	}

	public NoDataFoundException(String msg) {
		super(msg);
	}

}
