
package com.chalk.salt.core.quartz;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;

/**
 * The Class QuartzSchedulerBean.
 * 
 * @author <a href="mailto:suraj.raturi@techblue.co.uk">Suraj Raturi</a>
 */
@Startup
@Singleton
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@TransactionManagement(TransactionManagementType.BEAN)
public class QuartzSchedulerBean {

    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /** The job factory. */
    @Inject
    @Named("cdiJobFactory")
    private JobFactory jobFactory;

    /** The Constant QUARTZ_PROPERTIES. */
    private static final String QUARTZ_PROPERTIES = "quartz.properties";

    private Scheduler quartzScheduler;

    /**
     * Initialize scheduler.
     */
    @PostConstruct
    void initializeScheduler() {
        if (quartzScheduler != null) {
            logger.info("Quartz scheduler is already initialized. Aborting init process");
            return;
        }
        logger.info("Initializing Referencing Platform Quartz scheduler");
        try {
            quartzScheduler = new StdSchedulerFactory(QUARTZ_PROPERTIES).getScheduler();
            quartzScheduler.setJobFactory(jobFactory);
            quartzScheduler.startDelayed(120);
            logger.info("Starting Referencing Platform Quartz scheduler initialized successfully");
        } catch (final SchedulerException schedulerException) {
            logger.error("An error occurred while starting quartz scheduler.", schedulerException);
        }
    }

    @PreDestroy
    void stopScheduler() {
        logger.info("Referencing platform application is about to undeploy. Shutting down quartz scheduler.");
        if (quartzScheduler != null) {
            try {
                quartzScheduler.shutdown(false);
            } catch (final SchedulerException se) {
                logger.error("An error occurred while shutting down quartz scheduler instance", se);
            }
        } else {
            logger.warn("Quartz Scheduler instance not found. Cannot initiate scheduler shutdown.");
        }
    }

    /**
     * Start scheduler.
     * 
     * @return the boolean
     */
    public void restartScheduler() {
        logger.info("Processing the request to restart the referencing scheduler ..");
        try {
            stopScheduler();
            quartzScheduler = null;
            initializeScheduler();
        } catch (final Exception exception) {
            if (exception instanceof SchedulerException) {
                logger.error("Error occurred while re starting the referencing scheduler . trying again, Error - ", exception);
                try {
                    quartzScheduler = null;
                    initializeScheduler();
                } catch (final Exception schedulerException) {
                    logger.error("Error occurred while starting up the quartz scheduler. Error - ", schedulerException);
                }
            }
        }
        if (quartzScheduler == null) {
            logger.error("Error occurred while starting the quartz scheduler for the referencing platform , Error - Quartz Scheduler can not be null");
        }
        logger.info("Scheduler successfully started..");
    }

}
