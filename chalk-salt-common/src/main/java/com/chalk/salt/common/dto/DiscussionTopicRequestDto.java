package com.chalk.salt.common.dto;

/**
 * The Class DiscussionTopicRequestDto.
 */
public class DiscussionTopicRequestDto {
	
	/** The topic title. */
	private String topicTitle;
	
	/** The topic description. */
	private String topicDescription;
	
	/** The subject id. */
	private int subjectId;
	
	/** The secur uuid. */
	private String securUuid;
	
	/** The class id. */
	private int classId;
	
	/** The request date. */
	private String requestDate;
	
	/** The approval date. */
	private String approvalDate;
	
	/**
	 * Gets the topic title.
	 *
	 * @return the topic title
	 */
	public String getTopicTitle() {
		return topicTitle;
	}
	
	/**
	 * Sets the topic title.
	 *
	 * @param topicTitle the new topic title
	 */
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	
	/**
	 * Gets the topic description.
	 *
	 * @return the topic description
	 */
	public String getTopicDescription() {
		return topicDescription;
	}
	
	/**
	 * Sets the topic description.
	 *
	 * @param topicDescription the new topic description
	 */
	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}
	
	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public int getSubjectId() {
		return subjectId;
	}
	
	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
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
	 * Gets the class id.
	 *
	 * @return the class id
	 */
	public int getClassId() {
		return classId;
	}

	/**
	 * Sets the class id.
	 *
	 * @param classId the new class id
	 */
	public void setClassId(int classId) {
		this.classId = classId;
	}

	/**
	 * Gets the request date.
	 *
	 * @return the request date
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * Sets the request date.
	 *
	 * @param requestDate the new request date
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Gets the approval date.
	 *
	 * @return the approval date
	 */
	public String getApprovalDate() {
		return approvalDate;
	}

	/**
	 * Sets the approval date.
	 *
	 * @param approvalDate the new approval date
	 */
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	
}
