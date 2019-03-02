package com.tetherfi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChatApiPage extends BasePage{

	public ChatApiPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="btnStart")
	private WebElement startbtn;
	
	public void StartChat()
	{
		selectWebElement(startbtn);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
