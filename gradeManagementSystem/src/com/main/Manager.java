package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
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
	public boolean login(String user_id, String passowrd) {
		if(this.id != null && this.password != null)
		{
			if(this.id.equals(user_id))
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
			System.out.println("ID가 존재하지 않습니다.");
		
		return false;
	}
	
	/*
	 * 회원가입 
	 */
	public void join() {
		boolean chk = true;
		String sql;
		while(chk) {
			System.out.print("아이디 입력 :");
			this.id = scan.nextLine();
			if(this.id.equals("") && this.id != null) {
				System.out.println("필수 입력 값입니다!");
			} else {
				sql = String.format("SELECT COUNT(*) FROM insa.manager where id ='" + this.id + "'");
				
				Connection conn;
				try {
					conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.getInt(1) >= 1) {
						System.out.println("중복된 아이디입니다.");
					}else {
						System.out.println("사용할 수 있는 아이디입니다.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.print("ID를 입력해주세요 :");
		this.id = scan.nextLine();
		
		
		System.out.print("패스워드를 입력해주세요 :");
		this.password = scan.nextLine();
		while(true)
		{
			System.out.print("패스워드를 다시 입력해주세요 :");
			if(!this.password.equals(scan.nextLine()))
			{
				System.out.println("패스워드가 일치하지 않습니다.");
			}else
				break;
		}
		
		System.out.print("이름을 입력해주세요 :");
		this.name = scan.nextLine();
		System.out.print("이름을 입력해주세요 :");
		this.name = scan.nextLine();
		
		System.out.println("회원 가입에 성공하셨습니다."
				+ "\n로그인해주세요.");
	}
}