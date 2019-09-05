package com.tetherfi.model.user;

import java.util.Map;

public class TdmThresholdConfigDetails {
	private String TeamName;
	private String Query;
	private String AuxCodeFrom;
	private String AuxCodeTo;
	private String Threshold;
	private String modifyReason;

	public TdmThresholdConfigDetails(Map<String, String> map) {
		TeamName=readTeamName(map);
		Query=readQuery(map);
		AuxCodeFrom=readAuxCodeFrom(map);
		AuxCodeTo=readAuxCodeTo(map);
		Threshold=readThreshold(map);
		modifyReason=readModifyReason(map);
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

}
