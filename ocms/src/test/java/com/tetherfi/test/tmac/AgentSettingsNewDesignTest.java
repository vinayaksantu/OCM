package com.tetherfi.test.tmac;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class AgentSettingsNewDesignTest extends BaseTest {
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
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);
        agentTeamManagementPage.addNewAgentTeamManagementRecord(agentSettingsDetails.getTeamName());
        Assert.assertTrue(agentTeamManagementPage.verifyNewRecordCreated(),"Add New record assertion failed");
        homePage.navigateToOcmIconImg();
    }
    @BeforeMethod
    public void NavigateToAgentSettingsPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
        tmacPage.navigateToAgentSettingsPage();
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        Assert.assertTrue(agentSettingsPage.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");
    }
    @Test
    public void AddNewSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = {"DeleteAgentSettingsRecord"})
    public void EditSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyRecordUpdated(),"edit record assertion failed");
    }
    @Test(dependsOnMethods = {"EditSupervisorRecord"})
    public void DeleteSupervisorRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(),agentSettingsDetails.getDeleteReason());
        Assert.assertTrue(agentSettingsPage.verifyRecordDeleted(),"delete record assertion failed");}

    @Test(dependsOnMethods = {"AddNewSupervisorRecord"})
    public void AddNewAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = {"AddNewAgentSettingsRecord"})
    public void EditAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.editAgentSettingsRecord(agentSettingsDetails);
        Assert.assertTrue(agentSettingsPage.verifyRecordUpdated(),"edit record assertion failed");
    }
    @Test(dependsOnMethods = {"EditAgentSettingsRecord"})
    public void DeleteAgentSettingsRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);

        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        agentSettingsPage.deleteAgentSettingsRecord(agentSettingsDetails.getUsername(),agentSettingsDetails.getDeleteReason());
        Assert.assertTrue(agentSettingsPage.verifyRecordDeleted(),"delete record assertion failed");}
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
    @AfterClass
    public void DeleteAgentTeamManagementRecord() throws IOException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOcmIconImg();
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"tmac page assertion failed");
        tmacPage.navigateToAgentTeamManagementPage();
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team  management assertion failed");
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails=new AgentSettingsDetails(map);
        agentTeamManagementPage.deleteAgentTeamManagementRecord(agentSettingsDetails.getTeamName(),agentSettingsDetails.getDeleteReason());
        Assert.assertTrue(agentTeamManagementPage.verifyRecordDeleted(),"delete record assertion failed");
    }
}
