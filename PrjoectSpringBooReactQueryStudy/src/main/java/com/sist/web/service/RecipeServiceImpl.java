package com.sist.web.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Service
public class RecipeServiceImpl implements RecipeService{
	private RecipeDAO rDao;
	
	
	public RecipeServiceImpl(RecipeDAO dao) {
		this.rDao=dao;
	}
	
	@Override
	public List<RecipeVO> recipeTop7() {
		// TODO Auto-generated method stub
		return rDao.recipeTop7();
	}

	@Override
	public int recipeTotal() {
		// TODO Auto-generated method stub
		return (int)rDao.count();
	}

	@Override
	public List<RecipeVO> gitaRecipeTop9() {
		// TODO Auto-generated method stub
		return rDao.gitaRecipeTop9();
	}

	@Override
	public List<RecipeVO> ramenRecipeTop9() {
		// TODO Auto-generated method stub
		return rDao.ramenRecipeTop9();
	}

	@Override
	public RecipeEntity findByNo(int no) {
		// TODO Auto-generated method stub
		return rDao.findByNo(no);
	}

	@Override
	public void save(RecipeEntity vo) {
		// TODO Auto-generated method stub
		rDao.save(vo);
		
	}


	

}
