package com.tetherfi.model.fax;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class FaxRoutingConfigurationDetails {
    private String faxLine;
    private String intent;
    private String senderType;
    private String routeType;
    private String routeData;
    private String modifyReason;
    private String deleteReason;

    public FaxRoutingConfigurationDetails(Map<String, String> map) {
        faxLine = readFaxline(map);
        intent = readIntent(map);
        routeType = readRouteType(map);
        senderType = readSenderType(map);
        routeData=readRouteData(map);
        deleteReason = readDeleteReason(map);
        modifyReason = readModifyReason(map);
    }

    public String readFaxline(Map<String, String> map) {
        String value = map.get("FaxLine");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readIntent(Map<String, String> map) {
        String value = map.get("Intent");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readRouteType(Map<String, String> map) {
        String value = map.get("RouteType");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }
    public String readRouteData(Map<String, String> map) {
        String value = map.get("RouteData");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readSenderType(Map<String, String> map) {
        String value = map.get("SenderType");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(7);
        }
        return value;
    }

    public String readModifyReason(Map<String, String> map) {
        String value = map.get("Modify Reason");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String readDeleteReason(Map<String, String> map) {
        String value = map.get("Delete Reason");
        if (value == null || value.equalsIgnoreCase("random.str")) {
            value = RandomStringUtils.randomAlphabetic(10);
        }
        return value;
    }

    public String getFaxLine() {
        return faxLine;
    }

    public String getSenderType() {
        return senderType;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public String getDeleteReason() {

        return deleteReason;
    }

    public String getIntent() {
        return intent;
    }

    public String getRouteType() {
        return routeType;
    }

    public String getRouteData() {
        return routeData;
    }
}
