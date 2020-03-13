
package com.tetherfi.test.reports;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMSmsReportPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMSmsReportPage;
import com.tetherfi.pages.VbEnrollmentFlagPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class OCMSmsReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToOcmReportsPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOcmIconImg();
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
    }
  
    @Test(priority=1)
    public void ShowOCMSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");
        
    }
         
    @Test(priority=2, dependsOnMethods ="ShowOCMSmsReport")
    public void ShowOcmSmsReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }
   
    @Test(priority=3)
    public void ScheduleOCMSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
   
    @Test(priority=4,dependsOnMethods ="ShowOcmSmsReportInNewTab")
    public void ExportOcmSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
  
   @Test(priority=5,dependsOnMethods ="ExportOcmSmsReport")
    public void ViewDownloadedOcmSmsReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
       
   @Test(priority=6)
   public void ShowOcmSmsReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);

       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   }
   
   @Test(priority=7,dependsOnMethods ="ShowOcmSmsReportForDateRange")
   public void ShowOcmSmsReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);

       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }
     
    @Test(priority=8,dependsOnMethods ="ShowOcmSmsReportInNewTabDateRange")
    public void ExportOcmSmsReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
    
    @Test(priority=9,dependsOnMethods ="ExportOcmSmsReportDateRange")
    public void ViewDownloadedOcmSmsReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    
   
    @Test(priority=10,dependsOnMethods ="ShowOcmSmsReportForDateRange")
    public void ExportPageForOcmSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        OCMSmsReportPage smsPage= PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        smsPage.exportPage();
        Assert.assertTrue(smsPage.verifyExportPageFileDownloaded("OCM Sms Report.xlsx"),"Export page assertion failed");
    }
     
    @Test(priority=11)
	public void OCMWindow() throws Exception {
	
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
                
        Assert.assertTrue(smsPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMSmsReport","Maximize");
    	Assert.assertTrue(smsPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMSmsReport","Minimize");	
    }
	
	@Test(priority=12)
    public void VerifyDropdownForAllTheColumns() throws Exception {
		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		
		OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
		Assert.assertTrue(smsPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=13)
    public void VerifyColumnsHeaderEnable() throws Exception {
    	
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		
		OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
		Assert.assertTrue(smsPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=14)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        Assert.assertFalse(smsPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
   
    @Test(priority=15)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
   
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
    	Assert.assertTrue(smsPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=16)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        Assert.assertTrue(smsPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=17)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        Assert.assertTrue(smsPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=18)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        Assert.assertTrue(smsPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
    	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        Assert.assertTrue(smsPage.verifyExportToExcel(filePath1));
    }
    
    @Test(priority=20)
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        smsPage.SortByAscending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCM Sms Flag (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(smsPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=21)
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        smsPage.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCM Sms (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(smsPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=22)
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.ClearHomepgDrpDown(reportDetails);
    }
   
    @Test(dependsOnMethods ="ShowOcmSmsReportForDateRange")
    public void ExportPageForSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        smsPage.exportPage();     
        Assert.assertTrue(ocmReportsPage.verifyExportPageFileDownloaded("OCM Sms Report(1).xlsx"),"Export page assertion failed");
    }
   
    @Test(priority=23)
    public void SchedulereportinSmsPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);      
        
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        smsPage.navigateToExportSchedulerPage();    
        Assert.assertTrue(smsPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("OCMSmsReportTest","ExportSchedulerPage");
    	 Assert.assertTrue(smsPage.VerifyLogo(),"Logo assertion failed");
    }
     
    @Test(priority=24,dependsOnMethods ="ExportOcmSmsReport")
    public void ViewDownloadedOcmSmsReportInReportsDownloadPageinSmsPg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);  
        OCMSmsReportPage smsPage=PageFactory.createPageInstance(driver,OCMSmsReportPage.class);
        smsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    
    }
    /*  
    @Test(dependsOnMethods ="ShowOcmSmsReportForDateRange")
    public void VerifySearchByFeatureForSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByTextbox());
    }
    @Test(dependsOnMethods ="ShowOcmSmsReportForDateRange")
    public void VerifySearchFeatureForSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByColumnValue());
    }
    @Test(dependsOnMethods ="ShowOcmSmsReportForDateRange")
    public void VerifySearchContainsForSmsReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMSmsReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchContainsColumnValue());
    }   
    @Test()
    public void searchwithoutSearchTextbox() throws IOException {
    	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	ocmReportsPage.searchwithoutextsearch();
        Assert.assertEquals(ocmReportsPage.verifySuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }*/
      
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}
