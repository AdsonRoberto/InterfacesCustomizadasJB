package org.jb.stream.xml;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.stream.annotation.StreamElement;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLStreamElement extends XMLFieldAnnotation {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static XMLStreamElement adapt(StreamElement streamElement) {
        XMLStreamElement xmlStreamElement = new XMLStreamElement();
        xmlStreamElement.setName(streamElement.name());
        return xmlStreamElement;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLStreamElement().findAnnotation(field) != null;
    }

    public static XMLStreamElement getAnnotation(Field field) {
        return (XMLStreamElement) new XMLStreamElement().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLStreamElement{" +
                "name='" + name + '\'' +
                '}';
    }
}
