package org.jb.persistence.sqlite.annotation;

import org.jb.persistence.sqlite.annotation.enums.CascadeType;
import org.jb.persistence.sqlite.annotation.enums.FetchType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ManyToOne {
	CascadeType cascade() default CascadeType.NONE;
	FetchType fetch() default FetchType.LAZY;
	boolean optional() default true;
	Class targetEntity() default Object.class;
}
