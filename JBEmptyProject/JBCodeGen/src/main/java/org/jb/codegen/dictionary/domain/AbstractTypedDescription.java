package org.jb.codegen.dictionary.domain;

import org.jb.codegen.util.TypeUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 12/07/17.
 */

public abstract class AbstractTypedDescription {
    String name;
    String type;
    Set<String> modifiers;

    public AbstractTypedDescription() {
        super();

        modifiers = new HashSet<String>();
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

    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<String> modifiers) {
        this.modifiers = modifiers;
    }

    /** Insert Methods */

    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    /** Remove Methods */

    public void removeModifier(String modifier) {
        this.modifiers.remove(modifier);
    }

    /** Collection Info */

    public boolean isCollection() {
        return TypeUtil.isCollection(type);
    }

    public String getCollectionType() {
        return TypeUtil.collectionType(type);
    }

    public abstract ClassDescription getClassDescription();

    /** Contain Methods */

    public boolean containsModifier(String modifier) {
        return modifiers.contains(modifier.toLowerCase()) || modifiers.contains(modifier.toUpperCase());
    }
}
