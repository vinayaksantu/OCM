package com.tetherfi.test;

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.UserManagementPage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class UserManagementTest extends BaseTest{
	Screenshot screenshot=new Screenshot();

    @BeforeMethod
    public void NavigateToUserManagementPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToUserManagementPage();
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.isUserManagementPageDisplayed(),"User management assertion failed");
    }
   // @Test(priority=1)
    public void UserManagementPage(){
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    	Assert.assertTrue(userManagementPage.verifylogo(),"User Management logo assertion failed");
        Assert.assertTrue(userManagementPage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(userManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "Maximize Window","UserManagementTest");
    	Assert.assertTrue(userManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "Minimize Window","UserManagementTest");
    }
    
    //@Test(priority=2)
    public void AddNewUserManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.addNewCancel(userDetails.getUserId()), "Add New Cancel Assertion Failed");
    	screenshot.captureScreen(driver, "AddNewCancel","UserManagementTest");
        userManagementPage.addNewUserManagementRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.verifyNewRecordCreated(),"Add New record assertion failed");
    	screenshot.captureScreen(driver, "Record Created Successfully","UserManagementTest");
    }
    //@Test(priority=3)
    public void AddInvalidRecord() throws IOException {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.DuplicateRecord(userDetails.getUserId());
        Assert.assertFalse(userManagementPage.verifyNewRecordCreated(), "Duplicate Record Creation assertion failed");
    	screenshot.captureScreen(driver, "Duplicate Record","UserManagementTest");
    	Map<String, String> map1 = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails1=new UserDetails(map1);
    	userManagementPage.addUserManagementRecord(userDetails1.getUserId());
        Assert.assertFalse(userManagementPage.verifyNewRecordCreated(), "Invalid Record creation assertion failed");
    	screenshot.captureScreen(driver, "Invalid Record for domainID","UserManagementTest");
    	Map<String, String> map2 = new ExcelReader(filePath,"Create").getTestData().get(2);
        UserDetails userDetails2=new UserDetails(map2);
        userManagementPage.addUserManagementRecord(userDetails2.getUserId());
        Assert.assertFalse(userManagementPage.verifyNewRecordCreated(), "Invalid Record creation assertion failed");
   		screenshot.captureScreen(driver, "Invalid Record for userid","UserManagementTest");
   		userManagementPage.addinvalidrecord();
   		Assert.assertFalse(userManagementPage.verifyNewRecordCreated(), "Invalid Record Creation failed");
   		screenshot.captureScreen(driver, "Invalid Record","UserManagementTest");
    }
    
    @Test(priority=4)
    public void EditUserManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.editcancel(userDetails.getUpdatedUserId(),userDetails.getModifyReason(),userDetails.getUserId()), "Edit record cancel assertion failed");
   		screenshot.captureScreen(driver, "Edit Cancel","UserManagementTest");
        userManagementPage.editUserManagementRecord(userDetails.getUpdatedUserId(),userDetails.getModifyReason(),userDetails.getUserId());
        Assert.assertTrue(userManagementPage.verifyRecordUpdated(),"Edit record assertion failed");
   		screenshot.captureScreen(driver, "Record Updated","UserManagementTest");
    }
    
    //@Test(dependsOnMethods = "EditUserManagementRecord")
    public void DeleteUserManagementRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);

        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.deleteUserManagementRecord(userDetails.getUserId(),userDetails.getDeleteReason());
        Assert.assertTrue(userManagementPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(ITestResult result,Method method){
      	 if(ITestResult.FAILURE==result.getStatus()){
      		 try{
      			 screenshot.captureScreen(driver, method.getName(),"TmacBroadCastMsgTest");
      		 }
      		catch (Exception e){
      		 System.out.println("Exception while taking screenshot "+e.getMessage());
      		 } 
      		 driver.navigate().refresh();
      		 }
    }
}
