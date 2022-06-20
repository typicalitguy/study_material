package com.github.typicalitguy.chunk.demo;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class DemoItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		Thread.sleep(4000);
		System.out.println(items);
	}

}
