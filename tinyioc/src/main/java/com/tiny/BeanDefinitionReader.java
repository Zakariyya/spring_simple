package com.tiny;

/**
 * 读取bean定义文件：xml
 * 职责分离
 * @author anan
 *
 */
public interface BeanDefinitionReader {
	
	void loadBeanDefinitions(String location)throws Exception;
}
