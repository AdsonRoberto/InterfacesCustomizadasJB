package org.jb.common.dictionary.elements;

import org.jb.ui.xml.domain.XMLJBParameter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public class RuntimeParameterDescription extends RuntimeAbstractTypedDescription implements Comparable<RuntimeParameterDescription> {
    Set<Object> xmlAnnotations;

    RuntimeAbstractMethodDescription methodDescription;

    public RuntimeParameterDescription() {
        super();
        xmlAnnotations = new HashSet<Object>();
    }

    public Set<Object> getXmlAnnotations() {
        return xmlAnnotations;
    }

    public void setXmlAnnotations(Set<Object> xmlAnnotations) {
        this.xmlAnnotations = xmlAnnotations;
    }

    public RuntimeAbstractMethodDescription getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(RuntimeAbstractMethodDescription methodDescription) {
        this.methodDescription = methodDescription;
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

    public RuntimeClassDescription getClassDescription() {
        return getMethodDescription().getClassDescription();
    }

    @Override
    public int compareTo(RuntimeParameterDescription pd) {
        XMLJBParameter thisXmlParam = (XMLJBParameter) this.findXmlAnnotationByType(XMLJBParameter.class);
        XMLJBParameter pdXmlParam = (XMLJBParameter) pd.findXmlAnnotationByType(XMLJBParameter.class);

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
    }
}
