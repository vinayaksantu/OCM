package com.tetherfi.model.email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class EmailTemplateDetails {

	 private String departmentName;
	    private String deptEnabled;
	    private String groupName;
	    private String groupEnabled;
	    private String templateName;
	    private String UpdatedEnabled;
	    private String enabled;
	    private String text;
	    private String type;
	    private String modifyReason;
	    private  String deleteReason;
	    private String UpdatedDeptEnabled;
	    private String UpdatedGroupEnabled;
	    private String Query;
	    private String Subject;
	    private String body;

	    public EmailTemplateDetails(Map<String,String> map){
	        departmentName=readDepartmentName(map);
	        deptEnabled=readDeptEnabled(map);
	        groupName=readGroupName(map);
	        groupEnabled=readGroupEnabled(map);
	        templateName=readTemplateName(map);
	        UpdatedEnabled=readUpdatedEnabled(map);
	        enabled=readEnabled(map);
	        text=readText(map);
	        type=readType(map);
	        modifyReason=readModifyReason(map);
	        deleteReason=readDeleteReason(map);
	        UpdatedDeptEnabled=readUpdatedDeptEnabled(map);
	        UpdatedGroupEnabled=readUpdatedGroupEnabled(map);
	        Query=readQuery(map);
	        Subject=readSubject(map);
	        body=readBody(map);
	        
	    }

	    private String readBody(Map<String, String> map) {
	    	String value=map.get("Body");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
		}

		private String readSubject(Map<String, String> map) {
	    	String value=map.get("Subject");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
		}

		private String readQuery(Map<String, String> map) {
	    	String value=map.get("Query");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
		}

		private String readUpdatedGroupEnabled(Map<String, String> map) {
	    	String value=map.get("Updated Group Enabled");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
		}

		private String readUpdatedEnabled(Map<String, String> map) {
	    	String value=map.get("Updated Enabled");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
		}

		private String readUpdatedDeptEnabled(Map<String, String> map) {
	    	 String value=map.get("Updated Department Enabled");
	         if(value==null||value.equalsIgnoreCase("random.str")){
	             value=RandomStringUtils.randomAlphabetic(7);
	         }
	         return value;
		}
		
	    private String readDepartmentName(Map<String, String> map) {
	        String value=map.get("Department Name");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
	    }

	    private String readDeptEnabled(Map<String, String> map) {
	        String value=map.get("Department Enabled");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value="NO";
	        }
	        return value;
	    }

	    private String readGroupName(Map<String, String> map) {
	        String value=map.get("Group Name");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
	    }

	    private String readGroupEnabled(Map<String, String> map) {
	        String value=map.get("Group Enabled");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value="NO";
	        }
	        return value;
	    }

	    private String readTemplateName(Map<String, String> map) {
	        String value=map.get("Template Name");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
	    }

	    private String readEnabled(Map<String, String> map) {
	        String value=map.get("Enabled");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value="No";
	        }
	        return value;
	    }

	    private String readText(Map<String, String> map) {
	        String value=map.get("Text");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value=RandomStringUtils.randomAlphabetic(7);
	        }
	        return value;
	    }

	    private String readType(Map<String, String> map) {
	        String value=map.get("Type");
	        if(value==null||value.equalsIgnoreCase("random.str")){
	            value= RandomStringUtils.randomAlphabetic(7);
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

	    public String getDepartmentName() {
	        return departmentName;
	    }

	    public String getDeptEnabled() {
	        return deptEnabled;
	    }

	    public String getGroupName() {
	        return groupName;
	    }

	    public String getGroupEnabled() {
	        return groupEnabled;
	    }

	    public String getTemplateName() {
	        return templateName;
	    }

	    public String getEnabled() {
	        return enabled;
	    }

	    public String getText() {
	        return text;
	    }

	    public String getModifyReason() {
	        return modifyReason;
	    }

	    public String getDeleteReason() {
	        return deleteReason;
	    }
		
		public String getUpdatedDeptEnabled() {
			return UpdatedDeptEnabled;
		}

		public String getUpdatedEnabled() {
			return UpdatedEnabled;
		}

		public String getUpdatedGroupEnabled() {
			return UpdatedGroupEnabled;
		}

		public String getQuery() {
			return Query;
		}

		public String getSubject() {
			return Subject;
		}

		public String getType() {
			return type;
		}

		public String getBody() {
			return body;
		}
		

}
