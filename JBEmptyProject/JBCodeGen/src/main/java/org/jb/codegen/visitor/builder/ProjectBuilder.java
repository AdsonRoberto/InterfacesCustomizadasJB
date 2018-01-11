package org.jb.codegen.visitor.builder;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;

/**
 * Created by fabiano on 09/10/17.
 */

public abstract class ProjectBuilder {
    protected DescriptionDictionary dictionary;
    protected Messager messager;
    protected Filer filer;
    protected Element element;

    public ProjectBuilder() {
        this.dictionary = new DescriptionDictionary();
    }

    public ProjectBuilder(DescriptionDictionary dictionary) {
        super();
        this.dictionary = dictionary;
    }

    public ProjectBuilder(DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
        super();
        this.dictionary = dictionary;
        this.messager = messager;
        this.filer = filer;
        this.element = element;
    }

    public DescriptionDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
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

    public abstract void build();

    public abstract void buildProjectResourceFiles();

    public abstract void buildProjectConfigFiles();

    public abstract void buildEntityResourceFiles();

    public abstract void buildEnumResourceFiles();
}
