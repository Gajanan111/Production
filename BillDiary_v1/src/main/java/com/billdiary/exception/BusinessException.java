package com.billdiary.exception;
		
/**
 * @author gajanan
 *
 */
public class BusinessException extends Exception{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3149320897105268973L;
	
	/**
	 * Constructor
	 * @param s
	 */
	public BusinessException(String s) {
		super(s);
	}

}
