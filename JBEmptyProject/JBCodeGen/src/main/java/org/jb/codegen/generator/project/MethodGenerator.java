package org.jb.codegen.generator.project;

import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;

public abstract class MethodGenerator extends Generator {
	protected MethodDescription m;
	protected DescriptionDictionary dictionary;
	
	public MethodGenerator(MethodDescription m, DescriptionDictionary dictionary) {
		super();
		this.m = m;
		this.dictionary = dictionary;
	}
	
	public MethodGenerator(MethodDescription m, DescriptionDictionary dictionary,
						   Messager messager, Filer filer, Element element) {
		super(messager, filer, element);
		this.m = m;
		this.dictionary = dictionary;
	}
}
