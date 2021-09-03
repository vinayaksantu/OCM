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
import java.util.Map;

public class AuditTrailReportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	@BeforeMethod
	public void NavigateToOcmReportsPage() {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMIconImg();
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
	}

	/*@Test(priority=1)
	public void ShowAuditTrailReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");
	}
	
	@Test(priority=2,dependsOnMethods ="ShowAuditTrailReport")
	public void ShowAuditTrailReportInNewTab() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	@Test(priority=3)
	public void ScheduleAuditTrailReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.scheduleReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
	}

	@Test(priority=4,dependsOnMethods ="ShowAuditTrailReportInNewTab")
	public void ExportAuditTrailReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=5,dependsOnMethods ="ExportAuditTrailReport")
	public void ViewDownloadedAuditTrailReportInNotificationPan() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInNotificationTab();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameInNotificationPan(reportDetails.getReportName()),"Reporter name not found in notification tab");
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportGeneratedTime(),"Reporter date not found in notification tab");
	}
	
	@Test(priority=6,dependsOnMethods ="ExportAuditTrailReport")
	public void ViewDownloadedAuditTrailReportInReportsDownloadPage() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}*/
	
	@Test(priority=7)
	public void ShowAuditTrailReportForDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
	}
	
	@Test(priority=8,dependsOnMethods ="ShowAuditTrailReportForDateRange")
	public void ShowAuditTrailReportInNewTabDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReportInNewPage(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
		ocmReportsPage.switchBackToParentWindow();
	}
	
	@Test(priority=9,dependsOnMethods ="ShowAuditTrailReportInNewTabDateRange")
	public void ExportAuditTrailReportDateRange() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.exportReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
	}
	
	@Test(priority=10,dependsOnMethods ="ExportAuditTrailReportDateRange")
	public void ViewDownloadedAuditTrailReportInNotificationPanDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInNotificationTab();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameInNotificationPan(reportDetails.getReportName()),"Reporter name not found in notification tab");
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportGeneratedTime(),"Reporter date not found in notification tab");
	}
	
	@Test(priority=11,dependsOnMethods ="ExportAuditTrailReportDateRange")
	public void ViewDownloadedAuditTrailReportInReportsDownloadPageDateRange() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	
	@Test(priority=12,dependsOnMethods ="ShowAuditTrailReportForDateRange")
	public void ExportPageForAuditTrailReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		ocmReportsPage.exportPage();
		Assert.assertTrue(ocmReportsPage.verifyExportPageFileDownloaded("Audit Trail Report.xlsx"),"Export page assertion failed");
	}
	
	@Test(priority=13)//,dependsOnMethods ="ShowAuditTrailReport")
	public void ExportToCsvForAuditTrailReport() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
		ocmReportsPage.exportToCSV();
		Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
		ocmReportsPage.viewDownloadedReports();
		Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
	}
	@Test(priority=14)//,dependsOnMethods ="ShowAuditTrailReport")
    public void ScheduledReportsForAuditTrailReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        ocmReportsPage.scheduledReports();
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Scheduled report at reports page assertion failed");
    }
	
    @Test(priority=15,dependsOnMethods ="ShowAuditTrailReportForDateRange")
    public void VerifySortingForAuditTrailReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySorting());
    }
    
    @Test(priority=16,dependsOnMethods ="ShowAuditTrailReportForDateRange")
    public void VerifySearchByFeatureForAuditTrailReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByTextbox());
    }
    
    @Test(priority=17,dependsOnMethods ="ShowAuditTrailReportForDateRange")
    public void VerifySearchFeatureForAuditTrailReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByColumnValue());
    }
    
    @Test(priority=18,dependsOnMethods ="ShowAuditTrailReportForDateRange")
    public void VerifySearchContainsForAuditTrailReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchContainsColumnValue());
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen("AuditTrailReportTest",method.getName());
		driver.navigate().refresh();
    }
}
