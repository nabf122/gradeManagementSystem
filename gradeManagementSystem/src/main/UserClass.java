package main;

import java.util.Scanner;

public class UserClass {

	Scanner scan = new Scanner(System.in);
	
	private String user_id;
	private String password;
	private String name;
	// private
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
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
	
	/*
	 * 로그인 
	 */
	public boolean login(String user_id, String passowrd) {
		if(this.user_id != null && this.password != null)
		{
			if(this.user_id.equals(user_id))
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
		System.out.print("ID를 입력해주세요 :");
		this.user_id = scan.nextLine();
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
		
		System.out.println("회원 가입에 성공하셨습니다."
				+ "\n로그인해주세요.");
	}
}
