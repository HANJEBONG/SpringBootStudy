package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class MemberRestController {
	private MemberDAO mDao;
	public MemberRestController(MemberDAO dao) {
		this.mDao=dao;
	}
	
	@GetMapping("/member/login/{id}/{pwd}")
	public ResponseEntity<Map> memberLogin(@PathVariable("id")String id,@PathVariable("pwd")String pwd){
		Map map=new HashMap();
		try {
			int idCount=mDao.idCount(id);
			if(idCount==0) {
				map.put("msg", "NOID");
			}else {
				ReactMemeberEntity vo=mDao.findbyId(id);
				if(vo.getPwd().equals(pwd)) {
					map.put("msg", "OK");
					map.put("vo", vo);
				}else {
					map.put("msg", "NOPWD");
				}
			}
		}catch(Exception ex){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
