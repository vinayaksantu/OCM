package com.tetherfi.test.chat;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.pages.ChatPage;
import com.tetherfi.pages.ChatTemplatesPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TextTemplatesPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class TextTemplatesTest extends BaseTest {

	Screenshot screenshot=new Screenshot(driver);


	@BeforeClass
	public void NavigateToTextTemplatesModule() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage OCMHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(OCMHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
		OCMHomePage.navigateToTab("Chat");
		ChatPage chatPage = PageFactory.createPageInstance(driver,ChatPage.class);
		Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
		chatPage.navigateToTextTemplatesPage();
		TextTemplatesPage textTemplatesPage=PageFactory.createPageInstance(driver, TextTemplatesPage.class);
		Assert.assertTrue(textTemplatesPage.isChatTemplatePageDisplayed(), "Text Templates Page assertion failed");
	}


	@Test(priority=1)
	public void TextTemplatesPage() throws InterruptedException {
		TextTemplatesPage textTemplatesPage = PageFactory.createPageInstance(driver, TextTemplatesPage.class);
		Assert.assertTrue(textTemplatesPage.VerifyLogo(),"Logo assertion failed");
		Assert.assertTrue(textTemplatesPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen(driver,"TextTemplatesTest","Maximize");
		Assert.assertTrue(textTemplatesPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen(driver,"TextTemplatesTest","Minimize");	
	}
	
	@Test(priority=2)
	public void VerifyDepartmentsDropdownForAllTheColumns() throws Exception {
		TextTemplatesPage textTemplatesPage = PageFactory.createPageInstance(driver, TextTemplatesPage.class);
		textTemplatesPage.navigateToTab("Departments");
		Assert.assertTrue(textTemplatesPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}

	@Test(priority=3)
	public void VerifyDepartmentsColumnsHeaderEnable() throws Exception {
		TextTemplatesPage textTemplatesPage = PageFactory.createPageInstance(driver, TextTemplatesPage.class);
		textTemplatesPage.navigateToTab("Departments");
		Assert.assertTrue(textTemplatesPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	@Test(priority=4)
	public void VerifyDepartmentsColumnsHeaderDisable() throws Exception {
		TextTemplatesPage textTemplatesPage = PageFactory.createPageInstance(driver, TextTemplatesPage.class);
		textTemplatesPage.navigateToTab("Departments");
		Assert.assertFalse(textTemplatesPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method) throws InterruptedException {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("TextTemplatesTest",method.getName());
		driver.navigate().refresh();
	}




}
