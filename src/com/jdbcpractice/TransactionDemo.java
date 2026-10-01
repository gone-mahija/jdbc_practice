package com.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDemo {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_practice";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "your_mysql_password";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			String sql = "Update students set marks = ? where id =?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, 80);
			ps.setInt(2, 7);
			ps.addBatch();
			
			ps.setInt(1,  95);
			ps.setInt(2,  8);
			ps.addBatch();
			
			ps.setInt(1, 100);
			ps.setInt(2, 999);
			ps.addBatch();
			
			int[] results = ps.executeBatch();
			
			for(int count : results) {
				System.out.println(count);
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

}
