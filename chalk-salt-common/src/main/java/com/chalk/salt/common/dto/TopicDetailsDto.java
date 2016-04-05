package com.chalk.salt.common.dto;

/**
 * The Class TopicDetailsDto.
 */
public class TopicDetailsDto {
	
	/** The topic name. */
	private String topicTitle;
	
	/** The comments. */
	private Integer comments;
	
	/** The last modified date. */
	private String lastModifiedDate;
	
	/** The last modified user name. */
	private String lastModifiedUserName;
	
	/** The topic id. */
	private Integer topicId;
	
	/** The topic description. */
	private String topicDescription;
	
	/** The topic creation date. */
	private String topicCreationDate;
	
	/**
	 * Gets the topic creation date.
	 *
	 * @return the topic creation date
	 */
	public String getTopicCreationDate() {
		return topicCreationDate;
	}

	/**
	 * Sets the topic creation date.
	 *
	 * @param topicCreationDate the new topic creation date
	 */
	public void setTopicCreationDate(String topicCreationDate) {
		this.topicCreationDate = topicCreationDate;
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
	 * Gets the topic id.
	 *
	 * @return the topic id
	 */
	public Integer getTopicId() {
		return topicId;
	}

	/**
	 * Sets the topic id.
	 *
	 * @param topicId the new topic id
	 */
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

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
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public Integer getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(Integer comments) {
		this.comments = comments;
	}

	/**
	 * Gets the last modified date.
	 *
	 * @return the last modified date
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * Sets the last modified date.
	 *
	 * @param lastModifiedDate the new last modified date
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Gets the last modified user name.
	 *
	 * @return the last modified user name
	 */
	public String getLastModifiedUserName() {
		return lastModifiedUserName;
	}

	/**
	 * Sets the last modified user name.
	 *
	 * @param lastModifiedUserName the new last modified user name
	 */
	public void setLastModifiedUserName(String lastModifiedUserName) {
		this.lastModifiedUserName = lastModifiedUserName;
	}
}
