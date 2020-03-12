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
    public void NavigateToWaitTimeColorConfigPage() throws Exception {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
        tmacPage.navigateToWaitTimeColorConfigPage();
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.isWaitTimeColorConfigPageDisplayed(),"Wait time color config page assertion failed");
    }
   
    @Test (priority=1)
    public void WaitTimeColorConfigPage()
    {
    	WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
    	Assert.assertTrue(waitTimeColorConfigPage.verifylogo(),"Wait Time ColorConfig logo assertion failed");
        Assert.assertTrue(waitTimeColorConfigPage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(waitTimeColorConfigPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("WaitTimeColorConfigTest", "Maximize Window");
    	Assert.assertTrue(waitTimeColorConfigPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("WaitTimeColorConfigTest", "Minimize Window");
    }
   
    @Test(priority=2)
    public void AddNewWaitTimeColorConfigCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.addNewCancel(waitTimeColorConfigDetails));
    }
    
    @Test(priority=3)
    public void AddNewWaitTimeColorConfigRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        waitTimeColorConfigPage.addNewWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }
   
   @Test(priority=4)
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
   }
   
   @Test(priority=5)
   public void AddRecordWithoutStartTime() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       waitTimeColorConfigPage.addRecordWithoutStartTime(waitTimeColorConfigDetails);
       Assert.assertFalse(waitTimeColorConfigPage.verifymessage(),"AddRecordWithoutStartTime Assertion failed");
   }
   
   @Test(priority=6)
   public void AddRecordWithoutEndTime() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       waitTimeColorConfigPage.addRecordWithoutEndTime(waitTimeColorConfigDetails);
       Assert.assertFalse(waitTimeColorConfigPage.verifymessage(),"AddRecordWithoutEndTime Assertion failed");
   }
   
   @Test(priority=7)
   public void AddRecordWithoutDuplicate() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       waitTimeColorConfigPage.addNewWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
       Assert.assertFalse(waitTimeColorConfigPage.verifymessage(),"Duplicate Assertion failed");
   }
   

   @Test(priority=8)
   public void VerifySearchIsNotEqualTo() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifySearchIsNotEqualTo(waitTimeColorConfigDetails.getColorcode()));
   }
   
   @Test(priority=9)
   public void VerifySearchContains() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifySearchContains(waitTimeColorConfigDetails.getColorcode()));
   }
   
   @Test(priority=10)
   public void VerifySearchDoesNotContains() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifySearchDoesNotContains(waitTimeColorConfigDetails.getColorcode()));
   }
   
   @Test(priority=11)
   public void VerifySearchStartsWith() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifySearchStartsWith(waitTimeColorConfigDetails.getColorcode()));
   }
   
   @Test(priority=12)
   public void VerifySearchEndsWith() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifySearchEndsWith(waitTimeColorConfigDetails.getColorcode()));
   }
  
   
   @Test(priority=13)
    public void EditWaitTimeColorConfigCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.editcancel(waitTimeColorConfigDetails));
   	}
   	
    @Test(priority=14)
    public void EditWaitTimeColorConfigRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);     
        waitTimeColorConfigPage.editWaitTimeColorConfigRecord(waitTimeColorConfigDetails);
        Assert.assertEquals(waitTimeColorConfigPage.getSuccessMessage(),"Record updated successfully","Edit record assertion failed");
    }
   	
   	@Test(priority=15)
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
    }
    
   @Test(priority=16)
   public void searchPage() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertFalse(waitTimeColorConfigPage.clearAll(waitTimeColorConfigDetails),"ClearAll Assertion Failed");
       screenshot.captureScreen("WaitTimeColorConfigTest", "clearall");
       Assert.assertTrue(waitTimeColorConfigPage.verifyclose());
       screenshot.captureScreen("WaitTimeColorConfigTest", "SearchClose");
       }
   
   @Test(priority=17)
   public void SearchClearSearch() throws Exception
   {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyinvalidsearch(waitTimeColorConfigDetails), "InvalidSearchAssertionFailed");
       screenshot.captureScreen("WaitTimeColorConfigTest", "Invalid Search");
       Assert.assertTrue(waitTimeColorConfigPage.verifyclearsearch(), "Clear All Assertion Failed");
   }
   
   @Test(priority=18)
   public void ExportToExcel() throws Exception
   {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
   		WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyExportToExcel(filePath));
   }
   
   @Test(priority=19)//,dependsOnMethods="ExportToExcel")
   public void ExportToExcelData() throws Exception
   {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config.xlsx";
   		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
   		WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		Assert.assertTrue(waitTimeColorConfigPage.verifyexportToExcelSheet(maplist));
   }
   
   @Test(priority=20)
    public void DeleteCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
        WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
        Assert.assertTrue(waitTimeColorConfigPage.deleteNo(waitTimeColorConfigDetails.getStartTime(),waitTimeColorConfigDetails.getDeleteReason()));
   }
   
   @Test(priority=21)
   public void DeleteRecord() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       waitTimeColorConfigPage.deleteWaitTimeColorConfigRecord(waitTimeColorConfigDetails.getStartTime(),waitTimeColorConfigDetails.getDeleteReason());
       Assert.assertEquals(waitTimeColorConfigPage.getSuccessMessage(),"Record deleted successfully","Delete record assertion failed");
   }
   
   @Test(priority=22)
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
   }
   
   @Test(priority=23)
   public void database() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
   		WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
   		WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		Assert.assertTrue(waitTimeColorConfigPage.verifyDatabase(waitTimeColorConfigDetails.getQuery()));
   }
   
   @Test(priority=24)
   public void GroupBy(){
       	WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		Assert.assertTrue(waitTimeColorConfigPage.groupby());
   }
   
   @Test(priority=25)
   public void VerifyArrowMoveForPreviousAndNextPage() {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		Assert.assertTrue(waitTimeColorConfigPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
   }
   
   @Test(priority=26)
   public void VerifyArrowMoveForFirstAndLastPage() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
   }
   
   @Test(priority=27)
   public void VerifyTotalNumberOfItemsPerPageDetails() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
   }
   
   @Test(priority=28)
   public void VerifyNumberOfItemsPerPageSelection() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
   }
   
   @Test(priority=29)
   public void SortingByAscending() throws IOException {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
	   waitTimeColorConfigPage.SortByAscending();
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config (1).xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(waitTimeColorConfigPage.verifyexportToExcelSheet(maplist));
   }
   @Test(priority=30)
   public void SortingByDescending() throws IOException {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
	   waitTimeColorConfigPage.SortByDescending();
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config (2).xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(waitTimeColorConfigPage.verifyexportToExcelSheet(maplist));
   }
   
   @Test(priority=31)
   public void ExporttoExcelWithoutData() throws Exception
   {
	   WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WaitTimeColorConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WaitTimeColorConfigDetails waitTimeColorConfigDetails = new WaitTimeColorConfigDetails(map);
       Assert.assertTrue(waitTimeColorConfigPage.ExporttoExcelWithoutData(waitTimeColorConfigDetails));
   }
   
   @Test(priority=32)
   public void VerifyDropdownForAllTheColumns() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
   }
   
   @Test(priority=33)
   public void VerifyColumnsHeaderEnable() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertTrue(waitTimeColorConfigPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
   }
   
   @Test(priority=34)
   public void VerifyColumnsHeaderDisable() {
       WaitTimeColorConfigPage waitTimeColorConfigPage=PageFactory.createPageInstance(driver,WaitTimeColorConfigPage.class);
       Assert.assertFalse(waitTimeColorConfigPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
   }

    @AfterMethod
    	public void afterEachMethod(Method method) throws InterruptedException {
            Screenshot screenshot=new Screenshot(driver);
            screenshot.captureScreen("WaitTimeColorConfigTest",method.getName());
            driver.navigate().refresh();
        }
}
