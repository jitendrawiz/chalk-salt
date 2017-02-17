package com.chalk.salt.common.dto;

import java.util.List;

/**
 * The Class DashBoardDataDto.
 */
public class DashBoardDataDto
{

    /** The notes. */
    private List<DashBoardNotesDto> notes;

    /** The vedios. */
    private List<DashBoardVediosContentDto> videos;

    /** The subjective notes. */
    private DashBoardNotesDto subjectiveNotes;

    /**
     * Gets the subjective notes.
     *
     * @return the subjective notes
     */
    public DashBoardNotesDto getSubjectiveNotes()
        {
            return subjectiveNotes;
        }

    /**
     * Sets the subjective notes.
     *
     * @param subjectiveNotes
     *            the new subjective notes
     */
    public void setSubjectiveNotes(DashBoardNotesDto subjectiveNotes)
        {
            this.subjectiveNotes = subjectiveNotes;
        }

    /**
     * Gets the notes.
     *
     * @return the notes
     */
    public List<DashBoardNotesDto> getNotes()
        {
            return notes;
        }

    /**
     * Sets the notes.
     *
     * @param notes
     *            the new notes
     */
    public void setNotes(List<DashBoardNotesDto> notes)
        {
            this.notes = notes;
        }

    /**
     * Gets the videos.
     *
     * @return the videos
     */
    public List<DashBoardVediosContentDto> getVideos()
        {
            return videos;
        }

    /**
     * Sets the videos.
     *
     * @param videos
     *            the new videos
     */
    public void setVideos(List<DashBoardVediosContentDto> videos)
        {
            this.videos = videos;
        }

}
