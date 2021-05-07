package com.tetherfi.pages;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.tetherfi.model.tmac.SlotSchedulerDetails;

public class SlotSchedulerPage extends BasePage  {

	public SlotSchedulerPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".ibox-title h5")
	private WebElement slotScheduler;

	@FindBy(id="navbarheader")
	private WebElement header;

	@FindBy(xpath="//i[@class='far fa-calendar-alt']")
	private WebElement slotSchedulerImg;

	@FindBy(xpath="//i[@class='fas fa-expand']")
	private WebElement maximize;

	@FindBy(xpath="//div[@class='ibox float-e-margins fullscreen']")
	private WebElement fullscreen;

	@FindBy(xpath="//i[@class='fas fa-compress']")
	private WebElement minimize;

	@FindBy(xpath="//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")
	private List<WebElement> tablerecord;

	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;

	@FindBy(xpath="//span[@class='k-input']")
	private WebElement pagerSize;

	@FindBy(xpath="//li[@aria-controls='tgrid']")
	private WebElement slotdetailsTab;

	@FindBy(xpath="//li[@aria-controls='tscheduler']")
	private WebElement slotOccupencyTab;

	@FindBy(xpath="//button[@id='create']")
	private WebElement addNewSlotSchedulerRecordButton;

	@FindBy(xpath="//button[@class='k-button k-button-icontext k-grid-excel']")
	private WebElement ExportExcelButton;

	@FindBy(xpath="//label[text()='Slot Group*']")
	private WebElement slotGroupLabel;

	@FindBy(xpath="//label[text()='Slot Type*']")
	private WebElement slotTypeLabel;

	@FindBy(xpath="//label[text()='Start Of Week']")
	private WebElement startofWeekLabel;

	@FindBy(xpath="//label[text()='End Of Week']")
	private WebElement endOfWeekLabel;

	@FindBy(xpath="//label[text()='From Time*']")
	private WebElement fromTimeLabel;

	@FindBy(xpath="//label[text()='To Time*']")
	private WebElement toTimeLabel;

	@FindBy(xpath="//label[text()='Monday']")
	private WebElement mondayLabel;

	@FindBy(xpath="//label[text()='Tuesday']")
	private WebElement tuesdayLabel;

	@FindBy(xpath="//label[text()='Wednesday']")
	private WebElement wednesdayLabel;

	@FindBy(xpath="//label[text()='Thursday']")
	private WebElement thursdayLabel;

	@FindBy(xpath="//label[text()='Friday']")
	private WebElement fridayLabel;

	@FindBy(xpath="//label[text()='Saturday']")
	private WebElement saturdayLabel;

	@FindBy(xpath="//label[text()='Sunday']")
	private WebElement sundayLabel;

	@FindBy(xpath="//label[text()='Slot Description*']")
	private WebElement slotDescriptionLabel;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-primary k-grid-update']")
	private WebElement saveButton;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-cancel']")
	private WebElement cancelButton;

	@FindBy(xpath="//span[@aria-owns='SlotGroup_listbox']")
	private WebElement slotGroupDropDown;

	@FindBy(css="ul[id='SlotGroup_listbox'] li")
	private List<WebElement> slotGroupDropDownValues;

	@FindBy(xpath="//span[@aria-owns='SlotType_listbox']")
	private WebElement slotTypeDropDown;

	@FindBy(css="ul[id='SlotType_listbox'] li")
	private List<WebElement> slotTypeDropDownValues;

	@FindBy(xpath="//span[@aria-controls='StartOfWeek_dateview']")
	private WebElement startOfWeekCalenderIcon;

	@FindBy(xpath="//span[@aria-controls='EndOfWeek_dateview']")
	private WebElement endOfWeekCalenderIcon;

	@FindBy(css=".k-header .k-link.k-nav-fast")
	private List<WebElement> CurrentDate;

	@FindBy(xpath="//a[@aria-label='Next']")
	private List<WebElement> NextIcon;

	@FindBy(xpath="//a[@aria-label='Previous']")
	private List<WebElement> PrevIcon;

	@FindBy(xpath="//span[@aria-controls='FromTime_timeview']")
	private WebElement fromTimeIcon;

	@FindBy(xpath="//span[@aria-controls='ToTime_timeview']")
	private WebElement toTimeIcon;

	@FindBy(css="ul[id='FromTime_timeview'] li")
	private List<WebElement> fromTimevalues;

	@FindBy(css="ul[id='ToTime_timeview'] li")
	private List<WebElement> toTimevalues;

	@FindBy(xpath="//input[@class='k-formatted-value k-input' and @placeholder='Enter Value']/..")
	private List<WebElement> days;

	@FindBy(id="Monday")
	private WebElement Monday;

	@FindBy(id="Tuesday")
	private WebElement Tuesday;

	@FindBy(id="Wednesday")
	private WebElement Wednesday;

	@FindBy(id="Thursday")
	private WebElement Thursday;

	@FindBy(id="Friday")
	private WebElement Friday;

	@FindBy(id="Saturday")
	private WebElement Saturday;

	@FindBy(id="Sunday")
	private WebElement Sunday;

	@FindBy(xpath="//input[@id='SlotDescription']")
	private WebElement slotDescription;

	@FindBy(css="#toast-container .toast-error .toast-message")
	private List<WebElement> errorMsg;

	@FindBy(className="toast-message")
	private WebElement successmsg;

	@FindBy(xpath="//input[@id='StartOfWeek']")
	private WebElement startOfWeek;

	@FindBy(id="tgrid")
	private WebElement auditGridContent;

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;

	@FindBy(css = ".fa-search")
	private WebElement searchBtn;

	@FindBy(css = ".modal-body .form-inline .form-group .k-select")
	private List<WebElement> selectSearchCol;

	@FindBy(css="ul[id='1001sColumnName_listbox'] li")
	private List<WebElement> columnNameList;

	@FindBy(css="ul[id='1001sCriteria_listbox'] li")
	private List<WebElement> searchCriteriaDropDwn;

	@FindBy(id = "1001sTextToSearch")
	private WebElement searchTextBox;

	@FindBy(css = ".modal-footer .button-theme")
	private WebElement searchSearchBtn;

	@FindBy(css=".k-grid-content")
	private WebElement gridContent;

	@FindBy(xpath="//span[@aria-controls='1001sColumnName_listbox']")
	private WebElement selectSearchColumn;

	@FindBy(xpath="//span[@aria-owns='1001sCriteria_listbox']")
	private WebElement condition;

	@FindBy(xpath = "//a[@class='k-button k-button-icontext k-grid-edit']")
	private WebElement editButton;

	@FindBy(xpath="//input[@id='ModifyReason']")
	private WebElement modifyReason;

	@FindBy(xpath="//a[@class='k-button k-button-icontext k-grid-CustomDelete']")
	private WebElement deleteButton;

	@FindBy(id="ModifyReason1")
	private WebElement deleteReason;

	@FindBy(css="#myWindow #noButton")
	private WebElement deleteNoButton;

	@FindBy(css="#myWindow #yesButton")
	private WebElement deleteYesButton;

	@FindBy(xpath="//table//tbody/tr/td[3]")
	private WebElement tablerow;

	@FindBy(id="export")
	private WebElement exportButton;

	@FindBy(xpath="//a[text()='Slot Type']")
	private WebElement gridSlotType;

	@FindBy(xpath="//div[@data-role='droptarget']")
	private WebElement dropTarget;

	@FindBy(xpath="//p[@class='k-reset']")
	private WebElement groupBy;

	@FindBy(xpath="//tbody/tr/td/p[@class='k-reset']/../../following-sibling::tr/td[4]")
	private WebElement groupBySlotType;

	@FindBy(css=".k-pager-numbers .k-state-selected")
	private WebElement pageNumber;

	@FindBy(css="a[aria-label='Go to the first page']")
	private WebElement firstPageIcon;

	@FindBy(css="a[aria-label='Go to the previous page']")
	private WebElement previousPageIcon;

	@FindBy(css=".k-pager-sizes .k-icon")
	private WebElement pagerDropdown;

	@FindBy(css=".k-animation-container ul li")
	private List<WebElement> pageSizeListBox;

	@FindBy(xpath="//a[@title='Edit Column Settings']")
	private List<WebElement> headersDropdown;

	@FindBy(css="div[style*='overflow: visible'] span[class^='k-link']")
	private List<WebElement> headersColumns;

	@FindBy(css="th a[class='k-link']")
	private List<WebElement> headersText;

	@FindBy(xpath="//a[@aria-label='Go to the last page']")
	private WebElement lastPageIcon;

	@FindBy(xpath="//button[@class='k-button k-pdf']")
	private WebElement exportPDFButton;

	@FindBy(xpath="//button[@title='Previous']")
	private WebElement previousButton;

	@FindBy(xpath="//button[@title='Next']")
	private WebElement NextButton;

	@FindBy(xpath="//span[@class='k-icon k-i-calendar']")
	private WebElement CalenderDateSelectionButton;

	@FindBy(xpath="//a[@class='k-button k-button-icon k-icon-button k-scheduler-refresh']")
	private WebElement refreshButton;

	@FindBy(xpath="//span[@class='k-lg-date-format']")
	private WebElement calenderFieldDate;










	public boolean isSlotScheduelrPageIsDisplayed() {
		waitForLoad(driver);
		waitForJqueryLoad(driver);
		return slotScheduler.isEnabled();
	}

	public boolean verifylogo() 
	{
		if(isElementExist(slotSchedulerImg)) 
		{return true;}
		else return false;
	}

	public boolean verifygridcontent() {
		int size=tablerecord.size();
		System.out.println(+size);
		String item[]=(items.getText()).split("\\s+");
		int itemno=Integer.parseInt(item[2]);
		System.out.println(itemno);
		if(itemno==size)
			return true;
		else 
			return false;}

	public boolean VerifySlotSchedulerPageButtonPresence() {
		waitForJqueryLoad(driver);
		boolean status=false;
		if(slotdetailsTab.isEnabled()) {
			if(slotOccupencyTab.isEnabled()) {
				if(addNewSlotSchedulerRecordButton.isEnabled()) {
					if(ExportExcelButton.isEnabled()) {
						status=true;
					}
					else {System.out.println("ExportExcelButton is Disabled");}
				}
				else {System.out.println("addNewSlotSchedulerRecordButton is Disabled");}
			}
			else {System.out.println("slotOccupencyTab is disabled");}
		}
		else {System.out.println("slotdetailsTab is disabled");}
		return status;
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
			return true;
		else 
			return false;}

	public boolean VerifyAddSlotSchedulerPopUpLabelsandCancelButton() throws Exception {
		waitForJqueryLoad(driver);
		String actualItems=items.getText();
		System.out.println(actualItems);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(2000);
		boolean status=false;
		if(slotGroupLabel.isDisplayed()) {
			if(slotTypeLabel.isDisplayed()) {
				if(startofWeekLabel.isDisplayed()) {
					if(endOfWeekLabel.isDisplayed()) {
						if(fromTimeLabel.isDisplayed()) {
							if(toTimeLabel.isDisplayed()) {
								if(mondayLabel.isDisplayed()) {
									if(tuesdayLabel.isDisplayed()) {
										if(wednesdayLabel.isDisplayed()) {
											if(thursdayLabel.isDisplayed()) {
												if(fridayLabel.isDisplayed()) {
													if(saturdayLabel.isDisplayed()) {
														if(sundayLabel.isDisplayed()) {
															if(slotDescriptionLabel.isDisplayed()) {
																if(saveButton.isDisplayed()) {
																	if(cancelButton.isDisplayed()) {
																		selectWebElement(cancelButton);
																		waitForJqueryLoad(driver);
																		if(actualItems.equals(items.getText())) {
																			status=true;
																		}
																		else {System.out.println("actualItems data Mismatch");}			
																	}
																	else {System.out.println("cancelButton is not Displayed");}
																}
																else {System.out.println("saveButton is not Displayed");}
															}
															else {System.out.println("slotDescriptionLabel is not Displayed");}
														}
														else {System.out.println("sundayLabel is not Displayed");}
													}
													else {System.out.println("saturdayLabel is not Displayed");}
												}
												else {System.out.println("fridayLabel is not Displayed");}
											}
											else {System.out.println("thursdayLabel is not Displayed");}
										}
										else {System.out.println("wednesdayLabel is not Displayed");}
									}
									else {System.out.println("tuesdayLabel is not Dispalyed");}
								}
								else {System.out.println("mondayLabel is not Displayed");}
							}
							else {System.out.println("toTimeLabel is not Displayed");}
						}
						else {System.out.println("fromTimeLabel is not Dispalyed");}
					}
					else {System.out.println("endOfWeekLabel is not Displayed");}
				}
				else {System.out.println("startofWeekLabel is not Displayed");}
			}
			else {System.out.println("slotTypeLabel is not Displayed");}
		}
		else {System.out.println("slotGroupLabel is not Displayed");}
		return status;
	}

	public String VerifyMessage() {
		//waitForJqueryLoad(driver);
		if(errorMsg.get(0).isDisplayed())
		{return errorMsg.get(0).getText();}
		else
		{return successmsg.getText();}
	}


	public void setStartOfWeek(SlotSchedulerDetails details)throws ParseException {
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(startOfWeekCalenderIcon);
		selectWebElement(startOfWeekCalenderIcon);
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String setDateStr=details.getStartOfWeek();
		System.out.println(details.getStartOfWeek()+" nnnnn");
		waitForJqueryLoad(driver);
		String currentDatestr=CurrentDate.get(0).getText();
		Date setDate=new SimpleDateFormat("yyyy-MM-dd").parse(setDateStr);
		System.out.println(currentDatestr);
		Date currDate=new SimpleDateFormat("MMMM yyyy").parse(currentDatestr);
		int monthDiff=Months.monthsBetween(new DateTime(currDate).withDayOfMonth(1), new DateTime(setDate).withDayOfMonth(1)).getMonths();
		System.out.println(monthDiff);
		boolean isFuture=true;
		if(monthDiff<0) {
			isFuture=false;
			monthDiff=-1*monthDiff; 
		}
		for(int i=0;i<monthDiff;i++) {
			if(isFuture)
				selectWebElement(NextIcon.get(0));
			else
				selectWebElement(PrevIcon.get(0));

		}
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String day;
		day=new SimpleDateFormat("dd").format(setDate);
		System.out.println(day);
		waitForJqueryLoad(driver);
		List<WebElement> runsOn= driver.findElements(By.xpath("//a[text()='"+Integer.parseInt(day)+"']"));
		int x= runsOn.size();
		if(x==4)
			runsOn.get(x-2).click();
		else
			runsOn.get(x-1).click();
	}

	public void setEndOfWeek(SlotSchedulerDetails details)throws ParseException {
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(endOfWeekCalenderIcon);
		selectWebElement(endOfWeekCalenderIcon);
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String setDateStr=details.getEndOfWeek();
		waitForJqueryLoad(driver);
		String currentDatestr=CurrentDate.get(1).getText();
		Date setDate=new SimpleDateFormat("yyyy-MM-dd").parse(setDateStr);
		System.out.println(currentDatestr);
		Date currDate=new SimpleDateFormat("MMMM yyyy").parse(currentDatestr);
		int monthDiff=Months.monthsBetween(new DateTime(currDate).withDayOfMonth(1), new DateTime(setDate).withDayOfMonth(1)).getMonths();
		System.out.println(monthDiff);
		boolean isFuture=true;
		if(monthDiff<0) {
			isFuture=false;
			monthDiff=-1*monthDiff; 
		}
		for(int i=0;i<monthDiff;i++) {
			if(isFuture)
				selectWebElement(NextIcon.get(1));
			else
				selectWebElement(PrevIcon.get(1));

		}
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String day;
		day=new SimpleDateFormat("dd").format(setDate);
		System.out.println(day);
		waitForJqueryLoad(driver);
		List<WebElement> runsOn= driver.findElements(By.xpath("//a[text()='"+Integer.parseInt(day)+"']"));
		int x= runsOn.size();
		if(x==4)
			runsOn.get(x-2).click();
		else
			runsOn.get(x-1).click();
	}

	public void VerifySlotSchedulerWithoutSlotGroup(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutSlotType(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutStartOfWeek(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		setEndOfWeek(details);
		startOfWeek.clear();
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutEndOfWeek(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutFromTime(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutToTime(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutSlotDescription(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutSlotGroupSlotTypeandSlotDescription(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		selectWebElement(saveButton);
	}

	public void VerifySlotSchedulerWithoutSlotTypeandSlotDescription(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		selectWebElement(saveButton);
	}

	public void VerifyAddSlotSchedulerWithValidData(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public boolean VerifyDefaultValueOfMondayFieldandDecimalValueRoundUp(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		String defaultValue=Monday.getAttribute("value");
		boolean status=false;
		if(defaultValue.equals("0")) {
			selectWebElement(days.get(0));
			enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
			selectWebElement(mondayLabel);
			double d=Double.parseDouble(details.getMonday());
			System.out.println(d);
			int RoundUpValue=(int) Math.round(d);
			String RoundUpValue1=Integer.toString(RoundUpValue);  
			String updatedValue=Monday.getAttribute("value");
			System.out.println("RoundUPValue Is"+RoundUpValue1);
			System.out.println("UpdatedValue Is"+updatedValue);
			if(RoundUpValue1.equals(updatedValue)) {
				status=true;
			}
			else {System.out.println("Round Up Value Data Mismatch");}

		}
		else {System.out.println("default Value Data Mismatch");}

		return status;
	}

	public boolean VerifyDefaultValueOfTuesdayFieldandDecimalValueRoundUp(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		String defaultValue=Tuesday.getAttribute("value");
		boolean status=false;
		if(defaultValue.equals("0")) {
			selectWebElement(days.get(1));
			enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
			selectWebElement(tuesdayLabel);
			double d=Double.parseDouble(details.getTuesday());
			System.out.println(d);
			int RoundUpValue=(int) Math.round(d);
			String RoundUpValue1=Integer.toString(RoundUpValue);  
			String updatedValue=Tuesday.getAttribute("value");
			System.out.println("RoundUPValue Is"+RoundUpValue1);
			System.out.println("UpdatedValue Is"+updatedValue);
			if(RoundUpValue1.equals(updatedValue)) {
				status=true;
			}
			else {System.out.println("Round Up Value Data Mismatch");}

		}
		else {System.out.println("default Value Data Mismatch");}

		return status;
	}

	public boolean VerifyDefaultValueOfWednesdayFieldandDecimalValueRoundUp(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		String defaultValue=Tuesday.getAttribute("value");
		boolean status=false;
		if(defaultValue.equals("0")) {
			selectWebElement(days.get(2));
			enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
			selectWebElement(wednesdayLabel);
			double d=Double.parseDouble(details.getWednesday());
			System.out.println(d);
			int RoundUpValue=(int) Math.round(d);
			String RoundUpValue1=Integer.toString(RoundUpValue);  
			String updatedValue=Wednesday.getAttribute("value");
			System.out.println("RoundUPValue Is"+RoundUpValue1);
			System.out.println("UpdatedValue Is"+updatedValue);
			if(RoundUpValue1.equals(updatedValue)) {
				status=true;
			}
			else {System.out.println("Round Up Value Data Mismatch");}

		}
		else {System.out.println("default Value Data Mismatch");}

		return status;
	}

	public boolean VerifyDefaultValueOfThursdayFieldandDecimalValueRoundUp(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		String defaultValue=Thursday.getAttribute("value");
		boolean status=false;
		if(defaultValue.equals("0")) {
			selectWebElement(days.get(3));
			enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
			selectWebElement(thursdayLabel);
			double d=Double.parseDouble(details.getThursady());
			System.out.println(d);
			int RoundUpValue=(int) Math.round(d);
			String RoundUpValue1=Integer.toString(RoundUpValue);  
			String updatedValue=Thursday.getAttribute("value");
			System.out.println("RoundUPValue Is"+RoundUpValue1);
			System.out.println("UpdatedValue Is"+updatedValue);
			if(RoundUpValue1.equals(updatedValue)) {
				status=true;
			}
			else {System.out.println("Round Up Value Data Mismatch");}

		}
		else {System.out.println("default Value Data Mismatch");}

		return status;
	}

	public boolean VerifyDefaultValueOfFridayFieldandDecimalValueRoundUp(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		String defaultValue=Friday.getAttribute("value");
		boolean status=false;
		if(defaultValue.equals("0")) {
			selectWebElement(days.get(4));
			enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
			selectWebElement(fridayLabel);
			double d=Double.parseDouble(details.getFriday());
			System.out.println(d);
			int RoundUpValue=(int) Math.round(d);
			String RoundUpValue1=Integer.toString(RoundUpValue);  
			String updatedValue=Friday.getAttribute("value");
			System.out.println("RoundUPValue Is"+RoundUpValue1);
			System.out.println("UpdatedValue Is"+updatedValue);
			if(RoundUpValue1.equals(updatedValue)) {
				status=true;
			}
			else {System.out.println("Round Up Value Data Mismatch");}

		}
		else {System.out.println("default Value Data Mismatch");}

		return status;
	}

	public boolean VerifyDefaultValueOfSaturdayFieldandDecimalValueRoundUp(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		String defaultValue=Saturday.getAttribute("value");
		boolean status=false;
		if(defaultValue.equals("0")) {
			selectWebElement(days.get(5));
			enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
			selectWebElement(saturdayLabel);
			double d=Double.parseDouble(details.getSatuarday());
			System.out.println(d);
			int RoundUpValue=(int) Math.round(d);
			String RoundUpValue1=Integer.toString(RoundUpValue);  
			String updatedValue=Saturday.getAttribute("value");
			System.out.println("RoundUPValue Is"+RoundUpValue1);
			System.out.println("UpdatedValue Is"+updatedValue);
			if(RoundUpValue1.equals(updatedValue)) {
				status=true;
			}
			else {System.out.println("Round Up Value Data Mismatch");}

		}
		else {System.out.println("default Value Data Mismatch");}

		return status;
	}

	public boolean VerifyDefaultValueOfSundayFieldandDecimalValueRoundUp(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		String defaultValue=Sunday.getAttribute("value");
		boolean status=false;
		if(defaultValue.equals("0")) {
			selectWebElement(days.get(6));
			enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
			selectWebElement(sundayLabel);
			double d=Double.parseDouble(details.getSunday());
			System.out.println(d);
			int RoundUpValue=(int) Math.round(d);
			String RoundUpValue1=Integer.toString(RoundUpValue);  
			String updatedValue=Sunday.getAttribute("value");
			System.out.println("RoundUPValue Is"+RoundUpValue1);
			System.out.println("UpdatedValue Is"+updatedValue);
			if(RoundUpValue1.equals(updatedValue)) {
				status=true;
			}
			else {System.out.println("Round Up Value Data Mismatch");}

		}
		else {System.out.println("default Value Data Mismatch");}

		return status;
	}

	public void VerifySlotSchedulerWithAllValidFields(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(addNewSlotSchedulerRecordButton);
		Thread.sleep(1000);
		selectWebElement(slotGroupDropDown);
		selectDropdownFromVisibleText(slotGroupDropDownValues,details.getSlotGroup());
		selectWebElement(slotTypeDropDown);
		selectDropdownFromVisibleText(slotTypeDropDownValues,details.getSlotType());
		setStartOfWeek(details);
		setEndOfWeek(details);
		selectWebElement(fromTimeIcon);
		selectDropdownFromVisibleText(fromTimevalues,details.getFromTime());
		selectWebElement(toTimeIcon);
		selectDropdownFromVisibleText(toTimevalues,details.getTotime());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Monday,details.getMonday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Tuesday,details.getTuesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Wednesday,details.getWednesday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Thursday,details.getThursady());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Friday,details.getFriday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Saturday,details.getSatuarday());
		selectWebElement(days.get(0));
		enterValueToTxtFieldWithoutClear(Sunday,details.getSunday());
		enterValueToTxtFieldWithoutClear(slotDescription,details.getSlotDescription());
		selectWebElement(saveButton);
	}

	public String verifySuccessMessage(){
		waitUntilWebElementIsVisible(successmsg);return successmsg.getText();
	}

	public void SearchRecord(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Slot Description");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is equal to");
		enterValueToTxtField(searchTextBox,details.getSlotDescription());		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
	}

	public boolean verifyGridData(List<Map<String, String>> maplist,SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		SearchRecord(details);
		List<Map<String,String>> UI=getGriddata(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getGriddata(){
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=1;j<headers.size()-2;j++) {
					scrollToElement(headers.get(j));
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public void VerifyEditSlotSchedulerWithoutModifyReason(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		SearchRecord(details);
		waitForJqueryLoad(driver);
		selectWebElement(editButton);
		Thread.sleep(1000);
		selectWebElement(days.get(0));
		Monday.sendKeys(Keys.BACK_SPACE);
		enterValueToTxtFieldWithoutClear(Monday,details.getUpdatedMonday());
		selectWebElement(days.get(2));
		Thursday.sendKeys(Keys.BACK_SPACE);
		enterValueToTxtFieldWithoutClear(Thursday,details.getUpdatedThursady());
		selectWebElement(slotDescription);
		System.out.println(details.getUpdatedSlotDescription());
		enterValueToTxtBox1(slotDescription,details.getUpdatedSlotDescription());
		selectWebElement(saveButton);
	}

	public void VerifyEditSlotSchedulerWithValidFields(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		SearchRecord(details);
		waitForJqueryLoad(driver);
		selectWebElement(editButton);
		Thread.sleep(1000);
		selectWebElement(days.get(0));
		Monday.sendKeys(Keys.BACK_SPACE);
		enterValueToTxtFieldWithoutClear(Monday,details.getUpdatedMonday());
		selectWebElement(days.get(2));
		Thursday.sendKeys(Keys.BACK_SPACE);
		enterValueToTxtFieldWithoutClear(Thursday,details.getUpdatedThursady());
		selectWebElement(slotDescription);
		System.out.println(details.getUpdatedSlotDescription());
		enterValueToTxtBox1(slotDescription,details.getUpdatedSlotDescription());
		selectWebElement(modifyReason);
		enterValueToTxtBox1(modifyReason,details.getModifyReason());
		selectWebElement(saveButton);
	}

	public boolean ExportToExcelButton(String filePath) {
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("Slot Scheduler")) {
				f.delete();
			}
		}
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath,"Slot Scheduler");
		return Status;
	}

	public boolean verifyExportToExcelSheet(List<Map<String, String>> maplist) throws Exception {
		List<Map<String,String>> UI=getdata(); 
		System.out.println(UI);
		System.out.println(maplist);
		if(UI.equals(maplist))
			return true;
		else
			return false;
	}

	private List<Map<String,String>> getdata() throws Exception{
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){
			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			String col=null;
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				for(int j=1;j<headers.size();j++) {
					scrollToElement(headers.get(j));
					Thread.sleep(100);
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.click();
				Thread.sleep(1000);
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public boolean VerifyExportToExcelWithoutData(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		SearchRecord(details);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		waitForJqueryLoad(driver);
		if(errorMsg.get(0).getText().equals("There is no record to export"))
			return true;
		else
			return false;
	}

	public void verifySortByAscending() {
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridSlotType);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifySortByDescending() {
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(auditGridContent);
		selectWebElement(gridSlotType);
		waitForJqueryLoad(driver);
		selectWebElement(gridSlotType);
		waitForJqueryLoad(driver);
		selectWebElement(exportButton);
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean DeleteCancel(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		SearchRecord(details);
		waitForJqueryLoad(driver);
		selectWebElement(deleteButton);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deleteReason,details.getModifyReason());
		selectWebElement(deleteNoButton);
		waitForJqueryLoad(driver);
		if(tablerow.getText().equals(details.getSlotType()))
		{return true;}
		else
			return false;

	}

	public void DeleteRecordWithOutDeleteReason(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		SearchRecord(details);
		waitForJqueryLoad(driver);
		selectWebElement(deleteButton);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectWebElement(deleteYesButton);
	}

	public void DeleteRecord(SlotSchedulerDetails details) throws Exception {
		waitForJqueryLoad(driver);
		SearchRecord(details);
		waitForJqueryLoad(driver);
		selectWebElement(deleteButton);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enterValueToTxtFieldWithoutClear(deleteReason,details.getModifyReason());
		selectWebElement(deleteYesButton);
	}

	public boolean groupBy() {
		DragandDrop(gridSlotType,dropTarget);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();	
		}
		System.out.println(groupBy.getText().split(":")[1]);
		System.out.println(groupBySlotType.getText());
		if(groupBy.getText().split(": ")[1].equals(groupBySlotType.getText()))
			return true;
		else
			return false;

	}

	public boolean verifyArrowMoveForPreviousAndNextPage(){
		boolean status=false;
		if(!nextPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(nextPageIcon);
			waitForJqueryLoad(driver);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(previousPageIcon);
			waitForJqueryLoad(driver);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			if(nextnumber==(pagenumber+1) && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}
	public boolean verifyArrowMoveForFirstAndLastPage(){
		boolean status=false;
		if(!lastPageIcon.getAttribute("class").contains("k-state-disabled")){
			int pagenumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(lastPageIcon);
			waitForJqueryLoad(driver);
			int nextnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			selectWebElement(firstPageIcon);
			waitForJqueryLoad(driver);
			int previousnumber=Integer.valueOf(getTextFromWebElement(pageNumber));
			if(nextnumber>pagenumber && pagenumber==previousnumber){status=true;}
		}else{
			System.out.println("previous and next page icon disabled");status=true;
		}
		return status;
	}

	public boolean verifyTotalNumberOfItemsPerPageDetails(){
		String item = items.getText();
		return item.matches("(\\d.*) - (\\d.*) of (\\d.*) items");
	}

	public boolean verifyNumberOfItemsPerPage() {
		boolean status = false;
		try {
			int item = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
			selectWebElement(pagerDropdown);
			Thread.sleep(1500);
			for (int i = 0; i < pageSizeListBox.size(); i++) {
				if(Integer.valueOf(pageSizeListBox.get(i).getText())>item){continue;}
				selectDropdownFromVisibleText(pageSizeListBox, pageSizeListBox.get(i).getText());
				waitForJqueryLoad(driver);
				int totalItems = Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
				int pagersize = Integer.valueOf(pagerSize.getText());
				int pages = (totalItems % pagersize == 0) ? item / pagersize : item / pagersize+1;
				int totalRows=(gridContent.findElements(By.tagName("tr")).size());
				selectWebElement(lastPageIcon);
				waitForJqueryLoad(driver);
				int lastPageNumber = Integer.valueOf(pageNumber.getText());
				if (item == totalItems && pages == lastPageNumber&&totalRows==pagersize) {
					status = true;
				} else {System.out.println(items+":"+totalItems+"\t"+pages+":"+lastPageNumber+"\t"+totalRows+":"+pagersize);
				status = false;
				break;
				}selectWebElement(pagerDropdown);Thread.sleep(1500);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} return status;
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

	public boolean verifycolumnsHeaderEnabled(){
		boolean status=false;
		WebElement ele= headersDropdown.get(0);
		if(ele.isDisplayed()){
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
		return status;
	}

	public boolean verifycolumnsHeaderDisabled() {
		boolean status = false;
		WebElement ele = headersDropdown.get(0);
		if (ele.isDisplayed()) {
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
		return status;
	}

	public boolean verifySearchIsnotEqualTo(String slotDescription) throws Exception {
		waitForJqueryLoad(driver);
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Slot Description", slotDescription);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Slot Description");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Is not equal to");
		enterValueToTxtField(searchTextBox,slotDescription);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchContains(String slotDescription) throws Exception {
		waitForJqueryLoad(driver);
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Slot Description", slotDescription);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Slot Description");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Contains");
		enterValueToTxtField(searchTextBox,slotDescription);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchDoesNotContains(String slotDescription) throws Exception {
		waitForJqueryLoad(driver);
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Slot Description", slotDescription);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Slot Description");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Does not Contain");
		enterValueToTxtField(searchTextBox,slotDescription);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchStartsWith(String slotDescription) throws Exception {
		waitForJqueryLoad(driver);
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Slot Description", slotDescription);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Slot Description");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Starts with");
		enterValueToTxtField(searchTextBox,slotDescription);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public boolean verifySearchEndsWith(String slotDescription) throws Exception {
		waitForJqueryLoad(driver);
		boolean Status=false;
		Map<String,String>map=new HashMap<String,String>();
		map.put("Slot Description", slotDescription);
		selectWebElement(searchBtn);
		selectWebElement(selectSearchColumn);
		selectDropdownFromVisibleText(columnNameList,"Slot Description");
		Thread.sleep(1000);
		selectWebElement(condition);
		selectDropdownFromVisibleText(searchCriteriaDropDwn,"Ends with");
		enterValueToTxtField(searchTextBox,slotDescription);		
		selectWebElement(searchSearchBtn);
		waitForJqueryLoad(driver);
		waitUntilWebElementIsVisible(gridContent);
		List<Map<String,String>> UI=gettable(); 
		for (Map<String,String> map1: UI)
		{   	
			if(map1.equals(map))
				Status= false;
			else 
				Status= true;
		}
		return Status;

	}

	public List<Map<String, String>> gettable() {
		int item=Integer.valueOf(items.getText().split("of ")[1].split(" items")[0]);
		int pagersize=Integer.valueOf(pagerSize.getText());
		int pages=(item%pagersize==0)?item/pagersize-1:item/pagersize;
		List<Map<String,String>> arr=new ArrayList<Map<String,String>>();
		for(int k=0;k<=pages;k++){

			waitUntilWebElementIsVisible(auditGridContent);
			List<WebElement> rows=auditGridContent.findElements(By.tagName("tr"));
			List<WebElement> headers = rows.get(0).findElements(By.tagName("th"));
			for(int i=1;i<rows.size();i++) {
				Map<String,String> map = new HashMap<String,String>();
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String col=null;
				for(int j=1;j<headers.size();j++){
					scrollToElement(headers.get(j));
					/*if(headers.get(j).getText().equals("Last Changed On")){
								col=cols.get(j).getText().substring(11);
								}
							else*/
					col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}

	public void NavigateToSlotOccupancyTab() {
		waitForJqueryLoad(driver);
		selectWebElement(slotOccupencyTab);
	}

	public boolean VerifyPresenceOfButtonsInSlotOccupencyTab() {
		waitForJqueryLoad(driver);
		NavigateToSlotOccupancyTab();
		boolean status=false;
		if(exportPDFButton.isEnabled()) {
			if(previousButton.isEnabled()) {
				if(NextButton.isEnabled()) {
					if(CalenderDateSelectionButton.isEnabled()) {
						if(refreshButton.isEnabled()) {
							status=true;
						}
						else {System.out.println("refreshButton is Disabled");}
					}
					else {System.out.println("CalenderDateSelectionButton is Disabled");}
				}
				else {System.out.println("NextButton is Disabled");}
			}
			else {System.out.println("previousButton is Disabled");}
		}
		else {System.out.println("exportPDFButton is Disabled");}
		return status;
	}

	public boolean VerifyPreviousButton() throws ParseException {
		waitForJqueryLoad(driver);
		NavigateToSlotOccupancyTab();
		String DateFromCurrentPage=GetDateFromCalenderField();	 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date myDate = dateFormat.parse(DateFromCurrentPage);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date newDate = calendar.getTime();
		String date = dateFormat.format(newDate);
		System.out.println(date);  
		selectWebElement(previousButton);
		waitForJqueryLoad(driver);
		String DateFromPreviousPage=GetDateFromCalenderField();	
		if(date.equals(DateFromPreviousPage))
			return true;
		else
			return false;

	}

	public String GetDateFromCalenderField() throws ParseException {
		waitForJqueryLoad(driver);
		String dateInString = calenderFieldDate.getText().split("-")[0];
		System.out.println(dateInString);
		SimpleDateFormat inputFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy",  Locale.ENGLISH);
		SimpleDateFormat outputFormat=   new SimpleDateFormat("dd-MM-yyyy");
		Date oneWayTripDate = inputFormat.parse(dateInString);
		String  datetime=    outputFormat.format(oneWayTripDate);
		System.out.println(datetime);
		return datetime;
	}
	
	public boolean VerifyNextButton() throws ParseException {
		waitForJqueryLoad(driver);
		NavigateToSlotOccupancyTab();
		String DateFromCurrentPage=GetDateFromCalenderField();	 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date myDate = dateFormat.parse(DateFromCurrentPage);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR, +7);
		Date newDate = calendar.getTime();
		String date = dateFormat.format(newDate);
		System.out.println(date);  
		selectWebElement(NextButton);
		waitForJqueryLoad(driver);
		String DateFromPreviousPage=GetDateFromCalenderField();	
		if(date.equals(DateFromPreviousPage))
			return true;
		else
			return false;

	}
	
	public boolean ExportPDFButton(String filePath) throws Exception {
		waitForJqueryLoad(driver);
		NavigateToSlotOccupancyTab();
		waitForJqueryLoad(driver);
		Thread.sleep(2000);
		final File folder=new File(filePath);
		for(final File f : folder.listFiles()) {
			if(f.getName().startsWith("SlotOccupancy")) {
				f.delete();
			}
		}
		selectWebElement(exportPDFButton);
		waitForJqueryLoad(driver);
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		Boolean Status=verifyExportPageFileDownload(filePath,"SlotOccupancy");
		return Status;
	}
}

