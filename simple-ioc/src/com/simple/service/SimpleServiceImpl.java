package com.simple.service;

import com.simple.annotion.Service;

@Service("simpleServiceImpl")
public class SimpleServiceImpl implements SimpleService {

	@Override
	public String get() {
		System.out.println(" =======simpleService.get()  run");
		return "return simpleService.get()  run";
	}

}
 