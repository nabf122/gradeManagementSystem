package com.exam.java;

public class ExamList {
	private Exam[] exams;
	private int current;
	
	public ExamList(){
	      exams = new Exam[3];
	      current = 0;
	      }
	
	// add
	public void addList(Exam exam)
	{	
		Exam[] exams = this.exams;
		int size = this.current;
		
		if(exams.length == size)
		{
			// 크키가 5개 정도 더 큰 새로운 배열을 생성하세요.
			Exam[] temp = new Exam[size + 5];
			
			// 값을 이주시킨다.
			for(int i = 0; i < size; i ++)
			{
				temp[i] = exams[i];
			}
			
			// list.exams가 새로 만든 temp 배열을 참조하도록 한다.
			exams = temp;
		}
		
		exams[current] = exam;
		current++;
	}
	
	// remove
	public void removeList(int s_num)
	{
		for(int i = 0; i < current; i ++)
		{
			if(exams[i].getS_num() == s_num)
			{
				exams[i] = null;
				current--;
			}
		}
	}
	
	// get
	public Exam getList(int i) {
		// TODO Auto-generated method stub
		return this.exams[i];
	}

	// size
	public int size() {
		// TODO Auto-generated method stub
		return current;
	}
}
