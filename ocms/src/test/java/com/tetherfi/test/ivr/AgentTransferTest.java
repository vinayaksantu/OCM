package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.AgentTransferDetails;
import com.tetherfi.model.ivr.HolidayListDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;
import com.tetherfi.pages.AgentTransferPage;
import com.tetherfi.pages.HolidayListPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentTransferTest extends BaseTest{
	
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
    public void NavigateToAgentTransferPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        screenshot.captureScreen(driver, "IVR Page","AgentTransferTest");
        ivrPage.navigateToAgentTransferPage();
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertTrue(agentTransferPage.isAgentTransferPageDisplayed(), "Holiday List page assertion failed");
        screenshot.captureScreen(driver,"AgentTransfer Page","AgentTransferTest");
    }
	
	/*@Test(priority=1)
	public void AgentTransferPage() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
    	Assert.assertTrue(agentTransferPage.verifylogo(),"Agent transfer logo assertion failed");
    	Assert.assertTrue(agentTransferPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","AgentTransferTest");
    	Assert.assertTrue(agentTransferPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","AgentTransferTest");
	}
	
	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertTrue(agentTransferPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
	@Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertTrue(agentTransferPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
	@Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertFalse(agentTransferPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
	
	
	@Test(priority=5)
	public void addNewAgentTransferRecord() throws Exception {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        Assert.assertTrue(agentTransferPage.addCancel(agentTransferDetails), "Add cancel assertion failed");
    	screenshot.captureScreen(driver,"Add Cancel","AgentTransferTest");
    	agentTransferPage.addNewAgentTransferRecord(agentTransferDetails);
        Assert.assertTrue(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"Record Created Successfully","AgentTransferTest");
	}
	
	@Test(priority=6)
    public void VerifyAuditTrialReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAgentTransferCreate(agentTransferDetails,"Create"));
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForCreate","AgentTransferTest");   
    }
	
	@Test(priority=7)
	public void DuplicateRecord() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addNewAgentTransferRecord(agentTransferDetails);
        Assert.assertFalse(agentTransferPage.verifymessage(), "Duplicate Record creation assertion failed" );
    	screenshot.captureScreen(driver,"Duplicate Record","AgentTransferTest");
	}
	
	@Test(priority=8)
	public void AddRecordDifferentVDNSameOption() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addNewAgentTransferRecord(agentTransferDetails);
        Assert.assertTrue(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"AddRecordDifferentVDNSameOption","AgentTransferTest");
	}
	
	@Test(priority=9)
	public void AddRecordDifferentOptionSameVDN() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addNewAgentTransferRecord(agentTransferDetails);
        Assert.assertTrue(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"AddRecordDifferentOptionSameVDN","AgentTransferTest");
	}
	
	@Test(priority=10)
	public void AddRecordDifferentMenuID() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addNewAgentTransferRecord(agentTransferDetails);
        Assert.assertTrue(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"AddRecordDifferentMenuID","AgentTransferTest");
	}
	
	@Test(priority=11)
	public void AddRecordwithoutMenuId() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addRecordWithoutMenuId(agentTransferDetails);
        Assert.assertFalse(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"AddRecordDifferentMenuID","AgentTransferTest");
	}
	
	@Test(priority=12)
	public void AddRecordwithoutVDN() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addRecordWithoutVDN(agentTransferDetails);
        Assert.assertFalse(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"AddRecordwithoutVDN","AgentTransferTest");
	}
	
	@Test(priority=13)
	public void AddRecordwithoutOption() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addRecordWithoutOption(agentTransferDetails);
        Assert.assertFalse(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"AddRecordwithoutOption","AgentTransferTest");
	}
	
	@Test(priority=14)
	public void AddRecordwithoutVdnDecription() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
    	agentTransferPage.addRecordWithoutVDNDescription(agentTransferDetails);
        Assert.assertFalse(agentTransferPage.verifymessage(), "Record creation assertion failed" );
    	screenshot.captureScreen(driver,"AddRecordwithoutVdnDescription","AgentTransferTest");
	}
	
	//@Test(priority=15)//,dependsOnMethods = ("addNewHolidayListRecord"))
  	public void editCancelAgentTransferRecord() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
  		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        Assert.assertTrue(agentTransferPage.editCancelbtn(agentTransferDetails));
      	screenshot.captureScreen(driver, "edit Cancel","AgentTransferTest");
	}
	
	//@Test(priority=16)//,dependsOnMethods = ("addNewHolidayListRecord"))
  	public void editAgentTransferRecord() throws Exception {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
  		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        agentTransferPage.editAgentTransferRecord(agentTransferDetails);
        Assert.assertTrue(agentTransferPage.verifymessage(), "Record updation assertion failed" );
      	screenshot.captureScreen(driver, "Record Updated Successfully","AgentTransferTest");
  	}
	
	//@Test(priority=17)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAgentTransferUpdate(agentTransferDetails,"Update"));
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForUpdate","AgentTransferTest");
    }
   
    @Test(priority=18)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertTrue(agentTransferPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=19)
    public void ExportToExcelData() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Transfer.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		Assert.assertTrue(agentTransferPage.verifyexportToExcelSheet(maplist));	
    }
	
	@Test(priority=20)
    public void SortingByAscending() throws IOException {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		agentTransferPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Transfer (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentTransferPage.verifyexportToExcelSheet(maplist));
        screenshot.captureScreen(driver, "SortingByAscending","AgentTransferTest");
    }
    
	@Test(priority=21)
    public void SortingByDescending() throws IOException {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		agentTransferPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Transfer (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentTransferPage.verifyexportToExcelSheet(maplist));
        screenshot.captureScreen(driver, "SortingByDescending","AgentTransferTest");
    }
	
	@Test(priority=22)
	public void searchPage() throws IOException {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        Assert.assertFalse(agentTransferPage.clearAll(agentTransferDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","AgentTransferTest");
        Assert.assertTrue(agentTransferPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","AgentTransferTest");
    }
    
	//@Test(priority=23)//,dependsOnMethods = ("editHolidayListRecord"))
    public void DeleteCancelAgentTransferRecord() throws IOException {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        Assert.assertTrue(agentTransferPage.verifydeleteNo(agentTransferDetails));
        screenshot.captureScreen(driver, "delete No","AgentTransferTest");
	}
	
	@Test(priority=24)//,dependsOnMethods = ("editHolidayListRecord"))
    public void DeleteAgentTransferRecord() throws IOException {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        agentTransferPage.deleteAgentTransferRecord(agentTransferDetails);
        Assert.assertTrue(agentTransferPage.verifymessage(),"delete record assertion failed");
        screenshot.captureScreen(driver, "Verify Record Deleted", "AgentTransferTest");
        }
	
	@Test(priority=25)
    public void ExporttoExcelWithoutData() throws Exception
    {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        Assert.assertTrue(agentTransferPage.ExporttoExcelWithoutData(agentTransferDetails));
       screenshot.captureScreen(driver, "ExporttoExcelWithoutData", "AgentTransferTest");
	}
	
	@Test(priority=26)
    public void SearchClearSearch() throws Exception
    {
		AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        Assert.assertTrue(agentTransferPage.verifyinvalidsearchwithwrongdata(agentTransferDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "AgentTransferTest");
        Assert.assertTrue(agentTransferPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen(driver,"Clear Search", "AgentTransferTest");
    }*/
    
    @Test(priority=27)
    public void database() throws Exception
    {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTransferData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        AgentTransferDetails agentTransferDetails=new AgentTransferDetails (map);
        Assert.assertTrue(agentTransferPage .verifyDatabase(agentTransferDetails.getQuery()));
    }
	
	@Test(priority=28)
    public void GroupBy()
    {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
    	Assert.assertTrue(agentTransferPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","AgentTranferTest");
    	Assert.assertTrue(agentTransferPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","AgentTransferTest");
    }
    
    @Test(priority=29)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
    	Assert.assertTrue(agentTransferPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForFirstAndLastPage() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertTrue(agentTransferPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    @Test(priority=31)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertTrue(agentTransferPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyNumberOfItemsPerPageSelection() {
        AgentTransferPage agentTransferPage = PageFactory.createPageInstance(driver, AgentTransferPage.class);
        Assert.assertTrue(agentTransferPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result,Method method){
   	 if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, method.getName(),"AgentTransferTest");
   		 }
   		catch (Exception e){
   		 System.out.println("Exception while taking screenshot "+e.getMessage());
   		 } 
   		 driver.navigate().refresh();
   		 }
   		
    }

}
