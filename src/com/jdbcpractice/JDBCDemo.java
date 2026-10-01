package com.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Program started");
		String url = "jdbc:mysql://localhost:3306/jdbc_practice";
		String username = "root";
		String password = "Mahivce@538";
		
		String sql = "DELETE FROM students where id = ?";
		
		try {
			//1. Establish the connection
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection successful");
			
			//2. Create a statement
			Statement stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("Statement created");
			
			//3. Execute sql
			
			ps.setInt(1,  6);
			int rows = ps.executeUpdate();
			System.out.println("Rows inserted: "+rows);
			ResultSet rs = stmt.executeQuery("select * from students");
			
			
			
			//4. Read result
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int marks = rs.getInt("marks");
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(marks);
				
			}
			//5. close connection
			con.close();
		
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
