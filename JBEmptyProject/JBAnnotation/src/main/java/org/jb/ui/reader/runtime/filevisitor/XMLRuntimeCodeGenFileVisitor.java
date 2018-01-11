package org.jb.ui.reader.runtime.filevisitor;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeAttributeDescription;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.dictionary.elements.RuntimeEnumDescription;
import org.jb.common.dictionary.elements.RuntimeMethodDescription;
import org.jb.common.dictionary.elements.RuntimeParameterDescription;
import org.jb.common.reader.runtime.filevisitor.XMLRuntimeFileVisitor;
import org.jb.ui.annotation.domain.enums.KindView;
import org.jb.ui.annotation.visual.enums.DescriptionType;
import org.jb.ui.annotation.visual.enums.TemporalType;
import org.jb.ui.xml.domain.XMLJBAction;
import org.jb.ui.xml.domain.XMLJBAttribute;
import org.jb.ui.xml.domain.XMLJBEntity;
import org.jb.ui.xml.domain.XMLJBEnumeration;
import org.jb.ui.xml.domain.XMLJBParameter;
import org.jb.ui.xml.visual.XMLJBDescription;
import org.jb.ui.xml.visual.XMLJBLarge;
import org.jb.ui.xml.visual.XMLJBTemporal;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabiano on 10/07/17.
 */

public class XMLRuntimeCodeGenFileVisitor extends XMLRuntimeFileVisitor {
    public XMLRuntimeCodeGenFileVisitor(RuntimeDictionary dictionary) {
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

        if(e != null) {
            //Tag: JB-ENUMERATION
            if(element.getElementsByTagName("jb-enumeration") != null
                    && element.getElementsByTagName("jb-enumeration").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-enumeration").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    e.addXmlAnnotation(getXMLJBEnumeration((Element) nNode));
                }
            }
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
            //Tag: JB-ENTITY
            if(element.getElementsByTagName("jb-entity") != null
                    && element.getElementsByTagName("jb-entity").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-entity").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    c.addXmlAnnotation(getXMLJBEntity((Element) nNode));
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
            //Tag: JB-ATTRIBUTE
            if(element.getElementsByTagName("jb-attribute") != null
                    && element.getElementsByTagName("jb-attribute").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-attribute").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLJBAttribute((Element) nNode));
                }
            }

            //Tag: JB-TEMPORAL
            if(element.getElementsByTagName("jb-temporal") != null
                    && element.getElementsByTagName("jb-temporal").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-temporal").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLJBTemporal((Element) nNode));
                }
            }

            //Tag: JB-LARGE
            if(element.getElementsByTagName("jb-large") != null
                    && element.getElementsByTagName("jb-large").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-large").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ad.addXmlAnnotation(getXMLJBLarge((Element) nNode));
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

        if(md != null) {
            //Tag: JB-ACTION
            if(element.getElementsByTagName("jb-action") != null
                    && element.getElementsByTagName("jb-action").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-action").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    md.addXmlAnnotation(getXMLJBAction((Element) nNode));
                }
            }

            //Tag: JB-DESCRIPTION
            if(element.getElementsByTagName("jb-description") != null
                    && element.getElementsByTagName("jb-description").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-description").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    md.addXmlAnnotation(getXMLJBDescription((Element) nNode));
                }
            }


            //Tag: PARAMETER
            NodeList nList = element.getElementsByTagName("parameter");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    visitXMLParameter(md, (Element) nNode);
                }
            }
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

        if(pd != null) {
            //Tag: JB-PARAMETER
            if(element.getElementsByTagName("jb-parameter") != null
                    && element.getElementsByTagName("jb-parameter").getLength() > 0) {
                Node nNode = element.getElementsByTagName("jb-parameter").item(0);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    pd.addXmlAnnotation(getXMLJBParameter((Element) nNode));
                }
            }
        }
    }

    public XMLJBEnumeration getXMLJBEnumeration(Element element) {
        XMLJBEnumeration annot = new XMLJBEnumeration();

        //TODO NOTHING

        return annot;
    }

    public XMLJBEntity getXMLJBEntity(Element element) {
        XMLJBEntity annot = new XMLJBEntity();

        annot.setLabel(element.getAttribute("label"));
        annot.setCollectionLabel(element.getAttribute("collectionLabel"));
        annot.setIcon(element.getAttribute("icon"));

        return annot;
    }

    public XMLJBAttribute getXMLJBAttribute(Element element) {
        XMLJBAttribute annot = new XMLJBAttribute();

        annot.setOrder(Integer.valueOf(element.getElementsByTagName("order").item(0).getTextContent()));
        annot.setName(element.getElementsByTagName("name").item(0).getTextContent());
        annot.setId(Boolean.valueOf(element.getElementsByTagName("id").item(0).getTextContent()).booleanValue());

        //Tag: VIEWS
        if(element.getElementsByTagName("views") != null
                && element.getElementsByTagName("views").getLength() > 0) {
            Node nNode = element.getElementsByTagName("views").item(0);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                annot.setViews(getViews((Element) nNode));
            }
        }

        return annot;
    }

    public XMLJBAction getXMLJBAction(Element element) {
        XMLJBAction annot = new XMLJBAction();

        annot.setOrder(Integer.valueOf(element.getElementsByTagName("order").item(0).getTextContent()));
        annot.setName(element.getElementsByTagName("name").item(0).getTextContent());

        return annot;
    }

    public XMLJBParameter getXMLJBParameter(Element element) {
        XMLJBParameter annot = new XMLJBParameter();

        annot.setOrder(Integer.valueOf(element.getElementsByTagName("order").item(0).getTextContent()));
        annot.setName(element.getElementsByTagName("name").item(0).getTextContent());

        return annot;
    }

    public XMLJBTemporal getXMLJBTemporal(Element element) {
        XMLJBTemporal annot = new XMLJBTemporal();

        annot.setValue(TemporalType.valueOf(element.getElementsByTagName("value").item(0).getTextContent()));

        return annot;
    }

    public XMLJBLarge getXMLJBLarge(Element element) {
        XMLJBLarge annot = new XMLJBLarge();

        //TODO NOTHING

        return annot;
    }

    public XMLJBDescription getXMLJBDescription(Element element) {
        XMLJBDescription annot = new XMLJBDescription();

        annot.setValue(DescriptionType.valueOf(element.getElementsByTagName("value").item(0).getTextContent()));

        return annot;
    }

    public KindView[] getViews(Element element) {
        List<KindView> kindList = new ArrayList<KindView>();

        NodeList nList = element.getElementsByTagName("kind-view");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                kindList.add(getView((Element) nNode));
            }
        }

        return kindList.toArray(new KindView[kindList.size()]);
    }

    public KindView getView(Element element) {
        return KindView.valueOf(element.getTextContent());
    }
}