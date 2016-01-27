package com.chalk.salt.api.model;

/**
 * The Class SubjectModel.
 */
public class SubjectModel extends ApiModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7492056214273577170L;

	/** The subject id. */
	private Integer subjectId;

	/** The subject name. */
	private String subjectName;


	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(Integer subjectId) {
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
