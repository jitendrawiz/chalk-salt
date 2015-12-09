/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.chalk.salt.api.security.cdi.Secured;
import com.chalk.salt.api.util.ApiConstants;

/**
 * The Class PropertyResource.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
@Path(ApiConstants.API_PUBLIC_V1_BASEPATH)
@Secured
public class PropertyResource {

    /**
     * Gets the accessible domains.
     *
     * @return the accessible domains
     */
    @GET
    @Path("/domains")
    @RequiresAuthentication
    @RequiresPermissions(value = "agent:domain:view")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAccessibleDomains() {
        final List<String> domainList = new ArrayList<String>();
        domainList.add("PropCo Enterprise Romans");
        return domainList;

    }
}