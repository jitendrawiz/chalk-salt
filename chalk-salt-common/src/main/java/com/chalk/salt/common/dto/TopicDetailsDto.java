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
