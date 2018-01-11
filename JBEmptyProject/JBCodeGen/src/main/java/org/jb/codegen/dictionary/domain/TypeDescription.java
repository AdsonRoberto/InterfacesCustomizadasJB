package org.jb.codegen.dictionary.domain;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 10/07/17.
 */

public abstract class TypeDescription {
    Integer index;
    String simpleName;
    String canonicalName;
    Set<String> modifiers;

    Set<Annotation> annotations;
    Set<Object> xmlAnnotations;

    DescriptionDictionary dictionary;

    public TypeDescription() {
        super();
        index = 0;
        modifiers = new HashSet<String>();

        annotations = new HashSet<Annotation>();
        xmlAnnotations = new HashSet<Object>();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<String> modifiers) {
        this.modifiers = modifiers;
    }

    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Set<Annotation> annotations) {
        this.annotations = annotations;
    }

    public Set<Object> getXmlAnnotations() {
        return xmlAnnotations;
    }

    public void setXmlAnnotations(Set<Object> xmlAnnotations) {
        this.xmlAnnotations = xmlAnnotations;
    }

    public DescriptionDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }

    /** Insert Methods */

    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public void addAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
    }

    public void addXmlAnnotation(Object xmlAnnotation) {
        this.xmlAnnotations.add(xmlAnnotation);
    }

    /** Remove Methods */

    public void removeModifier(String modifier) {
        this.modifiers.remove(modifier);
    }

    public void removeAnnotation(Annotation annotation) {
        this.annotations.remove(annotation);
    }

    public void removeXmlAnnotation(Object xmlAnnotation) {
        this.xmlAnnotations.remove(xmlAnnotation);
    }

    /** Find Methods */

    public Annotation findAnnotationByType(Class<?> annotationClass) {
        for(Annotation annotation : annotations) {
            if(annotation.annotationType().equals(annotationClass)) {
                return annotation;
            }
        }
        return null;
    }

    public Object findXmlAnnotationByType(Class<?> xmlAnnotationClass) {
        for(Object xmlAnnotation : xmlAnnotations) {
            if(xmlAnnotation.getClass().equals(xmlAnnotationClass)) {
                return xmlAnnotation;
            }
        }
        return null;
    }

    public Boolean isAnnotatedWith(Class<?> c) {
        for(Annotation a : annotations) {
            if(a.annotationType().isAssignableFrom(c)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isXmlAnnotatedWith(Class<?> c) {
        for(Object a : xmlAnnotations) {
            if(a.getClass().isAssignableFrom(c)) {
                return true;
            }
        }
        return false;
    }

    /** Contain Methods */

    public boolean containsModifier(String modifier) {
        return modifiers.contains(modifier.toLowerCase()) || modifiers.contains(modifier.toUpperCase());
    }
}
