package com.tetherfi.test.tmac;

import org.testng.annotations.Test;
import com.tetherfi.model.tmac.AgentTeamMgmtDetails;
import com.tetherfi.pages.AgentTeamManagementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
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
	Screenshot screenshot=new Screenshot();

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
    
    //@Test (priority=1)
    public void AgentTeamManagementPage()
    {
    	AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
    	Assert.assertTrue(agentTeamManagementPage.verifylogo(),"Agent Team  management logo assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifyLevelHierarchybutton(), "Level Hierarchy button assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifyNamebutton(), "Name button assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifyDisplayHierarchybutton(), "Display Hierarchy button assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifylastchangedbybutton(), "Last Changedby assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifylastchangedonbutton(), "Last Changedon assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifygridcontent(),"Grid Container assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifyTeamIdenable(), "Team Id enable assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifyTeamIddisable(), "Team Id disable assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifyParentIdenable(), "Parent Id enable assertion failed");
        Assert.assertTrue(agentTeamManagementPage.verifyParentIddisable(), "Parent Id disable assertion failed");
    	Assert.assertTrue(agentTeamManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "Maximize Window","AgentTeamMgmtTest");
    	Assert.assertTrue(agentTeamManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "Minimize Window","AgentTeamMgmtTest");
    }

    @Test(priority=2)
    public void AddNewAgentTeamManagementRecord() throws IOException, InterruptedException {
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
    @Test(priority=3)
    public void AddInvalidRecord() throws IOException {
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
       Assert.assertEquals(agentTeamManagementPage.duplicateMessage(),"Duplicate Name");
       screenshot.captureScreen(driver, "Duplicate Agent","AgentTeamMgmtTest");
       }   
   

    @Test(priority=4)
    public void EditAgentTeamManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.editCancelRecord(agentTeamMgmtDetails.getTeamName(),agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getModifyReason()),"Edit Cancel Assertion failed");
        screenshot.captureScreen(driver, "Edit Cancel","AgentTeamMgmtTest");
        agentTeamManagementPage.editAgentTeamManagementRecord(agentTeamMgmtDetails.getTeamName(),agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getModifyReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Edit record assertion failed");
        screenshot.captureScreen(driver, "Edit record", "AgentTeamMgmtTest");
    }
    
    @Test(priority=5)
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
    }
    
    @Test(priority=7)
    public void DeleteAgentTeamManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTeamManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentTeamMgmtDetails agentTeamMgmtDetails=new AgentTeamMgmtDetails(map);
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.verifydeleteNo(agentTeamMgmtDetails.getUpdateTeamName(), agentTeamMgmtDetails.getDeleteReason()));
        screenshot.captureScreen(driver, "delete No","AgentTeamMgmtTest");
        agentTeamManagementPage.deleteAgentTeamManagementRecord(agentTeamMgmtDetails.getUpdateTeamName(),agentTeamMgmtDetails.getDeleteReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"delete record assertion failed");
        screenshot.captureScreen(driver, "Verify Record Deleted", "AgentTeamMgmtTest");
        }
   
    @Test(priority=8)
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