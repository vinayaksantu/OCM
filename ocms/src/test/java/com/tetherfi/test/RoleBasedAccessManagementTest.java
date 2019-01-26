package com.tetherfi.test;

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class RoleBasedAccessManagementTest extends BaseTest {
    @BeforeMethod
    public void NavigateToRoleBasedAccessManagementPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToRoleBasedAccessManagementPage();
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.isRoleBasedAccessManagementPageDisplayed(),"Role Based access management assertion failed");
    }
    @Test
    public void AddNewRoleBasedAccessManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);

        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.addNewRoleBasedAccessManagementRecord(userDetails.getRoleName());
        Assert.assertTrue(roleBasedAccessManagementPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewRoleBasedAccessManagementRecord")
    public void EditRoleBasedAccessManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);

        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.editRoleBasedAccessManagementRecord(userDetails.getRoleName(),userDetails.getUpdateRoleName(),userDetails.getModifyReason());
        Assert.assertTrue(roleBasedAccessManagementPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditRoleBasedAccessManagementRecord")
    public void DeleteRoleBasedAccessManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);

        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.deleteRoleBasedAccessManagementRecord(userDetails.getRoleName(),userDetails.getDeleteReason());
        Assert.assertTrue(roleBasedAccessManagementPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    Screenshot screenshot=new Screenshot();
    screenshot.captureScreen(driver,method.getName(),"RoleBasedAccessManagementTest");
    }
}
