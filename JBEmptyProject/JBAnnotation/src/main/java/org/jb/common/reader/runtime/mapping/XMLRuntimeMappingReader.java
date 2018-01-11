package org.jb.common.reader.runtime.mapping;

import org.jb.common.config.Configuration;
import org.jb.common.config.enums.MappingType;
import org.jb.common.dictionary.RuntimeDictionary;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fabiano on 09/10/17.
 */

public abstract class XMLRuntimeMappingReader {
    protected RuntimeDictionary dictionary;
    protected Configuration configuration;
    protected ClassLoader classLoader;
    protected String directory;

    public XMLRuntimeMappingReader(RuntimeDictionary dictionary) {
        this.dictionary = dictionary;
        this.classLoader = getClass().getClassLoader();
    }

    public RuntimeDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(RuntimeDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
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
        parse(classLoader.getResourceAsStream("assets/config/mapping/"
                + ((directory != null && !directory.isEmpty()) ? (directory + "/") : "")
                + filepath));
    }

    public void parse(InputStream stream) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

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

    }

    public void parseEnumeration(Element eElement) {
        //System.out.println("Enum : " + eElement.getAttribute("enum"));

    }
}
