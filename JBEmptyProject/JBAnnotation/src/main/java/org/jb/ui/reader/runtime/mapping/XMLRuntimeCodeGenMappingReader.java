package org.jb.ui.reader.runtime.mapping;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.reader.runtime.mapping.XMLRuntimeMappingReader;
import org.jb.ui.reader.runtime.filevisitor.XMLRuntimeCodeGenFileVisitor;
import org.w3c.dom.Element;

import javax.annotation.processing.Filer;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimeCodeGenMappingReader extends XMLRuntimeMappingReader {
    public XMLRuntimeCodeGenMappingReader(RuntimeDictionary dictionary) {
        super(dictionary);
        configuration = dictionary.getProjectConfiguration().getCodeGenConfiguration();
        directory = "codegen";
    }

    public void parseEntity(Element eElement) {
        super.parseEntity(eElement);

        XMLRuntimeCodeGenFileVisitor fileVisitor = new XMLRuntimeCodeGenFileVisitor(dictionary);
        fileVisitor.visitXMLEntity(eElement);
    }

    public void parseEnumeration(Element eElement) {
        super.parseEnumeration(eElement);

        XMLRuntimeCodeGenFileVisitor fileVisitor = new XMLRuntimeCodeGenFileVisitor(dictionary);
        fileVisitor.visitXMLEnumeration(eElement);
    }
}
