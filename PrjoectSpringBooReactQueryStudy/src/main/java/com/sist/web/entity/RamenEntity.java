package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * code text 
name text 
cate1 text 
cate2 text 
nutrition text 
kcal int 
protein double 
fat double 
carbohydrate double 
sugar double 
calcium text 
sodium int 
colas double 
fatacid double 
transacid int 
onetime text 
weight text 
company text 
poster varchar(
 */
@Entity(name = "proce_food")
@Data
public class RamenEntity {
	@Id
	private String code;

	private String name,cate1,cate2,nutrition,calcium,onetime,weight,company,poster;
	private int kcal,transacid,sodium,hit;
	private double protein,fat,carbohydrate,sugar,colas,fatacid;
}
