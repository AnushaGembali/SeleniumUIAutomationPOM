package com.qa.opencart.tests;

import org.testng.annotations.DataProvider;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductDataProvider {

	@DataProvider
	public Object[][] getSearchProductname(){
		return new Object[][] {
			{"mac", "MacBook Pro"}
		};
		
	}
	
	@DataProvider(name = "dataForSelectingProd")
	public Object[][] getDataForSelectProd(){
		return new Object[][] {
			{"mac","MacBook"},
			{"mac","MacBook Pro"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@DataProvider
	public Object[][] getDataSearchProdCount(){
		return new Object[][] {
			{"mac",4},
			{"samsung",2},
			{"apple",1 },
			{"anusha",1}
		};
	}
	
	@DataProvider
	public Object[][] getRegistrationData(){
		return ExcelUtil.getTestData(AppConstants.REG_SHEET_NAME);
	}
}
