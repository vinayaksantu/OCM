package com.tetherfi.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tetherfi.model.report.ReportDetails;

public class OCMViolationReportPage extends BasePage{

	public OCMViolationReportPage(WebDriver driver) {
		super(driver);}

	@FindBy(css="a[aria-label='Go to the next page']")
	private WebElement nextPageIcon;
	
	@FindBy(id="grid")
	private WebElement auditGridContent;
	
	@FindBy(xpath="//span[@class='k-pager-info k-label']")
	private WebElement items;
	
	@FindBy(css=".k-pager-sizes .k-input")
	private WebElement pagerSize;
	
	
	public boolean verifyDatabase(String query,ReportDetails details) throws InterruptedException {
		//get dates from xl - step 2
		String reportbeforedate = details.getStartDate();
		String reportafterdate=details.getEndDate();
		//change date formats - step 3
		reportbeforedate=reportbeforedate.substring(6,10)+"-"+reportbeforedate.substring(3, 5)+"-"+reportbeforedate.substring(0, 2)+" "+reportbeforedate.substring(11, 13)+":"+reportbeforedate.substring(14, 16)+":"+reportbeforedate.substring(17, 19);
		reportafterdate=reportafterdate.substring(6,10)+"-"+reportafterdate.substring(3, 5)+"-"+reportafterdate.substring(0, 2)+" "+reportafterdate.substring(11, 13)+":"+reportafterdate.substring(14, 16)+":"+reportafterdate.substring(17, 19);
		//Replace identifiers in query to formatted date - step 5
		query=query.replaceAll("ReportBeforeDate",reportbeforedate );
		query=query.replaceAll("ReportAfterDate",reportafterdate );
		List<Map<String,String>> database=database(query);
		//		System.out.println("Printing Query" +" "+query);		
		//		System.out.println("Printing DB results" +" "+database);
		List<Map<String,String>> UI=getDataTable1(); 
		//		System.out.println("Printing UI Results"+" "+UI);	
		if(UI.equals(database))
			return true;
		else
			return false;
	}
	
	private List<Map<String, String>> getDataTable1() throws InterruptedException {
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
				for(int j=0;j<headers.size();j++){
					scrollToElement(headers.get(j));
					if(headers.get(j).getText().equals("")){
						col=cols.get(j).getText().substring(0);
					}
					else
						col=cols.get(j).getText();
					map.put(headers.get(j).getText(),col);
				}
				map.remove("");
				arr.add(map);
			}
			if(k!=pages)
			{
				Thread.sleep(10000);
				nextPageIcon.click();
				waitForJqueryLoad(driver);}
		}
		return arr;
	}
	
	
	
	
}
