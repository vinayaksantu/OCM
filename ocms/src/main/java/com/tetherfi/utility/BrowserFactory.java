package com.tetherfi.utility;

import com.tetherfi.constants.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
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

    public WebDriver createBrowserInstance(BrowserType browserType) {
        return createBrowserInstance(browserType, null);
    }

    public WebDriver createBrowserInstance(BrowserType browserType, String fileDownloadLocation) {
        WebDriver driver = null;
      if(browserType==BrowserType.CHROME){
          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("download.default_directory",  fileDownloadLocation);
          ChromeOptions options = new ChromeOptions();
          options.setExperimentalOption("prefs", prefs);
          //options.addArguments("headless");
          //options.addArguments("window-size=1200x600");
          DesiredCapabilities capabilities=getDesiredCapForDriver(browserType);
          String exePath = Constants.chromeDriverPath+"chromedriver.exe";
          System.setProperty("webdriver.chrome.driver", exePath);
          driver=new ChromeDriver(options);
          driver.manage().window().maximize();
        }
        return driver;
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
