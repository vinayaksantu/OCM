package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.ExportSchedulerDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.ExportSchedulerPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class ExportSchedulerTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToExportSchedulerPage() {
    	 HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
         homePage.navigateToOCMPage();
         OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
         Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
         ocmHomePage.navigateToExportSchedulerPage();
         ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
         Assert.assertTrue(ExportSchedulerPage.isExportSchedulerPageDisplayed(),"Cep Event Mapping assertion failed");
    }
    
    @Test(priority=1)
    public void ExportSchedulerPage() {
        ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.VerifyLogo(),"Export Scheduler logo assertion failed");
    	Assert.assertTrue(ExportSchedulerPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("ExportSchedulerTest","maximize window");
    	Assert.assertTrue(ExportSchedulerPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("ExportSchedulerTest","minimize window");
    }
    
  	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        Assert.assertTrue(ExportSchedulerPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        Assert.assertTrue(ExportSchedulerPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        Assert.assertFalse(ExportSchedulerPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddExportSchedulerDailyRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewExportSchedulerRecordDaily(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    
    @Test(priority=6,enabled=false)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyExportSchedulerCreate(ExportSchedulerDetails,"Create"));
    }
    
    @Test(priority=7)
    public void DuplicateRecord() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
         ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
     	 ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
         ExportSchedulerPage.addNewExportSchedulerRecordDaily(ExportSchedulerDetails);
         Assert.assertEquals(ExportSchedulerPage.getMessage(),"Record Creation Failed","Duplicate record assertion failed");
    }
    
    @Test(priority=8)
    public void AddExportSchedulerWeeklyRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewExportSchedulerRecordWeekly(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    
    @Test(priority=9,enabled=false)
    public void VerifyAuditTrialReportForWeeklyCreate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyExportSchedulerWeeklyCreate(ExportSchedulerDetails,"Create"));
    }
    
    @Test(priority=10)
    public void AddExportSchedulerMonthlyRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewExportSchedulerRecordMonthly(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    
    @Test(priority=11,enabled=false)
    public void VerifyAuditTrialReportForCreateMonthly() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyExportSchedulerMonthlyCreate(ExportSchedulerDetails,"Create"));
    }
    
    @Test(priority=12)
    public void AddExportSchedulerCustomDailyRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewExportSchedulerRecordCustomDaily(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    
    @Test(priority=13,enabled=false)
    public void VerifyAuditTrialReportForCreateCustomDaily() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyExportSchedulerCustomDailyCreate(ExportSchedulerDetails,"Create"));
    }
    @Test(priority=14)
    public void VerifyCancelBtnAtAddRecord(){
    	 ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	 ExportSchedulerPage.clickOnAddRecord();
    	 ExportSchedulerPage.clickOnCancelBtn();
    	 Assert.assertFalse(ExportSchedulerPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
	
    @Test(priority=15)
    public void AddRecordWithoutName() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutName(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg1(),"Invalid record assertion failed");
    }
    
    @Test(priority=16)
    public void AddRecordWithoutFrequency() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutFrequency(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg2(),"Invalid record assertion failed");
    }
    
    @Test(priority=17)
    public void AddRecordWithoutTime() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutTime(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg3(),"Invalid record assertion failed");
    }
    
    @Test(priority=18)
    public void AddRecordWithoutReport() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutReport(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg4(),"Invalid record assertion failed");
    }
    
    @Test(priority=19)
    public void AddRecordWithoutDay() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutDay(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg5(),"Invalid record assertion failed");
    }
    
    @Test(priority=20)
    public void AddRecordWithoutWeeklyTime() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutWeeklyTime(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg6(),"Invalid record assertion failed");
    }
    
    
    @Test(priority=21)
    public void AddRecordWithoutMonthlyDate() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutMonthlydate(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg7(),"Invalid record assertion failed");
    }
    
    @Test(priority=22)
    public void AddRecordWithoutMonthlyTime() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutMonthlyTime(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg8(),"Invalid record assertion failed");
    }
    
    @Test(priority=23)
    public void AddRecordWithoutStartTime() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutStartTime(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg9(),"Invalid record assertion failed");
    }
    
    @Test(priority=24)
    public void AddRecordWithoutEndTime() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
        ExportSchedulerPage.addNewRecordWithoutEndTime(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),ExportSchedulerDetails.getErrorMsg10(),"Invalid record assertion failed");
    }
    
	@Test(priority=25)
    public void EditWithoutModifyReasonRecord() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.editexportSchedulerRecordWithoutModifyReason(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=26)
    public void VerifyCancelBtnAtEditRecord() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.searchexportScheduler(ExportSchedulerDetails.getName());
    	Thread.sleep(3000);
    	ExportSchedulerPage.clickOnEditButton();
    	ExportSchedulerPage.clickOnCancelBtn();
        Assert.assertFalse(ExportSchedulerPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=27)
    public void EditExportSchedulerRecord() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.editexportSchedulerRecord(ExportSchedulerDetails);
        Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record updated successfully","Edit record assertion failed");
    }
    
   	@Test(priority=28,enabled=false)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyExportSchedulerUpdate(ExportSchedulerDetails,"Update"));
    }
    
    @Test(priority=29)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifySearchIsNotEqualTo(ExportSchedulerDetails.getName()));
    }
    @Test(priority=30)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifySearchContains(ExportSchedulerDetails.getName()));
    }
    
    @Test(priority=31)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifySearchDoesNotContains(ExportSchedulerDetails.getName()));
    }
    
    @Test(priority=32)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
        ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifySearchStartsWith(ExportSchedulerDetails.getName()));
    }
    @Test(priority=33)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(5);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifySearchEndsWith(ExportSchedulerDetails.getName()));
    }
    
    @Test(priority=34)
    public void searchPage() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertFalse(ExportSchedulerPage.clearAll(ExportSchedulerDetails),"ClearAll Assertion Failed");
    	screenshot.captureScreen("clearall","ExportSchedulerTest");
    	Assert.assertTrue(ExportSchedulerPage.verifyclose());
    }
       
   
   @Test(priority=35)
   public void ExportToExcel() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
	   ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
	   Assert.assertTrue(ExportSchedulerPage.verifyExportToExcel(filePath));
   }
    
   	@Test(priority=36,dependsOnMethods= {"ExportToExcel"})
   	public void ExportToExcelData() throws Exception{	
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Export Scheduler.xlsx";
   		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
   		ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
   		Assert.assertTrue(ExportSchedulerPage.verifyexportToExcelSheet(maplist));	
   	}
   	
   	@Test(priority=37)
    public void SortingByAscending() throws IOException {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Export Scheduler (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ExportSchedulerPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=38)
    public void SortingByDescending() throws IOException {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Export Scheduler (2).xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	Assert.assertTrue(ExportSchedulerPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=39)
    public void GroupBy() {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.groupby());
    	screenshot.captureScreen("ExportSchedulerTest", "GroupBy");
    	Assert.assertTrue(ExportSchedulerPage.groupby());
    	screenshot.captureScreen("ExportSchedulerTest", "AlreadyGroupBy");
    }
    
    @Test(priority=40)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=41)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=42)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=43)
    public void VerifyNumberOfItemsPerPageSelection() {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=44)
    public void searchwithoutSearchTextbox() throws IOException {
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.searchwithoutextsearch();
    	Assert.assertEquals(ExportSchedulerPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=45)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.deleteExportSchedulerWithoutDeleteReasonRecord(ExportSchedulerDetails);
    	Assert.assertEquals(ExportSchedulerPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=46)
    public void VerifyCancelBtnAtDeleteExportSchedulerRecord() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.searchexportScheduler(ExportSchedulerDetails.getName());
    	Thread.sleep(1000);
    	ExportSchedulerPage.clickOnDeleteButton();
    	ExportSchedulerPage.clickOnDeleteCancelBtn();
    	Assert.assertFalse(ExportSchedulerPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }

    @Test(priority=47)
    public void DeleteRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.deleteExportSchedulerRecord(ExportSchedulerDetails);
    	Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record successfully deleted","Delete record assertion failed");
    }
    
    @Test(priority=48,enabled=false)
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
    	homePage.navigateToOCMReportsPage();
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
    	Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map1);
    	ocmReportsPage.showReport(reportDetails);
    	Assert.assertTrue(ocmReportsPage.verifyExportSchedulerdelete(ExportSchedulerDetails,"Delete"));
    }
    
   @Test(priority=49)
    public void SearchClearSearch() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	Assert.assertTrue(ExportSchedulerPage.verifyinvalidsearchwithwrongdata(ExportSchedulerDetails), "InvalidSearchAssertionFailed");
    	screenshot.captureScreen("ExportSchedulerTest", "Invalid Search");
    	Assert.assertTrue(ExportSchedulerPage.verifyclearsearch(), "ClearSearch Assertion Failed");
    	screenshot.captureScreen("ExportSchedulerTest", "Clear Search");
    }
    
    @Test(priority=50)
    public void ExporttoExcelWithoutData() throws Exception{
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	Assert.assertTrue(ExportSchedulerPage.ExporttoExcelWithoutData(ExportSchedulerDetails));
    }   
    
    @Test(priority=51)
    public void database() throws Exception{
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	Assert.assertTrue(ExportSchedulerPage.verifyDatabase(ExportSchedulerDetails.getQuery()));
    }
    
    @Test(priority=52)
    public void DeleteWeeklyRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.deleteExportSchedulerRecord(ExportSchedulerDetails);
    	Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record successfully deleted","Delete record assertion failed");
    }
    
    @Test(priority=53)
    public void DeleteMonthlyRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(2);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.deleteExportSchedulerRecord(ExportSchedulerDetails);
    	Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record successfully deleted","Delete record assertion failed");
    }
    
    @Test(priority=54)
    public void DeleteCustomdailyRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ExportSchedulerData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(3);
    	ExportSchedulerDetails ExportSchedulerDetails = new ExportSchedulerDetails(map);
    	ExportSchedulerPage ExportSchedulerPage=PageFactory.createPageInstance(driver,ExportSchedulerPage.class);
    	ExportSchedulerPage.deleteExportSchedulerRecord(ExportSchedulerDetails);
    	Assert.assertEquals(ExportSchedulerPage.getSuccessMessage(),"Record successfully deleted","Delete record assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("ExportSchedulerTest",method.getName());
        driver.navigate().refresh();
    }

}
