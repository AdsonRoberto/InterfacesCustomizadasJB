package org.jb.persistence.sqlite.annotation;

import org.jb.persistence.sqlite.annotation.enums.CascadeType;
import org.jb.persistence.sqlite.annotation.enums.FetchType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToMany {
	CascadeType cascade() default CascadeType.NONE;
	FetchType fetch() default FetchType.LAZY;
	String mappedBy() default "";
	Class targetEntity() default Object.class;
}
