package com.tiny;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
	
	public PropertyValues(){
		
	}
	
	public void addPropertyValue(PropertyValue pv){
		this.propertyValueList.add(pv);
	}
	
	public List<PropertyValue> getProperyValues(){
		return this.propertyValueList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
