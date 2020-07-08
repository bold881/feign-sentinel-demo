package com.example.feignsentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloSentinel {
	
	@GetMapping("/getSentinel")
	@SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
	public String getSentinel() {
		throw new RuntimeException("self runtime exception");
//		log.info("getSentinel is ok!");
//		return "do getSentinel";
	}
	
	public String helloFallback() {
		log.info("in helloFallback");
		return "helloFallback";
	}
	
	public String exceptionHandler(long s, BlockException ex) {
		ex.printStackTrace();
		return "Oops, error occured at " + s;
	}
}
