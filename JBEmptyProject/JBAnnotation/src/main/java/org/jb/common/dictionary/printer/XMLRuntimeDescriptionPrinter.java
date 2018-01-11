package org.jb.common.dictionary.printer;

import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeAttributeDescription;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.dictionary.elements.RuntimeEnumDescription;
import org.jb.common.dictionary.elements.RuntimeMethodDescription;
import org.jb.common.dictionary.elements.RuntimeParameterDescription;

/**
 * Created by fabiano on 10/07/17.
 */

public class XMLRuntimeDescriptionPrinter {
    RuntimeDictionary dictionary;

    public XMLRuntimeDescriptionPrinter(RuntimeDictionary dictionary) {
        super();
        this.dictionary = dictionary;
    }

    public void print() {
        for(String c : dictionary.getClasses().keySet()) {
            printClass(dictionary.getClasses().get(c), false);
        }
        for(String e : dictionary.getEnums().keySet()) {
            printEnum(dictionary.getEnums().get(e));
        }
    }

    public void printEnum(RuntimeEnumDescription enumDescription) {
        System.out.println("ENUM: " + enumDescription.getSimpleName());
        System.out.println("\t" + "CANONICAL NAME: " + enumDescription.getCanonicalName());

        System.out.println("\t" + "XML-ANNOTATIONS: ");
        for(Object a : enumDescription.getXmlAnnotations()) {
            System.out.println("\t\t" + a.toString());
        }

        System.out.println("\t" + "LITERALS: ");
        for(String ec : enumDescription.getConstants()) {
            printEnumConstant(ec);
        }
    }

    public void printEnumConstant(String enumConstant) {
        System.out.println("\t\t" + enumConstant);
    }

    public void printClass(RuntimeClassDescription classDescription, boolean completed) {
        System.out.println("CLASS: " + classDescription.getSimpleName());
        System.out.println("\t" + "CANONICAL NAME: " + classDescription.getCanonicalName());
        System.out.println("\t" + "SUPERCLASSE: " + classDescription.getSuperClass());

        System.out.println("\t" + "INTERFACES: ");
        for(String interfac : classDescription.getInterfaces()) {
            System.out.println("\t\t" + interfac);
        }

        System.out.println("\t" + "XML-ANNOTATIONS: ");
        for(Object a : classDescription.getXmlAnnotations()) {
            System.out.println("\t\t" + a.toString());
        }

        if(completed) {
            System.out.println("\t" + "ALL-ATTRIBUTES: ");
            for (RuntimeAttributeDescription ad : classDescription.getAllAttributeDescriptions()) {
                printClassField(ad);
            }

            System.out.println("\t" + "ALL-METHODS: ");
            for (RuntimeMethodDescription md : classDescription.getAllMethodDescriptions()) {
                printClassMethod(md);
            }
        }
        else {
            System.out.println("\t" + "ATTRIBUTES: ");
            for (RuntimeAttributeDescription ad : classDescription.getAttributeDescriptions()) {
                printClassField(ad);
            }

            System.out.println("\t" + "METHODS: ");
            for (RuntimeMethodDescription md : classDescription.getMethodDescriptions()) {
                printClassMethod(md);
            }
        }
    }

    public void printClassField(RuntimeAttributeDescription attributeDescription) {
        System.out.println("\t\t" + attributeDescription.getName());
        System.out.println("\t\t\t" + "TYPE: " + attributeDescription.getType());

        System.out.println("\t\t\t" + "XML-ANNOTATIONS: ");
        for(Object an : attributeDescription.getXmlAnnotations()) {
            System.out.println("\t\t\t\t" + an.toString());
        }
    }

    public void printClassMethod(RuntimeMethodDescription methodDescription) {
        System.out.println("\t\t" + methodDescription.getName());
        System.out.println("\t\t\t" + "TYPE: " + methodDescription.getType());

        System.out.println("\t\t\t" + "XML-ANNOTATIONS: ");
        for(Object a : methodDescription.getXmlAnnotations()) {
            System.out.println("\t\t\t\t" + a.toString());
        }

        System.out.println("\t\t\t" + "PARAMETERS: ");
        for(RuntimeParameterDescription pd : methodDescription.getParameterDescriptions()) {
            printClassMethodParameter(pd);
        }
    }

    public void printClassMethodParameter(RuntimeParameterDescription parameterDescription) {
        System.out.println("\t\t\t" + parameterDescription.getName());
        System.out.println("\t\t\t\t" + "TYPE: " + parameterDescription.getType());

        if(parameterDescription.getMethodDescription().getClass().isAssignableFrom(RuntimeMethodDescription.class)) {
            System.out.println("\t\t\t\t" + "XML-ANNOTATIONS: ");
            for (Object a : parameterDescription.getXmlAnnotations()) {
                System.out.println("\t\t\t\t\t" + a.toString());
            }
        }
    }
}
