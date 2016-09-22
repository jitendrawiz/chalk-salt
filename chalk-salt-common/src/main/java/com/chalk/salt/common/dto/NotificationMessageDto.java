package com.chalk.salt.common.dto;

import java.util.Date;

/**
 * The Class MailInfoDto.
 *
 * @author shiva
 */
public class NotificationMessageDto extends BaseDto {

    /** The mail to address. */
    private String to;

    /** The from. */
    private String from;

    /** The body of the mail. */
    private String body;

    /** The sent date. */
    private Date sentDate;

    /**
     * Gets the mailto.
     *
     * @return the mail to address
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the mail to address.
     *
     * @param mailto the new mail to address
     */
    public void setTo(final String mailto) {
        this.to = mailto;
    }

    /**
     * Gets the body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param body the new body
     */
    public void setBody(final String body) {
        this.body = body;
    }

    /**
     * Gets the sent date.
     *
     * @return the sent date
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * Sets the sent date.
     *
     * @param sentDate the new sent date
     */
    public void setSentDate(final Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the from.
     *
     * @param from the new from
     */
    public void setFrom(final String from) {
        this.from = from;
    }

}
