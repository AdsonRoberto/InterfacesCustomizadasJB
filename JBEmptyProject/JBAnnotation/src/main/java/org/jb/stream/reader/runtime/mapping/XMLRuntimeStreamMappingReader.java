package org.jb.stream.reader.runtime.mapping;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.reader.runtime.mapping.XMLRuntimeMappingReader;
import org.jb.stream.reader.runtime.filevisitor.XMLRuntimeStreamFileVisitor;
import org.w3c.dom.Element;

import javax.annotation.processing.Filer;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimeStreamMappingReader extends XMLRuntimeMappingReader {
    public XMLRuntimeStreamMappingReader(RuntimeDictionary dictionary) {
        super(dictionary);
        configuration = dictionary.getProjectConfiguration().getStreamConfiguration();
        directory = "stream";
    }

    public void parseEntity(Element eElement) {
        super.parseEntity(eElement);

        XMLRuntimeStreamFileVisitor fileVisitor = new XMLRuntimeStreamFileVisitor(dictionary);
        fileVisitor.visitXMLEntity(eElement);
    }

    public void parseEnumeration(Element eElement) {
        super.parseEnumeration(eElement);

        XMLRuntimeStreamFileVisitor fileVisitor = new XMLRuntimeStreamFileVisitor(dictionary);
        fileVisitor.visitXMLEnumeration(eElement);
    }
}
