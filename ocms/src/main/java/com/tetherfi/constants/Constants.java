package com.tetherfi.constants;

public class Constants {
    /*public static final String applicationUrl="http://122.166.193.21:8090/DBS_CBG_OCM/";
    public static final String username="Akshith";
    public static final String password="P@ssw0rd123";
    public static final String winAuthUrl="http://"+Constants.username+":"+Constants.password+"@"+applicationUrl.split("//")[1];
   */
	
//.18 DOCMT1 SG Fax Instance
	/*public static final String host="jdbc:sqlserver://10.133.146.18:14330";
    public static final String db_user="sa";
    public static final String db_pass="d@T@T0r3@2020";
    public static final String db_name="DOCMT1_SG";*/
    
	/*public static final String host="jdbc:sqlserver://singtellab.cqnce3nduwba.us-east-1.rds.amazonaws.com:1433";
    public static final String db_user="sa";
    public static final String db_pass="tetherfi1234";
    public static final String db_name="singtel_ccp";*/
    

//	.61 PRODUCT OCM DB
	public static final String host="jdbc:sqlserver://172.16.2.61:1433";
    public static final String db_user="ProdQA";
    public static final String db_pass="Tetherfi123$";
    public static final String db_name="Product_OCM";
	
//    DSTA DB 
    /*public static final String host="jdbc:sqlserver://172.16.2.16:14330";
    public static final String db_user="sa";
    public static final String db_pass="admin@123";
    public static final String db_name="DSTA";*/
    
//	.18 TFax
    /*public static final String host="jdbc:sqlserver://10.133.146.18:14330";
    public static final String db_user="sa";
    public static final String db_pass="P@ssw0rd";
    public static final String db_name="TestTfax";*/
    
    public static final String chromeDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";
    public static final String ieDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";
    
//    public static final String chromeDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";
//    public static final String ieDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";
    public static final String firfoxDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";
    public static final String edgeDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";
	public static final String operaDriverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\";


    //public static String fileTransferDestinationTransfer = "D:/TetherfiWork/ProductOCM/scripts/ocms/src/test/resources/DownloadedFiles/Json/AgentSetting.json";
    public static String ftp_domain = "172.16.2.61";
    public static String ftp_userName = "Administrator";
    public static String ftp_password = "T3th3rfi982!";
    //public static String remoteFilePathTransfer = "\\\\172.16.2.16\\d$\\Products\\OCM\\UI\\CustomJSONConfigurations\\Admin%20Modules\\AgentSetting.json";

}
