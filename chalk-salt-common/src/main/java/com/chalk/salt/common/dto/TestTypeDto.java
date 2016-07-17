package com.chalk.salt.common.dto;

/**
 * The Class TestTypeDto.
 */
public class TestTypeDto {

	/** The test type uuid. */
	private String testTypeUuid;

	/** The test duration. */
	private String testDuration;

	/** The test type id. */
	private Integer testTypeId;

	/** The no of questions. */
	private Integer noOfQuestions;

	/**
	 * Gets the test type uuid.
	 *
	 * @return the test type uuid
	 */
	public String getTestTypeUuid() {
		return testTypeUuid;
	}

	/**
	 * Sets the test type uuid.
	 *
	 * @param testTypeUuid
	 *            the new test type uuid
	 */
	public void setTestTypeUuid(String testTypeUuid) {
		this.testTypeUuid = testTypeUuid;
	}

	/**
	 * Gets the test duration.
	 *
	 * @return the test duration
	 */
	public String getTestDuration() {
		return testDuration;
	}

	/**
	 * Sets the test duration.
	 *
	 * @param testDuration
	 *            the new test duration
	 */
	public void setTestDuration(String testDuration) {
		this.testDuration = testDuration;
	}

	/**
	 * Gets the test type id.
	 *
	 * @return the test type id
	 */
	public Integer getTestTypeId() {
		return testTypeId;
	}

	/**
	 * Sets the test type id.
	 *
	 * @param testTypeId
	 *            the new test type id
	 */
	public void setTestTypeId(Integer testTypeId) {
		this.testTypeId = testTypeId;
	}

	/**
	 * Gets the no of questions.
	 *
	 * @return the no of questions
	 */
	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	/**
	 * Sets the no of questions.
	 *
	 * @param noOfQuestions
	 *            the new no of questions
	 */
	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

}
