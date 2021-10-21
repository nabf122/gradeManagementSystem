package com.exam.java;

import java.util.Scanner;

public class Program {
	
	public static void main(String[] args)
	{
		/*
		 * 1. 성적 입력하기
		 * 성적 수정하기 -
		 * 2. 성적 조회하기
		 * 3. 성적 삭제하기
		 * 4. 프로그램 종료하기
		 */
		int menu;
		ExamConsole list = new ExamConsole();
		
		while(true) {
			menu = menuList();

			if(menu == 1)
			{
				list.inputData();
			}else if(menu == 2)
			{
				list.printData(size());
			}else if(menu == 3)
			{
				list.reomveData();
			}else
				System.exit(0);
		}
	}
	
	public static int menuList()
	{
		int menu;
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while(loop) {
			System.out.println("1. 성적 입력하기");
			System.out.println("2. 성적 출력하기");
			System.out.println("3. 성적 삭제하기");
			System.out.println("4. 프로그램 종료하기");
			menu = scan.nextInt();
			if(menu >= 1 && menu <= 4)
			{
				return menu;
			}
		}
		return 0;
	}
	
	public static int size()
	{
		int size;
		Scanner scan = new Scanner(System.in);
		
		size = scan.nextInt();
				
		return size;
	}
}
