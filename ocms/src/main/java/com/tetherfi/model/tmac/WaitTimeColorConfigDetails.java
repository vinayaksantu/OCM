package com.tetherfi.model.tmac;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class WaitTimeColorConfigDetails {

    private String startTime;
    private String endTime;
    private String colorcode;
    private String updatedColorCode;
    private String modifyReason;
    private  String deleteReason;
    private String query;

    public WaitTimeColorConfigDetails(Map<String,String> map){
        startTime=readStarttime(map);
        endTime=readEndtime(map);
        colorcode=readColorCode(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        updatedColorCode=readUpdatedColorCode(map);
        query=readQuery(map);
    }

    private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedColorCode(Map<String, String> map) {
        String value=map.get("Updated Color Code");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readColorCode(Map<String, String> map) {
        String value=map.get("Color Code");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readEndtime(Map<String, String> map) {
        String value=map.get("End Time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }

    private String readStarttime(Map<String, String> map) {
        String value=map.get("Start Time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(6);
        }
        return value;
    }
    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getColorcode() {
        return colorcode;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getUpdatedColorCode() {
        return updatedColorCode;
    }

	public String getQuery() {
		return query;
	}
}
