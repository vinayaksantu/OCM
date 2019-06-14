package com.tetherfi.pages;

import com.tetherfi.model.user.LogfileDownloaderDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogfileDownloaderPage extends BasePage {

    public LogfileDownloaderPage(WebDriver driver){super(driver);}

    @FindBy(css=".ibox-title h5")
    private WebElement logfileDownloader;

    @FindBy(css=".k-tabstrip-items li")
    private List<WebElement> tabList;

    @FindBy(css="#tdrillGrid .k-grid-content")
    private WebElement savedFilesGridContent;

    @FindBy(css=".k-grid-content")
    private WebElement downloaderGridContent;

    @FindBy(css="span[aria-owns='Module_listbox']")
    private WebElement moduleDropdown;

    @FindBy(css="ul[id='Module_listbox'] li")
    private List<WebElement> moduleListBox;

    @FindBy(css="span[aria-owns='Folder_listbox']")
    private WebElement folderDropdown;

    @FindBy(css="ul[id='Folder_listbox'] li")
    private List<WebElement> folderListBox;

    @FindBy(css=".checkbox")
    private WebElement checkbox;

    @FindBy(css=".toast-message")
    private WebElement successmsg;

    @FindBy(css="#toast-container .toast-error")
    private List<WebElement> errorMsg;

    @FindBy(css=".search-link")
    private WebElement searchLink;

    @FindBy(css=".modal-body .form-inline .form-group .k-select")
    private List<WebElement> selectSearchColumn;

    @FindBy(css="ul[id='1001sColumnName_listbox'] li")
    private List<WebElement> columnNameList;

    @FindBy(css="ul[id='1001sCriteria_listbox'] li")
    private List<WebElement> searchTypeList;

    @FindBy(css=".modal-body .form-inline .form-group .k-textbox")
    private List<WebElement> searchText;

    @FindBy(css=".modal-footer .k-primary")
    private WebElement searchBtn;

    @FindBy(id="fetchFiles")
    private WebElement fetchFilesBtn;

    @FindBy(css=".k-grid-Download")
    private WebElement downloadBtn;

    @FindBy(css=".k-grid-CustomDelete")
    private WebElement deleteBtn;

    @FindBy(css=".swal-button--confirm")
    private WebElement deleteConfirmBtn;

    public boolean isLogFileDownloaderPageDisplayed() {
        waitForLoad(driver);
        waitForJqueryLoad(driver);
        return logfileDownloader.isEnabled();
    }
    public void fetchFiles(LogfileDownloaderDetails details) throws Exception{
        navigateToTab("Downloader");
        if(!details.getModule().equals(""))
        {selectWebElement(moduleDropdown);
        selectDropdownFromVisibleText(moduleListBox,details.getModule());}
        if(!details.getFolder().equals("")){
        selectWebElement(folderDropdown);
        selectDropdownFromVisibleText(folderListBox,details.getFolder());
        }
        searchFile(details.getFilename());
        selectCheckBox(checkbox);
        selectWebElement(fetchFilesBtn);
    }
    public void navigateToTab(String tabname){
        waitUntilWebElementListIsVisible(tabList);
        waitUntilWebElementListIsClickable(tabList);
        for(WebElement ele:tabList){
            if(ele.getText().equalsIgnoreCase(tabname)){ele.click();break;}
        }
    }
    public void searchFile(String file) throws Exception {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"File");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),file);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(downloaderGridContent);
    }
    public void searchFilename(String filename) throws Exception {
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"File Names");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Is equal to");
        enterValueToTxtField(searchText.get(0),filename);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(savedFilesGridContent);
    }
    public void selectCheckBox(WebElement ele){
        if(!ele.isSelected()){selectCheckbox(ele);}
    }
    public boolean verifyFilesFetched(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Please wait while logs are being fetched"))
        {return true;}else{return false;}
    }

    public void downloadFile(String filename) throws Exception{
        navigateToTab("Saved Files");
        searchFilename(filename);
        waitUntilWebElementIsVisible(savedFilesGridContent);
        emptyDownloadsDirectory(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles");
        waitForJqueryLoad(driver);
        selectWebElement(downloadBtn);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean verifyFileDownloaded(){
        return verifyFilePresentInFolder(System.getProperty("user.dir")+"\\src\\test\\resources\\DownloadedFiles","LogfileDownload-");
    }
    public void DeleteFile(String filename) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String newdate= dateFormat.format(date);
        navigateToTab("Saved Files");
        searchFoldername(newdate);
        waitUntilWebElementIsVisible(savedFilesGridContent);
        selectWebElement(deleteBtn);
        selectWebElement(deleteConfirmBtn);
    }
    public boolean verifyRecordDeleted(){
        waitForJqueryLoad(driver);
        if(errorMsg.size()>0){return false;}
        if(waitUntilTextToBePresentInWebElement(successmsg,"Record Deleted Successfully")){
            return true;
        }else{return false;}
    }
    private void searchFoldername(String folder) throws Exception{
        selectWebElement(searchLink);
        selectWebElement(selectSearchColumn.get(0));
        selectDropdownFromVisibleText(columnNameList,"Folder");
        selectWebElement(selectSearchColumn.get(1));
        selectDropdownFromVisibleText(searchTypeList,"Contains");
        enterValueToTxtField(searchText.get(0),folder);
        selectWebElement(searchBtn);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(savedFilesGridContent);
    }
}
