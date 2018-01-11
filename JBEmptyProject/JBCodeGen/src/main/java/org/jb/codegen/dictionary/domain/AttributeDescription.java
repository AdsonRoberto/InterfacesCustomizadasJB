package org.jb.codegen.dictionary.domain;

import org.jb.ui.annotation.domain.JBAttribute;
import org.jb.ui.xml.domain.XMLJBAttribute;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public class AttributeDescription extends AbstractTypedDescription implements Comparable<AttributeDescription> {
    Set<Annotation> annotations;
    Set<Object> xmlAnnotations;

    ClassDescription classDescription;

    public AttributeDescription() {
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

    public ClassDescription getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(ClassDescription classDescription) {
        this.classDescription = classDescription;
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

    public Object findXmlAnnotationByType(Class<?> annotationClass) {
        for(Object xmlAnnotation : xmlAnnotations) {
            //TODO
            if(xmlAnnotation.getClass().equals(annotationClass)) {
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

    public MethodDescription getGetMethodDescription() {
        return getClassDescription().getGetMethodDescription(this);
    }

    public MethodDescription getSetMethodDescription() {
        return getClassDescription().getSetMethodDescription(this);
    }

    public boolean containsGetMethod() {
        return getClassDescription().getGetMethodDescription(this) != null;
    }

    public boolean containsSetMethod() {
        return getClassDescription().getSetMethodDescription(this) != null;
    }

    public Boolean isAttributeId() {
        for(Annotation annotation : annotations) {
            if(annotation.annotationType().equals(JBAttribute.class)) {
                return ((JBAttribute) annotation).id();
            }
        }
        //TODO
        for(Object xmlAnnotation : xmlAnnotations) {
            if(xmlAnnotation.getClass().equals(XMLJBAttribute.class)) {
                return ((XMLJBAttribute) xmlAnnotation).isId();
            }
        }
        return false;
    }

    /* Sorting the attributes's order*/

    @Override
    public int compareTo(AttributeDescription ad) {
        JBAttribute thisAttr = (JBAttribute) this.findAnnotationByType(JBAttribute.class);
        JBAttribute adAttr = (JBAttribute) ad.findAnnotationByType(JBAttribute.class);

        XMLJBAttribute thisXmlAttr = (XMLJBAttribute) this.findXmlAnnotationByType(XMLJBAttribute.class);
        XMLJBAttribute adXmlAttr = (XMLJBAttribute) ad.findXmlAnnotationByType(XMLJBAttribute.class);

        if(thisAttr == null || adAttr == null) {
            if(thisXmlAttr == null || adXmlAttr == null) {
                return 0;
            }
            else {
                if(thisXmlAttr.getOrder() > adXmlAttr.getOrder()) {
                    return 1;
                }
                else if(thisXmlAttr.getOrder() < adXmlAttr.getOrder()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
        else {
            if(thisAttr.order() > adAttr.order()) {
                return 1;
            }
            else if(thisAttr.order() < adAttr.order()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
