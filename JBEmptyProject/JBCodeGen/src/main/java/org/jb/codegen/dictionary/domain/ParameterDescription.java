package org.jb.codegen.dictionary.domain;

import org.jb.ui.annotation.domain.JBParameter;
import org.jb.ui.xml.domain.XMLJBParameter;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public class ParameterDescription extends AbstractTypedDescription implements Comparable<ParameterDescription> {
    Set<Annotation> annotations;
    Set<Object> xmlAnnotations;

    AbstractMethodDescription methodDescription;

    public ParameterDescription() {
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

    public AbstractMethodDescription getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(AbstractMethodDescription methodDescription) {
        this.methodDescription = methodDescription;
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

    public ClassDescription getClassDescription() {
        return getMethodDescription().getClassDescription();
    }

    @Override
    public int compareTo(ParameterDescription pd) {
        JBParameter thisParam = (JBParameter) this.findAnnotationByType(JBParameter.class);
        JBParameter pdParam = (JBParameter) pd.findAnnotationByType(JBParameter.class);

        XMLJBParameter thisXmlParam = (XMLJBParameter) this.findXmlAnnotationByType(XMLJBParameter.class);
        XMLJBParameter pdXmlParam = (XMLJBParameter) pd.findXmlAnnotationByType(XMLJBParameter.class);

        if (thisParam == null || pdParam == null) {
            if (thisXmlParam == null || pdXmlParam == null) {
                return 0;
            } else {
                if (thisXmlParam.getOrder() > pdXmlParam.getOrder()) {
                    return 1;
                } else if (thisXmlParam.getOrder() < pdXmlParam.getOrder()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        } else {
            if (thisParam.order() > pdParam.order()) {
                return 1;
            } else if (thisParam.order() < pdParam.order()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
