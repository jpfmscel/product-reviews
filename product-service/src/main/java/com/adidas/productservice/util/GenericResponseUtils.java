package com.adidas.productservice.util;

import java.util.Arrays;

import com.adidas.productservice.dto.GenericResponse;

public class GenericResponseUtils {

	public static GenericResponse buildGenericResponseOK(Object result) {
		return GenericResponse.builder().status("ok").code(200).result(result).build();
	}

	public static GenericResponse buildGenericResponseOK(String message) {
		return GenericResponse.builder().status("ok").code(200).messages(Arrays.asList(message)).build();
	}

	public static GenericResponse buildGenericResponseError(Throwable t) {
		return GenericResponse.builder().status("error").code(400).messages(Arrays.asList(t.getMessage())).build();
	}
}
