package com.collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// hasCode method is used to determine the hashcode value of an object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * when there is any collision then on the bucket's linkedList on each item
	 * equal method is checked if it matches with any item then the item's value is
	 * just updated else it will just add a new node in the end of the linkedlist
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}

class Factory {
	public static void populateAges(Map<String, String> agesMap) {
		agesMap.put("Abhishek Ghosh", "24");
		agesMap.put("Nasim Molla", "25");
		agesMap.put("Bishal Mukherjee", "25");
		agesMap.put("Kushal Mukherjee", "23");
		agesMap.put("Abhishek Pal", "24");
	}

	public static void checkTime(Map<Integer, String> map, int counter) {
		long currentTime = System.currentTimeMillis();
		for (int i = 0; i < counter; i++) {
			map.put(i, "Number_" + i);
		}
		for (int i = 0; i < counter; i++) {
			map.get(i);
		}
		System.out
				.println(map.getClass().getCanonicalName() + " takes : " + (System.currentTimeMillis() - currentTime));
	}

	public static void printMap(Map<?, ?> map) {
		System.out.println(map.getClass().getCanonicalName());
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}

public class App {

	public static void main(String[] args) {
		Map<String, String> agesMap = new HashMap<>();
		Factory.populateAges(agesMap);
		Factory.printMap(agesMap);// HashMap does not maintain the insertion order

		agesMap = new LinkedHashMap<>();
		Factory.populateAges(agesMap);
		Factory.printMap(agesMap);// LinkedHashMap maintain the insertion order

		agesMap = new TreeMap<>();// Tree map relies on Red Black tree data structure
		Factory.populateAges(agesMap);
		Factory.printMap(agesMap);// treemap is sort the keys internally as its a tree data structure so traversal
									// of items will be depending on keys

		agesMap = new TreeMap<>(Collections.reverseOrder());// We can assign our comparator as well
		Factory.populateAges(agesMap);
		Factory.printMap(agesMap);

		Set<Entry<String, String>> agesEntrySet = agesMap.entrySet();
		System.out.println(agesEntrySet);
		Set<String> keySet = agesMap.keySet();
		System.out.println(keySet);

		Factory.checkTime(new HashMap<>(), 100000);// HashMap is faster than treemap
		Factory.checkTime(new LinkedHashMap<>(), 100000);
		Factory.checkTime(new TreeMap<>(), 100000);

		Map<Person, String> personData = new HashMap<>();
		personData.put(new Person("Abhishek Ghosh", 24), "ghoshabhishek1640@gmail.com");
		personData.put(new Person("Abhishek Pal", 24), "abhishek.mis97@gmail.com");
		Factory.printMap(personData);

		/*
		 * if we don't define hasCode and equals method then hasCode method of the
		 * object class will be invoked then every time a new hashCode will be generated
		 * when there is a new creation of same object though there value can be same
		 */
		System.out.println(personData.get(new Person("Abhishek Ghosh", 24)));
		System.out.println(personData.get(new Person("Abhishek Pal", 24)));

		/*
		 * From java 8, hash map has started using red-black tree once threshold of
		 * linkedList is reached in case of hash collision. So in the worst case hashMap
		 * has read time complexity as O(log(n)) rather than o(n)
		 */
	}

}
