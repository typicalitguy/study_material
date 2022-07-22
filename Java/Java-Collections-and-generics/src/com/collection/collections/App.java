package com.collection.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Factory {
	public static List<Integer> getList() {
		return new ArrayList<>(List.of(0,1,2,3,4,5,6,7,8,9));
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
		Collections.shuffle(Factory.getList());// Shuffles the list
		Collections.rotate(Factory.getList(), 2);// rotates the list
		Collections.copy(new ArrayList<>(), Factory.getList());// copy one list to another
		Collections.unmodifiableList(Factory.getList());// create an unmodifiable list
		Collections.synchronizedList(Factory.getList());// creates synchronized thread-safe list by using intrinsic lock

	}

}
