package com.seleniumFramework.util;

public class ExcelUtil {
/**
 * get excel file extension
 * @param fileName
 * @return
 */
	public static String getExcelFileExtn(String fileName) {
		String excelExtn=null;
		excelExtn=fileName.substring(fileName.indexOf("."),fileName.length());
	
		return excelExtn;
	}

}
