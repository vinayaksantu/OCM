package com.tetherfi.test;

import com.tetherfi.pages.HomePage;
import com.tetherfi.pages.LoginPage;
import com.tetherfi.utility.BrowserFactory;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void openApplication()  {
        try {
            PageFactory.reset();
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createBrowserInstance(BrowserFactory.BrowserType.CHROME, System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
            String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LoginData.xlsx";
            Map<String, String> map = new ExcelReader(filePath,"Login").getTestData().get(0);
            driver.get("http://"+map.get("Username")+":"+map.get("Password")+"@"+map.get("Application URL").split("//")[1]);
            if(map.get("LoginType").equals("Custom")){
                LoginPage loginPage=PageFactory.createPageInstance(driver,LoginPage.class);
                loginPage.login(map.get("Username"),map.get("Password"));
                Thread.sleep(5000);
            }
            HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
            Assert.assertTrue(homePage.checkPageLoadStatus(), "user login successful status");
        }catch (Exception e){
            PageFactory.reset();
            driver.close();
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }
    @BeforeMethod
        public void startTestCase(Method method){
        System.out.println("Started Executing : "+method.getName());
    }
    @AfterMethod
        public void endTestCase(Method method){
        System.out.println("Completed Executing : "+method.getName());
    }

    @AfterClass
    public void tearDown() {
      try{
          HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
          homePage.userLogout();
          driver.close();
      }catch(Exception e)
        {
          driver.close();
        e.printStackTrace();
        }
    }
}