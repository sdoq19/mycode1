package com.resource.connpool.version2;

/**
 * 数据库连接信息接口实现类
 * 
 * @author SeDion 该类的默认驱动为mysql-connector-java 5.0以上版本
 * */
public class Mysql implements IDataBase {

	private String connurl;

	private String userName;

	private String password;

	public Mysql(String connurl, String userName, String password) {
		this.connurl = connurl;
		this.userName = userName;
		this.password = password;
	}

	public Mysql(String serverName, String dbName, String userName, String password) {
		this.connurl = "jdbc:mysql:" + serverName + "/" + dbName;
		this.userName = userName;
		this.password = password;
	}

	public String getConnUrl() {
		// TODO Auto-generated method stub
		return connurl;
	}

	public String getDirver() {
		// TODO Auto-generated method stub
		return "com.mysql.jdbc.Driver";
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

}