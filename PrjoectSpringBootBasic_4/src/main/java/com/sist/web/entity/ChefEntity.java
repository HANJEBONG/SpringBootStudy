package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "chef")
@Data
public class ChefEntity {
	@Id
	private String chef;
	private String poster;
	private String mem_cont1,mem_cont2,mem_cont3,mem_cont7;
	
}
