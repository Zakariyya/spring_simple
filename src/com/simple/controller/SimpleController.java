package com.simple.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simple.annotion.AutoWrited;
import com.simple.annotion.Controller;
import com.simple.annotion.RequestMapping;
import com.simple.service.SimpleService;

@Controller("simple")
public class SimpleController {

	@AutoWrited("simpleServiceImpl")
	SimpleService simpleService;
	
	@RequestMapping("get")
	public String getSimple(HttpServletRequest req, HttpServletResponse rsp, String param){
		
		return simpleService.get();
	}
	
	
	
}
