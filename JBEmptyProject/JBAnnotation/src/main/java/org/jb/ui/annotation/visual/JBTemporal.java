package org.jb.ui.annotation.visual;

import org.jb.ui.annotation.visual.enums.TemporalType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JBTemporal {
	TemporalType value() default TemporalType.DATE;
}
