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
    	screenshot.captureScreen("UserManagementTest","Maximize Window");
    	Assert.assertTrue(userManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("UserManagementTest","Minimize Window");
    }
    
    @Test(priority=2)
    public void AddNewUserManagementCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.addNewCancel(userDetails.getUserId()), "Add New Cancel Assertion Failed");
      }
    
    @Test(priority=3)
    public void AddNewUserManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    	userManagementPage.addNewUserManagementRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    @Test(priority=4)
    public void AddDuplicateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
   	 	userManagementPage.addNewUserManagementRecord(userDetails.getUserId());
        Assert.assertTrue(userManagementPage.verifyErrorMessage(), "Duplicate Record Creation assertion failed");
     }
    
    @Test(priority=5)
    public void EditUserManagementCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.editcancel(userDetails.getUpdatedUserId(),userDetails.getModifyReason(),userDetails.getUserId()), "Edit record cancel assertion failed");
       }
    
    @Test(priority=6)
    public void EditUserManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.editUserManagementRecord(userDetails.getUpdatedUserId(),userDetails.getModifyReason(),userDetails.getUserId());
        Assert.assertTrue(userManagementPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    
    
    @Test(priority=7)
    public void searchPage() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);	
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertFalse(userManagementPage.clearAll(userDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("UserManagementTest","clearall");
        Assert.assertTrue(userManagementPage.verifyclose());
    }
    
    @Test(priority=8)
    public void VerifySearchIsNotEqualTo() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);	
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifySearchIsNotEqualTo(userDetails.getUserId()));
    }
     
     @Test(priority=9)
     public void VerifySearchContains() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchContains(userDetails.getUserId()));
     }
     
     @Test(priority=10)
     public void VerifySearchDoesNotContains() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchDoesNotContains(userDetails.getUserId()));
     }
     
     @Test(priority=11)
     public void VerifySearchStartsWith() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchStartsWith(userDetails.getUserId()));
     }
     
     @Test(priority=12)
     public void VerifySearchEndsWith() throws Exception {
     	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
         Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(4);
         UserDetails userDetails=new UserDetails(map);	
         UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
         Assert.assertTrue(userManagementPage.verifySearchEndsWith(userDetails.getUserId()));
     }
    @Test(priority=13)
    public void ClearSearch() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);	
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyinvalidsearch(userDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("UserManagementTest","Invalid Search");
        Assert.assertTrue(userManagementPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    @Test(priority=14)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        UserDetails userDetails=new UserDetails(map);	
    	Assert.assertTrue(userManagementPage.verifyDatabase(userDetails.getQuery()));
    }
    
   @Test(priority=15)
    public void DeleteUserManagementCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.deleteUserManagementRecordNoBtn(userDetails.getUserId(),userDetails.getDeleteReason()));
    }
   
   @Test(priority=16)
   public void DeleteUserManagementRecord() throws Exception {
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
       UserDetails userDetails=new UserDetails(map);
       UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
       userManagementPage.deleteUserManagementRecord(userDetails.getUserId(),userDetails.getDeleteReason());
       Assert.assertTrue(userManagementPage.verifyRecordDeleted(),"Delete record assertion failed");
   }
   
    @Test(priority=17)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=18)
    public void ExportToExcelData() throws Exception
    {String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Management.xlsx";
    List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    Assert.assertTrue(userManagementPage.verifyexportToExcelSheet(maplist));
    }
   
   @Test(priority=19)
    public void GroupBy()
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    	Assert.assertTrue(userManagementPage.groupby());
    	Assert.assertTrue(userManagementPage.groupby());
    }
   
    @Test(priority=20)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
    	Assert.assertTrue(userManagementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=21)
    public void VerifyArrowMoveForFirstAndLastPage() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=22)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=23)
    public void VerifyNumberOfItemsPerPageSelection() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=24)
    public void VerifyDropdownForAllTheColumns() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    @Test(priority=25)
    public void VerifyColumnsHeaderEnable() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertTrue(userManagementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    @Test(priority=26)
    public void VerifyColumnsHeaderDisable() {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        Assert.assertFalse(userManagementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    @Test(priority=27)
    public void SortingByAscending() throws Exception
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.SortByAscending();
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Management (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=28)
    public void SortingByDescending() throws Exception
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        userManagementPage.SortByDescending();
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\User Management (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(userManagementPage.verifyexportToExcelSheet(maplist));
    }
    @Test(priority=29)
    public void ExporttoExcelWithoutData() throws Exception
    {
        UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.ExporttoExcelWithoutData(userDetails));
    }
    
    @Test(priority=30)
    public void VerifyPageBasedUserAccess() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifypagebaseduseraccess(userDetails));
        
    }
    @Test(priority=31)
    public void VerifyAccessCheckBox() throws Exception
    { 
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyaccesscheckbox(userDetails));
    }
    
    @Test(priority=32)
    public void VerifyAdminPageCancelButton() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifycancelchanges(userDetails));
    }
    
    @Test(priority=33)
    public void VerifyAdminPageSaveButton() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifysavechanges(userDetails));
    }
    
    @Test(priority=34)
    public void VerifyUnsuccefullcancelchanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyunsuccessfullcancelchanges(userDetails));
    }
    @Test(priority=35)
    public void VerifyUnsuccefullsavechanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);  
        Assert.assertTrue(userManagementPage.verifyunsuccessfullsavechanges(userDetails));
    }
   
    @Test(priority=36)
    public void VerifyReportsAccessCheckBox() throws Exception
    { 
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyreportsaccesscheckbox(userDetails));
    } 
    
    @Test(priority=37)
    public void VerifyReportsPageCancelButton() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyReportscancelchanges(userDetails));
    }
    
    @Test(priority=38)
    public void VerifyReportsPageSaveButton() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyReportssavechanges(userDetails));
    }
    
    @Test(priority=39)
    public void VerifyReportsunsuccefullcancelchanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyreportsunsuccessfullcancelchanges(userDetails));
    }
    
    @Test(priority=40)
    public void VerifyReportsunsuccefullsavechanges() throws Exception
    {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyreportsunsuccessfullsavechanges(userDetails));
    }
    
    @Test(priority=41)
    public void VerifyDashboardAccessCheckbox() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifydashboardaccesscheckbox(userDetails));
    }
    
    @Test(priority=42)
    public void VerifyDashboardPageCancelButton() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyDashboardcancelchanges(userDetails));
    }
    
    @Test(priority=43)
    public void VerifyDashboardPageSaveButton() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyDashboardsavechanges(userDetails));
    }
    
    @Test(priority=44)
    public void VerifyDashboardunsuccefullcancelchanges() throws Exception{
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifydashboardunsuccessfullcancelchanges(userDetails));
       }
    
    @Test(priority=45)
    public void VerifyDashboardunsuccefullsavechanges() throws Exception{
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifydashboardunsuccessfullsavechanges(userDetails));
    }
    
    @Test(priority=46)
    public void VerifyOtherApplicationAccessCheckbox() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyotherapplicationaccesscheckbox(userDetails));
    }
    
   @Test(priority=47)
    public void VerifyOtherApplicationPagecancelButton() throws Exception {
   		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyotherapplicationcancelchanges(userDetails));
    }
   
   @Test(priority=48)
   public void VerifyOtherApplicationPageSaveButton() throws Exception {
  		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
       UserDetails userDetails=new UserDetails(map);
       Assert.assertTrue(userManagementPage.verifyotherapplicationsavechanges(userDetails));
   }
    
    @Test(priority=49)
    public void VerifyOtherApplicationunsuccefullCancelchanges() throws Exception{
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyotherapplicationunsuccessfullcancelchanges(userDetails));
    }
    @Test(priority=50)
    public void VerifyOtherApplicationunsuccefullSavechanges() throws Exception{
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyotherapplicationunsuccessfullsavechanges(userDetails));
    }
    
    @Test(priority=51)
    public void VerifyAdminPagesCheckerAccessCheckbox() throws Exception {
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyAdminPagesCheckercheckbox(userDetails));
    }
    
   @Test(priority=52)
    public void VerifyAdminPagesCheckerPagecancelButton() throws Exception {
   		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyAdminPagesCheckercancelchanges(userDetails));
    }
   
   @Test(priority=53)
   public void VerifyAdminPagesCheckerPageSaveButton() throws Exception {
  		UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
       UserDetails userDetails=new UserDetails(map);
       Assert.assertTrue(userManagementPage.verifyAdminPagesCheckersavechanges(userDetails));
   }
    
    @Test(priority=54)
    public void VerifyAdminPagesCheckerunsuccefullCancelchanges() throws Exception{
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyAdminPagesCheckerunsuccessfullcancelchanges(userDetails));
    }
    @Test(priority=55)
    public void VerifyAdminPagesCheckerunsuccefullSavechanges() throws Exception{
    	UserManagementPage userManagementPage=PageFactory.createPageInstance(driver,UserManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\UserManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(3);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(userManagementPage.verifyAdminPagesCheckerunsuccessfullsavechanges(userDetails));
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result,Method method){
    	Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen("UserManagementTest",method.getName());
        driver.navigate().refresh();
    }
}
