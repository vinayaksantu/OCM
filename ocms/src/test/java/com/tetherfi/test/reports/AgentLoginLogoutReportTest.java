
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
import com.tetherfi.pages.AgentLoginLogoutReportPage;
import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.FTPServer;

public class AgentLoginLogoutReportTest extends BaseTest {
	
	String destinationFilePath = "C:/Users/Admin/git/scripts/ocms/src/test/resources/DownloadedFiles/Json/OCMAgentLoginLogoutReport.json";
    String remoteFilePath = "\\\\172.16.2.61\\c$\\Products\\OCM\\UI\\Configurations\\Reports\\OCMAgentLoginLogoutReport.json";

	
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToOcmReportsPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOcmIconImg();
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        Assert.assertTrue(ocmReportsPage.isOCMReportPageIsDisplayed());       
      FTPServer ftp=new FTPServer();
       ftp.transferFileFromRemote(remoteFilePath,destinationFilePath);
    }
 
    @Test(priority=1)
    public void ShowAgentLoginLogoutReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");      
    }      
    @Test(priority=2, dependsOnMethods ="ShowAgentLoginLogoutReport")
    public void ShowOcmAgentLoginLogoutReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }  
    @Test(priority=3)
    public void ScheduleAgentLoginLogoutReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    @Test(priority=4,dependsOnMethods ="ShowOcmAgentLoginLogoutReportInNewTab")
    public void ExportOcmAgentLoginLogoutReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
   @Test(priority=5,dependsOnMethods ="ExportOcmAgentLoginLogoutReport")
    public void ViewDownloadedOcmAgentLoginLogoutReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }   
   @Test(priority=6)
   public void ShowOcmAgentLoginLogoutReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   }
   
   @Test(priority=7,dependsOnMethods ="ShowOcmAgentLoginLogoutReportForDateRange")
   public void ShowOcmAgentLoginLogoutReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }    
    @Test(priority=8,dependsOnMethods ="ShowOcmAgentLoginLogoutReportInNewTabDateRange")
    public void ExportOcmAgentLoginLogoutReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }    
    @Test(priority=9,dependsOnMethods ="ExportOcmAgentLoginLogoutReportDateRange")
    public void ViewDownloadedOcmAgentLoginLogoutReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    @Test(priority=10)
    public void ScheduleAgentLoginLogoutReportforDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentAuxReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    @Test(priority=11)
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.ClearHomepgDrpDown(reportDetails);
    }
    @Test(priority=12)
   	public void OCMWindow() throws Exception {  	
   	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
	    Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map);
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
	    ocmReportsPage.showReport(reportDetails);        
	    AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);                   
	    Assert.assertTrue(agntloginlogoutPage.maximizewindow(),"Fullscreen Assertion Failed"); 
       	screenshot.captureScreen(driver,"AgentLoginLogoutReport","Maximize");
       	Assert.assertTrue(agntloginlogoutPage.minimizewindow(), "Restored Assertion Failed");
       	screenshot.captureScreen(driver,"AgentLoginLogoutReport","Minimize");	
       }
   	@Test(priority=13)
    public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
		Assert.assertTrue(agntloginlogoutPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }   
    @Test(priority=14)
    public void VerifyColumnsHeaderEnable() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
		Assert.assertTrue(agntloginlogoutPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }  
    @Test(priority=15)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        Assert.assertFalse(agntloginlogoutPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }  
    @Test(priority=16)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {  
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	Assert.assertTrue(agntloginlogoutPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }   
    @Test(priority=17)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        Assert.assertTrue(agntloginlogoutPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    } 
    @Test(priority=18)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        Assert.assertTrue(agntloginlogoutPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }   
    @Test(priority=19)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        Assert.assertTrue(agntloginlogoutPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }    
    @Test(priority=20)
    public void ExportPage() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        Assert.assertTrue(agntloginlogoutPage.verifyExportToExcel(filePath1));
    }
    @Test(priority=21)
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        agntloginlogoutPage.SortByAscending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentLoginLogoutReport (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(agntloginlogoutPage.verifyexportToExcelSheet(maplist));
    }  
    @Test(priority=22)
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        agntloginlogoutPage.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentLoginLogoutReport (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(agntloginlogoutPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=23)
    public void SchedulereportinAgentLoginLogoutReportPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);            
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        agntloginlogoutPage.navigateToExportSchedulerPage();    
        Assert.assertTrue(agntloginlogoutPage.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("AgentLoginLogoutReportTest","ExportSchedulerPage");
    }
    @Test(priority=24)
    public void ExportToExcelForAgentloginReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        agntloginlogoutPage.exportToExcel();
        Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
    }
    @Test(priority=25,dependsOnMethods ="ExportToExcelForAgentloginReport")
    public void ViewDownloadedOcmAgentLoginLogoutReportInReportsDownloadPageinAgentLoginLogoutPg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);  
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        agntloginlogoutPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");   
    }           
    @Test(priority=26)//has one error
    public void VerifySearchByFeatureForAgentLoginLogoutReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByTextbox());
    }
    @Test(priority=27)//has an error
    public void VerifySearchFeatureForAgentLoginLogoutReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchByColumnValue());
    }
    @Test(priority=28)//has the same error
    public void VerifySearchContainsForAgentLoginLogoutReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(ocmReportsPage.verifySearchContainsColumnValue());
    }   
    @Test(priority=29)
    public void verifyAdvancedSearchinreportpage() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map);
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
         ocmReportsPage.showReport(reportDetails);
         AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
         Assert.assertTrue(agntloginlogoutPage.verifyAdvanceSearch(reportDetails));
    }
    @Test(priority=31)
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
      	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);
      	AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
      	agntloginlogoutPage.searchwithoutextsearch(reportDetails);
        Assert.assertEquals(agntloginlogoutPage.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    @Test(priority=32)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	Assert.assertTrue(agntloginlogoutPage.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
    }   
    @Test(priority=33)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	Assert.assertTrue(agntloginlogoutPage.verifySearchContains(reportDetails.getSearchStr()));
    }   
    @Test(priority=34)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	Assert.assertTrue(agntloginlogoutPage.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
    	}  
    @Test(priority=35)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	Assert.assertTrue(agntloginlogoutPage.verifySearchStartsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=36)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	Assert.assertTrue(agntloginlogoutPage.verifySearchEndsWith(reportDetails.getSearchStr()));
    }
    @Test(priority=37)
    public void SearchClearSearch() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
        agntloginlogoutPage.verifySearchClear(reportDetails);    	
    }  
    @Test(priority=38)
    public void VerifyJsonDataForColumnNames() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
    	AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	agntloginlogoutPage.verifycolumnsHeaderEnabled();
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(agntloginlogoutPage.verifyJsonDataForgridColumnHidden(json.getJsonGridColumnTitleKeyDataForReports("Hidden")),"JSON data grid column hidden assertion failed");  	
    }
    
    @Test(priority=39)
    public void database() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentLoginLogoutReportData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
    	ReportDetails reportDetails= new ReportDetails(map); 
    	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);
    	AgentLoginLogoutReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentLoginLogoutReportPage.class);
    	Assert.assertTrue(agntloginlogoutPage.verifyDatabase(reportDetails.getQuery(),reportDetails));
    }

    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}

