package org.jb.codegen.visitor.visitor;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

/**
 * Created by fabiano on 09/10/17.
 */

public abstract class Visitor {
    protected DescriptionDictionary dictionary;

    public Visitor() {
        this.dictionary = new DescriptionDictionary();
    }

    public Visitor(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public DescriptionDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }
}
