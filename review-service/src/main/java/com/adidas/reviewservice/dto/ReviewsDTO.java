package com.adidas.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDTO {
	private String productId;
	private Double averageScore;
	private Integer quantity;
}
