package com.chalk.salt.common.dto;

/**
 * The Class AnswerDto.
 */
public class AnswerDto {

	/** The Answered. */
	private String answered;

	/** The Question id. */
	private String questionId;

	/**
	 * Gets the answered.
	 *
	 * @return the answered
	 */
	public String getAnswered() {
		return answered;
	}

	/**
	 * Sets the answered.
	 *
	 * @param answered the new answered
	 */
	public void setAnswered(String answered) {
		this.answered = answered;
	}

	/**
	 * Gets the question id.
	 *
	 * @return the question id
	 */
	public String getQuestionId() {
		return questionId;
	}

	/**
	 * Sets the question id.
	 *
	 * @param questionId the new question id
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
}
