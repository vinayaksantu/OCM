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

import com.tetherfi.model.ivr.FaxApplicationFormDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxApplicationFormPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxApplicationFormTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToFaxApplicationFormPage() throws Exception {
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
        ivrPage.navigateToFaxApplicationFormPage();
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.isFaxApplicationFormPageDisplayed(), "Fax application form page assertion failed");
    	screenshot.captureScreen(driver,"FaxApplicationForm Page","FaxApplicationFormTest");
    }
    
    @Test(priority=1)
    public void FaxApplicationFormPage() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
    	Assert.assertTrue(faxApplicationFormPage.verifylogo(),"CallBackAnnouncement logo assertion failed");
    	Assert.assertTrue(faxApplicationFormPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxApplicationFormTest");
    	Assert.assertTrue(faxApplicationFormPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxApplicationFormTest");
    }
    
    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertFalse(faxApplicationFormPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddNewFaxApplicationFormRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.addnewCancel(faxApplicationFormDetails), "Add new record cancel assertion failed");
        screenshot.captureScreen("FaxApplicationFormTest", "Cancel Record");
        faxApplicationFormPage.addNewFaxApplicationFormRecord(faxApplicationFormDetails);
        Assert.assertEquals(faxApplicationFormPage.verifySuccessMessage(),"Record Created Successfully", "Add New record assertion failed");

    }
    
    @Test(priority=6,dependsOnMethods ="AddNewFaxApplicationFormRecord")
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxApplicationFormCreate(faxApplicationFormDetails,"Create"));
    }
   
    @Test(priority=7,dependsOnMethods = "AddNewFaxApplicationFormRecord")
    public void AddDuplicateFaxApplicationFormRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.addNewFaxApplicationFormRecord(faxApplicationFormDetails);
        Assert.assertEquals(faxApplicationFormPage.verifySuccessMessage(),"Record Creation Failed, Already Exist", "Duplicate record assertion failed");
    }
    
    @Test(priority=8)
    public void AddNewFaxApplicationFormWithoutFunctionality() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.addNewFaxApplicationFormWithoutFunctionlaity(faxApplicationFormDetails);
        Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage(), "Add New record assertion failed");
    }
    
    @Test(priority=9)
    public void AddNewFaxApplicationFormWithoutLanguage() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.addNewFaxApplicationFormWithoutLanguage(faxApplicationFormDetails);
        Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage(), "Add New record assertion failed");
    }
    
    @Test(priority=10)
    public void AddNewFaxApplicationFormWithoutPDFFile() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.addNewFaxApplicationFormWithoutPDFFile(faxApplicationFormDetails);
        Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage(), "Add New record assertion failed");
    }
    
    @Test(priority=11)
    public void AddNewFaxApplicationFormWithoutWaveFile() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.addNewFaxApplicationFormWithoutWaveFile(faxApplicationFormDetails);
        Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage(), "Add New record assertion failed");
    }
    
    @Test(priority=12)
    public void AddNewFaxApplicationFormWithoutStatus() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.addNewFaxApplicationFormWithoutStatus(faxApplicationFormDetails);
        Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage(), "Add New record assertion failed");
    }
    
    @Test(priority=13)//,dependsOnMethods = "AddNewFaxApplicationFormRecord")
    public void EditFaxApplicationFormRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.editFaxApplicationFormRecord(faxApplicationFormDetails);
        Assert.assertEquals(faxApplicationFormPage.verifySuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(priority=14,dependsOnMethods="EditFaxApplicationFormRecord")
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxApplicationFormUpdate(faxApplicationFormDetails,"Update"));
    }
    
    @Test(priority=15)
    public void EditWithoutModifyReasonFaxApplicationFormRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.editwithoutModifyreasonRecord(faxApplicationFormDetails);
        Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage(), "Edit assertion failed");
    }
    
    @Test(priority=16)
    public void VerifyCancelButtonAtEditFaxApplicationFormRecord() throws Exception {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        Assert.assertTrue(faxApplicationFormPage.editcancel(faxApplicationFormDetails ));
    }
    
    @Test(priority=17)
    public void searchPage() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertFalse(faxApplicationFormPage .clearAll(faxApplicationFormDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("FaxApplicationFormTest","clearall");
        Assert.assertTrue(faxApplicationFormPage .verifyclose());
    }
    
    @Test(priority=18)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.searchwithoutextsearch(faxApplicationFormDetails);
    	Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage());
    }
    
    @Test(priority=19)
    public void DeleteWithoutDeleteReasonRecord() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.deleteWithoutModifyReasonRecord(faxApplicationFormDetails);
        Assert.assertTrue(faxApplicationFormPage.verifyErrorMessage(), "Delete assertion failed");
    }
    
    @Test(priority=20,dependsOnMethods = {"DeleteWithoutDeleteReasonRecord"})
    public void VerifyCancelButtonDeleteRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
         FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
         FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
         Assert.assertTrue(faxApplicationFormPage.Canceldelete(faxApplicationFormDetails), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=21)
    public void DeleteFaxApplicationFormRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
         FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
         FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
         faxApplicationFormPage.deleteFaxApplicationFormRecord(faxApplicationFormDetails);
         Assert.assertEquals(faxApplicationFormPage.verifySuccessMessage(),"Record Deleted Successfully","delete record assertion failed");
    }
    
    @Test(priority=22,dependsOnMethods= {"DeleteFaxApplicationFormRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxApplicationFormdelete(faxApplicationFormDetails,"Delete"));
    }
    
    @Test(priority=23)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=24)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Application Form.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
    	Assert.assertTrue(faxApplicationFormPage.verifyexportToExcelSheet(maplist));	
    } 

    @Test(priority=24)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
    	Assert.assertTrue(faxApplicationFormPage.verifyinvalidsearchwithwrongdata(faxApplicationFormDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("FaxApplicationFormTest","Invalid Search with wrong data");
        Assert.assertTrue(faxApplicationFormPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=25)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
        Assert.assertTrue(faxApplicationFormPage.ExporttoExcelWithoutData(faxApplicationFormDetails));
    }
  
    @Test(priority=26)
    public void SortingByAscending() throws IOException {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Application Form (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxApplicationFormPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=27)
    public void SortingByDescending() throws IOException {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        faxApplicationFormPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Application Form (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxApplicationFormPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void GroupBy()
    {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
    	Assert.assertTrue(faxApplicationFormPage.groupby());
        screenshot.captureScreen("FaxApplicationFormTest","GroupBy");
    	Assert.assertTrue(faxApplicationFormPage.groupby());
        screenshot.captureScreen("FaxApplicationFormTest", "AlreadyGroupBy");
    }
    
    @Test(priority=29)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
    	Assert.assertTrue(faxApplicationFormPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        Assert.assertTrue(faxApplicationFormPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=33)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxApplicationFormData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        FaxApplicationFormPage faxApplicationFormPage = PageFactory.createPageInstance(driver, FaxApplicationFormPage.class);
        FaxApplicationFormDetails faxApplicationFormDetails = new FaxApplicationFormDetails(map);
    	Assert.assertTrue(faxApplicationFormPage.verifyDatabase(faxApplicationFormDetails.getQuery()));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("FaxApplicationFormTest",method.getName());
        driver.navigate().refresh();
    }
}
