package org.jb.ui.xml.domain;

import org.jb.common.annotation.XMLTypeAnnotation;
import org.jb.ui.annotation.domain.JBEntity;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBEntity extends XMLTypeAnnotation {
    String label;
    String collectionLabel;
    String icon = "ic_launcher";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCollectionLabel() {
        return collectionLabel;
    }

    public void setCollectionLabel(String collectionLabel) {
        this.collectionLabel = collectionLabel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static XMLJBEntity adapt(JBEntity jbEntity) {
        XMLJBEntity xmljbEntity = new XMLJBEntity();
        xmljbEntity.setLabel(jbEntity.label());
        xmljbEntity.setCollectionLabel(jbEntity.collectionLabel());
        xmljbEntity.setIcon(jbEntity.icon());
        return xmljbEntity;
    }

    public static boolean isAnnoted(Class<?> c) {
        return new XMLJBEntity().findAnnotation(c) != null;
    }

    public static XMLJBEntity getAnnotation(Class<?> c) {
        return (XMLJBEntity) new XMLJBEntity().findAnnotation(c);
    }

    @Override
    public String toString() {
        return "XMLJBEntity{" +
                "label='" + label + '\'' +
                ", collectionLabel='" + collectionLabel + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
