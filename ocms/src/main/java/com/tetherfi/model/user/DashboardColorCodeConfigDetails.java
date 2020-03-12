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
    private String updatedbackgroundColor;
    private String errorMsg1;
    private String errorMsg2;
    private String errorMsg3;
    private String errorMsg4;

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
        updatedbackgroundColor=readUpdatedBackGroundColor(map);
        errorMsg1=readErrorMsg1(map);
        errorMsg2=readErrorMsg2(map);
        errorMsg3=readErrorMsg3(map);
        errorMsg4=readErrorMsg4(map);
        query=readQuery(map);
    }

    private String readErrorMsg4(Map<String, String> map) {
    	String value=map.get("ErrorMsg4");
		return value;
	}

	private String readErrorMsg3(Map<String, String> map) {
		String value=map.get("ErrorMsg3");
		return value;
	}

	private String readErrorMsg2(Map<String, String> map) {
		String value=map.get("ErrorMsg2");
		return value;
	}

	private String readErrorMsg1(Map<String, String> map) {
		String value=map.get("ErrorMsg1");
		return value;
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
    
     private String readUpdatedBackGroundColor(Map<String, String>map) {
    	 String value=map.get("Updated Background Color");
 		return value;
     }
     
	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedColorCode(Map<String, String> map) {
        String value=map.get("Updated Background Color");
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
	
	public String getUpdatedBackGroundColor() {
		return updatedbackgroundColor;
	}

	public String getdashboardName() {
		return dashboardName;		
	}
	
	public String getcolumnName() {
		return columnName;		
	}
	 public String getFontcolor() {
	        return fontcolor;
	    }

	public String getErrorMsg1() {
		return errorMsg1;
	}

	public String getErrorMsg2() {
		return errorMsg2;
	}

	public String getErrorMsg3() {
		return errorMsg3;
	}

	public String getErrorMsg4() {
		return errorMsg4;
	}

}
