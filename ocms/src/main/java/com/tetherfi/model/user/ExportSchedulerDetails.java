package com.tetherfi.model.user;

import java.util.Map;

public class ExportSchedulerDetails {
	
	String name;
	String email;
	String frequency;
	String ReportList;
	String modifyReason;
	String query;
	String deleteReason;
	String time;
	String day;
	String date;
	String StartTime;
	String errorMsg5;
	String errorMsg4;
	String errorMsg3;
	String errorMsg2;
	String errorMsg1;
	String errorMsg6;
	String errorMsg7;
	String errorMsg8;
	String errorMsg9;
	String errorMsg10;
	String updatedReportList;
	String updatedTime;
	
	public ExportSchedulerDetails(Map<String, String> map) {
			name =readName(map);
			frequency=readFrequency(map);
			email=readEmail(map);
			ReportList=readReportList(map);
			modifyReason=readModifyReason(map);
			query=readQuery(map);
			deleteReason=readDeleteReason(map);
			time=readTime(map);
			day=readDay(map);
			date=readDate(map);
			StartTime=readStartTime(map);
			errorMsg1=readErrorMsg1(map);
			errorMsg2=readErrorMsg2(map);
			errorMsg3=readErrorMsg3(map);
			errorMsg4=readErrorMsg4(map);
			errorMsg5=readErrorMsg5(map);
			errorMsg6=readErrorMsg6(map);
			errorMsg7=readErrorMsg7(map);
			errorMsg8=readErrorMsg8(map);
			errorMsg9=readErrorMsg9(map);
			errorMsg10=readErrorMsg10(map);
			updatedReportList=readUpdatedList(map);
			updatedTime=readUpdatedTime(map);

		}

		private String readUpdatedTime(Map<String, String> map) {
			String value=map.get("Updated Time");
			return value;
	}

		private String readUpdatedList(Map<String, String> map) {
			String value=map.get("Updated Report List");
			return value;
	}

		private String readErrorMsg10(Map<String, String> map) {
			String value=map.get("ErrorMsg10");
			return value;
	}

		private String readErrorMsg9(Map<String, String> map) {
			String value=map.get("ErrorMsg9");
			return value;
	}

		private String readErrorMsg8(Map<String, String> map) {
			String value=map.get("ErrorMsg8");
			return value;
	}

		private String readErrorMsg7(Map<String, String> map) {
			String value=map.get("ErrorMsg7");
			return value;
	}

		private String readErrorMsg6(Map<String, String> map) {
			String value=map.get("ErrorMsg6");
			return value;
	}

		private String readErrorMsg5(Map<String, String> map) {
			String value=map.get("ErrorMsg5");
			return value;
	}

		private String readErrorMsg4(Map<String, String> map) {
			String value=map.get("ErrorMsg4");
			return value;
	}

		private String readErrorMsg3(Map<String, String> map) {
			String value=map.get("ErrorMsg3");
			return value;
	}

		private String readErrorMsg2(Map<String, String> map) {
			String value=map.get("ErrorMsg2");
			return value;
	}

		private String readErrorMsg1(Map<String, String> map) {
			String value=map.get("ErrorMsg1");
			return value;
	}

		private String readStartTime(Map<String, String> map) {
			String value=map.get("Start Time");
			return value;
	}

		private String readDate(Map<String, String> map) {
			String value=map.get("Date");
			return value;
	}

		private String readDay(Map<String, String> map) {
			String value=map.get("Day");
			return value;
	}

		private String readTime(Map<String, String> map) {
			String value=map.get("Time");
			return value;
	}

		private String readEmail(Map<String, String> map) {
			String value=map.get("Email");
			return value;
	}

		private String readFrequency(Map<String, String> map) {
			String value=map.get("Frequency");
			return value;
	}

		private String readDeleteReason(Map<String, String> map) {
			String value=map.get("Delete Reason");
			return value;
		}

		private String readQuery(Map<String, String> map) {
			String value=map.get("Query");
			return value;
		}

		private String readModifyReason(Map<String, String> map) {
			String value=map.get("Modify Reason");
			return value;
		}

		private String readReportList(Map<String, String> map) {
			String value=map.get("Report List");
			return value;
		}

		private String readName(Map<String, String> map) {
			String value=map.get("Name");
					return value;
		}

		public String getName() {
			return name;
		}


		public String getReportList() {
			return ReportList;
		}


		public String getModifyReason() {
			return modifyReason;
		}

		public String getQuery() {
			return query;
		}

		public String getDeleteReason() {
			return deleteReason;
		}

		public String getEmail() {
			return email;
		}

		public String getFrequency() {
			return frequency;
		}

		public String getTime() {
			return time;
		}

		public String getDay() {
			return day;
		}

		public String getDate() {
			return date;
		}

		public String getStartTime() {
			return StartTime;
		}

		public String getErrorMsg4() {
			return errorMsg4;
		}

		public String getErrorMsg1() {
			return errorMsg1;
		}

		public String getErrorMsg5() {
			return errorMsg5;
		}

		public String getErrorMsg2() {
			return errorMsg2;
		}

		public String getErrorMsg3() {
			return errorMsg3;
		}

		public String getErrorMsg6() {
			return errorMsg6;

		}

		public String getErrorMsg7() {
			return errorMsg7;
		}

		public String getErrorMsg8() {
			return errorMsg8;

		}

		public String getErrorMsg9() {
			return errorMsg9;

		}

		public String getErrorMsg10() {
			return errorMsg10;

		}

		public String getUpdatedReportList() {
			return updatedReportList;
		}

		public String getUpdatedTime() {
			return updatedTime;
		}

	}
















