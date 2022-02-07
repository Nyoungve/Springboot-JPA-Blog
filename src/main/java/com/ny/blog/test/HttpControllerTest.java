package com.ny.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller 
//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG="HttpControllerTest:";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member(1,"nyny","1234","email"); 
		Member m = Member.builder().userName("nyny").password("1234").email("mail").build();
		System.out.println(TAG + "getter : "+m.getId());
		m.setId(5000); //id를 5000으로 주깅...ㅎㅎ 
		System.out.println(TAG + "setter : "+m.getId());
		return "lombok test 완료";
	}
	//localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : "+m.getId()+",  "+m.getUserName()+", "+m.getEmail()+", "+m.getPassword();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 : "+m.getId()+",  "+m.getUserName()+", "+m.getEmail()+", "+m.getPassword();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+",  "+m.getUserName()+", "+m.getEmail()+", "+m.getPassword();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
