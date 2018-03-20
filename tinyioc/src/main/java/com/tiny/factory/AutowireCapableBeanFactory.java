package com.tiny.factory;


import java.lang.reflect.Field;

import com.tiny.BeanDefinition;
import com.tiny.PropertyValue;

/**
 * 
 * @author anan
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{

	@Override
	Object doCreateBean(BeanDefinition beanDefinition) throws Exception {

		Object bean = beanDefinition.getBeanClass().newInstance();
		beanDefinition.setBean(bean);
		applyPropertyValues(bean,beanDefinition);
		
//		return beanDefintion.getBeanClass().newInstance();
		return bean;
	}
	
	private void applyPropertyValues(Object bean, BeanDefinition mdb) throws Exception{
		
		for(PropertyValue propertyValue:mdb.getPropertyValues().getProperyValues()){
			Field field = bean.getClass().getDeclaredField(propertyValue.getName());
			field.setAccessible(true);//可见性
			field.set(bean, propertyValue.getValue());
			
		}
		
		
	}
	
}


















