package com.tetherfi.model.user;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class QueryBrowserDetails {

    private String tableName;
    private String query;

    public QueryBrowserDetails(Map<String,String> map){
        tableName=readTableName(map);
        query=readQuery(map);
    }

    private String readQuery(Map<String, String> map) {
        String value=map.get("Query");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value=RandomStringUtils.randomAlphabetic(7);
        }
        return value;}

    private String readTableName(Map<String, String> map) {
        String value=map.get("Table Name");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String getTableName() {
        return tableName;
    }

    public String getQuery() {
        return query;
    }
}
