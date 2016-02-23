
package com.chalk.salt.common.dto;


/**
 * The Enum NotificationRecipientType.
 */
public enum NotificationRecipientType {

    
    /** The student. */
    STUDENT(1, "Student"),

   
    /** The admin. */
    ADMIN(2, "Admin");
    

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
