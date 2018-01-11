package org.jb.common.reader.runtime.filevisitor;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.dictionary.elements.RuntimeMethodDescription;
import org.w3c.dom.Element;

/**
 * Created by fabiano on 10/07/17.
 */

public abstract class XMLRuntimeFileVisitor {
    protected RuntimeDictionary dictionary;

    public XMLRuntimeFileVisitor(RuntimeDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public RuntimeDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(RuntimeDictionary dictionary) {
        this.dictionary = dictionary;
    }

    protected abstract void visitXMLEnumeration(Element element);
    protected abstract void visitXMLEntity(Element element);
    protected abstract void visitXMLAttribute(RuntimeClassDescription c, Element element);
    protected abstract void visitXMLMethod(RuntimeClassDescription c, Element element);
    protected abstract void visitXMLParameter(RuntimeMethodDescription m, Element element);
}