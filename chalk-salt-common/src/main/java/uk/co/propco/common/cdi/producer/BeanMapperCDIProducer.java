/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.common.cdi.producer;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.chalk.salt.common.cdi.annotations.BeanMapper;

/**
 * The Class BeanMapperCDIProducer.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class BeanMapperCDIProducer {
    /**
     * Produce mapper.
     *
     * @return the mapper
     */
    @Produces
    @BeanMapper
    @Singleton
    public Mapper produceMapper() {
        final List<String> mappingFiles = new ArrayList<String>();
        mappingFiles.add("uk/co/propco/dozer/dozer-bean-mappings.xml");
        return new DozerBeanMapper(mappingFiles);        
    }
}
