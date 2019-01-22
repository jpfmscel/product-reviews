package com.jp.authservice.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

	@Id
	private String id;
	private String code;
	private String title;
	private String description;
	private List<Review> reviews;
	
	
}
