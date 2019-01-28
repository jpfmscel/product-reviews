package com.adidas.reviewservice.controllers;

import java.util.Arrays;

import com.adidas.reviewservice.dto.GenericResponse;

public interface GenericController {

	default public GenericResponse buildGenericResponseOK(Object result) {
		return GenericResponse.builder().status("ok").code(200).result(result).build();
	}

	default public GenericResponse buildGenericResponseOK(String message) {
		return GenericResponse.builder().status("ok").code(200).messages(Arrays.asList(message)).build();
	}

	default public GenericResponse buildGenericResponseError(Throwable t) {
		return GenericResponse.builder().status("error").code(400).messages(Arrays.asList(t.getMessage())).build();
	}
}
