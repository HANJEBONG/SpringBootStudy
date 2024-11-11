package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class CommentRestController {
	private CommentService cService;
	public CommentRestController(CommentService service) {
		this.cService=service;
	}
	
	@GetMapping("comment/list/{cate}/{pno}/{page}")
	public ResponseEntity<Map> commentListData(@PathVariable("cate")int cate,@PathVariable("pno")int pno,@PathVariable("page")int page){
		Map map=new HashMap();
		try {
			
			int rowSize=6;
			int start=(rowSize*page)-rowSize;
			List<CommentEntity> list=cService.commentList(cate,pno,start);
			
			int total=cService.commentTotal(cate,pno);
			int totalpage=(int)(Math.ceil(total/(double)rowSize));
			
			final int BLOCK = 5;
			int startPage = ((page-1)/BLOCK*BLOCK)+1;
			int endPage = ((page-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("cList", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("total",total);
			
		}catch(Exception ex) {
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("comment/insert")
	public ResponseEntity<Map> commentInsert(@RequestBody CommentEntity vo){
		Map map=new HashMap();
		try {
			cService.commentInsert(vo);
			map.put("msg", "yes");
		}catch(Exception ex) {
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
