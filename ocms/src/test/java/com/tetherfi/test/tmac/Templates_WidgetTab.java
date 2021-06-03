package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.AgentTemplateDetails;
import com.tetherfi.pages.AgentTemplatePage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class Templates_WidgetTab extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod
	public void NavigateToAgentTemplatePage() throws InterruptedException, Exception {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToAgentTemplatePage();
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.isAgentemplatePageIsDisplayed(), "AgentTemplatePage Assertion  failed");
	}

	@Test(priority=1,description="Verify the Add new record Without Widget")
	public void addTemplateWithoutWidget() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.addTemplateWithoutWidget(AgentTemplateDetails));

	}

	@Test(priority=2,description="To Verify Add New Template without Template Name")
	public void AddTemplatewithOutTemplateName() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyAddwidgetTmpwithoutTemplatename(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Please Provide Template Name", "Widget Assertion Failed");
	}

	@Test(priority=3,description="To Verify Add New Template without Screen")
	public void AddWidgetwithOutScreen() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyAddwidgetTmpwithoutScreen(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Please Select any one Screen", "screen Assertion Failed");
	}

	@Test(priority=4,description="To Verify Close Button at add new widget")
	public void VerifyAddNewWidgtTemplateWindowCloseButton() throws Exception {
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.VerifyAddWidgetTemplateWindowCancelButton(2), "CloseButton assertion Failed");
	}
	
	
	@Test(priority=5,description="To Verify Add New Widget Template with valid Data also demonstrates transfer to")
	public void AddNewWidgetTemplate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyAddnewWidgetTemplate(AgentTemplateDetails,2),"Wideget Assertion Failed");
	}
	
	//has defect ProdOCM-62997,
	//@Test(priority=6,description="To Verify 'Transfer all to' selected Widget")//Search Data issue
	public void VerifyTransferAllToBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferAllToBtnWidgetTab(AgentTemplateDetails,2);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record updated Successfully", "Transfer all Assertion Failed");
	}

	//has defect ProdOCM-62997,
	//@Test(priority= 7,description="To Verify 'Transfer from' selected widget values")//Serach Data Issue
	public void VerifyTransferFromBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferFromBtnWidgetTab(AgentTemplateDetails,2);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Record updated successfully","Transfer from Assertion Failed");
	}
	
	//PRDOCM-66196 
	//@Test(priority=8,description="To Verify 'Transfer all from' selected Widget")//Search Data issue
	public void VerifyTransferAllFromBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferAllFromBtnWidgetTab(AgentTemplateDetails,2);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "At least one screen is required","TransferAll Assertion Failed");
	}

	@Test(priority=9,description="verify the edit button without modify reason")
	public void EditRecordwithoutModifyreason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editWidgetRecordWithoutModifyreason(AgentTemplateDetails,2);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the modify reason","Edit record assertion failed");
	}
	
	@Test(priority=10,description="verify the cancel button at edit record")
	public void VerifyCancelBtnatEditRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.editCancelWidgetTab(AgentTemplateDetails,2),"Cancel Btn at Edit record assertion failed");  	 
	}

	@Test(priority=11,description="verify the edit button")
	public void EditWidgetRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editWidgetRecord(AgentTemplateDetails,2);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record updated Successfully","Edit record assertion failed");
	}
	
	@Test(priority=12,description="verify the delete button without delete reason")
	public void deleteWithoutDeletereason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.deleteWithoutDeletereasonWidgettab(AgentTemplateDetails,2);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the delete reason","Delete record assertion failed");
	}
	
	@Test(priority=13,description="verify the cancel button at delete record")
	public void VerifyCancelBtnatDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.DeleteCancelWidgettab(AgentTemplateDetails,2), "Cancel Btn at Edit record assertion failed");
	}
	
	@Test(priority=14,description="verify the delete button")
	public void DeleteWidgetRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"WidgetTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.deleteWidgetRecord(AgentTemplateDetails,2);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record Deleted Successfully","Delete record assertion failed");
	}
	//in database every assignment mapping gets created as a new record,cannot be automated,template name gets displayed on UI and also has UI defect
	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("WidgetTab1", method.getName());
		driver.navigate().refresh();
	}
}
