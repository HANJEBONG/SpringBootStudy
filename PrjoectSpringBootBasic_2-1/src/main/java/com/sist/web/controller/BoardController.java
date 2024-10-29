package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board/list")
	public String board_list(String page,Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-rowSize;
		List<BoardData> list=dao.boardListData(start);
		int count=(int)dao.count();
		int totalpage = (int)(Math.ceil(count/12.0));
		
		final int BLOCK=5;
		
		
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("totalpage",totalpage);
		
		
		return "board/list";
	}
	
	@GetMapping("board/detail")
	public String board_detail(int no,Model model) {
		BoardEntity vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		
		model.addAttribute("vo",vo);
		
		return "board/detail";
	}
	
	@GetMapping("board/delete")
	public String board_delete(int no,Model model) {
		
		model.addAttribute("no",no);
		return "board/delete";
	}
	
	@GetMapping("board/delete_ok")
	@ResponseBody
	public String board_delete(int no,String pwd) {
		BoardEntity vo=dao.findByNo(no);
		String result="";
		if(pwd.equals(vo.getPwd())) {
			dao.delete(vo);
			result="<script>"
					+ "location.href=\"/board/list\""
					+ "</script>";
		}else {
			result="<script>"
					+ "alert(\"password failed...\");"
					+ "history.back();"
					+ "</script>";
		}
		
		
		
		return result;
	}
}
