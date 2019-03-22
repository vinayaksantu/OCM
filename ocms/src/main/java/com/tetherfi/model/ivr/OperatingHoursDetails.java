package com.tetherfi.model.ivr;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class OperatingHoursDetails {
    private String vdn;
    private String weekDay;
    private String startTime;
    private String endTime;
    private String updateVdnName;
    private String modifyReason;
    private String deleteReason;
    private String query;
    private String bypassPublicHoliday;
    private String weekDay2;
    public OperatingHoursDetails(Map<String,String> map){
        vdn=readVdn(map);
        weekDay=readWeekDay(map);
        startTime=readStartTime(map);
        endTime=readEndTime(map);
        query=readQuery(map);
        bypassPublicHoliday=readBypassPublicHoliday(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        weekDay2=readWeekDay2(map);
    }

    private String readWeekDay2(Map<String, String> map) {
		String value=map.get("Week day 2");
		return value;
	}

	private String readBypassPublicHoliday(Map<String, String> map) {
		String value=map.get("Bypass Public Holiday");
		return value;
	}

	private String readQuery(Map<String, String> map) {
    	String value=map.get("Query");
    			return value;
	}

	public String readVdn(Map<String,String> map){
        String value=map.get("VDN");
        return value;
    }
    public String readWeekDay(Map<String,String> map){
        String value=map.get("Week day");
        return value;
    }

    public String readStartTime(Map<String,String> map){
        String value=map.get("start time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readEndTime(Map<String,String> map){
        String value=map.get("end time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readUpdatedVdn(Map<String,String> map){
        String value=map.get("Updated vdn Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("delete reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getVdnName() {
        return vdn;
    }
    public String getWeekDay(){return weekDay;}
    public String getStartTime(){return startTime;}
    public String getEndTime(){return endTime;}


    public String getUpdateVdnName(){return updateVdnName;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}

	public String getQuery() {
		return query;
	}

	public String getBypassPublicHoliday() {
		return bypassPublicHoliday;
	}

	public String getWeekDay2() {
		return weekDay2;
	}

}


