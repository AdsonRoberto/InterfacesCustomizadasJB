package org.jb.ui.xml.domain;

import org.jb.common.annotation.XMLTypeAnnotation;
import org.jb.ui.annotation.domain.JBEnumeration;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBEnumeration extends XMLTypeAnnotation {
    public static XMLJBEnumeration adapt(JBEnumeration jbEnumeration) {
        return new XMLJBEnumeration();
    }

    public static boolean isAnnoted(Class<?> c) {
        return new XMLJBEnumeration().findAnnotation(c) != null;
    }

    public static XMLJBEnumeration getAnnotation(Class<?> c) {
        return (XMLJBEnumeration) new XMLJBEnumeration().findAnnotation(c);
    }

    @Override
    public String toString() {
        return "XMLJBEnumeration{}";
    }
}
