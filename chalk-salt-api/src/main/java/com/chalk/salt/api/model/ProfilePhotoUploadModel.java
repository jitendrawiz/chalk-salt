package com.chalk.salt.api.model;

import java.io.File;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ProfilePhotoUploadModel extends ApiModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -159989475170082138L;
	/** The file. */
    @FormParam(value = "file")
    @PartType(MediaType.WILDCARD)
    private File file;

    /** The name. */
    @FormParam(value = "name")
    @PartType(MediaType.TEXT_PLAIN)
    private String name;

    /** The document type. */
    @FormParam(value = "documentType")
    @PartType(MediaType.TEXT_PLAIN)
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
