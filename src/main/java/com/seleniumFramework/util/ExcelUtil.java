package com.seleniumFramework.util;

public class ExcelUtil {

	public static String getExcelFileExtn(String fileName) {
		String excelExtn=null;
		excelExtn=fileName.substring(fileName.indexOf("."),fileName.length());
	
		return excelExtn;
	}

}
