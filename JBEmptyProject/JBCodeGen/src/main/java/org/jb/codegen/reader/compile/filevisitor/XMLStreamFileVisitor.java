package org.jb.codegen.reader.compile.filevisitor;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;

import org.jb.stream.annotation.enums.EnumType;
import org.jb.stream.xml.XMLStreamAttribute;
import org.jb.stream.xml.XMLStreamElement;
import org.jb.stream.xml.XMLStreamEntity;
import org.jb.stream.xml.XMLStreamEnumerated;
import org.jb.stream.xml.XMLStreamTemporal;
import org.jb.stream.xml.XMLStreamTransient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by fabiano on 10/07/17.
 */

public class XMLStreamFileVisitor extends XMLFileVisitor {
    public XMLStreamFileVisitor(DescriptionDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public void visitXMLEnumeration(Element element) {

        //TODO NOTHING

    }

    @Override
    public void visitXMLEntity(Element element) {
        if(dictionary.containsClassWithKey(element.getAttribute("class"))) {
            ClassDescription c = dictionary.getClass(element.getAttribute("class"));

            //Tag: WEB-ENTITY
            if(element.getElementsByTagName("stream-entity") != null
                    && element.getElementsByTagName("stream-entity").getLength() > 0) {
                Node nNode = element.getElementsByTagName("stream-entity").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    c.addXmlAnnotation(getXMLStreamEntity((Element) nNode));
                }
            }

            //Tag: ATTRIBUTE
            NodeList nList = element.getElementsByTagName("attribute");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    visitXMLAttribute(c, (Element) nNode);
                }
            }

            //Tag: METHOD
            nList = element.getElementsByTagName("method");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    visitXMLMethod(c, (Element) nNode);
                }
            }
        }
    }

    @Override
    public void visitXMLAttribute(ClassDescription c, Element element) {
        if(c.containsAttribute(element.getAttribute("name"))) {
            AttributeDescription ad = c.findAttributeDescriptionByName(element.getAttribute("name"));

            //Tag: STREAM-ATTRIBUTE
            if (element.getElementsByTagName("stream-attribute") != null
                    && element.getElementsByTagName("stream-attribute").getLength() > 0) {
                Node nNode = element.getElementsByTagName("stream-attribute").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLStreamAttribute((Element) nNode));
                }
            }

            //Tag: STREAM-ELEMENT
            if (element.getElementsByTagName("stream-element") != null
                    && element.getElementsByTagName("stream-element").getLength() > 0) {
                Node nNode = element.getElementsByTagName("stream-element").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLStreamElement((Element) nNode));
                }
            }

            //Tag: STREAM-ENUMERATED
            if (element.getElementsByTagName("stream-enumerated") != null
                    && element.getElementsByTagName("stream-enumerated").getLength() > 0) {
                Node nNode = element.getElementsByTagName("stream-enumerated").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLStreamEnumerated((Element) nNode));
                }
            }

            //Tag: STREAM-TEMPORAL
            if (element.getElementsByTagName("stream-temporal") != null
                    && element.getElementsByTagName("stream-temporal").getLength() > 0) {
                Node nNode = element.getElementsByTagName("stream-temporal").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLStreamTemporal((Element) nNode));
                }
            }

            //Tag: STREAM-TRANSIENT
            if (element.getElementsByTagName("stream-transient") != null
                    && element.getElementsByTagName("stream-transient").getLength() > 0) {
                Node nNode = element.getElementsByTagName("stream-transient").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLStreamTransient((Element) nNode));
                }
            }
        }
    }

    @Override
    public void visitXMLMethod(ClassDescription c, Element element) {

        //TODO NOTHING

    }

    @Override
    public void visitXMLParameter(MethodDescription m, Element element) {

        //TODO NOTHING

    }

    private XMLStreamEntity getXMLStreamEntity(Element element) {
        XMLStreamEntity annot = new XMLStreamEntity();

        annot.setName(element.getAttribute("name"));
        annot.setCollectionName(element.getAttribute("collectionName"));

        return annot;
    }

    private XMLStreamAttribute getXMLStreamAttribute(Element element) {
        XMLStreamAttribute annot = new XMLStreamAttribute();

        annot.setName(element.getElementsByTagName("name").item(0).getTextContent());

        return annot;
    }

    private XMLStreamElement getXMLStreamElement(Element element) {
        XMLStreamElement annot = new XMLStreamElement();

        annot.setName(element.getElementsByTagName("name").item(0).getTextContent());

        return annot;
    }

    private XMLStreamEnumerated getXMLStreamEnumerated(Element element) {
        XMLStreamEnumerated annot = new XMLStreamEnumerated();

        annot.setValue(EnumType.valueOf(element.getElementsByTagName("value").item(0).getTextContent()));

        return annot;
    }

    private XMLStreamTemporal getXMLStreamTemporal(Element element) {
        XMLStreamTemporal annot = new XMLStreamTemporal();

        annot.setPattern(element.getElementsByTagName("pattern").item(0).getTextContent());

        return annot;
    }

    private XMLStreamTransient getXMLStreamTransient(Element element) {
        XMLStreamTransient annot = new XMLStreamTransient();

        //TODO NOTHING

        return annot;
    }
}