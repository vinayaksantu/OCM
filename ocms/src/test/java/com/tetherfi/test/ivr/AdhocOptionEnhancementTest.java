package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.AdhocOptionEnhancementDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AdhocOptionEnhancementTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToAdhocOptionEnhancementPage() throws Exception {
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
        ivrPage.navigateToAdhocOptionEnhancementPage();
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.isAdhocOptionEnhancementPageDisplayed(), "adhoc option enhancement page assertion failed");
    }
    
   /* @Test(priority=1)
    public void AdhocOptionEnhancementPage() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifylogo(),"AdhocOptionEnhancement logo assertion failed");
    	Assert.assertTrue(adhocOptionEnhancementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("AdhocOptionEnhancementTest","maximize window");
    	Assert.assertTrue(adhocOptionEnhancementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("AdhocOptionEnhancementTest","minimize window");
    }
    @Test(priority=2)
    public void AddNewAdhocOptionEnhancementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");

    }
    @Test(dependsOnMethods ="AddNewAdhocOptionEnhancementRecord", priority=3)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAdhocOptionEnhancementCreate(adhocOptionEnhancementDetails,"Create"));
    }
   
    @Test(dependsOnMethods = "AddNewAdhocOptionEnhancementRecord",priority=4)
    public void AddDuplicateAdhocOptionEnhancementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Creation Failed, Already Exist", "Duplicate record assertion failed");
    }
    
    @Test(dependsOnMethods = "AddDuplicateAdhocOptionEnhancementRecord",priority=5)
    public void AddNewAdhocOptionEnhancementwithdifferentLangvageRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
    }
    
    @Test(priority=6)
    public void AddEmptyAdhocOptionEnhancementRecord() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementInvalidRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Promotion Number, Promotion Description, Language, Direct Transfer Enabled, Intent, Status", "Empty record assertion failed");
    }
    @Test(priority=7)
    public void AddAdhocOptionEnhancementRecordWithoutPromotionalNumber() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewRecordwithoutPromotionNumber(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Promotion Number");
    }
    
    @Test(priority=8)
    public void AddAdhocOptionEnhancementRecordWithoutPromotionalDescription() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
     	adhocOptionEnhancementPage.addNewRecordwithoutPromotiondescription(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Promotion Description");
    }  
    
    @Test(priority=9)
    public void AddAdhocOptionEnhancementRecordWithoutLanguage() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewRecordwithoutLanguage(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Language");
    }
    @Test(priority=10)
    public void AddAdhocOptionEnhancementRecordWithoutDirectTransfer() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewRecordwithoutDirectTransferEnabled(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Direct Transfer Enabled");
    }
    @Test(priority=11)
    public void AddAdhocOptionEnhancementRecordWithoutPromotionNameWavfile() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	adhocOptionEnhancementPage.addNewRecordwithoutpromotionnamewavfile(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Promotion Name WaveFile");
    }
    @Test(priority=12)
    public void AddAdhocOptionEnhancementRecordWithoutPromotionDetailsWavfile() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);       
    	adhocOptionEnhancementPage.addNewRecordwithoutpromotiondetailswavfile(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Promotional Details Wavefile");
    }
    @Test(priority=13)
    public void AddEmptyAdhocOptionEnhancementRecordWithoutIntent() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);       
    	adhocOptionEnhancementPage.addNewRecordwithoutIntent(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Intent");
    }
    @Test(priority=14)
    public void AddAdhocOptionEnhancementRecordWithoutStatus() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);         	
        adhocOptionEnhancementPage.addNewRecordwithoutStatus(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please Provide Status");   
    }
    
    @Test(priority=15)
    public void VerifyCancelButtonAtAddAdhocOptionEnhancementRecord() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.clickonAddNewRecord();
        adhocOptionEnhancementPage.clickOnCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyEditFormContainer(),"Cancel Btn at Add record assertion failed");
    }*/
    @Test(priority=16)
    public void VerifySearchIsNotEqualTo()  throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifySearchIsNotEqualTo(adhocOptionEnhancementDetails.getPromotionalDescription()));
    }
    
    @Test(priority=17)
    public void VerifySearchContains() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifySearchContains(adhocOptionEnhancementDetails.getPromotionalDescription()));
    }
    
    @Test(priority=18)
    public void  VerifySearchDoesNotContains() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifySearchDoesNotContains(adhocOptionEnhancementDetails.getPromotionalDescription()));
    }
    
    @Test(priority=19)
    public void  VerifySearchStartsWith() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifySearchStartsWith(adhocOptionEnhancementDetails.getPromotionalDescription()));
    }
    
    @Test(priority=20)
    public void  VerifySearchEndsWith() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifySearchEndsWith(adhocOptionEnhancementDetails.getPromotionalDescription()));
    }
    /*@Test(priority=16)
    public void EditAdhocOptionEnhancementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(dependsOnMethods="EditAdhocOptionEnhancementRecord",priority=17)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAdhocOptionEnhancementUpdate(adhocOptionEnhancementDetails,"Update"));
    }
    @Test(priority=18)
    public void EditEmptyAdhocOptionEnhancementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Please Provide Promotion Description, Intent","Edit empty record assertion failed");
    }
    
    @Test(priority=19)
    public void EditWithoutModifyReasonAdhocOptionEnhancementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editRecordWithoutModifyReason(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Please enter the modify reason","Edit without modify reason record assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyCancelButtonAtEditAdhocOptionEnhancementRecord() throws IOException, Exception {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.searchAdhocOptionEnhancementRecord("Promotion Number","4");
        Thread.sleep(2000);
        adhocOptionEnhancementPage.clickOnEditButton();
        adhocOptionEnhancementPage.clickOnCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=21)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=22)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=23)
    public void SortingByAscending() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(adhocOptionEnhancementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=24)
    public void SortingByDescending() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(adhocOptionEnhancementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=25)
    public void GroupBy()
    {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.groupby());
        screenshot.captureScreen("AdhocOptionEnhancementTest", "GroupBy");
    	Assert.assertTrue(adhocOptionEnhancementPage.groupby());
        screenshot.captureScreen("AdhocOptionEnhancementTest","AlreadyGroupBy");
    }
    
    @Test(priority=26)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=27)
    public void VerifyArrowMoveForFirstAndLastPage() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=28)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=29)
    public void VerifyNumberOfItemsPerPageSelection() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyDropdownForAllTheColumns() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyColumnsHeaderEnable() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyColumnsHeaderDisable() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertFalse(adhocOptionEnhancementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=33)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyDatabase(adhocOptionEnhancementDetails.getQuery()));
    }
    
    @Test(priority=34)
    public void DeleteWithoutDeleteReasonInAdhocOptionEnhancementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Please enter the delete reason","delete record assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyCancelButtonInDeleteAdhocOptionEnhancementRecord() throws Exception {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.searchAdhocOptionEnhancementRecord("Promotion Number","4");
        Thread.sleep(2000);
        adhocOptionEnhancementPage.clickOnDeleteButton();
        adhocOptionEnhancementPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=36)//,dependsOnMethods= {"EditAdhocOptionEnhancementRecord"})
    public void DeleteAdhocOptionEnhancementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(dependsOnMethods= {"DeleteAdhocOptionEnhancementRecord"},priority=37)
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAdhocOptionEnhancementdelete(adhocOptionEnhancementDetails,"Delete"));
    }
     
    @Test(priority=38)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertFalse(adhocOptionEnhancementPage.clearAll(adhocOptionEnhancementDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("AdhocOptionEnhancementTest","clearall");
        Assert.assertTrue(adhocOptionEnhancementPage.verifyclose());
    }
    @Test(priority=39)
    public void searchwithoutSearchTextbox() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	adhocOptionEnhancementPage.searchwithoutextsearch(adhocOptionEnhancementDetails);
    	Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please enter the text to search or remove the filter");
    }
    
    @Test(priority=40)
    public void ValidANDBooleanSearch() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        Assert.assertTrue(adhocOptionEnhancementPage.validAndBooleanSearch(adhocOptionEnhancementDetails));
    }
    
    @Test(priority=41)
    public void ValiORBooleanSearch() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        Assert.assertTrue(adhocOptionEnhancementPage.validORBooleanSearch(adhocOptionEnhancementDetails));
    }
    
    @Test(priority=42)
    public void InvalidBooleanSearchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        adhocOptionEnhancementPage.InvalidBooleanSearchwithoutSearchTextbox(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please enter the text to search or remove the filter");
    }
    
    @Test(priority=43)
    public void InvalidBooleanSearchwithoutSearchTextbox1() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        adhocOptionEnhancementPage.InvalidBooleanSearchwithoutSearchTextbox1(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifyErrorMessage(),"Please enter the text to search or remove the filter"); 
    }
    
    @Test(priority=44)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyinvalidsearchwithwrongdata(adhocOptionEnhancementDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("AdhocOptionEnhancementTest","Invalid Search");
        Assert.assertTrue(adhocOptionEnhancementPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=45)
    public void ExporttoExcelWithoutData() throws Exception
    {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        Assert.assertTrue(adhocOptionEnhancementPage.ExporttoExcelWithoutData(adhocOptionEnhancementDetails));
    } */  
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("AdhocOptionEnhancementTest",method.getName());
	        driver.navigate().refresh();
	}
}
