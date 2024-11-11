package com.sist.web.service;

import java.util.List;

import com.sist.web.entity.CommentEntity;

public interface CommentService {
	public List<CommentEntity> commentList(int cate,int pno,int start);
	public int commentTotal(int cate,int pno);
	public void commentInsert(CommentEntity vo);
}
