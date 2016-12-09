package com.chalk.salt.common.dto;

/**
 * The Class NotificationDto.
 */
public class NotificationDto extends BaseDto
{
    /** The notification uuid. */
    private String notificationUuid;

    /** The start date. */
    private String startDate;

    /** The end date. */
    private String endDate;

    /** The notification. */
    private String notification;

    
    /** The created date. */
    private String createdDate;
    
    
    
    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public String getCreatedDate()
        {
            return createdDate;
        }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(String createdDate)
        {
            this.createdDate = createdDate;
        }

    /**
     * Gets the notification uuid.
     *
     * @return the notification uuid
     */
    public String getNotificationUuid()
        {
            return notificationUuid;
        }

    /**
     * Sets the notification uuid.
     *
     * @param notificationUuid
     *            the new notification uuid
     */
    public void setNotificationUuid(String notificationUuid)
        {
            this.notificationUuid = notificationUuid;
        }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public String getStartDate()
        {
            return startDate;
        }

    /**
     * Sets the start date.
     *
     * @param startDate
     *            the new start date
     */
    public void setStartDate(String startDate)
        {
            this.startDate = startDate;
        }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public String getEndDate()
        {
            return endDate;
        }

    /**
     * Sets the end date.
     *
     * @param endDate
     *            the new end date
     */
    public void setEndDate(String endDate)
        {
            this.endDate = endDate;
        }

    /**
     * Gets the notification.
     *
     * @return the notification
     */
    public String getNotification()
        {
            return notification;
        }

    /**
     * Sets the notification.
     *
     * @param notification
     *            the new notification
     */
    public void setNotification(String notification)
        {
            this.notification = notification;
        }

}
