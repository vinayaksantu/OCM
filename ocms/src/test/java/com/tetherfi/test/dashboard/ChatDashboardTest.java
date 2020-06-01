package com.tetherfi.test.dashboard;

import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class ChatDashboardTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToChatDashboardPage() {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToDashBoard();
        DashboardPage dashboardPage = PageFactory.createPageInstance(driver,DashboardPage.class);
        Assert.assertTrue(dashboardPage.isDashboardPageIsDisplayed(),"Dashboard Page assertion failed");
        dashboardPage.navighateToTab("Chat");
        ChatDashboardPage chatDashboardPage =PageFactory.createPageInstance(driver,ChatDashboardPage.class);
        Assert.assertTrue(chatDashboardPage.isChatDashboardPageDisplayed(),"Chat dashborad assertion failed");
    }
    @Test
    public void ChatDashboardPageDisplay() throws IOException {
        ChatDashboardPage chatDashboardPage =PageFactory.createPageInstance(driver,ChatDashboardPage.class);
        Assert.assertTrue(chatDashboardPage.verifyChatDashboardPageContents(),"chat dashboard page contents");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen("ChatDashboard", method.getName());
    }
}