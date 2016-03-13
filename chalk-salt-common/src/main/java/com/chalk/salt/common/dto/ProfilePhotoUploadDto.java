package com.chalk.salt.common.dto;

import java.io.File;

public class ProfilePhotoUploadDto {
	/** The file. */
    private File file;

    /** The name. */
    private String name;

    /** The document type. */
    private Integer documentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}
}
