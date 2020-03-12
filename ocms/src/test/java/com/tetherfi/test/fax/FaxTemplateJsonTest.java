package com.tetherfi.test.fax;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxTemplateJsonTest extends BaseTest{
	  String destinationFilePath = "E:/Product/scripts/ocms/src/test/resources/DownloadedFiles/Json/FaxTemplate.json";
	    String remoteFilePath = "\\\\172.16.2.16\\Products\\OCM\\UI\\CustomJSONConfigurations\\Admin%20Modules\\FaxTemplate.json";

	    @BeforeMethod
	    public void NavigateToAgentSettingsPage() throws InterruptedException {
	        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
	        homePage.navigateToOCMPage();
	        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
	        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
	        ocmHomePage.navigateToTab("Fax");
	        FaxPage faxPage = PageFactory.createPageInstance(driver,FaxPage.class);
	        Assert.assertTrue(faxPage.isFaxPageDisplayed(),"Fax page assertion failed");
	        faxPage.navigateToFaxTemplatePage();
	        FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
	        Assert.assertTrue(faxTemplatePage.isFaxTemplatePageDisplayed(),"FaxTemplate page assertion failed");

	        FTPServer ftp=new FTPServer();
	        ftp.transferFileFromRemote(remoteFilePath,destinationFilePath);
	    }
	    
	   /* @Test
	    public void VerifyJsonDataForMakerAndChecker(){
	        FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
	        JSONReader json= new JSONReader(destinationFilePath);
	        Assert.assertTrue(faxTemplatePage.verifyJsonDataForMakerAndChecker(json.getJsonMakerCheckerKeyData()),"JSON data Maker Checker assertion failed");
	    }
	    
	    @Test
	    public void VerifyJsonDataForGridColumnHidden(){
	        FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
	        faxTemplatePage.selectFaxTemplateAuditTrailTab();
	        faxTemplatePage.selectMakeFaxTemplateChanges();
	        //faxTemplatePage.enableAllColumnsHeaders();
	        JSONReader json= new JSONReader(destinationFilePath);
	        Assert.assertTrue(faxTemplatePage.verifyJsonDataForgridColumnHidden(json.getJsonGridColumnTitleKeyData("Hidden")),"JSON data grid column hidden assertion failed");
	    }
	    
	    @Test
	    public void VerifyJsonDataForColumnIncludeGrid(){
	    	FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
	        faxTemplatePage.selectFaxTemplateAuditTrailTab();
	        faxTemplatePage.selectMakeFaxTemplateChanges();
	        JSONReader json= new JSONReader(destinationFilePath);
	        Assert.assertTrue(faxTemplatePage.verifyJsonDataForColumnIncludeGrid(json.getJsonGridColumnTitleKeyData("IncludeGrid")),"JSON data column include grid assertion failed");
	    }
	    
	    @Test
	    public void VerifyJsonDataForMandatoryField(){
	    	FaxTemplatePage faxTemplatePage=PageFactory.createPageInstance(driver,FaxTemplatePage.class);
	        faxTemplatePage.selectFaxTemplateAuditTrailTab();
	        faxTemplatePage.selectMakeFaxTemplateChanges();
	        faxTemplatePage.selectAddNewFaxTemplate();
	        JSONReader json= new JSONReader(destinationFilePath);
	        Assert.assertTrue(faxTemplatePage.verifyJsonDataForMandatoryField(json.getJsonGridColumnTitleKeyData("Mandatory")),"JSON data mandatory field assertion failed");
	    }
	    
	    @AfterMethod
	    public void afterEachMethod(Method method){
	        Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen( "FaxTemplateJsonTest",method.getName());
	        driver.navigate().refresh();
	}*/
}
