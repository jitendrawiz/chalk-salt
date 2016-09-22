package com.chalk.salt.api.exceptions.security;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.chalk.salt.common.dto.ErrorResponse;
import com.chalk.salt.common.util.ErrorCode;

public class PermissionDeniedException extends ApplicationAuthorizationException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1681601500385861751L;

    /** The missing permissions. */
    Set<String> missingPermissions = new LinkedHashSet<String>();

    /** The requires all. */
    boolean requiresAll = true;

    /**
     * Instantiates a new permission denied exception.
     */
    public PermissionDeniedException() {
        super();
    }

    /**
     * Instantiates a new permission denied exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public PermissionDeniedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new permission denied exception.
     *
     * @param message the message
     */
    public PermissionDeniedException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new permission denied exception.
     *
     * @param cause the cause
     */
    public PermissionDeniedException(final Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new permission denied exception.
     *
     * @param message the message
     * @param missingPermissions the missing permissions
     * @param requiresAll the requires all
     */
    public PermissionDeniedException(final String message, final Collection<String> missingPermissions, final boolean requiresAll) {
        super(message);
        this.missingPermissions.addAll(missingPermissions);
        this.requiresAll = requiresAll;
    }

    /**
     * Gets the missing permissions.
     *
     * @return the missing permissions
     */
    public Set<String> getMissingPermissions() {
        return missingPermissions;
    }

    /**
     * Checks if is requires all.
     *
     * @return true, if is requires all
     */
    public boolean isRequiresAll() {
        return requiresAll;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.barbon.referencing.api.exceptions.security.ApplicationAuthorizationException#getErrorResponse()
     */
    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = super.getErrorResponse();
        if (errorResponse == null) {
            errorResponse = new ErrorResponse();
        }
        if (errorResponse.getErrorCode() == null) {
            errorResponse.setErrorCode(ErrorCode.AUTHORIZATION_FAILURE);
        }
        if (StringUtils.isBlank(errorResponse.getMessage())) {
            final String message =
                "You require " + (requiresAll ? "all of " : "atleast one of ") + "the following permissions to access the resource: "
                    + StringUtils.join(missingPermissions.iterator(), ", ");
            errorResponse.setMessage(message);
        }
        return errorResponse;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return super.toString() + " requires " + (requiresAll ? "ALL OF " : "AT LEAST ONE OF ") + StringUtils.join(missingPermissions.iterator(), ", ");
    }

}
