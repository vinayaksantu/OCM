package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
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
    public void AddNewAgentTeamManagementRecord() throws IOException {
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
        homePage.navigateToOcmIconImg();
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
        screenshot.captureScreen(driver, "TMAC Page","WorkCodeListTest");
        tmacPage.navigateToWorkCodeListPage();
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.isWorkCodeListPageDisplayed(), "WorkCodeList page assertion failed");
        screenshot.captureScreen(driver, "WorkCodeList Page","WorkCodeListTest");
    }
    //@Test(priority=1)
    public void WorkCodeListPage()
    {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	Assert.assertTrue(workCodeListPage.verifylogo(),"Tmac Broadcast Message logo assertion failed");
        Assert.assertTrue(workCodeListPage.verifygridcontent(),"grid container assertion failed");
    	Assert.assertTrue(workCodeListPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "maximize window","WorkCodeListTest");
    	Assert.assertTrue(workCodeListPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "minimize window","WorkCodeListTest");
    }
    
    //@Test(priority=2)
    public void addNewWorkcodeListRecord() throws Exception
    {
        WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.addnewWorkGroupCancel(workcodeListDetails), "WorkGroup cancel assertion failed");
        screenshot.captureScreen(driver, "Workgroup record cancelled","WorkCodeListTest");
        workCodeListPage.addNewWorkGroup(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(), "Workgroup Record creation assertion failed" );
        screenshot.captureScreen(driver, "Workgroup Record Created","WorkCodeListTest");
        Map<String, String> map1 = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails1=new WorkCodeListDetails (map1);
        Assert.assertTrue(workCodeListPage.addNewWorkCodeCancel(workcodeListDetails1), "Workcode cancel assertion failed");
        screenshot.captureScreen(driver, "Workcode record cancelled","WorkCodeListTest");
        workCodeListPage.addNewWorkCode(workcodeListDetails1);
        Assert.assertTrue(workCodeListPage.verifymessage(), "Workcode Record creation assertion failed" );
        screenshot.captureScreen(driver, "Workcode record created","WorkCodeListTest");     
    }
    //@Test()
    public void VerifyAuditTrialReportForCreate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Map<String, String> map1 = new ExcelReader(filePath, "Queries").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails1=new WorkCodeListDetails (map1);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyWorkcodeListCreate(workcodeListDetails,"Create",workcodeListDetails1));
        screenshot.captureScreen("WorkCodeListTest", "VerifyAuditTrialReportForCreate");
        
    }
    //@Test(priority=3)
    public void addInvalidRecord() throws Exception{
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        workCodeListPage.addRecordWithoutWorklevel(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"AddRecordWithoutWorklevel Assertion failed");
        screenshot.captureScreen(driver, "addRecordWithoutWorklevel","WorkCodeListTest");
        workCodeListPage.addRecordWithoutWorkGroup(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"AddRecordWithoutWorkGroup Assertion failed");
        screenshot.captureScreen(driver, "AddRecordWithoutWorkGroup","WorkCodeListTest");
        workCodeListPage.addRecordWithoutName(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"AddRecordWithoutName Assertion failed");
        screenshot.captureScreen(driver, "AddRecordWithoutName","WorkCodeListTest");
        workCodeListPage.duplicateRecord(workcodeListDetails);
        Assert.assertFalse(workCodeListPage.errormessage(),"DuplicateRecord Assertion failed");
        screenshot.captureScreen(driver, "Duplicate Record","WorkCodeListTest");	
    }
   //@Test(priority=4)
    public void editWorkCodeListRecord() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        Assert.assertTrue(workCodeListPage.editWorkcodecancelled(workcodeListDetails),"EditWorkCode cancelled assertion failed");
        screenshot.captureScreen(driver, "EditWorkCode Cancelled","WorkCodeListTest");
        workCodeListPage.editworkcodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"Record updation assertion failed");
        screenshot.captureScreen(driver, "Record Updated","WorkCodeListTest");
    }
    
    //@Test()
    public void VerifyAuditTrialReportForUpdate() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        Map<String, String> map2 = new ExcelReader(filePath, "Queries").getTestData().get(2);	
        WorkCodeListDetails workcodeListDetails1=new WorkCodeListDetails (map2);	
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyWorkcodeListUpdate(workcodeListDetails,"Update",workcodeListDetails1));
        screenshot.captureScreen("WorkCodeListTest", "VerifyAuditTrialReportForUpdate");
    }
    
    //@Test(priority=5)
    public void searchPage() throws IOException {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        Assert.assertFalse(workCodeListPage.clearAll(workcodeListDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","WorkCodeListTest");
        Assert.assertTrue(workCodeListPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","WorkCodeListTest");
    }
    
    //@Test(priority=6)
    public void SearchClearSearch() throws Exception
    {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifyinvalidsearchwithwrongdata(workcodeListDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver, "Invalid Search with wrong data", "WorkCodeListTest");
        Assert.assertTrue(workCodeListPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen(driver, "Clear Search", "WorkCodeListTest");
    }
    
    //@Test(priority=7)
    public void InvalidSearch() throws Exception {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Assert.assertTrue(workCodeListPage.verifyinvalidsearch(workcodeListDetails), "InvalidSearchAssertionFailed");
        screenshot.captureScreen(driver, "Invalid Search", "WorkCodeListTest");
    }
    
    @Test(priority=8)
    public void DeleteWorkCodeListRecord() throws Exception {
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
        Map<String, String> map2 = new ExcelReader(filePath,"Queries").getTestData().get(2);
        WorkCodeListDetails workcodeListDetails1=new WorkCodeListDetails (map2);
        String workcode=ocmReportsPage.RunQuery(workcodeListDetails1.getQuery(),"WorkCode");
        Assert.assertTrue(workCodeListPage.verifydeleteNo(workcodeListDetails));
        screenshot.captureScreen(driver, "delete No","WorkCodeListTest");
        workCodeListPage.deleteWorkCodeListRecord(workcodeListDetails);
        Assert.assertTrue(workCodeListPage.verifymessage(),"delete record assertion failed");
        screenshot.captureScreen(driver, "Verify Record Deleted", "WorkCodeListTest");
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifycodeListdelete(workcodeListDetails,"Delete",workcode));
        screenshot.captureScreen("WorkCodeListTest", "VerifyAuditTrialReportForUpdate");
        }
   
    /*@Test(priority=9)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=10)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\WorkCode List.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    Assert.assertTrue(workCodeListPage.verifyexportToExcelSheet(maplist));
    	
    }
    
    @Test(priority=11)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);
    	Assert.assertTrue(workCodeListPage .verifyDatabase(workcodeListDetails.getQuery()));
    }
    @Test(priority=12)
    public void GroupBy()
    {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	Assert.assertTrue(workCodeListPage.groupby());
    	screenshot.captureScreen(driver, "GroupBy", "WorkCodeListTest");
    	Assert.assertTrue(workCodeListPage.groupby());
    	screenshot.captureScreen(driver, "AlreadyGroupBy", "WorkCodeListTest");	
    }
    @Test(priority=13)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	Assert.assertTrue(workCodeListPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    	screenshot.captureScreen(driver, "VerifyArrowMoveForPreviousAndNextPage", "WorkCodeListTest");	
    }
    @Test(priority=14)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    	screenshot.captureScreen(driver, "VerifyArrowMoveForFirstAndLastPage", "WorkCodeListTest");	

    }
    @Test(priority=15)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    	screenshot.captureScreen(driver, "VerifyTotalNumberOfItemsPerPageDetails", "WorkCodeListTest");	
    }
    
    @Test(priority=16)
    public void VerifyNumberOfItemsPerPageSelection() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
        screenshot.captureScreen(driver, "VerifyNumberOfItemsPerPageSelection","WorkCodeListTest");

    }
    @Test(priority=17)
    public void VerifyDropdownForAllTheColumns() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(priority=18)
    public void VerifyColumnsHeaderEnable() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertTrue(workCodeListPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(priority=19)
    public void VerifyColumnsHeaderDisable() {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
        Assert.assertFalse(workCodeListPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
	@Test(priority=20)
    public void SortingByAscending() throws IOException {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	workCodeListPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\WorkCode List (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(workCodeListPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=21)
    public void SortingByDescending() throws IOException {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	workCodeListPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\WorkCode List (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(workCodeListPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=22)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	WorkCodeListPage workCodeListPage  = PageFactory.createPageInstance(driver, WorkCodeListPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\WorkCodeListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        WorkCodeListDetails workcodeListDetails=new WorkCodeListDetails (map);	
        Assert.assertTrue(workCodeListPage.ExporttoExcelWithoutData(workcodeListDetails));
    }*/
        @AfterMethod
    public void afterEachMethod(ITestResult result,Method method){
   	 if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, method.getName(),"TmacBroadCastMsgTest");
   		 }
   		catch (Exception e){
   		 System.out.println("Exception while taking screenshot "+e.getMessage());
   		 } 
   		 driver.navigate().refresh();
   		 }
   		
    }
    @AfterClass
    public void Afterclass()
    {
    	System.out.println("Run successfully");
    	
    }
}
