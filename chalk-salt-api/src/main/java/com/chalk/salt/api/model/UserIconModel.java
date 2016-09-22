package com.chalk.salt.api.model;


public class UserIconModel extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1454408224737363751L;

    /** The icon. */
    private String icon;

    /** The link. */
    private String link;

    /** The name. */
    private String name;

    /**
     * Instantiates a new home icon dto.
     */
    public UserIconModel() {
        super();
    }

    /**
     * Gets the icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the icon.
     *
     * @param icon the new icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets the link.
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the link.
     *
     * @param link the new link
     */
    public void setLink(String link) {
        this.link = link;
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

}
