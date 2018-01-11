package org.jb.codegen.dictionary.domain;

import org.jb.ui.annotation.domain.JBAction;
import org.jb.ui.xml.domain.XMLJBAction;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public class MethodDescription extends AbstractMethodDescription implements Comparable<MethodDescription> {
    Set<Annotation> annotations;
    Set<Object> xmlAnnotations;

    public MethodDescription() {
        super();

        annotations = new HashSet<Annotation>();
        xmlAnnotations = new HashSet<Object>();
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

    /** Insert Methods */

    public void addAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
    }

    public void addXmlAnnotation(Object xmlAnnotation) {
        this.xmlAnnotations.add(xmlAnnotation);
    }

    /** Remove Methods */

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

    public ParameterDescription findParameterDescriptionByName(String name) {
        for(ParameterDescription parameterDescription : parameterDescriptions) {
            if(parameterDescription.getName().equals(name)) {
                return parameterDescription;
            }
        }
        return null;
    }

    /** Compare */

    public boolean hasEqualSignature(MethodDescription m) {
        if(this.getName().equals(m.getName())
                && this.getParameterDescriptions().size() == m.getParameterDescriptions().size()) {

            Iterator<ParameterDescription> it1 = this.getParameterDescriptions().iterator();
            Iterator<ParameterDescription> it2 = m.getParameterDescriptions().iterator();
            while(it1.hasNext() && it2.hasNext()) {
                ParameterDescription pd1 = it1.next();
                ParameterDescription pd2 = it2.next();
                if(!pd1.getType().equals(pd2.getType())) {
                    return false;
                }
            }
            return true;
        }
        return false;
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

    public boolean containsParameter(String name) {
        return findParameterDescriptionByName(name) != null;
    }

    @Override
    public int compareTo(MethodDescription md) {
        JBAction thisAction = (JBAction) this.findAnnotationByType(JBAction.class);
        JBAction mdAction = (JBAction) md.findAnnotationByType(JBAction.class);

        XMLJBAction thisXmlAction = (XMLJBAction) this.findXmlAnnotationByType(XMLJBAction.class);
        XMLJBAction mdXmlAction = (XMLJBAction) md.findXmlAnnotationByType(XMLJBAction.class);

        if (thisAction == null || mdAction == null) {
            if (thisXmlAction == null || mdXmlAction == null) {
                return 0;
            } else {
                if (thisXmlAction.getOrder() > mdXmlAction.getOrder()) {
                    return 1;
                } else if (thisXmlAction.getOrder() < mdXmlAction.getOrder()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        } else {
            if (thisAction.order() > mdAction.order()) {
                return 1;
            } else if (thisAction.order() < mdAction.order()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
