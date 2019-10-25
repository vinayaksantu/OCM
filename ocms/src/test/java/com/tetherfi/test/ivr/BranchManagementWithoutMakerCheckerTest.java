package com.tetherfi.test.ivr;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.BranchManagementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.BranchManagementWHPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class BranchManagementWithoutMakerCheckerTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
    public void NavigateToBranchManagementPage() throws Exception {
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
        ivrPage.navigateToBranchManagementPage();
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertTrue(BranchManagementWHPage.isBranchManagementPageDisplayed(), "adhoc option enhancement page assertion failed");
    }
	@Test(priority=1)
    public void BranchManagementWHPage() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	Assert.assertTrue(BranchManagementWHPage.verifylogo(),"BranchManagement logo assertion failed");
    	Assert.assertTrue(BranchManagementWHPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("BranchManagementTest","maximize window");
    	Assert.assertTrue(BranchManagementWHPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("BranchManagementTest","minimize window");
    }
    @Test(priority=2)
    public void AddNewBranchManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.addNewBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getSuccessMessage(),"Record Created Successfully", "Add New record assertion failed");

    }
    @Test(dependsOnMethods ="AddNewBranchManagementRecord", priority=3)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementCreate(BranchManagementDetails,"Create"));
    }
   
    @Test(dependsOnMethods = "AddNewBranchManagementRecord",priority=4)
    public void AddDuplicateBranchManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.addNewBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Record Creation Failed, Already Exist", "Duplicate record assertion failed");
    }
    
    @Test(priority=5)
    public void AddEmptyBranchManagementRecord() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.addEmptyBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide MainLines, SubLines, Location, BranchType, Branch Wav File, Address Wav File, Branch Name, Address Text, Line/Estate Order, Status, Language", "Empty record assertion failed");
    }
    @Test(priority=6)
    public void AddBranchManagementRecordWithoutMainLines() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.addwithoutMainLines(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide MainLines, SubLines");
    }
    
    @Test(priority=7)
    public void AddBranchManagementRecordWithoutSubLines() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
     	BranchManagementWHPage.addwithoutSubLines(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide SubLines");
    }  
    
    @Test(priority=8)
    public void AddBranchManagementRecordWithoutLocation() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.addwithoutLocation(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide Location");
    }
    @Test(priority=9)
    public void AddBranchManagementRecordWithoutBranchType() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.addwithoutBranchType(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide BranchType");
    }
    @Test(priority=10)
    public void AddBranchManagementRecordWithoutBranchName() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	BranchManagementWHPage.addwithoutBranchName(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide Branch Wav File, Branch Name");
    }
    @Test(priority=11)
    public void AddBranchManagementRecordWithoutAddressName() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);       
    	BranchManagementWHPage.addwithoutAddress(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide Address Wav File, Address Text");
    }
    @Test(priority=12)
    public void AddEmptyBranchManagementRecordWithoutLineEstateOrder() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);       
    	BranchManagementWHPage.addwithoutLineOrder(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide Line/Estate Order");
    }
    @Test(priority=13)
    public void AddBranchManagementRecordWithoutStatus() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);         	
        BranchManagementWHPage.addwithoutStatus(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide Status");   
    }
    
    @Test(priority=14)
    public void AddBranchManagementRecordWithoutLanguage() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);         	
        BranchManagementWHPage.addwithoutLanguage(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please Provide Language");   
    }
    
    @Test(priority=15)
    public void VerifyCancelButtonAtAddBranchManagementRecord() throws Exception {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails = new BranchManagementDetails(map);
        Assert.assertFalse(BranchManagementWHPage.addCancelButton(BranchManagementDetails),"Cancel Btn at Add record assertion failed");
    }
    
    @Test(priority=16)
    public void EditBranchManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage= PageFactory.createPageInstance(driver,BranchManagementWHPage.class);
        BranchManagementWHPage.EditBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(dependsOnMethods="EditBranchManagementRecord",priority=17)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementUpdate(BranchManagementDetails,"Update"));
    }
    
    @Test(priority=18)
    public void EditWithoutModifyReasonBranchManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage= PageFactory.createPageInstance(driver,BranchManagementWHPage.class);
        BranchManagementWHPage.EditRecordWithoutModifyReason(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getSuccessMessage(),"Please enter the modify reason","Edit without modify reason record assertion failed");
    }
    
    @Test(priority=19)
    public void VerifyCancelButtonAtEditBranchManagementRecord() throws IOException, Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        Assert.assertTrue(BranchManagementWHPage.EditCancel(BranchManagementDetails), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=20)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertTrue(BranchManagementWHPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=21)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	Assert.assertTrue(BranchManagementWHPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=22)
    public void SortingByAscending() throws IOException {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(BranchManagementWHPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=23)
    public void SortingByDescending() throws IOException {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementWHPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(BranchManagementWHPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=24)
    public void GroupBy()
    {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	Assert.assertTrue(BranchManagementWHPage.groupby());
        screenshot.captureScreen("BranchManagementTest", "GroupBy");
    	Assert.assertTrue(BranchManagementWHPage.groupby());
        screenshot.captureScreen("BranchManagementTest","AlreadyGroupBy");
    }
    
    @Test(priority=25)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	Assert.assertTrue(BranchManagementWHPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=26)
    public void VerifyArrowMoveForFirstAndLastPage() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertTrue(BranchManagementWHPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=27)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertTrue(BranchManagementWHPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=28)
    public void VerifyNumberOfItemsPerPageSelection() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertTrue(BranchManagementWHPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=29)
    public void VerifyDropdownForAllTheColumns() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertTrue(BranchManagementWHPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyColumnsHeaderEnable() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertTrue(BranchManagementWHPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyColumnsHeaderDisable() {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertFalse(BranchManagementWHPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=32)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
    	Assert.assertTrue(BranchManagementWHPage.verifyDatabase(BranchManagementDetails.getQuery()));
    }
    
    @Test(priority=33)
    public void DeleteWithoutDeleteReasonInBranchManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage= PageFactory.createPageInstance(driver,BranchManagementWHPage.class);
        BranchManagementWHPage.DeleteBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please enter the delete reason","delete record assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyCancelButtonInDeleteBranchManagementRecord() throws Exception {
        BranchManagementWHPage BranchManagementWHPage= PageFactory.createPageInstance(driver,BranchManagementWHPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        Assert.assertTrue(BranchManagementWHPage.DeleteCancel(BranchManagementDetails), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=35)//,dependsOnMethods= {"EditBranchManagementRecord"})
    public void DeleteBranchManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        BranchManagementWHPage BranchManagementWHPage= PageFactory.createPageInstance(driver,BranchManagementWHPage.class);
        BranchManagementWHPage.DeleteBranchManagementRecord(BranchManagementDetails);
        Assert.assertEquals(BranchManagementWHPage.getSuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(dependsOnMethods= {"DeleteBranchManagementRecord"},priority=36)
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyBranchManagementDelete(BranchManagementDetails,"Delete"));
    }
     
    @Test(priority=37)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
    	BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
        Assert.assertFalse(BranchManagementWHPage.clearAll(BranchManagementDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("BranchManagementTest","clearall");
        Assert.assertTrue(BranchManagementWHPage.verifyclose());
    }
    @Test(priority=38)
    public void searchwithoutSearchTextbox() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
    	BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	BranchManagementWHPage.searchwithoutextsearch(BranchManagementDetails);
    	Assert.assertEquals(BranchManagementWHPage.getErrorMsg(),"Please enter the text to search or remove the filter");
    }
   
    @Test(priority=39)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
    	BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	BranchManagementWHPage.DeleteBranchManagementRecord(BranchManagementDetails);
    	Assert.assertTrue(BranchManagementWHPage.verifyinvalidsearchwithwrongdata(BranchManagementDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("BranchManagementTest","Invalid Search");
        Assert.assertTrue(BranchManagementWHPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=40)
    public void ExporttoExcelWithoutData() throws Exception
    {
        BranchManagementWHPage BranchManagementWHPage = PageFactory.createPageInstance(driver, BranchManagementWHPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\BranchManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        BranchManagementDetails BranchManagementDetails=new BranchManagementDetails(map);
        Assert.assertTrue(BranchManagementWHPage.ExporttoExcelWithoutData(BranchManagementDetails));
    }   
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("BranchManagementTest",method.getName());
	        driver.navigate().refresh();
	}
}
