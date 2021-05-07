package com.tetherfi.model.user;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class IWRoleBasedAccessMatrixDetails {

	private String roleName;
	private String roleType;
	private String nodeId;
	private String nodeDescription;
	private String makerDNIS;
	private String checkerDNIS;
	private String callFlowName;
	private String voiceTalnet;
	private String newCallFlowName;
	private String modifyReason;
	private String importingCallFlowName;
	private String importCallFlowFile;
	
	
	
	public IWRoleBasedAccessMatrixDetails(Map<String,String> map){
		roleName=readRoleName(map);
		roleType=readRoleType(map);
		nodeId=readNodeID(map);
		nodeDescription=readNodeDescription(map);
		makerDNIS=readmakerDNIS(map);
		checkerDNIS=readCheckerDNIS(map);
		callFlowName=readCallFlowName(map);
		voiceTalnet=readVoiceTalnet(map);
		newCallFlowName=readNewCallFlowName(map);
		modifyReason=readModifyReason(map);
		importCallFlowFile=readImportCallflow(map);
		importingCallFlowName=readImportingCallFlowname(map);
    }



	private String readImportingCallFlowname(Map<String, String> map) {
		String value=map.get("Importing CallFlow Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readImportCallflow(Map<String, String> map) {
		String value=map.get("Import CallFlow File");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readModifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readNewCallFlowName(Map<String, String> map) {
		String value=map.get("New Call Flow Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readVoiceTalnet(Map<String, String> map) {
		String value=map.get("Voice Talnet");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readCallFlowName(Map<String, String> map) {
		String value=map.get("Call Flow Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readCheckerDNIS(Map<String, String> map) {
		String value=map.get("Checke DNIS");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readmakerDNIS(Map<String, String> map) {
		String value=map.get("Maker DNIS");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readNodeDescription(Map<String, String> map) {
		String value=map.get("Node Description");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
	}



	private String readNodeID(Map<String, String> map) {
		String value=map.get("Node ID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
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
	    
	    public String getNodeID() {
	        return nodeId;
	    }
	    
	    public String getNodeDescription() {
	        return nodeDescription;
	    }
	    
	    public String getMakerDNIS() {
	        return makerDNIS;
	    }
	    
	    public String getCheckerDNIS() {
	        return checkerDNIS;
	    }
	    
	    public String getCallFlowName() {
	        return callFlowName;
	    }
	    
	    public String getVoiceTalNet() {
	        return voiceTalnet;
	    }
	    
	    public String getNewCallFlowName() {
	    	return newCallFlowName;
	    }
	    
	    public String getModifyReason() {
	    	return modifyReason;
	    }
	    
	    public String getImportCallFlowFile() {
	    	return importCallFlowFile;
	    }
	    
	    public String getImportingCallFlowName() {
	    	return importingCallFlowName;
	    }
}
