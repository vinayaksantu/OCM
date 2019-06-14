package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.VBEnrollmentFlagDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.VbEnrollmentFlagPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class VBEnrollmentFlagTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToVbEnrollmentFlagPage() throws Exception {
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
        ivrPage.navigateToVbEnrollmentFlagPage();
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.isVbEnrollmentFlagPageDisplayed(), "VBEnrollmentFlag page assertion failed");
    	screenshot.captureScreen("VBEnrollmentFlagTest","VbEnrollmentFlagPage");
    }
	
	@Test(priority=1)
	public void VbEnrollmentFlagPage() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(VbEnrollmentFlagPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"VBEnrollmentFlagTest","Maximize");
    	Assert.assertTrue(VbEnrollmentFlagPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"VBEnrollmentFlagTest","Minimize");	
    }
	
	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertFalse(VbEnrollmentFlagPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
   @Test(priority=5)
    public void AddNewVBEnrollmentFlagRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addNewVBEnrollmentFlagRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    }
    
    @Test(priority=6,dependsOnMethods = "AddNewVBEnrollmentFlagRecord")
    public void AddDuplicateVBEnrollmentFlagRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addNewVBEnrollmentFlagRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Duplicate DNIS");
    }
    
    @Test(priority=7,dependsOnMethods ="AddNewVBEnrollmentFlagRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyVBEnrollmentFlagCreate(VBEnrollmentFlagDetails,"Create"));
    }
    
    @Test(priority=8)
    public void AddEmptyRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addNewEmptyRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Please Provide DNIS, Hotline Name, Enrollment Flag", "Add invalid record assertion failed");
    }
    
    @Test(priority=9)
    public void AddRecordWithoutDNIS() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addRecordWithoutDNIS(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Please Provide DNIS", "Add invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutHotLineName() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addRecordWithoutHotLineName(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Please Provide Hotline Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordWithoutEnrollmentFlag() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addRecordWithoutEnrollmentFlag(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Please Provide Enrollment Flag", "Add invalid record assertion failed");
    }
    
    @Test(priority=12)
    public void VerifyCancelBtnAtAddRecord(){
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.clickOnAddRecord();
        VbEnrollmentFlagPage.clickOnCancelBtn();
        Assert.assertFalse(VbEnrollmentFlagPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
	
    @Test(priority=13)
    public void EditVBEnrollmentFlagRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.editVBEnrollmentFlagRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(priority=14,dependsOnMethods="EditVBEnrollmentFlagRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyVBEnrollmentFlagUpdate(VBEnrollmentFlagDetails,"Update"));
    }
    
    @Test(priority=15,dependsOnMethods = "EditVBEnrollmentFlagRecord")
    public void EditWithoutModifyReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.editVBEnrollmentFlagRecordWithoutModifyReason(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=16,dependsOnMethods = "EditWithoutModifyReasonRecord")
    public void VerifyCancelBtnAtEditRecord() throws Exception{
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.searchVbEnrollmentFlagRecord("40000");
        Thread.sleep(1000);
        VbEnrollmentFlagPage.clickOnEditButton();
        VbEnrollmentFlagPage.clickOnCancelBtn();
        Assert.assertFalse(VbEnrollmentFlagPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=17)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertFalse(VbEnrollmentFlagPage .clearAll(VBEnrollmentFlagDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("VBEnrollmentFlagTest", "clearall");
        Assert.assertTrue(VbEnrollmentFlagPage.verifyclose());
    }
    
    @Test(priority=18)
    public void searchwithoutSearchTextbox() throws IOException {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.searchwithoutextsearch();
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=20)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VB Enrollment Flag.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=21)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.deleteVBEnrollmentFlagWithoutDeleteReasonRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=22)
    public void VerifyCancelBtnAtDeleteVBEnrollmentFlagRecord() throws Exception{
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.searchVbEnrollmentFlagRecord("40000");
        Thread.sleep(1000);
        VbEnrollmentFlagPage.clickOnDeleteButton();
        VbEnrollmentFlagPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(VbEnrollmentFlagPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=23,dependsOnMethods = "VerifyCancelBtnAtDeleteVBEnrollmentFlagRecord")
    public void DeleteVBEnrollmentFlagRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.deleteVBEnrollmentFlagRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(priority=24,dependsOnMethods= {"DeleteVBEnrollmentFlagRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyVBEnrollmentFlagdelete(VBEnrollmentFlagDetails,"Delete"));
    }
    
    @Test(priority=24)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.verifyinvalidsearchwithwrongdata(VBEnrollmentFlagDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("VBEnrollmentFlagTest","Invalid Search with wrong data");
        Assert.assertTrue(VbEnrollmentFlagPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=25)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.ExporttoExcelWithoutData(VBEnrollmentFlagDetails));
    }
  
    @Test(priority=26)
    public void SortingByAscending() throws IOException {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VB Enrollment Flag (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(VbEnrollmentFlagPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=27)
    public void SortingByDescending() throws IOException {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VB Enrollment Flag (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(VbEnrollmentFlagPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void GroupBy()
    {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.groupby());
        screenshot.captureScreen("VBEnrollmentFlagTest", "GroupBy");
    	Assert.assertTrue(VbEnrollmentFlagPage.groupby());
        screenshot.captureScreen("VBEnrollmentFlagTest", "AlreadyGroupBy");
    }
    
    @Test(priority=29)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForFirstAndLastPage() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyNumberOfItemsPerPageSelection() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    
    @Test(priority=33)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
    	Assert.assertTrue(VbEnrollmentFlagPage.verifyDatabase(VBEnrollmentFlagDetails.getQuery()));
    }
	
	 @AfterMethod
	    public void afterEachMethod(Method method) throws InterruptedException {
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("VBEnrollmentFlagTest",method.getName());
		        driver.navigate().refresh();
		}
}
