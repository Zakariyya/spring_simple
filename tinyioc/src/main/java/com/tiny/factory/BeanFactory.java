package com.tiny.factory;

import com.tiny.BeanDefinition;

/**
 * 定义了beanFactory的基本规范,bean的声明周期管理
 * @author anan
 *
 */
public interface BeanFactory {
	
	Object getBean(String name);
	
	//把bean注入到hashmao当中去
	void registerBeanDefintion(String name,BeanDefinition beanDefintion)throws Exception;
	
	
}
