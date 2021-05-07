package com.tetherfi.test.tmac;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.pages.AgentSkillSchedulerPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AgentSkillSchedulerTest extends BaseTest {

	
	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod()
	public void NavigateToAgentSkillSchedulerPage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToAgentSkillSchedulerPage();
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		Assert.assertTrue(AgentSkillSchedulerPage.isAgentSkillSchedulerPageIsDisplayed(), "AgentSkillScheduler Page Assertion  failed");
	}
	
	@Test(priority=1,description="To Verify Skill Template Page")
	public void VerifyAgentSkillSchedulerPage() {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		Assert.assertTrue(AgentSkillSchedulerPage.verifyLogo(), "AgentSkillSchedulerPage Logo Assertion Failed");
		Assert.assertTrue(AgentSkillSchedulerPage.maximizeWindow(), "fullScreen Assertion Failed");
		screenshot.captureScreen("AgentSkillSchedulerTest", "maximize window");
		Assert.assertTrue(AgentSkillSchedulerPage.minimizeWindow(), "Restore Assertion Failed");
		screenshot.captureScreen("AgentSkillSchedulerTest", "minimize window");
		Assert.assertTrue(AgentSkillSchedulerPage.VerifyAgentSkillSchedulerPageHeader(), "VerifySkillTemplatePage Headers Assertion Failed");
	}
	
	@Test(priority=2,description="To Verify DropDown Coloumns")
	public void verifyDropDownForAllTheColumns() {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
	}

	@Test(priority=3,description="To Verify Grid when Coloumns Header Enable")
	public void VerifyColumnsHeaderEnable() {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
	}

	@Test(priority=4,description=" To Verify Grid when Coloumn Header Disabled")
	public void VerifyColumnsHeaderDisable() {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertFalse(AgentSkillSchedulerPage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
	}
	
	@Test(priority=5,description="To Verify Export Excel Button")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.ExportToExcelButton(filePath), "Export Button assertion failed");
	}

	@Test(priority=6,dependsOnMethods="ExportToExcelButton",description="To Verify Exported Data")
	public void verifyExportedData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Scheduler.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}
	
	@Test(priority=7,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscending() throws Exception {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		AgentSkillSchedulerPage.verifySortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Scheduler (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentSkillSchedulerPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=8,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescending() throws Exception {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		AgentSkillSchedulerPage.verifySortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Agent Skill Scheduler (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(AgentSkillSchedulerPage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=9,description="To Verify Group By Coloumns ")
	public void verifyGroupBy() {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("AgentSkillSchedulerTest", "group By Column");
		Assert.assertTrue(AgentSkillSchedulerPage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("AgentSkillSchedulerTest", "Already grouped Coloumn");
	}

	@Test(priority=10,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPage() throws Exception {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=11,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPage() throws Exception {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
	}

	@Test(priority=12,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
	}

	@Test(priority=13,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelection() {
		AgentSkillSchedulerPage AgentSkillSchedulerPage=PageFactory.createPageInstance(driver, AgentSkillSchedulerPage.class);
		AgentSkillSchedulerPage.NavigateToSkillSchedulerTab();
		Assert.assertTrue(AgentSkillSchedulerPage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
	}
	
	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AgentSkillSchedulerTest", method.getName());
		driver.navigate().refresh();
	}
}
