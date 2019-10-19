
package com.tetherfi.test.reports;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMReportsPage;
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

public class OCMFrequencyThresholdTest extends BaseTest {
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
    public void ShowOCMFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");
        
    }
               
    @Test(priority=2, dependsOnMethods ="ShowOCMFrequencyReport")
    public void ShowOcmFrequencyReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }
    
    @Test(priority=3)
    public void ScheduleOCMFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
   
    @Test(priority=4,dependsOnMethods ="ShowOcmFrequencyReportInNewTab")
    public void ExportOcmFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
  
   @Test(priority=5,dependsOnMethods ="ExportOcmFrequencyReport")
    public void ViewDownloadedOcmFrequencyReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
        
   @Test(priority=6)
   public void ShowOcmFrequencyReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);

       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   }
   
   @Test(priority=7,dependsOnMethods ="ShowOcmFrequencyReportForDateRange")
   public void ShowOcmFrequencyReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);

       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }
     
    @Test(priority=8,dependsOnMethods ="ShowOcmFrequencyReportInNewTabDateRange")
    public void ExportOcmFrequencyReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
    
    @Test(priority=9,dependsOnMethods ="ExportOcmFrequencyReportDateRange")
    public void ViewDownloadedOcmFrequencyReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    @Test(priority=10,dependsOnMethods ="ShowOcmFrequencyReportForDateRange")
    public void ExportPageForOcmFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        ocmReportsPage.exportPage();
        Assert.assertTrue(ocmReportsPage.verifyExportPageFileDownloaded("OCM Threshold Report.xlsx"),"Export page assertion failed");
    }
    
    @Test(priority=11)
	public void OCMWindow() throws Exception {
	
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
                
        Assert.assertTrue(ocmReportsPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMFrequencyReport","Maximize");
    	Assert.assertTrue(ocmReportsPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMFrequencyReport","Minimize");	
    }
	
	@Test(priority=12)
    public void VerifyDropdownForAllTheColumns() throws Exception {
		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=13)
    public void VerifyColumnsHeaderEnable() throws Exception {
    	
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
		
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=14)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertFalse(ocmReportsPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
 
    @Test()
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
    	//OCMReportsPage ocmReportsPage= PageFactory.createPageInstance(driver, OCMReportsPage.class);
        ocmReportsPage.SortByAscending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCM Threshold Flag (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(ocmReportsPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test()
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
    	// OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
        ocmReportsPage.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCM Threshold (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(ocmReportsPage.verifyexportToExcelSheet(maplist));
    }
   
    @Test(priority=15)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
   
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
    	Assert.assertTrue(ocmReportsPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=16)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=17)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=18)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test()
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMFrequencyReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.clearAll();
        //Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    
    @Test(dependsOnMethods ="ShowOcmFrequencyReportForDateRange")
    public void VerifySearchByFeatureForFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByTextbox());
    }
    @Test(dependsOnMethods ="ShowOcmFrequencyReportForDateRange")
    public void VerifySearchFeatureForFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByColumnValue());
    }
    @Test(dependsOnMethods ="ShowOcmFrequencyReportForDateRange")
    public void VerifySearchContainsForFrequencyReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchContainsColumnValue());
    }
    
    @Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
        Assert.assertTrue(ocmReportsPage.verifyExportToExcel(filePath));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}

