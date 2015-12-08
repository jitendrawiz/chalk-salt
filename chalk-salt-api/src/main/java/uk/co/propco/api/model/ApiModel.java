/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.chalk.salt.common.dto.BaseDto;

/**
 * The Class ApiDto.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ApiModel extends BaseDto implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5781826382817707873L;

}
