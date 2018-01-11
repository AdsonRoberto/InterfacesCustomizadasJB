package org.jb.codegen.visitor.visitor;

import org.jb.codegen.dictionary.domain.AbstractMethodDescription;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.ConstructorDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by fabiano on 10/07/17.
 */

public class XMLDescriptionVisitor extends Visitor {
    public XMLDescriptionVisitor(DescriptionDictionary dictionary) {
        super(dictionary);
    }

    @SuppressWarnings("unchecked")
    public void visitEnum(TypeElement typeElement) {
        EnumDescription enumDescription = new EnumDescription();
        enumDescription.setSimpleName(typeElement.getSimpleName().toString());
        enumDescription.setCanonicalName(typeElement.asType().toString());

        for(Modifier modifier : typeElement.getModifiers()) {
            enumDescription.addModifier(modifier.toString());
        }

        //Annotations

        for (Element enclosed : typeElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.ENUM_CONSTANT) {
                visitEnumConstant((VariableElement) enclosed, enumDescription);
            }
        }

        dictionary.putEnum(enumDescription);
    }

    private void visitEnumConstant(VariableElement variableElement, EnumDescription enumDescription) {
        enumDescription.addConstant(variableElement.getSimpleName().toString());
    }

    @SuppressWarnings("unchecked")
    public void visitClass(TypeElement typeElement) {
        ClassDescription classDescription = new ClassDescription();
        classDescription.setSimpleName(typeElement.getSimpleName().toString());
        classDescription.setCanonicalName(typeElement.asType().toString());
        classDescription.setSuperClass(typeElement.getSuperclass().toString());

        for(Modifier modifier : typeElement.getModifiers()) {
            classDescription.addModifier(modifier.toString());
        }
        for(TypeMirror type : typeElement.getInterfaces()) {
            classDescription.addInterface(type.toString());
        }

        //Annotations

        for (Element enclosed : typeElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.FIELD) {
                visitClassField((VariableElement) enclosed, classDescription);
            }
            if(enclosed.getKind() == ElementKind.METHOD) {
                visitClassMethod((ExecutableElement) enclosed, classDescription);
            }
            if(enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                visitClassConstructor((ExecutableElement) enclosed, classDescription);
            }
        }

        dictionary.putClass(classDescription);
    }

    @SuppressWarnings("unchecked")
    private void visitClassField(VariableElement variableElement, ClassDescription classDescription) {
        AttributeDescription attributeDescription = new AttributeDescription();
        attributeDescription.setName(variableElement.getSimpleName().toString());
        attributeDescription.setType(variableElement.asType().toString());

        for(Modifier modifier : variableElement.getModifiers()) {
            attributeDescription.addModifier(modifier.toString());
        }

        //Annotations

        attributeDescription.setClassDescription(classDescription);
        classDescription.addAttributeDescription(attributeDescription);
    }

    @SuppressWarnings("unchecked")
    private void visitClassMethod(ExecutableElement executableElement, ClassDescription classDescription) {
        MethodDescription methodDescription = new MethodDescription();
        methodDescription.setName(executableElement.getSimpleName().toString());
        methodDescription.setType(executableElement.getReturnType().toString());

        for(Modifier modifier : executableElement.getModifiers()) {
            methodDescription.addModifier(modifier.toString());
        }

        //Annotations

        for(VariableElement variableElement : executableElement.getParameters()) {
            visitClassMethodParameter(variableElement, methodDescription);
        }

        methodDescription.setClassDescription(classDescription);
        classDescription.addMethodDescription(methodDescription);
    }

    private void visitClassConstructor(ExecutableElement executableElement, ClassDescription classDescription) {
        ConstructorDescription constructorDescription = new ConstructorDescription();
        constructorDescription.setName(executableElement.getSimpleName().toString());

        for(Modifier modifier : executableElement.getModifiers()) {
            constructorDescription.addModifier(modifier.toString());
        }

        for(VariableElement variableElement : executableElement.getParameters()) {
            visitClassMethodParameter(variableElement, constructorDescription);
        }

        constructorDescription.setClassDescription(classDescription);
        classDescription.addConstructorDescription(constructorDescription);
    }

    private void visitClassMethodParameter(VariableElement variableElement, AbstractMethodDescription abstractMethodDescription) {
        ParameterDescription parameterDescription = new ParameterDescription();
        parameterDescription.setName(variableElement.getSimpleName().toString());
        parameterDescription.setType(variableElement.asType().toString());

        for(Modifier modifier : variableElement.getModifiers()) {
            parameterDescription.addModifier(modifier.toString());
        }

        //Annotations

        parameterDescription.setMethodDescription(abstractMethodDescription);
        abstractMethodDescription.addParameterDescription(parameterDescription);
    }
}