package com.ny.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Reply {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(nullable=false, length = 200)
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨...
	
	//@OneToOne//하나의 답변이 하나의 게시글이 올 수 있다. 
	@ManyToOne  //여러개의 답변이 하나의 게시글에 올 수 있음. 
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne//한명의 유저는 여러개의 답변을 가질 수 있다.
	@JoinColumn(name="userId")
	private User user; 
	
	@CreationTimestamp
	private Timestamp createDate;
}
