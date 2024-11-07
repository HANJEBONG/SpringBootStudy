package com.sist.web.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin(origins = "*")
public class RamenRestController {
	private RamenService mService;
	
	public RamenRestController(RamenService service) {
		this.mService=service;
	}
	
	@GetMapping("ramen/list/{page}")
	public ResponseEntity<Map> ramenList(@PathVariable("page")int page){
		Map map=new HashMap();
		try {
			int rowSize=6;
			int start=(rowSize*page)-rowSize;
			
			List<RamenVO> list=mService.ramenListData(start);
			int total=mService.count();
			int totalpage=(int)(Math.ceil(total/(double)rowSize));
			
			final int BLOCK = 4;
			int startPage = ((page-1)/BLOCK*BLOCK)+1;
			int endPage = ((page-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("List", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("ramen/detail/{code}")
	public ResponseEntity<RamenEntity> ramenDetail(@PathVariable("code")String code){
		RamenEntity vo=new RamenEntity();
		try {
			 vo=mService.findBycode(code);
			 vo.setHit(vo.getHit()+1);
			 mService.save(vo);
			 vo=mService.findBycode(code);
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo ,HttpStatus.OK);
	}
}
