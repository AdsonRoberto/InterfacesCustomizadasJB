package org.jb.persistence.sqlite.annotation;

import org.jb.persistence.sqlite.annotation.enums.EnumType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enumerated {
	EnumType value();
}
