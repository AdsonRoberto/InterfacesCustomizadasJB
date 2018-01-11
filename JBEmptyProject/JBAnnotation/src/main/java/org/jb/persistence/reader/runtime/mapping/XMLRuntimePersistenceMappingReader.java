package org.jb.persistence.reader.runtime.mapping;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.reader.runtime.mapping.XMLRuntimeMappingReader;
import org.jb.persistence.reader.runtime.filevisitor.XMLRuntimePersistenceFileVisitor;
import org.w3c.dom.Element;

import javax.annotation.processing.Filer;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimePersistenceMappingReader extends XMLRuntimeMappingReader {
    public XMLRuntimePersistenceMappingReader(RuntimeDictionary dictionary) {
        super(dictionary);
        configuration = dictionary.getProjectConfiguration().getPersistenceConfiguration();
        directory = "persistence";
    }

    public void parseEntity(Element eElement) {
        super.parseEntity(eElement);

        XMLRuntimePersistenceFileVisitor fileVisitor = new XMLRuntimePersistenceFileVisitor(dictionary);
        fileVisitor.visitXMLEntity(eElement);
    }

    public void parseEnumeration(Element eElement) {
        super.parseEnumeration(eElement);

        XMLRuntimePersistenceFileVisitor fileVisitor = new XMLRuntimePersistenceFileVisitor(dictionary);
        fileVisitor.visitXMLEnumeration(eElement);
    }
}
