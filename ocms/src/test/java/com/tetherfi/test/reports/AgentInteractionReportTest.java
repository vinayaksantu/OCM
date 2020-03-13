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
import com.tetherfi.pages.AgentInteractionReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentInteractionReportTest extends BaseTest {
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
    public void ShowOCMAgentinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
    }      
    @Test(priority=2, dependsOnMethods ="ShowOCMAgentinteractionReport")
    public void ShowOcmAgentinteractionReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }  
    @Test(priority=3)
    public void ScheduleOCMAgentinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }   
    @Test(priority=4,dependsOnMethods ="ShowOcmAgentinteractionReportInNewTab")
    public void ExportOcmAgentinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
   @Test(priority=5,dependsOnMethods ="ExportOcmAgentinteractionReport")
    public void ViewDownloadedOcmAgentinteractionReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
    }    
   @Test(priority=6)
   public void ShowOcmAgentinteractionReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   } 
   @Test(priority=7,dependsOnMethods ="ShowOcmAgentinteractionReportForDateRange")
   public void ShowOcmAgentinteractionReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }   
    @Test(priority=8,dependsOnMethods ="ShowOcmAgentinteractionReportInNewTabDateRange")
    public void ExportOcmAgentinteractionReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    } 
    @Test(priority=9,dependsOnMethods ="ExportOcmAgentinteractionReportDateRange")
    public void ViewDownloadedOcmAgentinteractionReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");
    }
    @Test(priority=10)
    public void ScheduleOcmAgentinteractionReportforDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    @Test(priority=11)
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.ClearHomepgDrpDown(reportDetails);
    }
       @Test(priority=12)
	public void OCMWindow() throws Exception {	
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);             
        Assert.assertTrue(Agentintactnpage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMAgentinteractionReport","Maximize");
    	Assert.assertTrue(Agentintactnpage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMAgentinteractionReport","Minimize");	
    } 
	@Test(priority=13)
    public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
		Assert.assertTrue(Agentintactnpage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }   
    @Test(priority=14)
    public void VerifyColumnsHeaderEnable() throws Exception {  	
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
		Assert.assertTrue(Agentintactnpage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }  
    @Test(priority=15)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Assert.assertFalse(Agentintactnpage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    } 
    @Test(priority=16)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
    	Assert.assertTrue(Agentintactnpage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }    
    @Test(priority=17)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Assert.assertTrue(Agentintactnpage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }  
    @Test(priority=18)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Assert.assertTrue(Agentintactnpage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }  
    @Test(priority=19)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Assert.assertTrue(Agentintactnpage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }  
    @Test(priority=20)
    public void ExportPage() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Assert.assertTrue(Agentintactnpage.verifyExportToExcel(filePath1));
    }
    @Test(priority=21)
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Agentintactnpage.SortByAscending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentInteractionReport (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(Agentintactnpage.verifyexportToExcelSheet(maplist));
    }    
    @Test(priority=22)
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);        
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Agentintactnpage.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentInteractionReport (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(Agentintactnpage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=23)
    public void SchedulereportinAgentintactnpage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);       
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Agentintactnpage.navigateToExportSchedulerPage();    
        Assert.assertTrue(Agentintactnpage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("OCMAgentinteractionReportTest","ExportSchedulerPage");    	 
    }
    @Test(priority=24)
    public void ExportToExcelForAgentinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        AgentInteractionReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        agntloginlogoutPage.exportToExcel();
        Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
    }   
    @Test(priority=25,dependsOnMethods ="ExportToExcelForAgentinteractionReport")
    public void ViewDownloadedOcmAgentinteractionReportInReportsDownloadPageinAgentinteractionPg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);  
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Agentintactnpage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName1()),"Report not found in Reporter download page");    
    }    
    @Test(priority=26)
    public void VerifySearchByFeatureForAgentinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
        Assert.assertTrue(Agentintactnpage.verifySearchByTextbox(reportDetails));
    }
     @Test(priority=27)
    public void VerifySearchFeatureForAgentinteractionReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(Agentintactnpage.verifySearchIsEqualTo(reportDetails.getSearchStr()));
    }   
    @Test(priority=28)
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
      	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);
      	AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
      	Agentintactnpage.searchwithoutextsearch(reportDetails);
        Assert.assertEquals(Agentintactnpage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }  
    
    @Test(priority=29)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
    	Assert.assertTrue(Agentintactnpage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
    }   
   @Test(priority=30)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
    	Assert.assertTrue(Agentintactnpage.verifySearchContains(reportDetails.getSearchStr()));
    }   
    @Test(priority=31)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
    	Assert.assertTrue(Agentintactnpage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
    	}    
    @Test(priority=32)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
    	Assert.assertTrue(Agentintactnpage.verifySearchStartsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=33)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
    	Assert.assertTrue(Agentintactnpage.verifySearchEndsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=34)
  public void SearchClearSearch() throws Exception{
  	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
      Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
      ReportDetails reportDetails= new ReportDetails(map);
      OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	ocmReportsPage.showReport(reportDetails);  
      AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
      Agentintactnpage.verifySearchClear(reportDetails);    	
  }
    @Test(priority=35)
    public void verifyAdvancedSearchinreportpage() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map);
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
         AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);        
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(Agentintactnpage.verifyAdvanceSearch(reportDetails));            
    }
    @Test(priority=36)
    public void ClearfiltersAdvSrch() throws Exception{ 	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
        ocmReportsPage.ClearAdvFilters(reportDetails);
    }   
   @Test(priority=37)
    public void GroupBy() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
	  Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
	  ReportDetails reportDetails= new ReportDetails(map);
	  OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
	  ocmReportsPage.showReport(reportDetails);
	  AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
  	  Assert.assertTrue(Agentintactnpage.groupby());
  	  screenshot.captureScreen("OCMAgentinteractionReport", "GroupBy");
  	  Assert.assertTrue(Agentintactnpage.groupby());
      screenshot.captureScreen("OCMAgentinteractionReport", "AlreadyGroupBy");
    }
    
   /*@Test(priority=38)
    public void database() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentInteractionReportData.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
   		ReportDetails reportDetails= new ReportDetails(map);
   		AgentInteractionReportPage Agentintactnpage=PageFactory.createPageInstance(driver,AgentInteractionReportPage.class);
   		Assert.assertTrue(Agentintactnpage.verifyDatabase(reportDetails.getQuery()));
   }*/

    
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}
