package com.tetherfi.test.reports;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMAgentLoginLogoutReportPage;
import com.tetherfi.pages.OCMIvrCallTraceReportPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMIVRCallTraceReportTest extends BaseTest {
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
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
	} 

	@Test(priority=2,description="To verify Show Report in New Page for Single Date")
	public void ShowReportInNewPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}

	@Test(priority=3, description="To verify IVR call Trace report UI data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage IVRCallTraceReportPage=PageFactory.createPageInstance(driver, OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(IVRCallTraceReportPage.verifyDatabase(reportDetails.getQuery(), reportDetails),"Main Report Data Mismatch");    
		System.out.println("Main Report Data Match Successfull");
		List<String> UCIDList = new ArrayList<>();
		UCIDList=IVRCallTraceReportPage.getUCID();
		System.out.println("List of UCID's is"+ UCIDList);
		int k=0;
		for (int i=0;i<UCIDList.size();i++) {
			System.out.println(UCIDList.size());
			if(k==10) {
				IVRCallTraceReportPage.goToNextPage();
				k=k-10;			
			}
			Thread.sleep(3000);
			IVRCallTraceReportPage.clickOnUCIDRowOnMainReport(k);
			Assert.assertTrue(IVRCallTraceReportPage.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, UCIDList.get(i)),"Menu Traversal Grid data mismatch for UCID" + UCIDList.get(i));
			System.out.println("Menu Traversal Data match Successful for UCID" + " : "+ UCIDList.get(i));

		}
 }

	@Test(priority=4,description="To verify Search by feature using Caller ID")
	public void VerifySearchByFeature() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);    
		Assert.assertTrue(ivrCallTraceReportPage.verifySearchByTextbox(reportDetails));
	}

	@Test(priority=5,description="To verify the search using Is equal to search criteria")
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=6,description="To verify search using Is Not Equal to Search criteria")
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
	}

	@Test(priority=7,description="To verify search using contains search criteria")
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchContains(reportDetails.getSearchStr()));
	}

	@Test(priority=8,description="To verify Search using Does not Contain search criteria")
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
	}

	@Test(priority=9,description="To Verify search using starts with search criteria")
	public void VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifySearchStartsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=10,description="To verify search using Ends with search criteria")
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifySearchEndsWith(reportDetails.getSearchStr()));
	}

	@Test(priority=11,description="To perform search without providing text to search")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ivrCallTraceReportPage.searchwithoutextsearch(reportDetails);
		Assert.assertEquals(ivrCallTraceReportPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=12,description="To verify clear search filters functionality")
	public void SearchClear() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifySearchClear(reportDetails));    	
	}

	@Test(priority=13,description="To verify advanced search using AND criteria")
	public void verifyAdvancedSearchANDCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMIvrCallTraceReportPage.advancedSearchANDCriteria(reportDetails));   	
	}

	@Test(priority=14,description="To verify advanced search using OR criteria")
	public void verifyAdvancedSearchORCriteria() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
		ocmReportsPage.chooseReport(reportDetails);
		Assert.assertTrue(OCMIvrCallTraceReportPage.advancedSearchORCriteria(reportDetails));
	}

	@Test(priority=15,description="To verify advanced search using Is equal to Criteria")
	public void verifyAdvancedSearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyAdvanceSearchIsEqualTo(reportDetails));
	}

	@Test(priority=16,description="To verify advanced search using search is not equal to criteria")
	public void verifyAdvancedSearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyAdvanceSearchIsNotEqualTo(reportDetails));
	}

	@Test(priority=17,description="To verify advanced search using contains search criteria")
	public void verifyAdvancedSearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyAdvanceSearchContains(reportDetails));
	}

	@Test(priority=18,description="To verify advanced search using does not contains criteria")
	public void verifyAdvancedSearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyAdvanceSearchDoesNotContains(reportDetails));
	}

	@Test(priority=19,description="To verify advanced search using Starts With Search criteria")
	public void verifyAdvancedSearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyAdvanceSearchStartsWith(reportDetails));
	}

	@Test(priority=20,description="To verify advanced search using Ends With Search criteria")
	public void verifyAdvancedSearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(6);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyAdvanceSearchEndsWith(reportDetails));
	}

	@Test(priority=21,description="To Clear advanced search filters")
	public void ClearfiltersAdvSrch() throws Exception{ 	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
		Assert.assertTrue(OCMReportsPage.ClearAdvFilters(reportDetails));
	}
	
	@Test(priority=22,description="To verify clear filters button")
	public void ClearAll() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.ClearHomepgDrpDown(reportDetails);
		Assert.assertEquals(OCMReportsPage.getSuccessMessage(),"Filters cleared successfully!","Invalid filter assertion");
	}

	@Test(priority=23,description="To verify minimize and maximize")
	public void OCMWindow() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);             
		Assert.assertTrue(OCMIvrCallTraceReportPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("OCMIvrCallTraceReport","Maximize");
		Assert.assertTrue(OCMIvrCallTraceReportPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("OCMIvrCallTraceReport","Minimize");	
	}

	@Test(priority=24,description="To Verify dropdown of all the column headers")
	public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);		
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=25,description="To verify column header enable")
	public void VerifyColumnsHeaderEnable() throws Exception {  	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);		
		OCMReportsPage OCMReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);	
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=26,description="To Verify column header disable")
	public void VerifyColumnsHeaderDisable() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertFalse(OCMIvrCallTraceReportPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=27,description="To verify previous and next page button")
	public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	} 

	@Test(priority=28,description="Verify Pagination, Move to First and Last Page")
	public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}

	@Test(priority=29,description="To verify total number of items per page")
	public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage IvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(IvrCallTraceReportPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=30,description="To verfiy number of items selected per page")
	public void VerifyNumberOfItemsPerPageSelection() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage OCMIvrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(OCMIvrCallTraceReportPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=31,description="To verify Group By functionality")
	public void GroupBy() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.groupby());
		screenshot.captureScreen("OCMIvrCallTraceReport", "GroupBy");
		Assert.assertTrue(ivrCallTraceReportPage.groupby());
		screenshot.captureScreen("OCMIvrCallTraceReport", "AlreadyGroupBy");
	}

	@Test(priority=32,description="To verify navigation to export scheduler page from reports manager")
	public void ScheduleOCMIvrCallTraceReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=33,description="To Export Report from Report Manager page")
	public void ExportReportForSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.exportReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=34,dependsOnMethods ="ExportReportForSingleDate",description="To view exported report in report downloads page")
	public void ViewDownloadedReportInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}  

	@Test(priority=35,enabled=false,dependsOnMethods ="ViewDownloadedReportInReportDownloadsPage",description="To verify downloaded report data against UI data")
	public void VerifyDownloadedReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM IVR Call Trace Report"));	
	}
	
	@Test(priority=36,description="To verify record deletion from Report downloads page")
	public void DeleteRecordInReportDownloadsForSingleDate() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}
	
	@Test(priority=37,description="To verify Show Report for date range")
	public void ShowReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	} 

	@Test(priority=38,description="To verify Show Report for date range in new page")
	public void ShowReportInNewPageForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(OCMReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		OCMReportsPage.switchBackToParentWindow();
	}

	@Test(priority=39,description="To verify Export scheduler button")
	public void ScheduleReportforDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=40,description="To verify export report for date range from report manager page")
	public void ExportReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}

	@Test(priority=41,dependsOnMethods ="ExportReportForDateRange",description="To view exported report in report downloads page")
	public void ViewDownloadedReportInReportDownloadsPageForDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}

	@Test(priority=42,enabled=false,dependsOnMethods ="ViewDownloadedReportInReportDownloadsPageForDateRange",description="To verify downloaded report data against UI data")
	public void VerifyDownloadedReportData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM IVR Call Trace Report"));		
	}

	@Test(priority=43,description="To Delete downloaded report from report downloads without delete reason")
	public void DeleteRecordWithoutDeleteReason() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
		Assert.assertTrue(ocmReportsPage.deleteWithoutDeleteReason(reportDetails),"empty delete reason record assertion failed");		
	}

	@Test(priority=44,description="To verify delete cance button in report downloads")
	public void VerifyDeleteCancelBtnForDateRange() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();    	
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}

	@Test(priority=45,description="To verify record deletion from Report downloads page")
	public void DeleteRecordInReportDownloads() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}

	@Test(priority=46,description="To export the data using Export page functionality")
	public void ExportPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		OCMReportsPage.showReport(reportDetails);    	
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyExportToExcel(filePath1));
	}

	@Test(priority=47,dependsOnMethods="ExportPage",description="To verify Exported Page data Against UI data")
	public void VerifyExportedPage() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMIvrCallTraceReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=48,description="Scheduled report button in IvrCallTrace report page")
	public void SchedulereportInOCMIvrCallTraceReportPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);       
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ivrCallTraceReportPage.navigateToExportSchedulerPage();    
		Assert.assertTrue(ivrCallTraceReportPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");		    	 
	}

	@Test(priority=49,description="To export the data using Export to Excel")
	public void ExportToExcel() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ivrCallTraceReportPage.exportToExcel();
		Assert.assertTrue(ivrCallTraceReportPage.verifyReportExported(),"export report assertion failed");
	} 

	@Test(priority=50,dependsOnMethods ="ExportToExcel",description="To verify exported excel data is shown in report downloads page")
	public void viewExportedExcelDataInReportDownloadsPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);  
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		ivrCallTraceReportPage.viewDownloadedReportInReportsDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
	}

	@Test(priority=51,enabled=false,dependsOnMethods ="viewExportedExcelDataInReportDownloadsPage",description="To verify Exported Excel data against UI data")
	public void verifyExportedExcelData() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM IVR Call Trace Report"));		
	}

	@Test(priority=52,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifySorting(),"item per page assertion failed");
	} 

	@Test(priority=53,enabled=false,description="To Verify Menu traversal in drill down grid")
	public void VerifyDrillDownMenuTraversal() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyMenuTraversalButton(reportDetails),"menu traversal error");
	}

	@Test(priority=54,enabled=false,description="To Verify Call flow Diagram in drill down Page")
	public void VerifyCallFlowDiagram() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyCallflowDiagram(reportDetails),"Call flow error");
	} 

	@Test(priority=55,enabled=false,description="To Verify Call flow Grid in drill down Page")
	public void VerifyCallFlowGrid() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IVRCallTraceReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMIvrCallTraceReportPage ivrCallTraceReportPage=PageFactory.createPageInstance(driver,OCMIvrCallTraceReportPage.class);
		Assert.assertTrue(ivrCallTraceReportPage.verifyCallflowGrid(reportDetails),"Call flow grid error");
	}

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("IVRCallTraceReportTest",method.getName());
		driver.navigate().refresh();
	}
}