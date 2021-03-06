package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.main.dao.DBConn;

public class ExamList {
	private int user_no;
	private String user_id;
	private String user_name;
	
	private int kor;
	private int eng;
	private int mat;
	private int sci;
	private int sum;
	
	public String sql;
	
	// db 연결 관련 초기화
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Scanner scan = new Scanner(System.in);
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getSci() {
		return sci;
	}
	public void setSci(int sci) {
		this.sci = sci;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	// 성적 입력
	public void addData(String user_id)
	{
		sql = String.format("SELECT no, id FROM insa.user_info where id ='" + user_id + "'");
						
		try {
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 이미 등록된 학번인지 체크하기
			if(rs.next()) {
				this.user_no = rs.getInt(1);
				if(user_id.equals(rs.getString(2)))
				{
					this.user_id = rs.getString(2);
					
					// 해당 학번으로 이미 등록된 성적이 있는지 확인하는 SQL
					sql = "SELECT count(*) FROM insa.user_grades WHERE no in (SELECT no FROM insa.user_info WHERE id ='" + this.user_id + "')";
					
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					rs.next();
					
					if(rs.getInt(1) >= 1) {
						System.out.println("입력하신 학번으로 이미 등록된 성적이 있습니다.");
					}
				}else
				{
					System.out.println("등록되지 않은 학번입니다.");
				}
			}
			
			// 성적 입력 받기
			while(true)
			{
					System.out.print("국어 : ");
					kor = scan.nextInt();
					System.out.print("영어 : ");
					eng = scan.nextInt();
					System.out.print("수학 : ");
					mat = scan.nextInt();
					System.out.print("과학 : ");
					sci = scan.nextInt();
					
					if(kor >= 0 && kor <= 100 &&
							eng >= 0 && eng <= 100 &&
							mat >= 0 && mat <= 100 &&
							sci >= 0 && sci <= 100)
					{
						sql = "insert into insa.user_grades (user_no, kor, eng, mat, sci) values (?, ?, ?, ?, ?)";
						
						try {	// 값 받아오기
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setInt(1, this.user_no); 
							pstmt.setInt(2, this.kor); 
							pstmt.setInt(3, this.eng); 
							pstmt.setInt(4, this.mat); 
							pstmt.setInt(5, this.sci);
							
							// 테이블에 insert 수행하기
							int r = pstmt.executeUpdate();
							
							if (r == 0) {
								System.out.println("데이터값에 이상이 있습니다.");
							} else {
								System.out.println("성적 입력이 되었습니다.");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else
						System.out.println("0 ~ 100의 숫자만 입력하세요.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// 성적 조회
	public void selectData(String user_id)
	{
		sql = String.format("SELECT count(*) FROM insa.user_info ui, insa.user_grades ug WHERE ui.no = ug.user_no AND ui.id ='" + user_id + "'");
								
		try {
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
					
			Statement stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);
					
			// 등록된 학번인지 체크하기
			rs.next();
			
			if(rs.getInt(1) <= 0)
			{
				System.out.println("등록되지 않은 학번이거나 등록된 성적이 없습니다.");
			}else
			{
				sql = String.format("SELECT ui.id, ui.name, ug.kor, ug.eng, ug.mat, ug.sci FROM insa.user_info ui, insa.user_grades ug WHERE ui.no = ug.user_no AND ui.id ='" + user_id + "'");

				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				rs.next();
			
				this.user_id = rs.getString(1);
				this.user_name = rs.getString(2);
				this.kor = rs.getInt(3);
				this.eng = rs.getInt(4);
				this.mat = rs.getInt(5);
				this.sci = rs.getInt(6);
				
				sum = sum(this.kor, this.eng, this.mat, this.sci);
				System.out.println();
				System.out.println(toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 성적 수정
	/*
	public boolean updateData(int s_num)
	{
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│          성 적 수 정           │ ");
		System.out.println("└──────────────────────────────┘");
		for(int i = 0 ; i < MAX_SIZE; ++i)
		{
			if(arraylist.get(i).s_num == s_num && arraylist.get(i) != null)
			{
				while(true)
				{
					System.out.print("국어 : ");
					kor = scan.nextInt();
					System.out.print("영어 : ");
					eng = scan.nextInt();
					System.out.print("수학 : ");
					mat = scan.nextInt();
					System.out.print("과학 : ");
					sci = scan.nextInt();
					
					if(kor >= 0 && kor <= 100 &&
							eng >= 0 && eng <= 100 &&
							mat >= 0 && mat <= 100 &&
							sci >= 0 && sci <= 100)
					{
						break;
					}else
						System.out.println("0 ~ 100의 숫자만 입력하세요.");
				}
				
				arraylist.set(i, new ExamTable(s_num,arraylist.get(i).name,kor,eng,mat,sci));
				System.out.println("SYSTEM : 수정되었습니다.");
				
				return true;
			}
		}
		return false;
	}
	
	*/
	
	// 성적 삭제
	public void deleteData(String user_id)
	{
		sql = String.format("SELECT count(*) FROM insa.user_info ui, insa.user_grades ug WHERE ui.no = ug.user_no AND ui.id ='" + user_id + "'");
										
		try {
			conn = DriverManager.getConnection(DBConn.url, DBConn.user, DBConn.password);
							
			stmt = conn.createStatement();	
			rs = stmt.executeQuery(sql);

			rs.next();
					
			if(rs.getInt(1) <= 0)
			{
				System.out.println("등록되지 않은 학번이거나 등록된 성적이 없습니다.");
			}else
			{
				this.user_id = user_id;
				
				sql = String.format("DELETE FROM insa.user_grades WHERE user_no IN (SELECT no FROM insa.user_info WHERE id ='"+ this.user_id +"') ");
				
				pstmt = conn.prepareStatement(sql);
				int r = pstmt.executeUpdate();
				
				System.out.println(this.user_id + "학번의 성적이 삭제되었습니다.");
				System.out.println(r + "건의 데이터가 삭제되었습니다.");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	int sum(int a, int b, int c, int d)
	{
		return a+b+c+d;
	}
	
	@Override
	public String toString() {
		return "[학번=" + this.user_id + ", 이름=" + this.user_name + ", 국어=" + this.kor + ", 영어=" + this.eng + ", 수학="
				+ this.mat + ", 과학=" + this.sci + ", 총합=" + this.sum + ", 평균=" + (double)this.sum/4 + "]\n";
	}
	
	
	
	
}