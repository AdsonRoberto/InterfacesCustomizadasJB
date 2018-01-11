package org.jb.persistence.web.xml;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.persistence.web.annotation.WebComposition;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLWebComposition extends XMLFieldAnnotation {
    XMLRequest list;

    public XMLRequest getList() {
        return list;
    }

    public void setList(XMLRequest list) {
        this.list = list;
    }

    public static XMLWebComposition adapt(WebComposition webComposition) {
        XMLWebComposition xmlWebComposition = new XMLWebComposition();
        xmlWebComposition.setList(XMLRequest.adapt(webComposition.list()));
        return xmlWebComposition;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLWebComposition().findAnnotation(field) != null;
    }

    public static XMLWebComposition getAnnotation(Field field) {
        return (XMLWebComposition) new XMLWebComposition().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLWebComposition{" +
                " \n\t\t\t\t\tlist=" + list +
                '}';
    }
}
