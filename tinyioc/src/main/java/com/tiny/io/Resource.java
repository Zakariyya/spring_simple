package com.tiny.io;

import java.io.InputStream;

/**
 * 用于定义获取资源
 * @author anan
 *
 */
public interface Resource {

	InputStream getInputStream() throws Exception;
	
}






