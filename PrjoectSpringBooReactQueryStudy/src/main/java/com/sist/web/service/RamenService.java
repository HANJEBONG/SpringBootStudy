package com.sist.web.service;

import java.util.*;

import com.sist.web.entity.*;

public interface RamenService {
	public List<RamenVO> remanTop5();
	public List<RamenVO> ramenListData(int start);
	public RamenEntity findBycode(String code);
	public int count();
	public void save(RamenEntity vo);
}
