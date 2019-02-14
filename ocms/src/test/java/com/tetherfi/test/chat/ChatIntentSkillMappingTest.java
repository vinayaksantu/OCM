package com.tetherfi.test.chat;

import com.tetherfi.model.chat.ChatIntentSkillMappingDetails;
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

public class ChatIntentSkillMappingTest extends BaseTest {
	Screenshot screenshot=new Screenshot();
    @BeforeMethod
    public void NavigateToIntentSkillMappingPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("Chat");
        ChatPage chatPage = PageFactory.createPageInstance(driver,ChatPage.class);
        Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
        chatPage.navigateToChatIntentSkillMappingPage();
        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        Assert.assertTrue(intentSkillMappingPage.isChatIntentSkillMappingPageDisplayed(),"Intent skill mapping page assertion failed");
    }
    @Test
    public void AddNewIntentSkillMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\IntentSkillMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatIntentSkillMappingDetails intentSkillMappingDetails=new ChatIntentSkillMappingDetails(map);

        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        intentSkillMappingPage.addNewIntentSkillMappingRecord(intentSkillMappingDetails);
        Assert.assertEquals(intentSkillMappingPage.getMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewIntentSkillMappingRecord")
    public void EditIntentSkillMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SoiIntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatIntentSkillMappingDetails intentSkillMappingDetails=new ChatIntentSkillMappingDetails(map);

        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        intentSkillMappingPage.editIntentSkillMappingRecord(intentSkillMappingDetails);
        Assert.assertEquals(intentSkillMappingPage.getMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditIntentSkillMappingRecord")
    public void DeleteIntentSkillMappingRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SoiIntentMappingData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatIntentSkillMappingDetails intentSkillMappingDetails=new ChatIntentSkillMappingDetails(map);

        ChatIntentSkillMappingPage intentSkillMappingPage = PageFactory.createPageInstance(driver,ChatIntentSkillMappingPage.class);
        intentSkillMappingPage.deleteIntentSkillMappingRecord(intentSkillMappingDetails.getSegment(),intentSkillMappingDetails.getDeleteReason());
        Assert.assertEquals(intentSkillMappingPage.getMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
    	screenshot.captureScreen(driver, "", method.getName());
    }
}
