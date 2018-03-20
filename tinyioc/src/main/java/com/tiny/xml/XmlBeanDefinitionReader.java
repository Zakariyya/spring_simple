package com.tiny.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tiny.AbstractBeanDefinitionReader;
import com.tiny.BeanDefinition;
import com.tiny.PropertyValue;
import com.tiny.io.ResourceLoader;


public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
	
	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		
		super(resourceLoader);
	}

	/**
	 * 获取输入流
	 * @param location
	 * @throws Exception
	 */
	@Override
	public void loadBeanDefinitions(String location) throws Exception {
		InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
		doLoadBeanDefinitions(inputStream);
	}

	/**
	 * 读取输入流到doc
	 * @param inputStream
	 * @throws Exception
	 */
	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream);
		// 解析bean
		registerBeanDefinitions(doc);
		inputStream.close();
	}

	/**
	 * 通过doc对象获取元素
	 * @param doc
	 */
	public void registerBeanDefinitions(Document doc) {
		Element root = doc.getDocumentElement();

		parseBeanDefinitions(root);
	}

	/**
	 * 从根对象开始遍历dom树
	 * @param root
	 */
	protected void parseBeanDefinitions(Element root) {
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				processBeanDefinition(ele);
			}
		}
	}
	/**
	 * 解析每个节点
	 * @param ele
	 */
	protected void processBeanDefinition(Element ele) {
		String name = ele.getAttribute("id");
		String className = ele.getAttribute("class");
		BeanDefinition beanDefinition = new BeanDefinition();
		processProperty(ele, beanDefinition);
		beanDefinition.setBeanClassName(className);
		getRegistry().put(name, beanDefinition);
	}
	
	/**
	 * 分析每个节点的property的方法
	 * @param ele
	 * @param beanDefinition
	 */
	private void processProperty(Element ele, BeanDefinition beanDefinition) {
		NodeList propertyNode = ele.getElementsByTagName("property");
		for (int i = 0; i < propertyNode.getLength(); i++) {
			Node node = propertyNode.item(i);
			if (node instanceof Element) {
				Element propertyEle = (Element) node;
				String name = propertyEle.getAttribute("name");
				String value = propertyEle.getAttribute("value");
				beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
//				if (value != null && value.length() > 0) {
//					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
//				} else {
//					String ref = propertyEle.getAttribute("ref");
//					if (ref == null || ref.length() == 0) {
//						throw new IllegalArgumentException("Configuration problem: <property> element for property '"
//								+ name + "' must specify a ref or value");
//					}
//					BeanReference beanReference = new BeanReference(ref);
//					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
//				}
			}
		}
	}


}
