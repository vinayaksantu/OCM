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
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


public class RoleBasedAccessManagementTest extends BaseTest {
	Screenshot screenshot=new Screenshot(driver);
	
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
    
    @Test(priority=1)
    public void RoleBasedAccessManagementPage(){
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
    	Assert.assertTrue(roleBasedAccessManagementPage.verifylogo(),"Role Based Access Management logo assertion failed");
        Assert.assertTrue(roleBasedAccessManagementPage.verifygridcontent(),"Grid Container assertion failed");
    	Assert.assertTrue(roleBasedAccessManagementPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen(driver, "Maximize Window","RoleBasedAccessManagementTest");
    	Assert.assertTrue(roleBasedAccessManagementPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen(driver, "Minimize Window","RoleBasedAccessManagementTest");
    }
   
    @Test(priority=2)
    public void AddNewRoleBasedAccessManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.addNewCancel(userDetails.getRoleName()),"Add New Cancel Assertion Failed");
    	screenshot.captureScreen(driver, "Add new cancel","RoleBasedAccessManagementTest");
        roleBasedAccessManagementPage.addNewRoleBasedAccessManagementRecord(userDetails.getRoleName());
        Assert.assertTrue(roleBasedAccessManagementPage.verifyNewRecordCreated(),"Add New record assertion failed");
    	screenshot.captureScreen(driver, "Record Created","RoleBasedAccessManagementTest");
    }
    
