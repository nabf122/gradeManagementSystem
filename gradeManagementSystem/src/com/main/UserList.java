package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.main.bean.DeptBean;
import com.main.bean.UserBean;
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
		System.out.print("학생 추가(1)\n학생 조회(2)\n학생 수정(3)\n학생 삭제(4)\n#");
		
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
				return ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("이름 : ");
		this.name = scan.nextLine();
		
		System.out.print("이메일 : ");
		this.email = scan.nextLine();
		
		System.out.print("전화번호 : ");
		this.phone = scan.nextLine();
		
		// 학과 체크하기
		try {
			sql = "SELECT DEPT_NAME AS DEPTNAME FROM insa.user_dept";
			
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			ArrayList<DeptBean> list = new ArrayList<DeptBean>();
			
			while(rs.next()) {
				DeptBean bean = new DeptBean(); 
				bean.setDept_name(rs.getString("DEPTNAME"));
				list.add(bean);
			}
			
			if(list.size() > 0) {
				System.out.println("학과");
				System.out.println("-----");
				
				//결과물 출력
				for(int i=0; i<list.size(); i++) 
				{
					System.out.println(list.get(i).getDept_name());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.print("학과명 : ");
		this.dept_name = scan.nextLine();
		// 학과 체크하기
		try {
			sql = "SELECT no, dept_name FROM insa.user_dept WHERE dept_name ='"+ this.dept_name +"'";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			rs.next();
			if(this.dept_name.equals(rs.getString("dept_name")))
			{
				this.dept_no = rs.getInt("no");
			}else
			{
				System.out.println("존재하지 않는 학과명입니다.");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 입력한 학생 정보 데이터 insert
		try {
			sql = "INSERT INTO insa.user_info (dept_no, id, name, email, phone, auth) values (?, ?, ?, ?, ?, 1)";
			
			// conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, this.dept_no);
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
		
		System.out.print("단일 학번 조회하기(1)\n전체 학번 조회하기(2)\n:");
		String a = scan.nextLine();
		
		if(a.equals("1")) {
			try {
				System.out.print("학번 : ");
				a = scan.nextLine();
				
				sql = "SELECT COUNT(*) FROM insa.user_info WHERE id ='"+ a +"'";
				
				conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				
				if(rs.getInt(1) <= 0)
				{
					System.out.println("등록되지 않은 학번입니다.");
					return ;
				}else
					this.user_id = a;
			} catch (Exception e) {
				// TODO: handle exception
			}
						
			sql = "SELECT ui.id as ID, ud.dept_name AS DEPTNAME, ui.name AS NAME, ui.email AS EMAIL, ui.phone AS PHONE, IF(auth = '1', '1학년','2학년') AS AUTH"
					+ " FROM insa.user_info ui, insa.user_dept ud WHERE ui.dept_no = ud.no AND ui.id = '"+ this.user_id +"'";
		}else {
			sql = "SELECT ui.id as ID, ud.dept_name AS DEPTNAME, ui.name AS NAME, ui.email AS EMAIL, ui.phone AS PHONE, IF(auth = '1', '1학년','2학년') AS AUTH"
					+ " FROM insa.user_info ui, insa.user_dept ud WHERE ui.dept_no = ud.no";
		}
		
		try {
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
									
			//결과를 담을 ArrayList생성
			ArrayList<UserBean> list = new ArrayList<UserBean>();
			
			while(rs.next())
			{
				UserBean bean = new UserBean(); 
				bean.setId(rs.getString("ID")); 
				bean.setDept_name(rs.getString("DEPTNAME")); 
				bean.setName(rs.getString("NAME")); 
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPhone(rs.getString("PHONE")); 
				bean.setAuth(rs.getString("AUTH")); 
				list.add(bean);
			}
			
			if(list.size() > 0) {
				System.out.println("학번 | 학과 | 이름 | 이메일 | 전화번호 | 학년");
				
				//결과물 출력
				for(int i=0; i<list.size(); i++) 
				{
					System.out.println(list.get(i).getId() +" | "+list.get(i).getDept_name()+" | "+list.get(i).getName()
							+" | "+list.get(i).getEmail()+" | "+list.get(i).getPhone()+" | "+list.get(i).getAuth());
				}
			}else
				System.out.println("학생 정보가 없습니다.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// update
	public void userUpdate() {
		
	}
	
	// delete
	public void userDelete() {
		System.out.println("학생 정보를 삭제합니다.");
		
		System.out.print("학번 : ");
		this.user_id = scan.nextLine();
		
		try {
			sql = "SELECT COUNT(*) FROM insa.user_info WHERE id ='"+ this.user_id +"'";
			
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			rs.next();

			if(rs.getInt(1) <= 0) {
				System.out.println("학생 정보가 없습니다.");
			}
				
			System.out.print(rs.getInt(1) + "건이 검색되었습니다.\n정말 삭제하시겠습니까?(y/n)\n#");
			if(scan.nextLine().equals("y") || scan.nextLine().equals("Y")) {
				sql = "DELETE FROM insa.user_info WHERE id ='"+ this.user_id +"'";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
				System.out.println("학생 정보가 삭제되었습니다.");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
