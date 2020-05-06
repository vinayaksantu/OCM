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

import com.tetherfi.model.chat.ChatTemplateDetails;
import com.tetherfi.model.fax.FaxAddressBookDetails;
import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.model.user.CepEventMappingDetails;
//import com.tetherfi.model.user.faxAddressBookDetails;
import com.tetherfi.pages.AdhocOptionEnhancementPage;
import com.tetherfi.pages.CepEventMappingPage;
//import com.tetherfi.pages.faxAddressBookPage;
import com.tetherfi.pages.ChatTemplatesPage;
import com.tetherfi.pages.FaxAddressBookPage;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxSendersPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxAddressBookTest extends BaseTest{
	Screenshot screenshot=new Screenshot(driver);

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
		// faxAddressBookPage.NavigateToRecipientTab();
	}

	/*@Test(priority=1)
	public void VerifyFaxAddressBookPage() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifylogo(),"FaxSenderslogo assertion failed");
		Assert.assertTrue(faxAddressBookPage.maximizewindow(),"Fullscreen Assertion Failed"); 
		screenshot.captureScreen("FaxAddressBookTest","maximize window");
		Assert.assertTrue(faxAddressBookPage.minimizewindow(), "Restored Assertion Failed");
		screenshot.captureScreen("FaxAddressBookTest","minimize window");
	}

	@Test(priority=2)
	public void VerifyDropdownForAllTheColumnsRecipient() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	} 
	
	@Test(priority=3)
	public void VerifyRecipientColumnsHeaderEnable() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	}

	/*@Test(priority=4)
	public void VerifyRecipientColumnsHeaderDisable() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertFalse(faxAddressBookPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}
	@Test(priority=5)
	public void VerifyRecipientDragAndDrop() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Thread.sleep(2000);
		faxAddressBookPage.dragRecipientColumntoGroup("Name1");
		Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("Name1",1),"drag and drop assertion failed");
	}

	@Test(priority=6)
	public void VerifyRecipientDragAndDropofAlreadyGroupedHeader() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Thread.sleep(2000);
		faxAddressBookPage.dragRecipientColumntoGroup("Name1");
		faxAddressBookPage.dragRecipientColumntoGroup("Name1");
		Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("Name1",1),"drag and drop assertion failed");
	}
	@Test(priority=7)
	public void ExportToExcelData() throws Exception{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Recipient.xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyexportToExcelSheet(maplist));	
	} 

	@Test(priority=8)
	public void SortingByAscending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.SortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Recipient (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxAddressBookPage.verifyexportToExcelSheet(maplist));
	}

	@Test(priority=9)
	public void SortingByDescending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.SortByDescending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Recipient (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxAddressBookPage.verifyexportToExcelSheet(maplist));
	}
	@Test(priority=10)
	public void VerifyRecipientArrowMoveForPreviousAndNextPageRecipient() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForPreviousAndNextPage(1),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=11)
	public void VerifyRecipientArrowMoveForFirstAndLastPageRecipient() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForFirstAndLastPage(1),"arrow move for first and last page assertion failed");
	}

	@Test(priority=12)
	public void VerifyRecipientTotalNumberOfItemsPerPageDetailsRecipient() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyTotalNumberOfItemsPerPageDetails(1),"item per page assertion failed");
	}

	@Test(priority=13)
	public void VerifyRecipientNumberOfItemsPerPageSelectionRecipient() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyNumberOfItemsPerPage(1),"item per page assertion failed");
	}

	@Test(priority=14)
	public void RecipientsearchPageClearAllandClose() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertFalse(faxAddressBookPage.RecipientclearAll(faxAddressBoookDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("FaxAddressBookTest", "clearall");
		Assert.assertTrue(faxAddressBookPage.verifyRecipientSearchclose());
	}

	@Test(priority=15)
	public void searchRecipientwithoutSearchTextbox() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.searchwithoutextsearch();
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}


	@Test(priority=16)
	public void VerifyCancelButtonatAddRecipientRecord() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.clickonAddNewRecipientRecord();
		faxAddressBookPage.clickOnCancelBtn();
		Assert.assertTrue(faxAddressBookPage.canclBtnverificationForRecepientTab(),"Cancel Btn at Add record assertion failed");
	}

	@Test(priority=17)
	public void AddNewRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(), "Record Created Successfully");
	}
	@Test(priority=18)
	public void AddNewRecipientRecordwithoutName2() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(1);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(priority=19)//,dependsOnMethods = "AddNewRecipientRecord")
	public void VerifyAuditTrailReportForCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyRecipientCreate(faxAddressBoookDetails,"Create"),"Audit Trail report assertion failed");
	}
	@Test(priority=20)
	public void VerifySearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.searchIsEqualto(faxAddressBookDetails));
	}
	@Test(priority=21)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifySearchIsNotEqualTo(faxAddressBookDetails));
	}
	@Test(priority=22)
	public void  VerifySearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifySearchContains(faxAddressBookDetails));
	}

	@Test(priority=23)
	public void  VerifySearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifySearchDoesNotContains(faxAddressBookDetails));
	}

	@Test(priority=24)
	public void  VerifySearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(2);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifySearchStartsWith(faxAddressBookDetails));
	}
	@Test(priority=25)
	public void  VerifySearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(3);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifySearchEndsWith(faxAddressBookDetails));
	}


	@Test(priority=26)
	public void AddDuplicateRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Record Creation Failed, Already Exist");
	}

	@Test(priority=27)
	public void AddNewEmptyRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewEmptyRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please Provide Name 1, Fax Number");
	}

	@Test(priority=28)
	public void AddNewRecipientWithoutName1Record() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientWithoutFirstNameRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please Provide Name 1");
	}


	@Test(priority=29)
	public void AddNewRecipientWithoutNumberRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientWithoutNumberRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please Provide Fax Number");
	}

	@Test(priority=30)
	public void VerifyCancelButtonatAddressBookRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		faxAddressBookPage.addNewRecipientWithoutNumberRecord(faxAddressBoookDetails);
		faxAddressBookPage.clickOnCancelBtn();
		Assert.assertTrue(faxAddressBookPage.canclBtnverificationForAddressTab(),"Cancel Btn at Add record assertion failed");
	}

	@Test(priority=31)
	public void AddNewFaxAddressBookRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(), "Record Created Successfully");
	}

	@Test(priority=32)//dependsOnMethods = "AddNewFaxAddressBookRecord")
	public void VerifyAuditTrailReportForAddressCreate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyFaxAddressCreate(faxAddressBoookDetails,"Create"),"Audit Trail report assertion failed");
	}
	@Test(priority=33)
	public void VerifyAddressBookSearchIsEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(1);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.searchAddressBookIsEqualto(faxAddressBookDetails));
	}
	@Test(priority=34)
	public void VerifyAddressBookSearchIsNotEqualTo() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(1);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);	   
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookSearchIsNotEqualTo(faxAddressBookDetails));
	}
	@Test(priority=35)
	public void  VerifyAddressBookSearchContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(1);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookSearchContains(faxAddressBookDetails));
	}

	@Test(priority=36)
	public void  VerifyAddressBookSearchDoesNotContains() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(1);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);	   
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookSearchDoesNotContains(faxAddressBookDetails));
	}

	@Test(priority=37)
	public void  VerifyAddressBookSearchStartsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(1);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);   
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookSearchStartsWith(faxAddressBookDetails));
	}

	@Test(priority=38)
	public void  VerifyAddressBookSearchEndsWith() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(2);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);   
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookSearchEndsWith(faxAddressBookDetails));
	}


	@Test(priority=38)
	public void AddNewDuplicateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Duplicate Name");
	}

	@Test(priority=39)
	public void AddNewEmptyRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewEmptyRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please Provide FaxLine, Name, Recipient");
	}

	@Test(priority=40)
	public void AddNewRecordWithoutFaxLine() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecordWithoutFaxline(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please Provide FaxLine");
	}

	@Test(priority=41)
	public void AddNewRecordWithoutName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecordWithoutName(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please Provide Name");
	}

	@Test(priority=42)
	public void AddNewRecordWithoutRecipients() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecordWithoutRecipients(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please Provide Recipient");
	}

	@Test(priority=43)
	public void ExportToExcel() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyExportToExcel(filePath));
	}

	@Test(priority=44)
	public void AddressBookExportToExcel() throws Exception
	{
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookExportToExcel(filePath));
	}

	@Test(priority=45)
	public void AddressBookExportToExcelData() throws Exception
	{	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\AddressBook.xlsx";
	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
	Assert.assertTrue(faxAddressBookPage.verifyAddressBookexportToExcelSheet(maplist));	
	} 

	@Test(priority=46)
	public void AddressBookSortingByAscending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.AddressBookSortByAscending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\AddressBook (1).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookexportToExcelSheet(maplist));
	}

	@Test(priority=47)
	public void AddressBookSortingByDescending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.AddressBookSortByDescending();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\AddressBook (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookexportToExcelSheet(maplist));
	}
	@Test(priority=48)
	public void EditRecipientWithoutModifyReasonRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.editRecipientWithoutModifyReason(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
	}

	@Test(priority=49)
	public void VerifyRecipientCancelBtnAtEditRecord() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);		
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.clickOnEditButtonrecepient(faxAddressBoookDetails);
		faxAddressBookPage.clickOnCancelBtn();
		Assert.assertTrue(faxAddressBookPage.canclBtnverificationForRecepientTab(),"Cancel Btn at Add record assertion failed");
	}

	@Test(priority=50)
	public void EditRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.editRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Updated Successfully");
	}

	@Test(priority=51)//,dependsOnMethods="EditRecipientRecord")
	public void VerifyAuditTrialReportForRecipientUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyRecipientUpdate(faxAddressBoookDetails,"Update"));
	}

	@Test(priority=52)
	public void EditWithoutModifyReasonAddressBookRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.editAddressBookWithoutModifyReason(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
	}

	@Test(priority=53)
	public void VerifyCancelBtnAtEditAddressBookRecord() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditAddressBook").getTestData().get(0);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		faxAddressBookPage.clickOnEditButtonAddress(faxAddressBoookDetails);
		faxAddressBookPage.clickOnCancelBtn();
		Assert.assertTrue(faxAddressBookPage.canclBtnverificationForAddressTab(),"Cancel Btn at Add record assertion failed");
	}

	@Test(priority=54)
	public void EditFaxAddressBookRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.editAddressBookRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Updated Successfully");
	}

	@Test(priority=55)//dependsOnMethods="EditFaxAddressBookRecord")
	public void VerifyAuditTrialReportForFaxAddressBookUpdate() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAddressBookUpdate(faxAddressBoookDetails,"Update"));
	}


	@Test(priority=56)
	public void DeleteWithoutDeleteReasonAddressBookRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.deleteWithoutDeleteReasonAddress(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}

	@Test(priority=57)
	public void VerifyCancelBtnAtDeleteAddressRecord() throws Exception{  	
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		//faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.DeletecancelButtonAddressTab(faxAddressBoookDetails);
		faxAddressBookPage.clickOnDeleteCancelBtn();
		Assert.assertTrue(faxAddressBookPage.canclBtnverificationForAddressTab(),"Cancel Btn at Add record assertion failed");
	}

	@Test(priority=58)
	public void DeleteAddressBookRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.deleteRecordAddress(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Deleted Successfully");
	}

	@Test(priority=59)
	public void VerifyAuditTrialReportForAddressBookDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyAddressBookdelete(faxAddressBoookDetails,"Delete"));
	}

	@Test(priority=60)
	public void DeleteRecepientWithoutDeleteReasonRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(3);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.deleteRecipientWithoutDeleteReasonRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
	}
	@Test(priority=61)
	public void VerifyCancelBtnAtDeleteRecepientRecord() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(3);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.DeletecancelButtonRecepientTab(faxAddressBoookDetails);
		faxAddressBookPage.clickOnDeleteCancelBtn();
		Assert.assertTrue(faxAddressBookPage.canclBtnverificationForRecepientTab(),"Cancel Btn at Add record assertion failed");
	}
	@Test(priority=62)
	public void DeleteRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(3);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.deleteRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Deleted Successfully");
	}

	@Test(priority=63)
	public void VerifyAuditTrialReportForDelete() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(3);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
		homePage.navigateToOCMReportsPage();
		OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
		String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
		Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
		ReportDetails reportDetails= new ReportDetails(map1);
		ocmReportsPage.showReport(reportDetails);
		Assert.assertTrue(ocmReportsPage.verifyRecipientdelete(faxAddressBoookDetails,"Delete"));
	}


	@Test(priority=64)
	public void VerifyDragAndDropofAddressBook() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Thread.sleep(2000);
		faxAddressBookPage.dragAddressBookColumntoGroup("Name");
		Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("Name",0),"drag and drop assertion failed");
	}

	@Test(priority=65)
	public void VerifyDragAndDropofAlreadyGroupedHeader() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Thread.sleep(2000);
		faxAddressBookPage.dragAddressBookColumntoGroup("Name");
		faxAddressBookPage.dragAddressBookColumntoGroup("Name");
		Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("Name",0),"drag and drop assertion failed");
	}

	@Test(priority=66)
	public void VerifyAddressBookDropdownForAllTheColumns() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
		//faxAddressBookPage.NavigateToRecipientTab();
		//Assert.assertTrue(faxAddressBookPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	}


	@Test(priority=67)
	public void VerifyAddressBookColumnsHeaderEnable() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifycolumnsHeaderEnabledAdress(),"columns enabled assertion failed");
	}

	@Test(priority=68)
	public void VerifyAddressBookColumnsHeaderDisable() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertFalse(faxAddressBookPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	}

	@Test(priority=69)
	public void SearchRecipientClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyinvalidRecipientsearchwithwrongdata(faxAddressBoookDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("FaxaddressBookTest","Invalid Search with wrong data");
		Assert.assertTrue(faxAddressBookPage.verifyRecipientclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=70)
	public void AddressBookSearchClearAllClose() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertFalse(faxAddressBookPage.AddressBookclearAll(faxAddressBoookDetails),"ClearAll Assertion Failed");
		screenshot.captureScreen("FaxAddressBookTest", "clearall");
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookSearchclose());
	}

	@Test(priority=71)
	public void searchAddressBookwithoutSearchTextbox() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.searchAddressBookwithoutextsearch();
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
	}

	@Test(priority=72)
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyinvalidAddresssearchwithwrongdata(faxAddressBoookDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("FaxaddressBookTest","Invalid Search with wrong data");
		Assert.assertTrue(faxAddressBookPage.verifyAddressclearsearch(), "Clear All Assertion Failed");
	}

	@Test(priority=73)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForPreviousAndNextPage(0),"arrow move for previous and next page assertion failed");
	}

	@Test(priority=74)
	public void VerifyArrowMoveForFirstAndLastPage() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForFirstAndLastPage(0),"arrow move for first and last page assertion failed");
	}

	@Test(priority=75)
	public void VerifyTotalNumberOfItemsPerPageDetails() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyTotalNumberOfItemsPerPageDetails(0),"item per page assertion failed");
	}

	@Test(priority=76)
	public void VerifyNumberOfItemsPerPageSelection() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyNumberOfItemsPerPage(0),"item per page assertion failed");
	}
	@Test(priority=77)
	public void DeleteRecipientRecordAssignedtoAddressBook() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(2);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.deleteRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Deletion Failed, Recipient is already assigned to Address Book.");;
	}
ss
	@Test(priority=78)
	public void ExporttoExcelWithoutDataRecipient() throws Exception{		 
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "CreateRecipient").getTestData().get(0);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		Assert.assertTrue(faxAddressBookPage.ExporttoExcelWithoutDataRecipient(faxAddressBookDetails));
	}
	@Test(priority=79)
	public void ExporttoExcelWithoutDataAddressbook() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "AddressCreate").getTestData().get(0);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		Assert.assertTrue(faxAddressBookPage.ExporttoExcelWithoutDataAddressbook(faxAddressBookDetails));
	}
	@Test(priority=80)
	public void VerifyRecipientdatabase() throws Exception{
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "RecipientQuery").getTestData().get(0);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyRecipientDatabase(faxAddressBookDetails.getQuery()));

	}*/
	@Test(priority=81)
	public void verifyAddressBookdatabase() throws Exception{
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath, "AddressBookQuery").getTestData().get(0);
		FaxAddressBookDetails faxAddressBookDetails = new FaxAddressBookDetails(map);
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookDatabase(faxAddressBookDetails.getQuery()));

	}

	@AfterMethod
	public void afterEachMethod(Method method) {
		Screenshot screenshot=new Screenshot(driver);
		screenshot.captureScreen("FaxAddressBookUOBRun2",method.getName());
		driver.navigate().refresh();    }


}
