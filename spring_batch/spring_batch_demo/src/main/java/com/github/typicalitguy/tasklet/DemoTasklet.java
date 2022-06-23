package com.github.typicalitguy.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DemoTasklet implements Tasklet {
	private int taskId;

	DemoTasklet(int taskId) {
		this.taskId = taskId;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println(taskId);
		System.out.println(chunkContext.getStepContext());
		return RepeatStatus.FINISHED;
	}

}
