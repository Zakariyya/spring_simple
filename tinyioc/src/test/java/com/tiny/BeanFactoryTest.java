package com.tiny;

import java.util.Map;

import org.junit.Test;

import com.tiny.factory.AbstractBeanFactory;
import com.tiny.factory.AutowireCapableBeanFactory;
import com.tiny.io.ResourceLoader;
import com.tiny.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {

	@Test
	public void test() throws Exception{
		
		/**
		 * 定义一个xml reader
		 */
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
		
		/**
		 * 初始化BeanFactory并注册Bean
		 */
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		for(Map.Entry<String, BeanDefinition> beanDefinitionEntry:xmlBeanDefinitionReader.getRegistry().entrySet()){
			beanFactory.registerBeanDefintion(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}
		
		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloworld();
	}
	
	
	
	
}

















