package org.jb.codegen.visitor.printer;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.ConstructorDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import java.util.Collection;

/**
 * Created by fabiano on 09/10/17.
 */

public abstract class Printer {
    protected DescriptionDictionary dictionary;

    public Printer() {
        this.dictionary = new DescriptionDictionary();
    }

    public Printer(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public DescriptionDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void print() {
        print(false);
    }

    public void printAllInformation() {
        print(true);
    }

    public void print(boolean completed) {
        Collection<ClassDescription> classDescriptions = dictionary.getClassValues();
        for(ClassDescription classDescription : classDescriptions) {
            printClass(classDescription, completed);
        }

        Collection<EnumDescription> enumDescriptions = dictionary.getEnumValues();
        for(EnumDescription enumDescription : enumDescriptions) {
            printEnum(enumDescription);
        }
    }

    public abstract void printEnum(EnumDescription enumDescription);

    public abstract void printEnumConstant(String enumConstant);

    public abstract void printClass(ClassDescription classDescription, boolean completed);

    public abstract void printClassField(AttributeDescription attributeDescription);

    public abstract void printClassMethod(MethodDescription methodDescription);

    public abstract void printClassConstructor(ConstructorDescription constructorDescription);

    public abstract void printClassMethodParameter(ParameterDescription parameterDescription);
}
