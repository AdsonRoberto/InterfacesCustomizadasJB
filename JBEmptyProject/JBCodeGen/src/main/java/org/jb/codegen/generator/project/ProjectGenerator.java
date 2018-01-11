package org.jb.codegen.generator.project;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;

public abstract class ProjectGenerator extends Generator {
	protected DescriptionDictionary dictionary;
	
	public ProjectGenerator(DescriptionDictionary dictionary) {
		super();
		this.dictionary = dictionary;
	}
	
	public ProjectGenerator(DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
		super(messager, filer, element);
		this.dictionary = dictionary;
	}

}
