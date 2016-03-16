package com.chalk.salt.common.util;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public enum FileType {
	
	/** The fail to approve topic requests. */
    TYPE(25,".png");

	/** The value. */
    private final int value;
    private final String title;

    /**
     * Instantiates a new error code.
     *
     * @param value the value
     */
    FileType(final int value, final String title) {
        this.value = value;
        this.title = title;
    }

    /**
     * Value.
     *
     * @return the int
     */
    @JsonValue
    public int value() {
        return value;
    }

    public String getTitle(){
    	return this.title;
    }
    /**
     * From value.
     *
     * @param typeCode the type code
     * @return the error code
     */
    @JsonCreator
    public static FileType fromValue(final int typeCode) {
        for (final FileType fileType : FileType.values()) {
            if (fileType.value == typeCode) {
                return fileType;
            }
        }
        throw new IllegalArgumentException("Invalid Status type code: " + typeCode);

    }
}
