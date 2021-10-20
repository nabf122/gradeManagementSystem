package main;

import java.util.ArrayList;
import java.util.Scanner;

class ExamTable {
	public int s_num; // 학번 
	public String name;
	public int kor;
	public int eng;
	public int mat;
	public int sci;
	
	public ExamTable(int s_num, String name, int kor, int eng, int mat, int sci) {
		this.s_num = s_num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sci = sci;
	}
}

public class ExamList {

	/*
	 * 성적 데이터 저장과 데이터 처리하는 클래스
	 * kor : 국어
	 * eng : 영어
	 * mat : 수학
	 * sci : 과학
	 * sum : 학점 총 합
	 * arg : 평균
	 */
	private int s_num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int sci;
	private int sum;
	private int users;
	
	Scanner scan = new Scanner(System.in);
	
	private ArrayList<ExamTable> arraylist;
	
	/*
	 * 입력 가능한 최대 수 제한
	 */
	public static final int MAX_SIZE = 10;
	
	/*
	 * null로 초기화
	 */
	public ExamList()
	{
		arraylist = new ArrayList<ExamTable>(MAX_SIZE);
		for (int i = 0; i < MAX_SIZE; i++)
			arraylist.add(null);
	}
	

	public int getS_num() {
		return s_num;
	}

	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	/*
	 * 1. 성적 입력하기
	 */
	public Boolean addData()
	{
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│          성 적 입 력           │ ");
		System.out.println("└──────────────────────────────┘");
		System.out.println();
		System.out.print("학번 : ");
		s_num = scan.nextInt();
		System.out.print("이름 : ");
		name = scan.nextLine();

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
		
		for(int i = 0 ; i < MAX_SIZE; ++i)
		{
			if(arraylist.get(i) == null)
			{
				arraylist.set(i, new ExamTable(s_num,name,kor,eng,mat,sci));
				users ++;
				System.out.println("SYSTEM : 입력되었습니다.");
				return true;
			}
		}
		System.out.println("SYSTEM : 입력되지 않았습니다.");
		return false;
	}
	
	/*
	 * 2. 성적 출력하기
	 */
	public Boolean selectData(int s_num)
	{
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│          성 적 출 력           │ ");
		System.out.println("└──────────────────────────────┘");
		
		for(int i = 0 ; i < MAX_SIZE; ++i)
		{
			if(arraylist.get(i).s_num == s_num && arraylist.get(i) != null)	// 동일한 이름이 맞다면
			{
				sum = sum(arraylist.get(i).kor, arraylist.get(i).eng
						, arraylist.get(i).mat, arraylist.get(i).sci);
				System.out.println();
				System.out.print("학번 : " + arraylist.get(i).s_num+ "\n");
				System.out.print("이름 : " + arraylist.get(i).name+ "\n");
				System.out.print("국어 : " + arraylist.get(i).kor + "\n");
				System.out.print("영어 : " + arraylist.get(i).eng + "\n");
				System.out.print("수학 : " + arraylist.get(i).mat + "\n");
				System.out.print("과학 : " + arraylist.get(i).sci + "\n");
				System.out.print("총합 : " + sum + "\n");
				System.out.print("평균 : " + (double)sum/4 + "\n");
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 3. 전체 성적 출력하기
	 */
	public void selectAllData()
	{
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│        전 체 성 적 출 력        │ ");
		System.out.println("└──────────────────────────────┘");
		
		for(int i = 0 ; i < MAX_SIZE; ++i)
		{
			if(arraylist.get(i) != null)
			{
				sum = sum(arraylist.get(i).kor, arraylist.get(i).eng
						, arraylist.get(i).mat, arraylist.get(i).sci);
				System.out.println("저장된 학생 수 : " + users);
				System.out.println();
				System.out.print("학번 : " + arraylist.get(i).s_num+ "\n");
				System.out.print("이름 : " + arraylist.get(i).name+ "\n");
				System.out.print("국어 : " + arraylist.get(i).kor + "\n");
				System.out.print("영어 : " + arraylist.get(i).eng + "\n");
				System.out.print("수학 : " + arraylist.get(i).mat + "\n");
				System.out.print("과학 : " + arraylist.get(i).sci + "\n");
				System.out.print("총합 : " + sum + "\n");
				System.out.print("평균 : " + (double)sum/4 + "\n");
				System.out.println("──────────────────────────────");
			}
		}
	}
	
	/*
	 * 4. 성적 수정하기
	 */
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
	
	/*
	 * 5. 성적 삭제하기
	 */
	public Boolean deleteData(int s_num)
	{
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│          성 적 삭 제           │ ");
		System.out.println("└──────────────────────────────┘");
		
		for(int i = 0 ; i < MAX_SIZE; ++i)
		{
			if(arraylist.get(i).s_num == s_num && arraylist.get(i).s_num == s_num && arraylist.get(i) != null)	// 동일한 이름이 맞다면
			{
				arraylist.set(i, null);
				users --;
				
				return true;
			}
		}
		return false;
	}
	
	public int totalUsers()
	{
		return users;
	}
	
	int sum(int a, int b, int c, int d)
	{
		return a+b+c+d;
	}
	
	/*
	 * 학번
	 */
	
}
