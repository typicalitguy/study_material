package com.github.typicalitguy.tasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskletJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("first job")
		.incrementer(new RunIdIncrementer())
		.listener(new DemoTaskletJobExecutionListner())
		.start(firstStep())
		.next(secondStep())
		.build();
	}

	private Step firstStep() {
		return stepBuilderFactory.get("first step")
				.tasklet(task(1))
				.listener(new DemoTaskletStepExecutionListener())
				.build();
	}
	private Step secondStep() {
		return stepBuilderFactory.get("second step")
				.tasklet(new DemoTasklet(2))
				.build();
	}

	private Tasklet task(int taskId) {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Step execution context is " +chunkContext.getStepContext().getStepExecutionContext());
				System.out.println("Inside task : "+taskId);
				return RepeatStatus.FINISHED;
			}
		};
	}
}
