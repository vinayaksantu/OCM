package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;

public class AgentSettingsWMKTest extends BaseTest {
    @BeforeMethod
    public void NavigateToAgentSettingsPage(Method method) throws Exception {
    HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
    homePage.navigateToOCMPage();
    OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
    Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
    ocmHomePage.navigateToTab("TMAC");
    TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
    Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
    tmacPage.navigateToAgentSettingsPage();
    AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
    Assert.assertTrue(agentSettingsPage.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    @Test
    public void AddNewSupervisorRecord() throws Exception {
    	for(int i=0;i<300;i++) {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData (1).xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(i);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.addNewAgentSettingsRecord(agentSettingsDetails);
        }
        //Assert.assertTrue(agentSettingsPage.verifyNewRecordCreated(), "Add New record assertion failed");
    }
    
}
