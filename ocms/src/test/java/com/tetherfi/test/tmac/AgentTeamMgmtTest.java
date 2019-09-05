package com.tetherfi.test.tmac;

import org.testng.annotations.Test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentTeamMgmtDetails;
import com.tetherfi.pages.AgentTeamManagementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AgentTeamMgmtTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);

    @BeforeMethod
    public void NavigateToAgentTeamManagementPage()  {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
    	screenshot.captureScreen(driver, "TMAC Page","AgentTeamMgmtTest");
        tmacPage.navigateToAgentTeamManagementPage();
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team Management Page assertion failed");
    }
    
    @Test(priority=1)
    public void AgentTeamManagementPage()
    {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	Assert.assertTrue(agentTeamManagementPage.verifylogo(),"Agent Team  management logo assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(agentTeamManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("AgentTeamManagementTest", "Maximize Window");
    	Assert.assertTrue(agentTeamManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("AgentTeamManagementTest", "Minimize Window");
    }

    @Test(priority=2)
    public void AddNewCountryRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        agentTeamManagementPage.addnewCountry(agentTeamMgmtDetails);
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Country Assertion Failed");
    }
    @Test(priority=3)
    public void AddNewDivisionRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(2);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        agentTeamManagementPage.addnewDivision(agentTeamMgmtDetails);
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Division Assertion Failed");
    }
    @Test(priority=4)
    public void AddNewDepartmentRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";     
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Map<String, String> map3 = new ExcelReader(filePath, "Create").getTestData().get(3);
        AgentTeamMgmtDetails agentTeamMgmtDetails3=new AgentTeamMgmtDetails(map3);
        agentTeamManagementPage.addnewDepartment(agentTeamMgmtDetails3);
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Department Assertion Failed");
    }
    
    @Test(priority=5)
    public void AddNewAgentTeamManagementCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";     
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        Assert.assertTrue(agentTeamManagementPage.addNewCancel(agentTeamMgmtDetails),"Cancel New Record assertion failed");
    }
    
    @Test(priority=6)
    public void AddNewAgentTeamManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";     
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);   
        agentTeamManagementPage.addNewAgentTeamManagementRecord(agentTeamMgmtDetails.getLevel(),agentTeamMgmtDetails.getCountry(),agentTeamMgmtDetails.getDivision(),agentTeamMgmtDetails.getDepartment(),agentTeamMgmtDetails.getTeamName());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Record Created Successfully");
    }
    
    @Test(priority=7)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAgentTeamMgmtCreate(agentTeamMgmtDetails,"Create"));
    }
    
    @Test(priority=8)
    public void AddRecordTeamNameBlank() throws Exception {
   	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
       AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class); 
       agentTeamManagementPage.addInvalidRecord(agentTeamMgmtDetails);
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
    }
    
    @Test(priority=9)
    public void AddRecordCountryBlank() throws Exception {
   	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
       AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);        
       agentTeamManagementPage.addInvalidRecord2(agentTeamMgmtDetails);
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
    }
    @Test(priority=10)
    public void AddRecordDivisionBlank() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
    	AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);        
        agentTeamManagementPage.addInvalidRecord3(agentTeamMgmtDetails);
        Assert.assertTrue(agentTeamManagementPage.errorMessage(),"invalid data assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordDepartmentBlank() throws Exception {
   	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
       AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);        
       agentTeamManagementPage.addInvalidRecord4(agentTeamMgmtDetails);
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
    }
    @Test(priority=12)
    public void AddDuplicateRecord() throws Exception {
   	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
       AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);          
       agentTeamManagementPage.duplicateRecord(agentTeamMgmtDetails.getLevel(),agentTeamMgmtDetails.getCountry(),agentTeamMgmtDetails.getDivision(),agentTeamMgmtDetails.getDepartment(),agentTeamMgmtDetails.getTeamName());
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
       }   
  
    @Test(priority=13)
    public void EditAgentTeamManagementCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.editCancelRecord(agentTeamMgmtDetails.getTeamName(),agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getModifyReason()),"Edit Cancel Assertion failed");
    }
    @Test(priority=14)
    public void EditAgentTeamManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        agentTeamManagementPage.editAgentTeamManagementRecord(agentTeamMgmtDetails.getTeamName(),agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getModifyReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Edit record assertion failed");
    }
    
    //@Test(priority=15)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Update").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAgentTeamMgmtUpdate(agentTeamMgmtDetails,"Update"));
        screenshot.captureScreen("AgentTeamMgmtTest", "VerifyAuditTrialReportForUpdate");
    }
    
    @Test(priority=16)
    public void searchPage() throws Exception{
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertFalse(agentTeamManagementPage.clearAll(agentTeamMgmtDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("AgentTeamMgmtTest", "clearall");
        Assert.assertTrue(agentTeamManagementPage.verifyclose());
        screenshot.captureScreen("AgentTeamMgmtTest", "SearchClose");
        }
    
    @Test(priority=17)
    public void DeleteAgentTeamManagementCancelRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifydeleteNo(agentTeamMgmtDetails.getUpdateTeamName(), agentTeamMgmtDetails.getDeleteReason()));
    }
    @Test(priority=18)
    public void DeleteAgentTeamManagementRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	agentTeamManagementPage.deleteAgentTeamManagementRecord(agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getDeleteReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"delete record assertion failed");
    }
    
    //@Test(priority=19)
    public void VerifyAuditTrialReportForDelete() throws Exception {
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAgentTeamMgmtdelete(agentTeamMgmtDetails,"Delete"));
        }
    
    @Test(priority=20)
    public void SearchClearSearch() throws Exception{
	   String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
	   Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
	   AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
	   AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
	   Assert.assertTrue(agentTeamManagementPage.verifyinvalidsearch(agentTeamMgmtDetails), "InvalidSearchAssertionFailed");
	   screenshot.captureScreen("AgentTeamMgmtTest", "Invalid Search");
	   Assert.assertTrue(agentTeamManagementPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
   
    @Test(priority=21)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=22)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Organizational Structure.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    Assert.assertTrue(agentTeamManagementPage.verifyexportToExcelSheet(maplist));
    	
    }
    
    @Test(priority=23)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
    	Assert.assertTrue(agentTeamManagementPage.verifyDatabase(agentTeamMgmtDetails.getQuery()));
    }
    
    @Test(priority=24)
    public void GroupBy()
    {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	Assert.assertTrue(agentTeamManagementPage.groupby());
    	
    }
    @Test(priority=25)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	Assert.assertTrue(agentTeamManagementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=26)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    @Test(priority=27)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=28)
    public void VerifyNumberOfItemsPerPageSelection() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
   
    @Test(priority=29)
    public void VerifyDropdownForAllTheColumns() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(priority=30)
    public void VerifyColumnsHeaderEnable() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(priority=31)
    public void VerifyColumnsHeaderDisable() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertFalse(agentTeamManagementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    @Test(priority=32)
    public void SortingByAscending() throws IOException {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	agentTeamManagementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Organizational Structure (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentTeamManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=33)
    public void SortingByDescending() throws IOException {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	agentTeamManagementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Organizational Structure (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentTeamManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=34)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        Assert.assertTrue(agentTeamManagementPage.ExporttoExcelWithoutData(agentTeamMgmtDetails));
    }
    
    @AfterMethod
    public void afterEachMethod(Method method){
    	Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("AgentTeamManagementTest",method.getName());
        driver.navigate().refresh(); 
    }
}