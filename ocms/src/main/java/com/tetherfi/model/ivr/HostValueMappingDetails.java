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

}
