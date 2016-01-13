package com.chalk.salt.api.model;

public class SubjectModel extends ApiModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7492056214273577170L;

	/** The subject id. */
	private String subjectId;

	/** The subject name. */
	private String subjectName;

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
	 * @param subjectId
	 *            the new subject id
	 */
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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
	 * @param subjectName
	 *            the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
