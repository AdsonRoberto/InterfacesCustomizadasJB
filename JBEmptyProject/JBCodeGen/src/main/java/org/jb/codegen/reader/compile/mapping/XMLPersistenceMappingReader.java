package org.jb.codegen.reader.compile.mapping;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.reader.compile.filevisitor.XMLPersistenceFileVisitor;
import org.w3c.dom.Element;

import javax.annotation.processing.Filer;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLPersistenceMappingReader extends XMLMappingReader {
    public XMLPersistenceMappingReader(DescriptionDictionary dictionary) {
        super(dictionary);
        configuration = dictionary.getProjectConfiguration().getPersistenceConfiguration();
        directory = "persistence";
    }

    public XMLPersistenceMappingReader(DescriptionDictionary dictionary, Filer filer) {
        super(dictionary, filer);
        configuration = dictionary.getProjectConfiguration().getPersistenceConfiguration();
        directory = "persistence";
    }

    public void parseEntity(Element eElement) {
        super.parseEntity(eElement);

        XMLPersistenceFileVisitor fileVisitor = new XMLPersistenceFileVisitor(dictionary);
        fileVisitor.visitXMLEntity(eElement);
    }

    public void parseEnumeration(Element eElement) {
        super.parseEnumeration(eElement);

        XMLPersistenceFileVisitor fileVisitor = new XMLPersistenceFileVisitor(dictionary);
        fileVisitor.visitXMLEnumeration(eElement);
    }
}
