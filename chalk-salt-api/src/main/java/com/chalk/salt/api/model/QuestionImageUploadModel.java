package com.chalk.salt.api.model;

import java.io.File;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * The Class QuestionImageUploadModel.
 */
public class QuestionImageUploadModel extends ApiModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9133665849179214056L;

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
	private String documentType;

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
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * Sets the document type.
	 *
	 * @param documentType the new document type
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
