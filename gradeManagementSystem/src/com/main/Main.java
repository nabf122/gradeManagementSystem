package com.main;

import java.util.Scanner;

public class Main {
	/*
	 * 1. 성적 입력하기
	 * 2. 성적 조회하기
	 * 3. 성적 수정하기
	 * 4. 성적 삭제하기
	 * 5. 학생 관리
	 * 6. 학과 관리
	 * 7. 끝내기
	 */
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String str = "";
		String m_id = "";
		String user_id = "";
		String password = "";
		boolean loopChk = true;
		boolean chk;
		
		// 선언
		ExamList examlist = new ExamList();
		UserList userlist = new UserList();
		Manager muser = new Manager();
		
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│       [성적 관리 시스템]         │");
		System.out.println("└──────────────────────────────┘");
	
		// 학사 관리 시스템 시작 페이지. 로그인 또는 회원 가입
		while(loopChk)
		{
			System.out.println("로그인(1)\n관리자 계성 생성(2)\n끝내기(3)");
			System.out.print("#");
			str = scan.nextLine();
			
			if(str.equals("1"))	// 로그인 ID/PWD 입력
			{
				System.out.print("User ID :");
				m_id = scan.nextLine();
				System.out.print("Password :");
				password = scan.nextLine();
				if(muser.login(m_id, password) == true)
				{
					System.out.println("Welcome "+ muser.getName() + "\nLogin ID :"+ muser.getId() + "\n");
					loopChk = false;
				}
			}else if(str.equals("2"))	// 관리자 계정 생성
			{
				muser.join();
			}else if(str.equals("3"))	// 끝내기
			{
				scan.close();
				System.out.println("프로그램이 종료됩니다.");
				loopChk = false;
			}else
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
		}
	
		loopChk = true;
		while(loopChk)
		{
			System.out.println("성적 입력(1)\n성적 조회(2)\n성적 수정(3)\n성적 삭제(4)\n학생 관리(5)\n학과 관리(6)\n프로그램종료(7)");
			System.out.print("#");
			str = scan.nextLine();
			
			// 성적 입력
			if(str.equals("1"))	
			{
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│          성 적 입 력           │ ");
				System.out.println("└──────────────────────────────┘");
				System.out.println("입력할 학번을 입력하세요.");
				System.out.print("#");
				user_id = scan.nextLine();
				
				examlist.addData(user_id);
			}
			
			// 성적 조회
			else if(str.equals("2"))
			{
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│          성 적 조 회           │ ");
				System.out.println("└──────────────────────────────┘");
				System.out.println("조회할 학번을 입력하세요.");
				System.out.print("#");
				
				user_id = scan.nextLine();
				examlist.selectData(user_id);
			}
			
			// 성적 수정
			else if(str.equals("3"))
			{
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│          성 적 수 정           │ ");
				System.out.println("└──────────────────────────────┘");
				System.out.println("수정할 학번을 입력하세요.");
				System.out.print("#");
				
				user_id = scan.nextLine();
				// examlist.updateData(user_id);
			}
			
			// 성적 삭제
			else if(str.equals("4"))
			{
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│          성 적 삭 제           │ ");
				System.out.println("└──────────────────────────────┘");
				System.out.println("삭제할 학번을 입력하세요.");
				System.out.print("#");
				
				user_id = scan.nextLine();
				examlist.deleteData(user_id);
			}
			// 학생 관리하기
			else if(str.equals("5"))
			{
				userlist.UserManagementSystem();
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