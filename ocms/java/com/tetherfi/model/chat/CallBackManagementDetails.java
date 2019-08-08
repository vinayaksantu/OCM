package com.tetherfi.model.chat;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;
import java.util.Date;

public class CallBackManagementDetails {
    private String startDate;
    private String status;
    private String endDate;
    public CallBackManagementDetails(Map<String,String> map){
        startDate=readStartDate(map);
        status=readStatus(map);
        endDate=readEndDate(map);

    }
    public String readStartDate(Map<String,String> map){
        String value=map.get("Start Date");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readStatus(Map<String,String> map){
        String value=map.get("status");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readEndDate(Map<String,String> map){
        String value=map.get("end date");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public String getStatus(){ return status;}
}
