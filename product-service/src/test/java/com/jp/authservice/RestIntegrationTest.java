package com.jp.authservice;

import java.util.HashMap;

public interface RestIntegrationTest {

	default public Object post(String url, Object body) {
		return null;
	}

	default public Object get(String url, HashMap<String, Object> params) {
		return null;
	}

	default public Object put(String url, Object body) {
		return null;
	}

	default public Object delete(String url, Object body) {
		return null;
	}
}
