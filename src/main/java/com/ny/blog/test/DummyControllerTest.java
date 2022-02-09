package com.ny.blog.test;


import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ny.blog.model.RoleType;
import com.ny.blog.model.User;
import com.ny.blog.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 controller = RestController 
@RestController
public class DummyControllerTest {
	
	@Autowired //DummyControllerTest가 메모리에 뜰 때 UserRepository 인터페이스도 메모리에 띄워준다. DI 의존성 주입.
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		//userRepository.deleteById(id); //이렇게만 하면.... 위험하다..없는 id 삭제요청해서 에러날수도있음. try catch
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패 하였습니다... id :["+id+"]는 존재하지 않습니다.  ";
		}
		return  "삭제되었습니다. id : "+id;
	}
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json 데이터 요청=>Java Object(메세지 컨버터의 Jackson라이브러리가 변환해서 받아준다.)
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
//		requestUser.setId(id);
//		userRepository.save(requestUser); //이렇게 save처리하면 set으로 안넣은 값은 null로 들어감... 아래와 같이 처리해야지 보내는 값만 update처리 됨. 
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패..");
		});
		
		user.setEmail(requestUser.getEmail()); 
		user.setPassword(requestUser.getPassword());
		//userRepository.save(user); // @Transactional 선언하면 save함수 쓰지 않아도 update 처리됨...==> dirty checking
		return null;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	//한페이지당 두건의 데이터를 리턴 받겠다. Page<User> 페이지 타입, List<User> List 타입
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable); //아래 페이지 정보도 함께 뜨는데 뜨는거 싫을 시에 아래와 같이 List 타입으로 변경해본다.
		List<User> users = pagingUser.getContent(); //페이지 정보 리턴 안됨...^^
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을 수 있다. 
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//DB에 존재하지 않는 id를 찾을 경우, user는 null이 됨. 
		// return null이 되는 문제를 막기 위해서 Optional로 null인지 아닌지 판단해서 return 하도록 한다. 
		//Optional<User> user = userRepository.findById(id); 
		//return user.get();
		
//		//람다식으로 바꾸기 
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id); //에러메시지 노출된다.
			}
		});
		// 웹브라우저에서 요청이 들어오면, 자바오브젝트로 되어있는 User객체는 웹브라우저가 이해할 수 있는 데이터로 변환된다. -> json(Gson라이브러리)
		//스프링부트는 MessageConverter라는 애가 응답 시에 자동으로 작동하게 되어 있으며, 자바오브젝트 리턴시에 자동으로 Jackson라이브러리 호출해서 user오브젝트를 json으로 변환한 후 브라우저에 던져준다.
		
		return user;
	}
	
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
