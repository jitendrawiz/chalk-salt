package com.chalk.salt.common.dto;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class NotificationTemplateRequest extends BaseDto {

    /** The template key. */
    private String templateKey;

    /** The data map. */
    private Map<String, Object> dataMap;

    /** The template body. */
    private String templateBody;

    /** The merge full template. */
    private boolean mergeBodyInTemplate;

    /** The merge subject. */
    private boolean mergeSubject;

    /** The office jndi. */
    private String officeJndi;

    /**
     * Instantiates a new notification template request.
     */
    public NotificationTemplateRequest() {

    }

    /**
     * Instantiates a new notification template request.
     * 
     * @param templateKey the template key
     * @param mergeBodyInTemplate the merge body in template
     * @param mergeSubject the merge subject
     */
    public NotificationTemplateRequest(final String templateKey, final boolean mergeBodyInTemplate, final boolean mergeSubject) {
        if (StringUtils.isBlank(templateKey)) {
            throw new IllegalArgumentException("templateKey is not a valid value in notificationTemplateRequest");
        }
        this.templateKey = templateKey;
        this.mergeBodyInTemplate = mergeBodyInTemplate;
        this.mergeSubject = mergeSubject;
    }

    /**
     * Gets the template key.
     * 
     * @return the template key
     */
    public String getTemplateKey() {
        return templateKey;
    }

    /**
     * Sets the template key.
     * 
     * @param templateKey the new template key
     */
    public void setTemplateKey(final String templateKey) {
        this.templateKey = templateKey;
    }

    /**
     * Gets the data map.
     * 
     * @return the data map
     */
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    /**
     * Sets the data map.
     * 
     * @param dataMap the data map
     */
    public void setDataMap(final Map<String, Object> dataMap) {
        this.dataMap = dataMap;
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

    /**
     * Gets the template body.
     * 
     * @return the template body
     */
    public String getTemplateBody() {
        return templateBody;
    }

    /**
     * Sets the template body.
     * 
     * @param templateBody the new template body
     */
    public void setTemplateBody(final String templateBody) {
        this.templateBody = templateBody;
    }

    /**
     * Gets the office jndi.
     *
     * @return the officeJndi
     */
    public String getOfficeJndi() {
        return officeJndi;
    }

    /**
     * Sets the office jndi.
     *
     * @param officeJndi the officeJndi to set
     */
    public void setOfficeJndi(final String officeJndi) {
        this.officeJndi = officeJndi;
    }

}
