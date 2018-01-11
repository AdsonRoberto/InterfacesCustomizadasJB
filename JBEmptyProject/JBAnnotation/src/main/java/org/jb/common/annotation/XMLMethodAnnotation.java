package org.jb.common.annotation;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.dictionary.elements.RuntimeMethodDescription;
import org.jb.common.singleton.SingletonSession;

import java.lang.reflect.Method;

/**
 * Created by fabiano on 16/10/17.
 */

public class XMLMethodAnnotation extends XMLAnnotation {
    public XMLMethodAnnotation() {
        super();
    }

    public static boolean isAnnoted(Method method) {
        return new XMLMethodAnnotation().findAnnotation(method) != null;
    }

    public static XMLMethodAnnotation getAnnotation(Method method) {
        return new XMLMethodAnnotation().findAnnotation(method);
    }

    public XMLMethodAnnotation findAnnotation(Method method) {
        if(!method.getDeclaringClass().equals(Object.class)) {
            SingletonSession session = SingletonSession.instance();
            RuntimeDictionary dictionary = session.getDictionary();
            dictionary.load(method.getDeclaringClass().getClassLoader());

            RuntimeClassDescription c = dictionary.getClass(method.getDeclaringClass().getCanonicalName());
            //System.out.println("Class: " + method.getDeclaringClass().getCanonicalName() + " - Method: " + method.getName());
            if(c.containsMethod(method.getName())) {
                for(RuntimeMethodDescription m : c.findMethodDescriptionByName(method.getName())) {
                    //System.out.println("XML-Class: " + method.getDeclaringClass().getCanonicalName() + " - XML-Method: " + m.getName());
                    return getClass().cast(m.findXmlAnnotationByType(getClass()));
                }
            }
            else {
                return null;
            }
        }
        return null;
    }
}
