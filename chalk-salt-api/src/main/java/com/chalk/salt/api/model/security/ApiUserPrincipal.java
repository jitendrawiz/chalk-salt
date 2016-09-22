package com.chalk.salt.api.model.security;

public class ApiUserPrincipal extends UserPrincipal {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8147884511508319848L;

    /** The consumer key. */
    private final String consumerKey;

    /**
     * Instantiates a new api consumer principal.
     *
     * @param consumerKey the consumer key
     * @param userId the user id
     */
    public ApiUserPrincipal(final String consumerKey, final Long userId) {
        super(userId);
        this.consumerKey = consumerKey;
    }

    /**
     * Gets the consumer key.
     *
     * @return the consumer key
     */
    public String getConsumerKey() {
        return consumerKey;
    }

}
