package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeVO;
import java.util.*;
@Repository
public interface RecipeDAO extends JpaRepository<RecipeEntity, Integer>{
	@Query(value = "SELECT no,title,explane,thumb,subbadge,hit,ingerdient FROM ramen ORDER BY hit DESC "
			+ "LIMIT 0,7",nativeQuery = true)
	public List<RecipeVO> recipeTop7();
	
	
	@Query(value = "SELECT no,title,explane,thumb,subbadge,hit,ingerdient FROM ramen WHERE subbadge = '라면' ORDER BY hit DESC "
			+ "LIMIT 0,9",nativeQuery = true)
	public List<RecipeVO> ramenRecipeTop9();
	
	@Query(value = "SELECT no,title,explane,thumb,subbadge,hit,ingerdient FROM ramen WHERE subbadge = '냉면/소바' ORDER BY hit DESC "
			+ "LIMIT 0,9",nativeQuery = true)
	public List<RecipeVO> gitaRecipeTop9();
	
	public RecipeEntity findByNo(int no);
}
