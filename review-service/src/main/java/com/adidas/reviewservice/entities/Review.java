package com.adidas.reviewservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Review {

	@Id
	private String id;
	private String title;
	private String description;
	private Integer score;
	private String productId;
}
