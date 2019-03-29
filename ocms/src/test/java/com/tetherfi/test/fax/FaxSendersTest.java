package com.tetherfi.test.fax;

import com.tetherfi.model.fax.FaxLineConfigDetails;
import com.tetherfi.model.fax.FaxSendersDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FaxSendersTest extends BaseTest {
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @BeforeMethod
    public void NavigateToFaxSendersPage() {
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
    }

    //@Test(priority=1)
    public void FaxSendersPage() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(faxSendersPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"maximize window","FaxSendersTest");
    	Assert.assertTrue(faxSendersPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"minimize window","FaxSendersTest");
    }
    
    //@Test(priority=2)
    public void AddFaxSendersRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.AddCancelRecord(faxSendersDetails));
        faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    //@Test(dependsOnMethods = {"AddFaxSendersRecord"},priority=3)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxSendersCreate(faxSendersDetails,"Create"));
    	screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","FaxSendersTest");
    	}
    
   //@Test(dependsOnMethods = {"AddFaxSendersRecord"},priority=3)
   public void AddDuplicateFaxSendersRecord() throws IOException {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
   //@Test
   public void AddFaxSendersRecordwithsameFaxLine() throws IOException {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
       Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
   }
   
   //@Test
   public void AddFaxSendersRecordwithsameFaxNumber() throws IOException {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
       Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
   }
   
   
   //@Test(dependsOnMethods = {"AddFaxSendersRecord"},priority=3)
   public void AddInvalidRecord() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecordwithoutinput(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
       faxSendersPage.addNewFaxSendersRecordwithoutfaxline(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
       faxSendersPage.addNewFaxSendersRecordwithoutName(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
       faxSendersPage.addNewFaxSendersRecordwithoutFaxNumber(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
       faxSendersPage.addNewFaxSendersRecordwithoutType(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
   //@Test//(dependsOnMethods = {"AddFaxSendersRecord"})
   public void EditFaxSendersRecordCancel() throws IOException {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       Assert.assertTrue(faxSendersPage.EditCancelRecord(faxSendersDetails));
   }
   
   //@Test
   public void EditFaxSendersInvalidRecord() throws IOException {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.editFaxSendersInvalidRecord(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
    //@Test(dependsOnMethods = {"EditFaxSendersRecordCancel"})
    public void EditFaxSendersRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.editFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(),"Record Updated Successfully");
    }
    
    //@Test(dependsOnMethods="EditFaxSendersRecord",priority=6)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySendersUpdate(faxSendersDetails,"Update"));
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForUpdate","FaxSendersTest");
    }
    //@Test(priority=19)
    public void searchPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertFalse(faxSendersPage.clearAll(faxSendersDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","FaxSendersTest");
        Assert.assertTrue(faxSendersPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","FaxSendersTest");
    }
    
    //@Test(priority=20)
    public void searchwithoutSearchTextbox() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.searchwithoutextsearch(faxSendersDetails);
    	Assert.assertFalse(faxSendersPage.getErrorMessage());
    	screenshot.captureScreen(driver, "searchwithoutSearchTextbox()","FaxSendersTest");
    }
    
    @Test(priority=11)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
    	Assert.assertTrue(faxSendersPage.verifyDatabase(faxSendersDetails.getQuery()));
    }
    
    //@Test(priority=25)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyinvalidsearchwithwrongdata(faxSendersDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver,"Invalid Search with wrong data", "FaxLineConfigTest");
        Assert.assertTrue(faxSendersPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen( driver,"Clear Search", "FaxSendersTest");
    }
    
    
    //@Test//(dependsOnMethods = {"EditFaxSendersRecord"})
    public void DeleteFaxSendersCancelRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.deleteFaxSendersCancelRecord(faxSendersDetails));
    }
    
    //@Test(dependsOnMethods = {"DeleteFaxSendersCancelRecord"})
    public void DeleteFaxSendersInvalidRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.deleteFaxSendersInvalidRecord(faxSendersDetails);
        Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    
    //@Test(dependsOnMethods = {"DeleteFaxSendersCancelRecord"})
    public void DeleteFaxSendersRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.deleteFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Deleted Successfully");
    }
    //@Test(dependsOnMethods = {"DeleteFaxSendersRecord"})
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyFaxSendersdelete(faxSendersDetails,"Delete"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForUpdate","");
    }
    
    /*@Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyExportToExcel(filePath));
        screenshot.captureScreen(driver,"Export Excel","FaxSendersTest");
    }
    
    @Test(priority=18)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Senders.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen(driver,"Export Excel Sheet","FaxSendersTest");
    }
    
    @Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        Assert.assertTrue(faxSendersPage.ExporttoExcelWithoutData(faxSendersDetails));
        screenshot.captureScreen( driver,"ExporttoExcelWithoutData", "FaxSendersTest");
    }
    @Test(priority=27)
    public void SortingByAscending() throws IOException {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Senders (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxSendersPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=28)
    public void SortingByDescending() throws IOException {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Senders (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxSendersPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=29)
    public void GroupBy()
    {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","FaxSendersTest");
    	Assert.assertTrue(faxSendersPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","FaxSendersTest");
    }
    
    @Test(priority=30)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyArrowMoveForFirstAndLastPage() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=32)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=33)
    public void VerifyNumberOfItemsPerPageSelection() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=34)
    public void VerifyDropdownForAllTheColumns() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=35)
    public void VerifyColumnsHeaderEnable() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }*/
    
    //@Test(priority=0)
    public void VerifyColumnsHeaderDisable() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertFalse(faxSendersPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    //@Test
    public void BulkUploadData() throws Exception {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.VerifyBulkUpload("TEST.CSV");
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "CSV data being uploaded. Please check after a while"); 
        Thread.sleep(1000);
        faxSendersPage.VerifyBulkUpload("InvalidType.csv");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
        Thread.sleep(1000);
        faxSendersPage.VerifyBulkUpload("InvalidNumber.csv");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
        Thread.sleep(1000);
        faxSendersPage.VerifyBulkUpload("InvalidFaxline.csv");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
        Thread.sleep(1000);
        faxSendersPage.VerifyBulkUpload("HolidayList.xlsx");
        Assert.assertTrue(faxSendersPage.verifycolor());
        faxSendersPage.VerifyBulkUpload("TEST.CSV");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
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


