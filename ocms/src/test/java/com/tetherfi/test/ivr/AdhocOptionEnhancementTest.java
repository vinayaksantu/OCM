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
    public void NavigateToAdhocOptionEnhancementPage() {
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
    	screenshot.captureScreen(driver,"Adhoc Enhancement Page","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=1)
    public void AdhocOptionEnhancementPage() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifylogo(),"AdhocOptionEnhancement logo assertion failed");
    	Assert.assertTrue(adhocOptionEnhancementPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","AdhocOptionEnhancementTest");
    	Assert.assertTrue(adhocOptionEnhancementPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","AdhocOptionEnhancementTest");
    }
    @Test(priority=2)
    public void AddNewAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.addnewAdhocOptionEnhancementCancel(adhocOptionEnhancementDetails), "Add new record cancel assertion failed");
        screenshot.captureScreen(driver, "Cancel Record","AdhocOptionEnhancementTest");
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
        screenshot.captureScreen(driver,"Record Creation","AdhocOptionEnhancementTest");

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
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","AdhocOptionEnhancementTest");    
    }
   
    @Test(dependsOnMethods = "AddNewAdhocOptionEnhancementRecord",priority=4)
    public void AddDuplicateAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Creation Failed, Already Exist", "Duplicate record assertion failed");
    	screenshot.captureScreen(driver,"Duplicate Record","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods = "AddDuplicateAdhocOptionEnhancementRecord",priority=5)
    public void AddNewAdhocOptionEnhancementwithdifferentLangvageRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");
        screenshot.captureScreen(driver,"Record Creation with different language","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=6)
    public void AddEmptyAdhocOptionEnhancementRecord() throws IOException, Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails = new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.addNewAdhocOptionEnhancementInvalidRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Please Provide Promotion Number, Promotion Description, Language, Direct Transfer Enabled, Intent, Status", "Empty record assertion failed");
    	screenshot.captureScreen(driver,"addNewAdhocOptionEnhancementInvalidRecord","AdhocOptionEnhancementTest");
        adhocOptionEnhancementPage.addNewRecordwithoutPromotionNumber(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver,"addNewRecordwithoutPromotionNumber","AdhocOptionEnhancementTest");
    	adhocOptionEnhancementPage.addNewRecordwithoutPromotiondescription(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver,"addNewRecordwithoutPromotiondescription","AdhocOptionEnhancementTest");
    	adhocOptionEnhancementPage.addNewRecordwithoutLanguage(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver,"addNewRecordwithoutLanguage","AdhocOptionEnhancementTest");
    	adhocOptionEnhancementPage.addNewRecordwithoutDirectTransferEnabled(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver,"addNewRecordwithoutDirectTransferEnabled","AdhocOptionEnhancementTest");
    	adhocOptionEnhancementPage.addNewRecordwithoutpromotionnamewavfile(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver,"addNewRecordwithoutpromotionnamewavfile","AdhocOptionEnhancementTest");
    	adhocOptionEnhancementPage.addNewRecordwithoutpromotiondetailswavfile(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver,"addNewRecordwithoutpromotiondetailswavfile","AdhocOptionEnhancementTest");
        adhocOptionEnhancementPage.addNewRecordwithoutIntent(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver,"addNewRecordwithoutIntent","AdhocOptionEnhancementTest");
        adhocOptionEnhancementPage.addNewRecordwithoutStatus(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());   
    	screenshot.captureScreen(driver,"aaddNewRecordwithoutStatus","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods = "AddEmptyAdhocOptionEnhancementRecord",priority=7)
    public void VerifyCancelButtonAtAddAdhocOptionEnhancementRecord() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.clickonAddNewRecord();
        adhocOptionEnhancementPage.clickOnCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyEditFormContainer(),"Cancel Btn at Add record assertion failed");
    	screenshot.captureScreen(driver,"addcancelbtn","AdhocOptionEnhancementTest");
}
    
    @Test(dependsOnMethods = "AddDuplicateAdhocOptionEnhancementRecord",priority=8)
    public void EditAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    	screenshot.captureScreen(driver,"Record Updated","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods="EditAdhocOptionEnhancementRecord",priority=9)
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
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForUpdate","AdhocOptionEnhancementTest");
    }
    @Test(dependsOnMethods = "EditAdhocOptionEnhancementRecord",priority=10)
    public void EditEmptyAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Please Provide Promotion Description, Intent","Edit empty record assertion failed");
        screenshot.captureScreen(driver,"EditEmptyAdhocOptionEnhancementRecord","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods = "EditEmptyAdhocOptionEnhancementRecord",priority=11)
    public void EditWithoutModifyReasonAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.editAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Please enter the modify reason","Edit without modify reason record assertion failed");
        screenshot.captureScreen(driver,"EditWithoutModifyReasonAdhocOptionEnhancementRecord","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods = "EditWithoutModifyReasonAdhocOptionEnhancementRecord",priority=12)
    public void VerifyCancelButtonAtEditAdhocOptionEnhancementRecord() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.searchAdhocOptionEnhancementRecord("Promotion Number","4");
        adhocOptionEnhancementPage.clickOnEditButton();
        adhocOptionEnhancementPage.clickOnCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
        screenshot.captureScreen(driver,"VerifyCancelButtonAtEditAdhocOptionEnhancementRecord","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods = {"VerifyCancelButtonAtEditAdhocOptionEnhancementRecord"},priority=13)
    public void DeleteWithoutDeleteReasonInAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Please enter the delete reason","delete record assertion failed");
        screenshot.captureScreen(driver,"DeleteWithoutDeleteReasonInAdhocOptionEnhancementRecord","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods = {"DeleteWithoutDeleteReasonInAdhocOptionEnhancementRecord"},priority=14)
    public void VerifyCancelButtonInDeleteAdhocOptionEnhancementRecord() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.searchAdhocOptionEnhancementRecord("Promotion Number","4");
        adhocOptionEnhancementPage.clickOnDeleteButton();
        adhocOptionEnhancementPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(adhocOptionEnhancementPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
        screenshot.captureScreen(driver,"VerifyCancelButtonInDeleteAdhocOptionEnhancementRecord","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods = {"VerifyCancelButtonInDeleteAdhocOptionEnhancementRecord"},priority=15)
    public void DeleteAdhocOptionEnhancementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage= PageFactory.createPageInstance(driver,AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
        Assert.assertEquals(adhocOptionEnhancementPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
        screenshot.captureScreen(driver,"DeleteAdhocOptionEnhancementRecord()","AdhocOptionEnhancementTest");
    }
    
    @Test(dependsOnMethods= {"DeleteAdhocOptionEnhancementRecord"},priority=16)
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
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForUpdate","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=18)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=19)
    public void searchPage() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertFalse(adhocOptionEnhancementPage.clearAll(adhocOptionEnhancementDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","AdhocOptionEnhancementTest");
        Assert.assertTrue(adhocOptionEnhancementPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","AdhocOptionEnhancementTest");
    }
    @Test(priority=20)
    public void searchwithoutSearchTextbox() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	adhocOptionEnhancementPage.searchwithoutextsearch(adhocOptionEnhancementDetails);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=21)
    public void ValidANDBooleanSearch() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        Assert.assertTrue(adhocOptionEnhancementPage.validAndBooleanSearch(adhocOptionEnhancementDetails));
    	screenshot.captureScreen(driver, "ValidANDBooleanSearch","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=22)
    public void ValiORBooleanSearch() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        Assert.assertTrue(adhocOptionEnhancementPage.validORBooleanSearch(adhocOptionEnhancementDetails));
    	screenshot.captureScreen(driver, "ValiORBooleanSearch()","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=23)
    public void InvalidBooleanSearchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        adhocOptionEnhancementPage.InvalidBooleanSearchwithoutSearchTextbox(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage());
    	screenshot.captureScreen(driver, "InvalidBooleanSearchwithoutSearchTextbox()","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=24)
    public void InvalidBooleanSearchwithoutSearchTextbox1() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        adhocOptionEnhancementPage.InvalidBooleanSearchwithoutSearchTextbox1(adhocOptionEnhancementDetails);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyErrorMessage()); 
    	screenshot.captureScreen(driver, "InvalidBooleanSearchwithoutSearchTextbox1","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=25)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	adhocOptionEnhancementPage.deleteAdhocOptionEnhancementRecord(adhocOptionEnhancementDetails);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyinvalidsearchwithwrongdata(adhocOptionEnhancementDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "AdhocOptionEnhancementTest");
        Assert.assertTrue(adhocOptionEnhancementPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "AdhocOptionEnhancementTest");
    }
    
    @Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
        Assert.assertTrue(adhocOptionEnhancementPage.ExporttoExcelWithoutData(adhocOptionEnhancementDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "AdhocOptionEnhancementTest");
    }
  
    @Test(priority=27)
    public void SortingByAscending() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(adhocOptionEnhancementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void SortingByDescending() throws IOException {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        adhocOptionEnhancementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Adhoc Option Enhancement (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(adhocOptionEnhancementPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=29)
    public void GroupBy()
    {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","AdhocOptionEnhancementTest");
    	Assert.assertTrue(adhocOptionEnhancementPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","AdhocOptionEnhancementTest");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyDropdownForAllTheColumns() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyColumnsHeaderEnable() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertTrue(adhocOptionEnhancementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=36)
    public void VerifyColumnsHeaderDisable() {
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        Assert.assertFalse(adhocOptionEnhancementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=37)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AdhocOptionEnhancementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        AdhocOptionEnhancementPage adhocOptionEnhancementPage = PageFactory.createPageInstance(driver, AdhocOptionEnhancementPage.class);
        AdhocOptionEnhancementDetails adhocOptionEnhancementDetails=new AdhocOptionEnhancementDetails(map);
    	Assert.assertTrue(adhocOptionEnhancementPage.verifyDatabase(adhocOptionEnhancementDetails.getQuery()));
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws InterruptedException {
   	 if(ITestResult.FAILURE==result.getStatus()){
      		 try{
      			 screenshot.captureScreen(driver,method.getName(),"AdhocOptionEnhancementTest");
      		 }
      		catch (Exception e){
      		 System.out.println("Exception while taking screenshot "+e.getMessage());
      		 } 
      		 driver.navigate().refresh();
      		 }    }
}
