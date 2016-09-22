package com.chalk.salt.common.dto;

import java.util.List;

public class EmailNotificationDto extends NotificationMessageDto {

    /** The subject. */
    private String subject;

    /** The attachments. */
    private List<String> attachments;

    /**
     * Gets the attachments.
     *
     * @return the attachments
     */
    public List<String> getAttachments() {
        return attachments;
    }

    /**
     * Sets the attachments.
     *
     * @param attachments the new attachments
     */
    public void setAttachments(final List<String> attachments) {
        this.attachments = attachments;
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

}
