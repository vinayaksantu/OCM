package com.tetherfi.test;

import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.UserDetails;
import com.tetherfi.pages.RoleBasedAccessManagementWMCPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.pages.RoleBasedAccessManagementPage;
import com.tetherfi.pages.RoleBasedAccessManagementWMCPage;
import com.tetherfi.pages.RoleBasedAccessManagementWMCPage;
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
	public void NavigateToRoleBasedAccessManagementWMCPage() throws InterruptedException {
		HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
		ocmHomePage.navigateToRoleBasedAccessManagementPage();
		RoleBasedAccessManagementPage RoleBasedAccessManagementPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementPage.class);
		Assert.assertTrue(RoleBasedAccessManagementPage.isRoleBasedAccessManagementPageDisplayed(),"Role Based access management assertion failed");
	}
	
	@Test(priority=1)
    public void RoleBasedAccessManagementWMCPage(){
        RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
    	Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifylogo(),"Role Based Access Management logo assertion failed");
    	Assert.assertTrue(RoleBasedAccessManagementWMCPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("RoleBasedAccessManagementWMCTest", "Maximize Window");
    	Assert.assertTrue(RoleBasedAccessManagementWMCPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("RoleBasedAccessManagementWMCTest", "Minimize Window");
    }

    @Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
    	RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
    	Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }

    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() throws InterruptedException {
    	RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
    	Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }

    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
   		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
   		Assert.assertFalse(RoleBasedAccessManagementWMCPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }

    @Test(priority=5)
    public void AddNewRoleBasedAccessManagementCancelRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
        Assert.assertTrue(RoleBasedAccessManagementWMCPage.addNewCancel(userDetails),"Add New Cancel Assertion Failed");
    }

    @Test(priority=6)
    public void AddNewRoleBasedAccessManagementRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);      
        RoleBasedAccessManagementWMCPage.addRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }

    @Test(priority=7)
    public void AddNewRecordWithoutRoleName() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
        RoleBasedAccessManagementWMCPage.addwithoutRole(userDetails);
        Assert.assertEquals(RoleBasedAccessManagementWMCPage.getErrorMsg(),userDetails.getErrorMsg1(), "Invalid Record Assertion failed");  	
    }

    @Test(priority=8)
    public void AddDuplicateRecord() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        UserDetails userDetails=new UserDetails(map);
        RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
        RoleBasedAccessManagementWMCPage.addRoleBasedAccessManagementRecord(userDetails);
        Assert.assertEquals(RoleBasedAccessManagementWMCPage.getErrorMsg(),"Duplicate RoleName", "Invalid Record Assertion failed");  	
    }

	@Test(priority=9)
	public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementCreate(userDetails, "Create"),"Audit Trail report assertion failed");
	}
	
	@Test(priority=10)
	public void VerifyPageBasedUserAccess() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifypagebaseduseraccess(userDetails));
		screenshot.captureScreen("RRoleBasedAccessManagementWMCTest","VerifyPageBasedUserAccess");
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyclosebutton());

	}

	@Test(priority=11)
	public void VerifyAccessCheckBox() throws Exception
	{ 
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyaccesscheckbox(userDetails));
		screenshot.captureScreen("RoleBasedAccessManagementWMCTest","VerifyAccessCheckBox");
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyclosebutton());
	}

	@Test(priority=12)
	public void VerifyAdminPageCancelButton() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifycancelchanges(userDetails));
	}

	@Test(priority=13)
	public void VerifyAdminPageSaveButton() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage.verifysavechanges(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");

	}

	@Test(priority=14)
	public void VerifyunsuccefullCancelchanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyunsuccessfullcancelchanges(userDetails));
	}

	@Test(priority=15)
	public void VerifyunsuccefullSavechanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);		
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyunsuccessfullsavechanges(userDetails));
	}
	
	
	@Test(priority=16)
	public void VerifyAdminCheckerAccessCheckBox() throws Exception
	{ 
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyAdminCheckerAccessCheckbox(userDetails));
		screenshot.captureScreen("RoleBasedAccessManagementWMCTest","VerifyAdminCheckerAccessCheckBox");
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyclosebutton());
	}

	@Test(priority=17)
	public void VerifyAdminCheckerPageCancelButton() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyAdminCheckercancelchanges(userDetails));
	}

	@Test(priority=18)
	public void VerifyAdminCheckerPageSaveButton() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage.verifyAdminCheckersavechanges(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
	}

	@Test(priority=19)
	public void VerifyAdminCheckerunsuccefullCancelchanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyAdminCheckerunsuccessfullcancelchanges(userDetails));
	}

	@Test(priority=20)
	public void VerifyAdminCheckerunsuccefullSavechanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);		
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyAdminCheckerunsuccessfullsavechanges(userDetails));
	}

	@Test(priority=21)
	public void VerifyReportsAccessCheckBox() throws Exception
	{ 
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyreportsaccesscheckbox(userDetails));
	}

	@Test(priority=22)
	public void VerifyReportsPageCancelButton() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyReportscancelchanges(userDetails));
	}
	@Test(priority=23)
	public void VerifyReportsPageSaveButton() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage.verifyReportssavechanges(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
	}

	@Test(priority=24)
	public void VerifyReportsunsuccefullcancelchanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyreportsunsuccessfullcancelchanges(userDetails));
	}
	@Test(priority=25)
	public void VerifyReportsunsuccefullsavechanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);		
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyreportsunsuccessfullsavechanges(userDetails));
	}
	 
	@Test(priority=26)
	public void VerifyDashboardAccessCheckbox() throws Exception {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifydashboardaccesscheckbox(userDetails));
	}

	@Test(priority=27)
	public void VerifyDashboardPageCancelButton() throws Exception {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyDashboardcancelchanges(userDetails));
	}
	@Test(priority=28)
	public void VerifyDashboardPageSaveButton() throws Exception {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage.verifyDashboardsavechanges(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
	}

	@Test(priority=29)
	public void VerifyDashboardunsuccefullcancelchanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifydashboardunsuccessfullcancelchanges(userDetails));
	}

	@Test(priority=30)
	public void VerifyDashboardunsuccefullsavechanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifydashboardunsuccessfullsavechanges(userDetails));
	}

	@Test(priority=31)
	public void VerifyOtherApplicationAccessCheckbox() throws Exception {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyotherapplicationaccesscheckbox(userDetails));	
	}

	@Test(priority=32)
	public void VerifyOtherApplicationPageCancelButton() throws Exception {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyotherapplicationcancelchanges(userDetails));
	}
	@Test(priority=33)
	public void VerifyOtherApplicationPageSaveButton() throws Exception {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage.verifyotherapplicationsavechanges(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");

	}
	@Test(priority=34)
	public void VerifyOtherApplicationunsuccefullCancelchanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyotherapplicationunsuccessfullcancelchanges(userDetails));
	}
	@Test(priority=35)
	public void VerifyOtherApplicationunsuccefullSavechanges() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyotherapplicationunsuccessfullsavechanges(userDetails));
	}

	@Test(priority=36)
	public void EditRoleBasedAccessManagementCancelButton() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.EditCancel(userDetails),"Edit Cancel assertion failed");
	}

	@Test(priority=37)
	public void EditRoleBasedAccessManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		RoleBasedAccessManagementWMCPage.editRoleBasedAccessManagementRecord(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
	}

	@Test(priority=38)
	public void VerifyAuditTrailReportForUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map =  new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);	    
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementUpdate(userDetails, "Update"),"Audit Trail report assertion failed");
	}

	@Test(priority=39)
	public void EditRecordWithoutModifyReason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage = PageFactory.createPageInstance(driver, RoleBasedAccessManagementWMCPage.class);
		RoleBasedAccessManagementWMCPage.EditRecordWithoutModifyReason(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getErrorMsg(),"Please enter the modify reason", "Invalid Assertion Failed");
	}  
	
	@Test(priority=40)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifySearchIsNotEqualTo(userDetails.getRoleName()));
	}
	@Test(priority=41)
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifySearchContains(userDetails.getRoleName()));
	}

	@Test(priority=42)
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifySearchDoesNotContains(userDetails.getRoleName()));
	}

	@Test(priority=43)
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifySearchStartsWith(userDetails.getRoleName()));
	}
	@Test(priority=44)
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifySearchEndsWith(userDetails.getRoleName()));
	}

	@Test(priority=45)
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);	
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertFalse(RoleBasedAccessManagementWMCPage.clearAll(userDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("RoleBasedAccessManagementWMCTest", "clearall");
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyclose());
	}

	@Test(priority=46)
	public void ClearSearch() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);	
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyinvalidsearch(userDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("RoleBasedAccessManagementWMCTest","Invalid Search");
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=47)
	public void GroupBy()
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.groupby());
		screenshot.captureScreen("RoleBasedAccessManagementWMCTest","GroupBy");
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.groupby());
	}

	@Test(priority=48)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=49)
	public void VerifyArrowMoveForFirstAndLastPage() {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");

	}

	@Test(priority=50)
	public void VerifyTotalNumberOfItemsPerPageDetails() {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}

	@Test(priority=51)
	public void VerifyNumberOfItemsPerPageSelection() {
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}

	@Test(priority=52)
	public void ExportToExcel() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyExportToExcel(filePath));
	}

	@Test(priority=53)
	public void ExportToExcelData() throws Exception{	
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=54)
	public void SortingByAscending() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		RoleBasedAccessManagementWMCPage.SortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=55)
	public void SortingByDescending() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		RoleBasedAccessManagementWMCPage.SortByDescending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Role Based Access Management (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=56)
	public void ExporttoExcelWithoutData() throws Exception
	{
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.ExporttoExcelWithoutData(userDetails));
	}

	@Test(priority=57)
	public void VerifyDeleteRoleBasedAccessManagementNoButton() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.DeleteCancel(userDetails));
	}


	@Test(priority=58)
	public void VerifyDeleteRoleBasedAccessManagementWithoutModifyReason() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		RoleBasedAccessManagementWMCPage.DeleteRecordWithoutModifyReason(userDetails);		
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getErrorMsg(),"Please enter the delete reason","Delete record assertion failed");
	}

	@Test(priority=59)
	public void DeleteRoleBasedAccessManagementRecord() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
		UserDetails userDetails=new UserDetails(map);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		RoleBasedAccessManagementWMCPage.DeleteRoleBasedAccessManagementRecord(userDetails);
		Assert.assertEquals(RoleBasedAccessManagementWMCPage.getSuccessMessage(),"Record Deleted Successfully","Delete record assertion failed");
	}
	
	@Test(priority=60)
    public void VerifyAuditTrailReportForDelete() throws Exception {
		 String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		 Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
	     UserDetails UserDetails = new UserDetails(map);
	     HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMReportsPage();
	     OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	     String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	     Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	     ReportDetails reportDetails= new ReportDetails(map1);
	     ocmReportsPage.showReport(reportDetails);
	     Assert.assertTrue(ocmReportsPage.verifyRoleBasedAccessManagementDelete(UserDetails, "Delete"),"Audit Trail report assertion failed");
    }

	@Test(priority=61)
	public void database() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RoleBasedAccessManagementData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
		RoleBasedAccessManagementWMCPage RoleBasedAccessManagementWMCPage=PageFactory.createPageInstance(driver,RoleBasedAccessManagementWMCPage.class);
		UserDetails userDetails=new UserDetails(map);	
		Assert.assertTrue(RoleBasedAccessManagementWMCPage.verifyDatabase(userDetails.getQuery()));
	}

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("RoleBasedAccessManagementWMCTest",method.getName());
		driver.navigate().refresh();
	}
    
}