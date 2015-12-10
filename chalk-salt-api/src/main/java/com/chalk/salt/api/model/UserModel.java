/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.api.model;

/**
 * The Class User.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public class UserModel extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3362206995002927542L;

    /** The user name. */
    private String userName;

    /** The password. */
    private String password;

    /** The confirmPassword. */
    private String confirmPassword;

    /** The forename. */
    private String foreName;

    private String lastName;
    /** The surname. */
    private String surName;

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
    
    private String address;
    
    private String city;
    
    private String state;
    
    private String country;
    
    private String pincode;
    
    /**
     * Instantiates a new user.
     */
    public UserModel() {
        super();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurUuid() {
		return securUuid;
	}

	public void setSecurUuid(String securUuid) {
		this.securUuid = securUuid;
	}

	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
