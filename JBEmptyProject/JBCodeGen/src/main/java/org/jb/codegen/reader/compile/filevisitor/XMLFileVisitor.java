package org.jb.codegen.reader.compile.filevisitor;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.visitor.visitor.Visitor;

import org.w3c.dom.Element;

/**
 * Created by fabiano on 10/07/17.
 */

public abstract class XMLFileVisitor extends Visitor {
    public XMLFileVisitor(DescriptionDictionary dictionary) {
        super(dictionary);
    }

    protected abstract void visitXMLEnumeration(Element element);
    protected abstract  void visitXMLEntity(Element element);
    protected abstract  void visitXMLAttribute(ClassDescription c, Element element);
    protected abstract  void visitXMLMethod(ClassDescription c, Element element);
    protected abstract  void visitXMLParameter(MethodDescription m, Element element);
}