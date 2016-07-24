package com.chalk.salt.api.model;

import java.util.List;

/**
 * The Class AnswersModel.
 */
public class AnswersModel extends ApiModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5299544616021764342L;

	/** The answers. */
	List<AnswerModel> answers;

	/** The student id. */
	private String studentId;

	/** The class id. */
	private String classId;

	/** The subject id. */
	private String subjectId;

	/** The test type id. */
	private String testTypeId;
	
	/** The schedule test uuid. */
	private String scheduleTestUuid;
	

	/**
	 * Gets the schedule test uuid.
	 *
	 * @return the schedule test uuid
	 */
	public String getScheduleTestUuid()
        {
            return scheduleTestUuid;
        }

    /**
     * Sets the schedule test uuid.
     *
     * @param scheduleTestUuid the new schedule test uuid
     */
    public void setScheduleTestUuid(String scheduleTestUuid)
        {
            this.scheduleTestUuid = scheduleTestUuid;
        }

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
	 * @param testTypeId the new test type id
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
	public List<AnswerModel> getAnswers() {
		return answers;
	}

	/**
	 * Sets the answers.
	 *
	 * @param answers
	 *            the new answers
	 */
	public void setAnswers(List<AnswerModel> answers) {
		this.answers = answers;
	}

}
