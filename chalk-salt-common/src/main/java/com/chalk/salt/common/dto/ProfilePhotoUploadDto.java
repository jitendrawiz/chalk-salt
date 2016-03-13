package com.chalk.salt.common.dto;

import java.io.File;

/**
 * The Class ProfilePhotoUploadDto.
 */
public class ProfilePhotoUploadDto {
	/** The file. */
    private File file;

    /** The name. */
    private String name;

    /** The document type. */
    private Integer documentType;
    
    /** The file path. */
    private String filePath;

	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the file path.
	 *
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the document type.
	 *
	 * @return the document type
	 */
	public Integer getDocumentType() {
		return documentType;
	}

	/**
	 * Sets the document type.
	 *
	 * @param documentType the new document type
	 */
	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}
}
