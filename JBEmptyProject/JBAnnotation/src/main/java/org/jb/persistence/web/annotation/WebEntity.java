package org.jb.persistence.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebEntity {
	Request insert();
	Request update();
	Request delete();
	Request find();
	Request findById();
	Request findAll();
}
