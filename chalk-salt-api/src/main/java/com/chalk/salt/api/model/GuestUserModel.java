package com.chalk.salt.api.model;

/**
 * The Class GuestUserModel.
 */
public class GuestUserModel extends ApiModel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6965405715033788778L;

	/** The Constant serialVersionUID. */
	
	/** The user name. */
	private String userName;
	
	/** The email. */
	private String email;
	
	/** The mobile no. */
	private String mobileNo;
	
	/** The class id. */
	private String classId;
	
	/** The secur uuid. */
	private String securUuid;
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * Gets the mobile no.
	 *
	 * @return the mobile no
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	
	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	/**
	 * Gets the class id.
	 *
	 * @return the class id
	 */
	public String getClassId() {
		return classId;
	}
	
	/**
	 * Sets the class id.
	 *
	 * @param classId the new class id
	 */
	public void setClassId(String classId) {
		this.classId = classId;
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
	 * @param securUuid the securUuid to set
	 */
	public void setSecurUuid(String securUuid) {
		this.securUuid = securUuid;
	}
}
