package org.jb.codegen.reader.compile.filevisitor;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.persistence.web.annotation.enums.ContentType;
import org.jb.persistence.web.annotation.enums.HttpMethod;
import org.jb.persistence.web.xml.XMLPathParam;
import org.jb.persistence.web.xml.XMLQueryParam;
import org.jb.persistence.web.xml.XMLRequest;
import org.jb.persistence.web.xml.XMLWebAction;
import org.jb.persistence.web.xml.XMLWebAggregation;
import org.jb.persistence.web.xml.XMLWebComposition;
import org.jb.persistence.web.xml.XMLWebEntity;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabiano on 10/07/17.
 */

public class XMLPersistenceFileVisitor extends XMLFileVisitor {
    public XMLPersistenceFileVisitor(DescriptionDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public void visitXMLEnumeration(Element element) {
        //Do nothing
    }

    @Override
    public void visitXMLEntity(Element element) {
        if(dictionary.containsClassWithKey(element.getAttribute("class"))) {
            ClassDescription c = dictionary.getClass(element.getAttribute("class"));

            //Tag: WEB-ENTITY
            if(element.getElementsByTagName("web-entity") != null
                    && element.getElementsByTagName("web-entity").getLength() > 0) {
                Node nNode = element.getElementsByTagName("web-entity").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    c.addXmlAnnotation(getXMLWebEntity((Element) nNode));
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

            //Tag: WEB-AGGREGATION
            if (element.getElementsByTagName("web-aggregation") != null
                    && element.getElementsByTagName("web-aggregation").getLength() > 0) {
                Node nNode = element.getElementsByTagName("web-aggregation").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLWebAggregation((Element) nNode));
                }
            }

            //Tag: WEB-COMPOSITION
            if (element.getElementsByTagName("web-composition") != null
                    && element.getElementsByTagName("web-composition").getLength() > 0) {
                Node nNode = element.getElementsByTagName("web-composition").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLWebComposition((Element) nNode));
                }
            }
        }
    }

    @Override
    public void visitXMLMethod(ClassDescription c, Element element) {
        if(c.containsMethod(element.getAttribute("name"))) {
            MethodDescription md = c.findMethodDescriptionByName(element.getAttribute("name")).get(0);

            //Tag: WEB-ACTION
            if (element.getElementsByTagName("web-action") != null
                    && element.getElementsByTagName("web-action").getLength() > 0) {
                Node nNode = element.getElementsByTagName("web-action").item(0);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    md.addXmlAnnotation(getXMLWebAction((Element) nNode));
                }
            }
        }
    }

    @Override
    public void visitXMLParameter(MethodDescription m, Element element) {
        //TODO NOTHING
    }

    public XMLWebEntity getXMLWebEntity(Element element) {
        XMLWebEntity annot = new XMLWebEntity();

        //Tag: INSERT
        if(element.getElementsByTagName("insert") != null
                && element.getElementsByTagName("insert").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("insert").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setInsert(getXMLRequest((Element) nNode1));
            }
        }

        //Tag: UPDATE
        if(element.getElementsByTagName("update") != null
                && element.getElementsByTagName("update").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("update").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setUpdate(getXMLRequest((Element) nNode1));
            }
        }

        //Tag: DELETE
        if(element.getElementsByTagName("delete") != null
                && element.getElementsByTagName("delete").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("delete").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setDelete(getXMLRequest((Element) nNode1));
            }
        }

        //Tag: FIND
        if(element.getElementsByTagName("find") != null
                && element.getElementsByTagName("find").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("find").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setFind(getXMLRequest((Element) nNode1));
            }
        }

        //Tag: FIND-BY-ID
        if(element.getElementsByTagName("findById") != null
                && element.getElementsByTagName("findById").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("findById").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setFindById(getXMLRequest((Element) nNode1));
            }
        }

        //Tag: FIND-ALL
        if(element.getElementsByTagName("findAll") != null
                && element.getElementsByTagName("findAll").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("findAll").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setFindAll(getXMLRequest((Element) nNode1));
            }
        }

        return annot;
    }

    public XMLWebAggregation getXMLWebAggregation(Element element) {
        XMLWebAggregation annot = new XMLWebAggregation();

        //Tag: INSERT
        if (element.getElementsByTagName("insert") != null
                && element.getElementsByTagName("insert").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("insert").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setInsert(getXMLRequest((Element) nNode1));
            }
        }

        //Tag: DELETE
        if (element.getElementsByTagName("delete") != null
                && element.getElementsByTagName("delete").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("delete").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setDelete(getXMLRequest((Element) nNode1));
            }
        }

        //Tag: LIST
        if (element.getElementsByTagName("list") != null
                && element.getElementsByTagName("list").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("list").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setList(getXMLRequest((Element) nNode1));
            }
        }

        return annot;
    }

    public XMLWebComposition getXMLWebComposition(Element element) {
        XMLWebComposition annot = new XMLWebComposition();

        //Tag: LIST
        if (element.getElementsByTagName("list") != null
                && element.getElementsByTagName("list").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("list").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setList(getXMLRequest((Element) nNode1));
            }
        }

        return annot;
    }

    public XMLWebAction getXMLWebAction(Element element) {
        XMLWebAction annot = new XMLWebAction();

        //Tag: REQUEST
        if (element.getElementsByTagName("request") != null
                && element.getElementsByTagName("request").getLength() > 0) {
            Node nNode1 = element.getElementsByTagName("request").item(0);
            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                annot.setRequest(getXMLRequest((Element) nNode1));
            }
        }

        return annot;
    }

    public XMLRequest getXMLRequest(Element element) {
        XMLRequest request = new XMLRequest();

        String path = (element.getElementsByTagName("path") != null
                && element.getElementsByTagName("path").getLength() > 0)
                ? element.getElementsByTagName("path").item(0).getTextContent()
                : "";
        HttpMethod method = (element.getElementsByTagName("method") != null
                && element.getElementsByTagName("method").getLength() > 0)
                ? HttpMethod.valueOf(element.getElementsByTagName("method").item(0).getTextContent())
                : HttpMethod.GET;
        ContentType consumeType = (element.getElementsByTagName("consumedType") != null
                && element.getElementsByTagName("consumedType").getLength() > 0)
                ? ContentType.valueOf(element.getElementsByTagName("consumedType").item(0).getTextContent().toUpperCase().replaceAll("/", "_"))
                : ContentType.TEXT_PLAIN;
        ContentType produceType = (element.getElementsByTagName("producedType") != null
                && element.getElementsByTagName("producedType").getLength() > 0)
                ? ContentType.valueOf(element.getElementsByTagName("producedType").item(0).getTextContent().toUpperCase().replaceAll("/", "_"))
                : ContentType.TEXT_PLAIN;

        request.setPath(path);
        request.setMethod(method);
        request.setConsumeType(consumeType);
        request.setProduceType(produceType);

        //Tag: PATH-PARAMETERS
        if(element.getElementsByTagName("pathParameters") != null
                && element.getElementsByTagName("pathParameters").getLength() > 0) {
            Element e = (Element) element.getElementsByTagName("pathParameters").item(0);

            request.setPathParameters(getXMLPathParams(e));
        }

        //Tag: QUERY-PARAMETERS
        if(element.getElementsByTagName("queryParameters") != null
                && element.getElementsByTagName("queryParameters").getLength() > 0) {
            Element e = (Element) element.getElementsByTagName("queryParameters").item(0);

            request.setQueryParameters(getXMLQueryParams(e));
        }

        return request;
    }

    public XMLPathParam[] getXMLPathParams(Element element) {
        List<XMLPathParam> pathList = new ArrayList<XMLPathParam>();

        NodeList nList = element.getElementsByTagName("pathParam");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                pathList.add(getXMLPathParam((Element) nNode));
            }
        }

        return pathList.toArray(new XMLPathParam[pathList.size()]);
    }

    public XMLQueryParam[] getXMLQueryParams(Element element) {
        List<XMLQueryParam> queryList = new ArrayList<XMLQueryParam>();

        NodeList nList = element.getElementsByTagName("queryParam");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                queryList.add(getXMLQueryParam((Element) nNode));
            }
        }

        return queryList.toArray(new XMLQueryParam[queryList.size()]);
    }

    public XMLPathParam getXMLPathParam(Element element) {
        String param = (element.getElementsByTagName("param") != null
                && element.getElementsByTagName("param").getLength() > 0)
                ? element.getElementsByTagName("param").item(0).getTextContent()
                : "";
        String jbTextValue = (element.getElementsByTagName("jb-text-value") != null
                && element.getElementsByTagName("jb-text-value").getLength() > 0)
                ? element.getElementsByTagName("jb-text-value").item(0).getTextContent()
                : "";

        XMLPathParam pathParam = new XMLPathParam();
        pathParam.setParam(param);
        pathParam.setJbTextValue(jbTextValue);

        return pathParam;
    }

    public XMLQueryParam getXMLQueryParam(Element element) {
        String param = (element.getElementsByTagName("param") != null
                && element.getElementsByTagName("param").getLength() > 0)
                ? element.getElementsByTagName("param").item(0).getTextContent()
                : "";
        String jbTextValue = (element.getElementsByTagName("jb-text-value") != null
                && element.getElementsByTagName("jb-text-value").getLength() > 0)
                ? element.getElementsByTagName("jb-text-value").item(0).getTextContent()
                : "";

        XMLQueryParam queryParam = new XMLQueryParam();
        queryParam.setParam(param);
        queryParam.setJbTextValue(jbTextValue);

        return queryParam;
    }
}