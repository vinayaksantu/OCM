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

import com.tetherfi.model.ivr.BranchManagementDetails;
import com.tetherfi.pages.BranchManagementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class BranchManagementTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
    public void NavigateToBranchManagementPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "IVR page assertion failed");
        ivrPage.navigateToBranchManagementPage();
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.isBranchManagementPageDisplayed(), "Branch Management page assertion failed");
    }
	
	@Test (priority=1)
    public void BranchManagementPage() {
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	Assert.assertTrue(branchManagementPage.verifylogo(),"BranchManagement logo assertion failed");
    	Assert.assertTrue(branchManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","BranchManagementTest");
    	Assert.assertTrue(branchManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","BranchManagementTest");
    }
	
	@Test(priority=2)
	public void VerifyBranchManagementApprovedDataPage() 
	{
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        Assert.assertTrue(branchManagementPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
     }
       
	@Test(priority=3)
    public void VerifyBranchManagementAuditTrailDataPage() {
		BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        branchManagementPage.selectBranchManagementAuditTrailTab();
        Assert.assertTrue(branchManagementPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
       }
       
	@Test(priority=4)
    public void VerifyMakeBranchManagementChangeButton() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
    	Assert.assertTrue(branchManagementPage.verifyAddNewBranchManagementRecordButton(), "add new Agent Settings record button assertion failed");
    	Assert.assertTrue(branchManagementPage.verifyGoBackButton(), "Go back button assertion failed");
    	Assert.assertTrue(branchManagementPage.verifyExportToExcelButton(), "export to excel button assertion failed");
        Assert.assertTrue(branchManagementPage.verifyMakerDataTableHeaders(), "maker table headers assertion failed");
       }
       
    @Test(priority=5)
    public void VerifyDropdownForAllTheColumns() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
       }
     
    @Test(priority=6)
    public void VerifyColumnsHeaderEnable() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
     }
       
    @Test(priority=7)
    public void VerifyColumnsHeaderDisable() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertFalse(branchManagementPage.verifycolumnsHeaderDisbaled(),"columns disabled assertion failed");
      }
    
    @Test(priority=8)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=9)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=10)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
    @Test(priority=11)
    public void VerifyNumberOfItemsPerPageSelection() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=12)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=13)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Branch Management.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        Assert.assertTrue(branchManagementPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=14)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        Assert.assertTrue(branchManagementPage.ExporttoExcelWithoutData(branchManagementDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "BranchManagementTest");
       }
    
    @Test(priority=15)
    public void SortingByAscending() throws IOException {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
    	branchManagementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Branch Management (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(branchManagementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=16)
    public void SortingByDescending() throws IOException {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
    	branchManagementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Branch Management (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(branchManagementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=17)
    public void VerifyDragAndDrop() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
    	branchManagementPage.dragColumntoGroup("BranchType");
        Assert.assertTrue(branchManagementPage.verifyDragColumntoGroup("BranchType"),"drag and drop assertion failed");
    }
   
    @Test(priority=18)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
    	branchManagementPage.selectBranchManagementAuditTrailTab();
    	branchManagementPage.selectMakeBranchManagementChanges();
    	branchManagementPage.dragColumntoGroup("BranchType");
    	branchManagementPage.dragColumntoGroup("BranchType");
        Assert.assertTrue(branchManagementPage.verifyDragColumntoGroup("BranchType"),"drag and drop assertion failed");
    }
    
    @Test(priority=19)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
    	Assert.assertTrue(branchManagementPage.verifyDatabase(branchManagementDetails.getQuery()));
    }
    
    @Test(priority=20)
    public void searchPage() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
    	Assert.assertFalse(branchManagementPage.clearAll(branchManagementDetails),"ClearAll Assertion Failed");
    	screenshot.captureScreen(driver, "clearall","BranchManagementTest");
        Assert.assertTrue(branchManagementPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","BranchManagementTest");
    }
    
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        branchManagementPage.searchwithoutextsearch(branchManagementDetails);
    	Assert.assertFalse(branchManagementPage.getErrorMsg());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","BranchManagementTest");
    }
    
    @Test(priority=22)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    	BranchManagementPage branchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementDetails branchManagementDetails = new BranchManagementDetails(map);
        Assert.assertTrue(branchManagementPage.verifyApprovedSectionData(branchManagementDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "BranchManagementTest");
        Assert.assertTrue(branchManagementPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "BranchManagementTest");
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws InterruptedException {
    	 if(ITestResult.FAILURE==result.getStatus()){
       		 try{
       			 screenshot.captureScreen(method.getName(),"BranchManagementTest");
       		 }
       		catch (Exception e){
       		 System.out.println("Exception while taking screenshot "+e.getMessage());
       		 } 
       		 driver.navigate().refresh();
       		 }    }
}
