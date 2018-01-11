package org.jb.ui.xml.visual;

import org.jb.common.annotation.XMLMethodAnnotation;
import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.dictionary.elements.RuntimeMethodDescription;
import org.jb.common.reader.runtime.configuration.XMLRuntimeConfigurarionReader;
import org.jb.common.singleton.SingletonSession;
import org.jb.ui.annotation.visual.JBDescription;
import org.jb.ui.annotation.visual.enums.DescriptionType;
import org.jb.ui.config.CodeGenConfiguration;
import org.jb.ui.reader.runtime.configuration.XMLRuntimeCodeGenConfigurarionReader;
import org.jb.ui.reader.runtime.mapping.XMLRuntimeCodeGenMappingReader;

import java.lang.reflect.Method;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLJBDescription extends XMLMethodAnnotation {
    DescriptionType value = DescriptionType.PRIMARY;

    public DescriptionType getValue() {
        return value;
    }

    public void setValue(DescriptionType value) {
        this.value = value;
    }

    public static XMLJBDescription adapt(JBDescription jbDescription) {
        XMLJBDescription xmljbDescription = new XMLJBDescription();
        xmljbDescription.setValue(jbDescription.value());
        return xmljbDescription;
    }

    public static boolean isAnnoted(Method method) {
        return new XMLJBDescription().findAnnotation(method) != null;
    }

    public static XMLJBDescription getAnnotation(Method method) {
        return (XMLJBDescription) new XMLJBDescription().findAnnotation(method);
    }

    @Override
    public String toString() {
        return "XMLJBDescription{" +
                "value=" + value +
                '}';
    }
}
