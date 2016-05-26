package com.chalk.salt.api.model;

import java.util.List;

/**
 * The Class DashBoardDataModel.
 */
public class DashBoardDataModel extends ApiModel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 459187623376951788L;
	
	/** The notes. */
	private List<DashBoardNotesModel> notes;
	
	/** The vedios. */
	private List<DashBoardVediosContentModel> videos;

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public List<DashBoardNotesModel> getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(List<DashBoardNotesModel> notes) {
		this.notes = notes;
	}

	/**
	 * Gets the videos.
	 *
	 * @return the videos
	 */
	public List<DashBoardVediosContentModel> getVideos() {
		return videos;
	}

	/**
	 * Sets the videos.
	 *
	 * @param videos the new videos
	 */
	public void setVideos(List<DashBoardVediosContentModel> videos) {
		this.videos = videos;
	}

	

}
