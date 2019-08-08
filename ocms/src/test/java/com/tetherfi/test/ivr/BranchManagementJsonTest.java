package com.tetherfi.test.ivr;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tetherfi.pages.BranchManagementPage;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.pages.IvrPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.FTPServer;
import com.tetherfi.utility.JSONReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;

public class BranchManagementJsonTest extends BaseTest {

    String destinationFilePath = "D:/TetherfiWork/ProductOCM/scripts/ocms/src/test/resources/DownloadedFiles/Json/IvrHostmap.json";
    String remoteFilePath = "\\\\172.16.2.16\\d$\\Products\\OCM\\UI\\CustomJSONConfigurations\\Admin%20Modules\\IvrHostmap.json";

    @BeforeMethod
    public void NavigateToBranchManagementPage() throws InterruptedException {
        HomePage homePage= PageFactory.createPageInstance(driver,HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver,OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(),"OCM HOME Page assertion failed");
        ocmHomePage.navigateToTab("IVR");
        IvrPage IvrPage = PageFactory.createPageInstance(driver,IvrPage.class);
        Assert.assertTrue(IvrPage.isIVRPageDisplayed(),"Ivr page assertion failed");
        IvrPage.navigateToBranchManagementPage();
        BranchManagementPage BranchManagementPage=PageFactory.createPageInstance(driver,BranchManagementPage.class);
        Assert.assertTrue(BranchManagementPage.isBranchManagementPageDisplayed(),"Host Value Mapping page assertion failed");

        FTPServer ftp=new FTPServer();
        ftp.transferFileFromRemote(remoteFilePath,destinationFilePath);
    }
    @Test
    public void VerifyJsonDataForMakerAndChecker(){
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(BranchManagementPage.verifyJsonDataForMakerAndChecker(json.getJsonMakerCheckerKeyData()),"JSON data Maker Checker assertion failed");
    }
    @Test
    public void VerifyJsonDataForGridColumnHidden(){
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.selectBranchManagementAuditTrailTab();
        BranchManagementPage.selectMakeBranchManagementChanges();
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(BranchManagementPage.verifyJsonDataForgridColumnHidden(json.getJsonGridColumnTitleKeyData("Hidden")),"JSON data grid column hidden assertion failed");
    }
    @Test
    public void VerifyJsonDataForColumnIncludeGrid(){
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.selectBranchManagementAuditTrailTab();
        BranchManagementPage.selectMakeBranchManagementChanges();
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(BranchManagementPage.verifyJsonDataForColumnIncludeGrid(json.getJsonGridColumnTitleKeyData("IncludeGrid")),"JSON data column include grid assertion failed");
    }
    @Test
    public void VerifyJsonDataForMandatoryField(){
        BranchManagementPage BranchManagementPage = PageFactory.createPageInstance(driver, BranchManagementPage.class);
        BranchManagementPage.selectBranchManagementAuditTrailTab();
        BranchManagementPage.selectMakeBranchManagementChanges();
        BranchManagementPage.selectAddNewBranchManagement();
        JSONReader json= new JSONReader(destinationFilePath);
        Assert.assertTrue(BranchManagementPage.verifyJsonDataForMandatoryField(json.getJsonGridColumnTitleKeyData("Mandatory")),"JSON data mandatory field assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method){
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen( "BranchManagementJsonTest",method.getName());
        driver.navigate().refresh();
    }
}