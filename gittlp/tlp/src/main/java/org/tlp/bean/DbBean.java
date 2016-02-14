package org.tlp.bean;

public class DbBean {
	
	
	private String databaseUrl = "jdbc:mysql://tc.c4ebuphfnshg.us-west-2.rds.amazonaws.com:1433/tc";
	private String userName = "root";
	private String password = "root1234";		
	private String dbClass = "com.mysql.jdbc.Driver";
	
	public String getDatabaseUrl() {
		return databaseUrl;
	}
	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDbClass() {
		return dbClass;
	}
	public void setDbClass(String dbClass) {
		this.dbClass = dbClass;
	}
	
	
	
}
