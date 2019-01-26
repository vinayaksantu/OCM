package com.tetherfi.test.tmac;

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
import java.util.List;
import java.util.Map;

public class TmacBroadCastMsgTest extends BaseTest {
	Screenshot screenshot=new Screenshot();
    @BeforeClass
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        agentTeamManagementPage.addNewAgentTeamManagementRecord(tmacBroadCastMsgDetails.getLevel(),tmacBroadCastMsgDetails.getCountry(),tmacBroadCastMsgDetails.getDivision(),tmacBroadCastMsgDetails.getDepartment(),tmacBroadCastMsgDetails.getTeamName());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Add New record assertion failed");
        driver.navigate().refresh();
        homePage.navigateToOcmIconImg();
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
        screenshot.captureScreen(driver, "TMAC Page","TmacBroadCastMsgTest");
        tmacPage.navigateToTmacBroadcastMsgPage();
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.isTmacBroadcastMsgPageDisplayed(), "Operating hours page assertion failed");
        screenshot.captureScreen(driver, "TMACBroadcastMsg Page","TmacBroadCastMsgTest");
    }
    @Test(priority=1)
    public void TmacBroadCastMsgPage()
    {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    	Assert.assertTrue(tmacBroadCastMsgPage.verifylogo(),"Tmac Broadcast Message logo assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyMessagelabel(), "Message button assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyTeamNamelabel(), "Team Name button assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyStatuslabel(), "Status button assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifygridcontent(),"grid container assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyLastchangedByenable(),"lastchangedby assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyLastchangedBydisable(),"lastchangedby assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyLastchangedonenable(), "lastchangedon assertion failed");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyLastchangedondisable(),"lastchangedby assertion failed");
    	Assert.assertTrue(tmacBroadCastMsgPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "maximize window","TmacBroadCastMsgTest");
    	Assert.assertTrue(tmacBroadCastMsgPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "minimize window","TmacBroadCastMsgTest");
    }
    @Test(priority=2)
    public void AddNewTmacBroadCastMsg() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.addNewCancel(tmacBroadCastMsgDetails), "record cancelled assertion failed");
        screenshot.captureScreen(driver, "Add New Cancel","TmacBroadCastMsgTest");
        tmacBroadCastMsgPage.addTmacBroadcastMsg(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyNewRecordCreated(),"add record assertion failed");
        screenshot.captureScreen(driver, "Record Created Successfully","TmacBroadCastMsgTest");
    }
    @Test(priority=3)
    public void AddInvalidRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        tmacBroadCastMsgPage.duplicateRecord(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Duplicate Assertion failed");
        screenshot.captureScreen(driver, "Duplicate Record","TmacBroadCastMsgTest");
        tmacBroadCastMsgPage.addInvalidRecord(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Invalid Record Assertion failed");
        screenshot.captureScreen(driver, "Invalid Record when message is null","TmacBroadCastMsgTest");
        tmacBroadCastMsgPage.addInvalidRecord1(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Invalid Record Assertion failed");
        screenshot.captureScreen(driver, "Invalid record when status is null","TmacBroadCastMsgTest");
        tmacBroadCastMsgPage.addInvalidRecord2(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyErrorMessage(),"Invalid Record Assertion failed");
        screenshot.captureScreen(driver, "Invalid Record when Teamname is null","TmacBroadCastMsgTest");
    }
    @Test(priority=4)
    public void EditTmacBroadCastMsg() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.editcancel(tmacBroadCastMsgDetails),"Cancel assertion failed");
        screenshot.captureScreen(driver, "Cancelled Record","TmacBroadCastMsgTest");
        tmacBroadCastMsgPage.editTmacBroadcastMsg(tmacBroadCastMsgDetails);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyRecordUpdated(),"Record Updated assertion failed");
        screenshot.captureScreen(driver, "Updated Record","TmacBroadCastMsgTest");
    }
    
    @Test(priority=5)
    public void searchPage() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertFalse(tmacBroadCastMsgPage.clearAll(tmacBroadCastMsgDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","TmacBroadCastMsgTest");
        Assert.assertTrue(tmacBroadCastMsgPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","TmacBroadCastMsgTest");	
    }
    @Test(priority=6)
    public void SearchClearSearch() throws Exception
    {
    String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
    Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
    TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
    TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    Assert.assertTrue(tmacBroadCastMsgPage.verifyinvalidsearch(tmacBroadCastMsgDetails), "InvalidSearchAssertionFailed");
    Assert.assertTrue(tmacBroadCastMsgPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=7)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        Assert.assertTrue(tmacBroadCastMsgPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=8)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TMAC Broadcast Message.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    Assert.assertTrue(tmacBroadCastMsgPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=9)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacBroadcastMsgData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
        TmacBroadCastMsgDetails tmacBroadCastMsgDetails=new TmacBroadCastMsgDetails(map);
    	Assert.assertTrue(tmacBroadCastMsgPage.verifyDatabase(tmacBroadCastMsgDetails.getQuery()));
    }
    
    @Test(priority=10)
    public void GroupBy()
    {
        TmacBroadCastMsgPage tmacBroadCastMsgPage  = PageFactory.createPageInstance(driver, TmacBroadCastMsgPage.class);
    	Assert.assertTrue(tmacBroadCastMsgPage.groupby());
    	
    }
    
    
    @AfterMethod
    public void afterEachMethod(ITestResult result){
   	 if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, "failed","TmacBroadCastMsgTest");
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
