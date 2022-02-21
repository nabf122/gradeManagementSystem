package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.main.dao.DBConn;

public class UserList {
	
	private int dept_no;
	private String dept_name;
	private String user_id;
	private String name;
	private String email;
	private String phone;
	private String auth;
	private String use_flg;
	
	Scanner scan = new Scanner(System.in);
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;
	
	public String sql;
	
	
	public int getDept_no() {
		return dept_no;
	}
	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getUse_flg() {
		return use_flg;
	}
	public void setUse_flg(String use_flg) {
		this.use_flg = use_flg;
	}
	
	public void UserManagementSystem() {
		System.out.println(":");
		
		String str = scan.nextLine();
		
		if(str.equals("1")) {
			// insert
			userInsert();
		}else if(str.equals("2")) {
			// select
			userSelect();
		}else if(str.equals("3")) {
			// update
			userUpdate();
		}else if(str.equals("4")) {
			// delete
			userDelete();
		}else
			// return
			return ;
	}
	
	// insert
	public void userInsert() {
		System.out.println("새로운 학생 정보를 등록합니다.");
		
		System.out.print("학번 : ");
		this.user_id = scan.nextLine();
		
		// 학번 중복 체크
		try {
			sql = "SELECT COUNT(*) FROM insa.user_info WHERE id ='"+ this.user_id +"'";
			
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			rs.next();
			if(rs.getInt(1) >= 1)
			{
				System.out.println("이미 등록된 학번입니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.print("이름 : ");
		this.name = scan.nextLine();
		
		System.out.print("이메일 : ");
		this.email = scan.nextLine();
		
		System.out.print("전화번호 : ");
		this.phone = scan.nextLine();
		
		// 학과 체크하기
		int num = 0;
		try {
			sql = "SELECT count(*) FROM insa.user_dept";
			
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
				
			rs.next();
			num = rs.getInt(1);
			
			sql = "SELECT dept_name FROM insa.user_dept";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("등록된 학과 리스트");
			System.out.println("--------------");
			rs.next();
			for(int i=0;i<num+4;i++) 
			{
				System.out.println(rs.getString(i+1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		System.out.print("학과명 : ");
		this.dept_name = scan.nextLine();
		// 학과 체크하기
		try {
			sql = "SELECT COUNT(*) FROM insa.user_dept WHERE dept_name ='"+ this.dept_name +"'";
			
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			rs.next();
			if(rs.getInt(1) <= 0)
			{
				System.out.println("존재하지 않는 학과명입니다.");
			}else
			{
				sql = "SELECT dept_no FROM insa.user_dept WHERE dept_name ='"+ this.dept_name +"'";
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				rs.next();
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 입력한 학생 정보 데이터 insert
		try {
			sql = "INSERT INTO insa.user_info (dept_no, id, name, email, phone, auth) values (?, ?, ?, ?, ?, 1)";
			
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, this.user_id);
			pstmt.setString(3, this.name);
			pstmt.setString(4, this.email);
			pstmt.setString(5, this.phone);
				
			int r = pstmt.executeUpdate();
			
			if (r == 0) {
				System.out.println("데이터값에 이상이 있습니다.");
			} else {
				System.out.println("학생 정보가 입력되었습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// select
	public void userSelect() {
		
	}
	
	// update
	public void userUpdate() {
		
	}
	
	// delete
	public void userDelete() {
		
	}
}
