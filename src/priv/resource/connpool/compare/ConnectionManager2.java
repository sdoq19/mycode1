package priv.resource.connpool.compare;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Druid连接池
 * @author zhuzhenhao
 */
public class ConnectionManager2 {
	private static ConnectionManager2 instance;
	private static DruidDataSource dataSource;

	private ConnectionManager2() {
		dataSource = new DruidDataSource();
		dataSource.setUsername("zhuaqianmao_test");
		dataSource.setPassword("zhuaqianmao1qa2ws");
		dataSource.setUrl("jdbc:mysql://hejiabin.mysql.rds.aliyuncs.com:3306/xiaojinniu_2?"
				+ "useUnicode=true&characterEncoding=utf-8");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(10);
		try {
			dataSource.setFilters("stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static ConnectionManager2 getInstance() {
		if (instance == null) {
			instance = new ConnectionManager2();
		}
		return instance;
	}
	
	public synchronized final Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
