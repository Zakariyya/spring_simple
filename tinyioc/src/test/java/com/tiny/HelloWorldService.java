package com.tiny;

public class HelloWorldService {
	
	private String text;
	
	public void helloworld(){
		System.out.println(text);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
}
