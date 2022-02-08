package com.ny.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
//ORM => Object Related Mapping : Object를 테이블에 매핑해주는 기술 
@Entity //User클래스가 MySQL에 테이블로 생성이 된다. 
public class User {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 데이터 베이스의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; //아이디 
	
	@Column(nullable = false, length = 100) //123456 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email; 
	
	//@ColumnDefault("'user'") //이렇게 할 경우 테이블에 default 값으로 user 들어 간거 확인 됨... 주석.
	//private String role; //Enum을 쓰는게 좋다. (어떤 데이터의 도메인을 만들어 줄 수 있다. ) admin, user, manager각 페이지마다 권한을 주는 role.... 
	// String이 아니라 Enum을 주게 되면 도메인...어떠한 범위를 정해서 각각의 권한을 줄 수 있게 된다. 
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp //시간이 자동 입력 된다. 
	private Timestamp createDate;
	
}
