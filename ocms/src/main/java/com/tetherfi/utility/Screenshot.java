package com.tetherfi.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class Screenshot {
	WebDriver driver;
	
	public Screenshot(WebDriver driver){this.driver=driver;}

    public void captureScreen(String foldername, String filename){
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\Screenshot\\"+foldername+"\\" + filename+".jpg");
            FileUtils.copyFile(SrcFile, DestFile);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
