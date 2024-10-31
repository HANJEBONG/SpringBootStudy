package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "project_food_house")
@Data
public class FoodHouseEntity {
	
	@Id // sequence
	private int fno;
	private int jjimcount,likecount,hit;
	private String name,type,phone,address,theme,poster,images,time,parking,content,rdays;
	private double score;
}