package org.jb.stream.reader.runtime.filevisitor;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeAttributeDescription;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.dictionary.elements.RuntimeEnumDescription;
import org.jb.common.dictionary.elements.RuntimeMethodDescription;
import org.jb.common.dictionary.elements.RuntimeParameterDescription;
import org.jb.common.reader.runtime.filevisitor.XMLRuntimeFileVisitor;
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

public class XMLRuntimeStreamFileVisitor extends XMLRuntimeFileVisitor {
    public XMLRuntimeStreamFileVisitor(RuntimeDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public void visitXMLEnumeration(Element element) {
        RuntimeEnumDescription e = null;
        if(dictionary.containsEnumWithKey(element.getAttribute("enum"))) {
            e = dictionary.getEnum(element.getAttribute("enum"));
        }
        else {
            e = new RuntimeEnumDescription();
            e.setCanonicalName(element.getAttribute("enum"));
            dictionary.putEnum(e);
        }
    }

    @Override
    public void visitXMLEntity(Element element) {
        RuntimeClassDescription c = null;
        if(dictionary.containsClassWithKey(element.getAttribute("class"))) {
            c = dictionary.getClass(element.getAttribute("class"));
        }
        else {
            c = new RuntimeClassDescription();
            c.setCanonicalName(element.getAttribute("class"));
            dictionary.putClass(c);
        }

        if(c != null) {
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
    public void visitXMLAttribute(RuntimeClassDescription c, Element element) {
        RuntimeAttributeDescription ad = null;
        if(c.containsAttribute(element.getAttribute("name"))) {
            ad = c.findAttributeDescriptionByName(element.getAttribute("name"));
        }
        else {
            ad = new RuntimeAttributeDescription();
            ad.setName(element.getAttribute("name"));
            ad.setType(element.getAttribute("type"));
            ad.setEnclosingType(element.getAttribute("enclosing-type"));
            c.addAttributeDescription(ad);
        }

        if(ad != null) {
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
    public void visitXMLMethod(RuntimeClassDescription c, Element element) {
        RuntimeMethodDescription md = null;
        if(c.containsMethod(element.getAttribute("name"))) {
            md = c.findMethodDescriptionByName(element.getAttribute("name")).get(0);
        }
        else {
            md = new RuntimeMethodDescription();
            md.setName(element.getAttribute("name"));
            md.setType(element.getAttribute("type"));
            md.setEnclosingType(element.getAttribute("enclosing-type"));
            c.addMethodDescription(md);
        }
    }

    @Override
    public void visitXMLParameter(RuntimeMethodDescription m, Element element) {
        RuntimeParameterDescription pd = null;
        if(m.containsParameter(element.getAttribute("name"))) {
            pd = m.findParameterDescriptionByName(element.getAttribute("name"));
        }
        else {
            pd = new RuntimeParameterDescription();
            pd.setName(element.getAttribute("name"));
            pd.setType(element.getAttribute("type"));
            pd.setEnclosingType(element.getAttribute("enclosing-type"));
            m.addParameterDescription(pd);
        }
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