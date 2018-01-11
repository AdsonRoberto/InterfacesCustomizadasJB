package org.jb.ui.xml.domain;

import org.jb.common.annotation.XMLMethodAnnotation;
import org.jb.ui.annotation.domain.JBAction;

import java.lang.reflect.Method;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBAction extends XMLMethodAnnotation {
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

    public static XMLJBAction adapt(JBAction jbAction) {
        XMLJBAction xmljbAction = new XMLJBAction();
        xmljbAction.setOrder(jbAction.order());
        xmljbAction.setName(jbAction.name());
        return xmljbAction;
    }

    public static boolean isAnnoted(Method method) {
        return new XMLJBAction().findAnnotation(method) != null;
    }

    public static XMLJBAction getAnnotation(Method method) {
        return (XMLJBAction) new XMLJBAction().findAnnotation(method);
    }

    @Override
    public String toString() {
        return "XMLJBAction{" +
                "order=" + order +
                ", name='" + name + '\'' +
                '}';
    }
}
