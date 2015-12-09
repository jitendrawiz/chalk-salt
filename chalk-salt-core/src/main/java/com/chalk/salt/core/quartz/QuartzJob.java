package com.chalk.salt.core.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job{

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        System.out.println("Job A is running");
    }

}
