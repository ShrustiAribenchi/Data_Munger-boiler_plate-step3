package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	String fileName;
	BufferedReader br = null;

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		br = new BufferedReader(new FileReader(fileName));
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file. Note: Return type of the method will be
	 * Header
	 */

	@Override
	public Header getHeader() throws IOException {

		Header Head = null;
		br.mark(1);
		String header = br.readLine();
		String[] head = header.split(",");
		br.reset();
		Head = new Header(head);
		return Head;

	}
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. Note: Return Type of the method will be
	 * DataTypeDefinitions
	 */

	@Override
	public DataTypeDefinitions getColumnType() throws IOException {

		Object obj;
		int i = 0;
		String type = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String headerRow = br.readLine();
		String headerArgs[] = headerRow.split(",");
		String secondRow = br.readLine();
		secondRow += " ,";
		String[] dataElements = secondRow.split(",");
		String[] dataTypes = new String[headerArgs.length];
		if (dataElements != null) {
			for (i = 0; i < dataElements.length; i++) {

				try {
					obj = Integer.parseInt(dataElements[i]);
					if (obj instanceof Integer)

						type = obj.getClass().getName();
					dataTypes[i] = type;
				} catch (NumberFormatException e) {

					try {
						obj = Double.parseDouble(dataElements[i]);
						if (obj instanceof Double) {
							type = obj.getClass().getName();
							dataTypes[i] = type;
						}
					} catch (Exception e2) {
						obj = dataElements[i];
						if (obj instanceof String)
							type = obj.getClass().getName();
						dataTypes[i] = type;
					}
				}
			}

		}

		br.close();
		DataTypeDefinitions types = new DataTypeDefinitions(dataTypes);
		return types;

	}

}