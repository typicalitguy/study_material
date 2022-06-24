package com.github.typicalitguy.chunk.demo;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ProfessorNotifierTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Logger.log("Professor has been notified");
		return RepeatStatus.FINISHED;
	}

}
