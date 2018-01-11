package org.jb.persistence.web.xml;

import org.jb.common.annotation.XMLMethodAnnotation;
import org.jb.persistence.web.annotation.WebAction;

import java.lang.reflect.Method;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLWebAction extends XMLMethodAnnotation {
    XMLRequest request;

    public XMLRequest getRequest() {
        return request;
    }

    public void setRequest(XMLRequest request) {
        this.request = request;
    }

    public static XMLWebAction adapt(WebAction webAction) {
        XMLWebAction xmlWebAction = new XMLWebAction();
        xmlWebAction.setRequest(XMLRequest.adapt(webAction.request()));
        return xmlWebAction;
    }

    public static boolean isAnnoted(Method method) {
        return new XMLWebAction().findAnnotation(method) != null;
    }

    public static XMLWebAction getAnnotation(Method method) {
        return (XMLWebAction) new XMLWebAction().findAnnotation(method);
    }

    @Override
    public String toString() {
        return "XMLWebAction{" +
                " \n\t\t\t\t\trequest=" + request +
                '}';
    }
}
