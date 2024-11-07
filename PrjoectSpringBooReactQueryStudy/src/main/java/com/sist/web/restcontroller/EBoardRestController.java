package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.EBoardRepository;
import com.sist.web.entity.*;

import java.text.SimpleDateFormat;
import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class EBoardRestController {
    @Autowired
    private EBoardRepository bDao;
    
    @GetMapping("/board/list/{page}")
    public ResponseEntity<Map> eboard_list(@PathVariable("page") int page){
    	Map map=new HashMap();
    	System.out.println("page="+page);

	    	int rowSize=10;
	    	Pageable pg=PageRequest.of(page-1, rowSize,Sort.by(Sort.Direction.DESC,"id"));
	    	// 페이지 나누기 => Limit (Database) => ElasticSearch는 전체 데이터를 가지고 온다
	    	Page<EBoard> pList;
	    	List<EBoard> list=new ArrayList<EBoard>();
	    	try {
		    	pList=bDao.findAll(pg); // 정렬후에 => 데이터를 10개 
		    	
		    	if(pList!=null && pList.hasContent()) // 값 존재 확인 
		    	{
		    		list=pList.getContent(); // Page => List로 변환 
		    	}
		    	
		    	int count=(int)bDao.count();
		    	int totalpage=(int)(Math.ceil(count/10.0));
		    	
		   
			    	map.put("list", list);
			    	map.put("curpage",page);
			    	map.put("totalpage", totalpage);
			    	map.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			    	System.out.println(totalpage);
			    	// new => 클래스 저장 
			    return new ResponseEntity<>(map,HttpStatus.OK);
	    	}catch(Exception ex) {
	    		
	    		map.put("list", list);
		    	map.put("curpage",1);
		    	map.put("totalpage", 0);
		    	map.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    		return new ResponseEntity<>(map,HttpStatus.OK);
	    	}
    	
    }
    @PostMapping("/board/insert")
    public ResponseEntity<Map> eboard_insert(@RequestBody EBoard vo)
    {
    	Map map=new HashMap();
    	String result="";
    	EBoard _vo=new EBoard();
    	try
    	{
    		// 1. id , 2.  hit , 3. regdate
    		vo.setHit(0);
    		vo.setId(idMaxData());
    		vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    		
    		_vo=bDao.save(vo);
    		
    		result="yes";
    		map.put("vo", _vo);
    		map.put("msg", result);
    	}catch(Exception ex)
    	{
    		result=ex.getMessage();
    	}
    	return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    // 시퀀스 
    public int idMaxData() {
    	int max=0;
    	try
    	{
	    	int rowSize=10;
	    	int start=0; // (page-1)*rowSize 
	    	Pageable pg=PageRequest.of(start, rowSize,Sort.by(Sort.Direction.DESC,"id"));
	    	// 페이지 나누기 => Limit (Database) => ElasticSearch는 전체 데이터를 가지고 온다
	    	Page<EBoard> pList=bDao.findAll(pg); // 정렬후에 => 데이터를 10개 
	    	List<EBoard> list=new ArrayList<EBoard>();
	    	if(pList!=null && pList.hasContent()) // 값 존재 확인 
	    	{
	    		list=pList.getContent(); // Page => List로 변환 
	    		max=list.get(0).getId();
	    	}
    	}catch(Exception ex)
    	{
    		max=0;
    	}
    	return max+1;
    }
    // 상세보기
    @GetMapping("/board/detail/{id}")
    public ResponseEntity<EBoard> board_detail(@PathVariable("id") int no)
    {
 	   EBoard vo=null;
 	   try
 	   {
 		   vo=bDao.findById(no).get();
 		   vo.setHit(vo.getHit()+1);
 		   bDao.save(vo); // 조회수 증가
 		   vo=bDao.findById(no).get();
 	   }catch(Exception ex)
 	   {
 		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
 	   }
 	   return new ResponseEntity<>(vo,HttpStatus.OK);
    }
    @GetMapping("/board/update/{id}")
    public ResponseEntity<EBoard> board_update(@PathVariable("id") int no)
    {
 	     EBoard vo=new EBoard();
 	     try
 	     {
 	    	 vo=bDao.findById(no).get();
 	     }catch(Exception ex)
 	     {
 	    	 return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
 	     }
 	     return new ResponseEntity<>(vo,HttpStatus.OK);
    }
   
    @PutMapping("/board/update_ok/{id}")
    public ResponseEntity<Map> board_update_ok(@PathVariable("id") int no,
 		   @RequestBody EBoard vo)
    {
 	     
 	     Map map=new HashMap();
 	     try
 	     {
 	    	 EBoard dbvo=bDao.findById(no).get();
 	    	 if(vo.getPwd().equals(dbvo.getPwd()))// 수정
 	    	 {
 	    		 vo.setId(no);
 	    		 vo.setHit(dbvo.getHit());
 	    		 vo.setRegdate(dbvo.getRegdate());
 	    		 bDao.save(vo);// save() = insert/update 
 	    		 // no가 있는 경우(update) / no가 없는 경우(insert) 
 	    		 map.put("msg", "yes");
 	    	 }
 	    	 else
 	    	 {
 	    		 map.put("msg", "no");
 	    	 }
 	     }catch(Exception ex)
 	     {
 	    	 return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
 	    	 // onError
 	     }
 	     return new ResponseEntity<>(map,HttpStatus.OK); // onSuccess
    }
    /*
    @GetMapping("eboard/delete_ok_react")
    public String eboard_delete(int id,String pwd)
    {
    	String result="";
    	EBoard vo=bDao.findById(id).get();
    	if(vo.getPwd().equals(pwd))
    	{
    		bDao.delete(vo);
    		result="yes";
    	}
    	else
    	{
    		result="no";
    	}
    	return result;
    }
    */
    @DeleteMapping("/board/delete/{id}/{pwd}")
    //-------------------------------------- Mutation
    public ResponseEntity<Map> board_delete(@PathVariable("id") int id,
 		   @PathVariable("pwd") String pwd)
    {
 	   Map map=new HashMap();
 	   try
 	   {
 		   EBoard vo=bDao.findById(id).get();
 		   if(pwd.equals(vo.getPwd()))
 		   {
 			   bDao.delete(vo);
 			   map.put("msg", "yes");
 		   }
 		   else
 		   {
 			   map.put("msg", "no");
 		   }
 		   
 	   }catch(Exception ex)
 	   {
 		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
 		   //onError
 	   }
 	   return new ResponseEntity<>(map,HttpStatus.OK);
 	   // onSuccess
    }
    
}