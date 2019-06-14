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
    	screenshot.captureScreen(driver, "Agent Team Management Page","AgentTeamMgmtTest");
    }
    
  // (priority=1)
    public void AgentTeamManagementPage()
    {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	Assert.assertTrue(agentTeamManagementPage.verifylogo(),"Agent Team  management logo assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(agentTeamManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "Maximize Window","AgentTeamMgmtTest");
    	Assert.assertTrue(agentTeamManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "Minimize Window","AgentTeamMgmtTest");
    }

    //@Test(priority=2)
    public void AddNewAgentTeamManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentTeamMgmtDetails agentTeamMgmtDetails1=new AgentTeamMgmtDetails(map1);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        agentTeamManagementPage.addnewCountry(agentTeamMgmtDetails1);
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Country Assertion Failed");
        Map<String, String> map2 = new ExcelReader(filePath, "Create").getTestData().get(2);
        AgentTeamMgmtDetails agentTeamMgmtDetails2=new AgentTeamMgmtDetails(map2);
        agentTeamManagementPage.addnewDivision(agentTeamMgmtDetails2);
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Division Assertion Failed");
        Map<String, String> map3 = new ExcelReader(filePath, "Create").getTestData().get(3);
        AgentTeamMgmtDetails agentTeamMgmtDetails3=new AgentTeamMgmtDetails(map3);
        agentTeamManagementPage.addnewDepartment(agentTeamMgmtDetails3);
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Department Assertion Failed");
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        Assert.assertTrue(agentTeamManagementPage.addNewCancel(agentTeamMgmtDetails),"Cancel New Record assertion failed");
        screenshot.captureScreen(driver, "Cancel New Record","AgentTeamMgmtTest");
        agentTeamManagementPage.addNewAgentTeamManagementRecord(agentTeamMgmtDetails.getLevel(),agentTeamMgmtDetails.getCountry(),agentTeamMgmtDetails.getDivision(),agentTeamMgmtDetails.getDepartment(),agentTeamMgmtDetails.getTeamName());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(), "Record Created Successfully");
        screenshot.captureScreen(driver, "Add new Agent","AgentTeamMgmtTest");
    }
    
    //@Test()
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
        screenshot.captureScreen("AgentTeamMgmtTest", "VerifyAuditTrialReportForCreate");
    }
    
    //@Test(priority=3)
    public void AddInvalidRecord() throws Exception {
   	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
       AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
       AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class); 
       agentTeamManagementPage.addInvalidRecord(agentTeamMgmtDetails);
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
       screenshot.captureScreen(driver, "Invalid Agent when teamname is null","AgentTeamMgmtTest");
       agentTeamManagementPage.addInvalidRecord2(agentTeamMgmtDetails);
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
       screenshot.captureScreen(driver, "Invalid Agent when country is null","AgentTeamMgmtTest");
       agentTeamManagementPage.addInvalidRecord3(agentTeamMgmtDetails);
       Assert.assertTrue(agentTeamManagementPage.errorMessage(),"invalid data assertion failed");
       screenshot.captureScreen(driver, "Invalid Agent when divison is null","AgentTeamMgmtTest");	
       agentTeamManagementPage.addInvalidRecord4(agentTeamMgmtDetails);
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
       screenshot.captureScreen(driver, "Invalid Agent when department is null","AgentTeamMgmtTest");
       agentTeamManagementPage.duplicateRecord(agentTeamMgmtDetails.getLevel(),agentTeamMgmtDetails.getCountry(),agentTeamMgmtDetails.getDivision(),agentTeamMgmtDetails.getDepartment(),agentTeamMgmtDetails.getTeamName());
       Assert.assertTrue(agentTeamManagementPage.errorMessage());
       screenshot.captureScreen(driver, "Duplicate Agent","AgentTeamMgmtTest");
       }   
  
    //@Test(priority=4)
    public void EditAgentTeamManagementRecord() throws Exception {
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        Map<String, String> map2 = new ExcelReader(filePath,"Queries").getTestData().get(1);
        AgentTeamMgmtDetails agentTeamMgmtDetails2=new AgentTeamMgmtDetails(map2);
        String displayname=ocmReportsPage.RunQuery(agentTeamMgmtDetails2.getQuery(),"DisplayName");
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.editCancelRecord(agentTeamMgmtDetails.getTeamName(),agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getModifyReason()),"Edit Cancel Assertion failed");
        screenshot.captureScreen(driver, "Edit Cancel","AgentTeamMgmtTest");
        agentTeamManagementPage.editAgentTeamManagementRecord(agentTeamMgmtDetails.getTeamName(),agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getModifyReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Edit record assertion failed");
        screenshot.captureScreen(driver, "Edit record", "AgentTeamMgmtTest");
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Map<String, String> map3 = new ExcelReader(filePath,"Queries").getTestData().get(2);
        AgentTeamMgmtDetails agentTeamMgmtDetails3=new AgentTeamMgmtDetails(map3);
        Assert.assertTrue(ocmReportsPage.verifyAgentTeamMgmtUpdate(agentTeamMgmtDetails,"Update",displayname,agentTeamMgmtDetails3));
        screenshot.captureScreen("AgentTeamMgmtTest", "VerifyAuditTrialReportForUpdate");
    }
    
   /*@Test(priority=5)
    public void searchPage() throws Exception{
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertFalse(agentTeamManagementPage.clearAll(agentTeamMgmtDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","AgentTeamMgmtTest");
        Assert.assertTrue(agentTeamManagementPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","AgentTeamMgmtTest");
        }
    
   @Test(priority=6)
    public void SearchClearSearch() throws Exception
    {
    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
    Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
    AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
    AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    Assert.assertTrue(agentTeamManagementPage.verifyinvalidsearch(agentTeamMgmtDetails), "InvalidSearchAssertionFailed");
    screenshot.captureScreen(driver, "Invalid Search", "AgentTeamMgmtTest");
    Assert.assertTrue(agentTeamManagementPage.verifyclearsearch(), "Clear All Assertion Failed");
    screenshot.captureScreen(driver, "Clear Search", "AgentTeamMgmtTest");
    }*/
    
    @Test(priority=7)
    public void DeleteAgentTeamManagementRecord() throws Exception {
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        Map<String, String> map1 = new ExcelReader(filePath,"Queries").getTestData().get(2);
        AgentTeamMgmtDetails agentTeamMgmtDetails1=new AgentTeamMgmtDetails(map1);
        String displayname=ocmReportsPage.RunQuery(agentTeamMgmtDetails1.getQuery(),"DisplayName");
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifydeleteNo(agentTeamMgmtDetails.getUpdateTeamName(), agentTeamMgmtDetails.getDeleteReason()));
        screenshot.captureScreen(driver, "delete No","AgentTeamMgmtTest");
        agentTeamManagementPage.deleteAgentTeamManagementRecord(agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getDeleteReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"delete record assertion failed");
        screenshot.captureScreen(driver, "Verify Record Deleted", "AgentTeamMgmtTest");
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyAgentTeamMgmtdelete(agentTeamMgmtDetails,"Delete",displayname));
        screenshot.captureScreen("AgentTeamMgmtTest", "VerifyAuditTrialReportForUpdate");
        }
   
    /*@Test(priority=8)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=9)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Team Management.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    Assert.assertTrue(agentTeamManagementPage.verifyexportToExcelSheet(maplist));
    	
    }
    
    @Test(priority=10)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
    	Assert.assertTrue(agentTeamManagementPage.verifyDatabase(agentTeamMgmtDetails.getQuery()));
    }
    @Test(priority=11)
    public void GroupBy()
    {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	Assert.assertTrue(agentTeamManagementPage.groupby());
    	
    }
    @Test(priority=12)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	Assert.assertTrue(agentTeamManagementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    @Test(priority=13)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    @Test(priority=14)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=15)
    public void VerifyNumberOfItemsPerPageSelection() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
   
    @Test(priority=16)
    public void VerifyDropdownForAllTheColumns() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(priority=17)
    public void VerifyColumnsHeaderEnable() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(priority=18)
    public void VerifyColumnsHeaderDisable() {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertFalse(agentTeamManagementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    @Test(priority=19)
    public void SortingByAscending() throws IOException {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	agentTeamManagementPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Team Management (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentTeamManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=20)
    public void SortingByDescending() throws IOException {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	agentTeamManagementPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Team Management (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentTeamManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=21)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        Assert.assertTrue(agentTeamManagementPage.ExporttoExcelWithoutData(agentTeamMgmtDetails));
    }*/
    
    @AfterMethod
    public void afterEachMethod(ITestResult result){
   	 if(ITestResult.FAILURE==result.getStatus()){
		 try{
			 screenshot.captureScreen(driver, result.getName(),"AgentTeamMgmtTest");
		 }
		catch (Exception e){
		 System.out.println("Exception while taking screenshot "+e.getMessage());
		 } 
		 driver.navigate().refresh();
		 }
	
    }
}