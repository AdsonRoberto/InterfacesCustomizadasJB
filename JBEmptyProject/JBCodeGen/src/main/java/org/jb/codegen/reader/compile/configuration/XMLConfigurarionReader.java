package org.jb.codegen.reader.compile.configuration;

import org.jb.common.config.Configuration;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLConfigurarionReader {
    Configuration configuration;
    Filer filer;

    public XMLConfigurarionReader(Configuration configuration) {
        this.configuration = configuration;
    }

    public XMLConfigurarionReader(Configuration configuration, Filer filer) {
        this.configuration = configuration;
        this.filer = filer;
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

    public void parse(String filepath) {
        try {
            FileObject resource = filer.getResource(StandardLocation.SOURCE_OUTPUT, "assets.config.configuration", filepath);
            File file = new File(resource.toUri());
            parse(file);
        } catch(IOException e) {
            //e.printStackTrace();
        }
    }

    public void parse(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("property");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //System.out.println("Property name : " + eElement.getAttribute("name"));
                    //System.out.println("Property value : " + eElement.getAttribute("value"));

                    configuration.addProperty(eElement.getAttribute("name"), eElement.getAttribute("value"));
                }
            }

            nList = doc.getElementsByTagName("mapping");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //System.out.println("Mapping resource : " + eElement.getAttribute("resource"));

                    configuration.addMapping(eElement.getAttribute("resource"));
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
