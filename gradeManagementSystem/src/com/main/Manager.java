package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.main.dao.DBConn;

public class Manager {

	Scanner scan = new Scanner(System.in);
	
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String use_flg;
	
	public String yorn;
	public String sql;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getUse_flg() {
		return use_flg;
	}
	public void setUse_flg(String use_flg) {
		this.use_flg = use_flg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * 로그인 
	 */
	public boolean login(String id, String passowrd) {
		sql = String.format("SELECT id, password, name, email, phone FROM insa.manager where id ='" + id + "'");
		
		// mysql db 연결하기
		Connection conn;
		try {
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			Statement stmt = conn.createStatement();
			
			// id로 된 this.id, this.password 가져오기
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				this.id = rs.getString(1);
				this.password = rs.getString(2);
				this.name = rs.getString(3);
				this.email = rs.getString(4);
				this.phone = rs.getString(5);
			}
			
			if(this.id != null && this.password != null)
			{
				if(this.id.equals(id))
				{
					if(this.password.equals(passowrd))
					{
						System.out.println("로그인에 성공하셨습니다.");
						return true;
					}else
						System.out.println("ID 또는 Password가 틀렸습니다."
								+ "\n다시 시도해주세요.");
						return false;
				}else
					System.out.println("ID 또는 Password가 틀렸습니다."
							+ "\n다시 시도해주세요.");
					return false;
			}else
				System.out.println("ID 또는 Password가 틀렸습니다."
						+ "\n다시 시도해주세요.");
			
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 회원가입 
	 */
	public void join() {
		boolean chk = true;
		// String sql;
		
		// id 생성
		while(chk) {
			System.out.print("신규 아이디 입력 :");
			this.id = scan.nextLine();
			if(this.id.equals("") || this.id == null || this.id.equals(" ")) {
				System.out.println("필수 입력 값입니다. 올바른 값을 입력해주세요.");
			} else {
				sql = String.format("SELECT COUNT(*) FROM insa.manager where id ='" + this.id + "'");
				
				// mysql db 연결하기
				Connection conn;
				try {
					conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
					
					Statement stmt = conn.createStatement();
					
					// db에서 중복된 값인지 조회해서 체크하기
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						// int a = rs.getInt(1);
					}
					
					if(rs.getInt(1) >= 1) {
						System.out.println("중복된 아이디입니다.");
					}else {
						System.out.println("사용할 수 있는 아이디입니다.");
						System.out.print("사용하시겠습니까?(y/n) : ");
						this.yorn = scan.nextLine();
						if(yorn.equals("y")) {
							chk = false;
						}else {	}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// flag 초기화
		yorn = null;
		chk = true;
		
		// password 생성
		while(chk)
		{
			System.out.print("패스워드를 입력해주세요 :");
			this.password = scan.nextLine();
			if(this.password.equals("") || this.password == null || this.password.equals(" ")) {
				System.out.println("필수 입력 값입니다. 올바른 값을 입력해주세요.");
			} else {
				System.out.print("패스워드를 다시 한번 입력해주세요 :");
				if(!this.password.equals(scan.nextLine()))
				{
					System.out.println("패스워드가 일치하지 않습니다.");
				}else { 
					chk = false;
				}
			}
		}
		
		// flag 초기화
		yorn = null;
		chk = true;
				
		// name 생성
		while(chk)
		{
			System.out.print("이름를 입력해주세요 :");
			this.name = scan.nextLine();
			if(this.name.equals("") || this.password == null || this.password.equals(" ")) {
				System.out.println("필수 입력 값입니다. 올바른 값을 입력해주세요.");
			} else {
				chk = false;
			}	
		}

		// flag 초기화
		yorn = null;
		chk = true;
						
		// email 생성
		while(chk)
		{
			System.out.print("이메일을 입력해주세요 :");
			this.email = scan.nextLine();
			chk = false;	
		}		
		
		// flag 초기화
		yorn = null;
		chk = true;
						
		// phone 생성
		while(chk)
		{
			System.out.print("전화번호를 입력해주세요 :");
			this.phone = scan.nextLine();
			chk = false;
		}	
		
		// 최종 id 생성 체크
		System.out.println(toString());
		System.out.print("입력하신 정보로 회원 가입하시겠습니까?(y/n) : ");
		this.yorn = scan.nextLine();
		if(yorn.equals("y")) {
			
			// sql 쿼리
			sql = "insert into insa.manager (id, name, password, email, phone) values (?, ?, ?, ?, ?)";
			
			// mysql db 연결하기
			Connection conn;
			try {
				conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
				
				// 값 받아오기
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, this.id); 
				pstmt.setString(2, this.name); 
				pstmt.setString(3, this.password); 
				pstmt.setString(4, this.email); 
				pstmt.setString(5, this.phone);
				
				// 테이블에 insert 수행하기
				int r = pstmt.executeUpdate();
				// System.out.println("변경된 row : " + r);
				
				if (r == 0) {
					System.out.println("데이터값에 이상이 있습니다.");
				} else {
					System.out.println("회원 가입에 성공하셨습니다.\n로그인해주세요.");
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {	
			return ;
		}
	}
	
	@Override
	public String toString() {
		return "join info\n[id : " + id + ", password : " + password + ", name : " + name + ", email : " + email + ", phone : "
				+ phone + "]";
	}
}