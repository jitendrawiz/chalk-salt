package com.chalk.salt.api.model;

/**
 * The Class NotesModel.
 */
public class NotesModel extends ApiModel
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3473283032889470508L;

    /** The notes uuid. */
    private String notesUuid;

    /** The notes file name. */
    private String notesFileName;

    /** The notes title. */
    private String notesTitle;

    /** The class name. */
    private String className;

    /** The subject name. */
    private String subjectName;

    /** The file uri. */
    private String fileUri;

    /**
     * Gets the notes uuid.
     *
     * @return the notes uuid
     */
    public String getNotesUuid()
        {
            return notesUuid;
        }

    /**
     * Sets the notes uuid.
     *
     * @param notesUuid
     *            the new notes uuid
     */
    public void setNotesUuid(String notesUuid)
        {
            this.notesUuid = notesUuid;
        }

    /**
     * Gets the notes file name.
     *
     * @return the notes file name
     */
    public String getNotesFileName()
        {
            return notesFileName;
        }

    /**
     * Sets the notes file name.
     *
     * @param notesFileName
     *            the new notes file name
     */
    public void setNotesFileName(String notesFileName)
        {
            this.notesFileName = notesFileName;
        }

    /**
     * Gets the notes title.
     *
     * @return the notes title
     */
    public String getNotesTitle()
        {
            return notesTitle;
        }

    /**
     * Sets the notes title.
     *
     * @param notesTitle
     *            the new notes title
     */
    public void setNotesTitle(String notesTitle)
        {
            this.notesTitle = notesTitle;
        }

    /**
     * Gets the class name.
     *
     * @return the class name
     */
    public String getClassName()
        {
            return className;
        }

    /**
     * Sets the class name.
     *
     * @param className
     *            the new class name
     */
    public void setClassName(String className)
        {
            this.className = className;
        }

    /**
     * Gets the subject name.
     *
     * @return the subject name
     */
    public String getSubjectName()
        {
            return subjectName;
        }

    /**
     * Sets the subject name.
     *
     * @param subjectName
     *            the new subject name
     */
    public void setSubjectName(String subjectName)
        {
            this.subjectName = subjectName;
        }

    /**
     * Gets the file uri.
     *
     * @return the file uri
     */
    public String getFileUri()
        {
            return fileUri;
        }

    /**
     * Sets the file uri.
     *
     * @param fileUri
     *            the new file uri
     */
    public void setFileUri(String fileUri)
        {
            this.fileUri = fileUri;
        }

}
