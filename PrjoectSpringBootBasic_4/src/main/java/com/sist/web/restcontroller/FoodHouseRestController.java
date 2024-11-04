package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.dao.*;

@RestController
@CrossOrigin(origins = "*")
// port가 같은 경우에만 접근이 가능
// => react => 3000 springboot => 80
public class FoodHouseRestController {
	@Autowired
	private FoodHouseDAO fDao;
	@Autowired
	private RecipeDAO rDao;
	@Autowired
	private ChefDAO cDao;
	@GetMapping("food/main_react")
	public Map foodMainTopData(){
		Map map=new HashMap();
		List<FoodHouseVO> fList=fDao.foodHitTop9();
		List<RecipeEntity> rList=rDao.recipeMainData();
		ChefEntity vo=cDao.findByChef("핑콩이");
		map.put("cvo", vo);
		map.put("fList", fList);
		map.put("rList", rList);
		return map;
	}
	@GetMapping("food/List")
	public Map foodListData(int page) {
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		
		List<FoodHouseVO> list=fDao.foodListData(start);
		int total=(int)fDao.count();
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
		
		return map;
	}
	@GetMapping("food/detail_react")
	public FoodHouseEntity food_detail(int fno) {
		FoodHouseEntity vo=fDao.findByfno(fno); // 쿠키에 저장이 안된다
		vo.setHit(vo.getHit()+1);
		fDao.save(vo);
		vo=fDao.findByfno(fno);
		return vo;
	}
	@GetMapping("food/find_react")
	public Map foodFindData(int page,String address) {
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		
		List<FoodHouseVO> list=fDao.foodFindData(start,address);
		int total=(int)fDao.foodFindTotalData(address);
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
		
		return map;
	}
}
