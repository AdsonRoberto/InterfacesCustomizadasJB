package org.jb.codegen.reader.compile.mapping;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.common.config.Configuration;
import org.jb.common.config.enums.MappingType;
import org.jb.codegen.visitor.visitor.XMLDescriptionVisitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fabiano on 09/10/17.
 */

public abstract class XMLMappingReader {
    protected DescriptionDictionary dictionary;
    protected Configuration configuration;
    protected Filer filer;
    protected String directory;

    public XMLMappingReader(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public XMLMappingReader(DescriptionDictionary dictionary, Filer filer) {
        this.dictionary = dictionary;
        this.filer = filer;
    }

    public DescriptionDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Filer getFiler() {
        return filer;
    }

    public void setFiler(Filer filer) {
        this.filer = filer;
    }

    public void parse() {
        if(configuration.getMappingType() == MappingType.XML) {
            String[] mappings = configuration.getMappings().toArray(new String[configuration.getMappings().size()]);
            parse(mappings);
        }
        else {
            System.out.println("O objeto 'Configuration' está nulo ou o MappingType não é do tipo XML.");
        }
    }

    public void parse(String[] filepaths) {
        for (int i = 0; i < filepaths.length; i++) {
            parse(filepaths[i]);
        }
    }

    public void parse(String filepath) {
        try {
            String dir = "assets.config.mapping" + ((directory != null && !directory.isEmpty()) ? ("." + directory) : "");
            FileObject resource = filer.getResource(StandardLocation.SOURCE_OUTPUT, dir, filepath);
            File file = new File(resource.toUri());
            parse(file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("entity");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    parseEntity(eElement);
                }
            }

            nList = doc.getElementsByTagName("enumeration");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    parseEnumeration(eElement);
                }
            }
            //System.out.println("----------------------------");
            //System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseEntity(Element eElement) {
        //System.out.println("Class : " + eElement.getAttribute("class"));

        javax.lang.model.element.Element element = dictionary.getElement(eElement.getAttribute("class"));
        XMLDescriptionVisitor xmlDescriptionVisitor = new XMLDescriptionVisitor(dictionary);
        xmlDescriptionVisitor.visitClass((TypeElement) element);
    }

    public void parseEnumeration(Element eElement) {
        //System.out.println("Enum : " + eElement.getAttribute("enum"));

        javax.lang.model.element.Element element = dictionary.getElement(eElement.getAttribute("enum"));
        XMLDescriptionVisitor xmlDescriptionVisitor = new XMLDescriptionVisitor(dictionary);
        xmlDescriptionVisitor.visitEnum((TypeElement) element);
    }
}
