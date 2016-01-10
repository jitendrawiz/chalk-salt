package com.chalk.salt.common.dto;

public class AcademicInfoDto {

	private int studentClassId;
	private String studentClassName;
	private float percentage;
	private String previousSchool;
	

	public int getStudentClassId() {
		return studentClassId;
	}
	public void setStudentClassId(int studentClassId) {
		this.studentClassId = studentClassId;
	}
	
	public String getStudentClassName() {
		return studentClassName;
	}
	public void setStudentClassName(String studentClassName) {
		this.studentClassName = studentClassName;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	public String getPreviousSchool() {
		return previousSchool;
	}
	public void setPreviousSchool(String previousSchool) {
		this.previousSchool = previousSchool;
	}
	
	
}
