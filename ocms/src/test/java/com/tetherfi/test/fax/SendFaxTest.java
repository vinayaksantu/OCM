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
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxRoutingConfigurationPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SendFaxPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SendFaxTest extends BaseTest{
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
        faxPage.navigateToSendFaxPage();
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.isSendFaxPageDisplayed(), "FAX page assertion failed");
    }
    
    @Test(priority=1)
    public void SendFaxPage() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(sendFaxPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","SendFaxTest");
    	Assert.assertTrue(sendFaxPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","SendFaxTest");
    }
    
    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyColumnsHeaderEnable() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertFalse(sendFaxPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    //@Test(priority=5)
    public void AddSendFaxRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.AddCancelRecord(sendFaxDetails));
        sendFaxPage.addNewFaxRoutingConfigRecord(sendFaxDetails);
        Assert.assertEquals(sendFaxPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    //@Test(dependsOnMethods = {"AddSendFaxRecord"},priority=6)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySendFaxCreate(sendFaxDetails,"Create"));
    	screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","SendFaxTest");
    	}
    
    //@Test(dependsOnMethods = {"AddSendFaxRecord"},priority=7)
    public void AddDuplicateRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addSendFaxRecord(sendFaxDetails);
        Assert.assertFalse(sendFaxPage.getErrorMsg());
    	screenshot.captureScreen(driver,"Duplicate Record","FaxRoutingConfigTest");
    }
    
    //@Test(priority=8)
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
    
    
    @Test(priority=9)
    public void searchPage() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertFalse(sendFaxPage.clearAll(sendFaxDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","SendFaxTest");
        Assert.assertTrue(sendFaxPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","SendFaxTest");
    }
    
    @Test(priority=10)
    public void searchwithoutSearchTextbox() throws IOException {
    	SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.searchwithoutextsearch();
    	Assert.assertFalse(sendFaxPage.getErrorMsg());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","SendFaxTest");
    }
    
    //@Test(priority=11)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyDatabase(sendFaxDetails.getQuery()));
    }
    
    @Test(priority=12)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyinvalidsearchwithwrongdata(sendFaxDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "SendFaxTest");
        Assert.assertTrue(sendFaxPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "SendFaxTest");
    }
    
    //@Test(priority=13)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","SendFaxTest");
    }
    
    //@Test(priority=14)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Send Fax.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","SendFaxTest");
    }
    //@Test(priority=15)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.ExporttoExcelWithoutData(sendFaxDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "SendFaxTest");
    }
  
    //@Test(priority=16)
    public void SortingByAscending() throws IOException {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Send Fax (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(sendFaxPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=17)
    public void SortingByDescending() throws IOException {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Send Fax (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(sendFaxPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=18)
    public void GroupBy()
    {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","SendFaxTest");
    	Assert.assertTrue(sendFaxPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","SendFaxTest");
    }
    
    @Test(priority=19)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyArrowMoveForFirstAndLastPage() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=21)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=22)
    public void VerifyNumberOfItemsPerPageSelection() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
        screenshot.captureScreen(driver,method.getName(),"FaxLineConfigTest");
    }
    
    //@AfterClass
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
