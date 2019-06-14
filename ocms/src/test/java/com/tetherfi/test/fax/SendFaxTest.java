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
import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.fax.SendFaxDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxLineConfigPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxRoutingConfigurationPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMFaxSentDetailsReportPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SendFaxPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SendFaxTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
    FTPServer ftp=new FTPServer();
    String filepath = "\\\\172.16.2.16\\d$\\Products\\WindowsServices\\TFaxManagementConsole\\TiffFileUploadPath";
    String filepath1 = "\\\\172.16.2.16\\d$\\Products\\WindowsServices\\TestTFax\\ReceivedFaxes";


	//@BeforeClass
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
        
    }
    
	@BeforeMethod
    public void NavigateToSendFaxPage() throws Exception {
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    
	/*@Test(priority=1)
    public void SendFaxPage() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.verifylogo(),"FaxLineConfig logo assertion failed");
    	Assert.assertTrue(sendFaxPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("SendFaxTest","maximize window");
    	Assert.assertTrue(sendFaxPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("SendFaxTest","minimize window");
    }
    
    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertFalse(sendFaxPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddSendFaxRecordFromTemplate() throws Exception {
    	ftp.DeleteFilesFromFolder(filepath1);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.AddCancelRecord(sendFaxDetails));
        sendFaxPage.addNewSendFaxRecord(sendFaxDetails);
        Assert.assertEquals(sendFaxPage.getSuccessMessage(), "Send Fax Initiated successfully");
    }
    
    @Test(priority=6,dependsOnMethods = {"AddSendFaxRecordFromTemplate"})
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
    	}
    
    @Test(priority=7)
    public void SearchData() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyvalidsearchdata(sendFaxDetails));
    }
    	
    @Test(priority=8)
    public void SearchClearSeach() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyinvalidsearchwithwrongdata(sendFaxDetails));
        Assert.assertTrue(sendFaxPage.verifyclearsearch());
    }
    
    @Test(priority=9)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.ExporttoExcelWithoutData(sendFaxDetails));
    }
    
    @Test(priority=10)
    public void AddSendFaxRecordByFileUpload() throws Exception {
    	ftp.DeleteFilesFromFolder(filepath);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addNewSendFaxRecordFileUpload(sendFaxDetails);
        Assert.assertEquals(sendFaxPage.getSuccessMessage(), "Send Fax Initiated successfully");
    }
    @Test(priority=11)
    public void VerifyTiffFileInFolder() throws Exception {
        Assert.assertTrue(ftp.FileExist(filepath));
    }
    
    @Test(priority=12)
    public void AddSendFaxRecordByUploadingMultiplePages() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addNewSendFaxRecordFileUpload(sendFaxDetails);
        Assert.assertEquals(sendFaxPage.getSuccessMessage(), "Send Fax Initiated successfully");
    }

	@Test(priority=13)
    public void VerifyRecievedFileInFolder() throws Exception {
 		Thread.sleep(3000);
        Assert.assertTrue(ftp.FileExist(filepath1));
    }
    
	@Test(priority=14)
    public void AddRecordWithoutRecipientNumber() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
         SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
         sendFaxPage.addRecordWithoutRecipientNumber(sendFaxDetails);
         Assert.assertFalse(sendFaxPage.getErrorMsg());
    }
    
    @Test(priority=15)
    public void AddRecordWithoutFaxLine() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
         SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	 sendFaxPage.addRecordWithoutFaxLine(sendFaxDetails);
         Assert.assertFalse(sendFaxPage.getErrorMsg());
    }
    @Test(priority=16)
    public void AddRecordWithoutDateTime() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addNewInvalidRecordWithoutDateTime(sendFaxDetails);
     	Assert.assertFalse(sendFaxPage.getErrorMsg());
    }
    
    @Test(priority=17)
    public void AddInvalidRecordWithoutSelectingTemplate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addNewInvalidRecordWithoutTemplate(sendFaxDetails);
     	Assert.assertFalse(sendFaxPage.getErrorMsg());
    }
    
    @Test(priority=18)
    public void AddInvalidRecorduploadingWrongfile() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addNewInvalidRecordUploadingWrongFile(sendFaxDetails);
     	Assert.assertFalse(sendFaxPage.getErrorMsg());
    }
    
    @Test(priority=19)
    public void AddRecordWithoutTemplate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addRecordWithoutTemplate(sendFaxDetails);
     	Assert.assertFalse(sendFaxPage.getErrorMsg());
    }
    
    @Test(priority=20)
    public void AddButtonPreviewuploadwrongFile() throws Exception {
   	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.addButtonPreviewuploadWrongfile(sendFaxDetails);
     	Assert.assertFalse(sendFaxPage.getErrorMsg());
   }
    
    @Test(priority=21)
    public void AddButtonPreview() throws Exception {
    	 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
         Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
         SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
         SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
         sendFaxPage.addButtonPreviewwithoutuploadingfile(sendFaxDetails);
      	Assert.assertEquals(sendFaxPage.getInfoMsg(),"Please upload a file to preview");
    }
    
    @Test(priority=22)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertFalse(sendFaxPage.clearAll(sendFaxDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("SendFaxTest", "clearall");
        Assert.assertTrue(sendFaxPage.verifyclose());
        screenshot.captureScreen("SendFaxTest", "SearchClose");
    }
    
    @Test(priority=23)
    public void searchwithoutSearchTextbox() throws IOException {
    	SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.searchwithoutextsearch();
    	Assert.assertFalse(sendFaxPage.getErrorMsg());
    }*/
    
    @Test(priority=24)//,dependsOnMethods = {"AddSendFaxRecordByUploadingMultiplePages"})
    public void VerifyOCMFaxSentDetailsReportMutiplePages() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFaxSentDetailsReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        OCMFaxSentDetailsReportPage ocmFaxSentDetailsReportsPage=PageFactory.createPageInstance(driver, OCMFaxSentDetailsReportPage.class);
        Assert.assertTrue(ocmFaxSentDetailsReportsPage.VerifySendStatus(sendFaxDetails));
    	}
    
    @Test(priority=25)//,dependsOnMethods = {"AddSendFaxRecordByFileUpload"})
    public void VerifyOCMFaxSentDetailsReport() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFaxSentDetailsReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        OCMFaxSentDetailsReportPage ocmFaxSentDetailsReportsPage=PageFactory.createPageInstance(driver, OCMFaxSentDetailsReportPage.class);
        Assert.assertTrue(ocmFaxSentDetailsReportsPage.VerifySendStatus(sendFaxDetails));
    	}
    
    
   @Test(priority=26)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SendFaxData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        SendFaxDetails sendFaxDetails = new SendFaxDetails(map);
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyDatabase(sendFaxDetails.getQuery()));
    }
    
   @Test(priority=27)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=28)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Send Fax.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.verifyexportToExcelSheet(maplist));	
    }
    
  
    @Test(priority=29)
    public void SortingByAscending() throws IOException {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Send Fax (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(sendFaxPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=30)
    public void SortingByDescending() throws IOException {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        sendFaxPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Send Fax (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(sendFaxPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=31)
    public void GroupBy()
    {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","SendFaxTest");
    	Assert.assertTrue(sendFaxPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","SendFaxTest");
    }
    
    @Test(priority=32)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
    	Assert.assertTrue(sendFaxPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyArrowMoveForFirstAndLastPage() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyNumberOfItemsPerPageSelection() {
        SendFaxPage sendFaxPage = PageFactory.createPageInstance(driver, SendFaxPage.class);
        Assert.assertTrue(sendFaxPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
    	Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("SendFaxTest",method.getName());
    }
    
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
