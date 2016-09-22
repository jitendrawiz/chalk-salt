package com.chalk.salt.api.model.security;

public class DomainUserPrincipal extends UserPrincipal {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7158919953148504858L;

    /** The user name. */
    private Long userId;

    /** The secur uuid. */
    private String securUuid;

    /** The user name. */
    private String userName;

    /** The first name. */
    private String fullName;

    /** The email. */
    private String email;

    /**
     * Instantiates a new domain user principal.
     *
     * @param userId the user id
     * @param userName the user name
     */

    public DomainUserPrincipal(final Long userId, final String userName) {
        super(userId);
        this.userName = userName;
        this.userId = userId;
    }

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name.
     *
     * @param fullName the new full name
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }


    /**
     * Gets the user id.
     *
     * @return the userId
     */
    @Override
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the userId to set
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * Sets the user name.
     *
     * @param userName the userName to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets the secur uuid.
     *
     * @return the securUuid
     */
    public String getSecurUuid() {
        return securUuid;
    }

    /**
     * Sets the secur uuid.
     *
     * @param securUuid the securUuid to set
     */
    public void setSecurUuid(final String securUuid) {
        this.securUuid = securUuid;
    }

}
