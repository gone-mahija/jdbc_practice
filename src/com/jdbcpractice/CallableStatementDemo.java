package com.jdbcpractice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.mysql.cj.jdbc.CallableStatement;

public class CallableStatementDemo {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_practice";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "your_mysql_password";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(
				Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = con.prepareCall("{CALL getStudentsByMarks(?)}")
						) {
			cs.setInt(1, 80);
			ResultSet rs = cs.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int marks = rs.getInt("marks");
				
				System.out.println(id+" "+name+" "+marks);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
