package com.tetherfi.test;

import com.tetherfi.model.user.AgentSkillAssignmentDetails;
import com.tetherfi.pages.AgentSkillAssignmentPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AgentSkillAssignmentTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToAgentSkillAssignmentPage() {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToAgentSkillAssignmentPage();
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertTrue(agentSkillAssignmentPage.isAgentSkillAssignmentPageDisplayed(),"Agent skill assignment assertion failed");
    }
    
    //@Test(priority=1)
    public void AgentSkillAssignmentPage() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
    	Assert.assertTrue(agentSkillAssignmentPage.verifylogo(),"Skill Configuration logo assertion failed");
    	Assert.assertTrue(agentSkillAssignmentPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("maximize window","AgentSkillAssignmentTest");
    	Assert.assertTrue(agentSkillAssignmentPage .minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("minimize window","AgentSkillAssignmentTest");
    }
    //@Test
    public void EditAgentListRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AgentSkillAssignmentDetails agentSkillAssignmentDetails= new AgentSkillAssignmentDetails(map);
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.assignSkillToAgent(agentSkillAssignmentDetails);
        Assert.assertTrue(agentSkillAssignmentPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    //@Test
    public void AddMultiSkillRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
        AgentSkillAssignmentDetails agentSkillAssignmentDetails= new AgentSkillAssignmentDetails(map);

        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.navigateToMultiSkillTab();
        agentSkillAssignmentPage.assignSkillToMultipleAgent(agentSkillAssignmentDetails);
        Assert.assertTrue(agentSkillAssignmentPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    
    //@Test(priority=6)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertTrue(agentSkillAssignmentPage.verifyExportToExcel(filePath));
        screenshot.captureScreen("Export Excel", "AgentSkillAssignmentTest");
    }
    //@Test(priority=7)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Assignment.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
    	Assert.assertTrue(agentSkillAssignmentPage.verifyexportToExcelSheet(maplist));	
    	screenshot.captureScreen("Export Excel Sheet", "SAgentSkillAssignmentTest");
    }
    
    //@Test(priority=8)
    public void ExporttoExcelWithoutData() throws Exception
    {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSkillAssignmentData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(1);
        AgentSkillAssignmentDetails agentSkillAssignmentDetails= new AgentSkillAssignmentDetails(map);
        Assert.assertTrue(agentSkillAssignmentPage.ExporttoExcelWithoutData(agentSkillAssignmentDetails));
    }
  
    //@Test(priority=9)
    public void SortingByAscending() throws IOException {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Assignment (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentSkillAssignmentPage.verifyexportToExcelSheet(maplist));
    }
    //@Test(priority=10)
    public void SortingByDescending() throws IOException {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Assignment (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(agentSkillAssignmentPage.verifyexportToExcelSheet(maplist));
    }
    
    //@Test(priority=11)
    public void GroupBy()
    {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
    	Assert.assertTrue(agentSkillAssignmentPage.groupby());
        screenshot.captureScreen(driver, "GroupBy","AgentSkillAssignmentTest");
    	Assert.assertTrue(agentSkillAssignmentPage.groupby());
        screenshot.captureScreen(driver, "AlreadyGroupBy","AgentSkillAssignmentTest");
    }
    
    //@Test(priority=12)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
    	Assert.assertTrue(agentSkillAssignmentPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    //@Test(priority=13)
    public void VerifyArrowMoveForFirstAndLastPage() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertTrue(agentSkillAssignmentPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    //@Test(priority=14)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertTrue(agentSkillAssignmentPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    //@Test(priority=15)
    public void VerifyNumberOfItemsPerPageSelection() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertTrue(agentSkillAssignmentPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    //@Test(priority=16)
    public void VerifyDropdownForAllTheColumns() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertTrue(agentSkillAssignmentPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    //@Test(priority=17)
    public void VerifyColumnsHeaderEnable() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertTrue(agentSkillAssignmentPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    //@Test(priority=18)
    public void VerifyColumnsHeaderDisable() {
        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        Assert.assertFalse(agentSkillAssignmentPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    
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
