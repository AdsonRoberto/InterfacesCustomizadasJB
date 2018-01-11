package org.jb.common.dictionary.elements;

/**
 * Created by fabiano on 12/07/17.
 */

public abstract class RuntimeAbstractTypedDescription {
    String name;
    String type;
    String enclosingType;

    public RuntimeAbstractTypedDescription() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnclosingType() {
        return enclosingType;
    }

    public void setEnclosingType(String enclosingType) {
        this.enclosingType = enclosingType;
    }

    public abstract RuntimeClassDescription getClassDescription();

}
