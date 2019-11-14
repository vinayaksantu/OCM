package com.tetherfi.model.user;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class DashboardColorCodeConfigDetails {

    private String startRange;
    private String endRange;
    private String dashboardName;
    private String columnName;
    private String backgroundColor;
    private String fontcolor;
    private String updatedColor;
    private String modifyReason;
    private  String deleteReason;
    private String query;
    private String updatedStartRange;
    private String updatedEndrange;

    public DashboardColorCodeConfigDetails(Map<String,String> map){
       dashboardName=readdashboardName(map);
       columnName=readColumnName(map);
    	startRange=readStartRange(map);
        endRange=readEndRange(map);
        backgroundColor=readColorCode(map);
        fontcolor=readfontColorCode(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        updatedColor=readUpdatedColorCode(map);
        updatedStartRange=readUpdatedStartRange(map);
        updatedEndrange=readUpdatedEndRange(map);
        
        query=readQuery(map);
    }

    private String readColumnName(Map<String, String> map) {
    	String value=map.get("Column Name");
		return value;
	}

	private String readdashboardName(Map<String, String> map) {
    	String value=map.get("Dashboard Name");
		return value;
	}
	
	private String readStartRange(Map<String, String> map) {
        String value=map.get("Start Range");
        return value;
    }
	
	private String readEndRange(Map<String, String> map) {
        String value=map.get("End Range");
        return value;
    }

	private String readUpdatedStartRange(Map<String, String> map) {
    	String value=map.get("Updated Start Range");
		return value;
	}
    
     private String readUpdatedEndRange(Map<String, String> map) {
    	String value=map.get("Updated End Range");
		return value;
	}
    
	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedColorCode(Map<String, String> map) {
        String value=map.get("Updated Background Color ");
        return value;
    }
    
	private String readColorCode(Map<String, String> map) {
        String value=map.get("Background Color");
        //if(value==null||value.equalsIgnoreCase("random.str")){
           // value= RandomStringUtils.randomNumeric(6);
       // }
        return value;
    }
	
	private String readfontColorCode(Map<String, String> map) {
        String value=map.get("Font Color");
       // if(value==null||value.equalsIgnoreCase("random.str")){
      //      value= RandomStringUtils.randomNumeric(6);
       // }
        return value;
    }

       
    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
      //  if(value==null||value.equalsIgnoreCase("random.str")){
           // value= RandomStringUtils.randomAlphabetic(10);
       // }
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getStartRange() {
        return startRange;
    }

    public String getEndRange() {
        return endRange;
    }

    public String getColorcode() {
        return backgroundColor;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public String getUpdatedColorCode() {
        return updatedColor;
    }

	public String getQuery() {
		return query;
	}

	public String getUpdatedStartRange() {
		return updatedStartRange;
	}
	
	public String getUpdatedEndRange() {
		return updatedEndrange;
	}

	public String getdashboardName() {
		return dashboardName;		
	}
	
	public String getcolumnName() {
		return columnName;		
	}
}
