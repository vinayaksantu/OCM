package com.tetherfi.utility;
import com.tetherfi.constants.Constants;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DatabaseConnector {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public void connectToDataBase(String db) {
		try {
			//        	MS sql DB connection string
			/*String dbUrl=Constants.host+";databaseName="+db;
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");*/

			//        	My Sql JDBC connection	
			String dbUrl=Constants.host+""+db;
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(dbUrl, Constants.db_user, Constants.db_pass);
			System.out.println("Connection Established Successfull:");
		} catch (Exception e) {
			System.out.println("Unable to make connection with DB");
			e.printStackTrace();
		}
	}

	public void insertQuery(String query) {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Unable to execute query in DB");
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Unable to execute query in DB");
			e.printStackTrace();
		}
		return rs;
	}

	public List<Map<String, String>> getResultSetInMap(ResultSet rs) {
		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		try {
			while (rs.next()) {
				map = new HashMap<String, String>();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String str=rs.getString(i).trim();
					map.put(rs.getMetaData().getColumnName(i),str);
				}
				maplist.add(map);
			}
		} catch (Exception e) {
			System.out.println("Unable to fetch the Resultset Data");
			e.printStackTrace();
		}
		return maplist;
	}
	
	public List<Map<String, String>> getResultSetInMapForMySQL(ResultSet rs) {
		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		try {
			while (rs.next()) {
				map = new HashMap<String, String>();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String str=rs.getString(i).trim();
					map.put(rs.getMetaData().getColumnLabel(i),str);
				}
				maplist.add(map);
			}
		} catch (Exception e) {
			System.out.println("Unable to fetch the Resultset Data");
			e.printStackTrace();
		}
		return maplist;
	}

	public List<String> getResultSetInList(ResultSet rs) {
		List<String> maplist = new ArrayList<>();
		try {
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String str=rs.getString(i).trim();
					maplist.add(str);
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to fetch the Resultset Data");
			e.printStackTrace();
		}
		return maplist;
	}

	public void closeDbConnection() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("Unable to close connection with DB");
			e.printStackTrace();
		}
	}

	
}