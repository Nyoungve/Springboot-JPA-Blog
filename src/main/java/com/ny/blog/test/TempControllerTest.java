package com.ny.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일리턴 기본경로: src/main/resources/static
		//리턴명 : /home.html //앞에 슬래시가 꼭 있어야 한다 파일리턴되는거 뒤에 슬래시가 없으므로....
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/kny.jpg";
	}
	
	//위의 html, jpg파일과 달리...jsp파일은 정적인 파일이 아니다. 컴파일이 일어나기 때문에 브라우저가 인식을 못함.
	@GetMapping("/temp/jsp")
	public String tempJsp() {
	     // prefix: /WEB-INF/views/
	     // suffix: .jsp 
		//'/test.jsp'로 리턴하면 full 경로 : /WEB-INF/views//test.jsp.jsp 가 된다. 
		//'test'로 리턴해야지 /WEB-INF/views/test.jsp가 된다
		return "test";
	}
}
