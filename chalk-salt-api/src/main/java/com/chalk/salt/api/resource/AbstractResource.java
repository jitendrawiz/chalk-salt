package com.chalk.salt.api.resource;

import org.apache.shiro.SecurityUtils;

import com.chalk.salt.api.model.security.UserPrincipal;

public abstract class AbstractResource {

    /**
     * Gets the agent principal.
     *
     * @return the agent principal
     */
    protected UserPrincipal getUserPrincipal() {
        final Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof UserPrincipal) {
            return (UserPrincipal) principal;
        }
        return null;
    }

}