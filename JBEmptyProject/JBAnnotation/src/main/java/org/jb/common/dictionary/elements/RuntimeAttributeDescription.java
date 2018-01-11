package org.jb.common.dictionary.elements;

import org.jb.ui.xml.domain.XMLJBAttribute;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public class RuntimeAttributeDescription extends RuntimeAbstractTypedDescription implements Comparable<RuntimeAttributeDescription> {
    Set<Object> xmlAnnotations;

    RuntimeClassDescription classDescription;

    public RuntimeAttributeDescription() {
        super();

        xmlAnnotations = new HashSet<Object>();
    }

    public Set<Object> getXmlAnnotations() {
        return xmlAnnotations;
    }

    public void setXmlAnnotations(Set<Object> xmlAnnotations) {
        this.xmlAnnotations = xmlAnnotations;
    }

    public RuntimeClassDescription getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(RuntimeClassDescription classDescription) {
        this.classDescription = classDescription;
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

    public Object findXmlAnnotationByType(Class<?> annotationClass) {
        for(Object xmlAnnotation : xmlAnnotations) {
            //TODO
            if(xmlAnnotation.getClass().equals(annotationClass)) {
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

    public RuntimeMethodDescription getGetMethodDescription() {
        return getClassDescription().getGetMethodDescription(this);
    }

    public RuntimeMethodDescription getSetMethodDescription() {
        return getClassDescription().getSetMethodDescription(this);
    }

    public boolean containsGetMethod() {
        return getClassDescription().getGetMethodDescription(this) != null;
    }

    public boolean containsSetMethod() {
        return getClassDescription().getSetMethodDescription(this) != null;
    }

    public Boolean isAttributeId() {
        for(Object xmlAnnotation : xmlAnnotations) {
            if(xmlAnnotation.getClass().equals(XMLJBAttribute.class)) {
                return ((XMLJBAttribute) xmlAnnotation).isId();
            }
        }
        return false;
    }

    /* Sorting the attributes's order*/

    @Override
    public int compareTo(RuntimeAttributeDescription ad) {
        XMLJBAttribute thisXmlAttr = (XMLJBAttribute) this.findXmlAnnotationByType(XMLJBAttribute.class);
        XMLJBAttribute adXmlAttr = (XMLJBAttribute) ad.findXmlAnnotationByType(XMLJBAttribute.class);

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
}
