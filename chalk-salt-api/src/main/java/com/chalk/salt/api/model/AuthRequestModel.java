package com.chalk.salt.api.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class AuthRequestModel extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6779118085565388668L;

    /** The user name. */
    @JsonProperty
    @NotEmpty(message = "username : username can not be null/blank")
    private String username;

    /** The password. */
    @JsonProperty
    @NotEmpty(message = "password : password can not be null/blank")
    private String password;

    private Boolean rememberMe;
    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
    
}
