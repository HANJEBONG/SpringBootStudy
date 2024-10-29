package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/*
 * 	지원자격
 * 		Java,Spring,
 * 
 * 	    Mysql
 *       1. pagination => LIMIT 시작,갯수
 *       2. LIKE => '%'||?||'%' => CONCAT('%',?,'%')
 *       3. DATE => DATETIME => sysdate : now()
 *       4. NVL => isnull
 *       
 *       => 오라클		mysql(MariaDB) => 3306 (driver동일)
 *       	NUMBER		int , double
 *       	VARCHAR2	varchar
 *       	CLOB		text
 *       	DATE		datetime
 */
import com.sist.web.entity.FoodHouseData;
import com.sist.web.entity.FoodHouseEntity;
@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{ //<참조하는 class , ID의 데이터형>
	// 목록출력
	@Query(value = "SELECT fno,poster,name FROM project_food_house ORDER BY fno ASC "
			+ "LIMIT :start,12",nativeQuery = true)
	// #{start} => :start
	public List<FoodHouseData> foodListData(@Param("start")int start);
	// 상세보기
	public FoodHouseEntity findByFno(int fno);
	// SELECT * FROM 테이블명 WHERE fno = ?
	// Hit 증가 = update (save())
	// 검색 ... 
	// CRUD
	
}
