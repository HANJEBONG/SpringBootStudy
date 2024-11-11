package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.dao.CommentDAO;
import com.sist.web.entity.CommentEntity;

@Service
public class CommentServiceImpl implements CommentService{
	private CommentDAO cDao;
	
	public CommentServiceImpl(CommentDAO dao) {
		this.cDao=dao;
	}

	@Override
	public List<CommentEntity> commentList(int cate, int pno,int start) {
		// TODO Auto-generated method stub
		return cDao.commentList(cate,pno,start);
	}

	@Override
	public int commentTotal(int cate,int pno) {
		// TODO Auto-generated method stub
		return cDao.commentTotal(cate,pno);
	}

	@Override
	public void commentInsert(CommentEntity vo) {
		// TODO Auto-generated method stub
		cDao.save(vo);
	}

	
}
