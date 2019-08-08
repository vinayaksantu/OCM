package com.tetherfi.model.user;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class CmDataSyncDetails {
    private String objectType;
    private String modifyReason;

    public CmDataSyncDetails(Map<String,String>map) {
        objectType=readObjectType(map);
        modifyReason=readModifyReason(map);
    }
    public String readObjectType(Map<String,String> map){
        String value=map.get("object type");
        if(value==null||value.equalsIgnoreCase("random.str")){
            value= RandomStringUtils.randomAlphabetic(10);
        }
        return value;

    }
        public String readModifyReason(Map<String,String> map){
            String value=map.get("modify reason");
            if(value==null||value.equalsIgnoreCase("random.str")){
                value= RandomStringUtils.randomAlphabetic(10);
            }
            return value;

    }
    public String getObjectType() {
        return objectType;
    }

    public String getModifyReason() {
        return modifyReason;
    }
}
