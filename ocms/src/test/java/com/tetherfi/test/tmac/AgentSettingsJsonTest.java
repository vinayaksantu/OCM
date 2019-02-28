package com.tetherfi.test.tmac;

import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class AgentSettingsJsonTest extends BaseTest {

    String destinationFilePath = "D:/TetherfiWork/ProductOCM/scripts/ocms/src/test/resources/DownloadedFiles/Json/AgentSetting.json";
    String remoteFilePath = "\\\\172.16.2.16\\d$\\Products\\OCM\\UI\\CustomJSONConfigurations\\Admin%20Modules\\AgentSetting.json";

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

        FTPServer ftp=new FTPServer();
        ftp.transferFileFromRemote(remoteFilePath,destinationFilePath);
    }
    @Test
    public void VerifyJsonDataForMakerAndChecker(){
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(agentSettingsPage.verifyJsonDataForMakerAndChecker(json.getJsonMakerCheckerKeyData()),"JSON data Maker Checker assertion failed");
    }
    @Test
    public void VerifyJsonDataForGridColumnHidden(){
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.enableAllColumnsHeaders();
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(agentSettingsPage.verifyJsonDataForgridColumnHidden(json.getJsonGridColumnTitleKeyData("Hidden")),"JSON data grid column hidden assertion failed");
    }
    @Test
    public void VerifyJsonDataForColumnIncludeGrid(){
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(agentSettingsPage.verifyJsonDataForColumnIncludeGrid(json.getJsonGridColumnTitleKeyData("IncludeGrid")),"JSON data column include grid assertion failed");
    }
    @Test
    public void VerifyJsonDataForMandatoryField(){
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.selectAddNewAgentSettings();
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(agentSettingsPage.verifyJsonDataForMandatoryField(json.getJsonGridColumnTitleKeyData("Mandatory")),"JSON data mandatory field assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "AgentSettingsJsonTest",method.getName());
        driver.navigate().refresh();
    }
}
