package com.tiny.io;

import java.net.URL;

/**
 * 读取 Resource 流
 * @author anan
 *
 */
public class ResourceLoader {
	
	public Resource getResource(String location){
		URL url = this.getClass().getClassLoader().getResource(location);//获取当前文件的url
		return new UrlResource(url);
	}
	
}
