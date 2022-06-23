package com.github.typicalitguy.chunk.demo;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class DemoItemReader implements ItemReader<Integer> {

	private List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	int index = 0;

	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (index < list.size()) {
			return list.get(index++);
		}
		index = 0;
		return null;
	}

}
