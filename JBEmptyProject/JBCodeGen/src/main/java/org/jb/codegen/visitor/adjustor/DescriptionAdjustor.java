package org.jb.codegen.visitor.adjustor;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.dictionary.domain.MethodDescription;

import java.util.Collection;

/**
 * Created by fabiano on 10/07/17.
 */

public class DescriptionAdjustor extends Adjustor {
    public DescriptionAdjustor(DescriptionDictionary dictionary) {
        super(dictionary);
    }

    public void adjust() {
        Collection<ClassDescription> classDescriptions = dictionary.getClassValues();
        for(ClassDescription classDescription : classDescriptions) {
            adjustClass(classDescription);
        }
    }

    public void adjustClass(ClassDescription classDescription) {
        if(dictionary.containsClassWithKey(classDescription.getSuperClass())) {
            ClassDescription superClass = dictionary.getClass(classDescription.getSuperClass());

            adjustClass(superClass);

            /** Adicionando os mesmos atributos e métodos da própria classe. */
            if(classDescription.getAllAttributeDescriptions().isEmpty()) {
                classDescription.getAllAttributeDescriptions().addAll(classDescription.getAttributeDescriptions());
            }
            if(classDescription.getAllMethodDescriptions().isEmpty()) {
                classDescription.getAllMethodDescriptions().addAll(classDescription.getMethodDescriptions());
            }

            /** Adicionando os atributos e métodos da superclasse. */
            for(AttributeDescription ad : superClass.getAllAttributeDescriptions()) {
                classDescription.addAttributeDescriptionOnAllCollection(ad);
            }
            for(MethodDescription md : superClass.getAllMethodDescriptions()) {
                classDescription.addMethodDescriptionOnAllCollection(md);
            }
        }
        else {
            /** Adicionando os mesmos atributos e métodos da própria classe. */
            if(classDescription.getAllAttributeDescriptions().isEmpty()) {
                classDescription.getAllAttributeDescriptions().addAll(classDescription.getAttributeDescriptions());
            }
            if(classDescription.getAllMethodDescriptions().isEmpty()) {
                classDescription.getAllMethodDescriptions().addAll(classDescription.getMethodDescriptions());
            }
        }
    }
}
