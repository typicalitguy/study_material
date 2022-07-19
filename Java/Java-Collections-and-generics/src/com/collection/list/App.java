package com.collection.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

class Factory {
	public static void add(Collection<Integer> list, int counter) {
		Long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			list.add(i);
		}
		System.out.println(list.getClass().getCanonicalName() + " taking " + (System.currentTimeMillis() - currentTime)
				+ " milisecond to add " + counter + " items");
	}

	public static void addFirst(ArrayList<Integer> list, int counter) {
		Long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			list.add(0, i);
		}
		System.out.println(list.getClass().getCanonicalName() + " taking " + (System.currentTimeMillis() - currentTime)
				+ " milisecond to add " + counter + " items");
	}

	public static void addFirst(Vector<Integer> list, int counter) {
		Long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			list.add(0, i);
		}
		System.out.println(list.getClass().getCanonicalName() + " taking " + (System.currentTimeMillis() - currentTime)
				+ " milisecond to add " + counter + " items");
	}

	public static void addFirst(LinkedList<Integer> list, int counter) {
		Long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			list.addFirst(i);
		}
		System.out.println(list.getClass().getCanonicalName() + " taking " + (System.currentTimeMillis() - currentTime)
				+ " milisecond to add " + counter + " items");
	}

	public static void addLast(ArrayList<Integer> list, int counter) {
		Long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			list.add(i);
		}
		System.out.println(list.getClass().getCanonicalName() + " taking " + (System.currentTimeMillis() - currentTime)
				+ " milisecond to add " + counter + " items");
	}

	public static void addLast(Vector<Integer> list, int counter) {
		Long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			list.add(i);
		}
		System.out.println(list.getClass().getCanonicalName() + " taking " + (System.currentTimeMillis() - currentTime)
				+ " milisecond to add " + counter + " items");
	}

	public static void addLast(LinkedList<Integer> list, int counter) {
		Long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			list.addLast(i);
		}
		System.out.println(list.getClass().getCanonicalName() + " taking " + (System.currentTimeMillis() - currentTime)
				+ " milisecond to add " + counter + " items");
	}
}

public class App {

	private static final int COUNTER = 1000000;

	public static void main(String[] args) {
		Factory.addLast(new ArrayList<>(), COUNTER);
		Factory.addLast(new Vector<>(), COUNTER);
		Factory.addLast(new LinkedList<>(), COUNTER);

	}

}
