package org.jb.common.dictionary.elements;

import org.jb.ui.xml.domain.XMLJBAction;
import org.jb.ui.xml.domain.XMLJBAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public class RuntimeClassDescription extends RuntimeTypeDescription {
    String superClass;
    Set<String> interfaces;

    Set<RuntimeAttributeDescription> attributeDescriptions;
    Set<RuntimeMethodDescription> methodDescriptions;

    Set<RuntimeAttributeDescription> allAttributeDescriptions;
    Set<RuntimeMethodDescription> allMethodDescriptions;

    public RuntimeClassDescription() {
        super();

        interfaces = new HashSet<String>();
        attributeDescriptions = new HashSet<RuntimeAttributeDescription>();
        methodDescriptions = new HashSet<RuntimeMethodDescription>();

        allAttributeDescriptions = new HashSet<RuntimeAttributeDescription>();
        allMethodDescriptions = new HashSet<RuntimeMethodDescription>();
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<String> interfaces) {
        this.interfaces = interfaces;
    }

    public Set<RuntimeAttributeDescription> getAttributeDescriptions() {
        return attributeDescriptions;
    }

    public void setAttributeDescriptions(Set<RuntimeAttributeDescription> attributeDescriptions) {
        this.attributeDescriptions = attributeDescriptions;
    }

    public Set<RuntimeMethodDescription> getMethodDescriptions() {
        return methodDescriptions;
    }

    public void setMethodDescriptions(Set<RuntimeMethodDescription> methodDescriptions) {
        this.methodDescriptions = methodDescriptions;
    }

    public Set<RuntimeAttributeDescription> getAllAttributeDescriptions() {
        return allAttributeDescriptions;
    }

    public void setAllAttributeDescriptions(Set<RuntimeAttributeDescription> allAttributeDescriptions) {
        this.allAttributeDescriptions = allAttributeDescriptions;
    }

    public Set<RuntimeMethodDescription> getAllMethodDescriptions() {
        return allMethodDescriptions;
    }

    public void setAllMethodDescriptions(Set<RuntimeMethodDescription> allMethodDescriptions) {
        this.allMethodDescriptions = allMethodDescriptions;
    }

    /** Insert Methods */

    public void addInterface(String interfaceName) {
        this.interfaces.add(interfaceName);
    }

    public void addAttributeDescription(RuntimeAttributeDescription attributeDescription) {
        attributeDescription.setClassDescription(this);
        this.attributeDescriptions.add(attributeDescription);
    }

    public void addMethodDescription(RuntimeMethodDescription methodDescription) {
        methodDescription.setClassDescription(this);
        this.methodDescriptions.add(methodDescription);
    }

    public void addAttributeDescriptionOnAllCollection(RuntimeAttributeDescription attributeDescription) {
        for(RuntimeAttributeDescription a : allAttributeDescriptions) {
            if(a.getName().equals(attributeDescription.getName()))
                return;
        }
        this.allAttributeDescriptions.add(attributeDescription);
    }

    public void addMethodDescriptionOnAllCollection(RuntimeMethodDescription methodDescription) {
        for(RuntimeMethodDescription m : allMethodDescriptions) {
            if(m.hasEqualSignature(methodDescription))
                return;
        }
        this.allMethodDescriptions.add(methodDescription);
    }

    /** Remove Methods */

    public void removeInterface(String interfaceName) {
        this.interfaces.remove(interfaceName);
    }

    public void removeAttributeDescription(RuntimeAttributeDescription attributeDescription) {
        attributeDescription.setClassDescription(null);
        this.attributeDescriptions.remove(attributeDescription);
    }

    public void removeMethodDescription(RuntimeMethodDescription methodDescription) {
        methodDescription.setClassDescription(null);
        this.methodDescriptions.remove(methodDescription);
    }

    /** Find Methods */

    public String findInterfaceByName(String interfaceName) {
        for(String interf : interfaces) {
            if(interf.equals(interfaceName)) {
                return interf;
            }
        }
        return null;
    }

    public RuntimeAttributeDescription findAttributeDescriptionByName(String name) {
        for(RuntimeAttributeDescription attributeDescription : attributeDescriptions) {
            if(attributeDescription.getName().equals(name)) {
                return attributeDescription;
            }
        }
        return null;
    }

    public List<RuntimeMethodDescription> findMethodDescriptionByName(String name) {
        List<RuntimeMethodDescription> methods = new ArrayList<RuntimeMethodDescription>();
        for(RuntimeMethodDescription methodDescription : methodDescriptions) {
            if(methodDescription.getName().equals(name)) {
                methods.add(methodDescription);
            }
        }
        return methods;
    }

    public RuntimeMethodDescription findMethodDescriptionByNameIgnoreCase(String name) {
        for(RuntimeMethodDescription methodDescription : allMethodDescriptions) {
            if(methodDescription.getName().equalsIgnoreCase(name)) {
                return methodDescription;
            }
        }
        return null;
    }

    public RuntimeMethodDescription findMethodDescriptionByNameParameters(String name, String[] types) {
        for(RuntimeMethodDescription methodDescription : methodDescriptions) {
            if(methodDescription.getName().equals(name)
                    && methodDescription.getParameterDescriptions().size() == types.length) {
                boolean parametersEquals = true;
                int i = 0;
                for(RuntimeParameterDescription parameterDescription : methodDescription.getParameterDescriptions()) {
                    if(!types[i].equals(parameterDescription.getType())) {
                        parametersEquals = false;
                        break;
                    }
                    i++;
                }
                if(parametersEquals)
                    return methodDescription;
            }
        }
        return null;
    }

    /** Contain Methods */

    /** GET and SET Method Information */

    public RuntimeMethodDescription getGetMethodDescription(RuntimeAttributeDescription attributeDescription) {
        return findMethodDescriptionByNameIgnoreCase("get" + attributeDescription.getName());
    }

    public RuntimeMethodDescription getSetMethodDescription(RuntimeAttributeDescription attributeDescription) {
        return findMethodDescriptionByNameIgnoreCase("set" + attributeDescription.getName());
    }

    /** Elements for interface */

    public List<RuntimeAttributeDescription> getAttributesForInterface() {
        List<RuntimeAttributeDescription> attributesForInterface = new ArrayList<RuntimeAttributeDescription>();
        for(RuntimeAttributeDescription ad : allAttributeDescriptions) {
            if(ad.isXmlAnnotatedWith(XMLJBAttribute.class)) {
                attributesForInterface.add(ad);
            }
        }

        Collections.sort(attributesForInterface);

        return attributesForInterface;
    }

    public List<RuntimeMethodDescription> getMethodsForInterface() {
        List<RuntimeMethodDescription> methodsForInterface = new ArrayList<RuntimeMethodDescription>();
        for(RuntimeMethodDescription md : allMethodDescriptions) {
            if(md.isXmlAnnotatedWith(XMLJBAction.class)) {
                //System.out.println("Method: " + md.getName());
                methodsForInterface.add(md);
            }
        }

        Collections.sort(methodsForInterface);

        return methodsForInterface;
    }

    public boolean containsAttribute(String name) {
        return findAttributeDescriptionByName(name) != null;
    }

    public boolean containsIdAttribute() {
        for(RuntimeAttributeDescription ad : getAttributesForInterface()) {
            if(ad.isAttributeId()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsMethod(String name) {
        return findMethodDescriptionByName(name) != null
                && !findMethodDescriptionByName(name).isEmpty();
    }

    public boolean containsMethod(String name, String[] types) {
        return findMethodDescriptionByNameParameters(name, types) != null;
    }

    public RuntimeAttributeDescription findIdAttributeDescription() {
        for(RuntimeAttributeDescription ad : getAttributesForInterface()) {
            if(ad.isAttributeId()) {
                return ad;
            }
        }
        return null;
    }
}
