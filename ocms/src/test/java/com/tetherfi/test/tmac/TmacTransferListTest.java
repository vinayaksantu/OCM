package com.tetherfi.test.tmac;

import com.tetherfi.model.tmac.TmacTransferListDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class TmacTransferListTest extends BaseTest {
	
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigatetoTmacTransferListPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage=PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"tmac page assertion failed");
        tmacPage.navigateToTmacTranferListPage();
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        Assert.assertTrue(tmacTransferListPage.isTmacTransferListPageDisplayed(),"Agent Team  management assertion failed");
        screenshot.captureScreen(driver, "TMACTransList Page","TmacTransListTest");
    }
    
    //@Test (priority=0)
    public void VerifyTmacTransferListModuleDisplay() {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        Assert.assertEquals(tmacTransferListPage.getHeaderText(), "TMAC Transfer List", "Tmac Transfer List Module text assertion failed");
        Assert.assertTrue(tmacTransferListPage.verifySearchLink(), "search link assertion failed");
        Assert.assertTrue(tmacTransferListPage.verifyClearSearchLink(), "clear search link assertion failed");
        Assert.assertTrue(tmacTransferListPage.verifyFullScreenLink(), "full screen link assertion failed");
        Assert.assertTrue(tmacTransferListPage.verifyTmacTransferListTabsDisplayed(), "Tmac Transfer List tab assertion failed");
    }
    
    //@Test (priority=1)
    public void VerifyTmacConsultTransferTableHeaders() {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	Assert.assertTrue(tmacTransferListPage.verifyTmacConsultTransferDataTableHeaders(), "Tmac Consult Transfer table headers assertion failed");
    }
    
    //@Test (priority=2)
    public void VerifyAddNewConsultTransferButton() {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	Assert.assertTrue(tmacTransferListPage.verifyAddNewConsultTransferButton(), "Add New Tmac Consult Transfer Button assertion failed");
    	Assert.assertTrue(tmacTransferListPage.verifyExportToExcelButton(), "export to excel button assertion failed");
    }
    
    //@Test (priority=3)
    public void VerifyAddConsultTransferPopUpContentDisplayed() throws InterruptedException {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.selectNewConsulTransBtn();
    	Assert.assertTrue(tmacTransferListPage.verifyAddNewConsulTransPopupContents(),"Add new pop up content assertion failed");
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    }
    
    @Test (priority=4)
    public void VerifyErrMsgNameBlank() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.selectNewConsulTransBtn();
    	tmacTransferListPage.EnterNameNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.EnterAgtExtNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelTypeNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    }
    
    //@Test (priority=5)
    public void VerifyErrMsgAgtExtBlank() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(1);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.selectNewConsulTransBtn();
    	tmacTransferListPage.EnterNameNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.EnterAgtExtNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelTypeNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    }
    
    //@Test (priority=6)
    public void VerifyErrMsgTypeNotSelected() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(2);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.selectNewConsulTransBtn();
    	tmacTransferListPage.EnterNameNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.EnterAgtExtNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    }
    
    //@Test (priority=7)
    public void VerifyCancelonNewConsulTransfer() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(3);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	Assert.assertTrue(tmacTransferListPage.addNewCancel(tmacTransferListDetails), "Add New cancel assertion failed");
    }
    
    //@Test (priority=8)
    public void AddNewTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(3);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.addNewTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    //@Test (priority=9, dependsOnMethods = ("AddNewTmacConsultTransferRecord"))
    public void VerifyErrMsgDuplicateName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(4);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.selectNewConsulTransBtn();
    	tmacTransferListPage.EnterNameNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.EnterAgtExtNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelTypeNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=10, dependsOnMethods = ("AddNewTmacConsultTransferRecord"))
    public void VerifyErrMsgDuplicateValue() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulCreate").getTestData().get(5);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.selectNewConsulTransBtn();
    	tmacTransferListPage.EnterNameNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.EnterAgtExtNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelTypeNewConsulTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=11)
    public void AddNewTmacConsultTransferRecord2() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "BlindCreate").getTestData().get(6);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.addNewTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    //@Test (priority=12, dependsOnMethods = ("AddNewTmacConsultTransferRecord2"))
    public void VerifyDupNameErrMsgOnEditConsulRecd() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulEdit").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
        Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=13, dependsOnMethods = ("AddNewTmacConsultTransferRecord2"))
    public void VerifyDupValueErrMsgOnEditConsulRecd() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulEdit").getTestData().get(1);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
        Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=14, dependsOnMethods = ("AddNewTmacConsultTransferRecord2"))
    public void VerifyNoModReasonErrMsgOnEditConsulRecd() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "ConsulEdit").getTestData().get(2);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
        Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=15, dependsOnMethods = {"AddNewTmacConsultTransferRecord"})
    public void EditNameOnTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulEdit").getTestData().get(3);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    //@Test (priority=16, dependsOnMethods = {"EditNameOnTmacConsultTransferRecord"})
    public void EditAgtExtOnTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulEdit").getTestData().get(4);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    //@Test (priority=17, dependsOnMethods = {"EditNameOnTmacConsultTransferRecord"})
    public void EditTypeOnTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulEdit").getTestData().get(5);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    //@Test (priority=18, dependsOnMethods = {"EditNameOnTmacConsultTransferRecord"})
    public void EditAllOnTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulEdit").getTestData().get(6);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    //@Test (priority=19, dependsOnMethods = {"AddNewTmacConsultTransferRecord"})
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        Assert.assertTrue(tmacTransferListPage.verifyExportToExcelConsul(filePath));
    }
    
    //@Test (priority=20, dependsOnMethods = {"AddNewTmacConsultTransferRecord"})
    public void ExportToExcelData() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TMACConsultTransfer.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	Assert.assertTrue(tmacTransferListPage.verifyexportToExcelSheet(maplist)); 	
    }
    
    //@Test (priority=21, dependsOnMethods = {"AddNewTmacConsultTransferRecord"})
    public void database() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	Assert.assertTrue(tmacTransferListPage.verifyDatabase(tmacTransferListDetails.getQuery()));
    }
    
    //@Test (priority=22, dependsOnMethods = {"AddNewTmacConsultTransferRecord"})
    public void GroupBy()
    {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	Assert.assertTrue(tmacTransferListPage.groupby());
    	
    }
    
    //@Test (priority=23, dependsOnMethods = {"EditAllOnTmacConsultTransferRecord"})
    public void DeleteNoReasonErrMsgTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulDelete").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.deleteTmacConsultTransferList(tmacTransferListDetails);
        tmacTransferListPage.selectDeleteCloseButtonConsulTrans();
        Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=24, dependsOnMethods = {"EditAllOnTmacConsultTransferRecord"})
    public void DeleteNoReasonNoBtnTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulDelete").getTestData().get(1);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.deleteTmacConsultTransferListNoBtn(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.toastMsgDisplayed(), "Message displayed not expected");
        Assert.assertTrue(tmacTransferListPage.deletePopUpNotDisplayed(), "Delete Pop up displayed not expected");
    }
    
    //@Test (priority=25, dependsOnMethods = {"EditAllOnTmacConsultTransferRecord"})
    public void DeleteTmacConsultTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulDelete").getTestData().get(2);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.deleteTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    //@Test (priority=26, dependsOnMethods = {"EditAllOnTmacConsultTransferRecord"})
    public void DeleteTmacConsultTransferRecord2() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"ConsulDelete").getTestData().get(3);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.deleteTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    //@Test (priority=27)
    public void fullScreenAndRestore() throws InterruptedException
    {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.selectFullScreen();
    	Assert.assertTrue(tmacTransferListPage.ocmHeaderNotDisplayed(), "OCM Header not displayed assertion failed");
    	tmacTransferListPage.selectFullScreen();
    	Assert.assertTrue(tmacTransferListPage.ocmHeaderDisplayed(), "OCM Header displayed assertion failed");
    }
    
    //@Test (priority=28)
    public void VerifyTmacBlindTransferTableHeaders() {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	Assert.assertTrue(tmacTransferListPage.verifyTmacBlindTransferDataTableHeaders(), "Tmac Blind Transfer table headers assertion failed");
    }
    
    //@Test (priority=29)
    public void VerifyAddNewBlindTransferButton() {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	Assert.assertTrue(tmacTransferListPage.verifyAddNewBlindTransferButton(), "Add New Tmac Blind Transfer Button assertion failed");
    	Assert.assertTrue(tmacTransferListPage.verifyExportToExcelButtonBlindTrans(), "export to excel button assertion failed");
    }
    
    //@Test (priority=30)
    public void VerifyAddBlindTransferPopUpContentDisplayed() throws InterruptedException {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	tmacTransferListPage.selectNewBlindTransBtn();
    	Assert.assertTrue(tmacTransferListPage.verifyAddNewBlindTransPopupContents(),"Add new pop up content assertion failed");
    	tmacTransferListPage.selectCancelOnAddNewBlindTransPopUp();
    }
    
    //@Test (priority=31)
    public void VerifyErrMsgNoData() throws IOException, InterruptedException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "BlindCreate").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	tmacTransferListPage.selectNewBlindTransBtn();
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=32)
    public void VerifyErrMsgVDNBlank() throws IOException, InterruptedException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "BlindCreate").getTestData().get(1);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	tmacTransferListPage.selectNewBlindTransBtn();
    	tmacTransferListPage.EnterSkillNameNewBlindTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=33)
    public void VerifyErrMsgSkillNameNotSelected() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "BlindCreate").getTestData().get(2);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	tmacTransferListPage.selectNewBlindTransBtn();
    	tmacTransferListPage.EnterVDNNewBlindTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=34)
    public void VerifyCancelonNewBlindTransfer() throws IOException, InterruptedException {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "BlindCreate").getTestData().get(3);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	Assert.assertTrue(tmacTransferListPage.addNewCancelBlindTrans(tmacTransferListDetails), "Add New cancel assertion failed");
    }
    
    //@Test (priority=35)
    public void AddNewTmacBlindTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindCreate").getTestData().get(3);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.addNewTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    //@Test (priority=36, dependsOnMethods = ("AddNewTmacBlindTransferRecord"))
    public void VerifyErrMsgDupSkillIdNameVdn() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "BlindCreate").getTestData().get(4);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	tmacTransferListPage.selectNewBlindTransBtn();
    	tmacTransferListPage.EnterSkillNameNewBlindTrans(tmacTransferListDetails);
    	tmacTransferListPage.EnterVDNNewBlindTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage2(),tmacTransferListDetails.getExpErrMsg2());
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=37, dependsOnMethods = ("AddNewTmacBlindTransferRecord"))
    public void VerifyErrMsgDupSkillIdName() throws Exception {
    	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "BlindCreate").getTestData().get(5);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	tmacTransferListPage.selectNewBlindTransBtn();
    	tmacTransferListPage.EnterSkillNameNewBlindTrans(tmacTransferListDetails);
    	tmacTransferListPage.EnterVDNNewBlindTrans(tmacTransferListDetails);
    	tmacTransferListPage.SelSaveNewConsulTrans();
    	tmacTransferListPage.selectCancelOnAddNewConsulTransPopUp();
    	Assert.assertEquals(tmacTransferListPage.verifyMessage2(),tmacTransferListDetails.getExpErrMsg2());
    	Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=38, dependsOnMethods = ("AddNewTmacBlindTransferRecord"))
    public void VerifyRecdBlindTransDupVDN() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindCreate").getTestData().get(6);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.addNewTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    //@Test (priority=39, dependsOnMethods = ("AddNewTmacBlindTransferRecord"))
    public void VerifyRecdBlindTransDiffVDN() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindCreate").getTestData().get(7);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.addNewTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    
    //@Test (priority=40, dependsOnMethods = ("AddNewTmacBlindTransferRecord"))
    public void VerifyDupErrMsgEditTmacBlindTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindEdit").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.editTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
        Assert.assertEquals(tmacTransferListPage.verifyMessage2(),tmacTransferListDetails.getExpErrMsg2());
    }
    
    //@Test (priority=41, dependsOnMethods = ("AddNewTmacBlindTransferRecord"))
    public void VerifyErrMsgNoModReasonEditTmacBlindTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindEdit").getTestData().get(1);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.editTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=42, dependsOnMethods = ("AddNewTmacBlindTransferRecord"))
    public void EditTmacBlindTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindEdit").getTestData().get(2);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.editTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    
    //@Test (priority=43, dependsOnMethods = {"AddNewTmacBlindTransferRecord"})
    public void ExportToExcelBlindTrans() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	Assert.assertTrue(tmacTransferListPage.verifyExportToExcelBlind(filePath));
    }
    
    //@Test (priority=44, dependsOnMethods = {"AddNewTmacBlindTransferRecord"})
    public void ExportToExcelDataBlindTrans() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\TMACBlindTransfer.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	Assert.assertTrue(tmacTransferListPage.verifyexportToExcelSheetBlindTrans(maplist));
    }
    
    //@Test (priority=45, dependsOnMethods = {"AddNewTmacBlindTransferRecord"})
    public void databaseBlindTrans() throws Exception {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(1);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	Assert.assertTrue(tmacTransferListPage.verifyDatabaseBlindTrans(tmacTransferListDetails.getQuery()));
    }
    
    //@Test (priority=46, dependsOnMethods = {"AddNewTmacBlindTransferRecord"})
    public void GroupByBlindTrans()
    {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	Assert.assertTrue(tmacTransferListPage.groupbyBlindTrans());
    	
    }
    
    //@Test (priority=47, dependsOnMethods = {"EditTmacBlindTransferRecord"})
    public void DeleteNoReasonErrMsgTmacBlindTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindDelete").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.deleteTmacBlindTransferList(tmacTransferListDetails);
        tmacTransferListPage.selectDeleteCloseButtonConsulTrans();
        Assert.assertEquals(tmacTransferListPage.verifyMessage(),tmacTransferListDetails.getExpErrMsg());
    }
    
    //@Test (priority=48, dependsOnMethods = {"EditTmacBlindTransferRecord"})
    public void DeleteNoReasonNoBtnTmacBlindTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindDelete").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.deleteTmacBlindTransferListNoBtn(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.toastMsgDisplayed(), "Message displayed not expected");
        Assert.assertTrue(tmacTransferListPage.deletePopUpNotDisplayed(), "Delete Pop up displayed not expected");
    }
    
    //@Test (priority=49, dependsOnMethods = {"EditTmacBlindTransferRecord"})
    public void DeleteTmacBlindTransferRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindDelete").getTestData().get(1);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.deleteTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    //@Test (priority=50, dependsOnMethods = {"EditTmacBlindTransferRecord"})
    public void DeleteTmacBlindTransferRecord2() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindDelete").getTestData().get(2);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.deleteTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    
    //@Test (priority=51, dependsOnMethods = {"EditTmacBlindTransferRecord"})
    public void DeleteTmacBlindTransferRecord3() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"BlindDelete").getTestData().get(3);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.deleteTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    

    //@Test (priority=52)
    public void fullScreenAndRestoreBlindTrans() throws InterruptedException
    {
    	TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
    	tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
    	tmacTransferListPage.selectFullScreen();
    	Assert.assertTrue(tmacTransferListPage.ocmHeaderNotDisplayed(), "OCM Header not displayed assertion failed");
    	tmacTransferListPage.selectFullScreen();
    	Assert.assertTrue(tmacTransferListPage.ocmHeaderDisplayed(), "OCM Header displayed assertion failed");
    }
    
    @AfterMethod
    public void afterEachMethod(ITestResult result){
   	 if(ITestResult.FAILURE==result.getStatus()){
   		 try{
   			 screenshot.captureScreen(driver, "failed","TmacTransListTest");
   		 }
   		catch (Exception e){
   		 System.out.println("Exception while taking screenshot "+e.getMessage());
   		 } 
   		 driver.navigate().refresh();
   		 }
    }
}
