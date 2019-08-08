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
import com.tetherfi.pages.AdhocOptionEnhancementPage;
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
			faxAddressBookPage.NavigateToRecipientTab();
			Assert.assertTrue(faxAddressBookPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
	    }
	    
	@Test(priority=3)
	    public void VerifyColumnsHeaderEnable() {
			FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
			faxAddressBookPage.NavigateToRecipientTab();
			Assert.assertTrue(faxAddressBookPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
	    }
	    
	@Test(priority=4)
	    public void VerifyColumnsHeaderDisable() {
			FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
			faxAddressBookPage.NavigateToRecipientTab();
			Assert.assertFalse(faxAddressBookPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
	    }
	
	@Test(priority=5)
    public void VerifyCancelButtonAtAddAdhocOptionEnhancementRecord() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.clickonAddNewRecipientRecord();
		faxAddressBookPage.clickOnCancelBtn();
        Assert.assertFalse(faxAddressBookPage.verifyEditFormContainer(),"Cancel Btn at Add record assertion failed");
	}
	
	@Test(priority=6)
	public void AddNewFaxAddressBookRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(), "Record Created Successfully");
	}
	
	@Test(priority=7)//,dependsOnMethods = "AddNewFaxAddressBookRecipientRecord")
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
	
	@Test(priority=8)
	public void AddDuplicateRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Duplicate FaxNumber");
	}
	
	@Test(priority=9)
	public void AddNewEmptyFaxAddressBookRecipientRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewEmptyRecipientRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please provide a valid FirstName");
	}
	
	@Test(priority=10)
	public void AddNewFaxAddressBookRecipientWithoutFirstNameRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientWithoutFirstNameRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please provide a valid FirstName");
	}
	
	@Test(priority=11)
	public void AddNewFaxAddressBookRecipientWithoutLastNameRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientWithoutLastNameRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please provide a valid LastName");
	}
	
	@Test(priority=12)
	public void AddNewFaxAddressBookRecipientWithoutNumberRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"CreateRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.addNewRecipientWithoutNumberRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(),"Please provide a valid FaxNumber");
	}
	
	@Test(priority=13)
	public void AddNewFaxAddressBookRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getSuccessMessage(), "Record Created Successfully");
	}
	
	@Test(priority=14)//,dependsOnMethods = "AddNewFaxAddressBookRecipientRecord")
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
	
	@Test(priority=15)
	public void AddNewDuplicateRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Duplicate Name");
	}
	
	@Test(priority=16)
	public void AddNewEmptyRecord() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewEmptyRecord(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please provide a valid FaxLine");
	}
	
	@Test(priority=17)
	public void AddNewRecordWithoutFaxLine() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecordWithoutFaxline(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please provide a valid FaxLine");
	}
	
	@Test(priority=18)
	public void AddNewRecordWithoutName() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecordWithoutName(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please provide a valid Name");
	}
	
	@Test(priority=19)
	public void AddNewRecordWithoutRecipients() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.addNewAddressBookRecordWithoutRecipients(faxAddressBoookDetails);
		Assert.assertEquals(faxAddressBookPage.getMessage(), "Please provide a valid Recipient");
	}
	
	@Test(priority=20)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=21)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Recipient.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		Assert.assertTrue(faxAddressBookPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=22)
    public void SortingByAscending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Recipient (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAddressBookPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=23)
    public void SortingByDescending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Recipient (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAddressBookPage.verifyexportToExcelSheet(maplist));
    }
    
	@Test(priority=24)
    public void AddressBookExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookExportToExcel(filePath));
    }
    
    @Test(priority=25)
    public void AddressBookExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\AddressBook.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyAddressBookexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=26)
    public void AddressBookSortingByAscending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.AddressBookSortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\AddressBook (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAddressBookPage.verifyAddressBookexportToExcelSheet(maplist));
    }
    
    @Test(priority=27)
    public void AddressBookSortingByDescending() throws IOException {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.AddressBookSortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\AddressBook (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(faxAddressBookPage.verifyAddressBookexportToExcelSheet(maplist));
    }
    
    @Test(priority=17)
    public void EditRecipientRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.editRecipientRecord(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Updated Successfully");
    }
    
    @Test(priority=18,dependsOnMethods="EditRecipientRecord")
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
       
    @Test(priority=19)
    public void EditWithoutModifyReasonRecipientRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.editRecipientWithoutModifyReason(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=20)
    public void VerifyCancelBtnAtEditDepartmentRecord() throws Exception{
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.clickOnEditButton();
		faxAddressBookPage.clickOnCancelBtn();
        Assert.assertFalse(faxAddressBookPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=21)
    public void EditFaxAddressBookRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.editAddressBookRecord(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Updated Successfully");
    }
    
    @Test(priority=22,dependsOnMethods="EditFaxAddressBookRecord")
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
       
    @Test(priority=23)
    public void EditWithoutModifyReasonAddressBookRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"EditAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.editAddressBookWithoutModifyReason(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=24)
    public void VerifyCancelBtnAtEditAddressBookRecord() throws Exception{
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.clickOnEditButton();
		faxAddressBookPage.clickOnCancelBtn();
        Assert.assertFalse(faxAddressBookPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=25)
    public void DeleteWithoutDeleteReasonAddressBookRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.deleteWithoutDeleteReason(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    
    @Test(priority=26)
    public void VerifyCancelBtnAtDeleteRecipientRecord() throws Exception{
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.clickOnDeleteButton();
		faxAddressBookPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(faxAddressBookPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=27)
    public void DeleteAddressBookRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteAddressBook").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.deleteRecord(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Deleted Successfully");
    }
    
    @Test(priority=28)
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
    
    @Test(priority=29)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.deleteRecipientWithoutDeleteReasonRecord(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=30)
    public void VerifyCancelBtnAtDeleteAddressRecord() throws Exception{
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.clickOnDeleteButton();
		faxAddressBookPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(faxAddressBookPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    @Test(priority=31)
    public void DeleteRecipientRecord() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.deleteRecipientRecord(faxAddressBoookDetails);
        Assert.assertEquals(faxAddressBookPage.getSuccessMessage(),"Record Deleted Successfully");
    }
    
    @Test(priority=32)
    public void VerifyAuditTrialReportForDelete() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(0);
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
    
    /*@Test(priority=25)
    public void VerifyRecipientArrowMoveForPreviousAndNextPage() {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
        Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForPreviousAndNextPage(1),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=26)
    public void VerifyRecipientArrowMoveForFirstAndLastPage() {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
        Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForFirstAndLastPage(1),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=27)
    public void VerifyRecipientTotalNumberOfItemsPerPageDetails() {
        FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
        Assert.assertTrue(faxAddressBookPage.verifyTotalNumberOfItemsPerPageDetails(1),"item per page assertion failed");
    }
    
    @Test(priority=28)
    public void VerifyRecipientNumberOfItemsPerPageSelection() {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
        Assert.assertTrue(faxAddressBookPage.verifyNumberOfItemsPerPage(1),"item per page assertion failed");
    }
    
    /*@Test(priority=29)
    public void searchRecipientPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"DeleteRecipient").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
        FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
        Assert.assertFalse(faxAddressBookPage.RecipientclearAll(faxAddressBoookDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("FaxAddressBookTest", "clearall");
        Assert.assertTrue(faxAddressBookPage.verifyRecipientSearchclose());
    }
    
    @Test(priority=30)
    public void searchRecipientwithoutSearchTextbox() throws IOException {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
		faxAddressBookPage.searchwithoutextsearch();
        Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }*/
	
	/*@Test(priority=31)
    public void VerifyRecipientDragAndDrop() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
        Thread.sleep(2000);
        faxAddressBookPage.dragRecipientColumntoGroup("First Name");
        Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("First Name",1),"drag and drop assertion failed");
    }
	
	@Test(priority=32)
    public void VerifyRecipientDragAndDropofAlreadyGroupedHeader() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.NavigateToRecipientTab();
        Thread.sleep(2000);
        faxAddressBookPage.dragRecipientColumntoGroup("First Name");
        faxAddressBookPage.dragRecipientColumntoGroup("First Name");
        Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("First Name",1),"drag and drop assertion failed");
    }
	
	@Test(priority=33)
    public void VerifyDragAndDrop() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
        Thread.sleep(2000);
        faxAddressBookPage.dragAddressBookColumntoGroup("Name");
        Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("Name",0),"drag and drop assertion failed");
    }
	
	@Test(priority=34)
    public void VerifyDragAndDropofAlreadyGroupedHeader() throws Exception {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
        Thread.sleep(2000);
        faxAddressBookPage.dragAddressBookColumntoGroup("Name");
        faxAddressBookPage.dragAddressBookColumntoGroup("Name");
        Assert.assertTrue(faxAddressBookPage.verifyDragColumntoGroup("Name",0),"drag and drop assertion failed");
    }
    
	@Test(priority=35)
    public void VerifyAddressBookDropdownForAllTheColumns() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
	@Test(priority=36)
    public void VerifyAddressBookColumnsHeaderEnable() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
	@Test(priority=37)
    public void VerifyAddressBookColumnsHeaderDisable() {
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertFalse(faxAddressBookPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
	
	@Test(priority=38)
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
	
	@Test(priority=39)
    public void searchRecipientPage() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
        FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
        Assert.assertFalse(faxAddressBookPage.AddressBookclearAll(faxAddressBoookDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("FaxAddressBookTest", "clearall");
        Assert.assertTrue(faxAddressBookPage.verifyAddressBookSearchclose());
    }
    
    @Test(priority=40)
    public void searchAddressBookwithoutSearchTextbox() throws IOException {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		faxAddressBookPage.searchAddressBookwithoutextsearch();
        Assert.assertEquals(faxAddressBookPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=41)
	public void SearchClearSearch() throws Exception{
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\FaxAddressBookData.xlsx";
		Map<String, String> map = new ExcelReader(filePath,"AddressCreate").getTestData().get(0);
		FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
		FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
		Assert.assertTrue(faxAddressBookPage.verifyinvalidAddresssearchwithwrongdata(faxAddressBoookDetails),"invalidsearchwithwrongdata");
		screenshot.captureScreen("FaxaddressBookTest","Invalid Search with wrong data");
		Assert.assertTrue(faxAddressBookPage.verifyAddressclearsearch(), "Clear All Assertion Failed");
	 }
    
    @Test(priority=42)
    public void VerifyArrowMoveForPreviousAndNextPage() {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
        Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForPreviousAndNextPage(0),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=43)
    public void VerifyArrowMoveForFirstAndLastPage() {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
        Assert.assertTrue(faxAddressBookPage.verifyArrowMoveForFirstAndLastPage(0),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=44)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
        Assert.assertTrue(faxAddressBookPage.verifyTotalNumberOfItemsPerPageDetails(0),"item per page assertion failed");
    }
    
    @Test(priority=45)
    public void VerifyNumberOfItemsPerPageSelection() {
    	FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
        Assert.assertTrue(faxAddressBookPage.verifyNumberOfItemsPerPage(0),"item per page assertion failed");
    }
	*/

    
	
	
	
	
	
	
	
	 @AfterMethod
	    public void afterEachMethod(Method method) {
	    	Screenshot screenshot=new Screenshot(driver);
	        screenshot.captureScreen("FaxAddressBookTest",method.getName());
	        driver.navigate().refresh();    }
	
	


}
