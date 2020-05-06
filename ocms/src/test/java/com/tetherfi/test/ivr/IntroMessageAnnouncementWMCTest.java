package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.IntroMessageAnnouncementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IntroMessageAnnouncementPage;
import com.tetherfi.pages.IntroMessageAnnouncementWMCPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class IntroMessageAnnouncementWMCTest extends BaseTest {
	
    Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToIntroMessageAnnouncementPage(){
    	HomePage homepage=PageFactory.createPageInstance(driver, HomePage.class);
    	homepage.navigateToOCMPage();
    	OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
    	ocmHomePage.navigateToTab("IVR");
    	IvrPage ivrpage=PageFactory.createPageInstance(driver, IvrPage.class);
    	Assert.assertTrue(ivrpage.isIVRPageDisplayed(), "ivr page assertion failed");
    	ivrpage.navigateToIntroMessageAnnouncementPage();
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementWMCPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementWMCPage.isIntroMessageAnnouncementPageDisplayed(), "IntroMessage Announcement Assertion Failed");
    }
    
    @Test(priority=1)
    public void IntroMessageAnnouncementWMCPage() {
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.verifyLogo(), "IntroMessageAnnouncement Logo Assertion Failed");
    	Assert.assertTrue(introMessageAnnouncementPage.maximizeWindow(), "fullScreen Assertion Failed");
    	screenshot.captureScreen("IntroMessageAnnouncementTest", "maximize window");
    	Assert.assertTrue(introMessageAnnouncementPage.minimizeWindow(), "Restore Assertion Failed");
    	screenshot.captureScreen("IntroMessageAnnouncementTest", "minimize window");
    }
    
    @Test(priority=2)
    public void verifyDropDownForAllTheColumns() {
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
    	IntroMessageAnnouncementWMCPage introMesssageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertFalse(introMesssageAnnouncementPage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
    }
    
    @Test(priority=5)
    public void AddNewCancelRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
        IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementWMCPage introMesssageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
        Assert.assertTrue(introMesssageAnnouncementPage.addNewcancel(introMessageAnnouncementDetails),"Add New Record Assertion failed");
    }
    
    @Test(priority=6)
    public void AddNewIntroMessageAnnouncementRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
        IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
        IntroMessageAnnouncementWMCPage introMesssageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
        introMesssageAnnouncementPage.addNewIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
        Assert.assertEquals(introMesssageAnnouncementPage.verifySuccessMessage(), "Record created successfully","Add New record assertion failed");
    }
    
    @Test(priority=7,dependsOnMethods="AddNewIntroMessageAnnouncementRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	HomePage homepage=PageFactory.createPageInstance(driver, HomePage.class);
    	homepage.navigateToOCMReportsPage();
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
    	Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementCreate(introMessageAnnouncementDetails,"Create"));
    }
    
    @Test(priority=8)//,dependsOnMethods="AddNewIntroMessageAnnouncementRecord")
    public void AddDuplicateIntroMessageAnnouncement() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.addNewIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Duplicate Record", "Duplicate RecordAssertion Failed");
    }
    
    @Test(priority=9)
    public void AddIntroMessageAnnouncementWithOutFunctionality() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.AddIntroMessageAnnouncementRecordWithoutFunctionality(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Please provide input for Functionality", "Record Creation Assertion Failed");
    }
    
    @Test(priority=10)
    public void AddIntroMessageAnnouncementWithoutHotLine() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.AddNewIntroMessageAnnouncementRecordWithoutHotLine(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Please provide input for Hotline", "Record Creation Assertion  Failed");
    }
    
    @Test(priority=11)
    public void AddIntroMessageAnnouncementWithoutStartDateTime() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.AddIntroMessageAnnouncementWithoutStartDate(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Please provide input for Start Date Time", "Record Creation Assertion Failed");
    }
    
    @Test(priority=12)
    public void AddIntroMessageAnnouncementWithoutEndDateTime() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.AddNewIntroMessageAnnouncementWithoutEndDateTime(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Please provide input for End Date Time", "Record Assertion Failed");
    }
    
    @Test(priority=13)
    public void AddIntroMesssegeAnnounceMentWithoutLanguage() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncemenetPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncemenetPage.AddIntroMessageAnnouncementWithoutLanguage(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncemenetPage.verifySuccessMessage(), "Please provide input for Language", "Record Assertion Failed");
    }
    
    @Test(priority=14)
    public void AddIntroMessageAnnouncementWithoutStatus() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(1);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.AddIntroMessgeAnnouncementWithoutStatus(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Record created successfully", "Record Assertion Failed");
    	
    }
    
    @Test(priority=15)
    public void AddIntroMessageAnnounceMentWithoutInturupt() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.AddIntroMessageannouncementWithoutInturupt(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Record created successfully", "Record Assertion Failed");
    	
    }
    
    @Test (priority=16)
    public void AddIntroMessageannouncementWithoutWaveFile() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.AddIntroMessageAnnouncementWithoutWaveFile(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Please provide input for Wave File", "Record Assertion Failed");
    	
    }
    
    @Test(priority=17)
    public void EditIntroMessageAnnouncementRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncememntDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver,IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.editIntroMessageAnnouncementRecord(introMessageAnnouncememntDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Record updated successfully", "Record Updation Assertion Failed");
    }
    
    @Test(priority=18,dependsOnMethods="EditIntroMessageAnnouncementRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	HomePage homepage=PageFactory.createPageInstance(driver, HomePage.class);
    	homepage.navigateToOCMReportsPage();
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	String filePath1=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
    	Map<String,String>map1=new ExcelReader(filePath1,"Show").getTestData().get(0);
    	ReportDetails reportDetails=new ReportDetails(map1);
    	ocmReportsPage.showReport(reportDetails);
    	Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementUpdate(introMessageAnnouncementDetails, "Update"));
    }
    
    @Test(priority=19)
    public void EditWithOutModifyResonIntroMessageAnnouncementRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.editIntroMessageAnnouncementRecordWithoutModifyReson(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Please provide modify reason!", "Edit Assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyCancleButtonAtEditIntroMessageAnnouncementRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.editCancel(introMessageAnnouncementDetails));
    }
    
    @Test(priority=21)
    public void searchPage() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails= new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertFalse(introMessageAnnouncementPage.clearAll(introMessageAnnouncementDetails));
    	screenshot.captureScreen("IntroMessageAnnouncement Test", "clearall");
    	Assert.assertTrue(introMessageAnnouncementPage.verifyclose());
    	screenshot.captureScreen("IntroMessageAnnouncement Test", "SearchClose");
    }
    
    @Test(priority=22)
    public void SearchWithoutSearchTextBox() throws Exception  {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.searchWithoutSearchText(introMessageAnnouncementDetails);
    	Assert.assertTrue(introMessageAnnouncementPage.verifyErrorMessage());
    }
    
    @Test(priority=23)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	IntroMessageAnnouncementWMCPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=24)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	IntroMessageAnnouncementWMCPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=25)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	IntroMessageAnnouncementWMCPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
        IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        Assert.assertTrue(IntroMessageAnnouncementPage.ExporttoExcelWithoutData(IntroMessageAnnouncementDetails));
       }
    
    @Test(priority=26)
    public void SortingByAscending() throws IOException {
    	IntroMessageAnnouncementWMCPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
        IntroMessageAnnouncementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=27)
    public void SortingByDescending() throws IOException {
    	IntroMessageAnnouncementWMCPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
        IntroMessageAnnouncementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyexportToExcelSheet(maplist));
    }
    
    
    @Test(priority=28)
    public void DeleteWithoutDeleteReson() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.deleteWithoutDeleteReason(introMessageAnnouncementDetails);
    	Assert.assertTrue(introMessageAnnouncementPage.verifyErrorMessage());
    }
    
    @Test(priority=29)
    public void verifyCancelButtonDeleteRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.deleteCancel(introMessageAnnouncementDetails));
    }
    
    @Test(priority=30)
    public void DeleteIntroMessageAnnouncementRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.deleteIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Record deleted successfully", "Record Delete Assertion failed");
    }
    
    @Test(priority=31,dependsOnMethods="DeleteIntroMessageAnnouncementRecord")
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
         homePage.navigateToOCMReportsPage();
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
         String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
         Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map1);
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ocmReportsPage.verifyIntroMessageAnnouncementDelete(introMessageAnnouncementDetails, "Delete"));
    }
    
    @Test(priority=32)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.verifySearchIsNotEqualTo(introMessageAnnouncementDetails.getHotLine()));
    }
    
    @Test(priority=33)
    public void VerifySearchContains() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.verifySearchContains(introMessageAnnouncementDetails.getHotLine()));
    }
    
    @Test(priority=34)
    public void VerifySearchDoesNotContains() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.verifySearchDoesNotContains(introMessageAnnouncementDetails.getHotLine()));
    }
    
    @Test(priority=35)
    public void VerifySearchStartsWith() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.verifySearchStartsWith(introMessageAnnouncementDetails.getHotLine()));
    }
    
    @Test(priority=36)
    public void VerifySearchEndsWith() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	Assert.assertTrue(introMessageAnnouncementPage.verifySearchEndsWith(introMessageAnnouncementDetails.getHotLine()));
    }
    
    @Test(priority=37)
    public void DeleteNomandatoryFieldStatusIntroMessageAnnouncementRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(1);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.deleteIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Record deleted successfully", "Record Delete Assertion failed");
    }
    
    @Test(priority=38)
    public void DeleteNomandatoryFieldInturptIntroMessageAnnouncementRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(2);
    	IntroMessageAnnouncementDetails introMessageAnnouncementDetails=new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementWMCPage introMessageAnnouncementPage=PageFactory.createPageInstance(driver, IntroMessageAnnouncementWMCPage.class);
    	introMessageAnnouncementPage.deleteIntroMessageAnnouncementRecord(introMessageAnnouncementDetails);
    	Assert.assertEquals(introMessageAnnouncementPage.verifySuccessMessage(), "Record deleted successfully", "Record Delete Assertion failed");
    }
    
    
    @AfterMethod
    public void afterEachMethod(Method method){
    	Screenshot screenshot=new Screenshot(driver);
    	screenshot.captureScreen("IntroMessageAnnouncementWMCTest", method.getName());
    	driver.navigate().refresh();
    }
}
    


