package com.ny.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
@Entity
public class Board {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(nullable=false, length = 100)
	private String title; 
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨...
	
	@ColumnDefault("0")
	private int count; 	//조회수

	//fetch=FetchType.EAGER << User테이블에서 userId들고 올 때 무조건 들고 와라. 
	@ManyToOne(fetch=FetchType.EAGER) //Many = Many, User = One
	@JoinColumn(name="userId")
	private User userId;  //DB는 오브젝트를 저장할 수 없으나, FK.. 자바는 오브젝트 저장 가능 
	
	//mappedBy 연관관계의 주인이 아니다. FK포린키가 아니다. 데이터베이스에 컬럼을 만들지 말라는 뜻. Board를 select할 때 join문을 통해서 값을 얻기 위해 필요한것...
	//mappedBy 뒤에는 Reply 테이블의  Board 객체를 적어 줌... 
	//fetch=FetchType.LAZY << 필요하면 들고오고 필요하지 않으면 안들고 온다. 기본 설정임.
	//Board 댓글 상세보기 클릭할 경우 Reply다 들고 와야함... 그래서 필수로 들고 오도록 EAGER전략으로 바꿔줌. 
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER) 
	private List<Reply> reply; 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
