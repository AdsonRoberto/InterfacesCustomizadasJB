package org.jb.common.dictionary.elements;

import org.jb.ui.xml.domain.XMLJBParameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fabiano on 30/06/17.
 */

public abstract class RuntimeAbstractMethodDescription extends RuntimeAbstractTypedDescription {
    Set<RuntimeParameterDescription> parameterDescriptions;
    RuntimeClassDescription classDescription;

    public RuntimeAbstractMethodDescription() {
        super();

        parameterDescriptions = new HashSet<RuntimeParameterDescription>();
    }

    public Set<RuntimeParameterDescription> getParameterDescriptions() {
        return parameterDescriptions;
    }

    public void setParameterDescriptions(Set<RuntimeParameterDescription> parameterDescriptions) {
        this.parameterDescriptions = parameterDescriptions;
    }

    public RuntimeClassDescription getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(RuntimeClassDescription classDescription) {
        this.classDescription = classDescription;
    }

    /** Insert Methods */

    public void addParameterDescription(RuntimeParameterDescription parameterDescription) {
        parameterDescription.setMethodDescription(this);
        this.parameterDescriptions.add(parameterDescription);
    }

    /** Remove Methods */

    public void removeParameterDescription(RuntimeParameterDescription parameterDescription) {
        parameterDescription.setMethodDescription(null);
        this.parameterDescriptions.remove(parameterDescription);
    }

    /** Find Methods */

    public RuntimeParameterDescription findParameterDescriptionByName(String name) {
        for(RuntimeParameterDescription parameterDescription : parameterDescriptions) {
            if(parameterDescription.getName().equals(name)) {
                return parameterDescription;
            }
        }
        return null;
    }

    public List<RuntimeParameterDescription> getParametersForInterface() {
        List<RuntimeParameterDescription> parametersForInterface = new ArrayList<RuntimeParameterDescription>();
        for(RuntimeParameterDescription pd : parameterDescriptions) {
            if(pd.isXmlAnnotatedWith(XMLJBParameter.class)) {
                //System.out.println("Parameter: " + pd.getName());
                parametersForInterface.add(pd);
            }
        }

        Collections.sort(parametersForInterface);

        return parametersForInterface;
    }
}
