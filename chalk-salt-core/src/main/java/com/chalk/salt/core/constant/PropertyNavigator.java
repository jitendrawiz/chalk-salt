/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.core.constant;

/**
 * The Class PropertyNavigator.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class PropertyNavigator {

    /**
     * The Enum EntityMenu.
     *
     * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
     */
    public enum EntityMenu {

        /** The property. */
        PROPERTY("Property"),

        /** The landlord. */
        LANDLORD("Landlord"),

        /** The associate. */
        ASSOCIATE("Associate"),

        /** The tenant. */
        TENANT("Tanant"),

        /** The applicant. */
        APPLICANT("Applicant"),

        /** The guarantor. */
        GUARANTOR("Guarantor"),

        /** The contractor. */
        CONTRACTOR("Contractor"),

        /** The agent. */
        AGENT("Agent");

        /**
         * Instantiates a new entity menu.
         *
         * @param title the title
         */
        EntityMenu(final String title) {
            this.title = title;
        }

        /** The title. */
        private final String title;

        /**
         * Gets the title.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Convert.
         *
         * @param string the string
         * @return the entity menu
         */
        public static EntityMenu convert(final String string) {
            if (string == null)
                return null;
            for (final EntityMenu c : values()) {
                if (c.name().compareTo(string) == 0)
                    return c;
            }
            return null;
        }
    };

    /**
     * The Enum EntityTab.
     *
     * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
     */
    public enum EntityTab {

        /** The home. */
        HOME("Home"),

        /** The geographical data. */
        GEOGRAPHICAL_DATA("Geographical Data"),

        /** The unit particulars. */
        UNIT_PARTICULARS("Unit Particulars"),

        /** The rent. */
        RENT("Rent"),

        /** The user roles. */
        USER_ROLES("User Roles"),

        /** The descriptions. */
        DESCRIPTIONS("Descriptions"),

        /** The utilities. */
        UTILITIES("Utilities"),

        /** The document mgt. */
        DOCUMENT_MGT("Document Mgt"),

        /** The maintenance. */
        MAINTENANCE("Maintenance"),

        /** The viewings. */
        VIEWINGS("Viewings"),

        /** The services. */
        SERVICES("Services"),

        /** The internet. */
        INTERNET("Internet"),

        /** The property visits. */
        PROPERTY_VISITS("Property Visits"),

        /** The safety inspections. */
        SAFETY_INSPECTIONS("Safety Inspections"),

        /** The restrictions. */
        RESTRICTIONS("Restrictions"),

        /** The keys. */
        KEYS("Keys"),

        /** The inventory. */
        INVENTORY("Inventory"),

        /** The tenant. */
        TENANT("Tenant(s)"),

        /** The letter audit. */
        LETTER_AUDIT("Letter Audit"),

        /** The notes. */
        NOTES("Notes"),

        /** The media. */
        MEDIA("Pictures/Media"),

        /** The landlord. */
        LANDLORD("Landlord(s)"),

        /** The tasks. */
        TASKS("Tasks"),

        /** The contents insurance. */
        CONTENTS_INSURANCE("Contents Insurance"),

        /** The building insurance. */
        BUILDING_INSURANCE("Building Insurance"),

        /** The meter readings. */
        METER_READINGS("Meter Readings"),

        /** The mortgage. */
        MORTGAGE("Mortgage"),

        /** The clauses. */
        CLAUSES("Clauses"),

        /** The head lease. */
        HEAD_LEASE("Head Lease"),

        /** The feature list. */
        FEATURE_LIST("Feature List"),

        /** The marketing activity. */
        MARKETING_ACTIVITY("Marketing Activity"),

        /** The e sign. */
        E_SIGN("E-Signature"),

        /** The default fees. */
        DEFAULT_FEES("Default Fees");

        /**
         * Instantiates a new entity tab.
         *
         * @param title the title
         */
        EntityTab(final String title) {
            this.title = title;
        }

        /** The title. */
        private final String title;

        /**
         * Gets the title.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Convert.
         *
         * @param string the string
         * @return the entity tab
         */
        public static EntityTab convert(final String string) {
            if (string == null)
                return null;
            for (final EntityTab c : values()) {
                if (c.name().compareTo(string) == 0)
                    return c;
            }
            return null;
        }
    }
}
