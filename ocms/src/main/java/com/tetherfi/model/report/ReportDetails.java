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

    

    public ReportDetails(Map<String, String> map){
        reportChannel=readReportChannel(map);
        reportName=readReportname(map);
        reportName1=readReportName1(map);
        reportType=readReportType(map);
        reportDate=readReportDate(map);
        query=readQuery(map);
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
//            c.add(Calendar.DATE,-1);
            SimpleDateFormat ft =new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
            value=ft.format(c.getTime());
        }
        return value;
    }
    
    private String readEndDate(Map<String, String> map) throws ParseException {
        String value=map.get("End Date");
        if(value==null||value.equalsIgnoreCase("random.str")){
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date=new Date();
            value= dateFormat.format(date);
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
    private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

    public String getReportChannel() {
        return reportChannel;
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
}
