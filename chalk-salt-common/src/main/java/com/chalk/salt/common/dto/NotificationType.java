/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.common.dto;

/**
 * The Enum NotificationType.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public enum NotificationType {

    /** The email. */
    EMAIL(1, "Email"),

    /** The sms. */
    SMS(2, "Sms");

    /** The index. */
    private int index;

    /** The description. */
    private String description;

    /**
     * Instantiates a new notification type.
     * 
     * @param index the index
     * @param description the description
     */
    private NotificationType(final int index, final String description) {
        this.setIndex(index);
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
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the index.
     * 
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index.
     * 
     * @param index the new index
     */
    public void setIndex(final int index) {
        this.index = index;
    }

}
