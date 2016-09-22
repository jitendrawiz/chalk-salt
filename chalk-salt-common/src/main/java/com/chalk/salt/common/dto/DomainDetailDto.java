package com.chalk.salt.common.dto;

public class DomainDetailDto {

    /** The iref uuid. */
    private String irefUuid;

    /** The domain name. */
    private String domainName;

    /** The is defaul. */
    private boolean isDefault;

    /**
     * Instantiates a new domain detail model.
     */
    public DomainDetailDto() {
    }

    /**
     * Gets the iref uuid.
     *
     * @return the iref uuid
     */
    public String getIrefUuid() {
        return irefUuid;
    }

    /**
     * Sets the iref uuid.
     *
     * @param irefUuid the new iref uuid
     */
    public void setIrefUuid(String irefUuid) {
        this.irefUuid = irefUuid;
    }

    /**
     * Gets the domain name.
     *
     * @return the domain name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Sets the domain name.
     *
     * @param domainName the new domain name
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * @return the isDefault
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * @param isDefault the isDefault to set
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    

}
