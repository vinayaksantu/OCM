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

import com.tetherfi.model.ivr.CallbackAnnouncementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.CallbackAnnouncementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class CallBackAnnouncementTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToCallBackAnnouncementPage() {
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToCallbackAnnouncementPage();
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.isCallbackAnnouncementPageDisplayed(), "CallBack Announcement page assertion failed");
    	screenshot.captureScreen(driver,"CallBackAnnouncement Page","CallBackAnnouncementTest");
    }
    
    @Test(priority=1)
    public void CallbackAnnouncementPage() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
    	Assert.assertTrue(callbackAnnouncementPage.verifylogo(),"CallBackAnnouncement logo assertion failed");
    	Assert.assertTrue(callbackAnnouncementPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","CallBackAnnouncementTest");
    	Assert.assertTrue(callbackAnnouncementPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","CallBackAnnouncementTest");
    }
    
    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertFalse(callbackAnnouncementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddNewCallbackAnnouncementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.addnewCancel(callbackAnnouncementDetails), "Add new record cancel assertion failed");
        screenshot.captureScreen(driver, "Cancel Record","CallBackAnnouncementTest");
        callbackAnnouncementPage.addNewCallbackAnnouncementRecord(callbackAnnouncementDetails);
        Assert.assertEquals(callbackAnnouncementPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
        screenshot.captureScreen(driver,"Record Creation","CallBackAnnouncementTest");

    }
    
    @Test(priority=6,dependsOnMethods ="AddNewCallbackAnnouncementRecord",invocationCount=3)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyCallbackAnnouncementCreate(callbackAnnouncementDetails,"Create"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","CallBackAnnouncementTest");    
    }
   
    @Test(priority=7,dependsOnMethods = "AddNewCallbackAnnouncementRecord")
    public void AddDuplicateCallBackAnnouncementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.addNewCallbackAnnouncementRecord(callbackAnnouncementDetails);
        Assert.assertEquals(callbackAnnouncementPage.verifySuccessMessage(),"Record Creation Failed, Already Exist", "Duplicate record assertion failed");
    	screenshot.captureScreen(driver,"Duplicate Record","CallBackAnnouncementTest");
    }
    
    @Test(priority=8)
    public void AddNewCallbackAnnouncementWithoutWavFile() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.addNewCallbackAnnouncementWithoutWavFileRecord(callbackAnnouncementDetails);
        Assert.assertTrue(callbackAnnouncementPage.verifyErrorMessage(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"AddNewCallbackAnnouncementWithoutWavFile","CallBackAnnouncementTest");
    }
    
    @Test(priority=9)
    public void AddNewCallbackAnnouncementWithoutStartTime() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.addNewCallbackAnnouncementWithoutStartTimeRecord(callbackAnnouncementDetails);
        Assert.assertTrue(callbackAnnouncementPage.verifyErrorMessage(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"AddNewCallbackAnnouncementWithoutStartTime","CallBackAnnouncementTest");
    }
    
    @Test(priority=10)
    public void AddNewCallbackAnnouncementWithoutEndTime() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.addNewCallbackAnnouncementWithoutEndTimeRecord(callbackAnnouncementDetails);
        Assert.assertTrue(callbackAnnouncementPage.verifyErrorMessage(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"AddNewCallbackAnnouncementWithoutEndTime","CallBackAnnouncementTest");
    }
    
    @Test(priority=11)
    public void AddNewCallbackAnnouncementWithoutLanguage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.addNewCallbackAnnouncementWithoutLanguageRecord(callbackAnnouncementDetails);
        Assert.assertTrue(callbackAnnouncementPage.verifyErrorMessage(), "Add New record assertion failed");
        screenshot.captureScreen(driver,"AddNewCallbackAnnouncementWithoutLanguage","CallBackAnnouncementTest");
    }
    
    
    @Test(priority=12)//,dependsOnMethods = "AddDuplicateAdhocOptionEnhancementRecord")
    public void EditCallBackAnnouncementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.editCallbackAnnouncementRecord(callbackAnnouncementDetails);
        Assert.assertEquals(callbackAnnouncementPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    	screenshot.captureScreen(driver,"Record Updated","CallBackAnnouncementTest");
    }
    
    @Test(priority=13)//,dependsOnMethods="EditCallBackAnnouncementRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyCallBackAnnouncementUpdate(callbackAnnouncementDetails,"Update"));
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForUpdate","CallBackAnnouncementTest");
    }
    
    @Test(priority=14)
    public void EditWithoutModifyReasonCallbackannouncementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.editwithoutModifyreasonRecord(callbackAnnouncementDetails);
        Assert.assertTrue(callbackAnnouncementPage.verifyErrorMessage(), "Edit assertion failed");
        screenshot.captureScreen(driver,"EditWithoutModifyReasonCallbackAnnouncementRecord","CallBackAnnouncementTest");
    }
    
    @Test(priority=15)
    public void VerifyCancelButtonAtEditCallbackAnnouncementRecord() throws Exception {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        Assert.assertTrue(callbackAnnouncementPage.editcancel(callbackAnnouncementDetails));
        screenshot.captureScreen(driver,"VerifyCancelButtonAtEditRecord","CallBackAnnouncementTest");
    }
    
    @Test(priority=16)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertFalse(callbackAnnouncementPage .clearAll(callbackAnnouncementDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","CallBackAnnouncementTest");
        Assert.assertTrue(callbackAnnouncementPage .verifyclose());
        screenshot.captureScreen(driver, "SearchClose","CallBackAnnouncementTest");
    }
    @Test(priority=17)
    public void searchwithoutSearchTextbox() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.searchwithoutextsearch(callbackAnnouncementDetails);
    	Assert.assertTrue(callbackAnnouncementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","CallBackAnnouncementTest");
    }
    
    @Test(priority=18)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.deleteWithoutModifyReasonRecord(callbackAnnouncementDetails);
        Assert.assertTrue(callbackAnnouncementPage.verifyErrorMessage(), "Delete assertion failed");
        screenshot.captureScreen(driver,"DeleteWithoutDeleteReasonRecord","CallBackAnnouncementTest");
    }
    
    @Test(priority=19,dependsOnMethods = {"DeleteWithoutDeleteReasonRecord"})
    public void VerifyCancelButtonDeleteRecord() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
         CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
         CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
         Assert.assertTrue(callbackAnnouncementPage.Canceldelete(callbackAnnouncementDetails), "Cancel Btn at Delete record assertion failed");
        screenshot.captureScreen(driver,"VerifyCancelButtonRecord","CallBackAnnouncementTest");
    }
    
    @Test(dependsOnMethods = {"VerifyCancelButtonDeleteRecord"},priority=20)
    public void DeleteCallbackAnnouncementRecord() throws Exception {
   	 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.deleteCallbackAnnouncementRecord(callbackAnnouncementDetails);
        Assert.assertEquals(callbackAnnouncementPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
        screenshot.captureScreen(driver,"DeleteCallbackAnnouncementRecord","CallBackAnnouncementTest");
    }
    
    @Test(dependsOnMethods= {"DeleteCallbackAnnouncementRecord"},priority=21)
    public void VerifyAuditTrialReportForDelete() throws Exception {
   	 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyCallbackAnnouncemnetdelete(callbackAnnouncementDetails,"Delete"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForUpdate","CallBackAnnouncementTest");
    }
    
    @Test(priority=22)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","CallbackAnnouncementTest");
    }
    
    @Test(priority=23)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Callback Announcement.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
    	Assert.assertTrue(callbackAnnouncementPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","CallbackAnnouncementTest");
    } 

    @Test(priority=24)
    public void SearchClearSearch() throws Exception
    {
   	 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
    	Assert.assertTrue(callbackAnnouncementPage.verifyinvalidsearchwithwrongdata(callbackAnnouncementDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "CallbackAnnouncementTest");
        Assert.assertTrue(callbackAnnouncementPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "CallbackAnnouncementTest");
    }
    
    @Test(priority=25)
    public void ExporttoExcelWithoutData() throws Exception
    {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
        Assert.assertTrue(callbackAnnouncementPage.ExporttoExcelWithoutData(callbackAnnouncementDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "CallbackAnnouncementTest");
    }
  
    @Test(priority=26)
    public void SortingByAscending() throws IOException {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Callback Announcement (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(callbackAnnouncementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=27)
    public void SortingByDescending() throws IOException {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        callbackAnnouncementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Callback Announcement (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(callbackAnnouncementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void GroupBy()
    {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
    	Assert.assertTrue(callbackAnnouncementPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","CallBackAnnouncementTest");
    	Assert.assertTrue(callbackAnnouncementPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","CallBackAnnouncementTest");
    }
    
    @Test(priority=29)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
    	Assert.assertTrue(callbackAnnouncementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForFirstAndLastPage() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyNumberOfItemsPerPageSelection() {
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        Assert.assertTrue(callbackAnnouncementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
   
    @Test(priority=33)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallbackAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        CallbackAnnouncementPage callbackAnnouncementPage = PageFactory.createPageInstance(driver, CallbackAnnouncementPage.class);
        CallbackAnnouncementDetails callbackAnnouncementDetails = new CallbackAnnouncementDetails(map);
    	Assert.assertTrue(callbackAnnouncementPage.verifyDatabase(callbackAnnouncementDetails.getQuery()));
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws InterruptedException {
   	 if(ITestResult.FAILURE==result.getStatus()){
      		 try{
      			 screenshot.captureScreen(driver,method.getName(),"AdhocOptionEnhancementTest");
      		 }
      		catch (Exception e){
      		 System.out.println("Exception while taking screenshot "+e.getMessage());
      		 } 
      		 driver.navigate().refresh();
      		 }    }
}
