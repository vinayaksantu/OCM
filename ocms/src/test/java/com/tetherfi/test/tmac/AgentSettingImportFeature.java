package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.tmac.AgentSettingsDetails;
import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.AgentSettingsPageWMC;
import com.tetherfi.pages.FaxTemplateWMCPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;

import com.tetherfi.pages.TmacPage;

import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentSettingImportFeature extends BaseTest{

	@BeforeMethod
	public void NavigateToAgentSettingsPage() throws Exception {
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
		tmacPage.navigateToAgentSettingsPage();
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver,AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");	
	}
	
	@Test(priority=1,description="Verify the loading of first page")
	public void VerifyAgentSettingsPage() {
		AgentSettingsPageWMC agentSettingsPagewmc=PageFactory.createPageInstance(driver,AgentSettingsPageWMC.class);
		Assert.assertTrue(agentSettingsPagewmc.VerifyLogo(), "Agent Settings logo assertion failed");
		Assert.assertTrue(agentSettingsPagewmc.maximizeWindow(), "Window maximize assertion failed");
		Assert.assertTrue(agentSettingsPagewmc.minimizeWindow(), "Window minimize assertion failed");		
	}

	@Test(priority=2,description="Verify Import Profile picture button")
	public void VerifyImportprofilePic() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.VerifyImportPic(agentSettingsDetails);	
	}

	@Test(priority=3,description="Verify Error message when attribute is not selected")
	public void UploadtWithoutAttribute() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.UploadWithoutAttribute(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyToastMessage(), "Please select the User Attribute & Re-Upload the file", "Upload Assertion Failed");
	}
	
	@Test(priority=4,description="Verify Error message when attribute and picture is not selected")
	public void UploadtWithoutAttributeandPic() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.UploadWithoutAttrndPic(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyToastMessage(), "Please select the User Attribute & Re-Upload the file", "Upload Assertion Failed");
	}
	@Test(priority=5,description="Verify Error message when picture is not selected")
	public void UploadtWithoutPic() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(0);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.UplodWithoutPic(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyToastMessage(), "Please select the file to upload", "Upload Assertion Failed");
	}
	
	@Test(priority=6,description="Verify Error message when picture size is greater than 30kb")
	public void UploadPicWithBiggerSize() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(1);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.VerifyImportPic(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyToastMessage(), "Please select the User Attribute & Re-Upload the file", "File size greater Assertion Failed");
	}
	@Test(priority=7,description="Upload picture with Lan Id")
	public void UploadPicWithLanID() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(3);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.VerifyPicNdContinue(agentSettingsDetails);
		Assert.assertTrue(agentSettingsPage.VerifyInsertedRecordcount(agentSettingsDetails), "Insert Count Assertion Failed");
	}
	
	@Test(priority=8,description="Upload picture with Avaya Login Id")
	public void UploadPicWithAvayaLoginID() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(4);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.VerifyPicNdContinue(agentSettingsDetails);
		Assert.assertTrue(agentSettingsPage.VerifyInsertedRecordcount(agentSettingsDetails), "Avaya Login Insert Count Assertion Failed");
		
	}
	@Test(priority=9,description="Upload picture with different file format")
	public void UploadPicWithDifferentFileFormat() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(5);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		Assert.assertTrue(agentSettingsPage.VerifyFileTypeErrorMsg(agentSettingsDetails), "Mismatch in error message");
	}
	
	@Test(priority=10,description="Verify when pic is witin a folder and then upload zipped file")
	public void UploadPicWithinFolder() throws Exception {	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Agent Settings.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Import").getTestData().get(6);
		AgentSettingsDetails agentSettingsDetails = new AgentSettingsDetails(map);
		AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
		agentSettingsPage.VerifyImportPic(agentSettingsDetails);
		Assert.assertEquals(agentSettingsPage.VerifyToastMessage(), "Failed to import user Profile Pictures", "Folder Assertion Failed");
	}
	
	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("ImportFeatureTest",method.getName());
		driver.navigate().refresh();
	}	
}