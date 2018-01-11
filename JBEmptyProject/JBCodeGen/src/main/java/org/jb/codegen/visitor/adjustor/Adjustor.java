package org.jb.codegen.visitor.adjustor;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

/**
 * Created by fabiano on 09/10/17.
 */

public abstract class Adjustor {
    protected DescriptionDictionary dictionary;

    public Adjustor() {
        super();
        this.dictionary = new DescriptionDictionary();
    }

    public Adjustor(DescriptionDictionary dictionary) {
        super();
        this.dictionary = dictionary;
    }

    public DescriptionDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(DescriptionDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public abstract void adjust();

    public abstract void adjustClass(ClassDescription classDescription);
}
