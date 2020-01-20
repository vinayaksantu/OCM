package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class RoleBasedAccessManagementUITest extends BaseTest{
Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
    public void NavigateToRoleBasedAccessManagementPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToRoleBasedAccessManagementPage();
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        Assert.assertTrue(RoleBasedAccessManagementPage.isRoleBasedAccessManagementPageDisplayed(), "SMS Response Template Page assertion failed");
    }
	
	@Test(priority=1)
    public void RoleBasedAccessManagementPage() {
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
    	Assert.assertTrue(RoleBasedAccessManagementPage.verifylogo(),"User Role Mapping logo assertion failed");
    	Assert.assertTrue(RoleBasedAccessManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("RoleBasedAccessManagementUITest","maximize window");
    	Assert.assertTrue(RoleBasedAccessManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("RoleBasedAccessManagementUITest","minimize window");
    }
	@Test(priority=2)
	public void VerifyRoleBasedAccessManagementApprovedDataPage() {
        RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
       
	@Test(priority=3)
    public void VerifyRoleBasedAccessManagementAuditTrailDataPage() {
		RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
		RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
       }
	
	@Test(priority=4)
    public void VerifyMakeRoleBasedAccessManagementChangeButton() {
		RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
		RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
		RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
		Assert.assertTrue(RoleBasedAccessManagementPage.verifyAddRoleBasedAccessManagementRecordButton(), "add new Agent Settings record button assertion failed");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
       
    @Test(priority=5)
    public void VerifyDropdownForAllTheColumns() {
		RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
		RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6)
    public void VerifyColumnsHeaderEnable() throws InterruptedException {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7)
    public void VerifyColumnsHeaderDisable() {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertFalse(RoleBasedAccessManagementPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    @Test(priority=10)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
	@Test(priority=11)
    public void VerifyNumberOfItemsPerPageSelection() {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=12)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails UserDetails = new UserDetails(map);
        Assert.assertTrue(RoleBasedAccessManagementPage.ExporttoExcelWithoutData(UserDetails));
       }
    
   @Test(priority=15)
    public void SortingByAscending() throws IOException {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        RoleBasedAccessManagementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16)
    public void SortingByDescending() throws IOException {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        RoleBasedAccessManagementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17)
    public void VerifyDragAndDrop() {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        RoleBasedAccessManagementPage.dragColumntoGroup("Role Name");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyDragColumntoGroup("Role Name"),"drag and drop assertion failed");
    }
    @Test(priority=18)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        RoleBasedAccessManagementPage.selectRoleBasedAccessManagementAuditTrailTab();
        RoleBasedAccessManagementPage.selectMakeRoleBasedAccessManagementChanges();
        RoleBasedAccessManagementPage.dragColumntoGroup("Role Name");
        RoleBasedAccessManagementPage.dragColumntoGroup("Role Name");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyDragColumntoGroup("Role Name"),"drag and drop assertion failed");
    }
    
    @Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
	    UserDetails UserDetails = new UserDetails(map);
    	Assert.assertTrue(RoleBasedAccessManagementPage.verifyDatabase(UserDetails.getQuery()));
    }
    
    @Test(priority=20)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    UserDetails UserDetails = new UserDetails(map);
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        Assert.assertFalse(RoleBasedAccessManagementPage.clearAll(UserDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("RoleBasedAccessManagementUITest", "clearall");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyclose());
        screenshot.captureScreen("RoleBasedAccessManagementUITest", "SearchClose");
    }
    
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    UserDetails UserDetails = new UserDetails(map);
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
    	RoleBasedAccessManagementPage.searchwithoutextsearch(UserDetails);
    	Assert.assertEquals(RoleBasedAccessManagementPage.getErrorMsg(),"Please enter the text to search or remove the filter");
    }
    
    @Test(priority=22)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	    UserDetails UserDetails = new UserDetails(map);
    	RoleBasedAccessManagementPage RoleBasedAccessManagementPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementPage.class);
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyApprovedSectionData(UserDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("RoleBasedAccessManagementUITest","Invalid Search with wrong data");
        Assert.assertTrue(RoleBasedAccessManagementPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("RoleBasedAccessManagementUITest",method.getName());
	        driver.navigate().refresh();
	}

}

	
