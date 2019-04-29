package com.tetherfi.model.ivr;

import java.util.Map;

public class FaxApplicationFormDetails {
	private String Functionality;
	private String PDFFile;
	private String WavFile;
	private String Status;
	private String Language;
	private String ModifyReason;
	private String DeleteReason;
	private String UpdatedStatus;
	private String query;

	public FaxApplicationFormDetails(Map<String, String> map) {
		Functionality=readFunctionality(map);
		PDFFile=readPDFFile(map);
		WavFile=readWavFile(map);
		Status=readStatus(map);
		Language=readLanguage(map);
		ModifyReason=readModifyReason(map);
		DeleteReason=readDeleteReason(map);
		UpdatedStatus=readUpdatedStatus(map);
		query=readQuery(map);
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readUpdatedStatus(Map<String, String> map) {
		String value=map.get("Updated Status");
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

	private String readLanguage(Map<String, String> map) {
		String value=map.get("Language");
		return value;
	}

	private String readStatus(Map<String, String> map) {
		String value=map.get("Status");
		return value;
	}

	private String readWavFile(Map<String, String> map) {
		String value=map.get("Wave File");
		return value;
	}

	private String readPDFFile(Map<String, String> map) {
		String value=map.get("PDF File");
		return value;
	}

	private String readFunctionality(Map<String, String> map) {
		String value=map.get("Functionality");
		return value;
	}

	public String getFunctionality() {
		return Functionality;
	}

	public String getPDFFile() {
		return PDFFile;
	}

	public String getWavFile() {
		return WavFile;
	}

	public String getStatus() {
		return Status;
	}

	public String getLanguage() {
		return Language;
	}

	public String getModifyReason() {
		return ModifyReason;
	}

	public String getUpdatedStatus() {
		return UpdatedStatus;
	}

	public String getDeleteReason() {
		return DeleteReason;
	}

	public String getQuery() {
		return query;
	}

}
