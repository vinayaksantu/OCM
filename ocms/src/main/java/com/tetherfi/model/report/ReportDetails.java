package com.tetherfi.model.report;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ReportDetails {

    private String reportChannel;
    private String reportChannel1;
    private String reportName;
    private String reportType;
    private String reportDate;
    private String startDate;
    private String endDate;
    private String reportHeaders;
    private String advancedsearch;
    private String colname;
    private String coltype;
    private String searchStr;
    private String reportName1;
    private String query;
    private String getNumber;
    private String getdateType;
    private String queryDrillGridOne;
    private String queryDrillGridTwo;
    private String filterDate;
    private String interval;
    private String searchStr1;
    private String searchStr2;
    private String getDeleteReason;
    private String faxline;
    private String orgUnitID;
    
    public ReportDetails(Map<String, String> map){
        reportChannel=readReportChannel(map);
        reportChannel1=readReportChannel1(map);
        reportName=readReportname(map);
        reportName1=readReportName1(map);
        reportType=readReportType(map);
        reportDate=readReportDate(map);
        query=readQuery(map);
        queryDrillGridOne=readQueryDrillGridOne(map);
        queryDrillGridTwo=readQueryDrillGridTwo(map);
        try {
            startDate=readStartDate(map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate=readEndDate(map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reportHeaders=readReportHeaders(map);
        advancedsearch=readAdvancedSearch(map);
        colname=readColname(map);
        coltype=readColType(map);
        searchStr=readSearchStr(map);
        getNumber=readGetNumber(map);
        getdateType=readCalendarType(map);
        filterDate=readFilterDate(map);
        interval=readInterval(map);
        searchStr1=readSearchStr1(map);
        searchStr2=readSearchStr2(map);
        getDeleteReason=readDeleteReason(map);
        faxline=readFaxline(map);
        orgUnitID=readOrgUnitID(map);
    }
    
    private String readFaxline(Map<String, String> map) {
    	String value=map.get("Fax Line");
    	return value;
	}

	private String readDeleteReason(Map<String, String> map) {
    	String value=map.get("DeleteReason");
    	return value;
    }

    private String readSearchStr1(Map<String, String> map) {
        String value=map.get("Search String1");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="Null";
        }
        return value;
    }
	private String readSearchStr2(Map<String, String> map) {
        String value=map.get("Search String2");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="Null";
        }
        return value;
    }

    private String readInterval(Map<String, String> map) {
    	String value=map.get("Interval");
    	return value;
	}

	private String readFilterDate(Map<String, String> map) {
    	String value=map.get("Filter Date");
    	return value;
	}

	private String readCalendarType(Map<String, String> map) {
    	String value=map.get("GetCalendarType");
    	return value;
    }
    private String readGetNumber(Map<String, String> map) {
    	String value=map.get("GetNumber");
    	return value;
    }
    
    private String readReportName1(Map<String, String> map) {
    	String value=map.get("Report Name1");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}
	private String readSearchStr(Map<String, String> map) {
        String value=map.get("Search String");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="Null";
        }
        return value;
    }
    private String readColname(Map<String, String> map) {
        String value=map.get("Column Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="Null";
        }
        return value;
    }

    private String readColType(Map<String, String> map) {
        String value=map.get("Column Type");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="No";
        }
        return value;
    }
    private String readAdvancedSearch(Map<String, String> map) {
        String value=map.get("Advanced Search");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="No";
        }
        return value;
    }
    private String readReportHeaders(Map<String, String> map) {
        String value=map.get("Report Headers");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    
    private String readStartDate(Map<String, String> map) throws ParseException {
        String value=map.get("Start Date");
        if(value==null||value.equalsIgnoreCase("random.str")){
            Date dNow = new Date( );
            Calendar c = Calendar.getInstance();
            c.setTime(dNow);
            c.add(Calendar.MONTH,-1);//sub 6 months
            SimpleDateFormat ft =new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
            value=ft.format(c.getTime());
        }
        return value;
    }
    
    private String readEndDate(Map<String, String> map) throws ParseException {
        String value=map.get("End Date");
        if(value==null||value.equalsIgnoreCase("random.str")){
        	Date dNow = new Date( );
            Calendar c = Calendar.getInstance();
            c.setTime(dNow);
            c.add(Calendar.DATE, 0);//sub 6 months
            SimpleDateFormat ft =new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
            value=ft.format(c.getTime());
            /*DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date=new Date();
            value= dateFormat.format(date);*/
        }
        return value;
    }
    private String readReportDate(Map<String, String> map) {
        String value=map.get("Report Date");
        if(value==null||value.equalsIgnoreCase("random.str")){
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date=new Date();
            value= dateFormat.format(date);
            }
        return value;
    }
    private String readReportType(Map<String, String> map) {
        String value=map.get("Report Type");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readReportname(Map<String, String> map) {
        String value=map.get("Report Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readReportChannel(Map<String, String> map) {
        String value=map.get("Report Channel");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readReportChannel1(Map<String, String> map) {
        String value=map.get("Report Channel1");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}
    
    private String readQueryDrillGridOne(Map<String, String> map) {
		String value=map.get("QueryDrillGridOne");
		return value;
	}

    private String readQueryDrillGridTwo(Map<String, String> map) {
		String value=map.get("QueryDrillGridTwo");
		return value;
	}
    
    public String readOrgUnitID(Map<String,String> map){
        String value=map.get("OrgUnitID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    
    public String getReportChannel() {
        return reportChannel;
    }
    
    public String getReportChannel1() {
        return reportChannel1;
    }

    public String getReportName() {
        return reportName;
    }

    public String getReportType() {
        return reportType;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getReportHeaders() {
        return reportHeaders;
    }

    public String getAdvancedsearch() {
        return advancedsearch;
    }

    public String getColname() {
        return colname;
    }

    public String getColtype() {
        return coltype;
    }

    public String getSearchStr() {
        return searchStr;
    }
	public String getReportName1() {
		return reportName1;
	}
	public String getQuery() {
		return query;
	}
	
	public String getNumber() {
		return getNumber;
	}
	
	public String getCalendarType() {
		return getdateType;
	}
	public String getQueryDrillGridOne() {
		return queryDrillGridOne;
	}
	public String getQueryDrillGridTwo() {
		return queryDrillGridTwo;

	}

	public String getFilterDate() {
		return filterDate;
	}

	public String getInterval() {
		return interval;
	}
	
	public String getSearchStr1() {
        return searchStr1;
    }
    public String getSearchStr2() {
        return searchStr2;
    }
    
    public String getDeleteReason() {
		return getDeleteReason;
	}

	public String getFaxLine() {
		return faxline;
	}

	public String getOrgUnitID() {
		return orgUnitID;
	}

}
