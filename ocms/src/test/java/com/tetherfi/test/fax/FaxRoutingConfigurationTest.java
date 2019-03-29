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

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxRoutingConfigurationDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxRoutingConfigurationPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxRoutingConfigurationTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	//@BeforeClass
    public void AddFaxLineConfigRecord() throws IOException {
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
    public void NavigateToFaxRoutingConfigPage() throws Exception {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("FAX");
        FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
        Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
        faxPage.navigateToFaxRoutingConfigurationPage();
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.isFaxRoutingConfigurationPageDisplayed(), "FAX page assertion failed");
    }
    
    //@Test(priority=1)
    public void FaxRoutingConfigurationPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(faxRoutingConfigurationPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxRoutingConfigurationTest");
    	Assert.assertTrue(faxRoutingConfigurationPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxRoutingConfigurationTest");
    }
    
  //@Test(priority=34)
    public void VerifyDropdownForAllTheColumns() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    //@Test(priority=35)
    public void VerifyColumnsHeaderEnable() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    //@Test(priority=36)
    public void VerifyColumnsHeaderDisable() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertFalse(faxRoutingConfigurationPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    //@Test(priority=2)
    public void AddFaxRoutingConfigQueueRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.AddCancelRecord(faxRoutingConfigDetails));
        faxRoutingConfigurationPage.addNewFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    //@Test(priority=3)
    public void AddFaxRoutingConfigFolderRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.addNewFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    //@Test(dependsOnMethods = {"AddFaxRoutingConfigFolderRecord"},priority=4)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxRoutingConfigCreate(faxRoutingConfigDetails,"Create"));
    	screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","FaxRoutingConfigTest");
    	}
    
    //@Test
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.addNewFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    	screenshot.captureScreen(driver,"Duplicate Record","FaxRoutingConfigTest");
    }
    
    //@Test
    public void AddInvalidRecord() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
         FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
         faxRoutingConfigurationPage.addNewInvalidRecordWithoutFaxLine(faxRoutingConfigDetails);
         Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
     	 screenshot.captureScreen(driver,"addNewInvalidRecordWithoutFaxLine","FaxRoutingConfigTest");
     	 faxRoutingConfigurationPage.addNewInvalidRecordWithoutSenderType(faxRoutingConfigDetails);
         Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
     	 screenshot.captureScreen(driver,"addNewInvalidRecordWithoutSenderType","FaxRoutingConfigTest");
     	 faxRoutingConfigurationPage.addNewInvalidRecordWithoutRouteType(faxRoutingConfigDetails);
     	 Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    	 screenshot.captureScreen(driver,"addNewInvalidRecordWithoutRouteType","FaxRoutingConfigTest");
    	 faxRoutingConfigurationPage.addNewInvalidRecordWithoutRouteData(faxRoutingConfigDetails);
     	 Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    	 screenshot.captureScreen(driver,"addNewInvalidRecordWithoutRouteData","FaxRoutingConfigTest");
    }
    
    //(priority=4)//dependsOnMethods = {"AddFaxLineConfigRecord"},)
    public void EditFaxRoutingConfigCancelRecord() throws Exception {
   	 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.editcancel(faxRoutingConfigDetails));
    }
    
    //@Test(priority=3)
    public void EditInvalidFaxRoutingConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.editInvalidFaxRoutingConfig(faxRoutingConfigDetails);
        Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    }
    
    //@Test(dependsOnMethods = {"EditFaxRoutingConfigCancelRecord"},priority=5)
    public void EditFaxRoutingConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.editFaxRoutingConfig(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(),"Record Updated Successfully");
    }
    
    //@Test(dependsOnMethods="EditFaxRoutingConfigRecord",priority=6)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxRoutingConfigUpdate(faxRoutingConfigDetails,"Update"));
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForUpdate","FaxRoutingConfigTest");
    }
    
    //@Test(priority=19)
    public void searchPage() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertFalse(faxRoutingConfigurationPage.clearAll(faxRoutingConfigDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","FaxRoutingConfigTest");
        Assert.assertTrue(faxRoutingConfigurationPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","FaxRoutingConfigTest");
    }
    //@Test(priority=20)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.searchwithoutextsearch(faxRoutingConfigDetails);
    	Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","FaxRoutingConfigTest");
    }
    
    @Test(priority=11)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
    	Assert.assertTrue(faxRoutingConfigurationPage .verifyDatabase(faxRoutingConfigDetails.getQuery()));
    }
    //@Test//(dependsOnMethods = {"EditFaxLineConfigRecord"},priority=7)
    public void DeleteCancelFaxRoutingConfigRecord() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.deletecancelRecord(faxRoutingConfigDetails));
    }
    
    //@Test
    public void DeleteFaxRoutingConfigRecordWithoutReason() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.deleteRoutingConfigwithoutReason(faxRoutingConfigDetails);
    	Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());

    }
    
    //@Test//(dependsOnMethods = {"DeleteCancelFaxRoutingConfigRecord"},priority=8)
    public void DeleteFaxRoutingConfigRecord() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.deleteFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(),"Record Deleted Successfully");
    }
    
    //@Test(priority=9)
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxRoutingConfigdelete(faxRoutingConfigDetails,"Delete"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForUpdate","");
    }
    
    //@Test(priority=10)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyinvalidsearchwithwrongdata(faxRoutingConfigDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "FaxLineConfigTest");
        Assert.assertTrue(faxRoutingConfigurationPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "FaxLineConfigTest");
    }
    
    /*@Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","FaxRoutingConfigurationTest");
    }
    
    @Test(priority=18)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","FaxRoutingConfigurationTest");
    }
    //@Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigurationDetails = new FaxRoutingConfigurationDetails(map);
        Assert.assertTrue(faxRoutingConfigurationPage.ExporttoExcelWithoutData(faxRoutingConfigurationDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "FaxRoutingConfigurationTest");
    }
  
    @Test(priority=27)
    public void SortingByAscending() throws IOException {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void SortingByDescending() throws IOException {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=29)
    public void GroupBy()
    {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","FaxRoutingConfigurationTest");
    	Assert.assertTrue(faxRoutingConfigurationPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","FaxRoutingConfigurationTest");
    }
    
    //@Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    //@Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }*/
    
    @AfterMethod
    public void afterEachMethod(Method method) {
        screenshot.captureScreen(driver,method.getName(),"FaxLineConfigTest");
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
