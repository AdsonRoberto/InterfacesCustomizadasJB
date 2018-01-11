package org.jb.ui.xml.visual;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.ui.annotation.visual.JBTemporal;
import org.jb.ui.annotation.visual.enums.TemporalType;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBTemporal extends XMLFieldAnnotation {
    TemporalType value = TemporalType.DATE;

    public TemporalType getValue() {
        return value;
    }

    public void setValue(TemporalType value) {
        this.value = value;
    }

    public static XMLJBTemporal adapt(JBTemporal jbTemporal) {
        XMLJBTemporal xmljbTemporal = new XMLJBTemporal();
        xmljbTemporal.setValue(jbTemporal.value());
        return xmljbTemporal;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLJBTemporal().findAnnotation(field) != null;
    }

    public static XMLJBTemporal getAnnotation(Field field) {
        return (XMLJBTemporal) new XMLJBTemporal().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLJBTemporal{" +
                "value=" + value +
                '}';
    }
}
