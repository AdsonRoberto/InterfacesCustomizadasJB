package org.jb.stream.xml;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.stream.annotation.StreamTemporal;
import org.jb.stream.annotation.StreamTransient;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLStreamTransient extends XMLFieldAnnotation {
    public static XMLStreamTransient adapt(StreamTransient streamTransient) {
        return new XMLStreamTransient();
    }

    public static boolean isAnnoted(Field field) {
        return new XMLStreamTransient().findAnnotation(field) != null;
    }

    public static XMLStreamTransient getAnnotation(Field field) {
        return (XMLStreamTransient) new XMLStreamTransient().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLStreamTransient{}";
    }
}
