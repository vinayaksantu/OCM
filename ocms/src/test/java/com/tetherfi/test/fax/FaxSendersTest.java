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

    @Test(priority=1)
    public void FaxSendersPage() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(faxSendersPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("FaxSendersTest","maximize window");
    	Assert.assertTrue(faxSendersPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("FaxSendersTest","minimize window");
    }
    
    @Test(priority=2)
    public void AddFaxSendersRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.AddCancelRecord(faxSendersDetails));
        faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
    }
    
    @Test(dependsOnMethods = {"AddFaxSendersRecord"},priority=3)
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
    	}
    
   @Test(dependsOnMethods = {"AddFaxSendersRecord"},priority=4)
   public void AddDuplicateFaxSendersRecord() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
   @Test(priority=5)
   public void AddFaxSendersRecordwithsameFaxLine() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
       Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
   }
   
   @Test(priority=6)
   public void AddFaxSendersRecordwithsameFaxNumber() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecord(faxSendersDetails);
       Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Created Successfully");
   }
   
   
   @Test(priority=7)
   public void AddRecordWithoutInput() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecordwithoutinput(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   @Test(priority=8)
   public void AddRecordWithoutFaxLine() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecordwithoutfaxline(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
   @Test(priority=9)
   public void AddRecordWithoutName() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecordwithoutName(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
   @Test(priority=10)
   public void AddRecordWithoutFaxNumber() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecordwithoutFaxNumber(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
   @Test(priority=11)
   public void AddRecordWithoutType() throws Exception {
       String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.addNewFaxSendersRecordwithoutType(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
   @Test(priority=12)
   public void EditFaxSendersRecordCancel() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       Assert.assertTrue(faxSendersPage.EditCancelRecord(faxSendersDetails));
   }
   
   @Test(priority=13)
   public void EditFaxSendersInvalidRecord() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
       FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
       FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
       faxSendersPage.editFaxSendersInvalidRecord(faxSendersDetails);
       Assert.assertFalse(faxSendersPage.getErrorMessage());
   }
   
    @Test(priority=14,dependsOnMethods = {"AddFaxSendersRecord"})
    public void EditFaxSendersRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.editFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(),"Record Updated Successfully");
    }
    
    @Test(dependsOnMethods="EditFaxSendersRecord",priority=15)
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
    }
    @Test(priority=16)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertFalse(faxSendersPage.clearAll(faxSendersDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("FaxSendersTest", "clearall");
        Assert.assertTrue(faxSendersPage.verifyclose());
        screenshot.captureScreen("FaxSendersTest", "SearchClose");
    }
    
    @Test(priority=17)
    public void searchwithoutSearchTextbox() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.searchwithoutextsearch(faxSendersDetails);
    	Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    
    @Test(priority=18)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
    	Assert.assertTrue(faxSendersPage.verifyDatabase(faxSendersDetails.getQuery()));
    }
    
    @Test(priority=19)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyinvalidsearchwithwrongdata(faxSendersDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("FaxSendersTest","Invalid Search with wrong data");
        Assert.assertTrue(faxSendersPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    
    @Test(priority=20)
    public void DeleteFaxSendersCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.deleteFaxSendersCancelRecord(faxSendersDetails));
    }
    
    @Test(priority=21)
    public void DeleteFaxSendersInvalidRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.deleteFaxSendersInvalidRecord(faxSendersDetails);
        Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    
    @Test(dependsOnMethods = {"EditFaxSendersRecord"},priority=22)
    public void DeleteFaxSendersRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.deleteFaxSendersRecord(faxSendersDetails);
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "Record Deleted Successfully");
    }
    @Test(priority=23,dependsOnMethods = {"DeleteFaxSendersRecord"})
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
    }
    
    @Test(priority=24)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertTrue(faxSendersPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=25)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Senders.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	Assert.assertTrue(faxSendersPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=26)
    public void ExporttoExcelWithoutData() throws Exception
    {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxSendersData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(3);
        FaxSendersDetails faxSendersDetails = new FaxSendersDetails(map);
        Assert.assertTrue(faxSendersPage.ExporttoExcelWithoutData(faxSendersDetails));
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
        screenshot.captureScreen("FaxSendersTest", "GroupBy");
    	Assert.assertTrue(faxSendersPage.groupby());
        screenshot.captureScreen("FaxSendersTest", "AlreadyGroupBy");
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
    }
    
    @Test(priority=36)
    public void VerifyColumnsHeaderDisable() {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        Assert.assertFalse(faxSendersPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=37)
    public void BulkUploadData() throws Exception {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.VerifyBulkUpload("TEST.CSV");
        Assert.assertEquals(faxSendersPage.getSuccessMessage(), "CSV data being uploaded. Please check after a while"); 
    }
    @Test(priority=38)
    public void BulkUploadDataInvalidType() throws Exception {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.VerifyBulkUpload("InvalidType.csv");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    @Test(priority=39)
    public void BulkUploadDataInvalidNumber() throws Exception {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.VerifyBulkUpload("InvalidNumber.csv");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    @Test(priority=40)
    public void BulkUploadDataInvalidFaxline() throws Exception {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.VerifyBulkUpload("InvalidFaxline.csv");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    @Test(priority=41)
    public void BulkUploadDataInvalidFile() throws Exception {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.VerifyBulkUpload("HolidayList.xlsx");
        Assert.assertTrue(faxSendersPage.verifycolor());
    }
    @Test(priority=42)
    public void BulkUploadDuplicateData() throws Exception {
        FaxSendersPage faxSendersPage = PageFactory.createPageInstance(driver, FaxSendersPage.class);
        faxSendersPage.VerifyBulkUpload("TEST.CSV");
        Assert.assertFalse(faxSendersPage.getErrorMessage());
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
        	Screenshot screenshot=new Screenshot(driver);
            screenshot.captureScreen("FaxSendersTest",method.getName());
            driver.navigate().refresh();    
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


