package com.tetherfi.test.tmac;

import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.*;
import com.tetherfi.utility.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class AgentSettingsTmacTest {
    protected WebDriver driver;
    @BeforeClass
    public void NavigateToTmacPopupPage() throws IOException {
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
    @Test(dependsOnMethods = "com.tetherfi.test.tmac.AgentSettingsCreateTest.AddNewSupervisorRecord")
    public void VerifyLogintoTmac() {
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.switchToWindow(1);
        Assert.assertTrue(tmacPopupPage.isTmacPopUpDisplayed(),"TMAC popup not displayed");
    }
    @Test(dependsOnMethods = "VerifyLogintoTmac")
    public void VerifyVoiceTabEnabled() {
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        VoiceLibrary cl=new VoiceLibrary();
        cl.open();
        cl.loginAES("AVAYA#CMSIM#CSTA#AESSIM","Tetherfi","Tetherfi_01");
        cl.loginCallingStation("50004","40004");
        cl.initiateCall("49014");
        Assert.assertTrue(tmacPopupPage.verifyCallReceived(),"TMAC call received");
    }
    @Test(dependsOnMethods = "VerifyVoiceTabEnabled")
    public void VerifyAutoAnswerAllACDCalls() {
    TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
    Assert.assertTrue(tmacPopupPage.verifyCallAutoAnswered(),"TMAC call auto answered");
    }
    @Test(dependsOnMethods = "VerifyAutoAnswerAllACDCalls")
    public void VerifyGotoACWAfterEachACDCalls(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.dropCall();
        Assert.assertEquals(tmacPopupPage.getCurrentStatus(),"ACW");
    }
    @Test(dependsOnMethods = "VerifyGotoACWAfterEachACDCalls")
    public void VerifyNumberOfVoiceTabAllowed(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        VoiceLibrary cl=new VoiceLibrary();
        cl.initiateCall("40022");
        Assert.assertTrue(tmacPopupPage.verifyNumberOfTabsOpen(2),"TMAC call received");
    }
    @Test(dependsOnMethods = "VerifyNumberOfVoiceTabAllowed")
    public void VerifyGotoACWAfterAnyCalls(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.receiveCall(1);
        tmacPopupPage.dropCall(1);
        Assert.assertEquals(tmacPopupPage.getCurrentStatus(),"ACW");
    }
    @Test(dependsOnMethods = "VerifyGotoACWAfterAnyCalls")
    public void VerifyManualInDropwdownSelected(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.closeTab(1);
        Assert.assertEquals(tmacPopupPage.getCurrentStatus(),"ACW");
    }
    //@Test(dependsOnMethods = "VerifyGotoACWAfterAnyCalls")
    public void VerifyNumberOfVoiceTabExceeded(){
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.changeStatus("Available");
        VoiceLibrary cl=new VoiceLibrary();
        cl.initiateCall("49014");
        Assert.assertTrue(tmacPopupPage.verifyNumberOfTabsOpen(2),"TMAC call received");
    }
    @AfterMethod
    public void AfterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "AgentSettingsTmacTest",method.getName());
        System.out.println("Completed Executing : "+method.getName());
    }
    @AfterClass
    public void close(){
        VoiceLibrary cl=new VoiceLibrary();
        cl.stopDialling();
        cl.logout();
        cl.close();
        TmacPopupPage tmacPopupPage= PageFactory.createPageInstance(driver,TmacPopupPage.class);
        tmacPopupPage.userLogout();
        driver.close();
    }
}
