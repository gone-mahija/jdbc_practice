package com.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;

public class CallableStatementInOut {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_practice";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "your_mysql_password";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
				CallableStatement cs = con.prepareCall("{call increaseMarks(?)}");
				
				//IN Parameter
				cs.setInt(1, 80);
				
				//OUT Parameter
				cs.registerOutParameter(1, Types.INTEGER);
				
				//Execute procedure
				cs.execute();
				
				//Get OUT value
				int updatedMarks = cs.getInt(1);
				
				System.out.println("Updated marks : "+updatedMarks);
				 
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		

	}

}
