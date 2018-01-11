package org.jb.stream.xml;

import org.jb.common.annotation.XMLTypeAnnotation;
import org.jb.stream.annotation.StreamEntity;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLStreamEntity extends XMLTypeAnnotation {
    String name;
    String collectionName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public static XMLStreamEntity adapt(StreamEntity streamEntity) {
        XMLStreamEntity xmlStreamEntity = new XMLStreamEntity();
        xmlStreamEntity.setName(streamEntity.name());
        xmlStreamEntity.setCollectionName(streamEntity.collectionName());
        return xmlStreamEntity;
    }

    public static boolean isAnnoted(Class<?> c) {
        return new XMLStreamEntity().findAnnotation(c) != null;
    }

    public static XMLStreamEntity getAnnotation(Class<?> c) {
        return (XMLStreamEntity) new XMLStreamEntity().findAnnotation(c);
    }

    @Override
    public String toString() {
        return "XMLStreamEntity{" +
                "name='" + name + '\'' +
                ", collectionName='" + collectionName + '\'' +
                '}';
    }
}
