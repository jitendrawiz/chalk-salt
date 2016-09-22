package com.chalk.salt.common.cdi.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.common.cdi.annotations.AppLogger;


public class LoggerProducer {

    /**
     * Produce logger.
     *
     * @param injectionPoint the injection point
     * @return logger
     */
    @Produces
    @AppLogger
    public Logger produceLogger(final InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }    
}