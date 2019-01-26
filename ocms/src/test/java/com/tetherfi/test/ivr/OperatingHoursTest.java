package com.tetherfi.test.ivr;

import com.tetherfi.model.ivr.OperatingHoursDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
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

public class OperatingHoursTest extends BaseTest {
	Screenshot screenshot=new Screenshot();
    @BeforeMethod
    public void NavigateToOperatingHoursPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage ivrPage = PageFactory.createPageInstance(driver, IvrPage.class);
        Assert.assertTrue(ivrPage.isIVRPageDisplayed(), "ivr page assertion failed");
        ivrPage.navigateToOperatingHoursPage();
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        Assert.assertTrue(operatingHoursPage.isOperatingHoursPageDisplayed(), "Operating hours page assertion failed");
    }

    @Test
    public void AddNewOperatingHoursRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        OperatingHoursDetails operatingHoursDetails = new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage = PageFactory.createPageInstance(driver, OperatingHoursPage.class);
        operatingHoursPage.addNewOperatingHoursRecord(operatingHoursDetails);
        Assert.assertTrue(operatingHoursPage.verifyNewRecordCreated(), "Add New record assertion failed");
    }
    @Test(dependsOnMethods = {"AddNewOperatingHoursRecord"})
    public void EditOperatingHoursRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        operatingHoursPage.editOperatingHoursRecord(operatingHoursDetails);
        Assert.assertTrue(operatingHoursPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = {"EditOperatingHoursRecord"})
    public void DeleteAgentTeamManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\OperatingHoursData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        OperatingHoursDetails operatingHoursDetails=new OperatingHoursDetails(map);
        OperatingHoursPage operatingHoursPage=PageFactory.createPageInstance(driver,OperatingHoursPage.class);
        operatingHoursPage.deleteOperatingHoursRecord(operatingHoursDetails);
        Assert.assertTrue(operatingHoursPage.verifyRecordDeleted(),"delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
    	screenshot.captureScreen(driver, "", method.getName());
    }


}

