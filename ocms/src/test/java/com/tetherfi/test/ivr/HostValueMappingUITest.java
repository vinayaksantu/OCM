package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.HostValueMappingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.HostValueMappingPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class HostValueMappingUITest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	
	@BeforeClass
    public void NavigateToHostValueMappingPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "IVR Page assertion failed");
        ivrPage.navigateToHostValueMappingPage();
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.isHostValueMappingPageDisplayed(), "Host Value Mapping Page assertion failed");
    }
	
	@Test(priority=1)
    public void HostValueMappingPage() {
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
    	Assert.assertTrue(hostValueMappingPage.verifylogo(),"Host Value Mapping logo assertion failed");
    	Assert.assertTrue(hostValueMappingPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("HostValueMappingUITest","maximize window");
    	Assert.assertTrue(hostValueMappingPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("HostValueMappingUITest","minimize window");
    }
	@Test(priority=2)
	public void VerifyHostValueMappingApprovedDataPage() {
        HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
       
	@Test(priority=3)
    public void VerifyHostValueMappingAuditTrailDataPage() {
		HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
		hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        Assert.assertTrue(hostValueMappingPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
       }
       
	@Test(priority=4)
    public void VerifyMakeHostValueMappingChangeButton() {
		HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
		hostValueMappingPage.selectHostValueMappingAuditTrailTab();
		hostValueMappingPage.selectMakeHostValueMappingChanges();
		Assert.assertTrue(hostValueMappingPage.verifyAddNewHostValueMappingRecordButton(), "add new Agent Settings record button assertion failed");
        Assert.assertTrue(hostValueMappingPage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(hostValueMappingPage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(hostValueMappingPage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
       
    @Test(priority=5)
    public void VerifyDropdownForAllTheColumns() {
		HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
		hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6)
    public void VerifyColumnsHeaderEnable() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7)
    public void VerifyColumnsHeaderDisable() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertFalse(hostValueMappingPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
	@Test(priority=11)
    public void VerifyNumberOfItemsPerPageSelection() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=12)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Host Value Mapping.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        Assert.assertTrue(hostValueMappingPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
        Assert.assertTrue(hostValueMappingPage.ExporttoExcelWithoutData(hostValueMappingDetails));
       }
    
    @Test(priority=15)
    public void SortingByAscending() throws IOException {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        hostValueMappingPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Host Value Mapping (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(hostValueMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16)
    public void SortingByDescending() throws IOException {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        hostValueMappingPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Host Value Mapping (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(hostValueMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17)
    public void VerifyDragAndDrop() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        hostValueMappingPage.dragColumntoGroup("Functionality");
        Assert.assertTrue(hostValueMappingPage.verifyDragColumntoGroup("Functionality"),"drag and drop assertion failed");
    }
    @Test(priority=18)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        hostValueMappingPage.selectHostValueMappingAuditTrailTab();
        hostValueMappingPage.selectMakeHostValueMappingChanges();
        hostValueMappingPage.dragColumntoGroup("Functionality");
        hostValueMappingPage.dragColumntoGroup("Functionality");
        Assert.assertTrue(hostValueMappingPage.verifyDragColumntoGroup("Functionality"),"drag and drop assertion failed");
    }
    
    @Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
    	Assert.assertTrue(hostValueMappingPage.verifyDatabase(hostValueMappingDetails.getQuery()));
    }
    
    @Test(priority=20)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertFalse(hostValueMappingPage.clearAll(hostValueMappingDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("HostValueMappingUITest", "clearall");
        Assert.assertTrue(hostValueMappingPage.verifyclose());
        screenshot.captureScreen("HostValueMappingUITest", "SearchClose");
    }
    
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
    	hostValueMappingPage.searchwithoutextsearch(hostValueMappingDetails);
    	Assert.assertEquals(hostValueMappingPage.getErrorMsg(),"Please enter the text to search or remove the filter");
    }
    
    @Test(priority=22)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\HostValueMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
	    HostValueMappingDetails hostValueMappingDetails = new HostValueMappingDetails(map);
    	HostValueMappingPage hostValueMappingPage = PageFactory.createPageInstance(driver, HostValueMappingPage.class);
        Assert.assertTrue(hostValueMappingPage.verifyApprovedSectionData(hostValueMappingDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("HostValueMappingUITest","Invalid Search with wrong data");
        Assert.assertTrue(hostValueMappingPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("HostValueMappingUITest",method.getName());
	        driver.navigate().refresh();
	}
}
