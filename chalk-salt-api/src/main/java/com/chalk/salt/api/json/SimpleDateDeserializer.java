/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.json;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SimpleDateDeserializer.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class SimpleDateDeserializer extends JsonDeserializer<Date> {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * (non-Javadoc)
     *
     * @see org.codehaus.jackson.map.JsonDeserializer#deserialize(org.codehaus.jackson.JsonParser,
     * org.codehaus.jackson.map.DeserializationContext)
     */
    @Override
    public Date deserialize(final JsonParser jsonparser, final DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException {
        final String dateString = jsonparser.getText();
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
        .appendFixedDecimal(DateTimeFieldType.year(), 4)
        .appendLiteral("-")
        .appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2)
        .appendLiteral("-")
        .appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2)
        .toFormatter().withZoneUTC();
        try {
            final DateTime dt = formatter.parseDateTime(dateString);
            return dt.toDate();
        } catch (final Exception pe) {
            logger.debug("Error occurred while parsing date '" + dateString + "'", pe);
            return null;
        }
    }
}
