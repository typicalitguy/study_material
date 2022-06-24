package com.github.typicalitguy.chunk.demo;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class StudentItemWriter implements ItemWriter<Student> {

	@Override
	public void write(List<? extends Student> items) throws Exception {
		items.forEach(Logger::log);
	}


}
