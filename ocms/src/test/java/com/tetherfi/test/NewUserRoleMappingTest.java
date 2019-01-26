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

public class NewUserRoleMappingTest extends BaseTest {
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
        NewUserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,NewUserRoleMappingPage.class);
        Assert.assertTrue(userRoleMappingPage.isUserRoleMappingPageDisplayed(),"User role mapping page is displayed");
    }
    @Test
    public void AddNewSupervisorUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        NewUserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,NewUserRoleMappingPage.class);
        userRoleMappingPage.addNewUserRoleMappingRecord(userRoleMappingDetails);
        Assert.assertTrue(userRoleMappingPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewSupervisorUserRoleMappingRecord")
    public void EditSupervisorUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        NewUserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,NewUserRoleMappingPage.class);
        userRoleMappingPage.editNewUserRoleMappingRecord(userRoleMappingDetails);
        Assert.assertTrue(userRoleMappingPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditSupervisorUserRoleMappingRecord")
    public void DeleteSupervisorUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        NewUserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,NewUserRoleMappingPage.class);
        userRoleMappingPage.deleteUserRoleMappingRecord(userRoleMappingDetails.getBankUserName(),userRoleMappingDetails.getDeleteReason());
        Assert.assertTrue(userRoleMappingPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @Test
    public void AddNewAgentUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        NewUserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,NewUserRoleMappingPage.class);
        userRoleMappingPage.addNewUserRoleMappingRecord(userRoleMappingDetails);
        Assert.assertTrue(userRoleMappingPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewAgentUserRoleMappingRecord")
    public void EditAgentUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        NewUserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,NewUserRoleMappingPage.class);
        userRoleMappingPage.editNewUserRoleMappingRecord(userRoleMappingDetails);
        Assert.assertTrue(userRoleMappingPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditAgentUserRoleMappingRecord")
    public void DeleteAgentUserRoleMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserRoleMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(1);
        UserRoleMappingDetails userRoleMappingDetails = new UserRoleMappingDetails(map);

        NewUserRoleMappingPage userRoleMappingPage=PageFactory.createPageInstance(driver,NewUserRoleMappingPage.class);
        userRoleMappingPage.deleteUserRoleMappingRecord(userRoleMappingDetails.getBankUserName(),userRoleMappingDetails.getDeleteReason());
        Assert.assertTrue(userRoleMappingPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
        Screenshot screenshot=new Screenshot();
        screenshot.captureScreen(driver,method.getName(),"NewUserRoleMappingTest");
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