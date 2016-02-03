package com.chalk.salt.api.model;

/**
 * The Class DiscussionCommentModel.
 */
public class DiscussionCommentModel extends ApiModel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2706081111651830219L;
	
	/** The discussion topic id. */
	private Integer discussionTopicId; 
	
	/** The general comments. */
	private String generalComments; 
	
	/** The created date. */
	private String createdDate; 
	
	/** The modified date. */
	private String modifiedDate; 
	
	/** The delete status. */
	private String deleteStatus;
	
	/** The comment uuid. */
	private String commentUuid;
	
	/**
	 * Gets the discussion topic id.
	 *
	 * @return the discussion topic id
	 */
	public Integer getDiscussionTopicId() {
		return discussionTopicId;
	}
	
	/**
	 * Sets the discussion topic id.
	 *
	 * @param discussionTopicId the new discussion topic id
	 */
	public void setDiscussionTopicId(Integer discussionTopicId) {
		this.discussionTopicId = discussionTopicId;
	}
	
	/**
	 * Gets the general comments.
	 *
	 * @return the general comments
	 */
	public String getGeneralComments() {
		return generalComments;
	}
	
	/**
	 * Sets the general comments.
	 *
	 * @param generalComments the new general comments
	 */
	public void setGeneralComments(String generalComments) {
		this.generalComments = generalComments;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * Gets the delete status.
	 *
	 * @return the delete status
	 */
	public String getDeleteStatus() {
		return deleteStatus;
	}
	
	/**
	 * Sets the delete status.
	 *
	 * @param deleteStatus the new delete status
	 */
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	/**
	 * Gets the comment uuid.
	 *
	 * @return the comment uuid
	 */
	public String getCommentUuid() {
		return commentUuid;
	}
	
	/**
	 * Sets the comment uuid.
	 *
	 * @param commentUuid the new comment uuid
	 */
	public void setCommentUuid(String commentUuid) {
		this.commentUuid = commentUuid;
	}
}
