package com.tetherfi.test.fax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tetherfi.model.fax.FaxTemplateDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.FaxPage;
import com.tetherfi.pages.FaxTemplateWMCPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class FaxTemplateWMCTest extends BaseTest{
	
	Screenshot screenshot=new Screenshot(driver);
	
	@BeforeMethod
	public void NavigateToFaxTemplatePage() {
		 HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	     homePage.navigateToOCMPage();
	     OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
	     Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
	     ocmHomePage.navigateToTab("FAX");
	     FaxPage faxPage = PageFactory.createPageInstance(driver, FaxPage.class);
	     Assert.assertTrue(faxPage.isFaxPageDisplayed(), "fax page assertion failed");
	     faxPage.navigateToFaxTemplatePage();
	     FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
	     Assert.assertTrue(faxTemplateWMCPage.isFaxTemplatePageDisplayed(), "FAX page assertion failed");
	}
	
	@Test(priority=1)
	public void FaxTemplateWMCPage() {
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver,FaxTemplateWMCPage.class);
    	Assert.assertTrue(faxTemplateWMCPage.verifyLogo(), "FaxTemplateWMCTest Logo Assertion Failed");
    	Assert.assertTrue(faxTemplateWMCPage.maximizeWindow(), "fullScreen Assertion Failed");
    	screenshot.captureScreen("FaxTemplateWMCTest", "maximize window");
    	Assert.assertTrue(faxTemplateWMCPage.minimizeWindow(), "Restore Assertion Failed");
    	screenshot.captureScreen("FaxTemplateWMCTest", "minimize window");
	}
	
	@Test(priority=2)
    public void verifyDropDownForAllTheColumns() {
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver,FaxTemplateWMCPage.class);
    	Assert.assertTrue(faxTemplateWMCPage.verifyDropDownOfAllHeaders(),"Columns DropDown Assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
    	FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver,FaxTemplateWMCPage.class);
    	Assert.assertTrue(faxTemplateWMCPage.VerifyColumnsHeadersEnable(), "Headers assertion Failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
    	FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver,FaxTemplateWMCPage.class);
    	Assert.assertFalse(faxTemplateWMCPage.verifycolumnsHeaderDisabled(), "Headers Assertion Failed");
    }
	
    @Test(priority=5)
    public void VerifyAddCancelButton() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
        Assert.assertTrue(faxTemplateWMCPage.addCancelButton(faxTemplateDetails), "Add cancel button assertion failed");
    }
    
    @Test(priority=6)
	public void VerifyAddRecordWithOutTemplateName() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.addWithoutTemplateName(faxTemplateDetails);
		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please Provide Template Name", "Template Name Assertion Failed");
	}
	
    @Test(priority=7)
   	public void VerifyAddRecordWithOutTemplateType() throws Exception {
   		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addWithoutTemplateType(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please Provide Template Type", "Template Type Assertion Failed");
   	}
    
    @Test(priority=8)
   	public void VerifyAddRecordWithOutContent() throws Exception {
   		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addWithoutContent(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please fill the content", "Content Assertion Failed");
   	}
    
    @Test(priority=9)
    public void AddNewAutoAckTemplateTypeRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addNewAutoAcKTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Created Successfully","Record Creation Assertion failed");
    }
    
    @Test(priority=10)
    public void AuditTrailReportForCreateAutoAck() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create1").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "Create"), "Audit Trail Assertion Failed");
    }
    
    @Test(priority=11)
    public void AddDuplicateAutoAckTemplateTypeRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addNewAutoAcKTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Duplicate Template Name","Duplicate Record Creation Assertion failed");
    }
	
	@Test(priority=12)
	public void VerifyUpdateAutoAckTemplateTypeRecordCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		Assert.assertTrue(faxTemplateWMCPage.EditCancel(faxTemplateDetails), "Edit cancel Assertion failed");
	}
	
	@Test(priority=13)
	public void VerifyUpdateAutoAckTemplateTypeRecordWithoutModifyReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.EditAutoAckTemplateTypeRecordWithOutModifyReason(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please enter the modify reason","Record Creation Assertion failed");
	}
	
	@Test(priority=14)
	public void VerifyUpdateAutoAckTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.EditAutoAckTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Updated Successfully","Record Creation Assertion failed");
	}
	
	@Test(priority=15)
	public void AuditTrailReportForUpdateAutoAck() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Edit1").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateUpdate(faxTemplateDetails, "Update"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=16)
	public void VerifyDeleteAutoAckTemplateTypeRecordCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		Assert.assertTrue(faxTemplateWMCPage.DeleteCancel(faxTemplateDetails), "Delete cancel Assertion failed");
	}
    
	@Test(priority=17)
	public void VerifyDeleteAutoAckTemplateTypeRecordWithoutDeleteReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.DeleteFaxTemplateRecordWithoutDeleteReason(faxTemplateDetails);
		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please enter the delete reason", "Delete Without Reason Assertion failed");
	}
	
	@Test(priority=18)
	public void VerifyDeleteAutoAckTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.DeleteAutoAckTemplateTypeRecord(faxTemplateDetails);
		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Deleted Successfully", "Delete Assertion failed");
	}
	
	@Test(priority=19)
	public void AuditTrailReportForDeleteAutoAck() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete1").getTestData().get(0);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "Delete"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=20)
    public void AddNewCoverPageTemplateTypeRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addNewCoverPageTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Created Successfully","Record Creation Assertion failed");
    }
	
	@Test(priority=21)
	public void AuditTrailReportForCreateCoverPageTemplateType() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create1").getTestData().get(1);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "Create"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=22)
    public void AddDuplicateCoverPageTemplateTypeRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addNewCoverPageTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Duplicate Template Name","Duplicate Record Creation Assertion failed");
    }
	
	@Test(priority=23)
	public void VerifyUpdateCoverPageTemplateTypeRecordCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		Assert.assertTrue(faxTemplateWMCPage.EditCancel(faxTemplateDetails), "Edit cancel Assertion failed");
	}
	
	@Test(priority=24)
	public void VerifyUpdateCoverPageTemplateTypeRecordWithoutModifyReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.EditCoverPageTemplateTypeRecordWithOutModifyReason(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please enter the modify reason","Record Creation Assertion failed");
	}
	
	@Test(priority=25)
	public void VerifyUpdateCoverPageTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.EditCoverPageTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Updated Successfully","Record Creation Assertion failed");
	}
	
	@Test(priority=26)
	public void AuditTrailReportForUpdateCoverPageTemplateType() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Edit1").getTestData().get(1);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateUpdate(faxTemplateDetails, "Update"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=27)
	public void VerifyDeleteCoverPageTemplateTypeRecordCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		Assert.assertTrue(faxTemplateWMCPage.DeleteCancel(faxTemplateDetails), "Delete cancel Assertion failed");
	}
	
	@Test(priority=28)
	public void VerifyDeleteCoverPageTemplateTypeRecordWithoutDeleteReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(1);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.DeleteFaxTemplateRecordWithoutDeleteReason(faxTemplateDetails);
		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please enter the delete reason", "Delete Without Reason Assertion failed");
	}
	
	@Test(priority=29)
	public void VerifyDeleteCoverPageTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(1);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.DeleteCoverPageTemplateTypeRecord(faxTemplateDetails);
		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Deleted Successfully", "Delete Assertion failed");
	}
	
	@Test(priority=30)
	public void AuditTrailReportForDeleteCoverPageTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete1").getTestData().get(1);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "Delete"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=31)
    public void AddNewCustomTemplateRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(2);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addNewCustomTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Created Successfully","Record Creation Assertion failed");
    }
	
	@Test(priority=32)
	public void AuditTrailReportForCustomTemplateType() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Create1").getTestData().get(2);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateCreate(faxTemplateDetails, "Create"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=33)
    public void AddDuplicateCustomTemplateTypeRecord() throws Exception {
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Create1").getTestData().get(2);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.addNewCustomTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Duplicate Template Name","Duplicate Record Creation Assertion failed");
    }
	
	@Test(priority=34)
	public void VerifyUpdateCustomTemplateTypeRecordCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(2);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		Assert.assertTrue(faxTemplateWMCPage.EditCancel(faxTemplateDetails), "Edit cancel Assertion failed");
	}
	
	@Test(priority=35)
	public void VerifyUpdateCustomTemplateTypeRecordWithoutModifyReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(2);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.EditCoverPageTemplateTypeRecordWithOutModifyReason(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please enter the modify reason","Record Creation Assertion failed");
	}
	
	@Test(priority=36)
	public void VerifyUpdateCustomTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Edit1").getTestData().get(2);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.EditCustomTemplateTypeRecord(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Updated Successfully","Record Creation Assertion failed");
	}
	
	@Test(priority=37)
	public void AuditTrailReportForUpdateCustomTemplateType() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Edit1").getTestData().get(2);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateUpdate(faxTemplateDetails, "Update"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=38)
	public void VerifyDeleteCustomTemplateTypeRecordCancelButton() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(2);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		Assert.assertTrue(faxTemplateWMCPage.DeleteCancel(faxTemplateDetails), "Delete cancel Assertion failed");
	}
	
	@Test(priority=39)
	public void VerifyDeleteCustomTemplateTypeRecordWithoutDeleteReason() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(2);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.DeleteFaxTemplateRecordWithoutDeleteReason(faxTemplateDetails);
		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please enter the delete reason", "Delete Without Reason Assertion failed");
	}
	
	@Test(priority=40)
	public void VerifyDeleteCustomTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String> map=new ExcelReader(filePath,"Delete1").getTestData().get(2);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.DeleteCustomTemplateTypeRecord(faxTemplateDetails);
		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Record Deleted Successfully", "Delete Assertion failed");
	}
	
	@Test(priority=41)
	public void AuditTrailReportForDeleteCustomTemplateTypeRecord() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String,String>map=new ExcelReader(filePath,"Delete1").getTestData().get(2);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
	    homePage.navigateToOCMReportsPage();
	    OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
	    String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
	    Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
	    ReportDetails reportDetails= new ReportDetails(map1);
	    ocmReportsPage.showReport(reportDetails);
	    Assert.assertTrue(ocmReportsPage.verifyFaxTemplateDelete(faxTemplateDetails, "Delete"), "Audit Trail Assertion Failed");
	}
	
	@Test(priority=42)
    public void ExportToExcel() throws Exception{
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
        Assert.assertTrue(faxTemplateWMCPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=43)
    public void ExportToExcelData() throws Exception{	
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);;
        Assert.assertTrue(faxTemplateWMCPage.verifyexportToExcelSheet(maplist));	
    }
    
    @Test(priority=44)
    public void VerifyExportToExcelWithoutData() throws Exception {
    	FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
    	String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Search").getTestData().get(1);
    	FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
        Assert.assertTrue(faxTemplateWMCPage.ExporttoExcelWithoutData(faxTemplateDetails));
     }
	
	@Test(priority=45)
	public void SortByAscending() throws IOException{
		FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.SortByAscending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template (1).xlsx";
		List<Map<String, String>> maplist=new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxTemplateWMCPage.verifyexportToExcelSheet(maplist));
	}
	
	@Test(priority=46)
	public void SortByDescending() throws IOException{
		FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.SortByDescending();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Fax Template (2).xlsx";
		List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
		Assert.assertTrue(faxTemplateWMCPage.verifyexportToExcelSheet(maplist));
	}
	
	 @Test(priority=47)
	 public void VerifyDragAndDrop() {
		FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.dragColumntoGroup("Template Name");
	    Assert.assertTrue(faxTemplateWMCPage.verifyDragColumntoGroup("Template Name"),"drag and drop assertion failed");
	}
	    
	@Test(priority=48)
	public void VerifyDragAndDropofAlreadyGroupedHeader() {
		FaxTemplateWMCPage faxTemplateWMCPage = PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		faxTemplateWMCPage.dragColumntoGroup("FileName");
		faxTemplateWMCPage.dragColumntoGroup("FileName");
	    Assert.assertTrue(faxTemplateWMCPage.verifyDragColumntoGroup("FileName"),"drag and drop assertion failed");
	}
	
	@Test(priority=49)
    public void searchPage() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
        Assert.assertFalse(faxTemplateWMCPage.clearAll(faxTemplateDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("FaxTemplateWMCTest", "clearall");
        Assert.assertTrue(faxTemplateWMCPage.verifyclose());
        screenshot.captureScreen("FaxTemplateWMCTest", "SearchClose");
    }
	
	@Test(priority=50)
    public void searchwithoutSearchTextbox() throws IOException {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
   		faxTemplateWMCPage.searchwithoutextsearch(faxTemplateDetails);
   		Assert.assertEquals(faxTemplateWMCPage.VerifyMessage(), "Please enter the text to search or remove the filter","Record Creation Assertion failed");
    }
	
	@Test(priority=51)
	public void SearchClearSearch() throws Exception{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
	    Assert.assertTrue(faxTemplateWMCPage.verifyinvalidsearchwithwrongdata(faxTemplateDetails ),"invalidsearchwithwrongdata");
        screenshot.captureScreen("FaxTemplateWMCTest","Invalid Search with wrong data");
  	    Assert.assertTrue(faxTemplateWMCPage.verifyclearsearch(), "Clear All Assertion Failed");
	 }
	
	@Test(priority=52)
	public void VerifyArrowMoveForPreviousAndNextPage() {
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		Assert.assertTrue(faxTemplateWMCPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
	}
		    
	@Test(priority=53)
	public void VerifyArrowMoveForFirstAndLastPage() {
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		Assert.assertTrue(faxTemplateWMCPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
	}
		    
    @Test(priority=54)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
    	FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
	    Assert.assertTrue(faxTemplateWMCPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
	}
		    
	@Test(priority=55)
	public void VerifyNumberOfItemsPerPageSelection() {
	   FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
	   Assert.assertTrue(faxTemplateWMCPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
	}
	
	@Test(priority=56)
	public void VerifySearchIsNotEqualTo() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(0);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		Assert.assertTrue(faxTemplateWMCPage.verifySearchIsnotEqualTo(faxTemplateDetails.getTemplateName()));
	}
	
	@Test(priority=57)
	public void VerifySearchContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		Assert.assertTrue(faxTemplateWMCPage.verifySearchContains(faxTemplateDetails.getTemplateName()));
	}
	
	@Test(priority=58)
	public void VerifySearchDoesNotContains() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(1);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		Assert.assertTrue(faxTemplateWMCPage.verifySearchDoesNotContains(faxTemplateDetails.getTemplateName()));
	}
    
	@Test(priority=59)
	public void VerifySearchStartsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(2);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		Assert.assertTrue(faxTemplateWMCPage.verifySearchStartsWith(faxTemplateDetails.getTemplateName()));
	}
	
	@Test(priority=60)
	public void VerifySearchEndsWith() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
   		Map<String, String> map=new ExcelReader(filePath,"Search").getTestData().get(3);
   		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
   		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		Assert.assertTrue(faxTemplateWMCPage.verifySearchEndsWith(faxTemplateDetails.getTemplateName()));
	
	}
	
	@Test(priority=61)
	public void dataBase() throws Exception {
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\FaxTemplateData.xlsx";
		Map<String, String>map=new ExcelReader(filePath,"Queries").getTestData().get(0);
		FaxTemplateWMCPage faxTemplateWMCPage=PageFactory.createPageInstance(driver, FaxTemplateWMCPage.class);
		FaxTemplateDetails faxTemplateDetails=new FaxTemplateDetails(map);
		Assert.assertTrue(faxTemplateWMCPage.verifyDatabase(faxTemplateDetails.getQuery()), "dataBase assertion Failed");
	}
	    
	 @AfterMethod
	    public void afterEachMethod(Method method){
	    	Screenshot screenshot=new Screenshot(driver);
	    	screenshot.captureScreen("FaxTemplateWMCTest", method.getName());
	    	driver.navigate().refresh();
	}
	

}
