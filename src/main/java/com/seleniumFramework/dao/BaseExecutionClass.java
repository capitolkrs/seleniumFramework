package com.seleniumFramework.dao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.seleniumFramework.helper.DataTable;
import com.seleniumFramework.model.TestCase;
import com.seleniumFramework.reports.Report;
import com.seleniumFramework.setup.TestSetup;


/**
 * @author capitalterefe
 *
 */
public class BaseExecutionClass {
	
	public Report report;
	public DataTable dataTable;
	public WebDriver driver;
	private TestCase testCase;
	
	//Driver
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//Report
	
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	
	//DataHelper
	
	public DataTable getDataHelper() {
		return dataTable;
	}
	public void setDataHelper(DataTable dataTable) {
		this.dataTable = dataTable;
		
	//TestCase
	}
	public TestCase getTestCase() {
		return testCase;
	}
	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}
	
/**	
 * constructor overload
 * @param currentTestCase
 * @throws Exception
 * 
*/	
	public BaseExecutionClass(TestCase currentTestCase) {
		this.testCase=currentTestCase;
		this.dataTable=TestSetup.getDataHelper(currentTestCase);
		
	}
	

}
