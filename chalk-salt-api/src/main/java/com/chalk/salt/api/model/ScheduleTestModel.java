package com.chalk.salt.api.model;

/**
 * The Class ScheduleTestModel.
 */
public class ScheduleTestModel extends ApiModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4329334832913493588L;

	/** The class id. */
	private Integer classId;

	/** The subject id. */
	private Integer subjectId;

	/** The test date. */
	private String testDate;

	/** The test time. */
	private String testTime;

	/** The test type uuid. */
	private String testTypeUuid;

	/** The test tite. */
	private String testTitle;
	
	/** The schedule test uuid. */
	private String scheduleTestUuid;
	
	/** The schedule test created date. */
	private String scheduleTestCreatedDate;
	
	/** The schedule test modified data. */
	private String scheduleTestModifiedData;
	
	

	/**
	 * Gets the schedule test uuid.
	 *
	 * @return the schedule test uuid
	 */
	public String getScheduleTestUuid() {
		return scheduleTestUuid;
	}

	/**
	 * Sets the schedule test uuid.
	 *
	 * @param scheduleTestUuid the new schedule test uuid
	 */
	public void setScheduleTestUuid(String scheduleTestUuid) {
		this.scheduleTestUuid = scheduleTestUuid;
	}

	/**
	 * Gets the schedule test created date.
	 *
	 * @return the schedule test created date
	 */
	public String getScheduleTestCreatedDate() {
		return scheduleTestCreatedDate;
	}

	/**
	 * Sets the schedule test created date.
	 *
	 * @param scheduleTestCreatedDate the new schedule test created date
	 */
	public void setScheduleTestCreatedDate(String scheduleTestCreatedDate) {
		this.scheduleTestCreatedDate = scheduleTestCreatedDate;
	}

	/**
	 * Gets the schedule test modified data.
	 *
	 * @return the schedule test modified data
	 */
	public String getScheduleTestModifiedData() {
		return scheduleTestModifiedData;
	}

	/**
	 * Sets the schedule test modified data.
	 *
	 * @param scheduleTestModifiedData the new schedule test modified data
	 */
	public void setScheduleTestModifiedData(String scheduleTestModifiedData) {
		this.scheduleTestModifiedData = scheduleTestModifiedData;
	}

	/**
	 * Gets the test title.
	 *
	 * @return the test title
	 */
	public String getTestTitle() {
		return testTitle;
	}

	/**
	 * Sets the test title.
	 *
	 * @param testTitle
	 *            the new test title
	 */
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	/**
	 * Gets the class id.
	 *
	 * @return the class id
	 */
	public Integer getClassId() {
		return classId;
	}

	/**
	 * Sets the class id.
	 *
	 * @param classId
	 *            the new class id
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

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
	 * @param subjectId
	 *            the new subject id
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets the test date.
	 *
	 * @return the test date
	 */
	public String getTestDate() {
		return testDate;
	}

	/**
	 * Sets the test date.
	 *
	 * @param testDate
	 *            the new test date
	 */
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	/**
	 * Gets the test time.
	 *
	 * @return the test time
	 */
	public String getTestTime() {
		return testTime;
	}

	/**
	 * Sets the test time.
	 *
	 * @param testTime
	 *            the new test time
	 */
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

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

}
