package com.tetherfi.model.tmac;

import java.util.Map;

public class AgentTemplateDetails {

	private String templateName;
	private String screenName;
	private String OrgUnit;	
	private String themeOptions;
	private String updatedTheme;
	private String modifyReason;
	private String deleteReason;
	private String screenList;
	private String updatedScreenList;
	private String auxcode;
	private String updatedAuxcode;
	private String widgetList;
	private String updatedWidgetList;
	private String templateId;
	private String weekday;
	private String fromTime;
	private String toTime;
	private boolean enableScreenCapture;
	private boolean tRSCameraCaptureEnabled;
	private boolean enableLocation;
	private boolean faceAuthEnabled;
	private boolean tRSAutoUpdateInterval;
	private boolean tRSLogUploadURL;
	private String query;

	public AgentTemplateDetails(Map<String,String> map){
		templateName=readTemplatename(map);	
		screenName=readScreenName(map);
		templateName=readTemplatename(map);	
		OrgUnit=readOrgUnit(map);
		themeOptions=readThemeoptions(map);
		updatedTheme=readupdatedTheme(map);
		modifyReason=readmodifyReason(map);
		deleteReason=readdeleteReason(map);
		screenList=readScreenList(map);
		updatedScreenList=readUpdatedScreenList(map);
		auxcode=readAuxcode(map);
		updatedAuxcode=readupdatedAuxcode(map);
		widgetList=readWidgetList(map);
		updatedWidgetList=readUpdatedWidgetList(map);
		templateId=readTemplateId(map);
		weekday=readweekday(map);
		fromTime=readfromTime(map);
		toTime=readtoTime(map);
		enableScreenCapture=readEnableScreenCapture(map);
		tRSCameraCaptureEnabled=readTRSCameraCaptureEnabled(map);
		enableLocation=readEnableLocation(map);
		faceAuthEnabled=readFaceAuthEnabled(map);
		tRSAutoUpdateInterval=readtRSAutoUpdateInterval(map);
		tRSLogUploadURL=readtRSLogUploadURL(map);
		query=readQuery(map);
	}


	private Boolean readFaceAuthEnabled(Map<String, String> map) {
		String value=map.get("Enable Screen Capture");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
	}

	private Boolean readEnableLocation(Map<String, String> map) {
		String value=map.get("TRS Camera Capture Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
	}

	private Boolean readTRSCameraCaptureEnabled(Map<String, String> map) {
		String value=map.get("Enable Location");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
	}

	private Boolean readEnableScreenCapture(Map<String, String> map) {
		String value=map.get("Face Auth Enabled");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
	}
	
	private Boolean readtRSAutoUpdateInterval(Map<String, String> map) {
		String value=map.get("TRS AUTO UPDATE INTERVAL");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
	}
	
	private Boolean readtRSLogUploadURL(Map<String, String> map) {
		String value=map.get("TRS LOG UPLOAD URL");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value="false";
        }
        return Boolean.valueOf(value);
	}


	private String readScreenName(Map<String, String> map) {
		String value=map.get("Screen Name");
		return value;
	}

	public String getScreenname() {
		return screenName;
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readweekday(Map<String, String> map) {
		String value=map.get("WeekDay");
		return value;
	}
	private String readfromTime(Map<String, String> map) {
		String value=map.get("FromTime");
		return value;
	}
	private String readtoTime(Map<String, String> map) {
		String value=map.get("ToTime");
		return value;
	}
	private String readWidgetList(Map<String, String> map) {
		String value=map.get("WidgetList");
		return value;
	}
	private String readUpdatedWidgetList(Map<String, String> map) {
		String value=map.get("UdatedWidgetList");
		return value;
	}
	private String readAuxcode(Map<String, String> map) {
		String value=map.get("AuxCode");
		return value;
	}
	private String readupdatedAuxcode(Map<String, String> map) {
		String value=map.get("UpdatedAuxCode");
		return value;
	}
	private String readupdatedTheme(Map<String, String> map) {
		String value=map.get("Updated Theme");
		return value;
	}
	private String readmodifyReason(Map<String, String> map) {
		String value=map.get("Modify Reason");
		return value;
	}

	private String readdeleteReason(Map<String, String> map) {
		String value=map.get("Delete Reason");
		return value;
	}

	private String readTemplatename(Map<String, String> map) {
		String value=map.get("Template Name");
		return value;
	}

	private String readTemplateId(Map<String, String> map) {
		String value=map.get("Template Id");
		return value;
	}
	private String readOrgUnit(Map<String, String> map) {
		String value=map.get("Org Unit");
		return value;
	}
	private String readThemeoptions(Map<String, String> map) {
		String value=map.get("Theme Options");
		return value;
	}
	private String readScreenList(Map<String, String> map) {
		String value=map.get("Screen List");
		return value;
	}
	private String readUpdatedScreenList(Map<String, String> map) {
		String value=map.get("UpdatedScreenList");
		return value;
	}

	public String getupdatedTheme() {
		return updatedTheme;
	}

	public String getmodifyReason() {
		return modifyReason;
	}

	public String getdeleteReason() {
		return deleteReason;
	}

	public String getTemplatename() {
		return templateName;
	}

	public String getOrgUnit() {
		return OrgUnit;
	}

	public String getThemeoptions() {
		return themeOptions;
	}

	public String getScreenList() {
		return screenList;
	}

	public String getUpdatedScreenList() {
		return updatedScreenList;
	}

	public String getUpdatedAuxcode() {
		return updatedAuxcode;
	}

	public String getAuxCode() {
		return auxcode;
	}

	public String getWidgetList() {
		return widgetList;
	}

	public String getupdatedWidgetList() {
		return updatedWidgetList;
	}

	public String gettemplateId() {
		return templateId;
	}
	public String getweekday() {
		return weekday;
	}
	public String getfromTime() {
		return fromTime;
	}
	public String gettoTime() {
		return toTime;
	}
	
	public boolean IsScreenCaptureEnabled() {
        return enableScreenCapture;
    }
	
	public boolean IsTRSCameraPictureEnabled() {
        return tRSCameraCaptureEnabled;
    }
	
	public boolean IsLocationEnabled() {
        return enableLocation;
    }
	
	public boolean IsFaceAuthenticationEnabled() {
        return faceAuthEnabled;
    }
	
	public boolean IsTRS_AUTO_UPDATE_INTERVAL() {
        return tRSAutoUpdateInterval;
    }
	
	public boolean IsTRS_LOG_UPLOAD_URL() {
        return tRSLogUploadURL;
    }
	
	
	public String getQuery() {
		return query;
	}
}

