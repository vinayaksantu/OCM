package com.tetherfi.test;

import com.tetherfi.model.user.LogfileDownloaderDetails;
import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LogfileDownloaderPage;
import com.tetherfi.pages.OCMHomePage;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class LogfileDownloaderTest extends BaseTest {
    @BeforeMethod
    public void NavigateToUserManagementPage() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        homePage.navigateToOCMPage();
        OCMHomePage ocmHomePage = PageFactory.createPageInstance(driver, OCMHomePage.class);
        Assert.assertTrue(ocmHomePage.isOCMHomePageIsDisplayed(), "OCM HOME Page assertion failed");
        ocmHomePage.navigateToLogfileDownloader();
        LogfileDownloaderPage logfileDownloaderPage=PageFactory.createPageInstance(driver,LogfileDownloaderPage.class);
        Assert.assertTrue(logfileDownloaderPage.isLogFileDownloaderPageDisplayed(),"logfile downloader assertion failed");
    }
    @Test
    public void DownloaderFetchFiles() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LogfileDownloaderData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"FetchFiles").getTestData().get(0);
        LogfileDownloaderDetails logfileDownloaderDetails=new LogfileDownloaderDetails(map);

        LogfileDownloaderPage logfileDownloaderPage=PageFactory.createPageInstance(driver,LogfileDownloaderPage.class);
        logfileDownloaderPage.fetchFiles(logfileDownloaderDetails);
        Assert.assertTrue(logfileDownloaderPage.verifyFilesFetched(),"fetch files assertion failed");
    }
    @Test(dependsOnMethods="DownloaderFetchFiles")
    public void SavedFilesDownload() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LogfileDownloaderData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Download").getTestData().get(0);
        LogfileDownloaderDetails logfileDownloaderDetails=new LogfileDownloaderDetails(map);

        LogfileDownloaderPage logfileDownloaderPage=PageFactory.createPageInstance(driver,LogfileDownloaderPage.class);
        logfileDownloaderPage.downloadFile(logfileDownloaderDetails.getFilename());
        Assert.assertTrue(logfileDownloaderPage.verifyFileDownloaded(),"download files assertion failed");
    }
    @Test(dependsOnMethods="SavedFilesDownload")
    public void SavedFilesDelete() throws Exception {
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LogfileDownloaderData.xlsx";
        Map<String, String> map = new ExcelReader(filePath,"Delete").getTestData().get(0);
        LogfileDownloaderDetails logfileDownloaderDetails=new LogfileDownloaderDetails(map);

        LogfileDownloaderPage logfileDownloaderPage=PageFactory.createPageInstance(driver,LogfileDownloaderPage.class);
        logfileDownloaderPage.DeleteFile(logfileDownloaderDetails.getFilename());
        Assert.assertTrue(logfileDownloaderPage.verifyRecordDeleted(),"delete files assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
        Screenshot screenshot=new Screenshot(driver);
        screenshot.captureScreen(driver,method.getName(),"LogfileDownloaderTest");
    }
}
