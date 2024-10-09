package com.example.demo.Exception;

public class ExceptionsList extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8159849447676152364L;
	
	public Exception ex;
	public ExceptionsList(Exception ex) {
		System.out.println("Exxxxxxxxxxxxxxxxxxxxxx: " + ex.getLocalizedMessage());
		this.ex = ex;
	
	}
}
