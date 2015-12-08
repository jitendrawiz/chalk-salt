package com.chalk.salt.api.model;

/**
 * The Class DomainDetailModel.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public class DomainDetailModel extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2575808578726923483L;

    /** The iref uuid. */
    private String irefUuid;

    /** The domain name. */
    private String domainName;

    /** The is defaul. */
    private Boolean isDefault;

    /**
     * Instantiates a new domain detail model.
     */
    public DomainDetailModel() {
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
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault the isDefault to set
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}
