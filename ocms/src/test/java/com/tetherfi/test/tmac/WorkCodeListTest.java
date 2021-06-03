package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.TmacBroadCastMsgDetails;
import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;
import com.tetherfi.model.tmac.WorkCodeListDetails;
import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.AgentTeamManagementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.pages.WorkCodeListPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class WorkCodeListTest extends BaseTest{
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WorkCodeListDetails workCodeListDetails=new WorkCodeListDetails(map);
        agentTeamManagementPage.addNewAgentTeamManagementRecord(workCodeListDetails.getLevel(),workCodeListDetails.getCountry(),workCodeListDetails.getDivision(),workCodeListDetails.getDepartment(),workCodeListDetails.getTeamName());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Add New record assertion failed");
        driver.navigate().refresh();
        homePage.navigateToOCMIconImg();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    
    @BeforeMethod
    public void NavigateToWorkCodeListPage()  {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver, TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "tmac page assertion failed");
        tmacPage.navigateToWorkCodeListPage();
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
    }
    
    @Test(priority=1)
    public void WorkCodeListPage()
    {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	Assert.assertTrue(workCodeListPage.verifylogo(),"Tmac Broadcast Message logo assertion failed");
        //Assert.assertTrue(workCodeListPage.verifygridcontent(),"grid container assertion failed");
    	Assert.assertTrue(workCodeListPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("WorkCodeListTest", "maximize window");
    	Assert.assertTrue(workCodeListPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("WorkCodeListTest", "minimize window");
    }
    
    @Test(priority=2)
    public void addNewWorkGroupListCancelRecord() throws Exception
    {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.addnewWorkGroupCancel(workcodeListDetails), "WorkGroup cancel assertion failed");
    }
    @Test(priority=3)
    public void addNewWorkGroupListRecord() throws Exception
    {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
      	workCodeListPage.addNewWorkGroup(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(), "Workgroup Record creation assertion failed" );
    }
    @Test(priority=4)
    public void addNewWorkcodeListCancelRecord() throws Exception
    {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.addNewWorkCodeCancel(workcodeListDetails), "Workcode cancel assertion failed");
    }  
    @Test(priority=5)
    public void addNewWorkcodeListRecord() throws Exception
    {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        workCodeListPage.addNewWorkCode(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(), "Workcode Record creation assertion failed" );
    }
    
    @Test(priority=6,enabled=true)
    public void VerifyAuditTrialReportForCreate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyWorkcodeListCreate(workcodeListDetails,"Create"));
        
    }
    @Test(priority=7)
    public void addInvalidRecordWithoutWorkLevel() throws Exception{
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.addRecordWithoutWorklevel(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"AddRecordWithoutWorklevel Assertion failed");
    }
    @Test(priority=8)
    public void addInvalidRecordWithoutWorkGroup() throws Exception{
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.addRecordWithoutWorkGroup(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"AddRecordWithoutWorkGroup Assertion failed");
    }
    @Test(priority=9)
    public void addInvalidRecordWithoutName() throws Exception{
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.addRecordWithoutName(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"AddRecordWithoutName Assertion failed");
    }
    
    @Test(priority=10,enabled=false)
    public void addDuplicateRecord() throws Exception{
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.duplicateRecord(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"DuplicateRecord Assertion failed");
    }
    
    @Test(priority=11)
    public void editWorkCodeListCancelRecord() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        Assert.assertTrue(workCodeListPage.editWorkcodecancelled(workcodeListDetails),"EditWorkCode cancelled assertion failed");
    }
    @Test(priority=12)
    public void editWorkCodeListRecord() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.editworkcodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"Record updation assertion failed");
    }
    
    @Test(priority=13,enabled=true)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyWorkcodeListUpdate(workcodeListDetails,"Update"));
    }
    
    @Test(priority=14)
    public void searchPage() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        Assert.assertFalse(workCodeListPage.clearAll(workcodeListDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("WorkCodeListTest", "clearall");
        Assert.assertTrue(workCodeListPage.verifyclose());
        screenshot.captureScreen("WorkCodeListTest", "SearchClose");
    }
    
    @Test(priority=15)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifySearchIsNotEqualTo(workcodeListDetails.getName()));
    }
    
    @Test(priority=16)
    public void VerifySearchContains() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifySearchContains(workcodeListDetails.getName()));
    }
    
    @Test(priority=17)
    public void VerifySearchDoesNotContains() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifySearchDoesNotContains(workcodeListDetails.getName()));
    }
    
    @Test(priority=18)
    public void VerifySearchStartsWith() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifySearchStartsWith(workcodeListDetails.getName()));
    }
    
    @Test(priority=19)
    public void VerifySearchEndsWith() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(5);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifySearchEndsWith(workcodeListDetails.getName()));
    }
    
    @Test(priority=20)
    public void DeleteWorkCodeListCancelRecord() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifydeleteNo(workcodeListDetails));
    }
    @Test(priority=21)
    public void DeleteWorkCodeListRecord() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);   
    	workCodeListPage.deleteWorkCodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"delete record assertion failed");
    }
    	
    @Test(priority=22,enabled=true)
    public void VerifyauditTrailReportDelete() throws Exception {
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifycodeListdelete(workcodeListDetails,"Delete"));
        }
    
    @Test(priority=23)
    public void SearchClearSearch() throws Exception
    {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifyinvalidsearchwithwrongdata(workcodeListDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("WorkCodeListTest", "Invalid Search with wrong data");
        Assert.assertTrue(workCodeListPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen("WorkCodeListTest", "Clear Search");
    }
   
    @Test(priority=24)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=25)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\WorkCode List.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    Assert.assertTrue(workCodeListPage.verifyexportToExcelSheet(maplist));
    	
    }
    
    @Test(priority=26)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
    	Assert.assertTrue(workCodeListPage .verifyDatabase(workcodeListDetails.getQuery()));
    }
    @Test(priority=27)
    public void GroupBy()
    {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	Assert.assertTrue(workCodeListPage.groupby());
    	screenshot.captureScreen("WorkCodeListTest", "GroupBy");
    	Assert.assertTrue(workCodeListPage.groupby());
    	screenshot.captureScreen("WorkCodeListTest", "AlreadyGroupBy");	
    }
    @Test(priority=28)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	Assert.assertTrue(workCodeListPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=29)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");

    }
    @Test(priority=30)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=31)
    public void VerifyNumberOfItemsPerPageSelection() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");

    }
    @Test(priority=32)
    public void VerifyDropdownForAllTheColumns() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(priority=33)
    public void VerifyColumnsHeaderEnable() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(priority=34)
    public void VerifyColumnsHeaderDisable() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertFalse(workCodeListPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
	@Test(priority=35)
    public void SortingByAscending() throws IOException {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	workCodeListPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\WorkCode List (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(workCodeListPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=36)
    public void SortingByDescending() throws IOException {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	workCodeListPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\WorkCode List (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(workCodeListPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=37)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.deleteWorkGroupRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.ExporttoExcelWithoutData(workcodeListDetails));
    }
        
    @AfterMethod
    public void afterEachMethod(Method method){
    	 Screenshot screenshot=new Screenshot(driver);
         screenshot.captureScreen("WorkCodeListTest",method.getName());
         driver.navigate().refresh();
     
    }
    
    //@AfterClass
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
         Map<String, String> map1 = new ExcelReader(filePath,"Queries").getTestData().get(2);
         TmacBroadCastMsgDetails tmacBroadCastMsgDetails1=new TmacBroadCastMsgDetails(map1);
         agentTeamManagementPage.database(tmacBroadCastMsgDetails1.getQuery());

    }
}
