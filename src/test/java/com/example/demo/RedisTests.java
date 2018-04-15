package com.example.demo;

import com.example.demo.util.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedisDemoApplication.class)
@ActiveProfiles(profiles = "dev")
public class RedisTests {

	@Autowired
	private RedisClient redisClient;

	@Test
	public void test1() {

		redisClient.set("test1_key2", "test1 redis value11111111111111111");

		Optional<String> str =  redisClient.get("test1_key2");
		System.out.println("str: ============================================================= ");
		System.out.println("str: "+str.get());
	}


	@Test
	public void test2() {

		Optional<String> str =  redisClient.get("test1_key1");
		System.out.println("str: ============================================================= ");
		System.out.println("str: "+str.get());
	}








}
