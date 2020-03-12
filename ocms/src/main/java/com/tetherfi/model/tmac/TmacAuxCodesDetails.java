package com.tetherfi.model.tmac;

import java.util.Map;

public class TmacAuxCodesDetails {
	private String value;
	private String name;
	private String code;
	private String status;
	private String updatedName;
	private String updatedStatus;
	private String modifyReason;
	private String deleteReason;
	
	public TmacAuxCodesDetails(Map<String,String>map) {
		
		value=readValue(map);
		name=readName(map);
		code=readCode(map);
		status=readStatus(map);
		updatedName=readUpdatedName(map);
		updatedStatus=readUpdatedStatus(map);
		modifyReason=readModifyReason(map);
		deleteReason=readDeleteReason(map);
		
		
		
		
	}

	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}

	private String readModifyReason(Map<String, String> map) {
       String value=map.get("Modify Reason");
		return value;
	}

	private String readUpdatedStatus(Map<String, String> map) {
		String value=map.get("Updated Status");
		return value;
	}

	private String readUpdatedName(Map<String, String> map) {
		String value=map.get("Updated Name");
		return value;
	}

	private String readStatus(Map<String, String> map) {
		String value=map.get("Status");
		return value;
	}

	private String readCode(Map<String, String> map) {
		String value=map.get("Code");
		return value;
	}

	private String readName(Map<String, String> map) {
		String value=map.get("Name");
		return value;
	}

	private String readValue(Map<String, String> map) {
		String value=map.get("Value");
		return value;
	}

	public String getStatus() {return status;}
	public String getName()   {return name;}
	public String getCode()   {return code;}
	public String getValue()  {return value;}
	public String getUpdatedName() {return updatedName;}
	public String getUpdatedStatus() {return updatedStatus;}
	public String getModifyReason() {return modifyReason;}
	public String getDeleteReason() {return deleteReason;}
	
}
