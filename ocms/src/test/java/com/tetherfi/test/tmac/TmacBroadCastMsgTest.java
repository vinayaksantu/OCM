package com.tetherfi.test.tmac;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class TmacBroadCastMsgTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    //@BeforeClass
    public void AddNewAgentTeamManagementRecord() throws Exception {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"tmac page assertion failed");
        tmacPage.navigateToAgentTeamManagementPage();
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team  management assertion failed");
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        agentTeamManagementPage.addNewAgentTeamManagementRecord(tmacBroadCastMsgDetails.getLevel(),tmacBroadCastMsgDetails.getCountry(),tmacBroadCastMsgDetails.getDivision(),tmacBroadCastMsgDetails.getDepartment(),tmacBroadCastMsgDetails.getTeamName());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Add New record assertion failed");
        driver.navigate().refresh();
        homePage.navigateToOCMIconImg();
    }
    @BeforeMethod
    public void NavigateToTmacBroadcastMsgPage()  {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver, TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "tmac page assertion failed");
        tmacPage.navigateToTmacBroadcastMsgPage();
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.isTmacBroadcastMsgPageDisplayed(), "TMAC Broadcast page assertion failed");
    }
   
    @Test(priority=1)
    public void TmacBroadCastMsgPage()
    {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    	Assert.assertTrue(tmacBroadCastMsgPage.verifylogo(),"Tmac Broadcast Message logo assertion failed");
    	Assert.assertTrue(tmacBroadCastMsgPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("TmacBroadCastMsgTest", "maximize window");
    	Assert.assertTrue(tmacBroadCastMsgPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("TmacBroadCastMsgTest", "minimize window");
    }
    @Test(priority=2)
    public void AddNewTmacBroadCastMsgCancel() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.addNewCancel(tmacBroadCastMsgDetails), "record cancelled assertion failed");
    }
    @Test(priority=3)
    public void AddNewTmacBroadCastMsg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        tmacBroadCastMsgPage.addTmacBroadcastMsg(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyNewRecordCreated(),"add record assertion failed");
    }
    
    @Test(dependsOnMethods = {"AddNewTmacBroadCastMsg"},priority=4,enabled=true)
    public void VerifyAuditTrialReportForCreate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyTmacBroadcastMsgCreate(tmacBroadCastMsgDetails,"Create"));
        
    }
   @Test(priority=5)
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        tmacBroadCastMsgPage.duplicateRecord(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Duplicate Assertion failed");
   }
   @Test(priority=6)
   public void AddRecordWithoutMesage() throws Exception {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
       TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
       tmacBroadCastMsgPage.addInvalidRecord(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Invalid Record Assertion failed");
   }
   
   @Test(priority=7)
   public void AddRecordwithoutStatus() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
   		TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
   		TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
   		tmacBroadCastMsgPage.addInvalidRecord1(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Invalid Record Assertion failed");
   }
   
   @Test(priority=8)
   public void AddRecordWithoutTeamName() throws Exception {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
       	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       	TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
       	TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
       	tmacBroadCastMsgPage.addInvalidRecord2(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Invalid Record Assertion failed");
    }
    @Test(priority=9)
    public void EditTmacBroadCastMsgCancel() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.editcancel(tmacBroadCastMsgDetails),"Cancel assertion failed");
    }
    @Test(priority=10)
    public void EditTmacBroadCastMsg() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);     
    	tmacBroadCastMsgPage.editTmacBroadcastMsg(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyRecordUpdated(),"Record Updated assertion failed");
    }
    
    @Test(dependsOnMethods = {"EditTmacBroadCastMsg"}, priority=11,enabled=true)
    	public void VerifyAuditTrailReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyTmacBroadcastMsgUpdate(tmacBroadCastMsgDetails,"Update"));
    }
    
    @Test(priority=12)
    public void VerifySearchIsNotEqualTo() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifySearchIsNotEqualTo(tmacBroadCastMsgDetails.getMessage()));
    }
    
    @Test(priority=13)
    public void VerifySearchContains() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifySearchContains(tmacBroadCastMsgDetails.getMessage()));
    }
    
    @Test(priority=14)
    public void VerifySearchDoesNotContains() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifySearchDoesNotContains(tmacBroadCastMsgDetails.getMessage()));
    }
    
    @Test(priority=15)
    public void VerifySearchStartsWith() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifySearchStartsWith(tmacBroadCastMsgDetails.getMessage()));
    }
    
    @Test(priority=16)
    public void VerifySearchEndsWith() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifySearchEndsWith(tmacBroadCastMsgDetails.getMessage()));
    }
    
    
    @Test(priority=17)
    public void searchPage() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertFalse(tmacBroadCastMsgPage.clearAll(tmacBroadCastMsgDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("TmacBroadCastMsgTest", "clearall");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyclose());
        screenshot.captureScreen("TmacBroadCastMsgTest", "SearchClose");	
    }
    
    @Test(priority=18)
    public void SearchClearSearch() throws Exception{
    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
    Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
    TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
    TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    Assert.assertTrue(tmacBroadCastMsgPage.verifyinvalidsearch(tmacBroadCastMsgDetails), "InvalidSearchAssertionFailed");
    Assert.assertTrue(tmacBroadCastMsgPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=20)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Broadcast Message.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    Assert.assertTrue(tmacBroadCastMsgPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=21)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
    	Assert.assertTrue(tmacBroadCastMsgPage.verifyDatabase(tmacBroadCastMsgDetails.getQuery()));
    }
    
    @Test(priority=22)
    public void GroupBy()
    {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    	Assert.assertTrue(tmacBroadCastMsgPage.groupby());
        screenshot.captureScreen("TmacBroadCastMsgTest", "GroupBy");
    	Assert.assertTrue(tmacBroadCastMsgPage.groupby());
        screenshot.captureScreen("TmacBroadCastMsgTest", "AlreadyGroupBy");
    }
    
    @Test(priority=23)
    public void VerifyArrowMoveForPreviousAndNextPage() throws Exception {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    	Assert.assertTrue(tmacBroadCastMsgPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=24)
    public void VerifyArrowMoveForFirstAndLastPage() throws Exception {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    @Test(priority=25)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=26)
    public void VerifyNumberOfItemsPerPageSelection() {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    @Test(priority=27)
    public void VerifyDropdownForAllTheColumns() {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(priority=28)
    public void VerifyColumnsHeaderEnable() {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(priority=29)
    public void VerifyColumnsHeaderDisable() {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    @Test(priority=30)
    public void SortingByAscending() throws Exception {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        tmacBroadCastMsgPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Broadcast Message (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(tmacBroadCastMsgPage.verifyexportToExcelSheet(maplist));	
        }
    @Test(priority=31)
    public void SortingByDescending() throws Exception {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        tmacBroadCastMsgPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Broadcast Message (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(tmacBroadCastMsgPage.verifyexportToExcelSheet(maplist));	
        }
    
    @Test(priority=32)
    public void ExporttoExcelWithoutData() throws Exception
    {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(1);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        tmacBroadCastMsgPage.database(tmacBroadCastMsgDetails.getQuery());
        Map<String, String> map1 = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails1=new TmacBroadCastMsgDetails(map1);
        Assert.assertTrue(tmacBroadCastMsgPage .ExporttoExcelWithoutData(tmacBroadCastMsgDetails1));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
    	 Screenshot screenshot=new Screenshot(driver);
         screenshot.captureScreen("TmacBroadCastMsgTest",method.getName());
         driver.navigate().refresh();
     
    }
    @AfterClass
    public void Afterclass() throws Exception
    {
    	HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
    	 OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
         Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
         ocmHomePage.navigateToTab("TMAC");
         TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
         Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"tmac page assertion failed");
         tmacPage.navigateToAgentTeamManagementPage();
         AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
         Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team  management assertion failed");
         String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
         TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
         agentTeamManagementPage.deleteAgentTeamManagementRecord(tmacBroadCastMsgDetails.getTeamName(),tmacBroadCastMsgDetails.getModifyReason());
         Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Add New record assertion failed");
    }
}
