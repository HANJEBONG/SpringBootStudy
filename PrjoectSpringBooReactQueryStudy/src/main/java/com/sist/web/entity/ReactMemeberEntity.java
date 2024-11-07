package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "reactmember")
@Data
public class ReactMemeberEntity {
	@Id
	private int no;
	private String id,name,sex,pwd;
}
