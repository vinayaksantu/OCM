package com.tetherfi.test.dashboard;

import com.tetherfi.pages.ChatCallbackDashboardPage;
import com.tetherfi.pages.ChatDashboardPage;
import com.tetherfi.pages.DashboardPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class ChatCallbackDashboardTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToChatDashboardPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToDashBoard();
        DashboardPage dashboardPage = PageFactory.createPageInstance(driver,DashboardPage.class);
        Assert.assertTrue(dashboardPage.isDashboardPageIsDisplayed(),"Dashboard Page assertion failed");
        dashboardPage.navighateToTab("Chat CallBack");
        ChatCallbackDashboardPage chatCallbackDashboardPage = PageFactory.createPageInstance(driver,ChatCallbackDashboardPage.class);
        Assert.assertTrue(chatCallbackDashboardPage.isChatCallbackDashboardPageDisplayed(),"Chat callback dashborad assertion failed");
    }
    @Test
    public void ChatDashboardPageDisplay() throws IOException {
        ChatCallbackDashboardPage chatCallbackDashboardPage = PageFactory.createPageInstance(driver,ChatCallbackDashboardPage.class);
        Assert.assertTrue(chatCallbackDashboardPage.verifyChatCallBackDashboradPageContents(),"chat callback dashboard page contents");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
        screenshot.captureScreen(driver, "", method.getName());
    }
}
