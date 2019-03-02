package com.tetherfi.test.chat;

import com.tetherfi.model.chat.ChatTemplateDetails;
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

public class ChatTemplatesTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToChatTemplatesPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("Chat");
        ChatPage chatPage = PageFactory.createPageInstance(driver,ChatPage.class);
        Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
        chatPage.navigateToChatTemplatesPage();
        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        Assert.assertTrue(chatTemplatesPage.isChatTemplatePageDisplayed(),"chat templates page assertion failed");
    }
    @Test
    public void AddNewDepartmentRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.addNewDepartmentRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = {"AddNewDepartmentRecord","DeleteGroupRecord","DeleteChatTemplatesRecord"})
    public void EditDepartmentRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.editDepartmentRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = {"EditDepartmentRecord","DeleteGroupRecord","DeleteChatTemplatesRecord"})
    public void DeleteDepartmentRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"DeleteDepartment").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Departments");
        Assert.assertTrue(chatTemplatesPage.isDepartmentTabDisplayed());
        chatTemplatesPage.deleteDepartmentRecord(chatTemplateDetails.getDepartmentName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewDepartmentRecord")
    public void AddNewGroupsRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.addNewGroupRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = {"AddNewGroupsRecord","DeleteChatTemplatesRecord"})
    public void EditGroupRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.editGroupRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = {"EditGroupRecord","DeleteChatTemplatesRecord"})
    public void DeleteGroupRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"DeleteGroup").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.navigateToTab("Groups");
        Assert.assertTrue(chatTemplatesPage.isGroupTabDisplayed());
        chatTemplatesPage.deleteGroupRecord(chatTemplateDetails.getGroupName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewGroupsRecord")
    public void AddNewChatTemplatesRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.addNewChatTemplatesRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = "AddNewChatTemplatesRecord")
    public void EditChatTemplatesRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.editChatTemplatesRecord(chatTemplateDetails);
        Assert.assertTrue(chatTemplatesPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = "EditChatTemplatesRecord")
    public void DeleteChatTemplatesRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatTemplatesData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatTemplateDetails chatTemplateDetails= new ChatTemplateDetails(map);

        ChatTemplatesPage chatTemplatesPage = PageFactory.createPageInstance(driver,ChatTemplatesPage.class);
        chatTemplatesPage.deleteChatTemplatesRecord(chatTemplateDetails.getName(),chatTemplateDetails.getDeleteReason());
        Assert.assertTrue(chatTemplatesPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) throws InterruptedException {
        screenshot.captureScreen(driver, "", method.getName());
    }
}
