package org.jb.codegen.dictionary.domain;

import org.jb.ui.annotation.domain.JBParameter;
import org.jb.ui.xml.domain.XMLJBParameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public abstract class AbstractMethodDescription extends AbstractTypedDescription {
    Set<ParameterDescription> parameterDescriptions;
    ClassDescription classDescription;

    public AbstractMethodDescription() {
        super();

        modifiers = new HashSet<String>();
        parameterDescriptions = new HashSet<ParameterDescription>();
    }

    public Set<ParameterDescription> getParameterDescriptions() {
        return parameterDescriptions;
    }

    public void setParameterDescriptions(Set<ParameterDescription> parameterDescriptions) {
        this.parameterDescriptions = parameterDescriptions;
    }

    public ClassDescription getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(ClassDescription classDescription) {
        this.classDescription = classDescription;
    }

    /** Insert Methods */

    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public void addParameterDescription(ParameterDescription parameterDescription) {
        parameterDescription.setMethodDescription(this);
        this.parameterDescriptions.add(parameterDescription);
    }

    /** Remove Methods */

    public void removeModifier(String modifier) {
        this.modifiers.remove(modifier);
    }

    public void removeParameterDescription(ParameterDescription parameterDescription) {
        parameterDescription.setMethodDescription(null);
        this.parameterDescriptions.remove(parameterDescription);
    }

    /** Find Methods */

    public ParameterDescription findParameterDescriptionByName(String name) {
        for(ParameterDescription parameterDescription : parameterDescriptions) {
            if(parameterDescription.getName().equals(name)) {
                return parameterDescription;
            }
        }
        return null;
    }

    public List<ParameterDescription> getParametersForInterface() {
        List<ParameterDescription> parametersForInterface = new ArrayList<ParameterDescription>();
        for(ParameterDescription pd : parameterDescriptions) {
            if(pd.isAnnotatedWith(JBParameter.class)
                    || pd.isXmlAnnotatedWith(XMLJBParameter.class)) {
                //System.out.println("Parameter: " + pd.getName());
                parametersForInterface.add(pd);
            }
        }

        Collections.sort(parametersForInterface);

        return parametersForInterface;
    }
}
