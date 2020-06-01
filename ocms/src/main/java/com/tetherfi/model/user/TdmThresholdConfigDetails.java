package com.tetherfi.model.user;

import java.util.Map;

public class TdmThresholdConfigDetails {
	private String TeamName;
	private String Query;
	private String AuxCodeFrom;
	private String AuxCodeTo;
	private String Threshold;
	private String modifyReason;
	private String updatedAuxCodeForm;
	private String deleteAuxCodeForm;
	private String TeamName1;
	private String TeamName2;
	private String updatedThreshold;
	

	public TdmThresholdConfigDetails(Map<String, String> map) {
		TeamName=readTeamName(map);
		Query=readQuery(map);
		AuxCodeFrom=readAuxCodeFrom(map);
		AuxCodeTo=readAuxCodeTo(map);
		Threshold=readThreshold(map);
		modifyReason=readModifyReason(map);
		updatedAuxCodeForm=readUpdatedAuxCodeForm(map);
		deleteAuxCodeForm=readDeleteUpdatedAuxCodeForm(map);
		TeamName1=readTeamName1(map);
		TeamName2=readTeamName2(map);
		updatedThreshold=readUpdatedThreshold(map);
		
	}

	private String readUpdatedThreshold(Map<String, String> map) {
		String Value=map.get("Updated Threshold");
		return Value;
	}

	private String readTeamName2(Map<String, String> map) {
		String Value=map.get("Team Name2");
		return Value;
	}

	private String readTeamName1(Map<String, String> map) {
		String Value=map.get("Team Name1");
		return Value;
	}

	private String readDeleteUpdatedAuxCodeForm(Map<String, String> map) {
		String Value=map.get("Delete Aux Code Form");
		return Value;
	}

	private String readUpdatedAuxCodeForm(Map<String, String> map) {
		String Value=map.get("Updated Aux Code Form");
		return Value;
	}

	private String readModifyReason(Map<String, String> map) {
		String Value=map.get("Modify Reason");
		return Value;
	}

	private String readThreshold(Map<String, String> map) {
		String Value=map.get("Threshold");
		return Value;
	}

	private String readAuxCodeFrom(Map<String, String> map) {
		String Value=map.get("Aux Code From");
		return Value;
	}

	private String readAuxCodeTo(Map<String, String> map) {
		String Value=map.get("Aux Code To");
		return Value;
	}

	private String readQuery(Map<String, String> map) {
		String Value=map.get("Query");
		return Value;
	}

	private String readTeamName(Map<String, String> map) {
		String Value=map.get("Team Name");
		return Value;
	}

	public String getTeamName() {
		return TeamName;
	}

	public String getAuxCodeFrom() {
		return AuxCodeFrom;
	}

	public String getAuxCodeTo() {
		return AuxCodeTo;
	}

	public String getThreshold() {
		return Threshold;
	}

	public String getQuery() {
		return Query;
	}

	public String getModifyReason() {
		return modifyReason;
	}
	
	public String getUpdatedAuxCodeForm() {
		return updatedAuxCodeForm;
	}
	
	public String getDeleteAuxCodeForm() {
		return deleteAuxCodeForm;
	}
	
	public String getTeamName1() {
		return TeamName1;
	}
	
	public String getTeamName2() {
		return TeamName2;
	}
	
	public String getUpdatedThreshold() {
		return updatedThreshold;
	}
	

}
