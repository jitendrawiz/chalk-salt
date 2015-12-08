/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.common.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class User.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public class UserDto {

    /** The user name. */
    private String username;

    /** The password. */
    private String password;

    /** The confirmPassword. */
    private String confirmPassword;

    /** The hashed password. */
    private String hashedPassword;

    /** The forename. */
    private String forename;

    
    /** The middle. */
    private String middle;
    
    /** The surname. */
    private String surname;

    /** The job title. */
    private String jobTitle;

    /** The dispaly as. */
    private String displayAs;

    /** The email address. */
    private String email;

    /** The domain details. */
    private List<DomainDetailDto> domainDetails;

    /** The send email. */
    private Boolean sendEmail;

    /** The secur uuid. */
    private String securUuid;
    
    /** The office id. */
    private Long officeId;
    
    /** The mobile. */
    private String mobile;
    
    /** The fax. */
    private String fax;
    
    /** The referencing id. */
    private Long referencingId;
    
    /** The max results. */
    private Long maxResults;
    
    /** The diary clash level. */
    private Long diaryClashLevel;
    
    /** The commission. */
    private BigDecimal commission;
    
    /** The mail server id. */
    private Long mailServerId;
    
    /** The email user. */
    private String emailUser;
    
    /** The email password. */
    private String emailPassword;
    
    /** The doc mgt user. */
    private String docMgtUser;
    
    /** The doc mgt password. */
    private String docMgtPassword;
    
    /** The description. */
    private String description;
    
    /** The expires. */
    private String expires;
    
    /** The allowed instances. */
    private Long allowedInstances;
    
    /** The negotiator. */
    private String negotiator;
    
    /** The omit diary. */
    private String omitDiary;
    
    /** The disable visibility. */
    private String disableVisibility;
    
    /**
     * Instantiates a new api user dto.
     */
    public UserDto() {
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets the active.
     *
     * @return the active
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirm password.
     *
     * @param confirmPassword the new confirm password
     */
    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Gets the hashed password.
     *
     * @return the hashed password
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Sets the hashed password.
     *
     * @param hashedPassword the new hashed password
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Gets the forename.
     *
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the forename.
     *
     * @param forename the new forename
     */
    public void setForename(final String forename) {
        this.forename = forename;
    }

    /**
     * Gets the surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     *
     * @param surname the new surname
     */
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    /**
     * Gets the job title.
     *
     * @return the job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Sets the job title.
     *
     * @param jobTitle the new job title
     */
    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Gets the dispaly as.
     *
     * @return the dispaly as
     */
    public String getDisplayAs() {
        return displayAs;
    }

    /**
     * Sets the dispaly as.
     *
     * @param displayAs the new display as
     */
    public void setDisplayAs(final String displayAs) {
        this.displayAs = displayAs;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the domain details.
     *
     * @return the domain details
     */
    public List<DomainDetailDto> getDomainDetails() {
        return domainDetails;
    }

    /**
     * Sets the domain details.
     *
     * @param domainDetails the new domain details
     */
    public void setDomainDetails(List<DomainDetailDto> domainDetails) {
        this.domainDetails = domainDetails;
    }

    /**
     * Gets the send email.
     *
     * @return the send email
     */
    public Boolean getSendEmail() {
        return sendEmail;
    }

    /**
     * Sets the send email.
     *
     * @param sendEmail the new send email
     */
    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    /**
     * Gets the secur uuid.
     *
     * @return the secur uuid
     */
    public String getSecurUuid() {
        return securUuid;
    }

    /**
     * Sets the secur uuid.
     *
     * @param securUuid the new secur uuid
     */
    public void setSecurUuid(String securUuid) {
        this.securUuid = securUuid;
    }

    /**
     * Gets the middle.
     *
     * @return the middle
     */
    public String getMiddle() {
        return middle;
    }

    /**
     * Sets the middle.
     *
     * @param middle the middle to set
     */
    public void setMiddle(String middle) {
        this.middle = middle;
    }

    /**
     * @return the officeId
     */
    public Long getOfficeId() {
        return officeId;
    }

    /**
     * @param officeId the officeId to set
     */
    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the referencingId
     */
    public Long getReferencingId() {
        return referencingId;
    }

    /**
     * @param referencingId the referencingId to set
     */
    public void setReferencingId(Long referencingId) {
        this.referencingId = referencingId;
    }

    /**
     * @return the maxResults
     */
    public Long getMaxResults() {
        return maxResults;
    }

    /**
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(Long maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * @return the diaryClashLevel
     */
    public Long getDiaryClashLevel() {
        return diaryClashLevel;
    }

    /**
     * @param diaryClashLevel the diaryClashLevel to set
     */
    public void setDiaryClashLevel(Long diaryClashLevel) {
        this.diaryClashLevel = diaryClashLevel;
    }

    /**
     * @return the commission
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * @return the mailServerId
     */
    public Long getMailServerId() {
        return mailServerId;
    }

    /**
     * @param mailServerId the mailServerId to set
     */
    public void setMailServerId(Long mailServerId) {
        this.mailServerId = mailServerId;
    }

    /**
     * @return the emailUser
     */
    public String getEmailUser() {
        return emailUser;
    }

    /**
     * @param emailUser the emailUser to set
     */
    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    /**
     * @return the emailPassword
     */
    public String getEmailPassword() {
        return emailPassword;
    }

    /**
     * @param emailPassword the emailPassword to set
     */
    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    /**
     * @return the docMgtUser
     */
    public String getDocMgtUser() {
        return docMgtUser;
    }

    /**
     * @param docMgtUser the docMgtUser to set
     */
    public void setDocMgtUser(String docMgtUser) {
        this.docMgtUser = docMgtUser;
    }

    /**
     * @return the docMgtPassword
     */
    public String getDocMgtPassword() {
        return docMgtPassword;
    }

    /**
     * @param docMgtPassword the docMgtPassword to set
     */
    public void setDocMgtPassword(String docMgtPassword) {
        this.docMgtPassword = docMgtPassword;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the expires
     */
    public String getExpires() {
        return expires;
    }

    /**
     * @param expires the expires to set
     */
    public void setExpires(String expires) {
        this.expires = expires;
    }

    /**
     * @return the allowedInstances
     */
    public Long getAllowedInstances() {
        return allowedInstances;
    }

    /**
     * @param allowedInstances the allowedInstances to set
     */
    public void setAllowedInstances(Long allowedInstances) {
        this.allowedInstances = allowedInstances;
    }

    /**
     * @return the negotiator
     */
    public String getNegotiator() {
        return negotiator;
    }

    /**
     * @param negotiator the negotiator to set
     */
    public void setNegotiator(String negotiator) {
        this.negotiator = negotiator;
    }

    /**
     * @return the omitDiary
     */
    public String getOmitDiary() {
        return omitDiary;
    }

    /**
     * @param omitDiary the omitDiary to set
     */
    public void setOmitDiary(String omitDiary) {
        this.omitDiary = omitDiary;
    }

    /**
     * @return the disableVisibility
     */
    public String getDisableVisibility() {
        return disableVisibility;
    }

    /**
     * @param disableVisibility the disableVisibility to set
     */
    public void setDisableVisibility(String disableVisibility) {
        this.disableVisibility = disableVisibility;
    }
    
}
