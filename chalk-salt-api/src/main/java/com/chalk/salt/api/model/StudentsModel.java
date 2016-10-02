package com.chalk.salt.api.model;

/**
 * The Class StudentsModel.
 */
public class StudentsModel
{

    /** The student id. */
    private Long studentId;

    /** The student name. */
    private String studentName;

    /**
     * Gets the student id.
     *
     * @return the student id
     */
    public Long getStudentId()
        {
            return studentId;
        }

    /**
     * Sets the student id.
     *
     * @param studentId
     *            the new student id
     */
    public void setStudentId(Long studentId)
        {
            this.studentId = studentId;
        }

    /**
     * Gets the student name.
     *
     * @return the student name
     */
    public String getStudentName()
        {
            return studentName;
        }

    /**
     * Sets the student name.
     *
     * @param studentName
     *            the new student name
     */
    public void setStudentName(String studentName)
        {
            this.studentName = studentName;
        }

}
