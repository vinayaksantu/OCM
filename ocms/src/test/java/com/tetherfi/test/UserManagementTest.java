package com.tetherfi.test;

import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;														
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserManagementTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

    @BeforeMethod
    public void NavigateToUserManagementPage() throws InterruptedException {
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToUserManagementPage();
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.isUserManagementPageDisplayed(),"User management assertion failed");
    }
    @Test(priority=1)
    public void UserManagementPage(){
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    	Assert.assertTrue(userManagementPage.verifylogo(),"User Management logo assertion failed");
        Assert.assertTrue(userManagementPage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(userManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "Maximize Window","UserManagementTest");
    	Assert.assertTrue(userManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "Minimize Window","UserManagementTest");
    }
    
    @Test(priority=2)
    public void AddNewUserManagementRecord() throws Exception {
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
    @Test(priority=3)
    public void AddInvalidRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.DuplicateRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(), "Duplicate Record Creation assertion failed");
    	screenshot.captureScreen(driver, "Duplicate Record","UserManagementTest");
    	Map<String, String> map1 = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails1=new UserDetails(map1);
    	userManagementPage.addUserManagementRecord(userDetails1.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(), "Invalid Record creation assertion failed");
    	screenshot.captureScreen(driver, "Invalid Record for domainID","UserManagementTest");
    	Map<String, String> map2 = new ExcelReader(filePath,"Create").getTestData().get(2);
        UserDetails userDetails2=new UserDetails(map2);
        userManagementPage.addUserManagementRecord(userDetails2.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(), "Invalid Record creation assertion failed");
   		screenshot.captureScreen(driver, "Invalid Record for userid","UserManagementTest");
   		userManagementPage.addinvalidrecord();
   		Assert.assertTrue(userManagementPage.verifyErrorMessage(), "Invalid Record Creation failed");
   		screenshot.captureScreen(driver, "Invalid Record","UserManagementTest");
    }
    @Test(priority=4)
    public void EditUserManagementRecord() throws Exception {
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
    @Test(priority=5)
    public void EditInvalidUserManagementRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.editUserManagementRecord(userDetails.getUpdatedUserId(),userDetails.getModifyReason(),userDetails.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(),"Edit Invalid record assertion failed");
   		screenshot.captureScreen(driver, "Invalid Record","UserManagementTest");
   		Map<String, String> map1 = new ExcelReader(filePath,"Edit").getTestData().get(2);
        UserDetails userDetails1=new UserDetails(map1);
        userManagementPage.editUserManagementRecord(userDetails1.getUpdatedUserId(),userDetails1.getModifyReason(),userDetails1.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(),"Edit Invalid record assertion failed");
   		screenshot.captureScreen(driver, "Invalid Record2","UserManagementTest");
   		Map<String, String> map2 = new ExcelReader(filePath,"Edit").getTestData().get(3);
        UserDetails userDetails2=new UserDetails(map2);
        userManagementPage.editInvalidUserManagementRecord(userDetails2.getUpdatedUserId(),userDetails2.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(),"Edit Invalid record assertion failed");
   		screenshot.captureScreen(driver, "Invalid Record3","UserManagementTest");
        Map<String, String> map3 = new ExcelReader(filePath,"Edit").getTestData().get(3);
        UserDetails userDetails3=new UserDetails(map3);
        userManagementPage.editInvalidUserManagementRecord2(userDetails3.getModifyReason(),userDetails3.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(),"Edit Invalid record assertion failed");
   		screenshot.captureScreen(driver, "Invalid Record4","UserManagementTest");
   		Map<String, String> map4 = new ExcelReader(filePath,"Edit").getTestData().get(4);
        UserDetails userDetails4=new UserDetails(map4);
   		userManagementPage.editUserManagementRecord(userDetails4.getUpdatedUserId(),userDetails4.getModifyReason(),userDetails4.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(),"Duplicate assertion failed");
   		screenshot.captureScreen(driver, "Duplicate Record","UserManagementTest");
    }
    
    @Test(priority=6)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);	
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertFalse(userManagementPage.clearAll(userDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen(driver, "clearall","UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclose());
        screenshot.captureScreen(driver, "SearchClose","UserManagementTest");
    }
    
    @Test(priority=7)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);	
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifySearchIsNotEqualTo(userDetails.getUserId()));
    }
     
     @Test(priority=8)
     public void VerifySearchContains() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchContains(userDetails.getUserId()));
     }
     
     @Test(priority=9)
     public void VerifySearchDoesNotContains() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchDoesNotContains(userDetails.getUserId()));
     }
     
     @Test(priority=10)
     public void VerifySearchStartsWith() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchStartsWith(userDetails.getUserId()));
     }
     
     @Test(priority=11)
     public void VerifySearchEndsWith() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchEndsWith(userDetails.getUserId()));
     }
    @Test(priority=12)
    public void ClearSearch() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);	
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyinvalidsearch(userDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen(driver, "Invalid Search", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclearsearch(), "Clear All Assertion Failed");
        screenshot.captureScreen(driver, "Clear Search", "UserManagementTest");
    }
    @Test(priority=13)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        UserDetails userDetails=new UserDetails(map);	
    	Assert.assertTrue(userManagementPage.verifyDatabase(userDetails.getQuery()));
    }
   @Test(priority=14)
    public void DeleteUserManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.deleteUserManagementRecordNoBtn(userDetails.getUserId(),userDetails.getDeleteReason()));
        screenshot.captureScreen(driver, "deleteUserManagementRecordNoBtn", "UserManagementTest");
        userManagementPage.deleteUserManagementRecord(userDetails.getUserId(),userDetails.getDeleteReason());
        Assert.assertTrue(userManagementPage.verifyRecordDeleted(),"Delete record assertion failed");
        screenshot.captureScreen(driver, "deleteUserManagementRecord", "UserManagementTest");
    }
   @Test(priority=15)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=16)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Management.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    Assert.assertTrue(userManagementPage.verifyexportToExcelSheet(maplist));
    }
   
   @Test(priority=17)
    public void GroupBy()
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    	Assert.assertTrue(userManagementPage.groupby());
    	screenshot.captureScreen(driver, "GroupBy", "UserManagementTest");
    	Assert.assertTrue(userManagementPage.groupby());
    	screenshot.captureScreen(driver, "AlreadyGroupBy", "UserManagementTest");	
    }
    @Test(priority=18)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    	Assert.assertTrue(userManagementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    	screenshot.captureScreen(driver, "VerifyArrowMoveForPreviousAndNextPage", "UserManagementTest");	
    }
    @Test(priority=19)
    public void VerifyArrowMoveForFirstAndLastPage() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    	screenshot.captureScreen(driver, "VerifyArrowMoveForFirstAndLastPage", "UserManagementTest");	

    }
    @Test(priority=20)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    	screenshot.captureScreen(driver, "VerifyTotalNumberOfItemsPerPageDetails", "UserManagementTest");	
    }
    
    @Test(priority=21)
    public void VerifyNumberOfItemsPerPageSelection() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
        screenshot.captureScreen(driver, "VerifyNumberOfItemsPerPageSelection","UserManagementTest");

    }
    @Test(priority=22)
    public void VerifyDropdownForAllTheColumns() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(priority=23)
    public void VerifyColumnsHeaderEnable() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(priority=24)
    public void VerifyColumnsHeaderDisable() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertFalse(userManagementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    @Test(priority=25)
    public void SortingByAscending() throws Exception
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.SortByAscending();
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Management (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=26)
    public void SortingByDescending() throws Exception
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.SortByDescending();
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Management (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=27)
    public void ExporttoExcelWithoutData() throws Exception
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.ExporttoExcelWithoutData(userDetails));
    }
    @Test(priority=28)
    public void VerifyPageBasedUserAccess() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifypagebaseduseraccess(userDetails));
        screenshot.captureScreen(driver,"VerifyPageBasedUserAccess", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclosebutton());

    }
    @Test(priority=29)
    public void VerifyAccessCheckBox() throws Exception
    { 
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "UserManagementTest");
    }
    @Test(priority=30)
    public void VerifyAdminPageButton() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifycancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifysavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "UserManagementTest");
    }
    @Test(priority=31)
    public void Verifyunsuccefullchanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(userManagementPage.verifyunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclosebutton());
    }
    
    @Test(priority=32)
    public void VerifyReportsAccessCheckBox() throws Exception
    { 
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyreportsaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "UserManagementTest");
    }
    @Test(priority=33)
    public void VerifyReportsPageButton() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyReportscancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyReportssavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "UserManagementTest");
    }
    @Test(priority=34)
    public void VerifyReportsunsuccefullchanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyreportsunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(userManagementPage.verifyreportsunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclosebutton());
    }
    @Test(priority=35)
    public void VerifyDashboardAccessCheckbox() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifydashboardaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "UserManagementTest");
    }
    @Test(priority=36)
    public void VerifyDashboardPageButton() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyDashboardcancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyDashboardsavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "UserManagementTest");
    }
    @Test(priority=37)
    public void VerifyDashboardunsuccefullchanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifydashboardunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(userManagementPage.verifydashboardunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclosebutton());
    }
    
    @Test(priority=38)
    public void VerifyOtherApplicationAccessCheckbox() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyotherapplicationaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclosebutton());
        Thread.sleep(1000);

    }
   @Test(priority=39)
    public void VerifyOtherApplicationPageButton() throws Exception {
   		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyotherapplicationcancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyotherapplicationsavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "UserManagementTest");
    }
    @Test(priority=40)
    public void VerifyOtherApplicationunsuccefullchanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyotherapplicationunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(userManagementPage.verifyotherapplicationunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "UserManagementTest");
        Assert.assertTrue(userManagementPage.verifyclosebutton());
    }
    @AfterMethod
    public void afterEachMethod(ITestResult result,Method method){
      	 if(ITestResult.FAILURE==result.getStatus()){
      		 try{
      			 screenshot.captureScreen(driver, method.getName(),"UserManagementTest");
      		 }
      		catch (Exception e){
      		 System.out.println("Exception while taking screenshot "+e.getMessage());
      		 } 
      		 driver.navigate().refresh();
      		 }
    }
}
