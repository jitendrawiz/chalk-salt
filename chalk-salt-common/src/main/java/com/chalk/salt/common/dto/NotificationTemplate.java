package com.chalk.salt.common.dto;

public class NotificationTemplate extends BaseDto {

    /** The notification template id. */
    private Long notificationTemplateId;

    /** The primary content. */
    private String primaryContent;

    /** The editable content. */
    private String editableContent;

    /** The subject. */
    private String subject;

    /** The recipient type. */
    private NotificationRecipientType recipientType;

    /** The notification template key. */
    private String notificationTemplateKey;

    /** The notification key. */
    private NotificationType notificationType;

    /** The merge body in template. */
    private boolean mergeBodyInTemplate;

    /** The internal. */
    private boolean internal;

    /** The recipient id. */
    private Long recipientId;

    /** The merge subject. */
    private boolean mergeSubject;

    /**
     * Gets the notification template id.
     * 
     * @return the notification template id
     */
    public Long getNotificationTemplateId() {
        return notificationTemplateId;
    }

    /**
     * Sets the notification template id.
     * 
     * @param notificationTemplateId the new notification template id
     */
    public void setNotificationTemplateId(final Long notificationTemplateId) {
        this.notificationTemplateId = notificationTemplateId;
    }

    /**
     * Gets the primary content.
     * 
     * @return the primary content
     */
    public String getPrimaryContent() {
        return primaryContent;
    }

    /**
     * Sets the primary content.
     * 
     * @param primaryContent the new primary content
     */
    public void setPrimaryContent(final String primaryContent) {
        this.primaryContent = primaryContent;
    }

    /**
     * Gets the editable content.
     * 
     * @return the editable content
     */
    public String getEditableContent() {
        return editableContent;
    }

    /**
     * Sets the editable content.
     * 
     * @param editableContent the new editable content
     */
    public void setEditableContent(final String editableContent) {
        this.editableContent = editableContent;
    }

    /**
     * Gets the recipient type.
     * 
     * @return the recipient type
     */
    public NotificationRecipientType getRecipientType() {
        return recipientType;
    }

    /**
     * Sets the recipient type.
     * 
     * @param recipientType the new recipient type
     */
    public void setRecipientType(final NotificationRecipientType recipientType) {
        this.recipientType = recipientType;
    }

    /**
     * Gets the notification type.
     * 
     * @return the notification type
     */
    public NotificationType getNotificationType() {
        return notificationType;
    }

    /**
     * Sets the notification type.
     * 
     * @param notificationType the new notification type
     */
    public void setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Gets the subject.
     * 
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     * 
     * @param subject the new subject
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * Gets the merge body in template.
     * 
     * @return the merge body in template
     */
    public boolean getMergeBodyInTemplate() {
        return mergeBodyInTemplate;
    }

    /**
     * Sets the merge body in template.
     * 
     * @param mergeBodyInTemplate the new merge body in template
     */
    public void setMergeBodyInTemplate(final boolean mergeBodyInTemplate) {
        this.mergeBodyInTemplate = mergeBodyInTemplate;
    }

    /**
     * Gets the notification template key.
     * 
     * @return the notification template key
     */
    public String getNotificationTemplateKey() {
        return notificationTemplateKey;
    }

    /**
     * Sets the notification template key.
     * 
     * @param notificationTemplateKey the new notification template key
     */
    public void setNotificationTemplateKey(final String notificationTemplateKey) {
        this.notificationTemplateKey = notificationTemplateKey;
    }

    /**
     * Checks if is internal.
     * 
     * @return true, if is internal
     */
    public boolean isInternal() {
        return internal;
    }

    /**
     * Sets the internal.
     * 
     * @param internal the new internal
     */
    public void setInternal(final boolean internal) {
        this.internal = internal;
    }

    /**
     * Gets the recipient id.
     * 
     * @return the recipient id
     */
    public Long getRecipientId() {
        return recipientId;
    }

    /**
     * Sets the recipient id.
     * 
     * @param recipientId the new recipient id
     */
    public void setRecipientId(final Long recipientId) {
        this.recipientId = recipientId;
    }

    /**
     * Checks if is merge subject.
     * 
     * @return true, if is merge subject
     */
    public boolean isMergeSubject() {
        return mergeSubject;
    }

    /**
     * Sets the merge subject.
     * 
     * @param mergeSubject the new merge subject
     */
    public void setMergeSubject(final boolean mergeSubject) {
        this.mergeSubject = mergeSubject;
    }

}
