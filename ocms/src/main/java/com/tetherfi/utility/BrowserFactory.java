package com.tetherfi.utility;

import com.tetherfi.constants.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    public static WebDriver webDriver = null;

    public enum BrowserType {
        CHROME("Chrome"), IE("ie"), FIREFOX("firefox");
        private final String browserType;
        BrowserType(String browserType) {
            this.browserType = browserType;
        }
    }
    public String browserName = "";

    public WebDriver createBrowserInstance(BrowserType browserType) {
        return createBrowserInstance(browserType, null);
    }

    public WebDriver createBrowserInstance(BrowserType browserType, String fileDownloadLocation) {
        WebDriver driver = null;
      if(browserType==BrowserType.CHROME){
          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("profile.default_content_settings.popups", 0);
          prefs.put("download.default_directory",  fileDownloadLocation);
          ChromeOptions options = new ChromeOptions();
          options.setExperimentalOption("prefs", prefs);
          //options.addArguments("headless");
          //options.addArguments("--disable-gpu");
          //options.addArguments("--no-sandbox");
          //options.addArguments("--allow-insecure-localhost");
          //options.addArguments("window-size=1200x600");
          DesiredCapabilities capabilities=getDesiredCapForDriver(browserType);
          String exePath = Constants.chromeDriverPath+"chromedriver.exe";
          System.setProperty("webdriver.chrome.driver", exePath);
          driver=new ChromeDriver(options);
          driver.manage().window().maximize();
        }
      else if(browserType==BrowserType.IE){
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory",  fileDownloadLocation);
            InternetExplorerOptions options= new InternetExplorerOptions();
            options.setCapability("prefs", prefs);
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            System.setProperty("webdriver.ie.driver", getDriverPath("IE"));
            //it is used to initialize the IE driver
            driver = new InternetExplorerDriver(options);
            driver.manage().window().maximize();
            browserName = "IE";
        }
      
        return driver;
    }
    
    private static String getDriverPath(String browser) {
        if(browser.equals("Chrome"))
        return Constants.chromeDriverPath + "chromedriver.exe";
        else
            return Constants.ieDriverPath+"IEDriverServer.exe";
    }

    private static String getChromeDriverPath() {
    return Constants.chromeDriverPath + "chromedriver.exe";
    }

    private DesiredCapabilities getDesiredCapForDriver(BrowserType browserType) {
       DesiredCapabilities cap = null;
       if(browserType.equals(BrowserType.CHROME)){
            cap=DesiredCapabilities.chrome();
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            String driverPath=getChromeDriverPath();
            System.setProperty("webdriver.chrome.driver",driverPath);
        }
        return cap;
    }
}
