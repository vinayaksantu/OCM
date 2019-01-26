package com.tetherfi.model.chat;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ChatTemplateDetails {
    private String departmentName;
    private String deptEnabled;
    private String groupName;
    private String groupEnabled;
    private String name;
    private String updatedname;
    private String enabled;
    private String text;
    private String intent;
	private String startTime;
    private String endTime;
    private String modifyReason;
    private  String deleteReason;

    public ChatTemplateDetails(Map<String,String> map){
        departmentName=readDepartmentName(map);
        deptEnabled=readDeptEnabled(map);
        groupName=readGroupName(map);
        groupEnabled=readGroupEnabled(map);
        name=readName(map);
        updatedname=readUpdatedname(map);
        enabled=readEnabled(map);
        text=readText(map);
        intent=readIntent(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
		try {
            startTime=readStartTime(map);
            endTime=readEndTime(map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String readStartTime(Map<String, String> map) throws ParseException {
        String value=map.get("Start Time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date=new Date();
            value= dateFormat.format(date);
        }
        return value;
    }
    private String readEndTime(Map<String, String> map) throws ParseException {
        String value=map.get("End Time");
        if(value==null||value.equalsIgnoreCase("random.str")){
            Date dNow = new Date( );
            Calendar c = Calendar.getInstance();
            c.setTime(dNow);
            c.add(Calendar.HOUR,1);//add 1 hour
            SimpleDateFormat ft =new SimpleDateFormat ("HH:mm:ss");
            value=ft.format(c.getTime());
        }
        return value;
    }
	
	private String readUpdatedname(Map<String, String> map) {
        String value=map.get("Updated Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    private String readDepartmentName(Map<String, String> map) {
        String value=map.get("Department Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    private String readDeptEnabled(Map<String, String> map) {
        String value=map.get("Department Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="NO";
        }
        return value;
    }

    private String readGroupName(Map<String, String> map) {
        String value=map.get("Group Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    private String readGroupEnabled(Map<String, String> map) {
        String value=map.get("Group Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="NO";
        }
        return value;
    }

    private String readName(Map<String, String> map) {
        String value=map.get("Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    private String readEnabled(Map<String, String> map) {
        String value=map.get("Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="No";
        }
        return value;
    }

    private String readText(Map<String, String> map) {
        String value=map.get("Text");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    private String readIntent(Map<String, String> map) {
        String value=map.get("Intent");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
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

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDeptEnabled() {
        return deptEnabled;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupEnabled() {
        return groupEnabled;
    }

    public String getName() {
        return name;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getText() {
        return text;
    }

    public String getIntent() {
        return intent;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getUpdatedname() {
        return updatedname;
    }
	
	public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}









