package com.adidas.reviewservice.util;

import java.util.Arrays;

import com.adidas.reviewservice.dto.GenericResponse;

public class GenericResponseUtils {

	public static GenericResponse buildGenericResponseOK(Object data) {
		return GenericResponse.builder().status("ok").code(200).data(data).build();
	}

	public static GenericResponse buildGenericResponseOK(String message) {
		return GenericResponse.builder().status("ok").code(200).messages(Arrays.asList(message)).build();
	}

	public static GenericResponse buildGenericResponseError(Throwable t) {
		return GenericResponse.builder().status("error").code(400).messages(Arrays.asList(t.getCause().getMessage())).build();
	}

	public static GenericResponse buildGenericResponseError(Object body) {
		return GenericResponse.builder().status("error").code(400).data(body)
				.build();
	}
	
	public static GenericResponse buildGenericResponseError(String message, Object body) {
		return GenericResponse.builder().status("error").code(400).messages(Arrays.asList(message)).data(body)
				.build();
	}

	public static GenericResponse buildGenericResponseError(String message) {
		return GenericResponse.builder().status("error").code(400).messages(Arrays.asList(message)).build();
	}
}
