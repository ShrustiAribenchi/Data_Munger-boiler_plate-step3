package com.stackroute.datamunger.query;

public class DataTypeDefinitions {

	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the data type for all columns for all data types
	 */

	String[] dataType;

	public DataTypeDefinitions(String[] dataType) {
		this.dataType = dataType;
	}

	public String[] getDataTypes() {

		return dataType;
	}

	public void setDataTypes(String[] dataType) {
		this.dataType = dataType;
	}}
