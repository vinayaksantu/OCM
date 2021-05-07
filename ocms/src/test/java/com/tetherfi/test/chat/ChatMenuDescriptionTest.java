package com.tetherfi.test.chat;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.model.chat.ChatMenuDescriptionDetails;
import com.tetherfi.model.report.ReportDetails;
import com.tetherfi.pages.ChatMenuDescriptionPage;
import com.tetherfi.pages.ChatPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.OCMReportsPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class ChatMenuDescriptionTest extends BaseTest {
	
	Screenshot screenshot=new Screenshot(driver);
    @BeforeMethod
    public void NavigateToChatMenuDescriptionPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("Chat");
        ChatPage chatPage = PageFactory.createPageInstance(driver,ChatPage.class);
        Assert.assertTrue(chatPage.isChatPageDisplayed(),"chat page assertion failed");
        chatPage.navigateToChatMenuDescriptionPage();
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        Assert.assertTrue(chatMenuDescriptionPage.isChatMenuDescriptionPageDisplayed(),"Chat Menu Description page assertion failed");
    }
    
   @Test(priority=1)
	public void ChatMenuDescriptionPage() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.VerifyLogo(),"Logo assertion failed");
        Assert.assertTrue(ChatMenuDescriptionPage.maximizewindow(),"Fullscreen Assertion Failed"); 
    	screenshot.captureScreen("ChatMenuDescriptionTest","Maximize");
    	Assert.assertTrue(ChatMenuDescriptionPage.minimizewindow(), "Restored Assertion Failed");
    	screenshot.captureScreen("ChatMenuDescriptionTest","Minimize");	
    }
	
	@Test(priority=2)
    public void VerifyDropdownForAllTheColumns() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyColumnsHeaderEnable() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyColumnsHeaderDisable() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertFalse(ChatMenuDescriptionPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=5)
    public void AddNewChatMenuDescriptionRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionMappingPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        chatMenuDescriptionMappingPage.addNewChatMenuDescriptionRecord(chatMenuDescriptionDetails);
        Assert.assertEquals(chatMenuDescriptionMappingPage.getSuccessMessage(),"Record Created Successfully","Add New record assertion failed");
    }
    
    @Test(priority=6,dependsOnMethods = "AddNewChatMenuDescriptionRecord")
    public void AddDuplicateChatMenuDescriptionRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.addNewChatMenuDescriptionRecord(ChatMenuDescriptionDetails);
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Duplicate MenuId");
    }
    
    @Test(priority=7,dependsOnMethods ="AddNewChatMenuDescriptionRecord",enabled=true)
    public void VerifyAuditTrialReportForCreate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map2 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map2);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatMenuDescriptionCreate(ChatMenuDescriptionDetails,"Create"));
    }
    
    @Test(priority=8)
    public void AddEmptyRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.addNewEmptyRecord(ChatMenuDescriptionDetails);
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Please Provide MenuId, MenuName, Intent", "Add invalid record assertion failed");
    }
    
    @Test(priority=9)
    public void AddRecordWithoutMenuId() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.addRecordWithoutMenuID(ChatMenuDescriptionDetails);
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Please Provide MenuId", "Add invalid record assertion failed");
    }
    
    @Test(priority=10)
    public void AddRecordWithoutIntent() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.addRecordWithoutIntent(ChatMenuDescriptionDetails);
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Please Provide Intent", "Add invalid record assertion failed");
    }
    
    @Test(priority=11)
    public void AddRecordWithoutMenuName() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.addRecordWithoutMenuName(ChatMenuDescriptionDetails);
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Please Provide MenuName", "Add invalid record assertion failed");
    }
    
    @Test(priority=12)
    public void VerifyCancelBtnAtAddRecord(){
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.clickOnAddRecord();
        ChatMenuDescriptionPage.clickOnCancelBtn();
        Assert.assertFalse(ChatMenuDescriptionPage.verifyEditFormContainer(), "Cancel Btn at Add record assertion failed");
	}
    
    @Test(priority=13)
    public void VerifySearchIsNotEqualTo() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        Assert.assertTrue(chatMenuDescriptionPage.verifySearchIsNotEqualTo(chatMenuDescriptionDetails.getMenuName()));
    }
    
    @Test(priority=14)
    public void VerifySearchContains() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        Assert.assertTrue(chatMenuDescriptionPage.verifySearchContains(chatMenuDescriptionDetails.getMenuName()));
    }
    
    @Test(priority=15)
    public void VerifySearchDoesNotContains() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        Assert.assertTrue(chatMenuDescriptionPage.verifySearchDoesNotContains(chatMenuDescriptionDetails.getMenuName()));
    }
    
    @Test(priority=16)
    public void VerifySearchStartsWith() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(2);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        Assert.assertTrue(chatMenuDescriptionPage.verifySearchStartsWith(chatMenuDescriptionDetails.getMenuName()));
    }
    
    @Test(priority=17)
    public void VerifySearchEndsWith() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(1);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        Assert.assertTrue(chatMenuDescriptionPage.verifySearchEndsWith(chatMenuDescriptionDetails.getMenuName()));
    }
    
    @Test(priority=18)//,dependsOnMethods = "AddNewChatMenuDescriptionRecord")
    public void EditChatMenuDescriptionRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        chatMenuDescriptionPage.editchatMenuDescriptionRecord(chatMenuDescriptionDetails);
		Thread.sleep(1000);
        Assert.assertEquals(chatMenuDescriptionPage.getSuccessMessage(),"Record Updated Successfully","Edit record assertion failed");
    }
    
    @Test(priority=19,dependsOnMethods="EditChatMenuDescriptionRecord",enabled=true)
    public void VerifyAuditTrialReportForUpdate() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);	
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatMenuDescriptionUpdate(ChatMenuDescriptionDetails,"Update"));
    }
       
    @Test(priority=20)
    public void EditWithoutModifyReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Edit").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.editChatMenuDescriptionWithoutModifyReason(ChatMenuDescriptionDetails);
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Please enter the modify reason", "empty modify reason record assertion failed");
    }
    
    @Test(priority=21)
    public void VerifyCancelBtnAtEditRecord() throws Exception{
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.searchChatMenuDescriptionRecord("24");
        Thread.sleep(1000);
        ChatMenuDescriptionPage.clickOnEditButton();
        ChatMenuDescriptionPage.clickOnCancelBtn();
        Assert.assertFalse(ChatMenuDescriptionPage.verifyEditFormContainer(), "Cancel Btn at Edit record assertion failed");
    }
    
    @Test(priority=22)
    public void searchPage() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertFalse(ChatMenuDescriptionPage .clearAll(ChatMenuDescriptionDetails),"ClearAll Assertion Failed");
        screenshot.captureScreen("ChatMenuDescriptionTest", "clearall");
        Assert.assertTrue(ChatMenuDescriptionPage.verifyclose());
    }
    
    @Test(priority=23)
    public void searchwithoutSearchTextbox() throws IOException {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.searchwithoutextsearch();
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Please enter the text to search or remove the filter", "Add invalid record assertion failed");
    }
    
    @Test(priority=24)
    public void ExportToExcel() throws Exception
    {
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles";
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.verifyExportToExcel(filePath));
    }
    
    @Test(priority=25)
    public void ExportToExcelData() throws Exception
    {	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Menu Description.xlsx";
    	List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
    	Assert.assertTrue(ChatMenuDescriptionPage.verifyexportToExcelSheet(maplist));	
    } 
    
    @Test(priority=26)
    public void DeleteWithoutDeleteReasonRecord() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.deleteWithoutDeleteReasonRecord(ChatMenuDescriptionDetails);
        Assert.assertEquals(ChatMenuDescriptionPage.getMessage(),"Please enter the delete reason","empty delete reason record assertion failed");
    }
    @Test(priority=27)
    public void VerifyCancelBtnAtDeleteChatMenuDescriptionRecord() throws Exception{
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.searchChatMenuDescriptionRecord("24");
        Thread.sleep(1000);
        ChatMenuDescriptionPage.clickOnDeleteButton();
        ChatMenuDescriptionPage.clickOnDeleteCancelBtn();
        Assert.assertFalse(ChatMenuDescriptionPage.verifyDeleteContainer(), "Cancel Btn at Delete record assertion failed");
    }
    
    @Test(priority=28)//,dependsOnMethods = "EditChatMenuDescriptionRecord")
    public void DeleteChatMenuDescriptionRecord() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage chatMenuDescriptionPage = PageFactory.createPageInstance(driver,ChatMenuDescriptionPage.class);
        chatMenuDescriptionPage.deletechatMenuDescriptionRecord(chatMenuDescriptionDetails);
		Thread.sleep(1000);
        Assert.assertEquals(chatMenuDescriptionPage.getSuccessMessage(),"Record Deleted Successfully","Delete record assertion failed");
    }
    
    @Test(priority=29,dependsOnMethods= {"DeleteChatMenuDescriptionRecord"},enabled=true)
    public void VerifyAuditTrialReportForDelete() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Delete").getTestData().get(0);	
        ChatMenuDescriptionDetails chatMenuDescriptionDetails=new ChatMenuDescriptionDetails(map);
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMReportsPage();
        OCMReportsPage ocmReportsPage=PageFactory.createPageInstance(driver, OCMReportsPage.class);
        String filePath1 = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\AuditTrailReportData.xlsx";
        Map<String, String> map1 = new ExcelReader(filePath1,"Show").getTestData().get(0);
        ReportDetails reportDetails= new ReportDetails(map1);
        ocmReportsPage.showReport(reportDetails);
        Assert.assertTrue(ocmReportsPage.verifyChatMenuDescriptiondelete(chatMenuDescriptionDetails,"Delete"));
    }
    
    @Test(priority=30)
    public void SearchClearSearch() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
    	Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
    	Assert.assertTrue(ChatMenuDescriptionPage.verifyinvalidsearchwithwrongdata(ChatMenuDescriptionDetails),"invalidsearchwithwrongdata");
        screenshot.captureScreen("ChatMenuDescriptionTest","Invalid Search with wrong data");
        Assert.assertTrue(ChatMenuDescriptionPage.verifyclearsearch(), "Clear All Assertion Failed");
    }
    
    @Test(priority=31)
    public void ExporttoExcelWithoutData() throws Exception
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.ExporttoExcelWithoutData(ChatMenuDescriptionDetails));
    }
  
    @Test(priority=32)
    public void SortingByAscending() throws IOException {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.SortByAscending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Menu Description (1).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ChatMenuDescriptionPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=33)
    public void SortingByDescending() throws IOException {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionPage.SortByDescending();
    	String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles\\Chat Menu Description (2).xlsx";
        List<Map<String, String>> maplist = new ExcelReader(filePath,"Sheet1").getTestData();
        Assert.assertTrue(ChatMenuDescriptionPage.verifyexportToExcelSheet(maplist));
    }
    
    @Test(priority=34)
    public void GroupBy()
    {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
    	Assert.assertTrue(ChatMenuDescriptionPage.groupby());
        screenshot.captureScreen("ChatMenuDescriptionTest", "GroupBy");
    	Assert.assertTrue(ChatMenuDescriptionPage.groupby());
        screenshot.captureScreen("ChatMenuDescriptionTest", "AlreadyGroupBy");
    }
    
    @Test(priority=35)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
    	Assert.assertTrue(ChatMenuDescriptionPage.verifyArrowMoveForPreviousAndNextPage(),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=36)
    public void VerifyArrowMoveForFirstAndLastPage() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.verifyArrowMoveForFirstAndLastPage(),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=37)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.verifyTotalNumberOfItemsPerPageDetails(),"item per page assertion failed");
    }
    
    @Test(priority=38)
    public void VerifyNumberOfItemsPerPageSelection() {
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        Assert.assertTrue(ChatMenuDescriptionPage.verifyNumberOfItemsPerPage(),"item per page assertion failed");
    }
    
    @Test(priority=39)
    public void database() throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ChatMenuDescriptionData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Queries").getTestData().get(0);
        ChatMenuDescriptionPage ChatMenuDescriptionPage = PageFactory.createPageInstance(driver, ChatMenuDescriptionPage.class);
        ChatMenuDescriptionDetails ChatMenuDescriptionDetails = new ChatMenuDescriptionDetails(map);
    	Assert.assertTrue(ChatMenuDescriptionPage.verifyDatabase(ChatMenuDescriptionDetails.getQuery()));
    }
       
	 @AfterMethod
	    public void afterEachMethod(Method method) throws InterruptedException {
		        Screenshot screenshot=new Screenshot(driver);
		        screenshot.captureScreen("ChatMenuDescriptionTest",method.getName());
		        driver.navigate().refresh();
		}
}

