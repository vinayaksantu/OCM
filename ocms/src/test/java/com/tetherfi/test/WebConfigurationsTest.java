package com.tetherfi.test;

import com.tetherfi.model.user.WebConfigurationDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.WebConfigurationPage;
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

public class WebConfigurationsTest extends BaseTest{

    @BeforeMethod
    public void NavigateToUserManagementPage() {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToWebConfigurationPage();
        WebConfigurationPage webConfigurationPage=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
        Assert.assertTrue(webConfigurationPage.isWebConfigurationPageDisplayed(),"Web Configuration assertion failed");
    }
    @Test
    public void AddNewWebConfigurationRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WebConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        WebConfigurationDetails webConfigurationDetails=new WebConfigurationDetails(map);

        WebConfigurationPage webConfigurationPage=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
        webConfigurationPage.addNewWebConfigurationRecord(webConfigurationDetails);
        Assert.assertTrue(webConfigurationPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewWebConfigurationRecord")
    public void EditWebConfigurationRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WebConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        WebConfigurationDetails webConfigurationDetails=new WebConfigurationDetails(map);

        WebConfigurationPage webConfigurationPage=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
        webConfigurationPage.editWebConfigurationRecord(webConfigurationDetails);
        Assert.assertTrue(webConfigurationPage.verifyRecordUpdated(),"edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditWebConfigurationRecord")
    public void DeleteWebConfigurationRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\WebConfigurationData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        WebConfigurationDetails webConfigurationDetails=new WebConfigurationDetails(map);

        WebConfigurationPage webConfigurationPage=PageFactory.createPageInstance(driver,WebConfigurationPage.class);
        webConfigurationPage.deleteWebConfigurationRecord(webConfigurationDetails.getKey(),webConfigurationDetails.getDeleteReason());
        Assert.assertTrue(webConfigurationPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("WebConfigurationsTest",method.getName());
    }

}
