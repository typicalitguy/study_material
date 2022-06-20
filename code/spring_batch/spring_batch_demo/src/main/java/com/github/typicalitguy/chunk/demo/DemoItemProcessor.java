package com.github.typicalitguy.chunk.demo;

import org.springframework.batch.item.ItemProcessor;

public class DemoItemProcessor implements ItemProcessor<Integer, String> {

	@Override
	public String process(Integer item) throws Exception {
		return "Value is : " + item;
	}

}
