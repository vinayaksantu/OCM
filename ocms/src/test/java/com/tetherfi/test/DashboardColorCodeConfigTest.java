package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.DashboardColorCodeConfigDetails;
import com.tetherfi.pages.DashboardColorCodeConfigPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class DashboardColorCodeConfigTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

    @BeforeMethod
    public void NavigateToDashboardColorCodeConfigPage() throws Exception {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToDashboardColorConfigPage();
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);        
        Assert.assertTrue(dshccpage.isDashboardColorConfigPageDisplayed(),("Dashboard Color Code Config page assertion failed"));
    }

    /*@Test (priority=1)
    public void DashboardColorCodeConfigPage()    {
    	DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
    	Assert.assertTrue(dshccpage.verifylogo(),"Dashboard Color Config logo assertion failed");
        Assert.assertTrue(dshccpage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(dshccpage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("DashboardColorConfigTest", "Maximize Window");
    	Assert.assertTrue(dshccpage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("DashboardColorConfigTest", "Minimize Window");
    }
   
    @Test(priority=2)
    public void AddNewDashboardColorConfigRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
        DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
        dshccpage.addNewDashboardColorConfigRecord(dashColorConfigDetails);
        Assert.assertEquals(dshccpage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }*/
   
   @Test(priority=3)
   public void AddnewCancel() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.VerifyCancelBtnAddNewRecord(dashColorConfigDetails));
      screenshot.captureScreen("DashboardColorConfigTest", "add cancel");
   }
   
   /*@Test(priority=3)
   public void VerifyAuditTrialReportForCreate() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       DashboardColorConfigDetails dshColorConfigDetails = new DashboardColorConfigDetails(map);
       HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
       homePage.navigateToOCMReportsPage();
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
       String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
       Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map1);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifydshColorConfigCreate(dshColorConfigDetails,"Create"));       
   }*/
   
   /*@Test(priority=4)
   public void AddRecordWithoutStartTime() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       dshccpage.addRecordWithoutStartTime(dashColorConfigDetails);
       Assert.assertFalse(dshccpage.verifymessage(),"AddRecordWithoutStartTime Assertion failed");
   }
   
   @Test(priority=5)
   public void AddRecordWithoutEndTime() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       dshccpage.addRecordWithoutEndTime(dashColorConfigDetails);
       Assert.assertFalse(dshccpage.verifymessage(),"AddRecordWithoutEndTime Assertion failed");
   }
   
   @Test(priority=6)
   public void AddRecordWithoutDuplicate() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       dshccpage.addNewDashboardColorConfigRecord(dashColorConfigDetails);
       Assert.assertFalse(dshccpage.verifymessage(),"Duplicate Assertion failed");
   }*/
  
   	@Test(priority=7)
    public void EditDashboardColorConfigCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
        DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
        Assert.assertTrue(dshccpage.editcancel(dashColorConfigDetails));
   	}
   	
   	/*@Test(priority=8)
        public void EditDashboardColorConfigRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
        DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);     
        dshccpage.editDashboardColorConfigRecord(dashColorConfigDetails);
        Assert.assertEquals(dshccpage.getSuccessMessage(),"Record updated successfully","Edit record assertion failed");
    }
   /*	
   	@Test(priority=9)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
 	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        DashboardColorConfigDetails dshColorConfigDetails = new DashboardColorConfigDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifydshColorConfigUpdate(dshColorConfigDetails,"Update"));
    }*/
   
   /*@Test(priority=10)
   public void searchPage() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertFalse(dshccpage.clearAll(dashColorConfigDetails),"ClearAll Assertion Failed");
       screenshot.captureScreen("DashboardColorConfigTest", "clearall");
       Assert.assertTrue(dshccpage.verifyclose());
       screenshot.captureScreen("DashboardColorConfigTest", "SearchClose");
       }
   
   @Test(priority=11)
   public void SearchClearSearch() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.verifyinvalidsearch(dashColorConfigDetails), "InvalidSearchAssertionFailed");
       screenshot.captureScreen("DashboardColorConfigTest", "Invalid Search");
       Assert.assertTrue(dshccpage.verifyclearsearch(), "Clear All Assertion Failed");
   }
   
   @Test(priority=12)
   public void ExportToExcel() throws Exception{
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
   		DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.verifyExportToExcel(filePath));
   }
   
   @Test(priority=13)
   public void ExportToExcelData() throws Exception{	
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Wait Time Color Config.xlsx";
   	  List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
   		DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
   		Assert.assertTrue(dshccpage.verifyexportToExcelSheet(maplist));
   } 
   
   @Test(priority=14)
    public void DeleteCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
        DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
        Assert.assertTrue(dshccpage.deleteNo(dashColorConfigDetails.getStartRange(),dashColorConfigDetails.getDeleteReason()));
   }
   
   @Test(priority=15)
   public void DeleteRecord() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       dshccpage.deleteDashboardColorConfigRecord(dashColorConfigDetails.getStartRange(),dashColorConfigDetails.getDeleteReason());
       Assert.assertEquals(dshccpage.getSuccessMessage(),"Record deleted successfully","Delete record assertion failed");
   }
   
  /*
   @Test(priority=16)
   public void VerifyAuditTrialReportForDelete() throws Exception {
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       DashboardColorConfigDetails dshColorConfigDetails = new DashboardColorConfigDetails(map);
       HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
       homePage.navigateToOCMReportsPage();
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
       String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
       Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map1);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifydshColorConfigdelete(dshColorConfigDetails,"Delete"));
   }
 
   @Test(priority=17)
   public void database() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
   		DashboardColorConfigDetails dshColorConfigDetails = new DashboardColorConfigDetails(map);
   		DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
   		Assert.assertTrue(dshccpage.verifyDatabase(dshColorConfigDetails.getQuery()));
   }
     
   @Test(priority=18)
   public void GroupBy(){
       	DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
   		Assert.assertTrue(dshccpage.groupby());
   }
   
   @Test(priority=19)
   public void VerifyArrowMoveForPreviousAndNextPage() {
	   DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
   		Assert.assertTrue(dshccpage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
   }
   
   @Test(priority=20)
   public void VerifyArrowMoveForFirstAndLastPage() {
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
   }
   
   @Test(priority=21)
   public void VerifyTotalNumberOfItemsPerPageDetails() {
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
   }
   
   @Test(priority=22)
   public void VerifyNumberOfItemsPerPageSelection() {
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
   }
   
   @Test(priority=23)
   public void VerifyDropdownForAllTheColumns() {
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
   }
   
   @Test(priority=24)
   public void VerifyColumnsHeaderEnable() {
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertTrue(dshccpage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
   }
   
   @Test(priority=25)
   public void VerifyColumnsHeaderDisable() {
       DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
       Assert.assertFalse(dshccpage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
   }
   
   @Test(priority=26)
   public void SortingByAscending() throws IOException {
	   DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
	   dshccpage.SortByAscending();
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Dashboard Color Code Config (1).xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(dshccpage.verifyexportToExcelSheet(maplist));
   }
   @Test(priority=27)
   public void SortingByDescending() throws IOException {
	   DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
	   dshccpage.SortByDescending();
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Dashboard Color Code Config (2).xlsx";
	   List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	   Assert.assertTrue(dshccpage.verifyexportToExcelSheet(maplist));
   }
   @Test(priority=28)
   public void ExporttoExcelWithoutData() throws Exception   {
	   DashboardColorCodeConfigPage dshccpage=PageFactory.createPageInstance(driver,DashboardColorCodeConfigPage.class);
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\DashboardColourConfig.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        DashboardColorCodeConfigDetails dashColorConfigDetails = new DashboardColorCodeConfigDetails(map);
       Assert.assertTrue(dshccpage.ExporttoExcelWithoutData(dashColorConfigDetails));
   }*/

    @AfterMethod
    	public void afterEachMethod(Method method) throws InterruptedException {
            Screenshot screenshot=new Screenshot(driver);
            screenshot.captureScreen("DashboardColorConfigTest",method.getName());
            driver.navigate().refresh();
        }
}
