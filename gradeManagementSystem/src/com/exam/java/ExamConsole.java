package com.exam.java;

import java.util.Scanner;

public class ExamConsole {

	/*
	 * 입/출력을 담당하는 클래스
	 */
	private ExamList list = new ExamList();
	
	// print data
	public void printData()
	{
		printData(list.size());
	}
	
	public void printData(int size)
	{
		System.out.println("성적 출력");
		
		for(int i = 0; i < size; i ++)
		{
			Exam exam = list.getList(i);
			
			if(exam == null) {
				System.out.println("null");
				System.out.println();
			}else {
				int s_num = exam.getS_num();
				String name = exam.getName();
				int kor = exam.getKor();
				int eng = exam.getEng();
				int math = exam.getMath();
				int com = exam.getCom();
				
				int total = exam.total();
				int avg = exam.avg(total);
				
				System.out.println("학번 : " + s_num);
				System.out.println("이름 : " + name);
				System.out.println("국어 : " + kor);
				System.out.println("영어 : " + eng);
				System.out.println("수학 : " + math);
				System.out.println("컴퓨터 : " + com);
				System.out.println("-----------------" );
				System.out.println("총합 : " + total);
				System.out.println("평균 : " + avg);
				System.out.println("-----------------" );
			}
		}
	}
	
	// input data
	public void inputData()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("성적 입력");
		
		int s_num;
		String name;
		int kor, eng, math, com;
		
		System.out.print("학번 : ");
		s_num = scan.nextInt();
		
		System.out.print("이름 : ");
		name = scan.nextLine();
				
		do {
			System.out.print("국어 : ");
			kor = scan.nextInt();
			if(kor < 0 || kor > 100) {
				System.out.println("0~100까지의 숫자를 입력해주세요.");
			}
		} while(kor < 0 || kor > 100);
		do {	
			System.out.print("영어 : ");
			eng = scan.nextInt();
			if(eng < 0 || eng > 100) {
				System.out.println("0~100까지의 숫자를 입력해주세요.");
			}
		} while(eng < 0 || eng > 100);
		do {
			System.out.print("수학 : ");
			math = scan.nextInt();
			if(math < 0 || math > 100) {
				System.out.println("0~100까지의 숫자를 입력해주세요.");
			}
		} while(math < 0 || math > 100);
		do {
			System.out.print("컴퓨터 : ");
			com = scan.nextInt();
			if(com < 0 || com > 100) {
				System.out.println("0~100까지의 숫자를 입력해주세요.");
			}
		}while(com < 0 || com > 100);
		
		Exam exam = new Exam(s_num, name, kor, eng, math, com);
		
		list.addList(exam);
	}
	
	//remove data
	public void reomveData()
	{
		int s_num;
		Scanner scan = new Scanner(System.in);
		System.out.println("성적 삭제");
		
		System.out.print("학번 : ");
		s_num = scan.nextInt();
		
		list.removeList(s_num);
	}
}
