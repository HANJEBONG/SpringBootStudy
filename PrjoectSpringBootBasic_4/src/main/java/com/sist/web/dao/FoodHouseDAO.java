package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
	// 9개의 데이터 ==> main에 출력
	@Query(value = "SELECT fno,name,poster,score,hit,jjimcount,type,content,theme "
			+ "FROM project_food_house ORDER BY hit DESC "
			+ "LIMIT 0,9",nativeQuery = true)
	public List<FoodHouseVO> foodHitTop9();
		
	public FoodHouseEntity findByfno(int fno);
}
