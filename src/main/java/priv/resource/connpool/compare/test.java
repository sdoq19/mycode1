package priv.resource.connpool.compare;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class test {
	
	public static void main(String[] args) {
		System.out.println("----------------不使用连接池...----------------");
		long tTime1 = 0l;
		for (int i = 0; i < 30; i++) {
			long btime = System.currentTimeMillis();
			MysqlDataSource mSource = new MysqlDataSource();
			mSource.setUser("zhuaqianmao_test");
			mSource.setPassword("zhuaqianmao1qa2ws");
			mSource.setURL("jdbc:mysql://hejiabin.mysql.rds.aliyuncs.com:3306/xiaojinniu_2?useUnicode=true&characterEncoding=utf-8");
			java.sql.Connection connection = null;
			java.sql.PreparedStatement ps = null;
			ResultSet rSet = null;
			try {
				connection = mSource.getConnection();
				ps = connection.prepareStatement("select * from xjn_user u where u.id < 10000;");
				rSet = ps.executeQuery();
				while(rSet.next()) {
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(rSet!=null) {
					try {
						rSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(ps !=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(connection!=null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			Long etime = System.currentTimeMillis();
			long differTime = (etime - btime);
			tTime1 += differTime;
			System.out.println("第" + (i + 1) + "次执行花费时间为:"  
                    + differTime);  
		}
		System.out.println("----------------不使用连接池..."+"总耗时："+tTime1+"----------------");
		
		
		System.out.println("----------------使用C3P0连接池...----------------");
		long tTime2 = 0l;
		for (int i = 0; i < 30; i++) {
			long btime = System.currentTimeMillis();
			ConnectionManager cManager = ConnectionManager.getInstance();
			Connection connection = cManager.getConnection();
			java.sql.PreparedStatement ps = null;
			ResultSet rSet = null;
			try {
				ps = connection.prepareStatement("select * from xjn_user u where u.id < 10000;");
				rSet = ps.executeQuery();
				while(rSet.next()) {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(rSet!=null) {
					try {
						rSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(ps !=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(connection!=null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			Long etime = System.currentTimeMillis();
			long differTime = (etime - btime);
			tTime2 += differTime;
			System.out.println("第" + (i + 1) + "次执行花费时间为:"  
                    + differTime);  
		}
		System.out.println("----------------使用C3P0连接池..."+"总耗时："+tTime2+"----------------");
		
		System.out.println("----------------使用Druid连接池...----------------");
		long tTime3 = 0l;
		for (int i = 0; i < 30; i++) {
			long btime = System.currentTimeMillis();
			ConnectionManager2 cManager = ConnectionManager2.getInstance();
			Connection connection = cManager.getConnection();
			java.sql.PreparedStatement ps = null;
			ResultSet rSet = null;
			try {
				ps = connection.prepareStatement("select * from xjn_user u where u.id < 10000;");
				rSet = ps.executeQuery();
				while(rSet.next()) {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(rSet!=null) {
					try {
						rSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(ps !=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(connection!=null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			Long etime = System.currentTimeMillis();
			long differTime = (etime - btime);
			tTime3 += differTime;
			System.out.println("第" + (i + 1) + "次执行花费时间为:"  
                    + differTime);  
		}
		System.out.println("----------------使用Druid连接池..."+"总耗时："+tTime3+"----------------");
		
	}
}
