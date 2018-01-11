package org.jb.codegen.reader.compile.mapping;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.reader.compile.filevisitor.XMLStreamFileVisitor;
import org.w3c.dom.Element;

import javax.annotation.processing.Filer;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLStreamMappingReader extends XMLMappingReader {
    public XMLStreamMappingReader(DescriptionDictionary dictionary) {
        super(dictionary);
        configuration = dictionary.getProjectConfiguration().getStreamConfiguration();
        directory = "stream";
    }

    public XMLStreamMappingReader(DescriptionDictionary dictionary, Filer filer) {
        super(dictionary, filer);
        configuration = dictionary.getProjectConfiguration().getStreamConfiguration();
        directory = "stream";
    }

    public void parseEntity(Element eElement) {
        super.parseEntity(eElement);

        XMLStreamFileVisitor fileVisitor = new XMLStreamFileVisitor(dictionary);
        fileVisitor.visitXMLEntity(eElement);
    }

    public void parseEnumeration(Element eElement) {
        super.parseEnumeration(eElement);

        XMLStreamFileVisitor fileVisitor = new XMLStreamFileVisitor(dictionary);
        fileVisitor.visitXMLEnumeration(eElement);
    }
}
