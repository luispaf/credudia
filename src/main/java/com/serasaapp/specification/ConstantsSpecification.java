package com.serasaapp.specification;

public class ConstantsSpecification {

	private ConstantsSpecification() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Indicador do início de um elemento de tempo em um valor de data/hora, conforme descrito na ISO 8601
	 * Ex: 2019-10-16T11:00:00
	 */
	public static final String TIME_DELIMITER = "T";
	
	public static final String OB_DELIMITER = "[,]";
}
