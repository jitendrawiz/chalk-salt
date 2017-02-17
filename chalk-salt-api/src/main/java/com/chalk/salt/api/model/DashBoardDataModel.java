package com.chalk.salt.api.model;

import java.util.List;

/**
 * The Class DashBoardDataModel.
 */
public class DashBoardDataModel extends ApiModel
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 459187623376951788L;

    /** The notes. */
    private List<DashBoardNotesModel> notes;

    /** The videos. */
    private List<DashBoardVediosContentModel> videos;

    /** The subjective notes. */
    private DashBoardNotesModel subjectiveNotes;

    /**
     * Gets the subjective notes.
     *
     * @return the subjective notes
     */
    public DashBoardNotesModel getSubjectiveNotes()
        {
            return subjectiveNotes;
        }

    /**
     * Sets the subjective notes.
     *
     * @param subjectiveNotes
     *            the new subjective notes
     */
    public void setSubjectiveNotes(DashBoardNotesModel subjectiveNotes)
        {
            this.subjectiveNotes = subjectiveNotes;
        }

    /**
     * Gets the notes.
     *
     * @return the notes
     */
    public List<DashBoardNotesModel> getNotes()
        {
            return notes;
        }

    /**
     * Sets the notes.
     *
     * @param notes
     *            the new notes
     */
    public void setNotes(List<DashBoardNotesModel> notes)
        {
            this.notes = notes;
        }

    /**
     * Gets the videos.
     *
     * @return the videos
     */
    public List<DashBoardVediosContentModel> getVideos()
        {
            return videos;
        }

    /**
     * Sets the videos.
     *
     * @param videos
     *            the new videos
     */
    public void setVideos(List<DashBoardVediosContentModel> videos)
        {
            this.videos = videos;
        }

}
