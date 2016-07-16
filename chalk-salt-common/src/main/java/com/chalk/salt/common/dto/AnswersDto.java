package com.chalk.salt.common.dto;

import java.util.List;

/**
 * The Class AnswersDto.
 */
public class AnswersDto {

	/** The answers. */
	List<AnswerDto> answers;

	/** The student id. */
	private String studentId;

	/** The class id. */
	private String classId;

	/** The subject id. */
	private String subjectId;

	/** The test type id. */
	private String testTypeId;

	/**
	 * Gets the test type id.
	 *
	 * @return the test type id
	 */
	public String getTestTypeId() {
		return testTypeId;
	}

	/**
	 * Sets the test type id.
	 *
	 * @param testTypeId
	 *            the new test type id
	 */
	public void setTestTypeId(String testTypeId) {
		this.testTypeId = testTypeId;
	}

	/**
	 * Gets the student id.
	 *
	 * @return the student id
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * Sets the student id.
	 *
	 * @param studentId
	 *            the new student id
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	 * @param classId
	 *            the new class id
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}

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
	 * Gets the answers.
	 *
	 * @return the answers
	 */
	public List<AnswerDto> getAnswers() {
		return answers;
	}

	/**
	 * Sets the answers.
	 *
	 * @param answers
	 *            the new answers
	 */
	public void setAnswers(List<AnswerDto> answers) {
		this.answers = answers;
	}

}
