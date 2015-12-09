package com.chalk.salt.core.quartz;

import javax.annotation.Resource;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Named;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

/**
 * A factory for creating CdiQuartzJob objects.
 * 
 * @author <a href="mailto:arun.singh@techblue.co.uk">Arun Singh</a>
 */

@Named("cdiJobFactory")
public class CdiQuartzJobFactory implements JobFactory {

    @Resource(lookup = "java:comp/BeanManager")
    private BeanManager beanManager;

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.spi.JobFactory#newJob(org.quartz.spi.TriggerFiredBundle)
     */
    @Override
    public Job newJob(final TriggerFiredBundle bundle, final Scheduler scheduler) throws SchedulerException {
        final JobDetail jobDetail = bundle.getJobDetail();
        final Class<? extends Job> jobClass = jobDetail.getJobClass();
        try {
            return getBean(jobClass);
        } catch (final Exception exp) {
            throw new SchedulerException(
                "An error occurred while looking up job - '"
                    + jobDetail.getJobClass().getName() + "' in CDI Registry", exp);
        }
    }

    /**
     * Gets the bean.
     * 
     * @param jobClazz the job clazz
     * @return the bean
     */
    @SuppressWarnings("rawtypes")
    private Job getBean(final Class jobClazz) {
        final Bean<?> bean = beanManager.getBeans(jobClazz).iterator().next();
        final CreationalContext<?> creationalContext = beanManager.createCreationalContext(bean);
        return (Job) beanManager.getReference(bean, jobClazz, creationalContext);
    }

}
