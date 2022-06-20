package com.github.typicalitguy.tasklet;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class DemoTaskletJobExecutionListner implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("before job");
		jobExecution.getJobConfigurationName();
		jobExecution.getId();
		jobExecution.getJobId();
		jobExecution.getJobInstance().getJobName();
		jobExecution.getJobInstance().getId();
		jobExecution.getJobInstance().getInstanceId();
		jobExecution.getJobParameters();
		jobExecution.getExecutionContext();
		jobExecution.getExitStatus();
		jobExecution.getFailureExceptions();
		jobExecution.getAllFailureExceptions();
		jobExecution.getExecutionContext().put("name", "Abhishek Ghosh");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("After job");
		jobExecution.getJobConfigurationName();
		jobExecution.getId();
		jobExecution.getJobId();
		jobExecution.getJobInstance().getJobName();
		jobExecution.getJobInstance().getId();
		jobExecution.getJobInstance().getInstanceId();
		jobExecution.getJobParameters();
		jobExecution.getExecutionContext();
		jobExecution.getExitStatus();
		jobExecution.getFailureExceptions();
		jobExecution.getAllFailureExceptions();
	}

}
