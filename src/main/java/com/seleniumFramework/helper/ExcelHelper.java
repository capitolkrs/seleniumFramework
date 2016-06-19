package com.seleniumFramework.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.seleniumFramework.util.ExcelUtil;
import com.seleniumFramework.util.SLFMConstants;

public class ExcelHelper {

	public static Map<String, Map<String, String>> readExcelSheet(String excelFilePath, String excelSheetName,
			Map<String, Object> criteriaMap) {
		String fileName = excelFilePath;

		Map<String, Map<String, String>> dataMap = new LinkedHashMap<String, Map<String, String>>();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(fileName);
			Workbook workbook;

			if (ExcelUtil.getExcelFileExtn(excelFilePath).equals(SLFMConstants.XLSX)) {
				workbook = new XSSFWorkbook(fis);

			} else {
				workbook = new HSSFWorkbook(fis);
			}
			// get the first sheet

			Sheet sheet = workbook.getSheet(excelSheetName);

			// get Header Map

			Iterator rows = sheet.rowIterator();
			Map<Integer, String> headerMap = new LinkedHashMap<Integer, String>();

			if (headerMap.isEmpty()) {
				Row row = (Row) rows.next();
				Iterator<?> cells = row.cellIterator();

				while (cells.hasNext()) {

					Cell cell = (Cell) cells.next();
					headerMap.put(cell.getColumnIndex(), cell.getStringCellValue());

				}
			}

			if ((fileName.contains("RunManager") || fileName.contains("LastRun_FailedTC"))
					&& (headerMap.containsKey(8) && headerMap.get(8).contains("ALM TC Name"))) {
				HelperClass.baseModel.almTCColumnPresent = true;
			}
			boolean executeKeyPresent = false;
			boolean tstItrKeyPrestnt = false;
			boolean envURLPresent = false;
			boolean commonData = false;

			if (criteriaMap.containsKey(SLFMConstants.EXECUTE)) {

				executeKeyPresent = true;

			} else if (criteriaMap.containsKey(SLFMConstants.TEST_CASE_NAME)
					|| criteriaMap.containsKey(SLFMConstants.ITERATIONS)) {

				tstItrKeyPrestnt = true;

			} else if (criteriaMap.containsKey(SLFMConstants.ENVIRONMENT)) {

				envURLPresent = true;

			} else {
				commonData = true;
			}

			if (commonData) {
				Map<String, String> rowMap = new LinkedHashMap<String, String>();

				while (rows.hasNext()) {
					Row row = (Row) rows.next();
					Iterator cells = row.cellIterator();

					while (cells.hasNext()) {
						Cell cell = (Cell) cells.next();

						if (!cell.toString().isEmpty()) {

							if (cells.hasNext()) {
								rowMap.put(cell.toString(), ((Cell) cells.next()).toString());
							} else {

								rowMap.put(cell.toString(), "");
							}

						}

					}
				}
				dataMap.put(SLFMConstants.COMMON_DATA, rowMap);
				
			}else if(envURLPresent){
				
				Map<String, String> rowMap=new LinkedHashMap<String, String>();
				
				while(rows.hasNext()){
					
					Row row=(Row) rows.next();
					Iterator cells=row.cellIterator();
					
					while(cells.hasNext()){
						Cell cell=(Cell) cells.next();
						
						if(cell.toString().equalsIgnoreCase(criteriaMap.get(SLFMConstants.ENVIRONMENT).toString())){
							
							cell=(Cell) cells.next();
							rowMap.put(SLFMConstants.ENVIRONMENT_URL, cell.toString());
						}
					}
					
				}
				
				dataMap.put(SLFMConstants.COMMON_DATA, rowMap);
				
			}else if(tstItrKeyPrestnt){
				Map<String, String>rowMap=null;
				
				String tstCaseName=(String) criteriaMap.get((SLFMConstants.TEST_CASE_NAME));
				tstCaseName=tstCaseName.replace("\"", "");
				
				//there is more to be added ******
			}
		} catch (IOException e) {
			
		System.out.println(e.getMessage());
		}finally{
			
			try {
				
				fis.close();
				
			} catch (Exception e) {

				System.out.println(e.getMessage());
				
			}
		}

		return dataMap;

	}

}
