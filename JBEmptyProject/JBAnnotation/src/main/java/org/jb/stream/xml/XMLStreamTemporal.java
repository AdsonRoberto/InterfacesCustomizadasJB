package org.jb.stream.xml;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.stream.annotation.StreamTemporal;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLStreamTemporal extends XMLFieldAnnotation {
    String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public static XMLStreamTemporal adapt(StreamTemporal streamTemporal) {
        XMLStreamTemporal xmlStreamTemporal = new XMLStreamTemporal();
        xmlStreamTemporal.setPattern(streamTemporal.pattern());
        return xmlStreamTemporal;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLStreamTemporal().findAnnotation(field) != null;
    }

    public static XMLStreamTemporal getAnnotation(Field field) {
        return (XMLStreamTemporal) new XMLStreamTemporal().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLStreamTemporal{" +
                "pattern='" + pattern + '\'' +
                '}';
    }
}
