package com.tetherfi.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextTemplatesPage extends BasePage {

	public TextTemplatesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement textTemplates;

	@FindBy(xpath="//i[@class='fas fa-comments']")
	private WebElement TTImg;
	
	@FindBy(id="navbarheader")
	private WebElement header;
	
	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;
				
	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;
				 
	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;
	
	@FindBy(xpath="//div[@id='tabstrip']/ul/li")
    private List<WebElement> tabList;
	
	@FindBy(css="th a[class='k-header-column-menu']")
	private List<WebElement> headersDropdown;
			    
	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;
			    
	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;
	
	








	public boolean isChatTemplatePageDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return textTemplates.isEnabled();
	}

	public boolean VerifyLogo() {
		if(TTImg.isDisplayed())
			return true;
		else
			return false;

	}

	public boolean maximizewindow() {
		selectWebElement(maximize);
		waitForJqueryLoad(driver);
		if(fullscreen.isEnabled())
			return true;
		else 
		{return false;}
	}
	public boolean minimizewindow() {
		selectWebElement(minimize);
		waitForJqueryLoad(driver);
		if(header.isDisplayed())
		{
			return true;
		}
		else 
			return false; 
	}
	
	public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(tabList);
        waitUntilWebElementListIsClickable(tabList);
        for(WebElement ele:tabList){
            if(ele.getText().equalsIgnoreCase(tabname)){ele.click();
            waitForJqueryLoad(driver);break;
            }
        }
    }
	
	public boolean verifyDropDownOfAllHeaders() {
        boolean status = false;
        try {for (WebElement ele : headersDropdown) {
            scrollToElement(ele);
            if (!ele.isDisplayed()) {
                continue;
            } else {
                selectWebElement(ele);
                    Thread.sleep(1000);
                if (headersColumns.get(0).getText().equals("Sort Ascending")) {
                    if (headersColumns.get(1).getText().equals("Sort Descending")) {
                        if (headersColumns.get(2).getText().equals("Columns")) {
                            status = true;selectWebElement(ele);
                        }
                    }
                }
                if (status) {
                } else {
                    break;
                }
            }
        }} catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    public boolean verifycolumnsHeaderDisabled() {
    	 boolean status=false;
	        try{for(WebElement ele:headersDropdown) {
	        	scrollToElement(ele);
	        	 if (!ele.isDisplayed()) {
		                continue;
	        	 }
		                else {
                try {
                    selectWebElement(ele);
                    Thread.sleep(1000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i =3; i < headersColumns.size(); i++) {
                    System.out.println(headersColumns.get(i).getText());
                    WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
                    if (checkbox.isSelected()) {
                        checkbox.click();
                    } else {
                    }
                    for (WebElement ele1 : headersText) {
                        if (ele1.getText().equals(headersColumns.get(i).getText())) {
                            status = true;
                            break;
                        }
                    }
                    if (!status) {
                        checkbox.click();
                    } else {
                        break;
                    }
                }
            }
	            break;
	        }
    }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	 	        return status;
    }
    public boolean verifycolumnsHeaderEnabled(){
        boolean status=false;
        try{
        	for(WebElement ele:headersDropdown) {
        	scrollToElement(ele);
        	 if (!ele.isDisplayed()) {
	                continue;
        	 }
        	 else {
                try {
                    selectWebElement(ele);
                    Thread.sleep(1000);
                    selectWebElement(headersColumns.get(2));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 3; i <headersColumns.size(); i++) {
                    WebElement checkbox = headersColumns.get(i).findElement(By.tagName("input"));
                    checkbox.click();
                    if (checkbox.isSelected()) {
                    } else {
                        checkbox.click();
                    }
                    for (WebElement ele1 : headersText) {
                        if (ele1.getText().equals(headersColumns.get(i).getText())) {
                            status = true;
                            break;
                        }
                    }
                    if (status) {
                    } else {
                        break;
                    }
                }
            }
            break;
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


}
