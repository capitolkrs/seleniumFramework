package com.seleniumFramework.setup;

import com.seleniumFramework.helper.DataTable;
import com.seleniumFramework.helper.ExcelDataTable;
import com.seleniumFramework.helper.HelperClass;
import com.seleniumFramework.model.*;
public class TestSetup {

	/**
	 * @param currentTestCase
	 * @return
	 * @throws Exception
	 */
	
	
	public static DataTable getDataHelper(TestCase currentTestCase) {
		DataTable dataHelper;
		
		if(HelperClass.baseModel.runDataSource.toLowerCase().equals("excel")){
			dataHelper=new ExcelDataTable(currentTestCase);
			
		}//else if(for xml)
		
		else{
			dataHelper=new ExcelDataTable();
		}
		
		return dataHelper;
	}

}
