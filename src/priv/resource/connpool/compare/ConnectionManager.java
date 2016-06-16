package priv.resource.connpool.compare;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0连接池
 * @author zhuzhenhao
 */
public class ConnectionManager {
	private static ConnectionManager instance;
	private static ComboPooledDataSource dataSource;

	private ConnectionManager() {
		dataSource = new ComboPooledDataSource();
		dataSource.setUser("zhuaqianmao_test");
		dataSource.setPassword("zhuaqianmao1qa2ws");
		dataSource.setJdbcUrl("jdbc:mysql://hejiabin.mysql.rds.aliyuncs.com:3306/xiaojinniu_2?"
				+ "useUnicode=true&characterEncoding=utf-8");
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setInitialPoolSize(5);
		dataSource.setMinPoolSize(1);
		dataSource.setMaxPoolSize(10);
		dataSource.setMaxIdleTime(60);
		dataSource.setMaxStatements(10);
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
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
