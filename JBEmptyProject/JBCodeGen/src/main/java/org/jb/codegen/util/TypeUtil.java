package org.jb.codegen.util;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class TypeUtil {
	public static String getSimpleTypeName(String typeName) {
		int index = typeName.indexOf("<");
		if(index > 0 && index < typeName.length())
			return typeName.substring(0, index);
		return typeName;
	}
	
	public static String getTypeOnColletion(String typeName) {
		int begin = typeName.indexOf("<");
		int end = typeName.indexOf(">");
		return typeName.substring(begin + 1, end);
	}
	
	public static boolean isList(String typeName) {
		try {
			Class<?> cl = Class.forName(TypeUtil.getSimpleTypeName(typeName));
			if(cl != null) {
				if(cl.isAssignableFrom(List.class) ||
						cl.isAssignableFrom(ArrayList.class)) {
					//Collection
					return true;
				}
				else {
					//Not Collection
					return false;
				}
			}
			else {
				//Not Collection
				return false;
			}
		} catch (ClassNotFoundException e) {
			//Not Collection
			return false;
		}
	}
	
	public static DataType dataType(AttributeDescription attr, DescriptionDictionary dictionary) {
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") || 
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {
			return DataType.SIMPLE;
		}
		else if(dictionary.containsEnumWithKey(attr.getType())) {
				return DataType.ENUM;
		}
		else if(dictionary.containsClassWithKey(attr.getType())) {
			return DataType.CLASS;
		}
		else if(attr.equals("java.util.List")) {
			return DataType.LIST;
		}		
		else if(attr.equals("java.util.Map")) {
			return DataType.MAP;
		}
		else if(attr.equals("java.util.Set")) {
			return DataType.SET;
		}
		else { 
			return DataType.UNKNOWN;
		}
	}
	
	public static DataType dataType(ParameterDescription param, DescriptionDictionary dictionary) {
		if(param.getType().equals("java.lang.Long") || param.getType().equals("long") || 
				param.getType().equals("java.lang.Integer") || param.getType().equals("int") || 
				param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
				param.getType().equals("java.lang.Double") || param.getType().equals("double") || 
				param.getType().equals("java.lang.Boolean") || param.getType().equals("boolean") || 
				param.getType().equals("java.lang.String") ||
				param.getType().equals("java.util.Date")) {
			return DataType.SIMPLE;
		}
		else if(dictionary.containsEnumWithKey(param.getType())) {
				return DataType.ENUM;
		}
		else if(dictionary.containsClassWithKey(param.getType())) {
			return DataType.CLASS;
		}
		else if(param.equals("java.util.List")) {
			return DataType.LIST;
		}		
		else if(param.equals("java.util.Map")) {
			return DataType.MAP;
		}
		else if(param.equals("java.util.Set")) {
			return DataType.SET;
		}
		else { 
			return DataType.UNKNOWN;
		}
	}

	public static String getResumedType(String type) {
		int dot = 0;
		for(int i = dot; i < type.length(); i++) {
			if(type.charAt(i) == '.') dot = i;
		}
		if(dot < type.length() - 1)
			return type.substring(dot + 1);
		return type;
	}

	public static Boolean isCollection(String type) {
		if(type.length() <= 0)
			return false;
		if(getSimpleTypeName(type).length() <= 0)
			return false;
		try {
			Class<?> c = Class.forName(getSimpleTypeName(type));
			if(c.isAssignableFrom(Collection.class)
					|| c.isAssignableFrom(AbstractList.class)
					|| c.isAssignableFrom(AbstractSet.class)) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			return false;
		}
		return false;
	}

	public static String collectionType(String type) {
		if(type.length() <= 0)
			return null;
		return getTypeOnColletion(type);
	}
}
