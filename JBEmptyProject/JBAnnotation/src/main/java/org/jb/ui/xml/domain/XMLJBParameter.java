package org.jb.ui.xml.domain;

import org.jb.common.annotation.XMLParameterAnnotation;
import org.jb.ui.annotation.domain.JBParameter;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBParameter extends XMLParameterAnnotation {
    int order;
    String name;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static XMLJBParameter adapt(JBParameter jbParameter) {
        XMLJBParameter xmljbAction = new XMLJBParameter();
        xmljbAction.setOrder(jbParameter.order());
        xmljbAction.setName(jbParameter.name());
        return xmljbAction;
    }

    @Override
    public String toString() {
        return "XMLJBParameter{" +
                "order=" + order +
                ", name='" + name + '\'' +
                '}';
    }
}
