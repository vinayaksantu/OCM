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
import com.tetherfi.pages.AgentHistoricalReportPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentHistoricalReportTest extends BaseTest {
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
    public void ShowOCMAgentHistoricalReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"Show report assertion failed");     
    }      
    @Test(priority=2, dependsOnMethods ="ShowOCMAgentHistoricalReport")
    public void ShowOcmAgentHistoricalReportInNewTab() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowInNewPage").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReportInNewPage(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportDisplayed(reportDetails),"show report in new tab assertion failed");
        ocmReportsPage.switchBackToParentWindow();
    }  
    @Test(priority=3)
    public void ScheduleOCMAgentHistoricalReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }   
    @Test(priority=4,dependsOnMethods ="ShowOcmAgentHistoricalReportInNewTab")
    public void ExportOcmAgentHistoricalReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    }
   @Test(priority=5,dependsOnMethods ="ExportOcmAgentHistoricalReport")
    public void ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPage() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }    
   @Test(priority=6)
   public void ShowOcmAgentHistoricalReportForDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReport(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
   } 
   @Test(priority=7,dependsOnMethods ="ShowOcmAgentHistoricalReportForDateRange")
   public void ShowOcmAgentHistoricalReportInNewTabDateRange() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"ShowInNewPageDateRange").getTestData().get(0);
       ReportDetails reportDetails= new ReportDetails(map);
       OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
       ocmReportsPage.showReportInNewPage(reportDetails);
       Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"show report in new tab assertion failed");
       ocmReportsPage.switchBackToParentWindow();
   }   
    @Test(priority=8,dependsOnMethods ="ShowOcmAgentHistoricalReportInNewTabDateRange")
    public void ExportOcmAgentHistoricalReportDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.exportReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyReportExported(),"export report assertion failed");
    } 
    @Test(priority=9,dependsOnMethods ="ExportOcmAgentHistoricalReportDateRange")
    public void ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPageDateRange() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");
    }
    @Test(priority=10)
    public void ScheduleOcmAgentHistoricalReportforDateRange() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReportDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.scheduleReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyScheduleReport(),"Schedule report assertion failed");
    }
    @Test(priority=11)
    public void ClearAll() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
    	OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.ClearHomepgDrpDown(reportDetails);
    }
       @Test(priority=12)
	public void OCMWindow() throws Exception {	
	    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);             
        Assert.assertTrue(agnthistpg.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver,"OCMAgentHistoricalReport","Maximize");
    	Assert.assertTrue(agnthistpg.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver,"OCMAgentHistoricalReport","Minimize");	
    } 
	@Test(priority=13)
    public void VerifyDropdownForAllTheColumns() throws Exception {		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);		
		AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agnthistpg.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }   
    @Test(priority=14)
    public void VerifyColumnsHeaderEnable() throws Exception {  	
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);		
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);	
		AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
		Assert.assertTrue(agnthistpg.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }  
    @Test(priority=15)
    public void VerifyColumnsHeaderDisable() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        Assert.assertFalse(agnthistpg.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    } 
    @Test(priority=16)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
    	Assert.assertTrue(agnthistpg.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }    
    @Test(priority=17)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        Assert.assertTrue(agnthistpg.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }  
    @Test(priority=18)
    public void VerifyTotalNumberOfItemsPerPageDetails() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        Assert.assertTrue(agnthistpg.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }  
    @Test(priority=19)
    public void VerifyNumberOfItemsPerPageSelection() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        Assert.assertTrue(agnthistpg.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }  
    @Test(priority=20)
    public void ExportPage() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);    	
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        Assert.assertTrue(agnthistpg.verifyExportToExcel(filePath1));
    }
    @Test(priority=21)
    public void SortingByAscending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        agnthistpg.SortByAscending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentHistoricalReport (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(agnthistpg.verifyexportToExcelSheet(maplist));
    }    
    @Test(priority=22)
    public void SortingByDescending() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);        
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        agnthistpg.SortByDescending();
    	String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\OCMAgentHistoricalReport (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath1,"Sheet1").getTestData();
        Assert.assertTrue(agnthistpg.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=23)
    public void Schedulereportinagnthistpg() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);       
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        agnthistpg.navigateToExportSchedulerPage();    
        Assert.assertTrue(agnthistpg.isExportSchedulerPageDisplayed(), "ExportScheduler page assertion failed");
    	screenshot.captureScreen("OCMAgentHistoricalReportTest","ExportSchedulerPage");    	 
    }
    @Test(priority=24)
    public void ExportToExcelForAgentHistoricalReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        AgentHistoricalReportPage agntloginlogoutPage=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        agntloginlogoutPage.exportToExcel();
        Assert.assertTrue(agntloginlogoutPage.verifyReportExported(),"export report assertion failed");
    }   
    @Test(priority=25,dependsOnMethods ="ExportToExcelForAgentHistoricalReport")
    public void ViewDownloadedOcmAgentHistoricalReportInReportsDownloadPageinAgentHistoricalPg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ExportReport").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);  
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        agnthistpg.viewDownloadedReportInReportsDownloadsPage();
        Assert.assertTrue(ocmReportsPage.verifyDownloadedReportNameAndTimeInReportsDownloadPage(reportDetails.getReportName()),"Report not found in Reporter download page");    
    }       
    @Test(priority=26)
    public void VerifySearchByFeatureForAgentHistoricalReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");     
        Assert.assertTrue(agnthistpg.verifySearchByTextbox(reportDetails));
    }
      @Test(priority=27)
    public void VerifySearchFeatureForThresoldReport() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);
        ocmReportsPage.showReport(reportDetails);
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
        Assert.assertTrue(ocmReportsPage.verifyDateRangeReportDisplayed(reportDetails),"Show report assertion failed");
        Assert.assertTrue(agnthistpg.verifySearchIsEqualTo(reportDetails.getSearchStr()));
    }     
    @Test(priority=28)
    public void searchwithoutSearchTextbox() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
      	OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);
      	AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
      	agnthistpg.searchwithoutextsearch(reportDetails);
        Assert.assertEquals(agnthistpg.getSuccessMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }  
    @Test(priority=29)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(1);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
    	Assert.assertTrue(agnthistpg.verifySearchIsNotEqualTo(reportDetails.getSearchStr()));
    }   
   @Test(priority=30)
    public void  VerifySearchContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
    	Assert.assertTrue(agnthistpg.verifySearchContains(reportDetails.getSearchStr()));
    }   
    @Test(priority=31)
    public void  VerifySearchDoesNotContains() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
    	Assert.assertTrue(agnthistpg.verifySearchDoesNotContains(reportDetails.getSearchStr())); 
    	}    
    @Test(priority=32)
    public void  VerifySearchStartsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(3);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
    	Assert.assertTrue(agnthistpg.verifySearchStartsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=33)
    public void  VerifySearchEndsWith() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
      	ocmReportsPage.showReport(reportDetails);  
        AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
    	Assert.assertTrue(agnthistpg.verifySearchEndsWith(reportDetails.getSearchStr()));
    }
  @Test(priority=34)
  public void SearchClearSearch() throws Exception{
  	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
      Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(2);
      ReportDetails reportDetails= new ReportDetails(map);
      OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	ocmReportsPage.showReport(reportDetails);  
      AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
      agnthistpg.verifySearchClear(reportDetails);    	
  }
    @Test(priority=35)
    public void verifyAdvancedSearchinreportpage() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
         ReportDetails reportDetails= new ReportDetails(map);
         OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);       
         AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);        
         ocmReportsPage.showReport(reportDetails);
         Assert.assertTrue(agnthistpg.verifyAdvanceSearch(reportDetails));            
    }
    @Test(priority=36)
    public void ClearfiltersAdvSrch() throws Exception{ 	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"AdvanceSearch").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map);
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver,OCMReportsPage.class);                   
        ocmReportsPage.ClearAdvFilters(reportDetails);
    } 
    
   @Test(priority=37)
    public void GroupBy() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
	  Map<String, String> map = new ExcelReader(filePath,"ShowDateRange").getTestData().get(0);
	  ReportDetails reportDetails= new ReportDetails(map);
	  OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
	  ocmReportsPage.showReport(reportDetails);
	  AgentHistoricalReportPage agnthistpg=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
  	  Assert.assertTrue(agnthistpg.groupby());
  	  screenshot.captureScreen("OCMAgentHistoricalReport", "GroupBy");
  	  Assert.assertTrue(agnthistpg.groupby());
      screenshot.captureScreen("OCMAgentHistoricalReport", "AlreadyGroupBy");
    }*/
    
   @Test(priority=38)
    public void database1() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
   		ReportDetails reportDetails= new ReportDetails(map);
   		AgentHistoricalReportPage AgentHistoricalReport=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
   		Assert.assertTrue(AgentHistoricalReport.verifyDatabase(reportDetails.getQuery()));
   }
 
   

    @Test(priority=38, description="To verify report data against DB")
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentHistoricalReportData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map);
		OCMReportsPage ocmReportsPage = PageFactory.createPageInstance(driver, OCMReportsPage.class);
		ocmReportsPage.showReport(reportDetails);
   		AgentHistoricalReportPage AgentHistoricalReport=PageFactory.createPageInstance(driver,AgentHistoricalReportPage.class);
   		AgentHistoricalReport.sortAscAgentName();;
		Assert.assertTrue(AgentHistoricalReport.verifyDatabase(reportDetails.getQuery(), reportDetails),"Main Report Data Mismatch");
		/*System.out.println("Main Report Data Match Successfull");
		List<String> skillList = new ArrayList<>();
		skillList = AgentHistoricalReport.getSkills();
		//System.out.println(skillList);
		int k=0;
		for(int i=0;i<skillList.size();i++) {
			if(k==10){
				AgentHistoricalReport.goToNextPage();
				k=k-10;
			}
			AgentHistoricalReport.clickOnSkillIdRowOnMainReport(k);
			Assert.assertTrue(AgentHistoricalReport.verifyDatabaseDrillGridOne(reportDetails.getQueryDrillGridOne(), reportDetails, skillList.get(i)),"Drill Grid One data mismatch for Skill Id " + skillList.get(i));
			System.out.println("Drill Grid One data match successfull for Skill Id " + skillList.get(i));
			k++;
			Thread.sleep(1000);
		}
		List<String> skillDates = new ArrayList<>();
		for(int i=0;i<skillList.size();i++) {
			AgentHistoricalReport.clickOnSkillIdRowOnMainReport(i);
			Thread.sleep(1000);
			skillDates = AgentHistoricalReport.getSkillDates();
			//System.out.println(skillDates);
			k=0;
			for(int j=0;j<skillDates.size();j++) {
				if(k==10){
					AgentHistoricalReport.goToNextPageDrillOne();
					k=k-10;
				}
				AgentHistoricalReport.clickOnDateRowOnDrillOneReport(k);
				Assert.assertTrue(AgentHistoricalReport.verifyDatabaseDrillGridTwo(reportDetails.getQueryDrillGridTwo(), reportDetails, skillDates.get(j), skillList.get(i)),"Drill Grid Two data mismatch for Skill Id " + skillList.get(i) + " and Date " + skillDates.get(j));
				System.out.println("Drill Grid Two data match successfull for Skill Id " + skillList.get(i) + " and Date " + skillDates.get(j));
				k++;
				Thread.sleep(1000);
			}
			AgentHistoricalReport.closeDrillOneReport();
			Thread.sleep(1000);
		}*/
	}
   
   @AfterMethod
   public void afterEachMethod(Method method) throws InterruptedException {
       Screenshot screenshot=new Screenshot(driver);
       screenshot.captureScreen("AgentHistoricalReportTest",method.getName());
       driver.navigate().refresh();
   }
   
}
