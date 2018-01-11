package org.jb.codegen.dictionary.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 10/07/17.
 */

public class EnumDescription extends TypeDescription {
    Set<String> constants;

    ClassDescription containerClass;

    public EnumDescription() {
        super();

        constants = new HashSet<String>();
        containerClass = null;
    }

    public Set<String> getConstants() {
        return constants;
    }

    public void setConstants(Set<String> constants) {
        this.constants = constants;
    }

    public ClassDescription getContainerClass() {
        return containerClass;
    }

    public void setContainerClass(ClassDescription containerClass) {
        this.containerClass = containerClass;
    }

    /** Insert Methods */

    public void addConstant(String constant) {
        this.constants.add(constant);
    }

    /** Remove Methods */

    public void removeConstant(String constant) {
        this.constants.remove(constant);
    }

    /** Find Methods */



    /** Contain Methods */

    public boolean containsConstant(String constant) {
        return constants.contains(constant.toLowerCase()) || constants.contains(constant.toUpperCase());
    }
}
