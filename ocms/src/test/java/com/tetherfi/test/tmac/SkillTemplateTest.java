package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.AttributeAssignmentDetails;
import com.tetherfi.model.tmac.AttributesDetails;
import com.tetherfi.model.tmac.SkillTemplateDetails;
import com.tetherfi.model.user.AgentSkillAssignmentNewDetails;
import com.tetherfi.pages.AgentSkillAssignmentNewPage;
import com.tetherfi.pages.AttributeAssignmentPage;
import com.tetherfi.pages.AttributesPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.SkillTemplatePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class SkillTemplateTest extends BaseTest {

Screenshot screenshot=new Screenshot(driver);
	
	@BeforeClass()
	public void NavigateToSkillTemplatePage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToSkillTemplatePage();
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.isSkillTemplatePageIsDisplayed(), "SkillTemplatePage Assertion  failed");
	}
	
	@Test(priority=1,description="To Verify Skill Template Page")
	public void VerifySkillTemplatePage() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyLogoButtonPresence(), "SkillTemplate Logo and Button Presence Assertion Failed");
		Assert.assertTrue(skillTemplatePage.maximizeWindow(), "fullScreen Assertion Failed");
		screenshot.captureScreen("SkillTemplateTest", "maximize window");
		Assert.assertTrue(skillTemplatePage.minimizeWindow(), "Restore Assertion Failed");
		screenshot.captureScreen("SkillTemplateTest", "minimize window");
		Assert.assertTrue(skillTemplatePage.VerifySkillTemplatePageHeader(), "VerifySkillTemplatePage Headers Assertion Failed");
	}
	
	@Test(priority=2,description="To Verify DropDown Coloumns")
	public void verifyDropDownForAllTheColumns() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
	}

	@Test(priority=3,description="To Verify Grid when Coloumns Header Enable")
	public void VerifyColumnsHeaderEnable() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
	}

	@Test(priority=4,description=" To Verify Grid when Coloumn Header Disabled")
	public void VerifyColumnsHeaderDisable() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertFalse(skillTemplatePage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
	}
	
	@Test(priority=5,description="To Verify Add New Skill Template Window Presence")
	public void VerifyAddNewSkillTemplateWindowPresence() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.VerifyPresenceOfAddNewSkillTemplateWindow(), "VerifyAddNewSkillTemplateWindowPresence assertion Failed");
	}
	
	@Test(priority=6,description="To Verify UI Team Names with DB")
	public void VerifyTeamNamewithDataBase() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Queries").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyTeamNameswithDataBase(skillTemplateDetails.getQuery()), "DataBase Assertion failed");
	}
	
	@Test(priority=7,description="To Verify Add New Skill Template without Template Name")
	public void AddNewSkillTemplatewithOutTemplateName() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.addNewSkillTemplateWithoutTemplateName(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "Please Provide Template Name", "AddNewSkillTemplatewithOutTemplateName Assertion Failed");
	}
	
	@Test(priority=8,description="To Verify Add New Skill Template without OrgUnit")
	public void AddNewSkillTemplatewithOutOrgUnit() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.addNewSkillTemplateWithoutOrgUnit(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "Please Select Org Unit", "AddNewSkillTemplatewithOutOrgUnit Assertion Failed");
	}
	
	@Test(priority=9,description="To Verify Add New Skill Template without Skill")
	public void AddNewSkillTemplatewithOutSkill() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.addNewSkillTemplateWithoutSkill(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "At least one skill is required", "AddNewSkillTemplatewithOutSkill Assertion Failed");
	}
	
	@Test(priority=10,description="To Verify Add New Skill Template Window Close Button")
	public void VerifyAddNewSkillTemplateWindowCloseButton() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.VerifyAddSkillTemplateWindowCloseButton(), "VerifyAddNewSkillTemplateWindowCloseButton assertion Failed");
	}
	
	@Test(priority=11,description="To Verify Search Voice Skills")
    public void SearchVoiceSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchVoiceSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=12,description="To Verify Search Text Chat Skills")
    public void SearchTextChatSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(1);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchTextChatSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=13,description="To Verify Search Audio Chat Skills")
    public void SearchAudioChatSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(2);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchAudioChatSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=14,description="To Verify Search Video Chat Skills")
    public void SearchVideoChatSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(3);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchVideoChatSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=15,description="To Verify Search Fax Skills")
    public void SearchFaxSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(4);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchFaxSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=16,description="To Verify Search Email Skills")
    public void SearchEmailSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(5);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchEmailSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=17,description="To Verify Search Sms Skills")
    public void SearchSMsSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(6);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchSMSSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=18,description="To Verify Search Email Channel Skills")
    public void SearchEmailChannelSkills() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(7);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.SearchEmailChannelSkillsToAssign(skillTemplateDetails);
        Assert.assertTrue(skillTemplatePage.verifySearchedMultiTabSkillData(skillTemplateDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=19,description="To Verify Add New Skill Template with valid Data")
	public void AddNewSkillTemplate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.verifyAddnewSkillTemplate(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "Record Created Successfully", "AddNewSkillTemplate Assertion Failed");
	}
	
	@Test(priority=20,description="To Verify Add New Skill Template with valid Data")
	public void EditSkillTemplate() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Edit").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.EditSkillTemplate(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "Record Updated Successfully", "AddNewSkillTemplate Assertion Failed");
	}
	
	@Test(priority=21,description="To Verify Delete No Button ")
	public void verifyDeleteNoButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.DeleteCancel(skillTemplateDetails);
		Assert.assertTrue(skillTemplatePage.DeleteCancel(skillTemplateDetails), "Delete No Buuton Assertion failed");
	}

	@Test(priority=22,description="To Verify Delete without Delete Reason")
	public void verifyDeleteWithoutDeleteReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.DeleteWithoutReason(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "Please enter the delete reason", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=23,description="To Vrify Delete Record ")
	public void DeleteSkillTemplateRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.DeleteRecord(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "Record Deleted Successfully", "DeleteTmacAuxCodesRecord Assertion Failed");
	}
	
	@Test(priority=24,description="To Verify Export Excel Button")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.ExportToExcelButton(filePath), "Export Button assertion failed");
	}

	@Test(priority=25,dependsOnMethods="ExportToExcelButton",description="To Verify Exported Data")
	public void verifyExportedData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Skill Template.xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=26,description="To Verify Export Excel without Data")
	public void verifyExportToExcelWithoutData() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Invalid").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.VerifyExportToExcelWithoutData(skillTemplateDetails), "Export to Excel Without Data assertion failed");
	}
	
	@Test(priority=27,description="To Verify Coloumns Sort By Ascending ")
	public void verifySortByAscending() throws Exception {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.verifySortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Skill Template (1).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(skillTemplatePage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=28,description="To Verify Coloumns Sort By Descending ")
	public void verifySortByDescending() throws Exception {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.verifySortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Skill Template (2).xlsx";
		List<Map<String,String>>maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(skillTemplatePage.VerifyExportToExcelSheet(maplist), "Export Data Assertion failed");
	}

	@Test(priority=29,description="To Verify Group By Coloumns ")
	public void verifyGroupBy() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("SkillTemplateTest", "group By Column");
		Assert.assertTrue(skillTemplatePage.groupBy(), "Group By Assertion failed");
		screenshot.captureScreen("SkillTemplateTest", "Already grouped Coloumn");
	}

	@Test(priority=30,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPage() throws Exception {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=31,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPage() throws Exception {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
	}

	@Test(priority=32,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
	}

	@Test(priority=33,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelection() {
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=34,description="To Verify Search Page ")
	public void searchPage() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.clearAll(skillTemplateDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("SkillTemplateTest", "clearall");
		Assert.assertTrue(skillTemplatePage.verifyclose());
		screenshot.captureScreen("SkillTemplateTest", "SearchClose");
	}

	@Test(priority=35,description="To Verify Search without Search Textbox Data ")
	public void searchwithoutSearchTextbox() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		skillTemplatePage.searchwithoutextsearch(skillTemplateDetails);
		Assert.assertEquals(skillTemplatePage.VerifyMessage(), "Please enter the text to search or remove the filter", "verifyWithoutDeleteReason Assertion Failed");
	}

	@Test(priority=36,description=" To Verify Search Clear Search Button")
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifyinvalidsearchwithwrongdata(skillTemplateDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("SkillTemplateTest","Invalid Search with wrong data");
		Assert.assertTrue(skillTemplatePage.verifyclearsearch(), "Clear All Assertion Failed");
	}
	
	@Test(priority=37,description="To Verify Search Data Is Not Equal to ")
	public void SearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(0);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifySearchIsnotEqualTo(skillTemplateDetails.getTemplatename()), "Search assertion Failed");
	}

	@Test(priority=38,description="To Verify Search Data Contains ")
	public void SearchContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(1);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifySearchContains(skillTemplateDetails.getTemplatename()), "Search assertion Failed");
	}

	@Test(priority=39,description="To Verify Search Data Doesnot Contain ")
	public void SearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(2);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifySearchDoesNotContains(skillTemplateDetails.getTemplatename()), "Search assertion Failed");
	}

	@Test(priority=40,description="To Verify Search Data Starts with ")
	public void SearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(3);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifySearchStartsWith(skillTemplateDetails.getTemplatename()), "Search assertion Failed");
	}

	@Test(priority=41,description="To Verify search Data Ens with ")
	public void SearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SkillTemplateData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"Invalid").getTestData().get(4);
		SkillTemplateDetails skillTemplateDetails=new SkillTemplateDetails(map);
		SkillTemplatePage skillTemplatePage=PageFactory.createPageInstance(driver, SkillTemplatePage.class);
		Assert.assertTrue(skillTemplatePage.verifySearchEndsWith(skillTemplateDetails.getTemplatename()), "Search assertion Failed");
	}
	
	
	
	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("SkillTemplateTest", method.getName());
		driver.navigate().refresh();
	}
	
	
	
}
