package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxAutoACKConfigurationDetails;
import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxAutoACKConfigurationPage;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SendFaxPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxAutoACKConfigurationTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

	@BeforeClass
    public void AddFaxLineConfigRecord() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxLineConfigPage();
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.isFaxLineConfigPageDisplayed(), "FAX page assertion failed");
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Created Successfully");
    }
    @BeforeMethod
    public void NavigateToFaxAutoACKConfigurationPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxAutoACKConfigurationPage();
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.isFaxAutoACKConfigurationPageDisplayed(), "FAX page assertion failed");
    }

    @Test(priority=1)
    public void FaxAutoACKConfigurationPage() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(faxAutoAckConfigurationPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxAutoACKConfigurationTest");
    	Assert.assertTrue(faxAutoAckConfigurationPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxAutoACKConfigurationTest");
    }
    
    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertFalse(faxAutoAckConfigurationPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddCancelAutoAckConfigRecord() throws Exception {
    	FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.addcancel(), "Cancel assertion failed");
    }
    
    @Test(priority=6)
    public void AddNewAutoAckConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addNewFaxAutoAckConfigRecord(faxAutoAckConfigurationDetails);
        Assert.assertEquals(faxAutoAckConfigurationPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(priority=7,dependsOnMethods = {"AddNewAutoAckConfigRecord"})
    public void VerifyAuditTrialReportForCreate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxAutoACKConfigCreate(faxAutoAckConfigurationDetails,"Create"));
    	screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","FaxAutoACKConfigTest");
    	}
    
    @Test(priority=8)
    public void DuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addNewFaxAutoAckConfigRecord(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=9)
    public void AddRecordWithoutFaxLine() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addRecordWithoutFaxLine(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=10)
    public void AddRecordWithoutSenderType() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addRecordWithoutSenderType(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=11)
    public void AddRecordWithoutName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addRecordWithoutName(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=12)
    public void AddRecordWithoutStatus() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addRecordWithoutStatus(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=13)
    public void AddRecordWithoutTemplate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addRecordWithoutTemplate(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=14)
    public void GroupBy()
    {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","FaxAutoACKConfigTest");
    	Assert.assertTrue(faxAutoAckConfigurationPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","FaxAutoACKConfigTest");
    }
    @Test(priority=15)
    public void EditCancelFaxAutoACKConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
    	FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.Editcancel(faxAutoAckConfigurationDetails), "Cancel assertion failed");
    }
    
    @Test(priority=16)
    public void EditFaxAutoAckConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.editFaxAutoAckConfigRecord(faxAutoAckConfigurationDetails);
        Assert.assertEquals(faxAutoAckConfigurationPage.getSuccessMessage(), "Record Updated Successfully");
    }
    
    @Test(priority=17,dependsOnMethods = {"EditFaxAutoAckConfigRecord"})
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxAutoACKConfigUpdate(faxAutoAckConfigurationDetails,"Update"));
    	screenshot.captureScreen(driver,"VerifyAuditTrialReportForUpdate","FaxAutoACKConfigTest");
    }
    
    @Test(priority=18)
    public void EditInvalidRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.EditInvalidRecord(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","FaxAutoACKConfigTest");
    }
    
    @Test(priority=20)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Auto ACK Configuration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","FaxLineConfigTest");
    }
    
    
    @Test(priority=21)
    public void AddDuplicateFaxlineDifferentSenderType() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.addNewFaxAutoAckConfigRecord(faxAutoAckConfigurationDetails);
        Assert.assertEquals(faxAutoAckConfigurationPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(priority=22)
    public void searchPage() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertFalse(faxAutoAckConfigurationPage.clearAll(faxAutoAckConfigurationDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","FaxAutoAckConfigTest");
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","FaxAutoAckConfigTest");
    }
    
    @Test(priority=23)
    public void searchwithoutSearchTextbox() throws IOException {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.searchwithoutextsearch();
    	Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","FaxAutoAckConfigTest");
    }
    
    @Test(priority=24)
    public void DeleteCancelFaxAutoAckConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
    	FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.Deletecancel(faxAutoAckConfigurationDetails), "Cancel assertion failed");
    }
    
    @Test(priority=25)
    public void DeleteFaxAutoAckConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.deleteFaxAutoAckConfigRecord(faxAutoAckConfigurationDetails);
        Assert.assertEquals(faxAutoAckConfigurationPage.getSuccessMessage(), "Record Deleted Successfully");
    }
    
    @Test(priority=26,dependsOnMethods = {"DeleteFaxAutoAckConfigRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxAutoACKConfigDelete(faxAutoAckConfigurationDetails,"Delete"));
    	screenshot.captureScreen(driver,"VerifyAuditTrialReportForDelete","FaxAutoACKConfigTest");
    }
    
    @Test(priority=27)
    public void DeleteInvalidRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.DeleteInvalidRecord(faxAutoAckConfigurationDetails);
        Assert.assertFalse(faxAutoAckConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=28)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails= new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyDatabase(faxAutoAckConfigurationDetails.getQuery()));
    }
       
    @Test(priority=29)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(1);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails = new FaxAutoACKConfigurationDetails(map);
        faxAutoAckConfigurationPage.deleteFaxAutoAckConfigRecord(faxAutoAckConfigurationDetails);
        Assert.assertTrue(faxAutoAckConfigurationPage.ExporttoExcelWithoutData(faxAutoAckConfigurationDetails));
        screenshot.captureScreen(driver,"ExporttoExcelWithoutData", "FaxLineConfigTest");
    }
    
    @Test(priority=30)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAutoACKConfigurationData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxAutoACKConfigurationDetails faxAutoAckConfigurationDetails = new FaxAutoACKConfigurationDetails(map);
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyinvalidsearchwithwrongdata(faxAutoAckConfigurationDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "FaxAutoACKConfigTest");
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "FaxAutoACKConfigTest");
    }
  
    @Test(priority=31)
    public void SortingByAscending() throws IOException {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Auto ACK Configuration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=32)
    public void SortingByDescending() throws IOException {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        faxAutoAckConfigurationPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Auto ACK Configuration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyexportToExcelSheet(maplist));
    }
   
    @Test(priority=33)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
    	Assert.assertTrue(faxAutoAckConfigurationPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=36)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxAutoACKConfigurationPage faxAutoAckConfigurationPage = PageFactory.createPageInstance(driver, FaxAutoACKConfigurationPage.class);
        Assert.assertTrue(faxAutoAckConfigurationPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
        screenshot.captureScreen(driver,method.getName(),"FaxAutoAckConfigTest");
    }
    
    @AfterClass
    public void DeleteFaxLineConfigRecord() throws IOException {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxLineConfigPage();
        FaxLineConfigPage faxLineConfigPage = PageFactory.createPageInstance(driver, FaxLineConfigPage.class);
        Assert.assertTrue(faxLineConfigPage.isFaxLineConfigPageDisplayed(), "FAX page assertion failed");
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxLineConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        faxLineConfigPage.deleteFaxLineConfig(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Deleted Successfully");
    }
    
}
