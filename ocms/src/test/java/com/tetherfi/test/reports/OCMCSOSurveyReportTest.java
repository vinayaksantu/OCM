package com.tetherfi.test.reports;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMCSOSurveyReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMCSOSurveyReportTest extends BaseTest{

	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {        
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());       	
	}


	@Test(priority=0, description="To veriy CSO Survey report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMCSOSurveyReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMCSOSurveyReportPage CSOSurveyReportPage=PageFactory.createPageInstance(driver, OCMCSOSurveyReportPage.class);
		Assert.assertTrue(CSOSurveyReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails),"Main report Data mismatch");
		System.out.println("UI and DB data matched for OCM CSO Survey report");
	}


	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("CSOSurveyReport", method.getName());
		driver.navigate().refresh();
	}

}
