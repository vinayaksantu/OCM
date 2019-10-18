
package com.tetherfi.test.reports;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OcmthresholdReportPage;
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

public class OCMThresholdReport extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToOcmReportsPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOcmIconImg();
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
    }
    
    @Test
    public void ShowOCMThresholdReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");
    }
    @Test(dependsOnMethods ="ShowOCMThresholdReport")
    public void ShowOcmThresholdReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }
   
    
    @Test()
    public void ScheduleOCMThresholdReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    
   
   @Test(dependsOnMethods ="ShowOcmThresholdReportInNewTab")
    public void ExportOcmThresholdReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
    
  @Test(dependsOnMethods ="ExportOcmThresholdReport")
    public void ViewDownloadedOcmThresholdReportInNotificationPan() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInNotificationTab();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameInNotificationPan(reportDetails.getReportName()),"Reporter name not found in notification tab");
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportGeneratedTime(),"Reporter date not found in notification tab");
    }
    
   @Test(dependsOnMethods ="ExportOcmThresholdReport")
    public void ViewDownloadedOcmThresholdReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    
   @Test
    public void ShowOcmThresholdReportForDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
    }
   @Test(dependsOnMethods ="ShowOcmThresholdReportForDateRange")
    public void ShowOcmThresholdReportInNewTabDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }
    @Test(dependsOnMethods ="ShowOcmThresholdReportInNewTabDateRange")
    public void ExportOcmThresholdReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
    @Test(dependsOnMethods ="ExportOcmThresholdReportDateRange")
    public void ViewDownloadedAuditTrailReportInNotificationPanDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInNotificationTab();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameInNotificationPan(reportDetails.getReportName()),"Reporter name not found in notification tab");
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportGeneratedTime(),"Reporter date not found in notification tab");
    }
    @Test(dependsOnMethods ="ExportOcmThresholdReportDateRange")
    public void ViewDownloadedOcmThresholdReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    @Test(dependsOnMethods ="ShowOcmThresholdReportForDateRange")
    public void ExportPageForOcmThresholdReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OcmThresholdReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);

        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        ocmReportsPage.exportPage();
        Assert.assertTrue(ocmReportsPage.verifyExportPageFileDownloaded("OCM Threshold Report.xlsx"),"Export page assertion failed");
    }
    
    
      @Test()
    public void SortingByAscending() throws IOException {
        OcmthresholdReportPage OCMThreshold= PageFactory.createPageInstance(driver, OcmthresholdReportPage.class);
        OCMThreshold.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCM Threshold Flag (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(OCMThreshold.verifyexportToExcelSheet(maplist));
    }
    
    @Test()
    public void SortingByDescending() throws IOException {
        OcmthresholdReportPage OCMThreshold = PageFactory.createPageInstance(driver, OcmthresholdReportPage.class);
        OCMThreshold.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCM Threshold (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(OCMThreshold.verifyexportToExcelSheet(maplist));
    }
   /* 
    @Test(priority=23)
    public void GroupBy()
    {
        VbEnrollmentFlagPage OCMThreshold = PageFactory.createPageInstance(driver, VbEnrollmentFlagPage.class);
    	Assert.assertTrue(VbEnrollmentFlagPage.groupby());
        screenshot.captureScreen("VBEnrollmentFlagTest", "GroupBy");
    	Assert.assertTrue(VbEnrollmentFlagPage.groupby());
        screenshot.captureScreen("VBEnrollmentFlagTest", "AlreadyGroupBy");
    }
    */
    @Test()
    public void VerifyArrowMoveForPreviousAndNextPage() {
        OcmthresholdReportPage OCMThreshold = PageFactory.createPageInstance(driver, OcmthresholdReportPage.class);
    	Assert.assertTrue(OCMThreshold.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test()
    public void VerifyArrowMoveForFirstAndLastPage() {
        OcmthresholdReportPage OCMThreshold = PageFactory.createPageInstance(driver, OcmthresholdReportPage.class);
        Assert.assertTrue(OCMThreshold.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test()
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        OcmthresholdReportPage OCMThreshold = PageFactory.createPageInstance(driver, OcmthresholdReportPage.class);
        Assert.assertTrue(OCMThreshold.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test()
    public void VerifyNumberOfItemsPerPageSelection() {
        OcmthresholdReportPage OCMThreshold = PageFactory.createPageInstance(driver, OcmthresholdReportPage.class);
        Assert.assertTrue(OCMThreshold.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}
