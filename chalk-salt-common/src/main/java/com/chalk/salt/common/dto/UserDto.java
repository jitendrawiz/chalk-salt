/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.common.dto;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class UserDto.
 */
public class UserDto {

	/** The user id. */
	private Long userId;

	/** The contact id. */
	private Long contactId;

	/** The user name. */
	private String userName;

	/** The password. */
	private String password;

	/** The confirmPassword. */
	private String confirmPassword;

	/** The forename. */
	private String firstName;

	/** The middle name. */
	private String middleName;

	/** The last name. */
	private String lastName;

	/** The email address. */
	private String email;

	/** The secur uuid. */
	private String securUuid;

	/** The disable date. */
	private String disableDate;

	/** The mobile. */
	private String mobile;

	/** The fax. */
	private String landline;

	/** The address. */
	private String address;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The country. */
	private String country;

	/** The pincode. */
	private String pincode;

	/** The active. */
	private Long active;

	/** The student class. */
	private String studentClass;

	/** The student class name. */
	private String studentClassName;

	/** The subjects. */
	private List<SubjectDto> subjects;

	/**
	 * Gets the subjects.
	 *
	 * @return the subjects
	 */
	public List<SubjectDto> getSubjects() {
		return subjects;
	}

	/**
	 * Sets the subjects.
	 *
	 * @param subjects the new subjects
	 */
	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}

	/**
	 * Gets the student class name.
	 *
	 * @return the student class name
	 */
	public String getStudentClassName() {
		return studentClassName;
	}

	/**
	 * Sets the student class name.
	 *
	 * @param studentClassName
	 *            the new student class name
	 */
	public void setStudentClassName(String studentClassName) {
		this.studentClassName = studentClassName;
	}

	/**
	 * Gets the student class.
	 *
	 * @return the student class
	 */
	public String getStudentClass() {
		return studentClass;
	}

	/**
	 * Sets the student class.
	 *
	 * @param studentClass
	 *            the new student class
	 */
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the confirm password.
	 *
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirm password.
	 *
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the secur uuid.
	 *
	 * @return the securUuid
	 */
	public String getSecurUuid() {
		return securUuid;
	}

	/**
	 * Sets the secur uuid.
	 *
	 * @param securUuid
	 *            the securUuid to set
	 */
	public void setSecurUuid(String securUuid) {
		this.securUuid = securUuid;
	}

	/**
	 * Gets the disable date.
	 *
	 * @return the disableDate
	 */
	public String getDisableDate() {
		return disableDate;
	}

	/**
	 * Sets the disable date.
	 *
	 * @param disableDate
	 *            the disableDate to set
	 */
	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	/**
	 * Gets the mobile.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the mobile.
	 *
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the landline.
	 *
	 * @return the landline
	 */
	public String getLandline() {
		return landline;
	}

	/**
	 * Sets the landline.
	 *
	 * @param landline
	 *            the landline to set
	 */
	public void setLandline(String landline) {
		this.landline = landline;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the pincode.
	 *
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * Sets the pincode.
	 *
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the contact id.
	 *
	 * @return the contactId
	 */
	public Long getContactId() {
		return contactId;
	}

	/**
	 * Sets the contact id.
	 *
	 * @param contactId
	 *            the contactId to set
	 */
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public Long getActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active
	 *            the active to set
	 */
	public void setActive(Long active) {
		this.active = active;
	}

}
