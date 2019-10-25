package com.tetherfi.model.tmac;
import java.util.Map;

public class AgentTeamMgmtDetails {
    private String teamName;
    private String level;
    private String country;
    private String division;
    private String department;
    private String updateTeamName;
    private String modifyReason;
    private String deleteReason;
	private String query;
	private String DisplayHierarchy;
	private String UpdatedDisplayHierarchy;
	

    public AgentTeamMgmtDetails(Map<String,String> map){
        teamName=readTeamName(map);
        level=readLevel(map);
        country=readCountry(map);
        division=readDivision(map);
        department=readDepartment(map);
        updateTeamName=readUpdatedTeamName(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        query=readQuery(map);
        DisplayHierarchy=readDisplayHiearchy(map);
        UpdatedDisplayHierarchy=readUpdatedDisplayHierarchy(map);
        
	}

	private String readUpdatedDisplayHierarchy(Map<String, String> map) {
		String value=map.get("Updated Display Hierarchy");
		return value;
	}

	private String readDisplayHiearchy(Map<String, String> map) {
		String value=map.get("Display Hierarchy");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
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
   private String readTeamName(Map<String,String> map){
       String value=map.get("Team Name");
       return value;
   }

    public String readUpdatedTeamName(Map<String,String> map){
        String value=map.get("Updated Team Name");
        return value;
    }
    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        return value;
    }

    public String getTeamName() {
        return teamName;
    }
    public String getLevel() {return level;}
    public String getCountry() {return country;}
    public String getDivision() {return division;}
    public String getDepartment() {return department;}
    public String getUpdateTeamName(){return updateTeamName;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}
	public String getQuery() {return query;}

	public String getDisplayHierarchy() {
		return DisplayHierarchy;
	}

	public String getUpdatedDisplayHierarchy() {
		return UpdatedDisplayHierarchy;
	}
	
	

}
