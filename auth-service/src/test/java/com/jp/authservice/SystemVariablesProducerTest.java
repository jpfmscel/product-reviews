package com.jp.authservice;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.security.Key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemVariablesProducerTest {

	@Autowired
	Key secretKey;

	@Test
	public void shouldFail_When_Key_Not_Provided_In_VM_Arguments() {
		assertThat("Environment variable -Dkey.secret not PROVIDED!", StringUtils.isEmpty(secretKey),
				is(false));
	}

}
