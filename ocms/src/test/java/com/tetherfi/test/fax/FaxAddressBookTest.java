package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxAddressBookDetails;
import com.tetherfi.pages.FaxAddressBookPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxAddressBookTest {
	protected WebDriver driver;
	Screenshot screenshot=new Screenshot(driver);

	@BeforeClass
	public void openApplication() {
		 try {
	            PageFactory.reset();
	            BrowserFactory browserFactory = new BrowserFactory();
	            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
	            String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
	            Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(13);
	            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
	            if(map.get("LoginType").equals("Custom")){
	                LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
	                loginPage.login(map.get("Username"),map.get("Password"),map.get("DomainName"));
	                Thread.sleep(5000);
	            }
	            HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	            Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
	        }catch (Exception e){
	            PageFactory.reset();
	            driver.close();
	            e.printStackTrace();
	        }
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void NavigatetoFaxAddressPage() {
		 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMPage();
	     OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	     Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	     ocmHomePage.navigateToTab("FAX");
	     FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
	     Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
	     faxPage.navigateToFaxAddressBookPage();
	     FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
	     Assert.assertTrue(faxAddressBookPage.isFaxAddressBookPageDisplayed(), "FAX page assertion failed");
	     //faxAddressBookPage.NavigateToRecipientTab();
	}
	@Test(priority=1)
    public void FaxSendersPage() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
    	Assert.assertTrue(faxAddressBookPage.verifylogo(),"FaxSenderslogo assertion failed");
    	Assert.assertTrue(faxAddressBookPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("FaxAddressBookTest","maximize window");
    	Assert.assertTrue(faxAddressBookPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("FaxAddressBookTest","minimize window");
    }
	@Test(priority=2)
	    public void VerifyDropdownForAllTheColumns() {
			FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
	        Assert.assertTrue(faxAddressBookPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	    }
	    
	@Test(priority=3)
	    public void VerifyColumnsHeaderEnable() {
			FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
	        Assert.assertTrue(faxAddressBookPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	    }
	    
	@Test(priority=4)
	    public void VerifyColumnsHeaderDisable() {
			FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
	        Assert.assertFalse(faxAddressBookPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	    }
	    
	//@Test
	public void AddNewFaxAddressBookRecipientRecord() throws Exception {
		for(int i=0;i<100;i++) {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\RecipientData.xlsx";
			Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(i);
			FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
			FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
			faxAddressBookPage.addNewFaxTemplateRecord(faxAddressBoookDetails);
			Assert.assertEquals(faxAddressBookPage.getSuccessMessage(), "Record Created Successfully");
		}
	}
	
	
	 @AfterMethod
	    public void afterEachMethod(Method method) {
	    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("FaxAddressBookTest",method.getName());
	        driver.navigate().refresh();    }
	
	


}
