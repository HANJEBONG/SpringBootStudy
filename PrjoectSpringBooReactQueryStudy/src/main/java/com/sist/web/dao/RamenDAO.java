package com.sist.web.dao;
import com.sist.web.entity.*;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RamenDAO extends JpaRepository<RamenEntity, String>{
	@Query(value = "SELECT code,name,hit,kcal,poster,onetime,cate2,protein,carbohydrate,fat FROM proce_food ORDER BY hit DESC "
			+ "LIMIT 0,5",nativeQuery = true)
	public List<RamenVO> remanTop5();
	
	@Query(value = "SELECT code,name,hit,kcal,poster,onetime,cate2,protein,carbohydrate,fat FROM proce_food ORDER BY hit DESC "
			+ "LIMIT :start,6",nativeQuery = true)
	public List<RamenVO> ramenListData(@Param("start") int start);
	
	public RamenEntity findBycode(String code);
	
}
