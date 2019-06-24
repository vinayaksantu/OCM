package com.tetherfi.test.sms;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.sms.SmsResponseTemplateDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.SmsResponseTemplatePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.SmsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SmsResponseTemplateUITest extends BaseTest{
Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
    public void NavigateToSmsResponseTemplatePage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        SmsPage smsPage = PageFactory.createPageInstance(driver, SmsPage.class);
        Assert.assertTrue(smsPage.isSMSPageDisplayed(), "IVR Page assertion failed");
        smsPage.navigateToSMSResponseTemplatePage();
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.isSMSResponseTemplatePageDisplayed(), "SMS Response Template Page assertion failed");
    }
	
	@Test(priority=1)
    public void SmsResponseTemplatePage() {
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
    	Assert.assertTrue(SmsResponseTemplatePage.verifylogo(),"Host Value Mapping logo assertion failed");
    	Assert.assertTrue(SmsResponseTemplatePage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("SmsResponseTemplateUITest","maximize window");
    	Assert.assertTrue(SmsResponseTemplatePage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("SmsResponseTemplateUITest","minimize window");
    }
	@Test(priority=2)
	public void VerifySmsResponseTemplateApprovedDataPage() {
        SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
       
	@Test(priority=3)
    public void VerifySmsResponseTemplateAuditTrailDataPage() {
		SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
		SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        Assert.assertTrue(SmsResponseTemplatePage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
       }
       
	@Test(priority=4)
    public void VerifyMakeSmsResponseTemplateChangeButton() {
		SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
		SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
		SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
		Assert.assertTrue(SmsResponseTemplatePage.verifyAddNewSmsResponseTemplateRecordButton(), "add new Agent Settings record button assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(SmsResponseTemplatePage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
       
    @Test(priority=5)
    public void VerifyDropdownForAllTheColumns() {
		SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
		SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6)
    public void VerifyColumnsHeaderEnable() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7)
    public void VerifyColumnsHeaderDisable() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertFalse(SmsResponseTemplatePage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
	@Test(priority=11)
    public void VerifyNumberOfItemsPerPageSelection() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=12)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        Assert.assertTrue(SmsResponseTemplatePage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
        Assert.assertTrue(SmsResponseTemplatePage.ExporttoExcelWithoutData(SmsResponseTemplateDetails));
       }
    
   @Test(priority=15)
    public void SortingByAscending() throws IOException {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        SmsResponseTemplatePage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(SmsResponseTemplatePage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16)
    public void SortingByDescending() throws IOException {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        SmsResponseTemplatePage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(SmsResponseTemplatePage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17)
    public void VerifyDragAndDrop() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        SmsResponseTemplatePage.dragColumntoGroup("Functionality");
        Assert.assertTrue(SmsResponseTemplatePage.verifyDragColumntoGroup("Functionality"),"drag and drop assertion failed");
    }
    @Test(priority=18)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        SmsResponseTemplatePage.selectSmsResponseTemplateAuditTrailTab();
        SmsResponseTemplatePage.selectMakeSmsResponseTemplateChanges();
        SmsResponseTemplatePage.dragColumntoGroup("Functionality");
        SmsResponseTemplatePage.dragColumntoGroup("Functionality");
        Assert.assertTrue(SmsResponseTemplatePage.verifyDragColumntoGroup("Functionality"),"drag and drop assertion failed");
    }
    
    @Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
    	Assert.assertTrue(SmsResponseTemplatePage.verifyDatabase(SmsResponseTemplateDetails.getQuery()));
    }
    
    @Test(priority=20)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertFalse(SmsResponseTemplatePage.clearAll(SmsResponseTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("SmsResponseTemplateUITest", "clearall");
        Assert.assertTrue(SmsResponseTemplatePage.verifyclose());
        screenshot.captureScreen("SmsResponseTemplateUITest", "SearchClose");
    }
    
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
    	SmsResponseTemplatePage.searchwithoutextsearch(SmsResponseTemplateDetails);
    	Assert.assertFalse(SmsResponseTemplatePage.getErrorMsg());
    }
    
    @Test(priority=22)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SmsResponseTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    SmsResponseTemplateDetails SmsResponseTemplateDetails = new SmsResponseTemplateDetails(map);
    	SmsResponseTemplatePage SmsResponseTemplatePage = PageFactory.createPageInstance(driver, SmsResponseTemplatePage.class);
        Assert.assertTrue(SmsResponseTemplatePage.verifyApprovedSectionData(SmsResponseTemplateDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("SmsResponseTemplateUITest","Invalid Search with wrong data");
        Assert.assertTrue(SmsResponseTemplatePage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("SmsResponseTemplateUITest",method.getName());
	        driver.navigate().refresh();
	}

}
