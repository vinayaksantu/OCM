package com.tetherfi.test.tmac;

import com.tetherfi.pages.AgentSettingsNewDesignPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.TmacPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class AgentSettingsUITest extends BaseTest {

    @BeforeMethod
    public void NavigateToAgentSettingsPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("TMAC");
        TmacPage tmacPage = PageFactory.createPageInstance(driver,TmacPage.class);
        Assert.assertTrue(tmacPage.isTMACPageDisplayed(),"TMAC page assertion failed");
        tmacPage.navigateToAgentSettingsPage();
        AgentSettingsNewDesignPage agentSettingsPage=PageFactory.createPageInstance(driver,AgentSettingsNewDesignPage.class);
        Assert.assertTrue(agentSettingsPage.isAgentSettingsPageDisplayed(),"Agent Settings page assertion failed");
    }
    
    @Test(priority=1)
    public void VerifyAgentSettingsModuleDisplay() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        Assert.assertEquals(agentSettingsPage.getHeaderText(), "Agent Settings", "Agent settings Module text assertion failed");
        Assert.assertTrue(agentSettingsPage.verifySearchLink(), "search link assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyClearSearchLink(), "clear search link assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyFullScreenLink(), "full screen link assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyAgentSettingsTabsDisplayed(), "Agent Settings tab assertion failed");
    }
    
    @Test(priority=2)
    public void VerifyAgentSettingsApprovedDataPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        Assert.assertTrue(agentSettingsPage.verifyApprovedDataTableHeaders(),"Approved table assertion failed");
    }
    
    @Test(priority=3)
    public void VerifyAgentSettingsAuditTrailDataPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        Assert.assertTrue(agentSettingsPage.verifyAuditTrailDataTableHeaders(),"Audit Trail table assertion failed");
    }
    
    @Test(priority=4)
    public void VerifyMakeAgentSettingsChangeButton() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyAddNewAgentSettingsRecordButton(), "add new Agent Settings record button assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyGoBackButton(), "Go back button assertion failed");
        Assert.assertTrue(agentSettingsPage.verifyExportToExcelButton(), "export to excel button assertion failed");
    }
    
    @Test(priority=5)
    public void VerifyAddNewAgentSettingsButton() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.selectAddNewAgentSettings();
        Assert.assertTrue(agentSettingsPage.verifyAddNewPopupContents(),"Add new pop up content assertion failed");
    }
    
    @Test(priority=5)
    public void VerifyClearAllAtSearchRecord() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyClearAllFunctionality(),"clear all at Search assertion failed");
    }
      
    @Test(priority=6)
    public void VerifyFullScreenButton() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyPageFullScrnd(),"full screen assertion failed");
    }
    
    @Test(priority=7)
    public void VerifyExportToExcel() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyExportToExcel(),"export to excel assertion failed");
    }
    
    @Test(priority=8)
    public void VerifyExportToExcelWithoutData() throws Exception {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.searchAgentSettingsRecord("tmac");
        agentSettingsPage.clickonExportToExcelBtn();
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"There is no record to export","export to excel assertion failed");
    }
    
    @Test(priority=9)
    public void VerifyDragAndDrop() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.dragColumntoGroup("Lan ID");
        Assert.assertTrue(agentSettingsPage.verifyDragColumntoGroup("Lan ID"),"drag and drop assertion failed");
    }
    
    @Test(priority=10)
    public void VerifyDragAndDropofAlreadyGroupedHeader() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.dragColumntoGroup("Lan ID");
        agentSettingsPage.dragColumntoGroup("Lan ID");
        Assert.assertTrue(agentSettingsPage.verifyDragColumntoGroup("Lan ID"),"drag and drop assertion failed");
    }
    
    @Test(priority=11)
    public void VerifyDropdownForAllTheColumns() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyDropDownOfAllHeaders(), "Columns dropdown assertion failed");
    }
    
    @Test(priority=12)
    public void VerifyColumnsHeaderEnable() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifycolumnsHeaderEnabled(),"columns enabled assertion failed");
    }
    
    @Test(priority=13)
    public void VerifyColumnsHeaderDisable() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertFalse(agentSettingsPage.verifycolumnsHeaderDisabled(),"columns disabled assertion failed");
    }
    
    @Test(priority=14)
    public void VerifyArrowMoveForPreviousAndNextPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyArrowMoveForPreviousAndNextPage(2),"arrow move for previous and next page assertion failed");
    }
    
    @Test(priority=15)
    public void VerifyArrowMoveForFirstAndLastPage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyArrowMoveForFirstAndLastPage(2),"arrow move for first and last page assertion failed");
    }
    
    @Test(priority=16)
    public void VerifyTotalNumberOfItemsPerPageDetails() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyTotalNumberOfItemsPerPageDetails(2),"item per page assertion failed");
    }
    
    @Test(priority=17)
    public void VerifyNumberOfItemsPerPageSelection() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        Assert.assertTrue(agentSettingsPage.verifyNumberOfItemsPerPage(2),"item per page assertion failed");
    }
    
    @Test(priority=18)
    public void VerifyMandatoryFieldWarningMessage() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.selectAddNewAgentSettings();
        agentSettingsPage.clickOnSave();
        Assert.assertEquals(agentSettingsPage.verifySuccessMessage(),"Please Provide Lan ID, Avaya Login ID, First Name, Last Name, Org. Unit, Profile, Supervisor, Access Role", "Mandatory field record assertion failed");
    }
    
    @Test(priority=19)
    public void VerifyCancelButtonAtRecordCreation() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.selectAddNewAgentSettings();
        agentSettingsPage.clickOnCancel();
        Assert.assertTrue(agentSettingsPage.verifyCancelButton(),"Cancel button assertion at create failed");
    }
    
    @Test(priority=20)
    public void VerifyCancelButtonAtRecordEdit() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.clickonTopmostEditButton();
        agentSettingsPage.clickOnCancel();
        Assert.assertTrue(agentSettingsPage.verifyCancelButton(),"Cancel button assertion at edit failed");
    }
    
    @Test(priority=21)
    public void VerifyCancelButtonAtRecordDelete() {
        AgentSettingsNewDesignPage agentSettingsPage = PageFactory.createPageInstance(driver, AgentSettingsNewDesignPage.class);
        agentSettingsPage.selectAgentSettingsAuditTrailTab();
        agentSettingsPage.selectMakeAgentSettingsChanges();
        agentSettingsPage.clickOnTopmostDeleteButton();
        agentSettingsPage.clickOnCancelAtDelete();
        Assert.assertTrue(agentSettingsPage.verifyCancelButtonAtDelete(),"Cancel button assertion at delete failed");
    }
    
     
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "AgentSettingsUITest",method.getName());
        driver.navigate().refresh();
    }
}
