package com.resource.connpool.version1;

import java.sql.Connection;   
  
public class ConnectionPoolTest {   
  
    public static void main(String[] args) throws Exception {   
        ConnectionPool connPool = new ConnectionPool(
        		"com.mysql.jdbc.Driver",   
                "jdbc:mysql://localhost:3306/test", 
                "root", 
                "root");   
  
        Connection conn = connPool.getConnection(); 
        connPool.createPool();
        
        connPool.setTestTable("testA"); 
       // conn.
        connPool.closeConnectionPool();   
    }   
       
}  
