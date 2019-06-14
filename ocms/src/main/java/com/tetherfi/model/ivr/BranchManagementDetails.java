package com.tetherfi.model.ivr;

import java.util.Map;

public class BranchManagementDetails {
	
	private String branchName;
	private String query;
	private String mainLines;
	private String subLines;
	private String location;
	private String BranchType;
	private String Address;
	private String LineEstateOrder;
	private String Status;
	private String Language;
	private String AddressWave;
	private String BranchWave; 
	private String updateSublines;
	private String updateStatus;
	private String ModifyReason;
	private String DeleteReason;

	public BranchManagementDetails(Map<String, String> map) {
		branchName=readBranchName(map);
		query=readQuery(map);
		mainLines=readMainLines(map);
		subLines=readSubLines(map);
		location=readLocation(map);
		BranchType=readBranchType(map);
		Address=readAddress(map);
		LineEstateOrder=readLineEstateOrder(map);
		Status=readStatus(map);
		Language=readLanguage(map);
		AddressWave=readAddressWave(map);
		BranchWave=readBranchWave(map);
		updateSublines=readUpdatedSublines(map);
		updateStatus=readUpdatedStatus(map);
		ModifyReason=readModifyReason(map);
		DeleteReason=readDeleteReason(map);
		
		
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
		String value=map.get("Update Status");
		return value;
	}

	private String readUpdatedSublines(Map<String, String> map) {
		String value=map.get("Update SubLines");
		return value;
	}

	private String readBranchWave(Map<String, String> map) {
		String value=map.get("Branch Wave");
		return value;
	}

	private String readAddressWave(Map<String, String> map) {
		String value=map.get("Address Wave");
		return value;
	}

	private String readLanguage(Map<String, String> map) {
		String value=map.get("Language");
		return value;
	}

	private String readStatus(Map<String, String> map) {
		String value=map.get("Status");
		return value;
	}

	private String readLineEstateOrder(Map<String, String> map) {
		String value=map.get("Line/Estate Order");
		return value;
	}

	private String readAddress(Map<String, String> map) {
		String value=map.get("Address");
		return value;
	}

	private String readBranchType(Map<String, String> map) {
		String value=map.get("BranchType");
		return value;
	}

	private String readLocation(Map<String, String> map) {
		String value=map.get("Location");
		return value;
	}

	private String readSubLines(Map<String, String> map) {
		String value=map.get("SubLines");
		return value;
	}

	private String readMainLines(Map<String, String> map) {
		String value=map.get("MainLines");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readBranchName(Map<String, String> map) {
        String value=map.get("BranchName");
		return value;
	}
	
	public String getBranchName() {
		return branchName;
	}

	public String getQuery() {
		return query;
	}

	public String getMainLines() {
		return mainLines;
	}

	public String getSubLines() {
		return subLines;
	}

	public String getLocation() {
		return location;
	}

	public String getBranchType() {
		return BranchType;
	}

	public String getAddress() {
		return Address;
	}

	public String getLineEstateOrder() {
		return LineEstateOrder;
	}

	public String getStatus() {
		return Status;
	}

	public String getLanguage() {
		return Language;
	}

	public String getAddressWave() {
		return AddressWave;
	}

	public String getBranchWave() {
		return BranchWave;
	}

	public String getUpdatedSubLines() {
		return updateSublines;
	}

	public String getUpdatedStatus() {
		return updateStatus; 
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

}
