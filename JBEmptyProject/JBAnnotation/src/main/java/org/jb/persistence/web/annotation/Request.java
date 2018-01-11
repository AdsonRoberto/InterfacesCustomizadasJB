package org.jb.persistence.web.annotation;

import org.jb.persistence.web.annotation.enums.ContentType;
import org.jb.persistence.web.annotation.enums.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {
	String path() default "";
	HttpMethod method() default HttpMethod.GET;
	ContentType consumeType() default ContentType.TEXT_PLAIN;
	ContentType produceType() default ContentType.TEXT_PLAIN;
	PathParam[] pathParameters() default {};
	QueryParam[] queryParameters() default {};
}
