package org.jb.ui.annotation.visual.util;

import org.jb.common.config.enums.MappingType;
import org.jb.common.dictionary.project.ProjectMapping;
import org.jb.common.dictionary.project.enums.ProjectLayer;
import org.jb.ui.annotation.visual.JBDescription;
import org.jb.ui.annotation.visual.enums.DescriptionType;
import org.jb.ui.xml.visual.XMLJBDescription;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fabiano on 27/07/17.
 */


public class DescriptionUtil {
    @SuppressWarnings("ALL")
    public static String extractDescription(Object object, DescriptionType type) {
        Class c = object.getClass();
        if(ProjectMapping.getMappingType(c.getClassLoader(), ProjectLayer.CODEGEN) == MappingType.ANNOTATION) {
            for (Method method : c.getMethods()) {
                if (method.isAnnotationPresent(JBDescription.class)) {
                    JBDescription description = method.getAnnotation(JBDescription.class);

                    if (description.value() == type) {
                        if (method.getParameterTypes().length > 0) {
                            return "[ERROR: Method shouldn't have parameters.]";
                        }
                        try {
                            return "" + method.invoke(object).toString();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if(ProjectMapping.getMappingType(c.getClassLoader(), ProjectLayer.CODEGEN) == MappingType.XML) {
            for(Method method : c.getMethods()) {
                if (XMLJBDescription.isAnnoted(method)) {
                    XMLJBDescription description = XMLJBDescription.getAnnotation(method);

                    if (description.getValue() == type) {
                        if (method.getParameterTypes().length > 0) {
                            return "[ERROR: Method shouldn't have parameters.]";
                        }
                        try {
                            return "" + method.invoke(object).toString();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return object.toString();
    }
}
