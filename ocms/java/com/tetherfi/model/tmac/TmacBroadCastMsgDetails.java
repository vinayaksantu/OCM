package com.tetherfi.model.tmac;


import java.util.Map;

public class TmacBroadCastMsgDetails {
    private String teamName;
    private String level;
    private String country;
    private String division;
    private String department;
    private String updatedMessage;
    private String modifyReason;
    private String deleteReason;
    private String message;
    private String status;
    private String header;
    private String query;
    private String updatedStatus;
    public  TmacBroadCastMsgDetails(Map<String,String> map){
        teamName=readTeamName(map);
        level=readLevel(map);
        country=readCountry(map);
        division=readDivision(map);
        department=readDepartment(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        message=readMessage(map);
        updatedMessage=readUpdatedMessage(map);
        status=readStatus(map);
        header=readHeader(map);
        query=readQuery(map);
        updatedStatus=readUpdatedStatus(map);
    }
    private String readUpdatedStatus(Map<String, String> map) {
    	String value=map.get("Updated Status");
		return value;
	}
	private String readQuery(Map<String, String> map) {
    	String value=map.get("Query");
		return value;
	}
	private String readHeader(Map<String, String> map) {
    	String value=map.get("Header");
		return value;
	}
	private String readUpdatedMessage(Map<String, String> map) {
		String value=map.get("Updated Message");
		return value;
	}
	private String readLevel(Map<String, String> map) {
 	   String value=map.get("Level");
        return value;
 	}
 private String readCountry(Map<String, String> map) {
 	String value=map.get("Country");
     return value;
 	}
 private String readDivision(Map<String, String> map) {
 	String value=map.get("Division");
     return value;
 	}
 private String readDepartment(Map<String, String> map) {
 	String value=map.get("Department");
     return value;
 	}
    private String readTeamName(Map<String, String> map) {
        String value=map.get("Team Name");
        return value;}

    public String readStatus(Map<String,String> map){
        String value=map.get("Status");
        return value;
    }
    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        return value;
    }

    public String readMessage(Map<String,String> map){
        String value=map.get("Message");
        return value;
    }
    public String getStatus(){return status;}
    public String getModifyReason(){return modifyReason;}
    public String getMessage(){return message;}

    public String getTeamName() {
        return teamName;
    }

    public String getDeleteReason() {
        return deleteReason;
    }
    public String getLevel() {return level;}
    public String getCountry() {return country;}
    public String getDivision() {return division;}
    public String getDepartment() {return department;}
    public String getUpdatedMessage() {return updatedMessage;}
	public String getHeader() {return header;}
	public String getQuery() {return query;
	}
	public String getUpdatedStatus() {
		return updatedStatus;
	}

   
	}
