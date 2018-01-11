package org.jb.stream.annotation;

import org.jb.stream.annotation.enums.EnumType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by fabiano on 04/04/17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StreamEnumerated {
    EnumType value() default EnumType.STRING;
}
