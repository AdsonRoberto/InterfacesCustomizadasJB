package org.jb.codegen.generator.project;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;

public abstract class EntityGenerator extends Generator {
	protected Integer requestCode = 0, resultCode = 0;
	protected ClassDescription c;
	protected DescriptionDictionary dictionary;
	
	public EntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super();
		this.c = c;
		this.dictionary = dictionary;
	}
	
	public EntityGenerator(ClassDescription c, DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
		super(messager, filer, element);
		this.c = c;
		this.dictionary = dictionary;
	}
}
