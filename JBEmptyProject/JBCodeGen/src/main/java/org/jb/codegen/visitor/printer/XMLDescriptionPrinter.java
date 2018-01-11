package org.jb.codegen.visitor.printer;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.ConstructorDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import java.lang.annotation.Annotation;

/**
 * Created by fabiano on 10/07/17.
 */

public class XMLDescriptionPrinter extends Printer {
    public XMLDescriptionPrinter(DescriptionDictionary dictionary) {
        super(dictionary);
    }

    public void printEnum(EnumDescription enumDescription) {
        System.out.println("ENUM: " + enumDescription.getSimpleName());
        System.out.println("\t" + "CANONICAL NAME: " + enumDescription.getCanonicalName());

        System.out.println("\t" + "MODIFIERS: ");
        for(String m : enumDescription.getModifiers()) {
            System.out.println("\t\t" + m);
        }

        System.out.println("\t" + "ANNOTATIONS: ");
        for(Annotation a : enumDescription.getAnnotations()) {
            System.out.println("\t\t" + a.toString());
        }

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

    public void printClass(ClassDescription classDescription, boolean completed) {
        System.out.println("CLASS: " + classDescription.getSimpleName());
        System.out.println("\t" + "CANONICAL NAME: " + classDescription.getCanonicalName());
        System.out.println("\t" + "SUPERCLASSE: " + classDescription.getSuperClass());

        System.out.println("\t" + "MODIFIERS: ");
        for(String m : classDescription.getModifiers()) {
            System.out.println("\t\t" + m);
        }

        System.out.println("\t" + "INTERFACES: ");
        for(String interfac : classDescription.getInterfaces()) {
            System.out.println("\t\t" + interfac);
        }

        System.out.println("\t" + "ANNOTATIONS: ");
        for(Annotation a : classDescription.getAnnotations()) {
            System.out.println("\t\t" + a.toString());
        }

        System.out.println("\t" + "XML-ANNOTATIONS: ");
        for(Object a : classDescription.getXmlAnnotations()) {
            System.out.println("\t\t" + a.toString());
        }

        System.out.println("\t" + "CONSTRUCTORS: ");
        for(ConstructorDescription cd : classDescription.getConstructorsDescriptions()) {
            printClassConstructor(cd);
        }

        if(completed) {
            System.out.println("\t" + "ALL-ATTRIBUTES: ");
            for (AttributeDescription ad : classDescription.getAllAttributeDescriptions()) {
                printClassField(ad);
            }

            System.out.println("\t" + "ALL-METHODS: ");
            for (MethodDescription md : classDescription.getAllMethodDescriptions()) {
                printClassMethod(md);
            }
        }
        else {
            System.out.println("\t" + "ATTRIBUTES: ");
            for (AttributeDescription ad : classDescription.getAttributeDescriptions()) {
                printClassField(ad);
            }

            System.out.println("\t" + "METHODS: ");
            for (MethodDescription md : classDescription.getMethodDescriptions()) {
                printClassMethod(md);
            }
        }
    }

    public void printClassField(AttributeDescription attributeDescription) {
        System.out.println("\t\t" + attributeDescription.getName());
        System.out.println("\t\t\t" + "TYPE: " + attributeDescription.getType());

        System.out.println("\t\t\t" + "MODIFIERS: ");
        for(String m : attributeDescription.getModifiers()) {
            System.out.println("\t\t\t\t" + m);
        }

        System.out.println("\t\t\t" + "ANNOTATIONS: ");
        for(Annotation an : attributeDescription.getAnnotations()) {
            System.out.println("\t\t\t\t" + an.toString());
        }

        System.out.println("\t\t\t" + "XML-ANNOTATIONS: ");
        for(Object an : attributeDescription.getXmlAnnotations()) {
            System.out.println("\t\t\t\t" + an.toString());
        }
    }

    public void printClassMethod(MethodDescription methodDescription) {
        System.out.println("\t\t" + methodDescription.getName());
        System.out.println("\t\t\t" + "TYPE: " + methodDescription.getType());

        System.out.println("\t\t\t" + "MODIFIERS: ");
        for(String m : methodDescription.getModifiers()) {
            System.out.println("\t\t\t\t" + m);
        }

        System.out.println("\t\t\t" + "ANNOTATIONS: ");
        for(Annotation a : methodDescription.getAnnotations()) {
            System.out.println("\t\t\t\t" + a.toString());
        }

        System.out.println("\t\t\t" + "XML-ANNOTATIONS: ");
        for(Object a : methodDescription.getXmlAnnotations()) {
            System.out.println("\t\t\t\t" + a.toString());
        }

        System.out.println("\t\t\t" + "PARAMETERS: ");
        for(ParameterDescription pd : methodDescription.getParameterDescriptions()) {
            printClassMethodParameter(pd);
        }
    }

    public void printClassConstructor(ConstructorDescription constructorDescription) {
        System.out.println("\t\t" + constructorDescription.getName());

        System.out.println("\t\t\t" + "MODIFIERS: ");
        for(String m : constructorDescription.getModifiers()) {
            System.out.println("\t\t\t\t" + m);
        }

        System.out.println("\t\t\t" + "PARAMETERS: ");
        for(ParameterDescription pd : constructorDescription.getParameterDescriptions()) {
            printClassMethodParameter(pd);
        }
    }

    public void printClassMethodParameter(ParameterDescription parameterDescription) {
        System.out.println("\t\t\t" + parameterDescription.getName());
        System.out.println("\t\t\t\t" + "TYPE: " + parameterDescription.getType());

        System.out.println("\t\t\t\t" + "MODIFIERS: ");
        for(String m : parameterDescription.getModifiers()) {
            System.out.println("\t\t\t\t\t" + m);
        }

        if(parameterDescription.getMethodDescription().getClass().isAssignableFrom(MethodDescription.class)) {
            System.out.println("\t\t\t\t" + "ANNOTATIONS: ");
            for (Annotation a : parameterDescription.getAnnotations()) {
                System.out.println("\t\t\t\t\t" + a.toString());
            }

            System.out.println("\t\t\t\t" + "XML-ANNOTATIONS: ");
            for (Object a : parameterDescription.getXmlAnnotations()) {
                System.out.println("\t\t\t\t\t" + a.toString());
            }
        }
    }
}
