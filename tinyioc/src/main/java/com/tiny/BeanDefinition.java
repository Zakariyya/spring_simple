package com.tiny;

/**
 * 定义了bean的元数据(POJO)
 * @author anan
 *
 */
public class BeanDefinition {

	/** 具体的类 */
	private Object bean;
	
	/** 类 */
	private Class beanClass;
	
	/** 类名称*/
	private String beanClassName;
	
	
	private PropertyValues propertyValues = new PropertyValues();

	
	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		try {
			this.beanClass = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	@Override
	public String toString() {
		return "BeanDefintion [bean=" + bean + ", beanClass=" + beanClass + ", beanClassName=" + beanClassName + "]";
	}
	
	
	
	
	
}
