package com.tetherfi.model.ivr;

import java.util.Map;

public class CallbackAnnouncementDetails {
	private String Wavfile;
	private String StartTime;
	private String EndTime;
	private String Language;
	private String UpdatedStartTime;
	private String UpdatedEndTime;
	private String ModifyReason;
	private String DeleteReason;
	private String query;
	
	public CallbackAnnouncementDetails(Map<String, String> map) {
		Wavfile=readWavFile(map);
		StartTime=readStartTime(map);
		EndTime=readEndTime(map);
		Language=readLanguage(map);
		UpdatedStartTime=readUpdatedStartTime(map);
		UpdatedEndTime=readUpdatedEndTime(map);
		ModifyReason=readModifyReason(map);
		DeleteReason=readDeleteReason(map);
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

	private String readUpdatedEndTime(Map<String, String> map) {
		String value=map.get("Updated EndTime");
		return value;
	}

	private String readUpdatedStartTime(Map<String, String> map) {
		String value=map.get("Updated StartTime");
		return value;
	}

	private String readLanguage(Map<String, String> map) {
		String value=map.get("Language");
		return value;
	}

	private String readEndTime(Map<String, String> map) {
		String value=map.get("End Time");
		return value;
	}

	private String readStartTime(Map<String, String> map) {
		String value=map.get("Start Time");
		return value;
	}

	private String readWavFile(Map<String, String> map) {
		String value=map.get("Wav File");
		return value;
	}

	public String getWavFile() {
		return Wavfile;
	}

	public String getStartTime() {
		return StartTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public String getLanguage() {
		return Language;
	}

	public String getUpdatedStartTime() {
		return UpdatedStartTime;
	}

	public String getUpdatedEndTime() {
		return UpdatedEndTime;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

	public String getQuery() {
		return query;
	}

}
