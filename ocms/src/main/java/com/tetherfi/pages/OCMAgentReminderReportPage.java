package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OCMAgentReminderReportPage extends BasePage {

	public OCMAgentReminderReportPage(WebDriver driver) {
		super(driver);
		}
	
	@FindBy(xpath="//button[normalize-space()='Scheduled Reports']")
	private WebElement schRptsinAgent;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void navigateToExportSchedulerPage() {			
		waitUntilWebElementIsClickable(schRptsinAgent);
		selectWebElement(schRptsinAgent);			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
