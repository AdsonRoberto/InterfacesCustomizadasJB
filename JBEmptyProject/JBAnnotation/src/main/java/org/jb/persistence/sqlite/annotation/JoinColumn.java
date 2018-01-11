package org.jb.persistence.sqlite.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JoinColumn {
	String name();
	String table() default "";
	String referencedColumnName() default "";
	boolean unique() default false;
	boolean nullable() default true;
}
