package com.tetherfi.test.dashboard;

import com.tetherfi.pages.DashboardPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.VoiceAgentServiceQueueDashboardPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class VoiceAgentServiceQueueTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToChatDashboardPage() {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToDashBoard();
        DashboardPage dashboardPage = PageFactory.createPageInstance(driver,DashboardPage.class);
        Assert.assertTrue(dashboardPage.isDashboardPageIsDisplayed(),"Dashboard Page assertion failed");
        dashboardPage.navighateToTab("Voice Agent Service Queue");
        VoiceAgentServiceQueueDashboardPage voiceAgentServiceQueueDashboardPage =PageFactory.createPageInstance(driver,VoiceAgentServiceQueueDashboardPage.class);
        Assert.assertTrue(voiceAgentServiceQueueDashboardPage.isVoiceAgentServiceQueueDashboardPageDisplayed(),"Voice Agent Service Queue dashborad assertion failed");
    }
    @Test
    public void VoiceAgentServiceQueueDashboardPageDisplay() {
        VoiceAgentServiceQueueDashboardPage voiceAgentServiceQueueDashboardPage =PageFactory.createPageInstance(driver,VoiceAgentServiceQueueDashboardPage.class);
        Assert.assertTrue(voiceAgentServiceQueueDashboardPage.verifyDashboardPageContents(),"chat dashboard page contents");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen("VoiceAgentServiceQueue", method.getName());
    }
}
