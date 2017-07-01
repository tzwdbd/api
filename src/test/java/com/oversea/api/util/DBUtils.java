package com.oversea.api.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource dataSource;
	
	public static void execute(String sql){
		if(dataSource == null){
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://122.225.114.19/core?useUnicode=true&characterEncoding=utf8&noAccessToProcedureBodies=true");
			dataSource.setUsername("dev");
			dataSource.setPassword("dev@)#)");
		}
		Connection conn = null;  
        PreparedStatement stmt = null; 
        try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);  
            stmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(stmt != null){
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (Exception e2) {
				}
			}
		}
	}

}
