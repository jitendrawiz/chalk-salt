package com.chalk.salt.common.dto;

/**
 * The Enum AuthStatus.
 */
public enum AuthStatus {

    /** The authenticated successfully. */
    ACCEPTED("Accepted"),

    /** The rejected. */
    REJECTED("Rejected");

    /** The description. */
    private String description;

    /**
     * Instantiates a new user auth status.
     *
     * @param description the description
     */
    private AuthStatus(final String description) {
        this.setDescription(description);
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
