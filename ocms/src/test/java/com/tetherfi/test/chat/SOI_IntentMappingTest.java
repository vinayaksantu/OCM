package com.tetherfi.test.chat;

import com.tetherfi.model.chat.SOI_IntentMappingDetails;
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

public class SOI_IntentMappingTest extends BaseTest {
	Screenshot screenshot=new Screenshot();
    @BeforeMethod
    public void NavigateToSOIIntentMappingPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("Chat");
        ChatPage chatPage = PageFactory.createPageInstance(driver,ChatPage.class);
        Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
        chatPage.navigateToSoiIntentMappingPage();
        SOI_IntentMappingPage soi_intentMappingPage = PageFactory.createPageInstance(driver,SOI_IntentMappingPage.class);
        Assert.assertTrue(soi_intentMappingPage.isSoiIntentMappingPageDisplayed(),"soi Intent mapping page assertion failed");
    }
    @Test
    public void AddNewSIO_IntentMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SoiIntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        SOI_IntentMappingDetails soi_intentMappingDetails=new SOI_IntentMappingDetails(map);

        SOI_IntentMappingPage soi_intentMappingPage = PageFactory.createPageInstance(driver,SOI_IntentMappingPage.class);
        soi_intentMappingPage.addNewSoiIntentMappingRecord(soi_intentMappingDetails);
        Assert.assertEquals(soi_intentMappingPage.getMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewSIO_IntentMappingRecord")
    public void EditSIO_IntentMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SoiIntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        SOI_IntentMappingDetails soi_intentMappingDetails=new SOI_IntentMappingDetails(map);

        SOI_IntentMappingPage soi_intentMappingPage = PageFactory.createPageInstance(driver,SOI_IntentMappingPage.class);
        soi_intentMappingPage.editSoiIntentMappingRecord(soi_intentMappingDetails);
        Assert.assertEquals(soi_intentMappingPage.getMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditSIO_IntentMappingRecord")
    public void DeleteSIO_IntentMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SoiIntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        SOI_IntentMappingDetails soi_intentMappingDetails=new SOI_IntentMappingDetails(map);

        SOI_IntentMappingPage soi_intentMappingPage = PageFactory.createPageInstance(driver,SOI_IntentMappingPage.class);
        soi_intentMappingPage.deleteWaitTimeColorConfigRecord(soi_intentMappingDetails.getSegment(),soi_intentMappingDetails.getDeleteReason());
        Assert.assertEquals(soi_intentMappingPage.getMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}
