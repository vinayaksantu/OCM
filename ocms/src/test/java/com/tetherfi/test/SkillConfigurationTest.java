package com.tetherfi.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.ivr.HolidayListDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.WaitTimeColorConfigDetails;
import com.tetherfi.model.user.SkillConfigurationDetails;
import com.tetherfi.pages.AgentSkillAssignmentPage;
import com.tetherfi.pages.HolidayListPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.SkillConfigurationPage;
import com.tetherfi.pages.WaitTimeColorConfigPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SkillConfigurationTest extends BaseTest{
	
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToSkillConfigurationPage() {
    	 HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
         homePage.navigateToOCMPage();
         OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
         Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
         ocmHomePage.navigateToSkillConfigurationPage();
         SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
         Assert.assertTrue(skillConfigurationPage.isSkillConfigurationPageDisplayed(),"Skill Configuration assertion failed");
    }
    @Test(priority=1)
    public void SkillConfigurationPage() {
        SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.verifylogo(),"Skill Configuration logo assertion failed");
    	Assert.assertTrue(skillConfigurationPage .maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("maximize window","SkillConfigurationTest");
    	Assert.assertTrue(skillConfigurationPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("minimize window","SkillConfigurationTest");
    }
    
  	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertFalse(skillConfigurationPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddSkillConfigurationRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.addNewCancel(skillConfigurationDetails));
        screenshot.captureScreen(driver, "add cancel","SkillConfigurationTest");
        skillConfigurationPage.addNewSkillConfigurationRecord(skillConfigurationDetails);
        Assert.assertEquals(skillConfigurationPage.getMessage(),"Record Created Successfully","Add New record assertion failed");
        screenshot.captureScreen(driver, "Record Created Successfully","SkillConfigurationTest");
    }
    
    @Test(priority=6)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySkillConfigurationCreate(skillConfigurationDetails,"Create"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForCreate","SkillConfigurationTest");   
    }
    
    @Test(priority=7)
    public void VerifySkillInAgentSkillAssignment() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
    	OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToAgentSkillAssignmentPage();
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.navigateToMultiSkillTab();
        Assert.assertTrue(agentSkillAssignmentPage.VerifySkill(skillConfigurationDetails.getSkillName()));  
    }
    
    @Test(priority=8)
    public void DuplicateRecord() throws Exception {
    	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
         SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
     	 SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
         skillConfigurationPage.addNewSkillConfigurationRecord(skillConfigurationDetails);
         Assert.assertFalse(skillConfigurationPage.verifyMessage(),"Duplicate record assertion failed");
         screenshot.captureScreen(driver, "Duplicate Record","SkillConfigurationTest");	
    }
    
    @Test(priority=9)
    public void AddRecordWithoutSkillID() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        skillConfigurationPage.addNewRecordWithoutSkillID(skillConfigurationDetails);
        Assert.assertFalse(skillConfigurationPage.verifyMessage(),"Duplicate record assertion failed");
        screenshot.captureScreen(driver, "AddRecordWithoutSkillID","SkillConfigurationTest");	
    }
    
    @Test(priority=10)
    public void AddRecordWithoutSkillName() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        skillConfigurationPage.addNewRecordWithoutSkillName(skillConfigurationDetails);
        Assert.assertFalse(skillConfigurationPage.verifyMessage(),"Duplicate record assertion failed");
        screenshot.captureScreen(driver, "AddRecordWithoutSkillName","SkillConfigurationTest");	
    }
    
    @Test(priority=11)
    public void AddRecordWithoutSkillExtension() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        skillConfigurationPage.addNewRecordWithoutSkillExtension(skillConfigurationDetails);
        Assert.assertFalse(skillConfigurationPage.verifyMessage(),"Duplicate record assertion failed");
        screenshot.captureScreen(driver, "AddRecordWithoutSkillExtension","SkillConfigurationTest");	
    }
    
    @Test(priority=12)
    public void AddRecordWithoutSkillPriority() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        skillConfigurationPage.addNewRecordWithoutSkillPriority(skillConfigurationDetails);
        Assert.assertFalse(skillConfigurationPage.verifyMessage(),"Duplicate record assertion failed");
        screenshot.captureScreen(driver, "AddRecordWithoutSkillPriority","SkillConfigurationTest");	
    }
    
    @Test(priority=13)
    public void AddRecordWithoutEnabled() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        skillConfigurationPage.addNewRecordWithoutEnabled(skillConfigurationDetails);
        Assert.assertFalse(skillConfigurationPage.verifyMessage(),"Duplicate record assertion failed");
        screenshot.captureScreen(driver, "AddRecordWithoutEnabled","SkillConfigurationTest");	
    }
    
    @Test(priority=14)
    public void EditCancelSkillConfigurationRecord() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.editcancel(skillConfigurationDetails));
        screenshot.captureScreen(driver, "edit cancel","SkillConfigurationTest");
    }
    
    @Test(priority=15)
    public void EditSkillConfigurationRecord() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	skillConfigurationPage.editSkillConfigurationRecord(skillConfigurationDetails);
        Assert.assertEquals(skillConfigurationPage.getMessage(),"Record Updated Successfully","Edit record assertion failed");
        screenshot.captureScreen(driver, "Record Updated Successfully","SkillConfigurationTest"); 
    }
    
   	@Test(priority=16)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
   	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySkillConfigurationUpdate(skillConfigurationDetails,"Update"));
        screenshot.captureScreen(driver,"VerifyAuditTrialReportForUpdate","SkillConfigurationTest");
    }
    @Test(priority=17)
    public void VerifyUpdatedSkillInSkillAssignment() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
    	OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToAgentSkillAssignmentPage();
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.navigateToMultiSkillTab();
        Assert.assertFalse(agentSkillAssignmentPage.VerifySkill(skillConfigurationDetails.getSkillName()));  
    
    }
  
   @Test(priority=18)
   public void searchPage() throws Exception{
  	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
       SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
       SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
       Assert.assertFalse(skillConfigurationPage.clearAll(skillConfigurationDetails),"ClearAll Assertion Failed");
       screenshot.captureScreen(driver, "clearall","SkillConfigurationTest");
       Assert.assertTrue(skillConfigurationPage.verifyclose());
       screenshot.captureScreen(driver, "SearchClose","SkillConfigurationTest");
       }
   
   	@Test(priority=19)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyExportToExcel(filePath));
        screenshot.captureScreen("Export Excel", "SkillConfigurationTest");
    }
    
    @Test(priority=20)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\SkillConfiguration.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen("Export Excel Sheet", "SkillConfigurationTest");
    }
    
    @Test(priority=21)
    public void DeleteCancelRecord() throws Exception {
  	 	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
         SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
     	 SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
         Assert.assertTrue(skillConfigurationPage.verifydeleteNo(skillConfigurationDetails));
         screenshot.captureScreen(driver, "Delete No","SkillConfigurationTest");
    }
    
    
    @Test(priority=21)
    public void DeleteRecord() throws Exception {
  	 	 String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
         SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
     	 SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
     	 skillConfigurationPage.deleteWaitTimeColorConfigRecord(skillConfigurationDetails);
     	 Assert.assertEquals(skillConfigurationPage.getMessage(),"Record Deleted Successfully","Delete record assertion failed");
         screenshot.captureScreen(driver, "Deleted Successfully","SkillConfigurationTest");  
    }
    
    @Test(priority=22)
    public void VerifyAuditTrialReportForDelete() throws Exception {
 	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifySkillConfigurationdelete(skillConfigurationDetails,"Delete"));
        screenshot.captureScreen(driver, "VerifyAuditTrialReportForUpdate","SkillConfigurationTest");
    }
    
    @Test(priority=23)
    public void VerifyDeletedSkillInSkillAssignment() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
    	OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToAgentSkillAssignmentPage();
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.navigateToMultiSkillTab();
        Assert.assertFalse(agentSkillAssignmentPage.VerifySkill(skillConfigurationDetails.getSkillName()));  
    
    }
    
    /*@Test(priority=24)
    public void SearchClearSearch() throws Exception
    {
  	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyinvalidsearch(skillConfigurationDetails), "InvalidSearchAssertionFailed");
        screenshot.captureScreen(driver, "Invalid Search", "WaitTimeColorConfigTest");
        Assert.assertTrue(skillConfigurationPage.verifyclearsearch(), "ClearSearch Assertion Failed");
        screenshot.captureScreen(driver, "Clear Search", "SkillConfigurationTest");
    }
    
    @Test(priority=23)
    public void ExporttoExcelWithoutData() throws Exception
    {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        Assert.assertTrue(skillConfigurationPage.ExporttoExcelWithoutData(skillConfigurationDetails));
    }
  
    @Test(priority=24)
    public void SortingByAscending() throws IOException {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	skillConfigurationPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\SkillConfiguration (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(skillConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=25)
    public void SortingByDescending() throws IOException {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	skillConfigurationPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\SkillConfiguration (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(skillConfigurationPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=26)
    public void GroupBy()
    {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","SkillConfigurationTest");
    	Assert.assertTrue(skillConfigurationPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","SkillConfigurationTest");
    }
    
    //@Test(priority=27)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
    	Assert.assertTrue(skillConfigurationPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=28)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    //@Test(priority=29)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=30)
    public void VerifyNumberOfItemsPerPageSelection() {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
        Assert.assertTrue(skillConfigurationPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=31)
    public void database() throws Exception
    {
    	SkillConfigurationPage skillConfigurationPage=PageFactory.createPageInstance(driver,SkillConfigurationPage.class);
 	 	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
        SkillConfigurationDetails skillConfigurationDetails = new SkillConfigurationDetails(map);
        Assert.assertTrue(skillConfigurationPage.verifyDatabase(skillConfigurationDetails.getQuery()));
   
    }*/
    
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws InterruptedException {
    	 if(ITestResult.FAILURE==result.getStatus()){
       		 try{
       			 screenshot.captureScreen(method.getName(),"SkillConfigurationTest");
       		 }
       		catch (Exception e){
       		 System.out.println("Exception while taking screenshot "+e.getMessage());
       		 } 
       		 driver.navigate().refresh();
       		 }    }

}
