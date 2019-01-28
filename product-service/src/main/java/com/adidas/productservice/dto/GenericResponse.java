package com.adidas.productservice.dto;

import java.util.List;

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
public class GenericResponse {

	private String status;
	private Integer code;
	private List<String> messages;
	private Object result;
	
}
