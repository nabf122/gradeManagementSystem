package main;

import java.util.Scanner;

public class MainClass {
	/*
	 * 1. 성적 입력하기
	 * 2. 성적 수정하기
	 * 3. 성적 조회하기
	 * 4. 성적 삭제하기
	 * 5. 로그인
	 * 6. 계정 생성
	 */
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String str = null;
		Boolean loopChk = true;
		Boolean chk;
		
		ExamList examlist = new ExamList();
		
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
				break;
			}else if(str.charAt(0) == '2')	// 회원 가입
			{
				
			}else if(str.charAt(0) == '3')	// 끝내기
			{
				scan.close();
				System.out.println("SYSTEM : 종료됩니다.");
				loopChk = false;
				
			}else
				System.out.println("SYSTEM : 잘못된 입력입니다. 다시 입력해주세요.");
		}
		
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│       [성적 관리 시스템]         │");
		System.out.println("└──────────────────────────────┘");
		
		while(loopChk)
		{
			System.out.println("1. 입력하기\n2. 출력하기\n3. 전체출력하기\n"
					+ "4. 수정하기\n5. 삭제하기\n6. 끝내기");
			System.out.print(":");
			str = scan.nextLine();
			
			if(str.charAt(0) == '1')	// 입력하기
			{
				examlist.addData();
			}else if(str.charAt(0) == '2')	// 출력하기
			{
				System.out.println("출력할 이름을 입력하세요.");
				System.out.print(":");
				str = scan.nextLine();
				chk = examlist.selectData(str);
				if(chk == true)	{	
				}else
					System.out.println("SYSTEM : 입력한 사용자가 없습니다.");
				
			}else if(str.charAt(0) == '3')	// 전체 출력하기
			{
				examlist.selectAllData();
			}
			else if(str.charAt(0) == '4')	// 수정하기
			{
				System.out.println("수정할 이름을 입력하세요.");
				System.out.print(":");
				str = scan.nextLine();
				examlist.updateData(str);
					
			}else if(str.charAt(0) == '5')	// 삭제하기
			{
				System.out.println("삭제할 이름을 입력하세요.");
				System.out.print(":");
				str = scan.nextLine();
				chk = examlist.deleteData(str);
				if(chk == true)
				{
					System.out.println("SYSTEM : 삭제되었습니다.");
				}else
					System.out.println("SYSTEM : 입력한 사용자가 없습니다.");
				
			}else if(str.charAt(0) == '6')	// 끝내기
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
