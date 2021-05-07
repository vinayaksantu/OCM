package com.tetherfi.test.reports;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.OCMRoleAccessReportPage;
import com.tetherfi.pages.OCMUserListingReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMAgentAuxReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMRoleAccessReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}
	
	@Test(priority=1,description="To verify Show Report for Single Date")
	public void ShowReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedwithoutDateAndTime(reportDetails),"Show report assertion failed");     
	}

	@Test(priority=2,description="To verify Show Report in New Page for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage1(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayedwithoutDateAndTime(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=3,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);    
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);             
		Assert.assertTrue(RoleAccessReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("RoleAccessReport","Maximize");
		Assert.assertTrue(RoleAccessReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("RoleAccessReport","Minimize");	
	} 

	@Test(priority=4,description="To Verify Dropdown for All the Columns")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);	
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}  

	@Test(priority=5,description="To Verify Columns Headers are Enabled")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);	
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	} 

	@Test(priority=6,description="To Verify Columns Headers are Disabled")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertFalse(RoleAccessReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=7,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		Assert.assertTrue(RoleAccessReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}
	
	@Test(priority=8,description="To Verify Arrow move for Previous and Next page")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=9,description="To Verify Total Number of Items Per Page Details")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	} 

	@Test(priority=10,description="To Verify Number of Items Per Page Selection")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=11,description="To verify search equals")
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();	
		Assert.assertTrue(RoleAccessReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}     

	@Test(priority=12,description="To verify search without providing data in searchbox")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		RoleAccessReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(RoleAccessReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}
	
	@Test(priority=13,description="To verify search IsNotEquals")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}   

	@Test(priority=14,description="To verify search Contains")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}   

	@Test(priority=15,description="To verify search doesnotContains")
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}    

	@Test(priority=16,description="To verify search StartsWith")
	public void VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=17,description="To verify search EndsWith")
	public void VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=18, description="To verify Group by functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		RoleAccessReportPage.selectPageSize();
		Assert.assertTrue(RoleAccessReportPage.groupby());
		screenshot.captureScreen("RoleAccessReport", "GroupBy");
		Assert.assertTrue(RoleAccessReportPage.groupby());
		screenshot.captureScreen("RoleAccessReport", "AlreadyGroupBy");
	}

	@Test(priority=19,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		RoleAccessReportPage.selectPageSize();
		Assert.assertTrue(RoleAccessReportPage.verifySorting(),"Sorting assertion failed");
	}

	@Test(priority=20,description="To export the data using Export to excel")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.exportToExcel();
		Assert.assertTrue(RoleAccessReportPage.verifyReportExportedToExcel(),"export report assertion failed");
	}

	@Test(priority=21,description="To verify search Clear Search")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReportforCommonReports(reportDetails);  
		OCMRoleAccessReportPage RoleAccessReportPage=PageFactory.createPageInstance(driver,OCMRoleAccessReportPage.class);
		RoleAccessReportPage.closeGroupBy();
		Assert.assertTrue(RoleAccessReportPage.verifySearchClear(reportDetails));    	
	}
	
	@Test(priority=22,description="To verify clear all functionality using clear all btn ")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleAccessReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown1(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("UOBRoleAccessReport_3.4.3.14",method.getName());
		driver.navigate().refresh();
	}
}