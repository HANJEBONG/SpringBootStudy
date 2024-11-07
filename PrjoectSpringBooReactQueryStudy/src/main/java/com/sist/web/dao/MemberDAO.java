package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ReactMemeberEntity;

@Repository
public interface MemberDAO extends JpaRepository<ReactMemeberEntity, Integer>{
	@Query(value = "SELECT COUNT(*) FROM reactmember WHERE id =:id",nativeQuery = true)
	public int idCount(@Param("id") String id);
	
	@Query(value = "SELECT * FROM reactmember WHERE id =:id",nativeQuery = true)
	public ReactMemeberEntity findbyId(String id);
	
}