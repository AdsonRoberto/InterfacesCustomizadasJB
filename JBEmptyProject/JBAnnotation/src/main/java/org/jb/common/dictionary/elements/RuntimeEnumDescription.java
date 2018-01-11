package org.jb.common.dictionary.elements;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabiano on 10/07/17.
 */

public class RuntimeEnumDescription extends RuntimeTypeDescription {
    Set<String> constants;

    RuntimeClassDescription containerClass;

    public RuntimeEnumDescription() {
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

    public RuntimeClassDescription getContainerClass() {
        return containerClass;
    }

    public void setContainerClass(RuntimeClassDescription containerClass) {
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
