package org.jb.ui.annotation.visual;

import org.jb.ui.annotation.visual.enums.DescriptionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JBDescription {
	DescriptionType value() default DescriptionType.PRIMARY;
}
