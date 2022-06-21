package com.github.typicalitguy.chunk.reader.json;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class JSONReaderChunckOriententedJobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job flatFileReaderChunkOrientedJob() {
		return jobBuilderFactory.get("first JSON reader chunk oriented job")
				.incrementer(new RunIdIncrementer())
				.start(firstDemoChunkOrientedStep())
				.build();
	}

	private Step firstDemoChunkOrientedStep() {
		return stepBuilderFactory.get("first JSON reader chunk oriented step")
				.<StudentJSON, StudentJSON>chunk(3)
				.reader(flatFileItemReader())
				.writer(itemWriter())
				.build();
	}

	public FlatFileItemReader<StudentJSON> flatFileItemReader() {
		FlatFileItemReader<StudentJSON> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(absoluteResourcePath("/src/main/resources/input-files/csv/students.csv"));
		flatFileItemReader.setLineMapper(createDefaultLineMapper());
		flatFileItemReader.setLinesToSkip(1);
		return flatFileItemReader;
	}

	private FileSystemResource absoluteResourcePath(String path) {
		return new FileSystemResource(Paths
				.get(System.getProperty("user.dir"), path).toString());
	}

	private DefaultLineMapper<StudentJSON> createDefaultLineMapper() {

		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setNames("id", "first_name", "last_name", "email");

		BeanWrapperFieldSetMapper<StudentJSON> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(StudentJSON.class);

		DefaultLineMapper<StudentJSON> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultLineMapper;
	}

	private ItemWriter<StudentJSON> itemWriter() {
		return new ItemWriter<>() {

			@Override
			public void write(List<? extends StudentJSON> items) throws Exception {
				System.out.println(items);
			}

		};
	}
}
