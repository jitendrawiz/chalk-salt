/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.model.security;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.chalk.salt.api.model.ApiModel;

/**
 * The Class ApiUserRole.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class ApiUserRole extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8695496891408111512L;

    /** The api role uuid. */
    @JsonProperty("roleId")
    private String apiRoleUuid;

    /** The name. */
    @JsonProperty("roleName")
    private String name;

    /** The permissions. */
    private Set<Integer> permissions;

    /**
     * Gets the api role uuid.
     *
     * @return the apiRoleUuid
     */
    public String getApiRoleUuid() {
        return apiRoleUuid;
    }

    /**
     * Sets the api role uuid.
     *
     * @param apiRoleUuid the apiRoleUuid to set
     */
    public void setApiRoleUuid(final String apiRoleUuid) {
        this.apiRoleUuid = apiRoleUuid;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    public Set<Integer> getPermissions() {
        return permissions;
    }

    /**
     * Sets the permissions.
     *
     * @param permissions the permissions to set
     */
    public void setPermissions(final Set<Integer> permissions) {
        this.permissions = permissions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.barbon.referencing.common.dto.BaseDto#equals(java.lang.Object)
     */
    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.barbon.referencing.common.dto.BaseDto#hashCode()
     */
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
