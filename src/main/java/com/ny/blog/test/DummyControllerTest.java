package com.ny.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ny.blog.model.RoleType;
import com.ny.blog.model.User;
import com.ny.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //DummyControllerTest가 메모리에 뜰 때 UserRepository 인터페이스도 메모리에 띄워준다. DI 의존성 주입.
	private UserRepository userRepository;
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http body에 username,password,email 가지고 요청해보기
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		//ENUM 타입으로 바꾼 role 컬럼... 
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "TEST회원가입이 완료되었습니다.";
	}
}
