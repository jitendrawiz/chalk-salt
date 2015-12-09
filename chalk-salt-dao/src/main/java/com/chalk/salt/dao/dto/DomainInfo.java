/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.dto;

/**
 * The Class DomainInfo.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class DomainInfo {

    /** The user id. */
    private Long userId;

    /** The secur uuid. */
    private String securUuid;

    /** The active. */
    private boolean active;

    /** The username. */
    private String username;

    /** The password. */
    private String password;
    
    private String foreName;
    
    private String middleName;
    
    private String lastName; 

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the securUuid
	 */
	public String getSecurUuid() {
		return securUuid;
	}

	/**
	 * @param securUuid the securUuid to set
	 */
	public void setSecurUuid(String securUuid) {
		this.securUuid = securUuid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the foreName
	 */
	public String getForeName() {
		return foreName;
	}

	/**
	 * @param foreName the foreName to set
	 */
	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
