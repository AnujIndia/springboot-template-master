package com.allstate.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allstate.springboot.domain.Policy;


@RestController
@RequestMapping("testswagger")
public class SwagggerTest {
	  private static final Logger logger = LoggerFactory.getLogger(SwagggerTest.class);

	@RequestMapping(method = RequestMethod.GET)
	public String test(){
		
		return "Welcome in to Swagger Framework";
	};
	
    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public void custom(@RequestBody Policy policy) {
    	 logger.info("Executing createPolicy method." + policy.getAge());
      
    }

    
}
