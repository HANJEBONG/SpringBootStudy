package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class MainRestController {
	private RecipeService rService;
	private RamenService mService;
	
	public MainRestController(RecipeService rService,RamenService mService) {
		this.rService=rService;
		this.mService=mService;
	}
	
	@GetMapping("/main")
	public ResponseEntity<Map> mainData(){
		Map map=new HashMap();
		try {
			List<RecipeVO> fList=rService.recipeTop7();
			List<RecipeVO> twoData=new ArrayList<RecipeVO>();
			List<RecipeVO> threeData=new ArrayList<RecipeVO>();
			for(int i=1;i<4;i++) {
				twoData.add(fList.get(i));
			}
			for(int i=4;i<7;i++) {
				threeData.add(fList.get(i));
			}

			
			List<RamenVO> rList=mService.remanTop5();
			List<RecipeVO> rTList=rService.ramenRecipeTop9();
			List<RecipeVO> gTList=rService.gitaRecipeTop9();
			map.put("rList", rList);
			map.put("oneData", fList.get(0));
			map.put("twoData", twoData);
			map.put("threeData", threeData);
			map.put("ramenOne", rTList.get(0));
			map.put("ramenTwo", rTList.get(1));
			map.put("ramenThree", rTList.get(2));
			map.put("nangOne", gTList.get(0));
			map.put("nangTwo", gTList.get(1));
			map.put("nangThree", gTList.get(2));
			for(int i=0;i<=2;i++) {
				rTList.remove(i);
				gTList.remove(i);
			}
			map.put("rTList", rTList);
			map.put("gTList", gTList);
			
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
