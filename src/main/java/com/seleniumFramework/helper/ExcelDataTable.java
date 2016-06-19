package com.seleniumFramework.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.seleniumFramework.dao.*;
import com.seleniumFramework.helper.*;
import com.seleniumFramework.model.*;
import com.seleniumFramework.reports.*;
import com.seleniumFramework.setup.*;
import com.seleniumFramework.util.SLFMConstants;

public class ExcelDataTable extends DataTable {

	String excelDataFile;
	String testCaseName;
	int currentIteration;
	
	ExcelHelper excelHelper;

	List<String> lstKeywords;
	Map<String, Map<String, String>> dataMap;

	public ExcelDataTable() {
		
		currentTestCase = new TestCase();
		lstKeywords = new ArrayList<String>();

		String testCaseName = "\"" + currentTestCase.testCaseName + "\"";

		excelDataFile = HelperClass.baseModel.excelDataPath + "/"
					+ currentTestCase.dataTableToRefer + ".xls";
		
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		
		criteriaMap.put(SLFMConstants.TEST_CASE_NAME, testCaseName);
		criteriaMap.put(SLFMConstants.ITERATIONS, new Integer(1));
		
		dataMap=ExcelHelper.readExcelSheet(excelDataFile, HelperClass.baseModel.excelDataSheet, criteriaMap);
		currentItdataMap=dataMap.get(String.valueOf(0));

	}
	
	public ExcelDataTable(TestCase testCase){
		
		excelHelper =new ExcelHelper();
		currentTestCase=testCase;
		
		lstKeywords =new ArrayList<String>();
		
		String testCaseName = "\"" + currentTestCase.testCaseName + "\"";

		excelDataFile = HelperClass.baseModel.excelDataPath + "/"
					+ currentTestCase.dataTableToRefer + ".xls";
		
		Map<String,Object>criteriaMap=new HashMap<String,Object>();
		
		criteriaMap.put(SLFMConstants.TEST_CASE_NAME, testCaseName);
		criteriaMap.put(SLFMConstants.ITERATIONS, new Integer(1));
		
		dataMap=ExcelHelper.readExcelSheet(excelDataFile, HelperClass.baseModel.excelDataSheet, criteriaMap);
		currentItdataMap=dataMap.get(String.valueOf(0));
	}
	
}
