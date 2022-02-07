package com.ny.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id; 
	private String userName; 
	private String password; 
	private String email;
	
	//@AllArgsConstructor <<< 이 어노테이션과 똑같은 것이므로.... 위에거 주석잡고 쓰자. 
	@Builder // 객체를 만들고 싶은데 어떤값을 자동으로 증가하는 시퀀스 값으로 만들고 싶을 때..... 
	public Member(int id, String userName, String password, String email) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	
}
