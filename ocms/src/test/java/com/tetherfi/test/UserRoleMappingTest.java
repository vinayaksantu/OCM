package com.tetherfi.test;

import com.tetherfi.model.user.UserRoleMappingDetails;
import com.tetherfi.pages.*;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class UserRoleMappingTest extends BaseTest {
    @BeforeClass
    public void AddNewAgentTeamManagementRecord() throws IOException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"tmac page assertion failed");
        tmacPage.navigateToAgentTeamManagementPage();
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team  management assertion failed");
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails=new UserRoleMappingDetails(map);
        agentTeamManagementPage.addNewAgentTeamManagementRecord(userRoleMappingDetails.getLevel(),userRoleMappingDetails.getCountry(),userRoleMappingDetails.getDivision(),userRoleMappingDetails.getDepartment(),userRoleMappingDetails.getTeamName());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"Add New record assertion failed");
        driver.navigate().refresh();
        homePage.navigateToOcmIconImg();
    }
    @BeforeMethod
    public void NavigateToUserRoleMappingPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("Home");
        ocmHomePage.navigateToUserRoleMappingPage();
        UserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
        Assert.assertTrue(userRoleMappingPage.isUserRoleMappingPageDisplayed(),"User role mapping page is displayed");
            }
    @Test
    public void AddNewUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        UserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
        userRoleMappingPage.addNewUserRoleMappingRecord(userRoleMappingDetails);
        Assert.assertTrue(userRoleMappingPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewUserRoleMappingRecord")
    public void EditUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        UserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
        userRoleMappingPage.editUserRoleMappingRecord(userRoleMappingDetails);
        Assert.assertTrue(userRoleMappingPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditUserRoleMappingRecord")
    public void DeleteUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        UserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,UserRoleMappingPage.class);
        userRoleMappingPage.deleteUserRoleMappingRecord(userRoleMappingDetails.getBankUserName(),userRoleMappingDetails.getDeleteReason());
        Assert.assertTrue(userRoleMappingPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen(driver,method.getName(),"UserRoleMappingTest");
        driver.navigate().refresh();
    }
    @AfterClass
    public void DeleteAgentTeamManagementRecord() throws IOException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOcmIconImg();
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"tmac page assertion failed");
        tmacPage.navigateToAgentTeamManagementPage();
        AgentTeamManagementPage agentTeamManagementPage=PageFactory.createPageInstance(driver,AgentTeamManagementPage.class);
        Assert.assertTrue(agentTeamManagementPage.isAgentTeamManagementPageDisplayed(),"Agent Team  management assertion failed");
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);
        agentTeamManagementPage.deleteAgentTeamManagementRecord(userRoleMappingDetails.getTeamName(),userRoleMappingDetails.getDeleteReason());
        Assert.assertTrue(agentTeamManagementPage.verifyMessage(),"delete record assertion failed");
        driver.navigate().refresh();
    }
}