    @Test(priority=3)
    public void AddNewInvalidRecord() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.addinvalidrecord();
        Assert.assertFalse(roleBasedAccessManagementPage.verifyNewRecordCreated(), "Invalid Record Assertion failed");
    	screenshot.captureScreen(driver, "Invalid Record","RoleBasedAccessManagementTest");
        roleBasedAccessManagementPage.DuplicateRecord(userDetails);
        Assert.assertFalse(roleBasedAccessManagementPage.verifyNewRecordCreated(), "Duplicate Record Assertion failed");
    	screenshot.captureScreen(driver, "Duplicate Record","RoleBasedAccessManagementTest");
    	
    }
   @Test(priority=4)
    public void EditRoleBasedAccessManagementCancelButton() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.editCancel(userDetails.getRoleName(),userDetails.getUpdateRoleName(),userDetails.getModifyReason()),"Edit Cancel assertion failed");
    	screenshot.captureScreen(driver, "Edit Cancel","RoleBasedAccessManagementTest");
    }
    //@Test(priority=5)
    public void EditRoleBasedAccessManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        roleBasedAccessManagementPage.editRoleBasedAccessManagementRecord(userDetails.getRoleName(),userDetails.getUpdateRoleName(),userDetails.getModifyReason());
        Assert.assertTrue(roleBasedAccessManagementPage.verifyRecordUpdated(),"Edit record assertion failed");
    	screenshot.captureScreen(driver, "Record Updated","RoleBasedAccessManagementTest");
    }
   
   //@Test(priority=6)
   public void searchPage() throws Exception {
   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
       UserDetails userDetails=new UserDetails(map);	
       RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
       Assert.assertFalse(roleBasedAccessManagementPage.clearAll(userDetails.getRoleName()),"ClearAll Assertion Failed");
       screenshot.captureScreen(driver, "clearall","RoleBasedAccessManagementTest");
       Assert.assertTrue(roleBasedAccessManagementPage.verifyclose());
       screenshot.captureScreen(driver, "SearchClose","RoleBasedAccessManagementTest");
   }
   //@Test(priority=7)
   public void ClearSearch() throws Exception {
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
   		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
   		UserDetails userDetails=new UserDetails(map);	
   		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
       Assert.assertTrue(roleBasedAccessManagementPage.verifyinvalidsearch(userDetails),"invalidsearchwithwrongdata");
       screenshot.captureScreen(driver, "Invalid Search", "RoleBasedAccessManagementTest");
       Assert.assertTrue(roleBasedAccessManagementPage.verifyclearsearch(), "Clear All Assertion Failed");
       screenshot.captureScreen(driver, "Clear Search", "RoleBasedAccessManagementTest");
   }
   
   //@Test(priority=8)
   public void GroupBy()
   {
       	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
   		Assert.assertTrue(roleBasedAccessManagementPage.groupby());
   		screenshot.captureScreen(driver, "GroupBy", "RoleBasedAccessManagementTest");
   		Assert.assertTrue(roleBasedAccessManagementPage.groupby());
   		screenshot.captureScreen(driver, "AlreadyGroupBy", "RoleBasedAccessManagementTest");	
   }
   //@Test(priority=9)
   public void VerifyArrowMoveForPreviousAndNextPage() {
      	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
      	Assert.assertTrue(roleBasedAccessManagementPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
      	screenshot.captureScreen(driver, "VerifyArrowMoveForPreviousAndNextPage", "RoleBasedAccessManagementTest");	
   }
   //@Test(priority=10)
   public void VerifyArrowMoveForFirstAndLastPage() {
     	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
     	Assert.assertTrue(roleBasedAccessManagementPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
     	screenshot.captureScreen(driver, "VerifyArrowMoveForFirstAndLastPage", "RoleBasedAccessManagementTest");	

   }
   //@Test(priority=11)
   public void VerifyTotalNumberOfItemsPerPageDetails() {
     	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
     	Assert.assertTrue(roleBasedAccessManagementPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
     	screenshot.captureScreen(driver, "VerifyTotalNumberOfItemsPerPageDetails", "RoleBasedAccessManagementTest");	
   }
   
  //@Test(priority=12)
   public void VerifyNumberOfItemsPerPageSelection() {
   		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
   		Assert.assertTrue(roleBasedAccessManagementPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
   		screenshot.captureScreen(driver, "VerifyNumberOfItemsPerPageSelection","RoleBasedAccessManagementTest");

   }
   
   //@Test(priority=13)
   public void VerifyDropdownForAllTheColumns() {
   		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
   		Assert.assertTrue(roleBasedAccessManagementPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
   }
   
   //@Test(priority=14)
   public void VerifyColumnsHeaderEnable() {
   		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
   		Assert.assertTrue(roleBasedAccessManagementPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
   }
   
   //@Test(priority=15)
   public void VerifyColumnsHeaderDisable() {
  		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
  		Assert.assertFalse(roleBasedAccessManagementPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
   }
   //@Test(priority=16)
   public void ExportToExcel() throws Exception{
   		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
		Assert.assertTrue(roleBasedAccessManagementPage.verifyExportToExcel(filePath));
   }
   
   //@Test(priority=17)
   public void ExportToExcelData() throws Exception{	
	   	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management.xlsx";
   		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
		Assert.assertTrue(roleBasedAccessManagementPage.verifyexportToExcelSheet(maplist));
   }
   
  //@Test(priority=18)
   public void SortingByAscending() throws Exception
   {
		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
		roleBasedAccessManagementPage.SortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(roleBasedAccessManagementPage.verifyexportToExcelSheet(maplist));
   }
   //@Test(priority=19)
   public void SortingByDescending() throws Exception
   {
		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
		roleBasedAccessManagementPage.SortByDescending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(roleBasedAccessManagementPage.verifyexportToExcelSheet(maplist));
   }
   //@Test(priority=20)
   public void ExporttoExcelWithoutData() throws Exception
   {
		RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
       String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
       Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
       UserDetails userDetails=new UserDetails(map);
       Assert.assertTrue(roleBasedAccessManagementPage.ExporttoExcelWithoutData(userDetails));
   }
  //@Test(priority=21)
    public void VerifyDeleteRoleBasedAccessManagementNoButton() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        Assert.assertTrue(roleBasedAccessManagementPage.deleteRecordNoBtn(userDetails.getRoleName(),userDetails.getDeleteReason()));
        screenshot.captureScreen(driver, "Record deleted No button", "RoleBasedAccessManagementTest");
  }
  //@Test(priority=22)
  	public void DeleteRoleBasedAccessManagementRecord() throws Exception       	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
      	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
      	UserDetails userDetails=new UserDetails(map);
      	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
      	roleBasedAccessManagementPage.deleteRoleBasedAccessManagementRecord(userDetails.getRoleName(),userDetails.getDeleteReason());
        Assert.assertTrue(roleBasedAccessManagementPage.verifyRecordDeleted(),"Delete record assertion failed");
        screenshot.captureScreen(driver, "Record Deleted", "RoleBasedAccessManagementTest");
  }
  	//@Test(priority=23)
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
      	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        UserDetails userDetails=new UserDetails(map);	
    	Assert.assertTrue(roleBasedAccessManagementPage.verifyDatabase(userDetails.getQuery()));
        screenshot.captureScreen(driver, "Database", "RoleBasedAccessManagementTest");

    }
    //@Test(priority=24)
    public void VerifyPageBasedUserAccess() throws Exception
    {
      	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifypagebaseduseraccess(userDetails));
        screenshot.captureScreen(driver,"VerifyPageBasedUserAccess", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());

    }
    //@Test(priority=25)
    public void VerifyAccessCheckBox() throws Exception
    { 
      	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());
    }
    
    //@Test(priority=26)
    public void VerifyAdminPageButton() throws Exception
    {
      	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifycancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifysavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "RoleBasedAccessManagementTest");
    }
    //@Test(priority=27)
    public void Verifyunsuccefullchanges() throws Exception
    {
      	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(roleBasedAccessManagementPage.verifyunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());
    }
    
    //@Test(priority=28)
    public void VerifyReportsAccessCheckBox() throws Exception
    { 
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyreportsaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());
        Thread.sleep(1000);
    }
    //@Test(priority=29)
    public void VerifyReportsPageButton() throws Exception
    {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyReportscancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyReportssavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "RoleBasedAccessManagementTest");
    }
    //@Test(priority=30)
    public void VerifyReportsunsuccefullchanges() throws Exception
    {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyreportsunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(roleBasedAccessManagementPage.verifyreportsunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());
    }
    //@Test(priority=31)
    public void VerifyDashboardAccessCheckbox() throws Exception {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifydashboardaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "RoleBasedAccessManagementTest");
        Thread.sleep(1000);
    }
    @Test(priority=32)
    public void VerifyDashboardPageButton() throws Exception {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyDashboardcancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyDashboardsavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "RoleBasedAccessManagementTest");
    }
    @Test(priority=33)
    public void VerifyDashboardunsuccefullchanges() throws Exception
    {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifydashboardunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(roleBasedAccessManagementPage.verifydashboardunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());
    }
  @Test(priority=34)
    public void VerifyOtherApplicationAccessCheckbox() throws Exception {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyotherapplicationaccesscheckbox(userDetails));
        screenshot.captureScreen(driver,"VerifyAccessCheckBox", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());
        Thread.sleep(1000);

    }
   @Test(priority=35)
    public void VerifyOtherApplicationPageButton() throws Exception {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyotherapplicationcancelchanges(userDetails));
        screenshot.captureScreen(driver,"verifycancelchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyotherapplicationsavechanges());
        screenshot.captureScreen(driver,"verifysavechanges", "RoleBasedAccessManagementTest");
    }
    @Test(priority=36)
    public void VerifyOtherApplicationunsuccefullchanges() throws Exception
    {
    	RoleBasedAccessManagementPage roleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        UserDetails userDetails=new UserDetails(map);
        Assert.assertTrue(roleBasedAccessManagementPage.verifyotherapplicationunsuccessfullcancelchanges(userDetails));
        Assert.assertTrue(roleBasedAccessManagementPage.verifyotherapplicationunsuccessfullsavechanges());
        screenshot.captureScreen(driver,"Verifyunsuccefullchanges", "RoleBasedAccessManagementTest");
        Assert.assertTrue(roleBasedAccessManagementPage.verifyclosebutton());
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result, Method method) throws Exception {
    	 if(ITestResult.FAILURE==result.getStatus()){
      		 try{
      			 screenshot.captureScreen(driver, method.getName(),"RoleBasedAccessManagementTest");
      		 }
      		catch (Exception e){
      		 System.out.println("Exception while taking screenshot "+e.getMessage());
      		 } 
      		 driver.navigate().refresh();
      		 }
    }
}