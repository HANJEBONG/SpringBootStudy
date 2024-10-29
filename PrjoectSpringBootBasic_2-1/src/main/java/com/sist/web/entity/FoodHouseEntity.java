package com.sist.web.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

/*
 * 	VO
 * 	DTO
 *  Entity : 다른 데이터를 첨부할 수 없다 , Only 테이블 컬럼만 추가
 *  		 컬럼명과 동일
 *  		 => INSERT,UPDATE,DELETE 문장을 생성
 *  		 => SELECT : SQL문장 / 자동 SQL문장 생성
 *           => 검색
 *              findBy 컬럼명()
 *              => findByFno(int Fno)
 *              => WHERE fno=
 *              => 메소드로 SQL문장을 처리
 *              => JPA (Hibernate) => 자동 SQL문장 제작 : 메소드 패턴
 *              => address="" AND type=""
 *              => findByAddressAndType(String address,String type)
 *                 =================================================
 *                 SQL 제작도 가능
 */

@Entity(name = "project_food_house")
@Data
public class FoodHouseEntity {
	
	@Id // sequence
	private int fno;
	private int jjimcount,likecount,hit;
	private String name,type,phone,address,theme,poster,images,time,parking,content,rdays;
	private double score;
}
