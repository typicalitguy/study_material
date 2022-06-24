package com.github.typicalitguy.chunk.demo;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StudentJobCompletionTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Step execution context is " +chunkContext.getStepContext().getStepExecutionContext());
		System.out.println("Student job is completed");
		return null;
	}

}
