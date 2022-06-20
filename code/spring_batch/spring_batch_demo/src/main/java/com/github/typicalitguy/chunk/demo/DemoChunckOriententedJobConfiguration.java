package com.github.typicalitguy.chunk.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoChunckOriententedJobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job firstDemoChunkOrientedJob() {
		return jobBuilderFactory.get("first demo chunk oriented job")
				.incrementer(new RunIdIncrementer())
				.start(firstDemoChunkOrientedStep())
				.build();
	}

	private Step firstDemoChunkOrientedStep() {
		return stepBuilderFactory.get("first demo chunk oriented job")
				.<Integer,String>chunk(2)
				.reader(new DemoItemReader())
				.processor(new DemoItemProcessor())
				.writer(new DemoItemWriter())
				.build();
	}
}
