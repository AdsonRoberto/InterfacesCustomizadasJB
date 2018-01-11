package org.jb.persistence.web.xml;

import org.jb.common.annotation.XMLTypeAnnotation;
import org.jb.persistence.web.annotation.WebEntity;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLWebEntity extends XMLTypeAnnotation {
    XMLRequest insert;
    XMLRequest update;
    XMLRequest delete;
    XMLRequest find;
    XMLRequest findById;
    XMLRequest findAll;

    public XMLRequest getInsert() {
        return insert;
    }

    public void setInsert(XMLRequest insert) {
        this.insert = insert;
    }

    public XMLRequest getUpdate() {
        return update;
    }

    public void setUpdate(XMLRequest update) {
        this.update = update;
    }

    public XMLRequest getDelete() {
        return delete;
    }

    public void setDelete(XMLRequest delete) {
        this.delete = delete;
    }

    public XMLRequest getFind() {
        return find;
    }

    public void setFind(XMLRequest find) {
        this.find = find;
    }

    public XMLRequest getFindById() {
        return findById;
    }

    public void setFindById(XMLRequest findById) {
        this.findById = findById;
    }

    public XMLRequest getFindAll() {
        return findAll;
    }

    public void setFindAll(XMLRequest findAll) {
        this.findAll = findAll;
    }

    public static XMLWebEntity adapt(WebEntity webEntity) {
        XMLWebEntity xmlWebEntity = new XMLWebEntity();
        xmlWebEntity.setInsert(XMLRequest.adapt(webEntity.insert()));
        xmlWebEntity.setUpdate(XMLRequest.adapt(webEntity.update()));
        xmlWebEntity.setDelete(XMLRequest.adapt(webEntity.delete()));
        xmlWebEntity.setFind(XMLRequest.adapt(webEntity.find()));
        xmlWebEntity.setFindById(XMLRequest.adapt(webEntity.findById()));
        xmlWebEntity.setFindAll(XMLRequest.adapt(webEntity.findAll()));
        return xmlWebEntity;
    }

    public static boolean isAnnoted(Class<?> c) {
        return new XMLWebEntity().findAnnotation(c) != null;
    }

    public static XMLWebEntity getAnnotation(Class<?> c) {
        return (XMLWebEntity) new XMLWebEntity().findAnnotation(c);
    }

    @Override
    public String toString() {
        return "XMLWebEntity{" +
                "  \n\t\t\tinsert=" + insert +
                ", \n\t\t\tupdate=" + update +
                ", \n\t\t\tdelete=" + delete +
                ", \n\t\t\tfind=" + find +
                ", \n\t\t\tfindById=" + findById +
                ", \n\t\t\tfindAll=" + findAll +
                '}';
    }
}
