package com.sist.web.service;

import com.sist.web.dao.*;
import com.sist.web.entity.*;
import java.util.*;

import org.springframework.data.repository.query.Param;



public interface RecipeService {
	public List<RecipeVO> recipeTop7();
	public int recipeTotal();
	public List<RecipeVO> gitaRecipeTop9();
	public List<RecipeVO> ramenRecipeTop9();
	public RecipeEntity findByNo(int no);
	public void save(RecipeEntity vo);
	public List<RecipeVO> recipeList(int start);
	public int count();
	
}
