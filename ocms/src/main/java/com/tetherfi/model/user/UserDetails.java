package com.tetherfi.model.user;



import java.util.Map;

public class UserDetails {
    private String userId;
    private String roleName;
    private String updateRoleName;
    private String modifyReason;
    private String deleteReason;
    private String updatedUserId;
    private String query;

    public UserDetails(Map<String,String> map){
        userId=readUserId(map);
        roleName=readRoleName(map);
        updateRoleName=readUpdatedRoleName(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        updatedUserId=readUpdatedUserId(map);
        query=readquery(map);
    }
    private String readquery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}
	private String readUpdatedUserId(Map<String, String> map) {
    	String value=map.get("Updated User ID");
        return value;
	}
	public String readUserId(Map<String,String> map){
        String value=map.get("User ID");
        return value;
    }
    public String readRoleName(Map<String,String> map){
        String value=map.get("Role Name");
        return value;
    }
    public String readUpdatedRoleName(Map<String,String> map){
        String value=map.get("Updated Role Name");
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
    public String getUserId(){return userId;}
    public String getRoleName(){return roleName;}
    public String getUpdateRoleName(){return updateRoleName;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}
	public String getUpdatedUserId(){return updatedUserId;}
	public String getQuery() { return query;
	}
}
