package com.sist.web.dao;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;

@Repository
public interface CommentDAO extends JpaRepository<CommentEntity, Integer>{
	@Query(value = "SELECT * FROM comment WHERE cate = :cate AND pno = :pno ORDER BY rno DESC "
			+ "LIMIT :start,5",nativeQuery = true)
	public List<CommentEntity> commentList(@Param("cate")int cate , @Param("pno")int pno,@Param("start")int start);
	
	@Query(value = "SELECT COUNT(*) FROM comment WHERE cate = :cate AND pno = :pno")
	public int commentTotal(@Param("cate")int cate,@Param("pno")int pno);
}
