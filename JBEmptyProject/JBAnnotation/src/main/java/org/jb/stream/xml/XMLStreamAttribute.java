package org.jb.stream.xml;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.stream.annotation.StreamAttribute;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLStreamAttribute extends XMLFieldAnnotation {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static XMLStreamAttribute adapt(StreamAttribute streamAttribute) {
        XMLStreamAttribute xmlStreamAttribute = new XMLStreamAttribute();
        xmlStreamAttribute.setName(streamAttribute.name());
        return xmlStreamAttribute;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLStreamAttribute().findAnnotation(field) != null;
    }

    public static XMLStreamAttribute getAnnotation(Field field) {
        return (XMLStreamAttribute) new XMLStreamAttribute().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLStreamAttribute{" +
                "name='" + name + '\'' +
                '}';
    }
}
