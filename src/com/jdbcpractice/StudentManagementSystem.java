package com.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagementSystem {
	
	private static final String url ="jdbc:mysql://localhost:3306/jdbc_practice";
	private static final String userName = "root";
	private static final String password = "Mahivce@538";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("=======STUDENT MANAGEMENT SYSTEM=======");
			System.out.println("1. Add Student");
			System.out.println("2. View Student");
			System.out.println("3. Update Student Marks");
			System.out.println("4. Delete Student");
			System.out.println("5. Exit");
			
			System.out.println("Enter your choice (1,2,3,4,5) : ");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1 : addStudent(sc);
				break;
				
				case 2 : viewStudents();
				break;
				
				case 3 : updateStudent(sc);
				break;
				
				case 4 : deleteStudent(sc);
				break;
				
				case 5 : 
					System.out.println("Thank you for using Student Management System.");
					sc.close();
					return;
					
				default : 
					System.out.println("Invalid choice. Please try again");
			}
		}

	}
	
	//CREATE
	private static void addStudent(Scanner sc) {
		System.out.println("Enter student name : ");
		String name = sc.next();
		
		System.out.println("Enter Marks : ");
		int marks = sc.nextInt();
		
		String sql = "INSERT INTO students (name, marks) values (?,?)";
		
		try(Connection con = DriverManager.getConnection(url, userName, password);
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setInt(2, marks);
			
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				System.out.println("Student added successfully.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	//READ
	
	private static void viewStudents() {
		String sql = "SELECT * FROM students";
		try(Connection con = DriverManager.getConnection(url, userName, password);
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			System.out.println("\nID\tName\tMarks");
			System.out.println("------------------------------------");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int marks = rs.getInt("marks");
				
				System.out.println(id +"\t"+ name +"\t"+marks);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//UPDATE
	private static void updateStudent(Scanner sc) {
		System.out.println("Enter student ID : ");
		int id = sc.nextInt();
		
		System.out.println("Enter new marks : ");
		int marks = sc.nextInt();
		
		String sql = "UPDATE students SET marks = ? where id = ?";
		
		try(Connection con = DriverManager.getConnection(url, userName, password);
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1,  marks);
			ps.setInt(2, id);
			
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				System.out.println("Student updated successfully");
				
			} else {
				System.out.println("Student with ID "+id+" not found.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//DELETE
	private static void deleteStudent(Scanner sc) {
		System.out.println("Enter student ID : ");
		int id = sc.nextInt();
		
		String sql = "Delete from students where id = ?";
		
		try(Connection con = DriverManager.getConnection(url, userName, password);
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			
			if(rows>0) {
				System.out.println("Student deleted successfully");
			} else {
				System.out.println("Student with ID "+id+" not found");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
