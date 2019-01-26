package com.tetherfi.test.tmac;

import com.tetherfi.model.tmac.TmacTransferListDetails;
import com.tetherfi.pages.*;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class TmacTransferListTest extends BaseTest {
	Screenshot screenshot=new Screenshot();
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
    @Test
    public void AddNewTmacConsultTransferRecord() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath, "Create").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.addNewTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }

    @Test(dependsOnMethods = {"AddNewTmacConsultTransferRecord"})
    public void EditTmacConsultTransferRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Edit").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.editTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordUpdated(),"Edit record assertion failed");
    }

    @Test(dependsOnMethods = {"EditTmacConsultTransferRecord"})
    public void DeleteTmacConsultTransferRecord() throws IOException, InterruptedException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Consult Transfer");
        tmacTransferListPage.deleteTmacConsultTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordDeleted(),"Delete record assertion failed");
    }
    @Test
    public void AddNewTmacBlindTransferRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"CreateBlindTransfer").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.addNewTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyNewRecordCreated(),"Add New record assertion failed");
    }
    @Test(dependsOnMethods = {"AddNewTmacBlindTransferRecord"})
    public void EditTmacBlindTransferRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"EditBlindTransfer").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.editTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordUpdated(),"Edit record assertion failed");
    }
    @Test(dependsOnMethods = {"EditTmacBlindTransferRecord"})
    public void DeleteTmacBlindTransferRecord() throws IOException {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TmacTransferListData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"DeleteBlindTransfer").getTestData().get(0);
        TmacTransferListDetails tmacTransferListDetails=new TmacTransferListDetails(map);
        TmacTransferListPage tmacTransferListPage=PageFactory.createPageInstance(driver,TmacTransferListPage.class);
        tmacTransferListPage.navigateToTab("TMAC Blind Transfer");
        tmacTransferListPage.deleteTmacBlindTransferList(tmacTransferListDetails);
        Assert.assertTrue(tmacTransferListPage.verifyRecordDeleted(),"Delete record assertion failed");
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
