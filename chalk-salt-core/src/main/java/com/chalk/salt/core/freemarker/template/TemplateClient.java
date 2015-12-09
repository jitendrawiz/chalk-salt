/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.core.freemarker.template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * The Class TempleteClient.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class TemplateClient {

    /** The configuration. */
    @Inject
    private  TemplateConfiguration notificationConfiguration;

    /** The logger. */
    @Inject
    @AppLogger
    protected Logger logger;
    
    /** The Constant threadLocal. */
    private final static ThreadLocal<Object> threadLocal=new ThreadLocal<Object>();
    
    /**
     * Gets the notification message.
     *
     * @return the notification message
     */
        final StringWriter stringWriter=new StringWriter();
        
        /**
         * Gets the notification message.
         *
         * @param dataModel the data model
         * @param key the key
         * @param domainDto the domain dto
         * @return the notification message
         */
        public String getNotificationMessage(final Map<String,Object> dataModel,final String key,final Object domainDto){
            threadLocal.set(domainDto);
        try {
            logger.info("getting notification templet..");
            final Template  template = notificationConfiguration.getConfiguration().getTemplate(key);
            logger.info("processing notification templet..");
            template.process(dataModel, stringWriter);
        } catch (IOException | TemplateException e) {
            logger.error("An error occure during process notification templet");
        }
        return stringWriter.toString();
    }
        
    /**
     * Gets the thread local.
     *
     * @return the thread local
     */
    public static ThreadLocal<Object> getThreadLocal(){
        return threadLocal;
    }
}
