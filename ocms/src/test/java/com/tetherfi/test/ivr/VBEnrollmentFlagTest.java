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

	@BeforeClass
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
    	screenshot.captureScreen("VBEnrollmentFlagTest","Maximize");
    	Assert.assertTrue(VbEnrollmentFlagPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("VBEnrollmentFlagTest","Minimize");	
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
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Dnis Already Exists! Please provide other Dnis");
    }
    
    @Test(priority=7)
    public void AddEmptyRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addNewEmptyRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Please Provide DNIS, Hotline Name, Enrollment Flag", "Add invalid record assertion failed");
    }
    
    @Test(priority=8)
    public void AddRecordWithoutDNIS() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addRecordWithoutDNIS(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Please Provide DNIS", "Add invalid record assertion failed");
    }
    
    @Test(priority=9)
    public void AddRecordWithoutHotLineName() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addRecordWithoutHotLineName(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Please Provide Hotline Name", "Add invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutEnrollmentFlag() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.addRecordWithoutEnrollmentFlag(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Please Provide Enrollment Flag", "Add invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void VerifyCancelBtnAtAddRecord(){
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.clickOnAddRecord();
        VbEnrollmentFlagPage.clickOnCancelBtn();
        Assert.assertFalse(VbEnrollmentFlagPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
	
	@Test(priority=12)
	public void VerifySearchIsNotEqualTo() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifySearchIsNotEqualTo(VBEnrollmentFlagDetails.getHotLineName()));
	}
	
	@Test(priority=13)
	public void VerifySearchContains() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifySearchContains(VBEnrollmentFlagDetails.getHotLineName()));
	}
	
	@Test(priority=14)
	public void VerifySearchDoesNotContains() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifySearchDoesNotContains(VBEnrollmentFlagDetails.getHotLineName()));
	}
	
	@Test(priority=15)
	public void VerifySearchStartsWith() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifySearchStartsWith(VBEnrollmentFlagDetails.getHotLineName()));
	}
	
	@Test(priority=16)
	public void VerifySearchEndsWith() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifySearchEndsWith(VBEnrollmentFlagDetails.getHotLineName()));
	}
	
    @Test(priority=17)
    public void EditVBEnrollmentFlagRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.editVBEnrollmentFlagRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
       
    @Test(priority=18,dependsOnMethods = "EditVBEnrollmentFlagRecord")
    public void EditWithoutModifyReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.editVBEnrollmentFlagRecordWithoutModifyReason(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=19,dependsOnMethods = "EditWithoutModifyReasonRecord")
    public void VerifyCancelBtnAtEditRecord() throws Exception{
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.searchVbEnrollmentFlagRecord("40000");
        Thread.sleep(1000);
        VbEnrollmentFlagPage.clickOnEditButton();
        VbEnrollmentFlagPage.clickOnCancelBtn();
        Assert.assertFalse(VbEnrollmentFlagPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=20)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertFalse(VbEnrollmentFlagPage .clearAll(VBEnrollmentFlagDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("VBEnrollmentFlagTest", "clearall");
        Assert.assertTrue(VbEnrollmentFlagPage.verifyclose());
    }
    
    @Test(priority=21)
    public void searchwithoutSearchTextbox() throws IOException {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.searchwithoutextsearch();
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=22)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=23)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VB Enrollment Flag.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=24)
    public void SortingByAscending() throws IOException {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VB Enrollment Flag (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(VbEnrollmentFlagPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=25)
    public void SortingByDescending() throws IOException {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\VB Enrollment Flag (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(VbEnrollmentFlagPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=26)
    public void GroupBy()
    {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.groupby());
        screenshot.captureScreen("VBEnrollmentFlagTest", "GroupBy");
    	Assert.assertTrue(VbEnrollmentFlagPage.groupby());
        screenshot.captureScreen("VBEnrollmentFlagTest", "AlreadyGroupBy");
    }
    
    @Test(priority=27)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=28)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=29)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyNumberOfItemsPerPageSelection() {
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    
    @Test(priority=31)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
    	Assert.assertTrue(VbEnrollmentFlagPage.verifyDatabase(VBEnrollmentFlagDetails.getQuery()));
    }
    
    @Test(priority=32)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.deleteVBEnrollmentFlagWithoutDeleteReasonRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifyErrorMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyCancelBtnAtDeleteVBEnrollmentFlagRecord() throws Exception{
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.searchVbEnrollmentFlagRecord("40000");
        Thread.sleep(1000);
        VbEnrollmentFlagPage.clickOnDeleteButton();
        VbEnrollmentFlagPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(VbEnrollmentFlagPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=34)//,dependsOnMethods = "VerifyCancelBtnAtDeleteVBEnrollmentFlagRecord")
    public void DeleteVBEnrollmentFlagRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        VbEnrollmentFlagPage.deleteVBEnrollmentFlagRecord(VBEnrollmentFlagDetails);
        Assert.assertEquals(VbEnrollmentFlagPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    
    
    @Test(priority=35)
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
    
    @Test(priority=36)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\VBEnrollmentFlagData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        VBEnrollmentFlagDetails VBEnrollmentFlagDetails = new VBEnrollmentFlagDetails(map);
        VbEnrollmentFlagPage VbEnrollmentFlagPage = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
        Assert.assertTrue(VbEnrollmentFlagPage.ExporttoExcelWithoutData(VBEnrollmentFlagDetails));
    }
    
    @Test(priority=37)//,dependsOnMethods ="AddNewVBEnrollmentFlagRecord",enabled=true)
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
    
    @Test(priority=38)//,dependsOnMethods="EditVBEnrollmentFlagRecord",enabled=true)
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
    
    @Test(priority=39)//,dependsOnMethods= {"DeleteVBEnrollmentFlagRecord"},enabled=true)
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
    
  
	 @AfterMethod
	    public void afterEachMethod(Method method) throws InterruptedException {
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("VBEnrollmentFlagTest",method.getName());
		        driver.navigate().refresh();
		}
}
