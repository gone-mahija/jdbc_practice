package com.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;

public class CallableOutDemo {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_practice";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Mahivce@538";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
				CallableStatement cs = con.prepareCall("{call countStudentsByMarks(?, ?)}");
				
				//IN Parameter
				cs.setInt(1, 80);
				
				//OUT Parameter
				cs.registerOutParameter(2, Types.INTEGER);
				
				//Execute procedure
				cs.execute();
				
				//Get OUT value
				int count = cs.getInt(2);
				
				System.out.println("Students with marks >= 80: "+count);
				 
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		

	}

}
