package com.collection.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class App {

	public static void main(String[] args) {
		Set<String> hashSet = new HashSet<>();// based on hashcode , does not maintain order allmost same as hashmap
		Set<String> linkedHashSet = new LinkedHashSet<>();// based on hashcode and doubly linked list ,maintain order
															// allmost same as linkedhashmap
		Set<String> treeSet = new TreeSet<>();// based on red black tree, implementation of SortedSet interface ,sorted
												// the value

		SortedSet<Integer> sortedSet = new TreeSet<>();
		sortedSet.add(1);
		sortedSet.add(5);
		sortedSet.add(2);
		sortedSet.add(4);
		sortedSet.add(3);
		
		System.out.println(sortedSet.first());
		System.out.println(sortedSet.last());
		
		for (Integer item : sortedSet) {
			System.out.println(item);
		}

		SortedSet<Integer> subSet = sortedSet.subSet(2, 5);
		for (Integer item : subSet) {
			System.out.println(item);
		}
		SortedSet<Integer> taiSet = sortedSet.tailSet(2);
		for (Integer item : taiSet) {
			System.out.println(item);
		}
	}

}
