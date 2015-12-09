/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AnnotationUtils.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public abstract class AnnotationUtils {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationUtils.class);

    /**
     * Find annotation.
     *
     * @param <T> the generic type
     * @param method the method
     * @param annotationType the annotation type
     * @return the t
     */
    static public <T extends Annotation> T findAnnotation(final Method method, final Class<T> annotationType) {
        final T anno = method.getAnnotation(annotationType);
        if (anno != null) {
            return anno;
        }
        return findAnnotation(method.getDeclaringClass(), method, annotationType);
    }

    /**
     * Find annotation.
     *
     * @param <T> the generic type
     * @param inspectedClass the inspected class
     * @param prototype the prototype
     * @param annotationType the annotation type
     * @return the t
     */
    static public <T extends Annotation> T findAnnotation(final Class<?> inspectedClass, final Method prototype, final Class<T> annotationType) {
        T anno = null;
        if (inspectedClass == null) {
            return null;
        }
        // look for a matching method, first
        try {
            final Method candidateMethod = inspectedClass.getMethod(prototype.getName(), prototype.getParameterTypes());
            anno = candidateMethod.getAnnotation(annotationType);
            if (anno != null) {
                return anno;
            }
        } catch (NoSuchMethodException | SecurityException exception) {
            LOGGER.debug("An error occurred while inspecting class '" + inspectedClass.getName() + "' for method '" + prototype.getName() + "' against annotation '"
                + annotationType.getName() + "'", exception);
        }

        // see if the class has an annotation
        anno = inspectedClass.getAnnotation(annotationType);
        if (anno != null) {
            return anno;
        }

        // see if any of our interfaces have the annotation
        for (final Class<?> i : inspectedClass.getInterfaces()) {
            anno = findAnnotation(i, prototype, annotationType);
            if (anno != null) {
                return anno;
            }
        }

        // climb the inheritance tree
        if (inspectedClass.getSuperclass() != Object.class) {
            return findAnnotation(inspectedClass.getSuperclass(), prototype, annotationType);
        }
        return null;
    }

    /**
     * Instantiates a new annotation utils.
     */
    private AnnotationUtils() {
    }
}
