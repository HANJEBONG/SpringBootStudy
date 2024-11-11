package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;
/*
 * rno int AI PK 
id varchar(50) 
msg text 
regdate datetime 
cate int
 */
@Entity(name = "comment")
@Data
public class CommentEntity {
	@Id
	private int rno;
	private String id,msg,regdate;
	private int cate,pno;
	

	@PrePersist
    public void regdate() {
    	this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
    }
}
