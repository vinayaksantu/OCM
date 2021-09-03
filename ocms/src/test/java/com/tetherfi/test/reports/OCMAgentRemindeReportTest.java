package com.tetherfi.test.reports;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMAgentReminderReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMAgentRemindeReportTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}
	
	
	@Test(priority=1)
	public void ShowOCMAgentReminderReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentReminderReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed"); 
	}
	
	@Test(priority=2)
	public void SchedulereportinAgentReminderReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMAgentReminderReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMAgentReminderReportPage OCMAgentReminderReportPage=PageFactory.createPageInstance(driver, OCMAgentReminderReportPage.class);
		
	}
	
	
	
	
	
	
	
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMAgentRemindeReportTest",method.getName());
		driver.navigate().refresh();
	}
	
	
	
	

}
