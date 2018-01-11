package org.jb.common.annotation;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeAttributeDescription;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.singleton.SingletonSession;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 16/10/17.
 */

public class XMLFieldAnnotation extends XMLAnnotation {
    public XMLFieldAnnotation() {
        super();
    }

    public static boolean isAnnoted(Field field) {
        return new XMLFieldAnnotation().findAnnotation(field) != null;
    }

    public static XMLFieldAnnotation getAnnotation(Field field) {
        return new XMLFieldAnnotation().findAnnotation(field);
    }

    public XMLFieldAnnotation findAnnotation(Field field) {
        if(!field.getDeclaringClass().equals(Object.class)) {
            SingletonSession session = SingletonSession.instance();
            RuntimeDictionary dictionary = session.getDictionary();
            dictionary.load(field.getDeclaringClass().getClassLoader());

            RuntimeClassDescription c = dictionary.getClass(field.getDeclaringClass().getCanonicalName());
            System.out.println("Class: " + field.getDeclaringClass().getCanonicalName() + " - Field: " + field.getName());
            if(c.containsAttribute(field.getName())) {
                RuntimeAttributeDescription a = c.findAttributeDescriptionByName(field.getName());
                System.out.println("XML-Class: " + field.getDeclaringClass().getCanonicalName() + " - XML-Field: " + a.getName());
                return getClass().cast(a.findXmlAnnotationByType(getClass()));
            }
            else {
                return null;
            }
        }
        return null;
    }
}
