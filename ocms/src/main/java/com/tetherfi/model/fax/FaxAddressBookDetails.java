package com.tetherfi.model.fax;

import java.util.Map;

public class FaxAddressBookDetails {
	private String firstName;
	private String lastName;
	private String Number;
	private String updatedFirstName;
	private String ModifyReason;
	private String DeleteReason;
	private String Name;
	private String Faxline;
	private String Recipients;
	private String Recipient1;
	private String Recipient;
	private String UpdatedName;
	private String UpdatedRecipients;
	String query;
	

	public FaxAddressBookDetails(Map<String, String> map) {
		firstName=readFirstName(map);
		lastName=readLastName(map);
		Number=readNumber(map);
		updatedFirstName=readUpdatedFirstName(map);
		ModifyReason=readModifyReason(map);
		DeleteReason=readDeleteReason(map);
		Name=readName(map);
		Faxline=readFaxline(map);
		Recipients=readRecipients(map);
		Recipient1=readRecipients1(map);
		Recipient=readRecipient(map);
		UpdatedName=readUpdatedName(map);
		UpdatedRecipients=readUpdatedRecipients(map);
		query=readQuery(map);
	}

	private String readUpdatedRecipients(Map<String, String> map) {
		String Value=map.get("Updated Recipients");
		return Value;
	}
	
	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedName(Map<String, String> map) {
		String Value=map.get("Updated Name");
		return Value;
	}

	private String readRecipients(Map<String, String> map) {
		String Value=map.get("Recipients");
		return Value;
	}

	private String readRecipients1(Map<String, String> map) {
		String Value=map.get("Recipient1");
		return Value;
	}

	private String readRecipient(Map<String, String> map) {
		String Value=map.get("Recipient");
		return Value;
	}

	private String readFaxline(Map<String, String> map) {
		String Value=map.get("FaxLine");
		return Value;
	}

	private String readName(Map<String, String> map) {
		String Value=map.get("Name");
		return Value;
	}

	private String readDeleteReason(Map<String, String> map) {
		String Value=map.get("Delete Reason");
		return Value;
	}

	private String readModifyReason(Map<String, String> map) {
		String Value=map.get("Modify Reason");
		return Value;
	}

	private String readUpdatedFirstName(Map<String, String> map) {
		String Value=map.get("Updated First Name");
		return Value;
	}

	private String readNumber(Map<String, String> map) {
		String Value=map.get("Fax Number");
				return Value;
	}

	private String readLastName(Map<String, String> map) {
		String Value=map.get("Name2");
		return Value;
	}

	private String readFirstName(Map<String, String> map) {
		String Value=map.get("Name1");
		return Value;
	}

	public String getfirstName() {
		return firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public String getNumber() {
		return Number;
	}

	public String getUpdatedFirstName() {
		return updatedFirstName;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

	public String getName() {
		return Name;
	}
	
	public String getFaxLine() {
		return Faxline;
	}

	public String getRecipient() {
		return Recipient;
	}

	public String getRecipient1() {
		return Recipient1;
	}

	public String getRecipients() {
		return Recipients;
	}

	public String getUpdatedName() {
		return UpdatedName;
	}

	public String getUpdatedRecipients() {
		return UpdatedRecipients;
	}
	
	public String getQuery() {
		return query;
	}


}
