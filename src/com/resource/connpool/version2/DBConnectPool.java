package com.resource.connpool.version2;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接池
 * */
public class DBConnectPool {
	private int iniSize = 2; // 连接池的初始大小

	private int addSize = 2; // 每次增加的大小

	private int maxSize = 4; // 最大的大小

	private IDataBase db;

	private List<DBConnector> connections = null; // 连接池，初始为空

	public DBConnectPool(IDataBase db) {
		this.db = db;
		iniPool();
	}

	/**
	 * 初始化连接池
	 */
	public void iniPool() {
		connections = new ArrayList<DBConnector>();
		connections.addAll(addPool(iniSize));
	}

	/**
	 * 增加连接数
	 * 
	 * @param size
	 *            要增加的数目
	 * @return
	 * */
	public List<DBConnector> addPool(int size) {
		List<DBConnector> subList = new ArrayList<DBConnector>();
		for (int i = 0; i < size; i++) {
			DBConnector conn = new DBPoolConnector(db);
			conn.connect();
			subList.add(conn);
		}
		return subList;
	}

	/**
	 * 判断连接数是否超过连接池的最大连接数
	 * 
	 * @return 是 否
	 * */
	public boolean isOverRange() {
		return connections.size() >= maxSize;
	}

	/**
	 * 获得空闲连接
	 * 
	 * @return 连接
	 * */
	public DBConnector getConnection() {
		// 循环，有空闲连接则进行返回
		for (DBConnector conn : connections) {
			if (conn.isFree()) {
				conn.setIsFree(false);
				return conn;
			}
		}
		// 没有空闲的
		if (isOverRange()) {
			throw new RuntimeException("The connection number is over range.");
		} else {
			int size = addSize;
			if ((connections.size() + size) > maxSize) {
				size = maxSize - connections.size();
			}
			List<DBConnector> subPool = addPool(size);
			connections.addAll(subPool);
			return subPool.get(0);
		}
	}

	/**
	 * 设置连接为空闲状态
	 * 
	 * @param conn
	 * */
	public void freeConnection(DBConnector conn) {
		conn.setIsFree(true);
	}

	/**
	 * 释放所有的空闲连接
	 * */
	public void releaseAllFree() {
		List<DBConnector> subPool = new ArrayList<DBConnector>();
		for (DBConnector conn : connections) {
			if (conn.isFree()) {
				subPool.add(conn);
				conn.close();
			}
		}
		connections.removeAll(subPool);
	}

	/**
	 * 释放连接
	 * 
	 * @param conn
	 *            要释放的连接
	 * */
	public void release(DBConnector conn) {
		conn.release();
		connections.remove(conn);
	}

	/**
	 * 释放所有连接
	 * */
	public void release() {
		for (DBConnector conn : connections) {
			conn.release();
		}
		connections.removeAll(connections);
	}

	/**
	 * 设置初始化最大连接数
	 * 
	 * @param iniSize
	 * */
	public void setIniSize(int iniSize) {
		this.iniSize = iniSize;
	}

	/**
	 * 设置每次递增的最大数目
	 * 
	 * @param addSize
	 * */
	public void setAddSize(int addSize) {
		this.addSize = addSize;
	}

	/**
	 * 设置连接池的最大数目
	 * 
	 * @param maxSize
	 * */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 连接池内部类，用于管理DBConnector安全性，屏蔽close功能
	 * */
	class DBPoolConnector extends DBConnector {
		public DBPoolConnector(IDataBase db) {
			super(db);
		}

		@Override
		boolean release() {
			return super.close();
		}

		@Override
		public boolean close() {
			throw new RuntimeException("Can not close,please use the Connection Pool close this connection.");
		}
	}
}