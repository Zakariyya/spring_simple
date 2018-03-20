package com.tiny;

import java.util.HashMap;
import java.util.Map;

import com.tiny.io.ResourceLoader;

/**
 * 模板类
 * @author anan
 *
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

	private Map<String,BeanDefinition> registry;
	
	private ResourceLoader resourceLoader;
	
	protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
		this.registry = new HashMap<String,BeanDefinition>();
		this.resourceLoader = resourceLoader;
	}

	public Map<String, BeanDefinition> getRegistry() {
		return registry;
	}

	public void setRegistry(Map<String, BeanDefinition> registry) {
		this.registry = registry;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public String toString() {
		return "AbstractBeanDefinitionReader [registry=" + registry + ", resourceLoader=" + resourceLoader + "]";
	}
	
	
}
