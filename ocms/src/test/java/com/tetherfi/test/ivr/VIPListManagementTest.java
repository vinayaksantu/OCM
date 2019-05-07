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

import com.tetherfi.model.ivr.IvrConfigDetails;
import com.tetherfi.model.ivr.VipListManagementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrConfigPage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.VipListManagementPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class VIPListManagementTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToVIPListManagementPage() {
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
        ivrPage.navigateToVipListManagementPage();
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.isVipListManagementPageDisplayed(), "VIP List Management page assertion failed");
    	screenshot.captureScreen(driver,"VIPListManagementTest","VIPListManagementPage");
    }
	
	/*@Test(priority=1)
	public void VIPListManagementPage() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(vipListMangementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"VIPListManagementTest","Maximize");
    	Assert.assertTrue(vipListMangementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"VIPListManagementTest","Minimize");	
    }
	
	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertFalse(vipListMangementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
   @Test(priority=5)
    public void AddNewVIPListRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addNewVipListManagementRecord(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    }
    
    @Test(priority=6,dependsOnMethods = "AddNewVIPListRecord")
    public void AddDuplicateVIPListRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addNewVipListManagementRecord(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Record Creation Failed, Already Exist", "Duplicate record assertion failed");
    }
    
    @Test(priority=7,dependsOnMethods ="AddNewVIPListRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyVIPListManagementCreate(vipListManagementDetails,"Create"));
    }
    
    @Test(priority=8)
    public void AddEmptyRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addNewEmptyRecord(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Caller ID, Email ID, FB Handle, Messenger ID, CustomerID Type, CustomerID No, Country, Contact Type, Inclusion Flag, Exclusion Flag, Other Data", "Add invalid record assertion failed");
    }
    
    @Test(priority=9)
    public void AddRecordWithoutCallerID() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutCallerID(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Caller ID", "Add invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutEmailId() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutEmailId(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Email ID", "Add invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordWithoutFBHandle() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutFBHandle(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide FB Handle", "Add invalid record assertion failed");
    }
    
    @Test(priority=12)
    public void AddRecordWithoutMessengerID() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutMessengerID(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Messenger ID", "Add invalid record assertion failed");
    }
    
    @Test(priority=13)
    public void AddRecordWithoutCustomerIDType() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutCustomerIDType(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide CustomerID Type", "Add invalid record assertion failed");
    }
    
    @Test(priority=14)
    public void AddRecordWithoutCustomerIDNo() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutCustomerIDNo(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide CustomerID No", "Add invalid record assertion failed");
    }
    
    @Test(priority=15)
    public void AddRecordWithoutCountry() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutCountry(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Country", "Add invalid record assertion failed");
    }
    
    @Test(priority=16)
    public void AddRecordWithoutContactType() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutContactType(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Contact Type", "Add invalid record assertion failed");
    }
    
    @Test(priority=17)
    public void AddRecordWithoutInclusionFlag() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutInclusionFlag(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Inclusion Flag", "Add invalid record assertion failed");
    }
    
    @Test(priority=18)
    public void AddRecordWithoutExclusionFlag() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutExclusionFlag(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Exclusion Flag", "Add invalid record assertion failed");
    }
    
    @Test(priority=19)
    public void AddRecordWithoutOtherData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.addRecordWithoutOtherData(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please Provide Other Data", "Add invalid record assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyCancelBtnAtAddRecord(){
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.clickOnAddRecord();
        vipListMangementPage.clickOnCancelBtn();
        Assert.assertFalse(vipListMangementPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
	
    @Test(priority=21)
    public void EditVIPListRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.editVIPListRecord(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(priority=22,dependsOnMethods="EditVIPListRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyVIPListManagementUpdate(vipListManagementDetails,"Update"));
    }
    
    @Test(priority=23,dependsOnMethods = "EditVIPListRecord")
    public void EditWithoutModifyReasonRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.editVIPListRecordWithoutModifyReason(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=24,dependsOnMethods = "EditWithoutModifyReasonRecord")
    public void VerifyCancelBtnAtEditConfigRecord(){
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.searchVipListManagementRecord("12");
        vipListMangementPage.clickOnEditButton();
        vipListMangementPage.clickOnCancelBtn();
        Assert.assertFalse(vipListMangementPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=25)
    public void searchPage() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertFalse(vipListMangementPage .clearAll(vipListManagementDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver,"VIPListManagementTest", "clearall");
        Assert.assertTrue(vipListMangementPage.verifyclose());
    }
    
    @Test(priority=26)
    public void searchwithoutSearchTextbox() throws IOException {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.searchwithoutextsearch();
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=27)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=28)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VIP List Management.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
    	Assert.assertTrue(vipListMangementPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=29)
    public void DeleteWithoutDeleteReasonRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.deleteVIPListWithoutDeleteReasonRecord(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=30)
    public void VerifyCancelBtnAtDeleteVIPListRecord(){
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.searchVipListManagementRecord("2");
        vipListMangementPage.clickOnDeleteButton();
        vipListMangementPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(vipListMangementPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=31,dependsOnMethods = "VerifyCancelBtnAtDeleteVIPListRecord")
    public void DeleteVIPListRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.deleteVIPListManagementRecord(vipListManagementDetails);
        Assert.assertEquals(vipListMangementPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(priority=32,dependsOnMethods= {"DeleteVIPListRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyVIPListManagementdelete(vipListManagementDetails,"Delete"));
    }
    
    @Test(priority=33)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
    	Assert.assertTrue(vipListMangementPage.verifyinvalidsearchwithwrongdata(vipListManagementDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("VIPListManagementTest","Invalid Search with wrong data");
        Assert.assertTrue(vipListMangementPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=34)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.ExporttoExcelWithoutData(vipListManagementDetails));
    }
  
    @Test(priority=35)
    public void SortingByAscending() throws IOException {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VIP List Management (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(vipListMangementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=36)
    public void SortingByDescending() throws IOException {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        vipListMangementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VIP List Management (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(vipListMangementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=37)
    public void GroupBy()
    {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
    	Assert.assertTrue(vipListMangementPage.groupby());
        screenshot.captureScreen("VIPListManagementTest", "GroupBy");
    	Assert.assertTrue(vipListMangementPage.groupby());
        screenshot.captureScreen("VIPListManagementTest", "AlreadyGroupBy");
    }
    
    @Test(priority=38)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
    	Assert.assertTrue(vipListMangementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=39)
    public void VerifyArrowMoveForFirstAndLastPage() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=40)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=41)
    public void VerifyNumberOfItemsPerPageSelection() {
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        Assert.assertTrue(vipListMangementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    */
    
    @Test(priority=36)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VIPListManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        VipListManagementPage vipListMangementPage = PageFactory.createPageInstance(driver, VipListManagementPage.class);
        VipListManagementDetails vipListManagementDetails = new VipListManagementDetails(map);
    	Assert.assertTrue(vipListMangementPage.verifyDatabase(vipListManagementDetails.getQuery()));
    }
	
	 @AfterMethod
	    public void afterEachMethod(Method method) throws InterruptedException {
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("VIPListManagementTest",method.getName());
		        driver.navigate().refresh();
		}
}