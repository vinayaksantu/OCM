package com.tetherfi.model.ivr;

import java.util.Map;

public class HostValueMappingDetails {
	private String query;
	private String hostData;
	private String functionality;
	private String language;
	private String status;
	private String description;
	private String Wavefile;
	private String UpdatedDescription;
	private String ModifyReason;
	private String DeleteReason;
	private String errorMsg1;
	private String errorMsg2;
	private String errorMsg3;
	private String errorMsg4;
	private String errorMsg5;
	private String errorMsg6;
	private String errorMsg7;
	
	public HostValueMappingDetails(Map<String, String> map) {
		query=readQuery(map);
		hostData=readHostData(map);
		functionality=readFunctionality(map);
		language=readLanguage(map);
		status=readStatus(map);
		description=readDescription(map);
		Wavefile=readWaveFile(map);
		UpdatedDescription=readUpdatedDescription(map);
		ModifyReason=readModifyReason(map);
		DeleteReason=readDeleteReason(map);
		errorMsg1=readErrorMsg1(map);
		errorMsg2=readErrorMsg2(map);
		errorMsg3=readErrorMsg3(map);
		errorMsg4=readErrorMsg4(map);
		errorMsg5=readErrorMsg5(map);
		errorMsg6=readErrorMsg6(map);
		errorMsg7=readErrorMsg7(map);

		
		
	}

	private String readErrorMsg7(Map<String, String> map) {
		String value=map.get("ErrorMsg7");
		return value;
	}

	private String readErrorMsg6(Map<String, String> map) {
		String value=map.get("ErrorMsg6");
		return value;
	}

	private String readErrorMsg5(Map<String, String> map) {
		String value=map.get("ErrorMsg5");
		return value;
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

	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}

	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}

	private String readUpdatedDescription(Map<String, String> map) {
		String value=map.get("Updated Description");
		return value;
	}

	private String readWaveFile(Map<String, String> map) {
		String value=map.get("WaveFile");
		return value;
	}

	private String readDescription(Map<String, String> map) {
		String value=map.get("Description");
		return value;
	}

	private String readStatus(Map<String, String> map) {
		String value=map.get("Status");
		return value;
	}

	private String readLanguage(Map<String, String> map) {
		String value=map.get("Language");
		return value;
	}

	private String readFunctionality(Map<String, String> map) {
		String value=map.get("Functionality");
		return value;
	}

	private String readHostData(Map<String, String> map) {
		String value=map.get("Host Data");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	public String getQuery() {
		return query;
	}

	public String getHostData() {
		return hostData;
	}

	public String getFunctionality() {
		return functionality;
	}

	public String getLanguage() {
		return language;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public String getWaveFile() {
		return Wavefile;
	}

	public String getUpdatedDescription() {
		return UpdatedDescription;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

	public String getErrorMsg6() {
		return errorMsg6;

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

	public String getErrorMsg5() {
		return errorMsg5;
	}

	public String getErrorMsg7() {
		return errorMsg7;
	}

}
