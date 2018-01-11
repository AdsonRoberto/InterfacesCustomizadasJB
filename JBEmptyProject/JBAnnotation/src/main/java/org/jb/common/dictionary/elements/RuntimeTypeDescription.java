package org.jb.common.dictionary.elements;

import org.jb.common.dictionary.RuntimeDictionary;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 10/07/17.
 */

public abstract class RuntimeTypeDescription {
    String simpleName;
    String canonicalName;

    Set<Object> xmlAnnotations;

    RuntimeDictionary dictionary;

    public RuntimeTypeDescription() {
        super();

        xmlAnnotations = new HashSet<Object>();
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

    public Set<Object> getXmlAnnotations() {
        return xmlAnnotations;
    }

    public void setXmlAnnotations(Set<Object> xmlAnnotations) {
        this.xmlAnnotations = xmlAnnotations;
    }

    public RuntimeDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(RuntimeDictionary dictionary) {
        this.dictionary = dictionary;
    }

    /** Insert Methods */

    public void addXmlAnnotation(Object xmlAnnotation) {
        this.xmlAnnotations.add(xmlAnnotation);
    }

    /** Remove Methods */

    public void removeXmlAnnotation(Object xmlAnnotation) {
        this.xmlAnnotations.remove(xmlAnnotation);
    }

    /** Find Methods */

    public Object findXmlAnnotationByType(Class<?> xmlAnnotationClass) {
        for(Object xmlAnnotation : xmlAnnotations) {
            if(xmlAnnotation.getClass().equals(xmlAnnotationClass)) {
                return xmlAnnotation;
            }
        }
        return null;
    }

    public Boolean isXmlAnnotatedWith(Class<?> c) {
        for(Object a : xmlAnnotations) {
            if(a.getClass().isAssignableFrom(c)) {
                return true;
            }
        }
        return false;
    }
}
