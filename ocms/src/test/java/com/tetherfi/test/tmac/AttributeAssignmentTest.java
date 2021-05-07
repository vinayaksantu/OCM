package com.tetherfi.test.tmac;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.tmac.AttributeAssignmentDetails;
import com.tetherfi.model.tmac.AttributesDetails;
import com.tetherfi.model.user.AgentSkillAssignmentNewDetails;
import com.tetherfi.pages.AgentSkillAssignmentNewPage;
import com.tetherfi.pages.AttributeAssignmentPage;
import com.tetherfi.pages.AttributesPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class AttributeAssignmentTest extends BaseTest {


	Screenshot screenshot=new Screenshot(driver);

	@BeforeMethod()
	public void NavigateToAttributesPage() {
		HomePage homePage=PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMPage();
		OCMHomePage ocmHomePage=PageFactory.createPageInstance(driver, OCMHomePage.class);
		Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HomePage Assertion Failed");
		ocmHomePage.navigateToTab("TMAC");
		TmacPage tmacPage=PageFactory.createPageInstance(driver, TmacPage.class);
		Assert.assertTrue(tmacPage.isTMACPageDisplayed(), "Tmac Page Assertion Failed");
		tmacPage.navigateToAttributeAssignmentPage();
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.isAttributeAssignmentPageIsDisplayed(), "Attributes PagenAssertion  failed");
	}

	@Test(priority=1,description="To Verify Attribute Assignment Page")
	public void VerifyAttributeAssignmentPage() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyLogo(), "AttributesTest Logo Assertion Failed");
		Assert.assertTrue(attributeAssignmentPage.maximizeWindow(), "fullScreen Assertion Failed");
		screenshot.captureScreen("AttributeAssignmentTest", "maximize window");
		Assert.assertTrue(attributeAssignmentPage.minimizeWindow(), "Restore Assertion Failed");
		screenshot.captureScreen("AttributeAssignmentTest", "minimize window");
	}

	@Test(priority=2,description="To Verify Categories with Attribute Module")
	public void VerifyPresenceOfAttributeAssignmentPage() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.VerifythePresenceOfAttributesAssignmentPage(), "VerifyPresenceOfAttributeAssignmentPage assertion Failed");
	}

	@Test(priority=3,description="To Verify Categories with Attribute Module")
	public void VerifyPresenceOfAttributeAssignmentPageCategoriesTabs() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.VerifyAttributeAssignmentPageCategories(), "VerifyPresenceOfAttributeAssignmentPage assertion Failed");
	}

	@Test(priority=4,description="To Verify Transfer To AND From Button")
	public void VerifyTransferToANDFromButton() throws Exception  {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Data").getTestData().get(0);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyTransferToANDFromButton(attributeAssignmentDetails), "VerifyTransferToANDFromButton assertion Failed");
	}

	@Test(priority=5,description="To Verify Bulk Transfer To AND From Button")
	public void VerifyBulkTransferToANDFromButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Data").getTestData().get(0);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyBulkTransferToANDFromButton(attributeAssignmentDetails), "VerifyTransferToANDFromButton assertion Failed");
	}

	@Test(priority=6,description="To Verify Save Button without selecting the Agent")
	public void VerifySaveButtonwithoutSelectingAgents() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.VerifySaveButtonwithoutSelectingAgents();
		Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "At least one agent is required", "VerifySaveButtonwithoutSelectingAgents Assertion Failed");
	}
	
	@Test(priority=7,description="To Verify Save Button Without Selecting the Attributes")
	public void VerifySaveButtonwithoutSelectingAttributes() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Data").getTestData().get(9);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.VerifySaveButtonwithoutSelectingAttributes(attributeAssignmentDetails);
		Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "At least one Attribute is required", "VerifySaveButtonwithoutSelectingAgents Assertion Failed");
	}

	@Test(priority=8,description="To Verify Clear Button")
	public void VerifyClearButton() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "Data").getTestData().get(0);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.VerifyClearButton(attributeAssignmentDetails), "VerifyTransferToANDFromButton assertion Failed");
	}

	@Test(priority=9,description="To Verify UI Team Names with DB")
	public void VerifyTeamNamewithDataBase() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Queries").getTestData().get(0);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyTeamNameswithDataBase(attributeAssignmentDetails.getQuery()), "DataBase Assertion failed");
	}

	@Test(priority=10,description="To Verify Prsence of Agent Attributes Window")
	public void VerifyPresenceofAgentAttributesWindow() throws Exception {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.VerifyPresenceofAgentAttributesDetails(), "VerifyPresenceofAgentAttributesWindow Assertion failed");
	}

	@Test(priority=11,description="To Verify Export Excel Button")
	public void ExportToExcelButton() {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.ExportToExcelButton(filePath), "Export Button assertion failed");
	}

	@Test(priority=12,description="To Verify DropDown Coloumns")
	public void verifyDropDownForAllTheColumns() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
	}

	@Test(priority=13,description="To Verify Grid when Coloumns Header Enable")
	public void VerifyColumnsHeaderEnable() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
	}

	@Test(priority=14,description=" To Verify Grid when Coloumn Header Disabled")
	public void VerifyColumnsHeaderDisable() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertFalse(attributeAssignmentPage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
	}

	@Test(priority=15,description="To Verify arrow Move previous and Next Page ")
	public void verifyArrowMoveForPreviousAndNextPage() throws Exception {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyArrowMoveForPreviousAndNextPage(),"Previous and Next Page Button Assertion Failed");
	}

	@Test(priority=16,description=" To Verify Arrow Move for First and Last Page")
	public void verifyArrowMoveForFirstAndLastPage() throws Exception {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyArrowMoveForFirstAndLastPage(), "First and Last Page Assertion Failed");
	}

	@Test(priority=17,description="To Verify Total Number of Items Per Page Deatials ")
	public void verifyTotalNumberOfItemsPerPageDetails() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyTotalNumberOfItemsPerPageDetails(), "Number of Items per Page assertion Failed");
	}

	@Test(priority=18,description="To Verify Number of Items PerPage Selection ")
	public void verifyNumberOfItemsPerPageSelection() {
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		Assert.assertTrue(attributeAssignmentPage.verifyNumberOfItemsPerPage(), "Number of Items per Page Assertion Failed");
	}
	
	@Test(priority=19,description="To Verify Search Channel CategoryTab Attributes ")
    public void SearchChannelCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(0);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchChannelCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=20,description="To Verify Search Intent CategoryTab Attributes ")
    public void SearchIntentCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(1);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchIntentCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=21,description="To Verify Search Package CategoryTab Attributes ")
    public void SearchPackageCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(2);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchPackageCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=22,description="To Verify Search CustomerType CategoryTab Attributes ")
    public void SearchCustomerTypeCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(3);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchCustomerTypeCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	
	@Test(priority=23,description="To Verify Search Age CategoryTab Attributes ")
    public void SearchAgeCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(4);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchAgeCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=24,description="To Verify Search Gender CategoryTab Attributes ")
    public void SearchGenderCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(5);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchGenderCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=25,description="To Verify Search Location CategoryTab Attributes ")
    public void SearchLocationCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(6);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchLocationCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=26,description="To Verify Search Sentiment CategoryTab Attributes ")
    public void SearchSentimentCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(7);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchSentimentCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }
	
	@Test(priority=27,description="To Verify Search Language CategoryTab Attributes ")
    public void SearchLanguageCategoryTabAttribute() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(8);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.SearchLanguageCategoryToAssignForAgentInAttributesTab(attributeAssignmentDetails);
        Assert.assertTrue(attributeAssignmentPage.verifySearchedAttributeData(attributeAssignmentDetails),"Assign skill assertion failed");
    }

	@Test(priority=28,description="To Verify Assign Attributes from Channel Tab")
	public void VerifyAssignAttributesfromChannelTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(0);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}

	@Test(priority=29,description="To Verify Assign Attributes from Intent Tab")
	public void VerifyAssignAttributesfromIntentTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(1);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}

	@Test(priority=30,description="To Verify Assign Attributes from Package Tab")
	public void VerifyAssignAttributesfromPackageTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(2);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}

	@Test(priority=31,description="To Verify Assign Attributes from CustomerType Tab")
	public void VerifyAssignAttributesfromCustomerTypeTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(3);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}

	@Test(priority=32,description="To Verify Assign Attributes from Age Tab")
	public void VerifyAssignAttributesfromAgeTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(4);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}

	@Test(priority=33,description="To Verify Assign Attributes from Gender Tab")
	public void VerifyAssignAttributesfromGenderTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(5);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}

	@Test(priority=34,description="To Verify Assign Attributes from Loaction Tab")
	public void VerifyAssignAttributesfromLocationTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(6);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}

	@Test(priority=35,description="To Verify Assign Attributes from Sentiment Tab")
	public void VerifyAssignAttributesfromSentimentTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(7);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}
	
	@Test(priority=36,description="To Verify Assign Attributes from Language Tab")
	public void VerifyAssignAttributesfromLanguageTab() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AttributeAssignmentData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Data").getTestData().get(8);
		AttributeAssignmentDetails attributeAssignmentDetails=new AttributeAssignmentDetails(map);
		AttributeAssignmentPage attributeAssignmentPage=PageFactory.createPageInstance(driver, AttributeAssignmentPage.class);
		attributeAssignmentPage.verifyAssignAttributesfromTab(attributeAssignmentDetails);
		//Assert.assertEquals(attributeAssignmentPage.VerifyMessage(), "Attribute Assigned Successfully", "VerifyAssignAttributesfromChannelTab Assertion Failed");
	}




	@AfterMethod
	public void afterEachMethod(Method method){
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("AttributeAssignmentTest", method.getName());
		driver.navigate().refresh();
	}
}
