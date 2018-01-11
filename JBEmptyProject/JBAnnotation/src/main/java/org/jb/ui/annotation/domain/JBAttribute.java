package org.jb.ui.annotation.domain;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import org.jb.ui.annotation.domain.enums.KindView;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JBAttribute {
	int order();
	String name();
	boolean id() default false;
	KindView []views() default {};
}
