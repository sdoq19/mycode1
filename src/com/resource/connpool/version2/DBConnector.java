package com.resource.connpool.version2;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接类 说明：该方法为单个数据库的连接类，可以脱离连接池来单独使用
 * */
public class DBConnector {
	private IDataBase db;

	private Connection conn;

	private boolean free = true;

	public DBConnector(IDataBase db) {
		this.db = db;
	}

	/**
	 * 连接数据库
	 * 
	 * @return 是否连接成功
	 */
	public Connection connect() {
		try {
			Class.forName(db.getDirver()).newInstance();
			conn = DriverManager.getConnection(db.getConnUrl(), db.getUserName(), db.getPassword());
			System.out.println("connect success！");
		} catch (Exception e) {
			System.out.println("connect failure！");
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}

	public Connection getConnection() {
		return conn;
	}

	/**
	 * 判断连接是否在使用 注意：在这个地方使用的是包访问权限，这一项是特地为连接池设置的，对于外面应用 的类并不能读到此方法。
	 * */
	boolean isFree() {
		return free;
	}

	/**
	 * 设置连接是否空闲
	 * 
	 * @param isFree
	 * */
	void setIsFree(boolean isFree) {
		this.free = isFree;
	}

	/**
	 * 关闭数据库
	 * 
	 * @return 数据库是否关闭成功
	 * */
	public boolean close() {
		try {
			if (conn != null) {
				conn.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("close failure！");
			return false;
		}
	}

	/**
	 * 释放连接，为连接池的重写提供接口
	 * 
	 * @return
	 * */
	boolean release() {
		throw new RuntimeException("do not use the connection pool,can not release.");
	}
}