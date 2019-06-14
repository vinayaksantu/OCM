package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.MenuDescriptionMappingDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.MenuDescriptionMappingPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class MenuDescriptionMappingTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToMenuDescriptionMappingPage() throws Exception {
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
        ivrPage.scrollingToBottomofAPage();
        ivrPage.navigateToMenuDescriptionMappingPage();
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.isMenuDescriptionMappingPageDisplayed(), "MenuDescriptionMapping page assertion failed");
    	screenshot.captureScreen(driver,"MenuDescriptionMappingTest","MenuDescriptionMappingPage");
    }
	
	@Test(priority=1)
	public void MenuDescriptionMappingPage() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(menuDescriptionMappingPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"MenuDescriptionMappingTest","Maximize");
    	Assert.assertTrue(menuDescriptionMappingPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"MenuDescriptionMappingTest","Minimize");	
    }
	
	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertFalse(menuDescriptionMappingPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
   @Test(priority=5)
    public void AddNewMenuDescriptionMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.addNewMenuDescriptionMappingRecord(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    }
    
    @Test(priority=6,dependsOnMethods = "AddNewMenuDescriptionMappingRecord")
    public void AddDuplicateMenuDescriptionMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.addNewMenuDescriptionMappingRecord(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Duplicate MenuId");
    }
    
    @Test(priority=7,dependsOnMethods ="AddNewMenuDescriptionMappingRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyMenuDescriptionMappingCreate(MenuDescriptionMappingDetails,"Create"));
    }
    
    @Test(priority=8)
    public void AddEmptyRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.addNewEmptyRecord(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Please Provide MenuId, MenuName, Intent", "Add invalid record assertion failed");
    }
    
    @Test(priority=9)
    public void AddRecordWithoutMenuID() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.addRecordWithoutMenuID(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Please Provide MenuId", "Add invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutMenuName() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.addRecordWithoutMenuName(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Please Provide MenuName", "Add invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordWithoutIntent() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.addRecordWithoutIntent(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Please Provide Intent", "Add invalid record assertion failed");
    }
    
    @Test(priority=12)
    public void VerifyCancelBtnAtAddRecord(){
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.clickOnAddRecord();
        menuDescriptionMappingPage.clickOnCancelBtn();
        Assert.assertFalse(menuDescriptionMappingPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
	
    @Test(priority=13)
    public void EditMenuDescriptionMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.editMenuDescriptionMappingRecord(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(priority=14,dependsOnMethods="EditMenuDescriptionMappingRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyMenuDescriptionMappingUpdate(MenuDescriptionMappingDetails,"Update"));
    }
    
    @Test(priority=15,dependsOnMethods = "EditMenuDescriptionMappingRecord")
    public void EditWithoutModifyReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.editMenuDescriptionMappingRecordWithoutModifyReason(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=16,dependsOnMethods = "EditWithoutModifyReasonRecord")
    public void VerifyCancelBtnAtEditRecord(){
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.searchMenuDescriptionMappingRecord("4");
        menuDescriptionMappingPage.clickOnEditButton();
        menuDescriptionMappingPage.clickOnCancelBtn();
        Assert.assertFalse(menuDescriptionMappingPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=17)
    public void searchPage() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertFalse(menuDescriptionMappingPage .clearAll(MenuDescriptionMappingDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver,"MenuDescriptionMappingTest", "clearall");
        Assert.assertTrue(menuDescriptionMappingPage.verifyclose());
    }
    
    @Test(priority=18)
    public void searchwithoutSearchTextbox() throws IOException {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.searchwithoutextsearch();
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=20)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Menu Description Mapping.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
    	Assert.assertTrue(menuDescriptionMappingPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=21)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.deleteMenuDescriptionMappingWithoutDeleteReasonRecord(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=22)
    public void VerifyCancelBtnAtDeleteMenuDescriptionMappingRecord(){
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.searchMenuDescriptionMappingRecord("4");
        menuDescriptionMappingPage.clickOnDeleteButton();
        menuDescriptionMappingPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(menuDescriptionMappingPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=23,dependsOnMethods = "VerifyCancelBtnAtDeleteMenuDescriptionMappingRecord")
    public void DeleteMenuDescriptionMappingRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.deleteMenuDescriptionMappingRecord(MenuDescriptionMappingDetails);
        Assert.assertEquals(menuDescriptionMappingPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(priority=24,dependsOnMethods= {"DeleteMenuDescriptionMappingRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyMenuDescriptionMappingdelete(MenuDescriptionMappingDetails,"Delete"));
    }
    
    @Test(priority=24)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
    	Assert.assertTrue(menuDescriptionMappingPage.verifyinvalidsearchwithwrongdata(MenuDescriptionMappingDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("MenuDescriptionMappingTest","Invalid Search with wrong data");
        Assert.assertTrue(menuDescriptionMappingPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=25)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.ExporttoExcelWithoutData(MenuDescriptionMappingDetails));
    }
  
    @Test(priority=26)
    public void SortingByAscending() throws IOException {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Menu Description Mapping (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(menuDescriptionMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=27)
    public void SortingByDescending() throws IOException {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        menuDescriptionMappingPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Menu Description Mapping (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(menuDescriptionMappingPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void GroupBy()
    {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
    	Assert.assertTrue(menuDescriptionMappingPage.groupby());
        screenshot.captureScreen("MenuDescriptionMappingTest", "GroupBy");
    	Assert.assertTrue(menuDescriptionMappingPage.groupby());
        screenshot.captureScreen("MenuDescriptionMappingTest", "AlreadyGroupBy");
    }
    
    @Test(priority=29)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
    	Assert.assertTrue(menuDescriptionMappingPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForFirstAndLastPage() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyNumberOfItemsPerPageSelection() {
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        Assert.assertTrue(menuDescriptionMappingPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    
    @Test(priority=33)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\MenuDescriptionMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        MenuDescriptionMappingPage menuDescriptionMappingPage = PageFactory.createPageInstance(driver, MenuDescriptionMappingPage.class);
        MenuDescriptionMappingDetails MenuDescriptionMappingDetails = new MenuDescriptionMappingDetails(map);
    	Assert.assertTrue(menuDescriptionMappingPage.verifyDatabase(MenuDescriptionMappingDetails.getQuery()));
    }
	
	 @AfterMethod
	    public void afterEachMethod(Method method) throws InterruptedException {
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("MenuDescriptionMappingTest",method.getName());
		        driver.navigate().refresh();
		}
}
