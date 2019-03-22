package com.tetherfi.test.tmac;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;								
import java.util.List;
import java.util.Map;

public class WaitTimeColorConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

    @BeforeMethod
    public void NavigateToWaitTimeColorConfigPage() {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
        screenshot.captureScreen(driver, "TMAC Page","WaitTimeColorConfigTest");
        tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.isWaitTimeColorConfigPageDisplayed(),"Wait time color config page assertion failed");
        screenshot.captureScreen(driver, "Wait Time Color Config Page","WaitTimeColorConfigTest");
    }
    //@Test (priority=1)
    public void WaitTimeColorConfigPage()
    {
    	WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
    	Assert.assertTrue(waitTimeColorConfigPage.verifylogo(),"Wait Time ColorConfig logo assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(waitTimeColorConfigPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "Maximize Window","WaitTimeColorConfigTest");
    	Assert.assertTrue(waitTimeColorConfigPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "Minimize Window","WaitTimeColorConfigTest");
    }
   //@Test(priority=2)
    public void AddNewWaitTimeColorConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.addNewCancel(waitTimeColorConfigDetails));
        screenshot.captureScreen(driver, "add cancel","WaitTimeColorConfigTest");
        waitTimeColorConfigPage.addNewWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record Created Successfully","Add New record assertion failed");
        screenshot.captureScreen(driver, "Record Created Successfully","WaitTimeColorConfigTest");
    }
   
   //@Test()
   public void VerifyAuditTrialReportForCreate() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
       homePage.navigateToOCMReportsPage();
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
       String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
       Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map1);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifywaitTimeColorConfigCreate(waitTimeColorConfigDetails,"Create"));
       screenshot.captureScreen("WaitTimeColorConfigTest", "VerifyAuditTrialReportForCreate");
       
   }
   //(priority=3)
   public void AddInvalidRecord() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       waitTimeColorConfigPage.addRecordWithoutStartTime(waitTimeColorConfigDetails);
       Assert.assertFalse(waitTimeColorConfigPage.verifymessage(),"AddRecordWithoutStartTime Assertion failed");
       screenshot.captureScreen(driver, "addRecordWithoutStartTime","WaitTimeColorConfigTest");
       waitTimeColorConfigPage.addRecordWithoutEndTime(waitTimeColorConfigDetails);
       Assert.assertFalse(waitTimeColorConfigPage.verifymessage(),"AddRecordWithoutEndTime Assertion failed");
       screenshot.captureScreen(driver, "addRecordWithoutEndTime","WaitTimeColorConfigTest");
       waitTimeColorConfigPage.DuplicateRecord(waitTimeColorConfigDetails);
       Assert.assertFalse(waitTimeColorConfigPage.verifymessage(),"Duplicate Assertion failed");
       screenshot.captureScreen(driver, "Duplicate Value","WaitTimeColorConfigTest");
   }
  
   	//@Test(priority=4)
    public void EditWaitTimeColorConfigRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.editcancel(waitTimeColorConfigDetails));
        screenshot.captureScreen(driver, "edit cancel","WaitTimeColorConfigTest");
        waitTimeColorConfigPage.editWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record updated successfully","Edit record assertion failed");
        screenshot.captureScreen(driver, "Record Updated successfully","WaitTimeColorConfigTest");
    }
   	@Test(priority=1)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifywaitTimeColorConfigUpdate(waitTimeColorConfigDetails,"Update"));
        screenshot.captureScreen("WaitTimeColorConfigTest", "VerifyAuditTrialReportForUpdate");
    }
    
   /*@Test(priority=5)
   public void searchPage() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertFalse(waitTimeColorConfigPage.clearAll(waitTimeColorConfigDetails),"ClearAll Assertion Failed");
       screenshot.captureScreen(driver, "clearall","WaitTimeColorConfigTest");
       Assert.assertTrue(waitTimeColorConfigPage.verifyclose());
       screenshot.captureScreen(driver, "SearchClose","WaitTimeColorConfigTest");
       }
   
   @Test(priority=6)
   public void SearchClearSearch() throws Exception
   {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyinvalidsearch(waitTimeColorConfigDetails), "InvalidSearchAssertionFailed");
       screenshot.captureScreen(driver, "Invalid Search", "WaitTimeColorConfigTest");
       Assert.assertTrue(waitTimeColorConfigPage.verifyclearsearch(), "Clear All Assertion Failed");
       screenshot.captureScreen(driver, "Clear Search", "WaitTimeColorConfigTest");
   }
   @Test(priority=7)
   public void ExportToExcel() throws Exception
   {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyExportToExcel(filePath));
       screenshot.captureScreen(driver, "ExportToExcel","WaitTimeColorConfigTest");
   }
   
   @Test(priority=8)
   public void ExportToExcelData() throws Exception
   {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config.xlsx";
   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   Assert.assertTrue(waitTimeColorConfigPage.verifyexportToExcelSheet(maplist));
   screenshot.captureScreen(driver, "ExportToExcelData","WaitTimeColorConfigTest");
   }*/
   
   //@Test(priority=9)
    public void DeleteRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.deleteNo(waitTimeColorConfigDetails.getStartTime(),waitTimeColorConfigDetails.getDeleteReason()));
        screenshot.captureScreen(driver, "Delete No","WaitTimeColorConfigTest");
        waitTimeColorConfigPage.deleteWaitTimeColorConfigRecord(waitTimeColorConfigDetails.getStartTime(),waitTimeColorConfigDetails.getDeleteReason());
        Assert.assertEquals(waitTimeColorConfigPage.getMessage(),"Record deleted successfully","Delete record assertion failed");
        screenshot.captureScreen(driver, "Deleted Successfully","WaitTimeColorConfigTest");
        
   }
   
   @Test(priority=2)
   public void VerifyAuditTrialReportForDelete() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
       homePage.navigateToOCMReportsPage();
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
       String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
       Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map1);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifywaitTimeColorConfigdelete(waitTimeColorConfigDetails,"Delete"));
       screenshot.captureScreen("WaitTimeColorConfigTest", "VerifyAuditTrialReportForUpdate");
   }
   
   /*@Test(priority=10)
   public void database() throws Exception {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   	Assert.assertTrue(waitTimeColorConfigPage.verifyDatabase(waitTimeColorConfigDetails.getQuery()));
    screenshot.captureScreen(driver, "Database","WaitTimeColorConfigTest");

   }
   
   @Test(priority=11)
   public void GroupBy()
   {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		Assert.assertTrue(waitTimeColorConfigPage.groupby());
        screenshot.captureScreen(driver, "groupby","WaitTimeColorConfigTest");
   }
   
   @Test(priority=12)
   public void VerifyArrowMoveForPreviousAndNextPage() {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		Assert.assertTrue(waitTimeColorConfigPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
        screenshot.captureScreen(driver,"VerifyArrowMoveForPreviousAndNextPage","WaitTimeColorConfigTest");

   }
   @Test(priority=13)
   public void VerifyArrowMoveForFirstAndLastPage() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
       screenshot.captureScreen(driver,"VerifyArrowMoveForFirstAndLastPage","WaitTimeColorConfigTest");

   }
   @Test(priority=14)
   public void VerifyTotalNumberOfItemsPerPageDetails() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
       screenshot.captureScreen(driver,"VerifyTotalNumberOfItemsPerPageDetails","WaitTimeColorConfigTest");
   }
   
   @Test(priority=15)
   public void VerifyNumberOfItemsPerPageSelection() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
       screenshot.captureScreen(driver,"VerifyNumberOfItemsPerPageSelection","WaitTimeColorConfigTest");
   }
   @Test(priority=16)
   public void VerifyDropdownForAllTheColumns() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
   }
   @Test(priority=17)
   public void VerifyColumnsHeaderEnable() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
   }
   @Test(priority=18)
   public void VerifyColumnsHeaderDisable() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertFalse(waitTimeColorConfigPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
   }
   @Test(priority=19)
   public void SortingByAscending() throws IOException {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
	   waitTimeColorConfigPage.SortByAscending();
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config (1).xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(waitTimeColorConfigPage.verifyexportToExcelSheet(maplist));
	   screenshot.captureScreen(driver, "SortingByAscending","WaitTimeColorConfigTest");
   }
   @Test(priority=20)
   public void SortingByDescending() throws IOException {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
	   waitTimeColorConfigPage.SortByDescending();
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config (2).xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(waitTimeColorConfigPage.verifyexportToExcelSheet(maplist));
	   screenshot.captureScreen(driver, "SortingByAscending","WaitTimeColorConfigTest");
   }
   @Test(priority=21)
   public void ExporttoExcelWithoutData() throws Exception
   {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       Assert.assertTrue(waitTimeColorConfigPage.ExporttoExcelWithoutData(waitTimeColorConfigDetails));
   }*/

    @AfterMethod
    public void afterEachMethod(ITestResult result,Method method) {
    	if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, method.getName(),"AgentTeamMgmtTest");
   		 }
   		catch (Exception e){
   		 System.out.println("Exception while taking screenshot "+e.getMessage());
   		 } 
   		 driver.navigate().refresh();
   		 }
   	
       }
}
