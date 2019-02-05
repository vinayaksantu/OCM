package com.tetherfi.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tetherfi.constants.Constants;
import com.tetherfi.utility.DatabaseConnector;

import java.io.File;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import java.util.List;
import java.util.Map;

public class BasePage {

    protected static WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
    }
   
    public void waitForLoad(WebDriver driver) {
      try {
          WebDriverWait wait = new WebDriverWait(driver, 20);
          ExpectedCondition<Boolean> pageLoadCondition = new
                  ExpectedCondition<Boolean>() 
          			{
                      public Boolean apply(WebDriver driver) 
                      {
                          return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                      }
                  };
          wait.until(pageLoadCondition);
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
      }
    }
    public void waitUntilLoadingImageDisapper(WebDriver driver){
        Wait<WebDriver> wait= new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5,TimeUnit.SECONDS).ignoring(java.util.NoSuchElementException.class).ignoring(TimeoutException.class);
        WebElement e=wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(".k-loading"));
            }
        });
        if(e!=null){waitUntilInvisibilityOfWebElement(e);}
    }
	public void waitForJqueryLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
							try {
								return ((Long)((JavascriptExecutor) driver).executeScript("return jQuery.active")==0);
							}catch (Exception e){return true;}	
                        }
                    };
            wait.until(pageLoadCondition);
        }catch(Exception e){e.printStackTrace();}
    }
    public void waitForApplicationLoad(WebDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        try {
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            try {
                //driver.get(Constants.winAuthUrl);
                wait.until(pageLoadCondition);
            } catch (Exception ex) {
                throw ex;
            }
        }
    }


    public void waitForWebElementIgnoringStaleException(WebElement element) 
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForWebElementIgnoringElementNotVisibleException(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.ignoring(ElementNotVisibleException.class).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForWebElementIgnoringElementNotInteractableException(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.ignoring(ElementNotInteractableException.class).until(ExpectedConditions.elementToBeClickable(element));
    }
	public void waitForWebElementsEnabled(List<WebElement> webElementList) {
        for(WebElement element: webElementList){
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeSelected(element));}
    }
    public void waitUntilWebElementIsVisible(WebElement webElement) { 
        {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
            webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
        }
		
	}

    public void waitUntilWebElementListIsVisible(List<WebElement> webElementList) 
    {
            for(WebElement ele: webElementList)
            {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
            webDriverWait.ignoring(ElementNotVisibleException.class).until(ExpectedConditions.visibilityOf(ele));
            }
      
    }

    public boolean waitUntilTextToBePresentInWebElement(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void enterValueToTxtField(WebElement webElement, String value) {
        waitUntilWebElementIsVisible(webElement);
        waitUntilWebElementIsClickable(webElement);
        webElement.clear();
        webElement.sendKeys(value);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void enterValueToTxtFieldWithoutClear(WebElement webElement, String value) {
        waitUntilWebElementIsVisible(webElement);
        waitUntilWebElementIsClickable(webElement);
        webElement.sendKeys(value);
    }

    public void enterValueToDropdownText(WebElement webElement, String value) {
        waitUntilWebElementIsVisible(webElement);
        waitUntilWebElementIsClickable(webElement);
        webElement.click();
        webElement.sendKeys(value);
    }

    public void selectWebElement(WebElement webElement) {
        waitUntilWebElementIsVisible(webElement);
        waitUntilWebElementIsClickable(webElement);
        waitForWebElementIgnoringStaleException(webElement);
        waitForWebElementIgnoringElementNotInteractableException(webElement);
        webElement.click();
    }
    public void selectInvisibleWebElement(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", webElement);
    }
    public boolean isWebElementEnabled(WebElement validateBtn) {
        return validateBtn.isEnabled();
    }

    public void btnClick(WebElement btn) {
        try {
            scrollToElement(btn);
            waitUntilWebElementIsVisible(btn);
            waitUntilWebElementIsClickable(btn);
            btn.click();
        } catch (Exception e) {
            scrollingToBottomofAPage();
            btn.click();
            e.printStackTrace();
        }
    }

    public void selectFromDropdownByValue(WebElement webElement, String value) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        scrollToElement(webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        Select dropDown = new Select(webElement);
        dropDown.selectByValue(value);

    }
	
	public void selectFromDropdownByVisibleText(WebElement webElement, String value) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        scrollToElement(webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        Select dropDown = new Select(webElement);
        dropDown.selectByVisibleText(value);
    }
	
    public void selectFromDropdownByPartialText(WebElement webElement, String value) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        scrollToElement(webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        Select dropDown = new Select(webElement);
        List<WebElement> servicesList = dropDown.getOptions();
        for (WebElement service : servicesList) {
            if (service.getText().contains(value)) {
                service.click();
                break;
            }
        }

    }

    public void waitUntilWebElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void waitUntilWebElementListIsClickable(List<WebElement> elementList) {
        for(WebElement ele: elementList) {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
        }
    }

    public void waitUntilWebElementIsEnabled(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getTextFromWebElement(WebElement ele){
        waitUntilWebElementIsVisible(ele);
        return ele.getText();
    }

    public boolean IsWebElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isWebElementDisplayed(WebElement element) {
        waitUntilWebElementIsVisible(element);
        return element.isDisplayed();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean checkPageLoadStatus() {
        boolean result = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            result = true;
        }
        return result;
    }

    public void waitUntilInvisibilityOfWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void selectCheckbox(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollingToBottomofAPage() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickOn(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void waitUntilWebElementsIsVisible(List<WebElement> element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    public void selectDropdownFromVisibleText(List<WebElement> webElementList, String text) {
        waitUntilWebElementListIsVisible(webElementList);
        waitUntilWebElementListIsClickable(webElementList);
        for(WebElement ele: webElementList){
        	System.out.println(ele);
            if(ele.getText().equalsIgnoreCase(text)){ clickOn(ele);break;}
        }
    }
    public void emptyDownloadsDirectory(String filepath)
    {
        File downloadsDir = new File(filepath);
        // Verify downloads dir is empty, if not remove all files.
        File[] downloadDirFiles = downloadsDir.listFiles();

        if (downloadDirFiles != null) {
            for (File f : downloadDirFiles) {
                if (f.exists()) {

                    boolean deleted= f.delete();
                    System.out.println(deleted);
                }
            }
        }
    }
    public boolean verifyFilePresentInFolder(String filepath, String pattern){
        File downloadsDir = new File(filepath);
        File[] downloadDirFiles = downloadsDir.listFiles();
        String actualName = null;
        boolean status=false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-");
        Date date=new Date();
        String currentDate= dateFormat.format(date);
        for (File file : downloadDirFiles) {
            actualName = file.getName();
           // if (actualName.contains(pattern+currentDate)) {
			if (actualName.contains(pattern)){
                status=true;
                break;
            }
        }return status;
    }

    /*public void waitForApplicationLoad(WebDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        try {
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            try {
                //driver.get(Constants.winAuthUrl);
                wait.until(pageLoadCondition);
            } catch (Exception ex) {
                throw ex;
            }
        }
    }*/    
    public void switchToNewWindow(){
        String oldTab=driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }
    public void switchBackToParentWindow(){
        String newTab=driver.getWindowHandle();
        ArrayList<String> oldTab = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        oldTab.remove(newTab);
        driver.switchTo().window(oldTab.get(0));
    }
	public void switchToWindow(int i){
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(Tabs.get(i));
    }
    public void clickOnUsingActionClass(WebElement ele){
        Actions builder = new Actions(driver);
        Action clickon = builder.click(ele).build();
        clickon.perform();
    }
    public void moveToElement (WebElement ele) {
    	Actions builder = new Actions(driver);
    	builder.moveToElement(ele).perform();
    }
    
    public boolean isElementExist(WebElement we) {
        try {
            we.isDisplayed();
            return true;
        } catch(NoSuchElementException e) {
            System.out.println("Element does not exist.");
            return false;
        }
    }
    public List<Map<String,String>> database(String query)
	{
		DatabaseConnector dc=new DatabaseConnector();
		dc.connectToDataBase(Constants.db_name);
		ResultSet rs=dc.executeQuery(query);
		List<Map<String,String>> dbdata=dc.getResultSetInMap(rs);
		dc.closeDbConnection();
		return dbdata;
	}
    public boolean verifyExportPageFileDownload(String filepath, String pattern){
        File downloadsDir = new File(filepath);
        File[] downloadDirFiles = downloadsDir.listFiles();
        String actualName = null;
        boolean status=false;
        for (File file : downloadDirFiles) {
            actualName = file.getName();
            System.out.println(actualName);
            if (actualName.contains(pattern)) {
                status=true;
                break;
            }
        }return status;
    }
    
    public void DragandDrop(WebElement ele,WebElement ele1)
    {
    	Actions builder = new Actions(driver);
    	builder.dragAndDrop(ele, ele1).build().perform();;
    }   
	public void selectDropdownFromVisibleTextContains(List<WebElement> webElementList, String text) {
        waitUntilWebElementListIsVisible(webElementList);
        waitUntilWebElementListIsClickable(webElementList);
        //waitForWebElementsIgnoringStaleException(webElementList);
        for(WebElement ele: webElementList){
            if(ele.getText().contains(text)){ele.click();break;}
        }
    }
    public void waitForWebElementsIgnoringStaleException(List<WebElement> elements) {
        for(WebElement element:elements){
            WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
            webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));}
    }
    public void waitUntilNewTabIsOpen(int windowsize){
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.ignoring(NoSuchWindowException.class).until(ExpectedConditions.numberOfWindowsToBe(windowsize));
    }
}