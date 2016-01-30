package com.chalk.salt.api.model;

/**
 * The Class TopicStatisticsModel.
 */
public class TopicStatisticsModel extends ApiModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8302554778775200964L;

	/** The subject id. */
	private String subjectId;
	
	/** The class id. */
	private String classId;
	
	/** The subject name. */
	private String subjectName;
	
	/** The topics. */
	private String topics;
	
	/** The comments. */
	private String comments;
	
	/** The last modified date. */
	private String lastModifiedDate;
	
	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public String getSubjectId() {
		return subjectId;
	}
	
	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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
	 * Gets the subject name.
	 *
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}
	
	/**
	 * Sets the subject name.
	 *
	 * @param subjectName the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * Gets the topics.
	 *
	 * @return the topics
	 */
	public String getTopics() {
		return topics;
	}
	
	/**
	 * Sets the topics.
	 *
	 * @param topics the new topics
	 */
	public void setTopics(String topics) {
		this.topics = topics;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
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
	
}
