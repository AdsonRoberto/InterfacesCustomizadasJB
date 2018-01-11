package org.jb.ui.xml.visual;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.ui.annotation.visual.JBLarge;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBLarge extends XMLFieldAnnotation {
    public static XMLJBLarge adapt(JBLarge jbLarge) {
        return new XMLJBLarge();
    }

    public static boolean isAnnoted(Field field) {
        return new XMLJBLarge().findAnnotation(field) != null;
    }

    public static XMLJBLarge getAnnotation(Field field) {
        return (XMLJBLarge) new XMLJBLarge().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLJBLarge{}";
    }
}
