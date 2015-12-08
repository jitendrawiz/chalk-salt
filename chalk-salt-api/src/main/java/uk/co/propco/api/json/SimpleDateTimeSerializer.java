/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * The Class SimpleDateTimeSerializer.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class SimpleDateTimeSerializer extends JsonSerializer<Date> {

    /*
     * (non-Javadoc)
     * 
     * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator,
     * org.codehaus.jackson.map.SerializerProvider)
     */
    @Override
    public void serialize(final Date value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException, JsonProcessingException {
        final String dateString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(value);
        jgen.writeString(dateString);
    }

}
