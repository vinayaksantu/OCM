package com.tetherfi.test.reports;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMChatTransferReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMChatTransferReportTest extends BaseTest {
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
	public void ShowReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	} 
	
	@Test(priority=2)
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}  
	
	@Test(priority=3,description="To verify Chat Transfer report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage ChatTransferReportPage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(ChatTransferReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails), "Main report Data Mismatch");   		
		System.out.println("Database Validation Completed Succesfully" +" : "+"UI and Database data is matched"); 		
	}

	@Test(priority=4)
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifySearchByTextbox(reportDetails));
	}
	
	@Test(priority=5)
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		Assert.assertTrue(chatTransferpage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}   
	
	@Test(priority=6)
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		chatTransferpage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(chatTransferpage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}  
	
	@Test(priority=7)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	} 
	
	@Test(priority=8)
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifySearchContains(reportDetails.getSearchStr()));
	} 
	
	@Test(priority=9)
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}  
	
	@Test(priority=10)
	public void VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=11)
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}
	
	@Test(priority=12)
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifySearchClear(reportDetails));    	
	}
//	Advanced Search AND and OR criteria missing
	@Test(priority=13, description="To verify Advance Search Is Equal to Search criteria")
	public void verifyAdvancedSearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(chatTransferpage.verifyAdvanceSearchIsEqualTo(reportDetails));            
	}
	
	@Test(priority=14,description="Verify Advance Search with Criteria Is not Equal to")
	public void verifyAdvancedSearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(chatTransferpage.verifyAdvancedSearchIsNotEqualTo(reportDetails));            
	}

	@Test(priority=15,description="Verify Advance Search with Criteria Contains")
	public void verifyAdvancedSearchContainsCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(chatTransferpage.verifyAdvanceSearchContains(reportDetails));            
	}
	
	@Test(priority=16,description="Verify Advance Search with Criteria Does not Contain")
	public void verifyAdvancedSearchDoesnotContainsCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(chatTransferpage.verifyAdvanceSearchDoesNotContains(reportDetails));            
	}

	@Test(priority=17,description="Verify Advance Search with Criteria Starts with")
	public void verifyAdvancedSearchStartsWithCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(chatTransferpage.verifyAdvanceSearchStartsWith(reportDetails));            
	}
	
	@Test(priority=18,description="Verify Advanced Search Criteria Ends With")
	public void verifyAdvancedSearchEndsWithCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);        
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(chatTransferpage.verifyAdvanceSearchEndsWith(reportDetails));            
	}

	@Test(priority=19)
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(ocmReportsPage.ClearAdvFilters(reportDetails));
	}

	@Test(priority=20)
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.groupby());
		screenshot.captureScreen("OCMChatTransferReport", "GroupBy");
		Assert.assertTrue(chatTransferpage.groupby());
		screenshot.captureScreen("OCMChatTransferReport", "AlreadyGroupBy");
	}
	
	@Test(priority=21)
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}
	
	@Test(priority=22)
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);             
		Assert.assertTrue(chatTransferpage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMChatTransferReport","Maximize");
		Assert.assertTrue(chatTransferpage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMChatTransferReport","Minimize");	
	} 
	
	@Test(priority=23)
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}   
	
	@Test(priority=24)
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}  
	
	@Test(priority=25)
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertFalse(chatTransferpage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	} 
	
	@Test(priority=26)
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}    
	
	@Test(priority=27)
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	} 
	
	@Test(priority=28)
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}  
	
	@Test(priority=29,enabled=false)
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
		
	@Test(priority=30)
	public void ScheduleOCMChatTransferReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}  
	
	@Test(priority=31)
	public void ExportReportForSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=32,dependsOnMethods ="ExportReportForSingleDate")
	public void ViewDownloadedReportInReportDownloadsPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  
	
	@Test(priority=33,dependsOnMethods ="ViewDownloadedReportInReportDownloadsPage",description="To verification of exported excel in Report downloads")
	public void verifyExportedData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Chat Transfer Report"));		
	}
	
	@Test(priority=34)
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 
	
	@Test(priority=35,dependsOnMethods ="ShowReportForDateRange")
	public void ShowReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}   
	
	@Test(priority=36,dependsOnMethods ="ShowReportInNewPageForDateRange")
	public void ExportReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	} 
	
	@Test(priority=37,dependsOnMethods ="ExportReportForDateRange")
	public void ViewDownloadedReportInReportDownloadsPageForDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	
	@Test(priority=38,dependsOnMethods ="ViewDownloadedReportInReportDownloadsPageForDateRange",description="To verification of exported excel in Report downloads")
	public void verifyDownloadedReportData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Chat Transfer Report"));		
	}
	
	@Test(priority=39)
	public void ScheduleOcmChatTransferReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}
		
	@Test(priority=40)
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifyExportToExcel(filePath1));
	}
	
	@Test(priority=41,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMChatTransferReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		Assert.assertTrue(chatTransferpage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=42)
	public void SchedulereportinchatTransferpage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		chatTransferpage.navigateToExportSchedulerPage();    
		Assert.assertTrue(chatTransferpage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
	}
	
	@Test(priority=43)
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMChatTransferReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		agntloginlogoutPage.exportToExcel();
		Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
	}  
	
	@Test(priority=44,dependsOnMethods ="ExportToExcel")
	public void ViewExportedExcelDataInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMChatTransferReportPage chatTransferpage=PageFactory.createPageInstance(driver,OCMChatTransferReportPage.class);
		chatTransferpage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}   
	
	@Test(priority=45,dependsOnMethods ="ViewExportedExcelDataInReportDownloadsPage",description="To verification of exported excel in Report downloads")
	public void verifyExportedExcelData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Chat Transfer Report"));		
	}

	@Test(priority=46,description="Delete record in Reports Download without Delete reason for date range")
	public void DeleteRecordWithoutDeleteReasonInReportDownloadsForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=47,description="Cancel Button in Reports Download Delete Button")
	public void VerifyDeleteCancelBtnInReportDownloadsPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
	
	@Test(priority=48,description="Delete Record at Reports download Button")
	public void DeleteRecordInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatTransferReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("OCMChatTransferReportResults_3.4.3.14", method.getName());
		driver.navigate().refresh();
	}

}
