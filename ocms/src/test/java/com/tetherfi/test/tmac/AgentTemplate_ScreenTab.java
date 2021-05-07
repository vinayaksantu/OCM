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

public class AgentTemplate_ScreenTab extends BaseTest {

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
		Map<String, String> map = new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
    	AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
    	agentTemplatePage.verifyAddScreenTemplateWithoutTemplate(AgentTemplateDetails);
    	Assert.assertEquals(agentTemplatePage.getErrorMsg(), "Please Provide Template Name"); 	 
    }
	
	@Test(priority=2,description="To Verify Add New Screen Template without Screen")
	public void AddTemplatewithOutScreen() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.addNewScreenTemplateWithoutScreen(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "At least one screen is required", "screen Assertion Failed");
	}
	
	@Test(priority=3,description="To Verify Add New Screen Template Window Close Button")
	public void VerifyAddNewScreenTemplateWindowCloseButton() throws Exception {
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		Assert.assertTrue(agentTemplatePage.VerifyAddScreenTemplateWindowCloseButton(1), "CloseButton assertion Failed");
	}
	
	@Test(priority=4,description="To Verify Add New Screen Template with valid Data")
	public void AddNewScreenTemplate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyAddnewScreenTemplate(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record inserted Successfully", "Screen Assertion Failed");
	}
	
	@Test(priority=5,description="To Verify 'Transfer all to' screen values")
	public void VerifyTransferAllToBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferAllToBtn(AgentTemplateDetails);
		Assert.assertEquals(agentTemplatePage.VerifyMessage(), "Record inserted Successfully", "Screen Assertion Failed");
	}
	@Test(priority=6,description="To Verify 'Transfer all from' screen values")
	public void VerifyTransferAllFromBtn() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
		agentTemplatePage.verifyTransferAllFromBtn(AgentTemplateDetails,1);
		Assert.assertEquals(agentTemplatePage.getErrorMsg(), "At least one screen is required","Screen Assertion Failed");
	}
			
	@Test(priority=7,description="verify the edit button without modify reason")
    public void EditRecordwithoutModifyreason() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
    	AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
    	agentTemplatePage.editScreenRecordWithoutModifyreason(AgentTemplateDetails,1);
        Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the modify reason","Edit record assertion failed");
    }
	@Test(priority=8,description="verify the cancel button at edit record")
    public void VerifyCancelBtnatEditRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
    	AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
    	Assert.assertTrue(agentTemplatePage.editCancelScreenTab(AgentTemplateDetails,1),"Cancel Btn at Edit record assertion failed");  	 
    }
	//PRDOCM-62994, PRDOCM-62997
	@Test(priority=9,description="verify the edit button")
    public void EditScreenRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
    	AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
    	agentTemplatePage.editScreenRecord(AgentTemplateDetails,1);
        Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record inserted Successfully","Edit record assertion failed");
    }
	@Test(priority=10,description="verify the delete button without delete reason")
    public void DeleteRecordwithoutDeletereason() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
    	AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
    	agentTemplatePage.deleteWithoutDeletereasonScreentab(AgentTemplateDetails,1);
        Assert.assertEquals(agentTemplatePage.getErrorMsg(),"Please enter the delete reason","Delete record assertion failed");
    }
	@Test(priority=11,description="verify the cancel button at delete record")
    public void VerifyCancelBtnatDeleteRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
    	AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
    	agentTemplatePage.DeleteCancelScreentab(AgentTemplateDetails,1);
    	Assert.assertTrue(agentTemplatePage.DeleteCancelScreentab(AgentTemplateDetails,1),"Cancel Btn at delete screen record assertion failed");
    }
    
	@Test(priority=12,description="verify the delete button")
    public void DeletescreenTemplateRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"ScreenTab").getTestData().get(0);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
    	AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class);
    	agentTemplatePage.deleteScreenRecord(AgentTemplateDetails,1);
        Assert.assertEquals(agentTemplatePage.VerifyMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }
    
	@Test(priority=13,description="To verify template screen UI data against DB")
	public void verifyDatabase() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AgentTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(1);
		AgentTemplateDetails AgentTemplateDetails=new AgentTemplateDetails(map);
		AgentTemplatePage agentTemplatePage=PageFactory.createPageInstance(driver, AgentTemplatePage.class); 	
		agentTemplatePage.NavigateToTemplateScreenTab();
		Assert.assertTrue(agentTemplatePage.verifyDatabase1(AgentTemplateDetails.getQuery(), 1));
	}
	

	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AgentTemplateTestScreen3", method.getName());
		driver.navigate().refresh();
	}

}
