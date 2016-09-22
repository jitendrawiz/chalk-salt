package com.chalk.salt.api.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.chalk.salt.common.dto.BaseDto;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ApiModel extends BaseDto implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5781826382817707873L;

}
