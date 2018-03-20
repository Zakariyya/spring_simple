package com.tiny.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tiny.BeanDefinition;

/**
 * 
 * @author anan
 *
 */
public abstract class AbstractBeanFactory implements BeanFactory{

	
	private Map<String,BeanDefinition> beanDefintionMap = new ConcurrentHashMap<String, BeanDefinition>();
	
	@Override
	public Object getBean(String name){
		return beanDefintionMap.get(name).getBean();
	}


	@Override
	public void registerBeanDefintion(String name, BeanDefinition beanDefintion) throws Exception {
		Object bean = doCreateBean(beanDefintion);
		beanDefintion.setBean(bean);
		beanDefintionMap.put(name, beanDefintion);
		
	}
	
	
	abstract Object doCreateBean(BeanDefinition beanDefintion)throws Exception;
	
	
	
	
}
