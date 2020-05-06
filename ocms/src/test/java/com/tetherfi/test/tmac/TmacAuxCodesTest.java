package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacAuxCodesDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.TmacAuxCodesPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class TmacAuxCodesTest extends BaseTest {
     Screenshot screenshot=new Screenshot(driver);
     
     @BeforeMethod()
     public void NavigateToTmaxAuxCodesPage() {
    	 HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
    	 homePage.navigateToOCMPage();
    	 OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
    	 Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
    	 ocmHomePage.navigateToTab("TMAC");
    	 TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
    	 Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
    	 tmacPage.navigateToTmacAuxCodesPage();
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.isTmacAuxCodesPageIsDisplayed(), "Tmac Aux Codes Assertion  failed");
     }

     /*@Test(priority=1)
     public void TmacAuxCodesPage() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifylogo(), "TMAC Aux Logo Assertion failed");
    	 Assert.assertTrue(tmacAuxCodesPage.verifygridcontent(), "GridContent Assertion failed");
    	 Assert.assertTrue(tmacAuxCodesPage.maximizewindow(), "Maximize Assertrion failed");
    	 screenshot.captureScreen("TmacAuxCodesTest", "Maximize Window");
    	 Assert.assertTrue(tmacAuxCodesPage.minimizewindow(), "Minimize Assertion failed");
    	 screenshot.captureScreen("TmacAuxCodesTest", "Minimize Window");
     }
     
     @Test(priority=2)
     public void verifyAddNewTmacAuxCodesCancelButton() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String, String>map= new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.addCancelButton(tmacAuxCodesDetails), "CancelButton assertion Field");
     }
     
     @Test(priority=3)
     public void AddRecordWithoutValue() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.addRecordWithoutValue(tmacAuxCodesDetails);
    	 Assert.assertFalse(tmacAuxCodesPage.errormessage(), "Add Record without Value Assertion Failed");
     }
     
     @Test(priority=4)
     public void AddRecordWithoutName() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.addRecordWithoutName(tmacAuxCodesDetails);
    	 Assert.assertFalse(tmacAuxCodesPage.errormessage(), "Add Record without Name Assertion Failed");
     }
     
     @Test(priority=5)
     public void AddRecordWithoutCode() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.addRecordWithoutCode(tmacAuxCodesDetails);
    	 Assert.assertFalse(tmacAuxCodesPage.errormessage(), "Add Record without Code Assertion Failed");
     }
     
     @Test(priority=6)
     public void AddRecordWithoutStatus() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.addRecordWithoutStatus(tmacAuxCodesDetails);
    	 Assert.assertFalse(tmacAuxCodesPage.errormessage(), "Add Record without Status Assertion Failed");
     }*/
     
     @Test(priority=7)
     public void AddNewTmacAuxCodesRecord() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String, String>map= new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.addNewTmacAuxCodesRecord(tmacAuxCodesDetails);
    	 Assert.assertTrue(tmacAuxCodesPage.verifymessage(), "Record Creation assertion failed");
     }
     
     /*@Test(priority=8,dependsOnMethods="AddNewTmacAuxCodesRecord")
     public void verifyAuditTrailReportForCreate() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String, String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
         homePage.navigateToOCMReportsPage();
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
         String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
         Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map2);
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ocmReportsPage.verifyTmacAuxCodesCreate(tmacAuxCodesDetails, "Create"), "Create Audit Trail Assertion Failed"); 
     }
     
     @Test(priority=9,dependsOnMethods="AddNewTmacAuxCodesRecord")
     public void DuplicateRecord() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String, String>map= new ExcelReader(filePath,"Create").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.addNewTmacAuxCodesRecord(tmacAuxCodesDetails);
    	 Assert.assertFalse(tmacAuxCodesPage.errormessage(), "Duplicate Record Creation assertion failed");
     }
     
     @Test(priority=10)
     public void verifyEditCancelButton() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.EditCancel(tmacAuxCodesDetails), "Edit cancel Button Assertion failed");
     }
     
     @Test(priority=11)
     public void verifyEditRecordWithOutModifyReason() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.EditRecordWithoutModifyReason(tmacAuxCodesDetails);
    	 Assert.assertFalse(tmacAuxCodesPage.errormessage(), "Without ModifyReason Record assertion failed");
     }*/
     
     @Test(priority=12)
     public void verifyEditRecord() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.EditRecord(tmacAuxCodesDetails);
         Assert.assertTrue(tmacAuxCodesPage.verifymessage(), "Edit Assrtion failed");
     }
     
     /*@Test(priority=13,dependsOnMethods="verifyEditRecord")
     public void verifyAuditTrailReportForUpdate() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String, String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
         homePage.navigateToOCMReportsPage();
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
         String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
         Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map2);
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ocmReportsPage.verifyTmacAuxCodesUpdate(tmacAuxCodesDetails, "Update"), "Update Audit Trail Assertion Failed"); 
     }
     
     @Test(priority=14)
     public void ExportToExcelButton() {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.ExportToExcelButton(filePath), "Export Button assertion failed");
    	 screenshot.captureScreen(driver, filePath, "TmacAuxCodes");
     }
     
     @Test(priority=15,dependsOnMethods="ExportToExcelButton")
     public void verifyExportedData() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TMAC Aux Codes.xlsx";
    	 List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
     }
     
    @Test(priority=16)
     public void verifyExportToExcelWithoutData() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesdetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.VerifyExportToExcelWithoutData(tmacAuxCodesdetails), "Export to Excel Without Data assertion failed");
     }
     
     @Test(priority=17)
     public void verifySortByAscending() throws Exception {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.verifySortByAscending();
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TMAC Aux Codes (1).xlsx";
    	 List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
    	 Assert.assertTrue(tmacAuxCodesPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
     }
     
     @Test(priority=18)
     public void verifySortByDescending() throws Exception {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.verifySortByDescending();
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TMAC Aux Codes (2).xlsx";
    	 List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
    	 Assert.assertTrue(tmacAuxCodesPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
     }
     
     @Test(priority=19)
     public void verifyGroupBy() {
    	 TmacAuxCodesPage tmacAuxcodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 tmacAuxcodesPage.groupBy();
    	 screenshot.captureScreen("Tmac Aux Codes", "group By Column");
    	 tmacAuxcodesPage.groupBy();
    	 screenshot.captureScreen("Tmac Aux Codes", "Already grouped Coloumn");
     }
      
     @Test(priority=20)
     public void verifyArrowMoveForPreviousAndNextPage() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
     }
     
     @Test(priority=21)
     public void verifyArrowMoveForFirstAndLastPage() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
     }
     
     @Test(priority=22)
     public void verifyTotalNumberOfItemsPerPageDetails() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
     }
     
     @Test(priority=23)
     public void verifyNumberOfItemsPerPageSelection() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
     }
     
     @Test(priority=24)
     public void verifyDropDownForAllTheColumns() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifyDropDownOfAllHeaders(), "Dropdown for all the Columns Assertion Failed");
     }
     
     @Test(priority=25)
     public void verifyColoumnsHeaderEnabled() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifycolumnsHeaderEnabled(), "Columns Headers Enabled Assertion Failed");
     }
     
     @Test(priority=26) 
     public void verifyColumnsHeaderDisabled() {
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertFalse(tmacAuxCodesPage.verifycolumnsHeaderDisabled(), "Columns Headers Disabled Assertion Failed");
     }
     
     @Test(priority=27)
     public void verifyDeleteNoButton() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, com.tetherfi.pages.TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.DeleteCancel(tmacAuxCodesDetails), "Delete No Buuton Assertion failed");
     }
     
     @Test(priority=28)
     public void verifyWithoutDeleteReason() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, com.tetherfi.pages.TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.DeleteWithoutReason(tmacAuxCodesDetails);
    	 Assert.assertFalse(tmacAuxCodesPage.errormessage(), "Delete without Delete Reason Failed");
     }*/
     
     @Test(priority=29)
     public void DeleteTmacAuxCodesRecord() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, com.tetherfi.pages.TmacAuxCodesPage.class);
    	 tmacAuxCodesPage.DeleteRecord(tmacAuxCodesDetails);
    	 Assert.assertTrue(tmacAuxCodesPage.verifymessage(), "Delete Assertion Failed");
     }
     
     /*@Test(priority=30,dependsOnMethods="DeleteTmacAuxCodesRecord")
     public void verifyAuditTrailReportForDelete() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String, String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
    	 TmacAuxCodesDetails tmacAuxCodesDetails=new TmacAuxCodesDetails(map);
    	 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
         homePage.navigateToOCMReportsPage();
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
         String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
         Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map2);
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ocmReportsPage.verifyTmacAuxCodesDelete(tmacAuxCodesDetails, "Delete"), "Create Audit Trail Assertion Failed"); 
     }
     
     @Test(priority=31)
     public void SearchIsNotEqualTo() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
    	 TmacAuxCodesDetails tmacAuxCodesdetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifySearchIsnotEqualTo(tmacAuxCodesdetails.getCode()), "Search assertion Failed");
     }
     
     @Test(priority=32)
     public void SearchContains() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
    	 TmacAuxCodesDetails tmacAuxCodesdetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifySearchContains(tmacAuxCodesdetails.getCode()), "Search assertion Failed");
     }
     
     @Test(priority=33)
     public void SearchDoesNotContains() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
    	 TmacAuxCodesDetails tmacAuxCodesdetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifySearchDoesNotContains(tmacAuxCodesdetails.getCode()), "Search assertion Failed");
     }
     
     @Test(priority=34)
     public void SearchStartsWith() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
    	 TmacAuxCodesDetails tmacAuxCodesdetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifySearchStartsWith(tmacAuxCodesdetails.getCode()), "Search assertion Failed");
     }
     
     @Test(priority=35)
     public void SearchEndsWith() throws Exception {
    	 String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacAuxCodesData.xlsx";
    	 Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(4);
    	 TmacAuxCodesDetails tmacAuxCodesdetails=new TmacAuxCodesDetails(map);
    	 TmacAuxCodesPage tmacAuxCodesPage=PageFactory.createPageInstance(driver, TmacAuxCodesPage.class);
    	 Assert.assertTrue(tmacAuxCodesPage.verifySearchEndsWith(tmacAuxCodesdetails.getCode()), "Search assertion Failed");
     }*/
          
     @AfterMethod()
     public void AfterEachMethod(Method method) {
    	 Screenshot screenshot=new Screenshot(driver);
    	 screenshot.captureScreen("TmacAuxCodesTest", method.getName());
    	 driver.navigate().refresh();
     }
}
