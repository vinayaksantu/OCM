package com.tetherfi.test.reports;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMChatbotInteractionReportPage;
import com.tetherfi.pages.OCMReportsPage;

import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMChatbotInteractionreportTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToOcmReportsPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMIconImg();
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());
    }
  
    @Test(priority=1,description="To verify Show Report for Single Date")
    public void ShowOCMChatbotInteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");
        
    }
         
    @Test(priority=2, dependsOnMethods ="ShowOCMChatbotInteractionReport")
    public void ShowChatbotInteractionReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }
   
    @Test(priority=3,description="To verify Export Scheduler on OCM Reports Page")
    public void ScheduleOCMChatbotInteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
   
    @Test(priority=4,description="To verify Export Report on OCM Reports Page")
    public void ExportChatbotInteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
  
    @Test(priority=5,dependsOnMethods ="ExportChatbotInteractionReport",description="To verify view download in Report downloads")
    public void ViewDownloadedChatbotInteractionReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    
    @Test(priority=6)//,dependsOnMethods ="ViewDownloadedChatbotInteractionReportInReportsDownloadPage",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmChatbotInteractionReportInReportsDownloadPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Chatbot Interaction Re"));	
	}
       
    @Test(priority=7,description="To verify Show Report for Date Range")
   public void ShowChatbotInteractionReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   }
   
    @Test(priority=8,description="To verify Show Report for Date Range in New Tab")
   public void ShowChatbotInteractionReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }
     
    @Test(priority=9,description="To verify Export Report on OCM Reports Page for Date Range")
    public void ExportChatbotInteractionReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
    
    @Test(priority=10,description="To verify Export Scheduler on OCM Reports Page for Date Range")
    public void ScheduleChatbotInteractionReportforDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    
    @Test(priority=11,dependsOnMethods ="ExportChatbotInteractionReportDateRange",description="To verification of exported excel in Report downloads for Date Range")
    public void ViewDownloadedChatbotInteractionReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    
    @Test(priority=12,dependsOnMethods ="ViewDownloadedChatbotInteractionReportInReportsDownloadPageDateRange",description="To verification of exported excel in Report downloads")
	public void VerifyViewDownloadedOcmChatbotInteractionReportInReportsDownloadDateRangePage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Chatbot Interaction Re"));	
	} 
    
    @Test(priority=13,description="Delete record in Reports Download without Delete reason")
    public void DeleteWithoutDeleteReasonRecordinReportsDownload() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();   	
		ocmReportsPage.deleteWithoutDeleteReason(reportDetails);
    	Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=14,description="Cancel Button in Reports Download Delete Button")
	public void VerifyCancelBtnAtReportsDownload() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deletecancelButton(reportDetails);
		Assert.assertFalse(ocmReportsPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
	}
    
    @Test(priority=15,description="Delete Record at Reports download Button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.viewDownloadedReportInReportDownloadsPage();
		ocmReportsPage.deleteRecordAtReportsDownloadsPage(reportDetails);
		Assert.assertEquals(ocmReportsPage.getSuccessMessage(),"Report Deleted","Delete record assertion failed");
	}
    
    @Test(priority=16,description="To verfiy clear all button")
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.ClearHomepgDrpDown(reportDetails);
    }
     
    @Test(priority=17,description="To Verify OCM Window Maximize minimize")
	public void OCMWindow() throws Exception {
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);      
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);             
        Assert.assertTrue(ChatbotInteractionPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMChatbotInteractionReport","Maximize");
    	Assert.assertTrue(ChatbotInteractionPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMChatbotInteractionReport","Minimize");	
    }
	
    @Test(priority=18,description="To Verify Dropdown for All the Columns")
    public void VerifyDropdownForAllTheColumns() throws Exception {	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=19,description="To Verify Columns Headers are Enabled")
    public void VerifyColumnsHeaderEnable() throws Exception { 	
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);	
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=20,description="To Verify Columns Headers are Disabled")
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        Assert.assertFalse(ChatbotInteractionPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
   
    @Test(priority=21,description="To Verify Arrow move for Previous and Next page")
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	Assert.assertTrue(ChatbotInteractionPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=22,description="To Verify Arrow move for first and last page")
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        Assert.assertTrue(ChatbotInteractionPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=23,description="To Verify Total Number of Items Per Page Details")
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        Assert.assertTrue(ChatbotInteractionPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=24,description="To Verify Number of Items Per Page Selection")
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        Assert.assertTrue(ChatbotInteractionPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=25,description="To Verify Export Page Button")
    public void ExportPage() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        Assert.assertTrue(ChatbotInteractionPage.verifyExportToExcel(filePath1));
    }
    
    @Test(priority=26,dependsOnMethods="ExportPage",description="To Verify Exported Page Against UI")
	public void VerifyExportedPage() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails); 
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMChatbotInteractionReport.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
		OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifyexportToExcelSheet(maplist));
	}
       
    @Test(priority=27,description="To Verify Schedule Report button on Main Page")
    public void SchedulereportinChatbotInteractionPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);         
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        ChatbotInteractionPage.navigateToExportSchedulerPage();    
        Assert.assertTrue(ChatbotInteractionPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("OCMChatbotInteractionReportTest","ExportSchedulerPage");
    	Assert.assertTrue(ChatbotInteractionPage.VerifyLogo(),"Logo assertion failed");
    }
     
    @Test(priority=28,description="To Verify Export Excel button on Main Page")
     public void ExportToExcelForChatbotInteractionReport() throws Exception {
         String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map);
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
         OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
         ChatbotInteractionPage.exportToExcel();
         Assert.assertTrue(ChatbotInteractionPage.verifyReportExported(),"export report assertion failed");
     }
    
    @Test(priority=29,dependsOnMethods ="ExportToExcelForChatbotInteractionReport",description="To Verify View Download button on Main Page")
    public void ViewDownloadedChatbotInteractionReportInReportsDownloadPageinChatbotInteractionPg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        ChatbotInteractionPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    
    }
    
    @Test(priority=30,dependsOnMethods ="ViewDownloadedChatbotInteractionReportInReportsDownloadPageinChatbotInteractionPg",description="Verification of exported excel from main page in Report downloads")
	public void VerifyViewDownloadedOcmChatbotInteractionReportInReportsDownload() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyExportedSheet("OCMReportDownload","OCM Chatbot Interaction Re"));	
	}
    
    @Test(priority=31,description="To verify search by feature")
    public void VerifySearchByFeatureForChatbotInteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
        Assert.assertTrue(ChatbotInteractionPage.verifySearchByTextbox(reportDetails));
    }
    
    @Test(priority=32,description="To verify search equals")
    public void VerifySearchEqualsFeatureForChatbotInteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ChatbotInteractionPage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
    }   
    
    @Test(priority=33,description="To verify search without providing data in searchbox")
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
      	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);
      	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
      	ChatbotInteractionPage.searchwithoutextsearch(reportDetails);
        Assert.assertEquals(ChatbotInteractionPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    } 
    
     @Test(priority=34,description="To verify search IsNotEquals")
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	Assert.assertTrue(ChatbotInteractionPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
    } 
      
   @Test(priority=35,description="To verify search Contains")
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	Assert.assertTrue(ChatbotInteractionPage.verifySearchContains(reportDetails.getSearchStr()));
    }   
    
    @Test(priority=36,description="To verify search doesnotContains")
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	Assert.assertTrue(ChatbotInteractionPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
    	}    
    	
    @Test(priority=37,description="To verify search StartsWith")
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	Assert.assertTrue(ChatbotInteractionPage.verifySearchStartsWith(reportDetails.getSearchStr()));
    }
    
  @Test(priority=38,description="To verify search EndsWith")
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(4);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	Assert.assertTrue(ChatbotInteractionPage.verifySearchEndsWith(reportDetails.getSearchStr()));
    }
    
  @Test(priority=39,description="To verify search Clear Search")
  public void SearchClearSearch() throws Exception{
  	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
      Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
      ReportDetails reportDetails= new ReportDetails(map);
      OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      ocmReportsPage.showReport(reportDetails);  
      OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
      ChatbotInteractionPage.verifySearchClear(reportDetails);    	
  }
  
    @Test(priority=40,description="To verify Advance Search Equals")
    public void verifyAdvancedSearchinreportpage() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map);
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
         OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);        
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(ChatbotInteractionPage.verifyAdvanceSearch(reportDetails));            
    }
    
    @Test(priority=41)
    public void ClearfiltersAdvSrch() throws Exception{ 	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
        ocmReportsPage.ClearAdvFilters(reportDetails);
    }  
    
    @Test(priority=42)
    public void GroupBy() throws Exception{
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
	   Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
	   ReportDetails reportDetails= new ReportDetails(map);
	   OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);   	
	   ocmReportsPage.showReport(reportDetails);
	   OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
	   Assert.assertTrue(ChatbotInteractionPage.groupby());
	   screenshot.captureScreen("OCMChatbotInteractionReport", "GroupBy");
	   Assert.assertTrue(ChatbotInteractionPage.groupby());
	   screenshot.captureScreen("OCMChatbotInteractionReport", "AlreadyGroupBy");
    }
    
    @Test(priority=43)
    public void verifyAdvancedSearchAndCriteria() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	 OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
  	    ocmReportsPage.chooseReport(reportDetails);
    	Assert.assertTrue(ChatbotInteractionPage.advancedSearchAndCriteria(reportDetails));   	
    }
    
    @Test(priority=44)
    public void verifyAdvancedSearchORCriteria() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
    	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
  	    ocmReportsPage.chooseReport(reportDetails);
    	Assert.assertTrue(ChatbotInteractionPage.advancedSearchORCriteria(reportDetails));   	
    }
    
    @Test(priority=45,description="To verify Advance Search Not Equal To")
    public void verifyAdvancedSearchinreportpageSearchNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(1);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
    	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifyAdvanceSearchNotEqualsTo(reportDetails)); 
    }
    
    @Test(priority=46,description="To verify Advance Search Contains")
    public void verifyAdvancedSearchinreportpageSearchcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(2);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
    	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifyAdvanceSearchContains(reportDetails)); 
    }
    
    @Test(priority=47,description="To verify Advance Search DoesNot Contains")
    public void verifyAdvancedSearchinreportpageSearchDoesnotcontains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(3);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
    	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifyAdvanceSearchDoesNotContains(reportDetails)); 
     }

    @Test(priority=48,description="To verify Advance Search Starts With")
    public void verifyAdvancedSearchinreportpageSearchStartswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(4);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
    	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifyAdvanceSearchStartsWith(reportDetails)); 
     }
    
    @Test(priority=49,description="To verify Advance Search Ends With")
    public void verifyAdvancedSearchinreportpageSearchEndswith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(5);
    	ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage OCMReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   	
    	OCMReportsPage.showReport(reportDetails);
    	OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifyAdvanceSearchEndsWith(reportDetails)); 
     }
    
    @Test(priority=50,description="To Verify Ascending and Descending order")
	public void VerifySorting() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
		OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
		Assert.assertTrue(ChatbotInteractionPage.verifySorting(),"item per page assertion failed");
	}
    
   /* @Test(priority=38)
    public void database() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OCMChatbotInteractionReport.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
   		ReportDetails reportDetails= new ReportDetails(map);
   		OCMChatbotInteractionReportPage ChatbotInteractionPage=PageFactory.createPageInstance(driver,OCMChatbotInteractionReportPage.class);
   		Assert.assertTrue(ChatbotInteractionPage.verifyDatabase(reportDetails.getQuery()));
   }*/
      
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    	driver.navigate().refresh();
    }
}
