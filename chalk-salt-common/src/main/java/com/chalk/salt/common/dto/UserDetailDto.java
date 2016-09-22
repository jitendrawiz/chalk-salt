package com.chalk.salt.common.dto;

import java.util.List;
import java.util.Map;

public class UserDetailDto {

    /** The user id. */
    private Long userId;

    /** The secur uuid. */
    private String securUuid;

    /** The user name. */
    private String username;

    /** The password. */
    private String password;

    /** The confirmPassword. */
    private String confirmPassword;

    /** The hashed password. */
    private String hashedPassword;

    /** The first name. */
    private String forename;
    
    /** The middle. */
    private String middle;

    /** The middle name. */
    private String surname;
    
    /** The job title. */
    private String jobTitle;

    /** The display as. */
    private String displayAs;

    /** The email. */
    private String email;

    /** The assigned domains. */
    private List<Map<String, String>> assignedDomains;

    /** The default domain. */
    private Map<String, String> defaultDomain;

    /** The send email. */
    private Boolean sendEmail;

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
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
    public void setForename(String forename) {
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
    public void setSurname(String surname) {
        this.surname = surname;
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
     * Gets the confirm password.
     *
     * @return the confirm password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirm password.
     *
     * @param confirmPassword the new confirm password
     */
    public void setConfirmPassword(String confirmPassword) {
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
     * Gets the assigned domains.
     *
     * @return the assigned domains
     */
    public List<Map<String, String>> getAssignedDomains() {
        return assignedDomains;
    }

    /**
     * Sets the assigned domains.
     *
     * @param assignedDomains the assigned domains
     */
    public void setAssignedDomains(List<Map<String, String>> assignedDomains) {
        this.assignedDomains = assignedDomains;
    }

    /**
     * Gets the default domain.
     *
     * @return the default domain
     */
    public Map<String, String> getDefaultDomain() {
        return defaultDomain;
    }

    /**
     * Sets the default domain.
     *
     * @param defaultDomain the default domain
     */
    public void setDefaultDomain(Map<String, String> defaultDomain) {
        this.defaultDomain = defaultDomain;
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
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
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
    public void setSecurUuid(final String securUuid) {
        this.securUuid = securUuid;
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
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return forename;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(final String firstName) {
        this.forename = firstName;
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
     * Gets the middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return surname;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the new middle name
     */
    public void setMiddleName(String middleName) {
        this.surname = middleName;
    }

    /**
     * Gets the display as.
     *
     * @return the display as
     */
    public String getDisplayAs() {
        return displayAs;
    }

    /**
     * Sets the display as.
     *
     * @param displayAs the new display as
     */
    public void setDisplayAs(final String displayAs) {
        this.displayAs = displayAs;
    }

    /**
     * @return the middle
     */
    public String getMiddle() {
        return middle;
    }

    /**
     * @param middle the middle to set
     */
    public void setMiddle(String middle) {
        this.middle = middle;
    }

}
