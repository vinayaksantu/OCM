package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

public class AgentTemplate_TemplateTab extends BaseTest {

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

	@Test(priority=1,description="Verify the add button at Template tab") 
	public void VerifyaddBtn() throws Exception{   	
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyAddButton(),"Label assertion failed");
	}
	@Test(priority=2,description="Verify the save button at Add new template record With duplicate org unit")
	public void AddnewtemplateRecordWithDuplicateOrgunit() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addnewTemplateRecord(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Org.Unit is already using by another template, Please Select Other Org.Unit"); 	 
	}

	@Test(priority=3,description="Verify the save button at Add new template record")
	public void AddnewtemplateRecord() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addnewTemplateRecord(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record Inserted Successfully"); 	 
	}

	@Test(priority=4,description="Verify the Add new record Without org unit")
	public void AddRecWithoutOrgunit() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addRecwithoutOrgUnit(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Please Select Org.Unit"); 	 
	}

	@Test(priority=5,description="Verify the Add new record Without Template name")
	public void AddRecWithoutTemplateName() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addRecwithoutTemplateName(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Please Enter Template Name"); 	 
	}


	@Test(priority=6,description="Verify the Add new record Without Template name")
	public void AddRecWithoutTheme() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addRecwithoutTheme(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Please Select Theme"); 	 
	}

	@Test(priority=7,description="Verify the save button at Add new template record")
	public void AddRecWithDuplicateTemplateName() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(2);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addnewTemplateRecord(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record with same Template Name exists"); 	 
	}
	//has defectPRDOCM-62997
	@Test(priority=8,description="verify the edit button")
	public void EditRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editRecord(AgentTemplateDetails,0);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record Inserted Successfully","Edit record assertion failed");
	}
	@Test(priority=9,description="verify the edit button without modify reason")
	public void EditRecordwithoutModifyreason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editRecordWithoutModifyreason(AgentTemplateDetails,0);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the modify reason","Edit record assertion failed");
	}
	@Test(priority=10,description="verify the cancel button at edit record")
	public void VerifyCancelBtnatEditRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editCancel(AgentTemplateDetails,0);
		Assert.assertFalse(agentTemplatePage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
	}

	@Test(priority=11,description="verify the delete button without delete reason")
	public void DeleteRecordwithoutModifyreason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.deleteRecordWithoutDeletereason(AgentTemplateDetails,0);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the delete reason","Delete record assertion failed");
	}
	@Test(priority=12,description="verify the cancel button at delete record")
	public void VerifyCancelBtnatDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.DeleteCancel(AgentTemplateDetails,0);
		Assert.assertFalse(agentTemplatePage.verifyDeleteFormContainer(), "Cancel Btn at Edit record assertion failed");
	}
	@Test(priority=13,description="verify the delete button")
	public void DeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"TemplateTab").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.deleteRecord(AgentTemplateDetails,0);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record Deleted Successfully","Delete record assertion failed");
	}


	@Test(priority=14)
	public void verifydatabase() throws Exception{
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Queries").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		agentTemplatePage.sortTemplateNameByAsc();
		Assert.assertTrue(agentTemplatePage.verifyTemplateDatabase(AgentTemplateDetails.getQuery()));

	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AgentTemplateTest1", method.getName());
		driver.navigate().refresh();
	}
}
