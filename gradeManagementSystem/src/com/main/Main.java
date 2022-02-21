package com.main;

import java.util.Scanner;

import com.main.dao.DBConn;

public class Main {
	/*
	 * 1. 성적 입력하기
	 * 2. 성적 수정하기
	 * 3. 성적 조회하기
	 * 4. 성적 삭제하기
	 * 5. 로그인
	 * 6. 계정 생성
	 */
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String str = null;
		String m_id = null;
		String user_id = null;
		String password = null;
		int num = 0;
		Boolean loopChk = true;
		Boolean chk;
		
		ExamList_new examlist = new ExamList_new();
		Manager mUser = new Manager();
		
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│       [성적 관리 시스템]         │");
		System.out.println("└──────────────────────────────┘");
	
		/*
		 * 로그인 또는 회원 가입
		 */
		while(loopChk)
		{
			System.out.println("1. 로그인\n2. 회원 가입\n3. 끝내기");
			System.out.print(":");
			str = scan.nextLine();
			
			if(str.charAt(0) == '1')	// 로그인 ID/PWD 입력
			{
				System.out.print("ID :");
				m_id = scan.nextLine();
				System.out.print("Password :");
				password = scan.nextLine();
				if(mUser.login(m_id, password) == true)
				{
					System.out.println("Welcome "+ mUser.getName() + "\n"
							+ "login id : "+ mUser.getId() + "\n");
					break;
				}
			}else if(str.charAt(0) == '2')	// 회원 가입
			{
				mUser.join();
			}else if(str.charAt(0) == '3')	// 끝내기
			{
				scan.close();
				System.out.println("SYSTEM : 종료됩니다.");
				loopChk = false;
				
			}else
				System.out.println("SYSTEM : 잘못된 입력입니다. 다시 입력해주세요.");
		}
	
		while(loopChk)
		{
			System.out.println("1. 입력하기\n2. 조회하기\n3. 수정하기\n"
					+ "4. 삭제하기\n5. 학생 관리\n6. 학과 관리\n7. 끝내기");
			System.out.print(":");
			
			str = scan.nextLine();
			
			// 입력하기
			if(str.equals("1"))	
			{
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│          성 적 입 력           │ ");
				System.out.println("└──────────────────────────────┘");
				System.out.println("입력할 학번을 입력하세요.");
				System.out.print(":");
				user_id = scan.nextLine();
				
				chk = examlist.addData(user_id);
				
				if(chk == true)	{ }
			}
			// 조회하기
			else if(str.equals("2"))
			{
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│          성 적 조 회           │ ");
				System.out.println("└──────────────────────────────┘");
				System.out.println("조회할 학번을 입력하세요.");
				System.out.print(":");
				user_id = scan.nextLine();
				
				chk = examlist.selectData(user_id);
				
				if(chk == true)	{ }
			}
			/* // 수정하기
			else if(str.equals("3"))
			{
				System.out.println("수정할 학번을 입력하세요.");
				System.out.print(":");
				num = scan.nextInt();
				 chk = examlist.updateData(num);
				if(chk == true)	{	
				}else
					System.out.println("SYSTEM : 입력한 사용자가 없습니다.");		
			} */
			// 삭제하기
			else if(str.equals("4"))
			{
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│          성 적 삭 제           │ ");
				System.out.println("└──────────────────────────────┘");
				System.out.println("삭제할 학번을 입력하세요.");
				System.out.print(":");
				
				user_id = scan.nextLine();
				chk = examlist.deleteData(user_id);
				if(chk == true){ }
			}
			// 학생 관리하기
			else if(str.equals("5"))
			{
				
			}
			// 학과 관리하기
			else if(str.equals("6"))
			{
				
			}
			// 끝내기
			else if(str.equals("7"))
			{
				scan.close();
				System.out.println("SYSTEM : 종료됩니다.");
				loopChk = false;
			}else
				System.out.println("SYSTEM : 잘못된 입력입니다. 다시 입력해주세요.");
		}
		
		scan.close();
	}
}