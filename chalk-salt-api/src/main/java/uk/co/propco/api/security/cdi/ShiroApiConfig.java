package com.chalk.salt.api.security.cdi;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * The Annotation Shiro API Config.
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, PARAMETER, TYPE })
@Qualifier
public @interface ShiroApiConfig {
}
