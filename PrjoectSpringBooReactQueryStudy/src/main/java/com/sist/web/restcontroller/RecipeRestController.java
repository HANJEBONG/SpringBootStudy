package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class RecipeRestController {
	private RecipeService rService;
	public RecipeRestController(RecipeService service) {
		this.rService=service;
	}
	
	@GetMapping("recipe/list/{curpage}")
	public ResponseEntity<Map> recipe_list(@PathVariable("curpage")int page){
		Map map=new HashMap();
		try {
			int rowSize=6;
			int start=(rowSize*page)-rowSize;
			List<RecipeVO> list=rService.recipeList(start);
			
			int total=rService.count();
			int totalpage=(int)(Math.ceil(total/(double)rowSize));
			
			final int BLOCK = 5;
			int startPage = ((page-1)/BLOCK*BLOCK)+1;
			int endPage = ((page-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("List", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("recipe/detail/{no}")
	public ResponseEntity<Map> recipeDetail(@PathVariable("no")int no){
		System.out.println(no);
		Map map=new HashMap();
		try {
			
			RecipeEntity vo=rService.findByNo(no);
			vo.setHit(vo.getHit()+1);
			rService.save(vo);
			vo=rService.findByNo(no);
			List<String> image=new ArrayList<String>();
			List<String> rec=new ArrayList<String>();
			
			
			String[] recipe=vo.getRecipe().split("\r\n");
			
			for(String ss:recipe) {
				String[] recipe2=ss.split("\\^");
				System.out.println(ss);
				System.out.println(recipe2[0]);
				
				System.out.println(recipe2[1]);
				image.add(recipe2[0]);
				rec.add(recipe2[1]);
			}
			
			
			map.put("vo", vo);
			map.put("image", image);
			map.put("rec", rec);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
}
