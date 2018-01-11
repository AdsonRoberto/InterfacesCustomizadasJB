package org.jb.common.annotation;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.singleton.SingletonSession;

/**
 * Created by fabiano on 16/10/17.
 */

public class XMLTypeAnnotation extends XMLAnnotation {
    public XMLTypeAnnotation() {
        super();
    }

    public static boolean isAnnoted(Class<?> c) {
        return new XMLTypeAnnotation().findAnnotation(c) != null;
    }

    public static XMLTypeAnnotation getAnnotation(Class<?> c) {
        return new XMLTypeAnnotation().findAnnotation(c);
    }

    public XMLTypeAnnotation findAnnotation(Class<?> c) {
        if(!c.equals(Object.class)) {
            SingletonSession session = SingletonSession.instance();
            RuntimeDictionary dictionary = session.getDictionary();
            dictionary.load(c.getClassLoader());

            if(dictionary.containsClassWithKey(c.getCanonicalName())) {
                RuntimeClassDescription cd = dictionary.getClass(c.getCanonicalName());
                System.out.println("Class: " + c.getCanonicalName() + " - XML-Class: " + cd.getCanonicalName());
                return getClass().cast(cd.findXmlAnnotationByType(getClass()));
            }
            else {
                return null;
            }
        }
        return null;
    }
}
