package com.chalk.salt.common.dto;

/**
 * The Class StudentsDto.
 */
public class StudentsDto
{

    /** The student id. */
    private Long studentId;

    /** The student name. */
    private String studentName;
    
    
    /** The secur uuid. */
    private String securUuid;
    
    

    /**
     * Gets the secur uuid.
     *
     * @return the secur uuid
     */
    public String getSecurUuid()
        {
            return securUuid;
        }

    /**
     * Sets the secur uuid.
     *
     * @param securUuid the new secur uuid
     */
    public void setSecurUuid(String securUuid)
        {
            this.securUuid = securUuid;
        }

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
