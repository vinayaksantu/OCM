package com.tetherfi.model.user;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class IWMasterAccessMatrixDetails {

	private String roleName;
	private String roleType;
	
	
	
	public IWMasterAccessMatrixDetails(Map<String,String> map){
		roleName=readRoleName(map);
		roleType=readRoleType(map);
      
    }



	private String readRoleType(Map<String, String> map) {
		String value=map.get("RoleType");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readRoleName(Map<String, String> map) {
		String value=map.get("RoleName");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}
	
	 public String getRoleName() {
	        return roleName;
	    }

	    public String getRoleType() {
	        return roleType;
	    }
}
