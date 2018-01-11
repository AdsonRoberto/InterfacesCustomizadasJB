package org.jb.codegen.generator.project;

import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;

public abstract class EnumGenerator extends Generator {
	protected EnumDescription e;
	protected DescriptionDictionary dictionary;
	
	public EnumGenerator(EnumDescription e, DescriptionDictionary dictionary) {
		super();
		this.e = e;
		this.dictionary = dictionary;
	}
	
	public EnumGenerator(EnumDescription e, DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
		super(messager, filer, element);
		this.e = e;
		this.dictionary = dictionary;
	}
}
