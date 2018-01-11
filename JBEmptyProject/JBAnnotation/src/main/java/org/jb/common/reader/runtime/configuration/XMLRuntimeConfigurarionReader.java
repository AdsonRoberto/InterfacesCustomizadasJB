package org.jb.common.reader.runtime.configuration;

import org.jb.common.config.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimeConfigurarionReader {
    protected Configuration configuration;
    protected ClassLoader classLoader;

    public XMLRuntimeConfigurarionReader() {
        classLoader = getClass().getClassLoader();
    }

    public XMLRuntimeConfigurarionReader(Field field) {
        classLoader = field.getDeclaringClass().getClassLoader();
    }

    public XMLRuntimeConfigurarionReader(Method method) {
        classLoader = method.getDeclaringClass().getClassLoader();
    }

    public XMLRuntimeConfigurarionReader(Class<?> c) {
        classLoader = c.getClassLoader();
    }

    public XMLRuntimeConfigurarionReader(ClassLoader c) {
        classLoader = c;
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

    public void parse(String filepath) {
        parse(classLoader.getResourceAsStream("assets/config/configuration/" + filepath));
    }

    public void parse(InputStream stream) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

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
