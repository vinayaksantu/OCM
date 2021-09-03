package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.Map;

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

public class TemplatesAuxCodeTabTest extends BaseTest {

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

	@Test(priority=1,description="Verify the Add new record Without Template name")
	public void AddRecWithoutTemplateName() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyAddTmpwithoutTemplatename(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Please Provide Template Name"); 	 
	}
	@Test(priority=2,description="To Verify Add New Template without AuxCode")
	public void AddTemplatewithOutAuxCode() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addNewAuxTemplateWithoutAuxcode(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "At least one aux is required", "Aux Assertion Failed");
	}

	@Test(priority=3,description="To Verify Window Close Button at Add new Aux record")
	public void VerifyAddNewAuxTemplateWindowCancelButton() throws Exception {
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.VerifyAddAuxTemplateWindowCancelButton(3), "CloseButton assertion Failed");
	}

	@Test(priority=4,description="To Verify Add New Aux Template with valid Data also demonstrates transfer to")
	public void AddNewAuxTemplate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.verifyAddnewAuxTemplate(AgentTemplateDetails),"AddButton assertion Failed");
	}

	//PRDOCM-62997
	@Test(priority=5,description="To Verify 'Transfer all to' Selected Aux List")
	public void VerifyTransferAllToBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferAllToBtnAuxTab(AgentTemplateDetails,3);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record updated Successfully", "Transfer all Assertion Failed");
	}

	//has defect PRDOCM-62997
	@Test(priority=6,description="To Verify 'Transfer from' Selected Aux List")
	public void VerifyTransferFromBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferFromBtn(AgentTemplateDetails,3);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record updated Successfully","Transfer from Assertion Failed");
	}

	//PRDOCM-62997
	//@Test(priority=7,description="To Verify 'Transfer all from' Selected Aux List")
	public void VerifyTransferAllFromBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferAllFromBtnAuxTab(AgentTemplateDetails,3);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "At least one aux is required","Transfer all Assertion Failed");
	}


	@Test(priority=8,description="verify the edit button without modify reason")
	public void EditRecordwithoutModifyreason() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editAuxRecordWithoutModifyreason(AgentTemplateDetails,3);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the modify reason","Edit record assertion failed");
	}
	@Test(priority=9,description="verify the cancel button at edit record")
	public void VerifyCancelBtnatEditRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editCancelAuxTab(AgentTemplateDetails,3);
		Assert.assertFalse(agentTemplatePage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
	}
	//has defect PRDOCM-62997
	@Test(priority=10,description="verify the edit button")
	public void EditAuxRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.editAuxRecord(AgentTemplateDetails,3);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record updated Successfully","Edit record assertion failed");
	}
	@Test(priority=11,description="verify the delete button without delete reason")
	public void deleteWithoutDeletereasonAuxtab() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.deleteWithoutDeletereasonAuxtab(AgentTemplateDetails,3);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the delete reason","Delete record assertion failed");
	}
	@Test(priority=12,description="verify the cancel button at delete record")
	public void VerifyCancelBtnatDeleteRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.DeleteCancelAuxtab(AgentTemplateDetails,3), "Cancel Btn at Edit record assertion failed");
	}
	@Test(priority=13,description="verify the delete button")
	public void DeleteAuxRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AuxTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.deleteAuxRecord(AgentTemplateDetails,3);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record Deleted Successfully","Delete record assertion failed");
	}

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AuxTabFinal", method.getName());
		driver.navigate().refresh();
	}
}



