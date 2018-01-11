package org.jb.codegen.dictionary.data;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.common.dictionary.project.ProjectConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;

public class DescriptionDictionary {
	ProjectConfiguration projectConfiguration = null;
	HashMap<String,ClassDescription> classes = null;
	HashMap<String,EnumDescription> enums = null;

	HashMap<String,Element> elements = null;
	
	public DescriptionDictionary() {
		super();
		projectConfiguration = new ProjectConfiguration();
		classes = new HashMap<String,ClassDescription>();
		enums = new HashMap<String,EnumDescription>();

		elements = new HashMap<String,Element>();
	}
	
	public Integer getTotalClasses() {
		if(classes != null)
			return classes.size();
		return 0;
	}
	
	public Integer getTotalEnums() {
		if(enums != null)
			return enums.size();
		return 0;
	}

	public Integer getTotalElements() {
		if(elements != null)
			return elements.size();
		return 0;
	}

	public ProjectConfiguration getProjectConfiguration() {
		return projectConfiguration;
	}

	public void setProjectConfiguration(ProjectConfiguration projectConfiguration) {
		this.projectConfiguration = projectConfiguration;
	}

	public HashMap<String, ClassDescription> getClasses() {
		return classes;
	}

	public HashMap<String, EnumDescription> getEnums() {
		return enums;
	}

	public HashMap<String, Element> getElements() {
		return elements;
	}

	public boolean containsClassWithKey(String key) {
		return classes.containsKey(key);
	}
	
	public boolean containsEnumWithKey(String key) {
		return enums.containsKey(key);
	}

	public boolean containsElementWithKey(String key) {
		return elements.containsKey(key);
	}
	
	public ClassDescription getClass(String key) {
		return classes.get(key);
	}
	
	public EnumDescription getEnum(String key) {
		return enums.get(key);
	}

	public Element getElement(String key) {
		return elements.get(key);
	}

	public void putClass(ClassDescription c) {
		if(!containsClassWithKey(c.getCanonicalName())) {
			classes.put(c.getCanonicalName(), c);
			c.setIndex(classes.size());
			c.setDictionary(this);
		}
	}

	public void putEnum(EnumDescription e) {
		if(!containsEnumWithKey(e.getCanonicalName())) {
			enums.put(e.getCanonicalName(), e);
			e.setIndex(enums.size());
			e.setDictionary(this);
		}
	}

	public void putElement(Element e) {
		if(!containsElementWithKey(e.toString())) {
			elements.put(e.toString(), e);
		}
	}

	public void putAllClasses(Collection<ClassDescription> collection) {
		Iterator<ClassDescription> it = collection.iterator();
		while(it.hasNext()) {
			putClass(it.next());
		}
	}

	public void putAllEnums(Collection<EnumDescription> collection) {
		Iterator<EnumDescription> it = collection.iterator();
		while(it.hasNext()) {
			putEnum(it.next());
		}
	}

	public void putAllElements(Collection<Element> collection) {
		Iterator<Element> it = collection.iterator();
		while(it.hasNext()) {
			putElement(it.next());
		}
	}
	
	public Collection<ClassDescription> getClassValues() {
		return classes.values();
	}
	
	public Collection<EnumDescription> getEnumValues() {
		return enums.values();
	}

	public Collection<Element> getElementValues() {
		return elements.values();
	}

	public List<ClassDescription> getSubClasses(ClassDescription c) {
		List<ClassDescription> list = new ArrayList<ClassDescription>();
		for (ClassDescription cd : classes.values()) {
			if(cd.getSuperClass().equals(c.getCanonicalName())) {
				list.add(cd);
			}
		}
		return list;
	}

	public List<ClassDescription> getConcreteSubClasses(ClassDescription c) {
		List<ClassDescription> list = new ArrayList<ClassDescription>();
		for (ClassDescription cd : classes.values()) {
			if(cd.getSuperClass().equals(c.getCanonicalName()) && !cd.containsModifier("ABSTRACT")) {
				list.add(cd);
			}
		}
		return list;
	}
}
