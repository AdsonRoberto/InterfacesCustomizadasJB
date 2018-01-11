package org.jb.ui.xml.domain;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.ui.annotation.domain.JBAttribute;
import org.jb.ui.annotation.domain.enums.KindView;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBAttribute extends XMLFieldAnnotation {
    int order;
    String name;
    boolean id = false;
    KindView[] views = {};

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

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public KindView[] getViews() {
        return views;
    }

    public void setViews(KindView[] views) {
        this.views = views;
    }

    public static XMLJBAttribute adapt(JBAttribute jbAttribute) {
        XMLJBAttribute xmljbAttribute = new XMLJBAttribute();
        xmljbAttribute.setOrder(jbAttribute.order());
        xmljbAttribute.setName(jbAttribute.name());
        xmljbAttribute.setId(jbAttribute.id());
        xmljbAttribute.setViews(jbAttribute.views());
        return xmljbAttribute;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLJBAttribute().findAnnotation(field) != null;
    }

    public static XMLJBAttribute getAnnotation(Field field) {
        return (XMLJBAttribute) new XMLJBAttribute().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLJBAttribute{" +
                "order=" + order +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", views=" + Arrays.toString(views) +
                '}';
    }
}
