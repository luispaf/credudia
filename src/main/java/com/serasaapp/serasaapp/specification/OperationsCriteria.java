package com.serasaapp.serasaapp.specification;

public class OperationsCriteria {

	private OperationsCriteria() {
		throw new IllegalStateException("Utility class");
	}
	/**
	 * Equals operator
	 */
	public static final String EQ = "EQ";
	/**
	 * Like operator
	 */
	public static final String LK = "LK";
	/**
	 * Greater Than operator
	 */
	public static final String GT = "GT";
	/**
	 * Less Than operator
	 */
	public static final String LT = "LT";
	/**
	 * In  operator
	 */
	public static final String IN = "IN";
	
	/**
	 * REGEX FOR SPLIT IN OPERATION
	 */
	public static final String REGEX_SPLIT_OP = "([+]" + EQ + "[+]|[+]" + LT + "[+]|[+]" + GT + "[+]|[+]" + LK + "[+])";
	
}
