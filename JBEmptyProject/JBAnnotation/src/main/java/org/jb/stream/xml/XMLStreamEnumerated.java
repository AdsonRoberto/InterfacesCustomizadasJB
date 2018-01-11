package org.jb.stream.xml;

import org.jb.common.annotation.XMLFieldAnnotation;
import org.jb.stream.annotation.StreamEnumerated;
import org.jb.stream.annotation.enums.EnumType;

import java.lang.reflect.Field;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLStreamEnumerated extends XMLFieldAnnotation {
    EnumType value = EnumType.STRING;

    public EnumType getValue() {
        return value;
    }

    public void setValue(EnumType value) {
        this.value = value;
    }

    public static XMLStreamEnumerated adapt(StreamEnumerated streamEnumerated) {
        XMLStreamEnumerated xmlStreamEnumerated = new XMLStreamEnumerated();
        xmlStreamEnumerated.setValue(streamEnumerated.value());
        return xmlStreamEnumerated;
    }

    public static boolean isAnnoted(Field field) {
        return new XMLStreamEnumerated().findAnnotation(field) != null;
    }

    public static XMLStreamEnumerated getAnnotation(Field field) {
        return (XMLStreamEnumerated) new XMLStreamEnumerated().findAnnotation(field);
    }

    @Override
    public String toString() {
        return "XMLStreamEnumerated{" +
                "value=" + value +
                '}';
    }
}
