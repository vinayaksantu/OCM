package com.tetherfi.test;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.user.UserOnBoardingDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.UserOnBoardingPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class UserOnBoardingImportUserProfilePicturesTest {

	protected WebDriver driver;
	@BeforeMethod
	public void NavigateToUserOnBoardingPage(Method method) throws Exception {
		try {
			PageFactory.reset();
			BrowserFactory browserFactory = new BrowserFactory();
			driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
		}catch (Exception e){
			PageFactory.reset();
			driver.close();
			e.printStackTrace();
		}
		System.out.println("Started Executing : "+method.getName());
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
		Test t = method.getAnnotation(Test.class);
		Map<String, String> map;
		if(t.groups()[0].equalsIgnoreCase("Checker"))
			map= new ExcelReader(filePath,"Login").getTestData().get(1);
		else
			map= new ExcelReader(filePath,"Login").getTestData().get(0);
		try{driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}catch (TimeoutException e){e.printStackTrace();driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);}
		 LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
		 loginPage.overrideSecurityConcern();/*UsedFor https withaddvanced btn*/
		if(map.get("LoginType").equals("Custom")){
			Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page not loaded");
			loginPage.login(map.get("Username"),map.get("Password"),map.get("EmailId"));
			Thread.sleep(5000);
		}
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToUserOnBoardingPage();
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.isUserOnBoardingPageDisplayed(), "NavigateToNewUserOnBoardingPage Page assertion failed");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	@Test(groups = { "Maker" }, priority=1,description="Verify Import Valid UserDetails ChannelCount and Features To APPROVE")
	public void VerifyImportValidSupervisorDetailsChannelCountandFeaturesToAPPROVE() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(16);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid UserDetails ChannelCount and Features To APPROVE Assertion Failed");
	}
	
	@Test( groups = { "Maker" },priority=2,dependsOnMethods="VerifyImportValidSupervisorDetailsChannelCountandFeaturesToAPPROVE",description="To Verify SendForApproval for Imported UserOnBoarding ")
	public void VerifySendForApprovalForImportrdSupervisorOnBoarding() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}
	
	@Test(groups = { "Checker" },priority=3,dependsOnMethods="VerifySendForApprovalForImportrdSupervisorOnBoarding",description="To Verify Checker Approve for Imported UserOnBoardingRecord")
	public void ApproveforImortedSupervisorUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Create");
		Assert.assertTrue(userOnBoardingPage.verifyMessage());
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Create"));
	}
	
	@Test(groups = { "Maker" }, priority=4,description="Verify Import Valid UserDetails ChannelCount and Features To APPROVE")
	public void VerifyImportValidAgentDetailsChannelCountandFeaturesToAPPROVE() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportUsers").getTestData().get(17);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserFiles(userOnBoardingDetails), "Verify Import Valid UserDetails ChannelCount and Features To APPROVE Assertion Failed");
	}
	
	@Test( groups = { "Maker" },priority=5,dependsOnMethods="VerifyImportValidAgentDetailsChannelCountandFeaturesToAPPROVE",description="To Verify SendForApproval for Imported UserOnBoarding ")
	public void VerifySendForApprovalForImportrdAgentOnBoarding() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectRecord();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}
	
	@Test(groups = { "Checker" },priority=6,dependsOnMethods="VerifySendForApprovalForImportrdAgentOnBoarding",description="To Verify Checker Approve for Imported UserOnBoardingRecord")
	public void ApproveforImortedAgentUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApprove("Approve Create");
		Assert.assertTrue(userOnBoardingPage.verifyMessage());
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Create"));
	}
	
	@Test(groups= {"Maker"},priority=7,description="To Verify Import User Profile Pictures PopUp")
	public void VerifyImportUserProfilePicturesPopUP() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyImportUserProfilePicturePopUp(), "Verify Import User Profile Pictures PopUP assertion failed");
	}
	
	@Test(groups= {"Maker"},priority=8,description="To Verify Upload without Attribute and ProfilePicture")
	public void VerifyUploadwithoutAttributeandprofilePicture() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadwithoutAttributeandProfilePic();
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please select the User Attribute & Re-Upload the file","Verify Upload without Attribute and Profile Picture Assertion failed");
	}
	
	@Test(groups= {"Maker"},priority=9,description="VerifyUpload without ProfilePicture")
	public void VerifyUploadwithoutProfilePicture() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadwithoutProfilePic(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please select the file to upload","Verify Upload without ProfilePicture Assertion failed");
	}
	
	@Test(groups= {"Maker"},priority=10,description="To Verify Import User Profile Pictures without Attribute")
	public void VerifyUploadwithoutAttribute() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(0);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadwithoutAttribute(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Please select the User Attribute & Re-Upload the file","Upload without Attribute Assertion failed");
	}
	
	@Test(groups= {"Maker"},priority=11,description="To Verify Import User Profile Pictures when Picture Name is not matching the Attribute")
	public void VerifyUploadProfilePicWhenPictureNameisNotMatchingtheAttribute() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(9);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Upload ProfilePic When PictureName is Not Matching the Attribute Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=12,description="To Verify Upload InValid Format ProfilePic File with LanID Attribute")
	public void VerifyUploadInValidFormatProfilePicFilewithLanIDAttribute() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(1);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		Assert.assertTrue(userOnBoardingPage.VerifyUploadInvalidFormatProfilePicFileWithLanIdAttribute(userOnBoardingDetails), "Verify Upload InValid Format ProfilePic File with LanID Attribute Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=13,description="Verify Upload When ImportProfilePics Inside the Folder with ZipFormat")/*Bug: File Imported Successfully with Folder Inside Zip File PRDOCM-68616*/
	public void VerifyUploadWhenImportProfilePicsInsidetheFolderwithZipFormat() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(3);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertEquals(userOnBoardingPage.VerifyMessage(), "Failed to import user profile pictures","Verify Upload When ImportProfilePics Inside the Folder with Zip Format Assertion failed");
	}
	
	@Test(groups= {"Maker"},priority=14,description="Verify Upload Valid ProfilePic withFile Size more than 30KB")
	public void VerifyUploadValidProfilePicwithFileSizemorethan30KB() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(4);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Upload Valid ProfilePic with FileSize morethan 30KB Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=15,description="Verify Uploading Two Different Image For Same LanId")
	public void VerifyUploadingTwoDifferentImageForSameLanId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(5);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Uploading Two Different Image For Same LanId Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=16,description="To VerifyUploading Two DifferentImage ForSame AvayaLoginId")
	public void VerifyUploadingTwoDifferentImageForSameAvayaLoginId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(6);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Uploading Two Different Image For Same AvayaLoginId Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=17,description="Verify Upload Valid ProfilePic with LanID Attribute")
	public void VerifyUploadValidProfilePicwithLanIDAttribute() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(2);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Upload Valid ProfilePic with LanID Attribute Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=18,description="To Verify ReplaceProfilePic with LanIDAttribute for the User Already having ProfilePics")
	public void VerifyReplaceProfilePicwithLanIDAttributefortheUserAlreadyhavingProfilePics() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(11);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Replace ProfilePic with LanID Attribute for the User Already having ProfilePics Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=19,description="To Verify Upload ValidProfilePic with AvayaLoginIDAttribute")
	public void VerifyUploadValidProfilePicwithAvayaLoginIDAttribute() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(7);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Upload Valid Profile Pic with Avaya LoginID Attribute Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=20,description="To VerifyUploadingPNGandJPGFormatImageswithAvayaLoginId")
	public void VerifyUploadingPNGandJPGFormatImageswithAvayaLoginId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(8);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Uploading PNG and JPG Format Images with AvayaLoginId Assertion Failed");
	}
	
	@Test(groups= {"Maker"},priority=21,description="To Verify Upload File with LanID as Atrribute and ImageName as AvayaLoginId")
	public void VerifyUploadFilewithLanIDasAtrributeandImageNameasAvayaLoginId() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "ImportProfilePic").getTestData().get(10);
		UserOnBoardingDetails userOnBoardingDetails = new UserOnBoardingDetails(map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.VerifyUploadProfilePicWithAttribute(userOnBoardingDetails);
		Assert.assertTrue(userOnBoardingPage.VerifyRecordcount(userOnBoardingDetails), "Verify Upload File with LanID as Atrribute and Image Name as AvayaLoginId Assertion Failed");
	}
	
	@Test(groups = { "Maker" }, priority=22,description="To Verify DeleteUserOnBoardingRecord ")
	public void DeleteImportedUserOnBoardingRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(7);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
		Assert.assertEquals(userOnBoardingPage.getSuccessMessage(), "Record Deleted Successfully");
	}
	
	@Test(groups = { "Maker" }, priority=23,description="To Verify DeleteUserOnBoardingRecord ")
	public void DeleteImportedUserOnBoardingRecord1() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\UserOnBoardingData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(6);
		UserOnBoardingDetails UserOnBoardingDetails = new UserOnBoardingDetails (map);
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.deleteUserOnBoardingRecord(UserOnBoardingDetails.getLanID(), UserOnBoardingDetails.getDeleteReason());
		Assert.assertEquals(userOnBoardingPage.getSuccessMessage(), "Record Deleted Successfully");
	}
	
	@Test( groups = { "Maker" },priority=24,dependsOnMethods="DeleteImportedUserOnBoardingRecord1",description="To Verify SendForApproval for Imported UserOnBoarding ")
	public void VerifySendForApprovalForDeletedImportedserOnBoardingRecords() throws Exception {
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.selectUserOnBoardingAuditTrailTab();
		userOnBoardingPage.selectDeletedImportRecords();
		userOnBoardingPage.sendForAprroval("sent");
		Assert.assertTrue(userOnBoardingPage.verifyStatus("Approval Pending"),"approval status details failed");
	}
	
	@Test(groups = { "Checker" },priority=25,dependsOnMethods="VerifySendForApprovalForDeletedImportedserOnBoardingRecords",description="To Verify ApproveforDeleteUserOnBoardingRecord ")
	public void ApproveforDeleteUserOnBoardingRecord() throws Exception{
		UserOnBoardingPage userOnBoardingPage = PageFactory.createPageInstance(driver, UserOnBoardingPage.class);
		userOnBoardingPage.clickonApproveforDeletedRecords("Approve Deleted");
		Assert.assertTrue(userOnBoardingPage.verifyMessage(),"Approve record assertion failed");
		Assert.assertTrue(userOnBoardingPage.verifyReviewAuditTrail("Approved","Approve Deleted"));
	}
	
	
	
	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("UserOnBoardingImportUserProfilePicturesTest",method.getName());
		driver.navigate().refresh();
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.userLogout();
		driver.close();
		System.out.println("Completed Executing : "+method.getName());
	}
}
