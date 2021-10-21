package com.exam.java;

public class Exam {
	private int s_num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int com;
		
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

	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	public int getCom() {
		return com;
	}
	public void setCom(int com) {
		this.com = com;
	}
	
	public Exam()
	{
		this(0 ,"default_Student" ,0, 0, 0, 0);
	}
	
	public Exam(int s_num, String name, int kor, int eng, int math, int com)
	{
		this.s_num = s_num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.com = com;
	}
	
	// 총 점수
	public int total()
	{
		return this.kor + this.eng + this.math + this.com;
	}
	
	// 평균 점수
	public int avg(int total)
	{
		return total/4;
	}
}
