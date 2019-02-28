package com.tetherfi.test.chat;

import com.tetherfi.model.chat.CallBackManagementDetails;
import com.tetherfi.pages.CallBackManagementPage;
import com.tetherfi.pages.ChatPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;

public class CallBackManagementTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToCallBackManagementPage() throws InterruptedException  {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("Chat");
        ChatPage chatPage=PageFactory.createPageInstance(driver,ChatPage.class);
        Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
        chatPage.navigateToCallBackMgmtPage();
        CallBackManagementPage callBackManagementPage=PageFactory.createPageInstance(driver,CallBackManagementPage.class);
        Assert.assertTrue(callBackManagementPage.isCallBackManagementPageDisplayed(),"call back  management assertion failed");
    }

    @Test
    public void ChangeStatusOpen() throws IOException, ParseException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallBackMgmtData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Open").getTestData().get(0);
        CallBackManagementDetails callBackManagementDetails=new CallBackManagementDetails(map);
        CallBackManagementPage callBackManagementPage=PageFactory.createPageInstance(driver,CallBackManagementPage.class);
        callBackManagementPage.changeStatusOpen(callBackManagementDetails);
       Assert.assertTrue(callBackManagementPage.verifyStatusChanged(),"Change status assertion failed");
    }
    @Test
    public void ChangeStatusClosed() throws IOException, ParseException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\CallBackMgmtData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Close").getTestData().get(0);
        CallBackManagementDetails callBackManagementDetails=new CallBackManagementDetails(map);
        CallBackManagementPage callBackManagementPage=PageFactory.createPageInstance(driver,CallBackManagementPage.class);
        callBackManagementPage.changeStatusClosed(callBackManagementDetails);
        Assert.assertTrue(callBackManagementPage.verifyStatusChanged(),"Change status assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
        screenshot.captureScreen(driver, "", method.getName());
    }
}
