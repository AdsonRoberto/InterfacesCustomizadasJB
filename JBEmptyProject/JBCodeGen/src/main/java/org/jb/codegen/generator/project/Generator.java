package org.jb.codegen.generator.project;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;

public abstract class Generator {
	protected Messager messager;
	protected Filer filer;
	protected Element element;
	protected StringBuilder str;

	public Generator() {
		super();
		str = new StringBuilder();
	}
	
	public Generator(Messager messager, Filer filer, Element element) {
		super();
		this.messager = messager;
		this.filer = filer;
		this.element = element;
		str = new StringBuilder();
	}

	public Messager getMessager() {
		return messager;
	}

	public void setMessager(Messager messager) {
		this.messager = messager;
	}

	public Filer getFiler() {
		return filer;
	}

	public void setFiler(Filer filer) {
		this.filer = filer;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}
	
	public boolean isGeneratorOk() {
		return (this.messager != null && this.filer != null && this.messager != null);
	}
	
	public abstract void source();

	public abstract void generate();
}
