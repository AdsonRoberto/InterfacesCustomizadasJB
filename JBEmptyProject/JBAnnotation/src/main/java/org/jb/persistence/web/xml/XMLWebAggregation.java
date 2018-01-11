package org.jb.persistence.web.xml;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.persistence.web.annotation.WebAggregation;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLWebAggregation extends XMLFieldAnnotation {
    XMLRequest insert;
    XMLRequest delete;
    XMLRequest list;

    public XMLRequest getInsert() {
        return insert;
    }

    public void setInsert(XMLRequest insert) {
        this.insert = insert;
    }

    public XMLRequest getDelete() {
        return delete;
    }

    public void setDelete(XMLRequest delete) {
        this.delete = delete;
    }

    public XMLRequest getList() {
        return list;
    }

    public void setList(XMLRequest list) {
        this.list = list;
    }

    public static XMLWebAggregation adapt(WebAggregation webAggregation) {
        XMLWebAggregation xmlWebAggregation = new XMLWebAggregation();
        xmlWebAggregation.setList(XMLRequest.adapt(webAggregation.list()));
        xmlWebAggregation.setInsert(XMLRequest.adapt(webAggregation.insert()));
        xmlWebAggregation.setDelete(XMLRequest.adapt(webAggregation.delete()));
        return xmlWebAggregation;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLWebAggregation().findAnnotation(field) != null;
    }

    public static XMLWebAggregation getAnnotation(Field field) {
        return (XMLWebAggregation) new XMLWebAggregation().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLWebAggregation{" +
                " \n\t\t\t\t\tinsert=" + insert +
                ", \n\t\t\t\t\tdelete=" + delete +
                ", \n\t\t\t\t\tlist=" + list +
                '}';
    }
}
