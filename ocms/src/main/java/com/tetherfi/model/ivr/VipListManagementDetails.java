package com.tetherfi.model.ivr;

import java.util.Map;

public class VipListManagementDetails {
	private String callerID;
	private String emailID;
	private String fbHandle;
	private String messengerID;
	private String customerIDType;
	private String customerIDNo;
	private String country;
	private String contactType;
	private String inclusionFlag;
	private String exclusionFlag;
	private String otherData;
	private String updatedFBHandle;
	private String modifyReason;
	private String deleteReason;
	private String query;

	public VipListManagementDetails(Map<String, String> map) {
		callerID=readCallerID(map);
		emailID=readEmailID(map);
		fbHandle=readFbHandle(map);
		messengerID=readMessengerID(map);
		customerIDType=readCustomerIDType(map);
		customerIDNo=readCustomerIDNo(map);
		country=readCountry(map);
		contactType=readContactType(map);
		inclusionFlag=readInclusionFlag(map);
		exclusionFlag=readExclusionFlag(map);
		otherData=readOtherData(map);
		updatedFBHandle=readUpdatedFBHandle(map);
		modifyReason=readModifyReason(map);
		deleteReason=readDeleteReason(map);
		query=readQuery(map);
	}


	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}


	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}


	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}


	private String readUpdatedFBHandle(Map<String, String> map) {
		String value=map.get("Updated FBHandle");
		return value;
	}


	private String readOtherData(Map<String, String> map) {
		String value=map.get("Other Data");
		return value;
	}

	private String readExclusionFlag(Map<String, String> map) {
		String value=map.get("Exclusion Flag");
		return value;
	}

	private String readInclusionFlag(Map<String, String> map) {
		String value=map.get("Inclusion Flag");
		return value;
	}

	private String readContactType(Map<String, String> map) {
		String value=map.get("Contact Type");
		return value;
	}

	private String readCountry(Map<String, String> map) {
		String value=map.get("Country");
		return value;
	}

	private String readCustomerIDNo(Map<String, String> map) {
		String value=map.get("CustomerID No");
		return value;
	}

	private String readCustomerIDType(Map<String, String> map) {
		String value=map.get("CustomerID Type");
		return value;
	}

	private String readMessengerID(Map<String, String> map) {
		String value=map.get("Messenger ID");
		return value;
	}

	private String readFbHandle(Map<String, String> map) {
		String value=map.get("FB Handle");
		return value;
	}

	private String readEmailID(Map<String, String> map) {
		String value=map.get("Email ID");
		return value;
	}

	private String readCallerID(Map<String, String> map) {
		String value=map.get("Caller ID");
		return value;
	}

	public String getCallerID() {
		return callerID;
	}

	public String getEmailID() {
		return emailID;
	}

	public String getFBHandle() {
		return fbHandle;
	}

	public String getMessengerID() {
		return messengerID;
	}

	public String getCustomerIDType() {
		return customerIDType;
	}

	public String getCustomerIDNo() {
		return customerIDNo;
	}

	public String getCountry() {
		return country;
	}

	public String getContactType() {
		return contactType;
	}

	public String getExclusionFlag() {
		return exclusionFlag;
	}

	public String getInclusionFlag() {
		return inclusionFlag;
	}

	public String getOtherData() {
		return otherData;
	}


	public String getUpdatedFBHandle() {
		return updatedFBHandle;
	}


	public String getModifyReason() {
		return modifyReason;
	}


	public String getDeleteReason() {
		return deleteReason;
	}


	public String getQuery() {
		return query;
	}

}
