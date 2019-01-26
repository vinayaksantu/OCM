package com.tetherfi.model.tmac;

import java.util.Map;

public class WorkCodeListDetails {
	private String teamName;
    private String level;
    private String country;
    private String division;
    private String department;
    private String workLevel;
    private String name;
    private String workgroup;
    private String updatedName;
    private String modifyReason;
    private String deleteReason;

	public WorkCodeListDetails(Map<String, String> map) {
		teamName=readTeamName(map);
        level=readLevel(map);
        country=readCountry(map);
        division=readDivision(map);
        department=readDepartment(map);
        workLevel=readworkLevel(map);
        workgroup=readworkGroup(map);
        updatedName=readupdatedName(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        name=readName(map);
       
	}

	private String readupdatedName(Map<String, String> map) {
		String value=map.get("Updated Name");
		return value;
	}

	private String readworkGroup(Map<String, String> map) {
		String value=map.get("WorkGroup");
		return value;
	}

	private String readName(Map<String, String> map) {
		String value=map.get("Name");
		return value;
	}

	private String readworkLevel(Map<String, String> map) {
		String value=map.get("Work Level");
		return value;
	}

	private String readTeamName(Map<String, String> map) {
		String value=map.get("Team Name");
        return value;
	}

	private String readLevel(Map<String, String> map) {
		String value=map.get("Level");
		return value;
	}

	private String readCountry(Map<String, String> map) {
		String value=map.get("Country");
		return value;
	}

	private String readDivision(Map<String, String> map) {
		String value=map.get("Division");
		return value;
	}

	private String readDepartment(Map<String, String> map) {
		String value=map.get("Department");
		return value;
	}

	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}

	private String readDeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}

	public String getLevel() {
		return level;
	}

	public String getCountry() {
		return country;
	}

	public String getDivision() {
		return division;
	}

	public String getDepartment() {
		return department;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getWorkLevel() {
		return workLevel;
	}

	public String getName() {
		return name;
	}

	public String getWorkGroup() {
		return workgroup;
	}

	public String getUpdatedName() {
		return updatedName;
	}
	
	public String getModifyReason() {
		return modifyReason;
	}
	
	public String getDeleteReason() {
		return deleteReason;
	}

	public Object getQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}
