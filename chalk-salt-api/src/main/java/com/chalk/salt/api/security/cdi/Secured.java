package com.chalk.salt.api.security.cdi;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * The Annotation Secured.
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, TYPE })
@InterceptorBinding
@Inherited
public @interface Secured {
}
