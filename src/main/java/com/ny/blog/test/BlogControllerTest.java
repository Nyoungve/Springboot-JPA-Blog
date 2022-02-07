package com.ny.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {
	@GetMapping("/test/hello") //http://localhost:8000/blog/test/hello
	public String hello() {
		return "<h1>hello spring boot nynyny222</h1>"; 
	}
}
