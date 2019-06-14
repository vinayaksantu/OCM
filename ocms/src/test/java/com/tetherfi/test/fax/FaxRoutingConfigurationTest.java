package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxRoutingConfigurationDetails;
import com.tetherfi.model.fax.FaxSendersDetails;
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxRoutingConfigurationPage;
import com.tetherfi.pages.FaxSendersPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SendFaxPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxRoutingConfigurationTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
	FTPServer ftp=new FTPServer();
	String filepath="\\\\172.16.2.16\\d$\\Products\\WindowsServices\\TFax\\RouteData";
	String filepath1="\\\\\\\\172.16.2.16\\\\d$\\\\Products\\\\WindowsServices\\\\TFax\\\\RouteData\\20000\\Test";
	
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
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(4);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        faxLineConfigPage.addNewFaxLineConfigRecord(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(), "Record Created Successfully");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
    
    @Test(priority=1)
    public void FaxRoutingConfigurationPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(faxRoutingConfigurationPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("FaxRoutingConfigurationTest","maximize window");
    	Assert.assertTrue(faxRoutingConfigurationPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("FaxRoutingConfigurationTest","minimize window");
    }
    
    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertFalse(faxRoutingConfigurationPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddFaxRoutingConfigQueueRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.AddCancelRecord(faxRoutingConfigDetails));
        faxRoutingConfigurationPage.addNewFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(priority=6)
    public void AddFaxRoutingConfigFolderRecord() throws Exception {
    	ftp.DeleteFilesFromFolder(filepath);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.addNewFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(dependsOnMethods = {"AddFaxRoutingConfigFolderRecord"},priority=7)
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
    	}
    
    @Test(dependsOnMethods = {"AddFaxRoutingConfigFolderRecord"},priority=8)
    public void VerifyRouteFolder() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
         FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
         Assert.assertTrue((ftp.FolderExist(filepath+"\\"+faxRoutingConfigDetails.getFaxLine()+"\\"+faxRoutingConfigDetails.getRouteData())));
    }
    
    @Test(dependsOnMethods= {"VerifyRouteFolder"},priority=9)
    public void VerifyFaxInRouteFolder() throws Exception {
    	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
         homePage.navigateToOCMPage();
         OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
         Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
         ocmHomePage.navigateToTab("FAX");
         FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
         Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
         faxPage.navigateToFaxSendersPage();
         FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);         
         Assert.assertTrue(faxSendersPage.isFaxSendersPageDisplayed(), "FAX page assertion failed");  
         String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(4);
         FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
         faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
         Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
         homePage.navigateToOCMPage();
         ocmHomePage.navigateToTab("FAX");
         faxPage.navigateToSendFaxPage();
         Thread.sleep(2000);
         SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
         String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
         Map<String, String> map1 = new ExcelReader(filePath1, "Create").getTestData().get(3);
         SendFaxDetails sendFaxDetails = new SendFaxDetails(map1);
         sendFaxPage.addNewSendFaxRecord(sendFaxDetails);
         Thread.sleep(60000);
         Assert.assertTrue(ftp.FileExist(filepath1));

	}
    @Test(priority=10)
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.addNewFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=11)
    public void AddInvalidRecordWithoutFaxline() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
         FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
         faxRoutingConfigurationPage.addNewInvalidRecordWithoutFaxLine(faxRoutingConfigDetails);
         Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    }
    @Test(priority=12)
    public void AddInvalidRecordWithoutSenderType() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
         FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
         faxRoutingConfigurationPage.addNewInvalidRecordWithoutSenderType(faxRoutingConfigDetails);
         Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    }
    @Test(priority=13)
    public void AddInvalidRecordWithoutRouteType() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
         FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
         faxRoutingConfigurationPage.addNewInvalidRecordWithoutRouteType(faxRoutingConfigDetails);
     	 Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    }
    @Test(priority=14)
    public void AddInvalidRecordWithoutRoutedata() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
         FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
         faxRoutingConfigurationPage.addNewInvalidRecordWithoutRouteData(faxRoutingConfigDetails);
     	 Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=15)
    public void EditFaxRoutingConfigCancelRecord() throws Exception {
   	 	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.editcancel(faxRoutingConfigDetails));
    }
    
    @Test(priority=16)
    public void EditInvalidFaxRoutingConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.editInvalidFaxRoutingConfig(faxRoutingConfigDetails);
        Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    }
    
    @Test(priority=17)//,dependsOnMethods = {"AddFaxRoutingConfigQueueRecord"})
    public void EditFaxRoutingConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.editFaxRoutingConfig(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(),"Record Updated Successfully");
    }
    
    @Test(dependsOnMethods="EditFaxRoutingConfigRecord",priority=18)
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
    }
    
    @Test(priority=19)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertFalse(faxRoutingConfigurationPage.clearAll(faxRoutingConfigDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("FaxRoutingConfigTest", "clearall");
        Assert.assertTrue(faxRoutingConfigurationPage.verifyclose());
        screenshot.captureScreen("FaxRoutingConfigTest", "SearchClose");
    }
    
    @Test(priority=20)
    public void searchwithoutSearchTextbox() throws IOException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.searchwithoutextsearch(faxRoutingConfigDetails);
    	Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","FaxRoutingConfigTest");
    }
    
    @Test(priority=21)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
    	Assert.assertTrue(faxRoutingConfigurationPage .verifyDatabase(faxRoutingConfigDetails.getQuery()));
    }
    
    @Test(priority=22)
    public void DeleteCancelFaxRoutingConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.deletecancelRecord(faxRoutingConfigDetails));
    }
    
    @Test(priority=23)
    public void DeleteFaxRoutingConfigRecordWithoutReason() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.deleteRoutingConfigwithoutReason(faxRoutingConfigDetails);
    	Assert.assertFalse(faxRoutingConfigurationPage.getErrorMsg());

    }
    
    @Test(dependsOnMethods = {"EditFaxRoutingConfigRecord"},priority=24)
    public void DeleteFaxRoutingConfigRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.deleteFaxRoutingConfigRecord(faxRoutingConfigDetails);
        Assert.assertEquals(faxRoutingConfigurationPage.getSuccessMessage(),"Record Deleted Successfully");
    }
    
    @Test(priority=25)
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
    }
    
    @Test(priority=26)
    public void SearchClearSearch() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxRoutingConfigurationDetails faxRoutingConfigDetails = new FaxRoutingConfigurationDetails(map);
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyinvalidsearchwithwrongdata(faxRoutingConfigDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("FaxRoutingConfigTest","Invalid Search with wrong data");
        Assert.assertTrue(faxRoutingConfigurationPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( "FaxRoutingConfigTest","Clear Search");
    }
    
    @Test(priority=27)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=28)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=29)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxRoutingConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        FaxRoutingConfigurationDetails faxRoutingConfigurationDetails = new FaxRoutingConfigurationDetails(map);
        Assert.assertTrue(faxRoutingConfigurationPage.ExporttoExcelWithoutData(faxRoutingConfigurationDetails));
    }
  
    @Test(priority=30)
    public void SortingByAscending() throws IOException {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=31)
    public void SortingByDescending() throws IOException {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        faxRoutingConfigurationPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Routing Configuration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxRoutingConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=32)
    public void GroupBy()
    {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.groupby());
        screenshot.captureScreen("FaxRoutingConfigurationTest", "GroupBy");
    	Assert.assertTrue(faxRoutingConfigurationPage.groupby());
        screenshot.captureScreen("FaxRoutingConfigurationTest", "AlreadyGroupBy");
    }
    
    @Test(priority=33)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
    	Assert.assertTrue(faxRoutingConfigurationPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=36)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxRoutingConfigurationPage faxRoutingConfigurationPage = PageFactory.createPageInstance(driver, FaxRoutingConfigurationPage.class);
        Assert.assertTrue(faxRoutingConfigurationPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
    	Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("FaxRoutingConfigTest",method.getName());
        driver.navigate().refresh();    }
    
    @AfterClass
    public void DeleteFaxLineConfigRecord() throws Exception {
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
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
        FaxLineConfigDetails faxLineConfigDetails = new FaxLineConfigDetails(map);
        faxLineConfigPage.deleteFaxLineConfig(faxLineConfigDetails);
        Assert.assertEquals(faxLineConfigPage.getSuccessMessage(),"Record Deleted Successfully");
    }
}
