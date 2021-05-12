package com.tetherfi.model.user;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class UserOnBoardingDetails {

	private String avayaLoginID;
    private String lanId;
    private String firstname;
    private String lastname;
    private String teamName;
    private String profile;
    private String supervisor;
    private String accessRole;
    private String features;
    private String crmName;
    private String OrgUnit;
    private String textTemplateName;
    private String totalVoiceTabs;
    private String totalChatTabs;
    private String totalEmailTabs;
    private String totalAudioChatTabs;
    private String totalVideoChatTabs;
    private String totalFaxTabs;
    private String totalSMSTabs;
    private String totalFaxoutTabs;
    private String totalFaxInternationalTabs;
    private String[] featurestobeSeleted;
    private boolean gotoACWaftereachACDcalls;
    private boolean autoanswerallACDcalls;
    private boolean gotoACWafteranycalls;
    private boolean cRMEnabled;
    private boolean holdVoiceCallOnChatCall;
    private boolean secondTextChatAutoAnswer;
    private boolean textChatAutoACWEnabled;
    private boolean textChatAutoAnswer;
    private String modifyReason;
    private  String deleteReason;
    private String UpdatedFirstName;
    private String profilePicture;
    private String query;
    private String getFilenameToImport;
    private String getattribute;
    private String insertedRecordCount;
    private String invalidRecordCount;
    private String userDetailsFileName;
    private String userDetailsFileNamewithLanIDBlank;
    private String userDetailsFileNamewithFirstNameBlank;
    private String userDetailsFileNamewithLastNameBlank;
    private String userDetailsFileNamewithAvayaLoginIDBlank;
    private String userDetailsFileNamewithOrgUnitBlank;
    private String userDetailsFileNamewithProfileBlank;
    private String userDetailsFileNamewithSupervisorLanIDBlank;
    private String userChannelTemplateFileName;
    private String userFeatureTemplateFileName;
    private String duplicateRecordCount;
    private String userDetailsNonMandatoryFieldsBlank;
    private String userDetailsAccessRoleandRoleFieldsBlank;
    private String userDetailsCRMandTextChatFieldsBlank;
    private String countryLevelSupervisorFileName;
    private String divisionLevelSupervisorFileName;
    private String departmentLevelSupervisorFileName;
    private String teamLevelSupervisorFileName;
    private String userDetailswithInvalidOrgUnit;
    private String userDetailswithNewColumn;
    private String userDetailswithUpdatedColumn;
    
    
    public UserOnBoardingDetails(Map<String,String> map){
    	OrgUnit=readOrgUnit(map);
    	UpdatedFirstName=readUpdatedFirstName(map);
        avayaLoginID=readAvayaLoginID(map);
        lanId=readLanID(map);
        firstname=readFirstname(map);
        lastname=readLastname(map);
        teamName=readTeamName(map);
        profile=readProfile(map);
        accessRole=readAccessRole(map);
        features=readFeatures(map);
        crmName=readCRMname(map);
        textTemplateName=readTextTemplateName(map);
        totalFaxoutTabs=readTotalFaxoutTabs(map);
        totalFaxInternationalTabs=readTotalFaxInternationalTabs(map);
        totalAudioChatTabs=readTotalAudioChatTabs(map);
        totalVideoChatTabs=readTotalVideoChatTabs(map);
        totalSMSTabs=readTotalSMSTabs(map);
        totalFaxTabs=readTotalFaxTabs(map);
        totalVoiceTabs=readTotalVoiceTabs(map);
        totalChatTabs=readTotalChatTabs(map);
        totalEmailTabs=readTotalEmailTabs(map);
        featurestobeSeleted=readFeaturesToBeSelected(map);
        gotoACWafteranycalls=readACWafteranycalls(map);
        gotoACWaftereachACDcalls=readGotoACWaftereachACDcalls(map);
        autoanswerallACDcalls=readAutoanswerallACDcalls(map);
        cRMEnabled=readcRMEnabled(map);
        holdVoiceCallOnChatCall=readHoldVoiceCallOnChatCall(map);
        secondTextChatAutoAnswer=readSecondTextChatAutoAnswer(map);
        textChatAutoACWEnabled=readTextChatAutoACWEnabled(map);
        textChatAutoAnswer=readTextChatAutoAnswer(map);
        supervisor=readSupervisor(map);
        modifyReason=readModifyReason(map);
        deleteReason=readDeleteReason(map);
        profilePicture=readProfilePicture(map);
        query=readQuery(map);
        getFilenameToImport=readFilename(map);
        getattribute=readAttribute(map);
        insertedRecordCount=readInsertedRecordCount(map);
        invalidRecordCount=readInvalidRecordCount(map);
        userDetailsFileName=readUserDetailsFileName(map);
        userDetailsFileNamewithLanIDBlank=readUserDetailsFileNamewithLanIDBlank(map);
        userDetailsFileNamewithFirstNameBlank=readUserDetailsFileNamewithFirstNameBlank(map);
        userDetailsFileNamewithLastNameBlank=readUserDetailsFileNamewithLastNameBlank(map);
        userDetailsFileNamewithAvayaLoginIDBlank=readUserDetailsFileNamewithAvayaLoginIDBlank(map);
        userDetailsFileNamewithOrgUnitBlank=readUserDetailsFileNamewithOrgUnitBlank(map);
        userDetailsFileNamewithProfileBlank=readUserDetailsFileNamewithProfileBlank(map);
        userDetailsFileNamewithSupervisorLanIDBlank=readUserDetailsFileNamewithSupervisorLanIDBlank(map);
        userChannelTemplateFileName=readUserChannelTemplateFileName(map);
        userFeatureTemplateFileName=readUserFeatureTemplateFileName(map);
        duplicateRecordCount=readDuplicateRecordCount(map);
        userDetailsNonMandatoryFieldsBlank=readUserDetailsNonMandatoryFieldsBlankFileName(map);
        userDetailsAccessRoleandRoleFieldsBlank=readUserDetailsAccessRoleandRoleFieldsBlankFileName(map);
        userDetailsCRMandTextChatFieldsBlank=readUserDetailsCRMandTextChatFieldsBlankFileName(map);
        countryLevelSupervisorFileName=readCountryLevelSupervisorFileName(map);
        divisionLevelSupervisorFileName=readDivisionLevelSupervisorFileName(map);
        departmentLevelSupervisorFileName=readDepartmentLevelSupervisorFileName(map);
        teamLevelSupervisorFileName=readTeamLevelSupervisorFileName(map);
        userDetailswithInvalidOrgUnit=readUserDetailswithInvalidOrgUnit(map);
        userDetailswithNewColumn=readUserDetailswithNewColumn(map);
        userDetailswithUpdatedColumn=readUserDetailswithUpdatedColumn(map);
        
    }
    
    private String readUserDetailswithUpdatedColumn(Map<String, String> map) {
    	String value=map.get("Updated Column");
		return value;
	}

	private String readUserDetailswithNewColumn(Map<String, String> map) {
		String value=map.get("New Column");
		return value;
	}

	private String readUserDetailswithInvalidOrgUnit(Map<String, String> map) {
    	String value=map.get("Invalid Org Unit");
		return value;
	}

	private String readTeamLevelSupervisorFileName(Map<String, String> map) {
    	String value=map.get("Team Level Supervisor");
		return value;
	}

	private String readDepartmentLevelSupervisorFileName(Map<String, String> map) {
		String value=map.get("Department Level Supervisor");
		return value;
	}

	private String readDivisionLevelSupervisorFileName(Map<String, String> map) {
		String value=map.get("Division Level Supervisor");
		return value;
	}

	private String readCountryLevelSupervisorFileName(Map<String, String> map) {
		String value=map.get("Country Level Supervisor");
		return value;
	}

	private String readUserDetailsCRMandTextChatFieldsBlankFileName(Map<String, String> map) {
		String value=map.get("CRM and TextChat Fields Blank");
		return value;
	}

	private String readUserDetailsAccessRoleandRoleFieldsBlankFileName(Map<String, String> map) {
		String value=map.get("AccessRole and Role Fields Blank");
		return value;
	}

	private String readUserDetailsNonMandatoryFieldsBlankFileName(Map<String, String> map) {
		String value=map.get("Non Mandatory Fields Blank");
		return value;
	}

	private String readUserDetailsFileNamewithSupervisorLanIDBlank(Map<String, String> map) {
    	String value=map.get("UserDeatils FileName with Supervisor LanID Blank");
		return value;
	}

	private String readUserDetailsFileNamewithOrgUnitBlank(Map<String, String> map) {
		String value=map.get("UserDeatils FileName with OrgUnit Blank");
		return value;
	}

	private String readUserDetailsFileNamewithProfileBlank(Map<String, String> map) {
		String value=map.get("UserDeatils FileName with Profile Blank");
		return value;
	}

	private String readUserDetailsFileNamewithAvayaLoginIDBlank(Map<String, String> map) {
		String value=map.get("UserDeatils FileName with Avaya Login ID Blank");
		return value;
	}

	private String readUserDetailsFileNamewithLastNameBlank(Map<String, String> map) {
		String value=map.get("UserDeatils FileName with Last Name Blank");
		return value;
	}

	private String readUserDetailsFileNamewithFirstNameBlank(Map<String, String> map) {
		String value=map.get("UserDeatils FileName with First Name Blank");
		return value;
	}

	private String readUserDetailsFileNamewithLanIDBlank(Map<String, String> map) {
		String value=map.get("UserDeatils FileName LanID Blank");
		return value;
	}

	private String readDuplicateRecordCount(Map<String, String> map) {
    	String value=map.get("Duplicate Record Count");
		return value;
	}

	private String readUserFeatureTemplateFileName(Map<String, String> map) {
    	String value=map.get("FeatureDeatails FileName");
		return value;
	}

	private String readUserChannelTemplateFileName(Map<String, String> map) {
		String value=map.get("ChannelCount FileName");
		return value;
	}

	private String readUserDetailsFileName(Map<String, String> map) {
		String value=map.get("UserDeatils FileName");
		return value;
	}

	private String readInvalidRecordCount(Map<String, String> map) {
    	String value=map.get("Invalid Record Count");
		return value;
	}

	private String readInsertedRecordCount(Map<String, String> map) {
    	String value=map.get("Inserted Record Count");
		return value;
	}

	private String readAttribute(Map<String, String> map) {
    	String value=map.get("Attribute");
		return value;
	}

    private String readFilename(Map<String, String> map) {
    	String value=map.get("Filename");
		return value;
	}

    private String readProfilePicture(Map<String, String> map) {
    	String value=map.get("Profile Picture");
		return value;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readOrgUnit(Map<String, String> map) {
    	String value=map.get("OrgUnit");
		return value;
	}

	private String readUpdatedFirstName(Map<String, String> map) {
        String value=map.get("Updated FirstName");
		return value;
	}

	private String readTotalFaxTabs(Map<String, String> map) {
        String value=map.get("Total Fax Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }

    private String readTotalSMSTabs(Map<String, String> map) {
        String value=map.get("Total SMS Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }

    private String readTotalVideoChatTabs(Map<String, String> map) {
        String value=map.get("Total Video Chat Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;}

    private String readTotalAudioChatTabs(Map<String, String> map) {
        String value=map.get("Total Audio Chat Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }

    private boolean readTextChatAutoAnswer(Map<String, String> map) {
        String value=map.get("Text Chat Auto Answer");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
    }

    private boolean readTextChatAutoACWEnabled(Map<String, String> map) {
        String value=map.get("Text Chat Auto ACW Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
    }

    private boolean readSecondTextChatAutoAnswer(Map<String, String> map) {
        String value=map.get("Second Text Chat Auto Answer");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
    }

    private boolean readHoldVoiceCallOnChatCall(Map<String, String> map) {
        String value=map.get("Hold Voice Call On Chat Call");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);}

    private boolean readcRMEnabled(Map<String, String> map) {
        String value=map.get("CRM Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);}

    private boolean readAutoanswerallACDcalls(Map<String, String> map) {
        String value=map.get("Auto answer all ACD calls");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);}
    private boolean readGotoACWaftereachACDcalls(Map<String, String> map) {
        String value=map.get("Go to ACW after each ACD calls");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);}

    private boolean readACWafteranycalls(Map<String, String> map) {
        String value=map.get("Go to ACW after any calls");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
    }

    private String[] readFeaturesToBeSelected(Map<String, String> map) {
        String value=map.get("Features To Be Selected");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value.split(",");
    }

    private String readTotalEmailTabs(Map<String, String> map) {
        String value=map.get("Total Email Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }

    private String readTotalChatTabs(Map<String, String> map) {
        String value=map.get("Total Chat Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }

    private String readTotalVoiceTabs(Map<String, String> map) {
        String value=map.get("Total Voice Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }

    private String readTotalFaxoutTabs(Map<String, String> map) {
        String value=map.get("Total Faxout Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }
    private String readTotalFaxInternationalTabs(Map<String, String> map) {
        String value=map.get("Total FaxInternational Tabs Allowed");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="0";}
        return value;
    }

    private String readTextTemplateName(Map<String, String> map) {
        String value=map.get("Text Template Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readCRMname(Map<String, String> map) {
        String value=map.get("CRM Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomNumeric(1);
        }
        return value;
    }

    private String readFeatures(Map<String, String> map) {
        String value=map.get("Features");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readAccessRole(Map<String, String> map) {
        String value=map.get("Access Role");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readLastname(Map<String, String> map) {
        String value=map.get("Last Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readFirstname(Map<String, String> map) {
        String value=map.get("First Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    private String readLanID(Map<String, String> map) {
        String value=map.get("Lan ID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readAvayaLoginID(Map<String,String> map){
        String value=map.get("Avaya Login ID");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readTeamName(Map<String,String> map){
        String value=map.get("Team Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readProfile(Map<String,String> map){
        String value=map.get("Profile");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readSupervisor(Map<String,String> map){
        String value=map.get("Supervisor Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readModifyReason(Map<String,String> map){
        String value=map.get("Modify Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String readDeleteReason(Map<String,String> map){
        String value=map.get("Delete Reason");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }
    public String getAvayaLoginID() {return avayaLoginID;}
    public String getProfile() {return profile;}
    public String getSupervisor() {return supervisor;}
    public String getTeamName() {return teamName;}
    public String getModifyReason(){return modifyReason;}
    public String getDeleteReason(){return deleteReason;}
    public String getLanID(){return lanId;}
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}

    public String getAccessRole() {
        return accessRole;
    }

    public String getFeatures() {
        return features;
    }

    public String getCrmName() {
        return crmName;
    }

    public String getTextTemplateName() {
        return textTemplateName;
    }

    public String  getTotalVoiceTabs() {
        return totalVoiceTabs;
    }

    public String getTotalChatTabs() {
        return totalChatTabs;
    }

    public String getTotalEmailTabs() {
        return totalEmailTabs;
    }

    public String[] getFeaturestobeSeleted() {
        return featurestobeSeleted;
    }

    public boolean isGotoACWaftereachACDcalls() {
        return gotoACWaftereachACDcalls;
    }

    public boolean isAutoanswerallACDcalls() {
        return autoanswerallACDcalls;
    }

    public boolean isGotoACWafteranycalls() {
        return gotoACWafteranycalls;
    }

    public boolean iscRMEnabled() {
        return cRMEnabled;
    }

    public boolean isHoldVoiceCallOnChatCall() {
        return holdVoiceCallOnChatCall;
    }

    public boolean isSecondTextChatAutoAnswer() {
        return secondTextChatAutoAnswer;
    }

    public boolean isTextChatAutoACWEnabled() {
        return textChatAutoACWEnabled;
    }

    public boolean isTextChatAutoAnswer() {
        return textChatAutoAnswer;
    }

    public String getTotalAudioChatTabs() {
        return totalAudioChatTabs;
    }

    public String getTotalVideoChatTabs() {
        return totalVideoChatTabs;
    }

    public String getTotalFaxTabs() {
        return totalFaxTabs;
    }

    public String getTotalSMSTabs() {
        return totalSMSTabs;
    }

    public String getTotalFaxoutTabs() { return totalFaxoutTabs; }

    public String getTotalFaxInternationalTabs() { return totalFaxInternationalTabs; }

	public String getUpdatedFirstname() {
		return UpdatedFirstName;
	}

	public Object getOrgUnit() {
		return OrgUnit;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	
	public String getattribute() {
    	return getattribute;
    }
    
    public String getfilename() {
    	return getFilenameToImport;
    }
    
    public String getInsertedRecordCount() {
    	return insertedRecordCount;
    }
    
    public String getInavlidRecordCount() {
    	return invalidRecordCount;
    }
    
    public String getUserDetailsFileName() {
    	return userDetailsFileName;
    }
    
    public String getUserDetailsFileNameWithLanIDBlank() {
    	return userDetailsFileNamewithLanIDBlank;
    }
    
    public String getUserDetailsFileNamewithFirstNameBlank() {
    	return userDetailsFileNamewithFirstNameBlank;
    }
    
    public String getUserDetailsFileNamewithLastNameBlank() {
    	return userDetailsFileNamewithLastNameBlank;
    }
    
    public String getUserDetailsFileNameAvayaLoginIdBlank() {
    	return userDetailsFileNamewithAvayaLoginIDBlank;
    }
    
    public String getUserDetailsFileNamewithProfileBlank() {
    	return userDetailsFileNamewithOrgUnitBlank;
    }
    
    public String getUserDetailsFileNamewithOrgUnitBlank() {
    	return userDetailsFileNamewithProfileBlank;
    }
    
    public String getUserDetailsFileNamewithSupervisorLanIDBlank() {
    	return userDetailsFileNamewithSupervisorLanIDBlank;
    }
    
    public String getChannelDetailsFileName() {
    	return userChannelTemplateFileName;
    }
    
    public String getFeatureDetailsFileName() {
    	return userFeatureTemplateFileName;
    }
    
    public String getDuplicateRecordCount() {
    	return duplicateRecordCount;
    }
    
    public String getUserDetailsNonMandatoryFieldsBlankFileName() {
    	return userDetailsNonMandatoryFieldsBlank;
    }
    
    public String getUserDetailsCRMandTexChatFieldsBlankFileName() {
    	return userDetailsCRMandTextChatFieldsBlank;
    }
    
    public String getUserDetailsAccessRoleandRoleFieldsBlankFileName() {
    	return userDetailsAccessRoleandRoleFieldsBlank;
    }
    
    public String getCountryLevelSupervisorFileName() {
    	return countryLevelSupervisorFileName;
    }
    
    public String getDivisionLevelSupervisorFileName() {
    	return divisionLevelSupervisorFileName;
    }
    
    public String getDepartmentLevelSupervisorFileName() {
    	return departmentLevelSupervisorFileName;
    }
    
    public String getTeamlevelSupervisorFileName() {
    	return teamLevelSupervisorFileName;
    }
    
    public String getUserDetailswithInavlidOrgUnitFileName() {
    	return userDetailswithInvalidOrgUnit;
    }
    
    public String getUserDetailswithNewColumnFileName() {
    	return userDetailswithNewColumn;
    }
    
    public String getUserDetailswithUpdatedColumnFileName() {
    	return userDetailswithUpdatedColumn;
    }
    
    
    
    
    
    
    
    
    
}
