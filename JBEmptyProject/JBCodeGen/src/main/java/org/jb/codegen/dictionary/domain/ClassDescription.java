package org.jb.codegen.dictionary.domain;

import org.jb.ui.annotation.domain.JBAction;
import org.jb.ui.annotation.domain.JBAttribute;
import org.jb.ui.annotation.domain.enums.KindView;
import org.jb.common.config.enums.MappingType;
import org.jb.codegen.util.ArrayUtil;
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

public class ClassDescription extends TypeDescription {
    String superClass;
    Set<String> interfaces;

    Set<ConstructorDescription> constructorsDescriptions;
    Set<AttributeDescription> attributeDescriptions;
    Set<MethodDescription> methodDescriptions;

    Set<AttributeDescription> allAttributeDescriptions;
    Set<MethodDescription> allMethodDescriptions;

    Set<ClassDescription> internalClasses;
    Set<EnumDescription> internalEnums;
    ClassDescription containerClass;

    public ClassDescription() {
        super();

        interfaces = new HashSet<String>();
        constructorsDescriptions = new HashSet<ConstructorDescription>();
        attributeDescriptions = new HashSet<AttributeDescription>();
        methodDescriptions = new HashSet<MethodDescription>();

        allAttributeDescriptions = new HashSet<AttributeDescription>();
        allMethodDescriptions = new HashSet<MethodDescription>();

        internalClasses = new HashSet<ClassDescription>();
        internalEnums = new HashSet<EnumDescription>();
        containerClass = null;
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

    public Set<ConstructorDescription> getConstructorsDescriptions() {
        return constructorsDescriptions;
    }

    public void setConstructorsDescriptions(Set<ConstructorDescription> constructorsDescriptions) {
        this.constructorsDescriptions = constructorsDescriptions;
    }

    public Set<AttributeDescription> getAttributeDescriptions() {
        return attributeDescriptions;
    }

    public void setAttributeDescriptions(Set<AttributeDescription> attributeDescriptions) {
        this.attributeDescriptions = attributeDescriptions;
    }

    public Set<MethodDescription> getMethodDescriptions() {
        return methodDescriptions;
    }

    public void setMethodDescriptions(Set<MethodDescription> methodDescriptions) {
        this.methodDescriptions = methodDescriptions;
    }

    public Set<AttributeDescription> getAllAttributeDescriptions() {
        return allAttributeDescriptions;
    }

    public void setAllAttributeDescriptions(Set<AttributeDescription> allAttributeDescriptions) {
        this.allAttributeDescriptions = allAttributeDescriptions;
    }

    public Set<MethodDescription> getAllMethodDescriptions() {
        return allMethodDescriptions;
    }

    public void setAllMethodDescriptions(Set<MethodDescription> allMethodDescriptions) {
        this.allMethodDescriptions = allMethodDescriptions;
    }

    public Set<ClassDescription> getInternalClasses() {
        return internalClasses;
    }

    public void setInternalClasses(Set<ClassDescription> internalClasses) {
        this.internalClasses = internalClasses;
    }

    public Set<EnumDescription> getInternalEnums() {
        return internalEnums;
    }

    public void setInternalEnums(Set<EnumDescription> internalEnums) {
        this.internalEnums = internalEnums;
    }

    public ClassDescription getContainerClass() {
        return containerClass;
    }

    public void setContainerClass(ClassDescription containerClass) {
        this.containerClass = containerClass;
    }

    /** Insert Methods */

    public void addInterface(String interfaceName) {
        this.interfaces.add(interfaceName);
    }

    public void addConstructorDescription(ConstructorDescription constructorDescription) {
        constructorDescription.setClassDescription(this);
        this.constructorsDescriptions.add(constructorDescription);
    }

    public void addAttributeDescription(AttributeDescription attributeDescription) {
        attributeDescription.setClassDescription(this);
        this.attributeDescriptions.add(attributeDescription);
    }

    public void addMethodDescription(MethodDescription methodDescription) {
        methodDescription.setClassDescription(this);
        this.methodDescriptions.add(methodDescription);
    }

    public void addAttributeDescriptionOnAllCollection(AttributeDescription attributeDescription) {
        for(AttributeDescription a : allAttributeDescriptions) {
            if(a.getName().equals(attributeDescription.getName()))
                return;
        }
        this.allAttributeDescriptions.add(attributeDescription);
    }

    public void addMethodDescriptionOnAllCollection(MethodDescription methodDescription) {
        for(MethodDescription m : allMethodDescriptions) {
            if(m.hasEqualSignature(methodDescription))
                return;
        }
        this.allMethodDescriptions.add(methodDescription);
    }

    public void addClassDescriptionOnInternalClasses(ClassDescription classDescription) {
        classDescription.setContainerClass(this);
        this.internalClasses.add(classDescription);
    }

    public void addEnumDescriptionOnInternalEnums(EnumDescription enumDescription) {
        enumDescription.setContainerClass(this);
        this.internalEnums.add(enumDescription);
    }

    /** Remove Methods */

    public void removeInterface(String interfaceName) {
        this.interfaces.remove(interfaceName);
    }

    public void removeConstructorDescription(ConstructorDescription constructorDescription) {
        constructorDescription.setClassDescription(null);
        this.constructorsDescriptions.remove(constructorDescription);
    }

    public void removeAttributeDescription(AttributeDescription attributeDescription) {
        attributeDescription.setClassDescription(null);
        this.attributeDescriptions.remove(attributeDescription);
    }

    public void removeMethodDescription(MethodDescription methodDescription) {
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

    public ConstructorDescription findConstructorDescriptionByParameters(String[] types) {
        for(ConstructorDescription constructorDescription : constructorsDescriptions) {
            if(constructorDescription.getParameterDescriptions().size() == types.length) {
                boolean parametersEquals = true;
                int i = 0;
                for(ParameterDescription parameterDescription : constructorDescription.getParameterDescriptions()) {
                    if(!types[i].equals(parameterDescription.getType())) {
                        parametersEquals = false;
                        break;
                    }
                    i++;
                }
                if(parametersEquals)
                    return constructorDescription;
            }
        }
        return null;
    }

    public AttributeDescription findAttributeDescriptionByName(String name) {
        for(AttributeDescription attributeDescription : attributeDescriptions) {
            if(attributeDescription.getName().equals(name)) {
                return attributeDescription;
            }
        }
        return null;
    }

    public List<MethodDescription> findMethodDescriptionByName(String name) {
        List<MethodDescription> methods = new ArrayList<MethodDescription>();
        for(MethodDescription methodDescription : methodDescriptions) {
            if(methodDescription.getName().equals(name)) {
                methods.add(methodDescription);
            }
        }
        return methods;
    }

    public MethodDescription findMethodDescriptionByNameIgnoreCase(String name) {
        for(MethodDescription methodDescription : allMethodDescriptions) {
            if(methodDescription.getName().equalsIgnoreCase(name)) {
                return methodDescription;
            }
        }
        return null;
    }

    public MethodDescription findMethodDescriptionByNameParameters(String name, String[] types) {
        for(MethodDescription methodDescription : methodDescriptions) {
            if(methodDescription.getName().equals(name)
                    && methodDescription.getParameterDescriptions().size() == types.length) {
                boolean parametersEquals = true;
                int i = 0;
                for(ParameterDescription parameterDescription : methodDescription.getParameterDescriptions()) {
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

    public MethodDescription getGetMethodDescription(AttributeDescription attributeDescription) {
        return findMethodDescriptionByNameIgnoreCase("get" + attributeDescription.getName());
    }

    public MethodDescription getSetMethodDescription(AttributeDescription attributeDescription) {
        return findMethodDescriptionByNameIgnoreCase("set" + attributeDescription.getName());
    }

    /** Elements for interface */

    public List<AttributeDescription> getAttributesForInterface() {
        List<AttributeDescription> attributesForInterface = new ArrayList<AttributeDescription>();
        for(AttributeDescription ad : allAttributeDescriptions) {
            if(ad.isAnnotatedWith(JBAttribute.class)
                    || ad.isXmlAnnotatedWith(XMLJBAttribute.class)) {
                attributesForInterface.add(ad);
            }
        }

        Collections.sort(attributesForInterface);

        return attributesForInterface;
    }

    public List<AttributeDescription> getAttributesForInterface(KindView kindView) {
        List<AttributeDescription> attributesForInterface = new ArrayList<AttributeDescription>();
        for(AttributeDescription ad : allAttributeDescriptions) {
            if(dictionary.getProjectConfiguration().getCodeGenConfiguration().getMappingType() == MappingType.ANNOTATION
                && ad.isAnnotatedWith(JBAttribute.class)) {
                JBAttribute annot = (JBAttribute) ad.findAnnotationByType(JBAttribute.class);
                if (ArrayUtil.contains(annot.views(), kindView) || ArrayUtil.contains(annot.views(), KindView.ALL)) {
                    attributesForInterface.add(ad);
                }
            }
            if(dictionary.getProjectConfiguration().getCodeGenConfiguration().getMappingType() == MappingType.XML
                    && ad.isXmlAnnotatedWith(XMLJBAttribute.class)) {
                XMLJBAttribute annot = (XMLJBAttribute) ad.findXmlAnnotationByType(XMLJBAttribute.class);
                if (ArrayUtil.contains(annot.getViews(), kindView) || ArrayUtil.contains(annot.getViews(), KindView.ALL)) {
                    attributesForInterface.add(ad);
                }
            }
        }

        Collections.sort(attributesForInterface);

        return attributesForInterface;
    }

    public List<MethodDescription> getMethodsForInterface() {
        List<MethodDescription> methodsForInterface = new ArrayList<MethodDescription>();
        for(MethodDescription md : allMethodDescriptions) {
            if(md.isAnnotatedWith(JBAction.class)
                    || md.isXmlAnnotatedWith(XMLJBAction.class)) {
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
        for(AttributeDescription ad : getAttributesForInterface()) {
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

    public AttributeDescription findIdAttributeDescription() {
        for(AttributeDescription ad : getAttributesForInterface()) {
            if(ad.isAttributeId()) {
                return ad;
            }
        }
        return null;
    }
}
