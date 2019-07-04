package com.tetherfi.model.user;

import java.util.Map;

public class DeskManagerDetails {
	private String TeamName;

	public DeskManagerDetails(Map<String, String> map) {
		TeamName=readTeamName(map);
	}

	private String readTeamName(Map<String, String> map) {
		String Value=map.get("Team Name");
		return Value;
	}

	public String getTeamName() {
		return TeamName;
	}

	public String getAuxCodeFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAuxCodeTo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getThreshold() {
		// TODO Auto-generated method stub
		return null;
	}

}
