package com.chalk.salt.common.dto;

/**
 * The Class AcademicInfoDto.
 */
public class AcademicInfoDto {

	/** The student class id. */
	private int studentClassId;
	
	/** The student class name. */
	private String studentClassName;
	
	/** The percentage. */
	private float percentage;
	
	/** The previous school. */
	private String previousSchool;
	
	/** The academic info id. */
	private Long academicInfoId;
	
	/**
	 * Gets the academic info id.
	 *
	 * @return the academic info id
	 */
	public Long getAcademicInfoId() {
		return academicInfoId;
	}
	
	/**
	 * Sets the academic info id.
	 *
	 * @param academicInfoId the new academic info id
	 */
	public void setAcademicInfoId(Long academicInfoId) {
		this.academicInfoId = academicInfoId;
	}
	
	/**
	 * Gets the student class id.
	 *
	 * @return the student class id
	 */
	public int getStudentClassId() {
		return studentClassId;
	}
	
	/**
	 * Sets the student class id.
	 *
	 * @param studentClassId the new student class id
	 */
	public void setStudentClassId(int studentClassId) {
		this.studentClassId = studentClassId;
	}
	
	/**
	 * Gets the student class name.
	 *
	 * @return the student class name
	 */
	public String getStudentClassName() {
		return studentClassName;
	}
	
	/**
	 * Sets the student class name.
	 *
	 * @param studentClassName the new student class name
	 */
	public void setStudentClassName(String studentClassName) {
		this.studentClassName = studentClassName;
	}
	
	/**
	 * Gets the percentage.
	 *
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}
	
	/**
	 * Sets the percentage.
	 *
	 * @param percentage the new percentage
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	/**
	 * Gets the previous school.
	 *
	 * @return the previous school
	 */
	public String getPreviousSchool() {
		return previousSchool;
	}
	
	/**
	 * Sets the previous school.
	 *
	 * @param previousSchool the new previous school
	 */
	public void setPreviousSchool(String previousSchool) {
		this.previousSchool = previousSchool;
	}
	
	
}
