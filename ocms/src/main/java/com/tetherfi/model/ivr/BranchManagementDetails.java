package com.tetherfi.model.ivr;

import java.util.Map;

public class BranchManagementDetails {
	
	private String branchName;
	private String query;

	public BranchManagementDetails(Map<String, String> map) {
		branchName=readBranchName(map);
		query=readQuery(map);
	}

	private String readQuery(Map<String, String> map) {
		String value=map.get("Query");
		return value;
	}

	private String readBranchName(Map<String, String> map) {
        String value=map.get("Branch Name");
		return value;
	}
	
	public String getBranchName() {
		return branchName;
	}

	public String getQuery() {
		return query;
	}

}
