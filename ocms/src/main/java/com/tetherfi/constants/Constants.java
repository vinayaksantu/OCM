package com.tetherfi.constants;

public class Constants {
    /*public static final String applicationUrl="http://122.166.193.21:8090/DBS_CBG_OCM/";
    public static final String username="Akshith";
    public static final String password="P@ssw0rd123";
    public static final String winAuthUrl="http://"+Constants.username+":"+Constants.password+"@"+applicationUrl.split("//")[1];
  */  public static final String host="jdbc:sqlserver://172.16.2.11:14330";
    public static final String db_user="sa";
    public static final String db_pass="P@ssw0rd";
    public static final String db_name="Product_OCM";
    public static final String chromeDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";

    //public static String fileTransferDestinationTransfer = "D:/TetherfiWork/ProductOCM/scripts/ocms/src/test/resources/DownloadedFiles/Json/AgentSetting.json";
    public static String ftp_domain = "172.16.2.16";
    public static String ftp_userName = "administrator";
    public static String ftp_password = "TetherfiBlr@123";
    //public static String remoteFilePathTransfer = "\\\\172.16.2.16\\d$\\Products\\OCM\\UI\\CustomJSONConfigurations\\Admin%20Modules\\AgentSetting.json";

}
