package com.tetherfi.test;

import com.tetherfi.model.user.AgentSkillAssignmentDetails;
import com.tetherfi.pages.AgentSkillAssignmentPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
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
    @Test
    public void EditAgentListRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        AgentSkillAssignmentDetails agentSkillAssignmentDetails= new AgentSkillAssignmentDetails(map);

        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.assignSkillToAgent(agentSkillAssignmentDetails);
        Assert.assertTrue(agentSkillAssignmentPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    @Test
    public void AddMultiSkillRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentSkillAssignmentData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
        AgentSkillAssignmentDetails agentSkillAssignmentDetails= new AgentSkillAssignmentDetails(map);

        AgentSkillAssignmentPage agentSkillAssignmentPage=PageFactory.createPageInstance(driver,AgentSkillAssignmentPage.class);
        agentSkillAssignmentPage.navigateToMultiSkillTab();
        agentSkillAssignmentPage.assignSkillToMultipleAgent(agentSkillAssignmentDetails);
        Assert.assertTrue(agentSkillAssignmentPage.verifyRecordUpdated(),"Assign skill assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        
        screenshot.captureScreen(driver, method.getName(), "AgentSkillAssignmentTest");
        driver.navigate().refresh();
    }

}
