package com.chalk.salt.common.dto;

/**
 * The Class QuestionOptionsDto.
 */
public class QuestionOptionsDto {

    /** The Id. */
    private Integer Id;

    /** The Question id. */
    private Integer QuestionId;

    /** The Name. */
    private String Name;

    /** The Is answer. */
    private Boolean IsAnswer;
    
    /** The answer. */
    private String answer;

    /**
     * Gets the answer.
     *
     * @return the answer
     */
    public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the answer.
	 *
	 * @param answer the new answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        Id = id;
    }

    /**
     * Gets the question id.
     *
     * @return the question id
     */
    public Integer getQuestionId() {
        return QuestionId;
    }

    /**
     * Sets the question id.
     *
     * @param questionId the new question id
     */
    public void setQuestionId(Integer questionId) {
        QuestionId = questionId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Gets the checks if is answer.
     *
     * @return the checks if is answer
     */
    public Boolean getIsAnswer() {
        return IsAnswer;
    }

    /**
     * Sets the checks if is answer.
     *
     * @param isAnswer the new checks if is answer
     */
    public void setIsAnswer(Boolean isAnswer) {
        IsAnswer = isAnswer;
    }

}
