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
import com.tetherfi.model.user.DashboardColorCodeConfigDetails;
import com.tetherfi.pages.DashboardColorCodeConfigPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.OCMChatInteractionReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class OCMChatInteractionReportTest extends BaseTest {
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
    public void ShowOCMChatinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
    }      
    @Test(priority=2, dependsOnMethods ="ShowOCMChatinteractionReport")
    public void ShowOcmChatinteractionReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }  
    @Test(priority=3)
    public void ScheduleOCMChatinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }   
    @Test(priority=4,dependsOnMethods ="ShowOcmChatinteractionReportInNewTab")
    public void ExportOcmChatinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
   @Test(priority=5,dependsOnMethods ="ExportOcmChatinteractionReport")
    public void ViewDownloadedOcmChatinteractionReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }    
   @Test(priority=6)
   public void ShowOcmChatinteractionReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   } 
   @Test(priority=7,dependsOnMethods ="ShowOcmChatinteractionReportForDateRange")
   public void ShowOcmChatinteractionReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }   
    @Test(priority=8,dependsOnMethods ="ShowOcmChatinteractionReportInNewTabDateRange")
    public void ExportOcmChatinteractionReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    } 
    @Test(priority=9,dependsOnMethods ="ExportOcmChatinteractionReportDateRange")
    public void ViewDownloadedOcmChatinteractionReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    @Test(priority=10)
    public void ScheduleOcmChatinteractionReportforDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    @Test(priority=11)
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.ClearHomepgDrpDown(reportDetails);
    }
       @Test(priority=12)
	public void OCMWindow() throws Exception {	
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);             
        Assert.assertTrue(chatintactnpage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMChatinteractionReport","Maximize");
    	Assert.assertTrue(chatintactnpage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMChatinteractionReport","Minimize");	
    } 
	@Test(priority=13)
    public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(chatintactnpage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }   
    @Test(priority=14)
    public void VerifyColumnsHeaderEnable() throws Exception {  	
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
		Assert.assertTrue(chatintactnpage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }  
    @Test(priority=15)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        Assert.assertFalse(chatintactnpage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    } 
    @Test(priority=16)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
    	Assert.assertTrue(chatintactnpage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }    
    @Test(priority=17)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        Assert.assertTrue(chatintactnpage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }  
    @Test(priority=18)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        Assert.assertTrue(chatintactnpage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }  
    @Test(priority=19)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        Assert.assertTrue(chatintactnpage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }  
    @Test(priority=20)
    public void ExportPage() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        Assert.assertTrue(chatintactnpage.verifyExportToExcel(filePath1));
    }
    @Test(priority=21)
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        chatintactnpage.SortByAscending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMChatinteractionReport (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(chatintactnpage.verifyexportToExcelSheet(maplist));
    }    
    @Test(priority=22)
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);        
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        chatintactnpage.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMChatinteractionReport (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(chatintactnpage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=23)
    public void Schedulereportinchatintactnpage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);       
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        chatintactnpage.navigateToExportSchedulerPage();    
        Assert.assertTrue(chatintactnpage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("OCMChatinteractionReportTest","ExportSchedulerPage");    	 
    }
    @Test(priority=24)
    public void ExportToExcelForChatinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        OCMChatInteractionReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        agntloginlogoutPage.exportToExcel();
        Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
    }   
    @Test(priority=25,dependsOnMethods ="ExportToExcelForChatinteractionReport")
    public void ViewDownloadedOcmChatinteractionReportInReportsDownloadPageinChatinteractionPg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);  
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        chatintactnpage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
    }       
    @Test(priority=26)
    public void VerifySearchByFeatureForChatinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
        Assert.assertTrue(chatintactnpage.verifySearchByTextbox(reportDetails));
    }
      @Test(priority=27)
    public void VerifySearchFeatureForThresoldReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(chatintactnpage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
    }     
    @Test(priority=28)
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
      	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);
      	OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
      	chatintactnpage.searchwithoutextsearch(reportDetails);
        Assert.assertEquals(chatintactnpage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }  
    @Test(priority=29)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
    	Assert.assertTrue(chatintactnpage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
    }   
   @Test(priority=30)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
    	Assert.assertTrue(chatintactnpage.verifySearchContains(reportDetails.getSearchStr()));
    }   
    @Test(priority=31)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
    	Assert.assertTrue(chatintactnpage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
    	}    
    @Test(priority=32)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
    	Assert.assertTrue(chatintactnpage.verifySearchStartsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=33)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
    	Assert.assertTrue(chatintactnpage.verifySearchEndsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=34)
  public void SearchClearSearch() throws Exception{
  	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
      Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
      ReportDetails reportDetails= new ReportDetails(map);
      OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	ocmReportsPage.showReport(reportDetails);  
      OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
      chatintactnpage.verifySearchClear(reportDetails);    	
  }
    @Test(priority=35)
    public void verifyAdvancedSearchinreportpage() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map);
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
         OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);        
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(chatintactnpage.verifyAdvanceSearch(reportDetails));            
    }
    @Test(priority=36)
    public void ClearfiltersAdvSrch() throws Exception{ 	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
        ocmReportsPage.ClearAdvFilters(reportDetails);
    } 
    
   @Test(priority=37)
    public void GroupBy() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
	  Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
	  ReportDetails reportDetails= new ReportDetails(map);
	  OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
	  ocmReportsPage.showReport(reportDetails);
	  OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
  	  Assert.assertTrue(chatintactnpage.groupby());
  	  screenshot.captureScreen("OCMChatinteractionReport", "GroupBy");
  	  Assert.assertTrue(chatintactnpage.groupby());
      screenshot.captureScreen("OCMChatinteractionReport", "AlreadyGroupBy");
    }
    
   /*@Test(priority=38)
    public void database() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatInteractionReportData.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
   		ReportDetails reportDetails= new ReportDetails(map);
   		OCMChatInteractionReportPage chatintactnpage=PageFactory.createPageInstance(driver,OCMChatInteractionReportPage.class);
   		Assert.assertTrue(chatintactnpage.verifyDatabase(reportDetails.getQuery()));
   }*/

    
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}
