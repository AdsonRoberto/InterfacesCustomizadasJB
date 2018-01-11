package org.jb.common.dictionary.elements;

import org.jb.ui.xml.domain.XMLJBAction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public class RuntimeMethodDescription extends RuntimeAbstractMethodDescription implements Comparable<RuntimeMethodDescription> {
    Set<Object> xmlAnnotations;

    public RuntimeMethodDescription() {
        super();

        xmlAnnotations = new HashSet<Object>();
    }

    public Set<Object> getXmlAnnotations() {
        return xmlAnnotations;
    }

    public void setXmlAnnotations(Set<Object> xmlAnnotations) {
        this.xmlAnnotations = xmlAnnotations;
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

    public RuntimeParameterDescription findParameterDescriptionByName(String name) {
        for(RuntimeParameterDescription parameterDescription : parameterDescriptions) {
            if(parameterDescription.getName().equals(name)) {
                return parameterDescription;
            }
        }
        return null;
    }

    /** Compare */

    public boolean hasEqualSignature(RuntimeMethodDescription m) {
        if(this.getName().equals(m.getName())
                && this.getParameterDescriptions().size() == m.getParameterDescriptions().size()) {

            Iterator<RuntimeParameterDescription> it1 = this.getParameterDescriptions().iterator();
            Iterator<RuntimeParameterDescription> it2 = m.getParameterDescriptions().iterator();
            while(it1.hasNext() && it2.hasNext()) {
                RuntimeParameterDescription pd1 = it1.next();
                RuntimeParameterDescription pd2 = it2.next();
                if(!pd1.getType().equals(pd2.getType())) {
                    return false;
                }
            }
            return true;
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
    public int compareTo(RuntimeMethodDescription md) {
        XMLJBAction thisXmlAction = (XMLJBAction) this.findXmlAnnotationByType(XMLJBAction.class);
        XMLJBAction mdXmlAction = (XMLJBAction) md.findXmlAnnotationByType(XMLJBAction.class);

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
    }
}
