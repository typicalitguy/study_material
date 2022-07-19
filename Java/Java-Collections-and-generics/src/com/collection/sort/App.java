package com.collection.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Person implements Comparable<Person> {
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

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Person o) {
		return Integer.compare(this.getAge(), o.getAge());
	}

}

public class App {

	public static void main(String[] args) {
		int[] nums = { 1, 5, -5, 4, 12, 3 };
		Arrays.sort(nums);// sorts in ascending

		List<String> names = Arrays.asList("Abhishek Ghosh", "Abhishek Pal", "Bishal Mukherjee", "Nasim Molla",
				"Kushal Mukherjee");

		Collections.sort(names);// Sorts ascending
		System.out.println(names);

		Collections.sort(names, Collections.reverseOrder());// Sorts descending
		System.out.println(names);

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Abhishek Ghosh", 24));
		persons.add(new Person("Abhishek Ghosh", 23));
		persons.add(new Person("Abhishek Pal", 24));
		persons.add(new Person("Bishal Mukherjee", 25));
		persons.add(new Person("Nasim Molla", 25));
		persons.add(new Person("Kushal Mukherjee", 23));

		Collections.sort(persons);// Sorting based on Comparable.compareTo implementation
		System.out.println(persons);

		Collections.sort(persons, (p1, p2) -> p1.getName().compareTo(p2.getName())); // Sorting based on lambda
																						// implementation of
																						// Comparator.compare
		Collections.sort(persons, Comparator.comparing(Person::getName).thenComparing(Person::getAge));
		System.out.println(persons);
		Collections.sort(persons, Comparator.comparing(Person::getName).thenComparing(Person::getAge).reversed());
		System.out.println(persons);
		// Comparator is better than comparable as the comparing logic is separated for
		// the actual class

	}

}
