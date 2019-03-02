package com.tetherfi.test;

import com.tetherfi.model.user.CmDataSyncDetails;
import com.tetherfi.pages.CMDataSyncPage;
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

public class CmDataSyncTest extends BaseTest {
    @BeforeMethod
    public void NavigateToCmDataSyncPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToCMDataSyncPage();
        CMDataSyncPage cmDataSyncPage=PageFactory.createPageInstance(driver,CMDataSyncPage.class);
        Assert.assertTrue(cmDataSyncPage.isCMDataSyncPageDisplayed(),"CMDataSync assertion failed");
    }
    @Test
    public void AgentSynchronization() throws IOException{
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CmDataSyncData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"AgentSync").getTestData().get(0);
        CmDataSyncDetails cmDataSyncDetails=new CmDataSyncDetails(map);
        CMDataSyncPage cmDataSyncPage=PageFactory.createPageInstance(driver,CMDataSyncPage.class);
        cmDataSyncPage.agentVdnSynchronization(cmDataSyncDetails);
        Assert.assertTrue(cmDataSyncPage.verifyAgentVdnSynchronized("Agent Sync Thread Started Successfully"),"Add New record assertion failed");
    }
    @Test
    public void VdnSynchronization() throws IOException{
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\CmDataSyncData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"VdnSync").getTestData().get(0);
        CmDataSyncDetails cmDataSyncDetails=new CmDataSyncDetails(map);
        CMDataSyncPage cmDataSyncPage=PageFactory.createPageInstance(driver,CMDataSyncPage.class);
        cmDataSyncPage.agentVdnSynchronization(cmDataSyncDetails);
        Assert.assertTrue(cmDataSyncPage.verifyAgentVdnSynchronized("VDN Sync Thread Started Successfully"),"Add New record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen(driver,method.getName(),"CmDataSyncTest");
    }

}
