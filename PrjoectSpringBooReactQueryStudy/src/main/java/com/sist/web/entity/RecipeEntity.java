package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
 * title varchar(4000) 
explane text 
ingerdient text 
thumb varchar(500) 
recipe text 
relproduct text 
ykey varchar(4000) 
tip text 
subbadge varchar(4000)
 */
@Entity(name = "ramen")
@Data
public class RecipeEntity {
	@Id
	private int no;
	private String title,explane,ingerdient,thumb,recipe,relproduct,ykey,tip,subbadge;
	private int hit;
}
