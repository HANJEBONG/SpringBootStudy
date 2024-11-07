package com.sist.web.service;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RamenServiceImpl implements RamenService{
	private RamenDAO mDao;
	
	public RamenServiceImpl(RamenDAO dao) {
		this.mDao=dao;
	}

	@Override
	public List<RamenVO> remanTop5() {
		// TODO Auto-generated method stub
		return mDao.remanTop5();
	}

	@Override
	public List<RamenVO> ramenListData(int start) {
		// TODO Auto-generated method stub
		return mDao.ramenListData(start);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return (int)mDao.count();
	}

	@Override
	public RamenEntity findBycode(String code) {
		// TODO Auto-generated method stub
		return mDao.findBycode(code);
	}

	@Override
	public void save(RamenEntity vo) {
		// TODO Auto-generated method stub
		mDao.save(vo);
		
	}
}
