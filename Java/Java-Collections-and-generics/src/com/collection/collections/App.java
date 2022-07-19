package com.collection.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Factory {
	public static List<Integer> getList() {
		List<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(5);
		nums.add(3);
		nums.add(2);
		nums.add(4);
		return nums;
	}
}

public class App {

	public static void main(String[] args) {
		try {
			List<Integer> list = Arrays.asList(1, 5, 3, 2, 4);
			list.add(5);// If we create an arraylist using Arrays.asList then we cann't add anything
						// into it else it will give java.lang.UnsupportedOperationException
		} catch (Exception ex) {
			System.out.println(ex);
		}
		List<Integer> nums = Factory.getList();
		Collections.shuffle(nums);// Shuffles the list
		Collections.rotate(nums, 2);// rotates the list
		Collections.copy(new ArrayList<>(), nums);// copy one list to another
		Collections.unmodifiableList(Factory.getList());// create an unmodifiable list
		Collections.synchronizedList(nums);// creates synchronized thread-safe list by using intrinsic lock

	}

}
