/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.common.dto;

/**
 * The Enum NotificationRecipientType.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public enum NotificationRecipientType {

    /** The landlord. */
    LANDLORD(1, "Landlord"),

    /** The guarantor. */
    GUARANTOR(2, "Guarantor"),

    /** The tenant. */
    TENANT(3, "Tenant"),

    /** The tenant. */
    AGENT(3, "Agent");

    /** The index. */
    private int index;

    /** The description. */
    private String description;

    /**
     * Instantiates a new notification recipient type.
     * 
     * @param index the index
     * @param description the description
     */
    NotificationRecipientType(final int index, final String description) {
        this.index = index;
        this.description = description;
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

    /**
     * From index.
     * 
     * @param index the index
     * @return the string
     */
    public static String fromIndex(final int index) {
        for (final NotificationRecipientType notificationRecipientType : NotificationRecipientType.values()) {
            if (notificationRecipientType.getIndex() == index) {
                return notificationRecipientType.getDescription();
            }
        }
        return null;
    }

}
