package com.tetherfi.test.tmac;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.TmacLoginPage;
import com.tetherfi.pages.TmacPopupPage;
import com.tetherfi.utility.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class AgentSettingsChatTest {
    protected WebDriver driver;
    @BeforeClass
    public void NavigateToTmacPopupPage() throws Exception {
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
        Map<String, String> map=new ExcelReader(filePath,"Login").getTestData().get(3);
        try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
        TmacLoginPage tmacloginPage = PageFactory.createPageInstance(driver,TmacLoginPage.class);
        Assert.assertTrue(tmacloginPage.checkPageLoadStatus(), "login page successful status");
        String filePath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentSettingsData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1, "Create").getTestData().get(0);
        AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map1);
        Map<String, String> map2 = new ExcelReader(filePath, "TMAC").getTestData().get(1);
        tmacloginPage.loginIntoTmac(agentSettingsDetails.getUsername(),map2.get("Station"));
        Assert.assertTrue(tmacloginPage.verifyUserLogged(),"Tmac login failed");
    }
    @BeforeMethod
    public void open(Method method){
        System.out.println("Started Executing : "+method.getName());
    }
    @Test//(dependsOnMethods = "com.tetherfi.test.tmac.AgentSettingsCreateTest.AddNewSupervisorRecord")
    public void VerifyLogintoTmac() {
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.switchToWindow(1);
        Assert.assertTrue(tmacPopupPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
    }
    @Test(dependsOnMethods = "VerifyLogintoTmac")
    public void VerifyChatTabEnabled() {
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        ChatAPI chatapi=new ChatAPI(driver);
        chatapi.open("https://172.16.2.16:15012/api/testapp.jsp");
        chatapi.initiateChat();
        chatapi.sendChatMessage("Testing chat msg");
        Assert.assertTrue(tmacPopupPage.verifyChatReceived(),"TMAC chat received assertion error");
    }
    @Test(dependsOnMethods = "VerifyChatTabEnabled")
    public void VerifyAutoAnswerTextChats() {
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        Assert.assertTrue(tmacPopupPage.verifyChatAutoAnswered(),"TMAC chat auto answered");
    }
    @Test(dependsOnMethods = "VerifyAutoAnswerTextChats")
    public void VerifyTextChatAutoACWEnabled(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.clickOnEndChat();
        Assert.assertEquals(tmacPopupPage.getCurrentStatus(),"ACW");
    }
    @Test(dependsOnMethods = "VerifyTextChatAutoACWEnabled")
    public void VerifyNumberOfChatTabAllowed(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        ChatAPI chatapi=new ChatAPI(driver);
        chatapi.switchToWindow(0);
        chatapi.initiateChat();
        chatapi.sendChatMessage("Testing second chat msg");
        Assert.assertTrue(tmacPopupPage.verifyNumberOfTabsOpen(2),"TMAC second chat received assertion error");
    }
    @Test(dependsOnMethods = "VerifyNumberOfChatTabAllowed")
    public void VerifyManualInDropwdownSelected(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.clickOnEndChat();
        tmacPopupPage.closeTab(1);
        Assert.assertEquals(tmacPopupPage.getCurrentStatus(),"ACW");
    }
    @Test(dependsOnMethods = "VerifyManualInDropwdownSelected")
    public void VerifyNumberOfChatTabExceeded(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        ChatAPI chatapi=new ChatAPI(driver);
        chatapi.switchToWindow(0);
        chatapi.initiateChat();
        chatapi.sendChatMessage("Testing Third chat msg");
        Assert.assertTrue(tmacPopupPage.verifyNumberOfTabsOpen(2),"TMAC third chat received assertion error");
    }
    @AfterMethod
    public void AfterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "AgentSettingsTmacTest",method.getName());
        System.out.println("Completed Executing : "+method.getName());
    }
    @AfterClass
    public void close(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.userLogout();
        driver.close();
    }
}
