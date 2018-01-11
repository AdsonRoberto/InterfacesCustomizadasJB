package org.jb.codegen.reader.runtime.mapping;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.reader.compile.filevisitor.XMLCodeGenFileVisitor;
import org.jb.codegen.reader.compile.mapping.XMLMappingReader;
import org.w3c.dom.Element;

import javax.annotation.processing.Filer;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimeCodeGenMappingReader extends XMLMappingReader {
    public XMLRuntimeCodeGenMappingReader(DescriptionDictionary dictionary) {
        super(dictionary);
        configuration = dictionary.getProjectConfiguration().getCodeGenConfiguration();
        directory = "codegen";
    }

    public XMLRuntimeCodeGenMappingReader(DescriptionDictionary dictionary, Filer filer) {
        super(dictionary, filer);
        configuration = dictionary.getProjectConfiguration().getCodeGenConfiguration();
        directory = "codegen";
    }

    public void parseEntity(Element eElement) {
        super.parseEntity(eElement);

        XMLCodeGenFileVisitor fileVisitor = new XMLCodeGenFileVisitor(dictionary);
        fileVisitor.visitXMLEntity(eElement);
    }

    public void parseEnumeration(Element eElement) {
        super.parseEnumeration(eElement);

        XMLCodeGenFileVisitor fileVisitor = new XMLCodeGenFileVisitor(dictionary);
        fileVisitor.visitXMLEnumeration(eElement);
    }
}
