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
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IntroMessageAnnouncementPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public final class IntroMessageAnnouncementUITest extends BaseTest{
Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
    public void NavigateToIntroMessageAnnouncementPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "IVR Page assertion failed");
        ivrPage.navigateToIntroMessageAnnouncementPage();
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.isIntroMessageAnnouncementPageDisplayed(), "Host Value Mapping Page assertion failed");
    }
	
	@Test(priority=1)
    public void IntroMessageAnnouncementPage() {
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
    	Assert.assertTrue(IntroMessageAnnouncementPage.verifylogo(),"Host Value Mapping logo assertion failed");
    	Assert.assertTrue(IntroMessageAnnouncementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("IntroMessageAnnouncementUITest","maximize window");
    	Assert.assertTrue(IntroMessageAnnouncementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("IntroMessageAnnouncementUITest","minimize window");
    }
	@Test(priority=2)
	public void VerifyIntroMessageAnnouncementApprovedDataPage() {
        IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
       
	@Test(priority=3)
    public void VerifyIntroMessageAnnouncementAuditTrailDataPage() {
		IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
		IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
       }
       
	@Test(priority=4)
    public void VerifyMakeIntroMessageAnnouncementChangeButton() {
		IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
		IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
		IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
		Assert.assertTrue(IntroMessageAnnouncementPage.verifyAddNewIntroMessageAnnouncementRecordButton(), "add new Agent Settings record button assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
       
    @Test(priority=5)
    public void VerifyDropdownForAllTheColumns() {
		IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
		IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6)
    public void VerifyColumnsHeaderEnable() throws InterruptedException {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7)
    public void VerifyColumnsHeaderDisable() {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertFalse(IntroMessageAnnouncementPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
	@Test(priority=11)
    public void VerifyNumberOfItemsPerPageSelection() {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=12)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
        Assert.assertTrue(IntroMessageAnnouncementPage.ExporttoExcelWithoutData(IntroMessageAnnouncementDetails));
       }
    
   @Test(priority=15)
    public void SortingByAscending() throws IOException {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        IntroMessageAnnouncementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16)
    public void SortingByDescending() throws IOException {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        IntroMessageAnnouncementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Intro Message Announcement (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17)
    public void VerifyDragAndDrop() {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        IntroMessageAnnouncementPage.dragColumntoGroup("Functionality");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyDragColumntoGroup("Functionality"),"drag and drop assertion failed");
    }
    @Test(priority=18)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        IntroMessageAnnouncementPage.selectIntroMessageAnnouncementAuditTrailTab();
        IntroMessageAnnouncementPage.selectMakeIntroMessageAnnouncementChanges();
        IntroMessageAnnouncementPage.dragColumntoGroup("Functionality");
        IntroMessageAnnouncementPage.dragColumntoGroup("Functionality");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyDragColumntoGroup("Functionality"),"drag and drop assertion failed");
    }
    
    @Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
    	Assert.assertTrue(IntroMessageAnnouncementPage.verifyDatabase(IntroMessageAnnouncementDetails.getQuery()));
    }
    
    @Test(priority=20)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertFalse(IntroMessageAnnouncementPage.clearAll(IntroMessageAnnouncementDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("IntroMessageAnnouncementUITest", "clearall");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyclose());
        screenshot.captureScreen("IntroMessageAnnouncementUITest", "SearchClose");
    }
    
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
    	IntroMessageAnnouncementPage.searchwithoutextsearch(IntroMessageAnnouncementDetails);
    	Assert.assertFalse(IntroMessageAnnouncementPage.getErrorMsg());
    }
    
    @Test(priority=22)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\IntroMessageAnnouncementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    IntroMessageAnnouncementDetails IntroMessageAnnouncementDetails = new IntroMessageAnnouncementDetails(map);
    	IntroMessageAnnouncementPage IntroMessageAnnouncementPage = PageFactory.createPageInstance(driver, IntroMessageAnnouncementPage.class);
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyApprovedSectionData(IntroMessageAnnouncementDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("IntroMessageAnnouncementUITest","Invalid Search with wrong data");
        Assert.assertTrue(IntroMessageAnnouncementPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("IntroMessageAnnouncementUITest",method.getName());
	        driver.navigate().refresh();
	}
}
