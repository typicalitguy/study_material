package com.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
//		return Integer.compare(this.getAge(), o.getAge());
		return this.getName().compareTo(o.getName());
	}

}

class Factory {
	public static void checkTimeWithDeque(Deque<Integer> stack) {
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500000; i++) {
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			stack.pop();
		}
		System.out.println("Time taken with ArrayDeque: " + (System.currentTimeMillis() - now) + "ms");
	}

	public static void checkTimeWithStack(Stack<Integer> stack) {
		long now = System.currentTimeMillis();
		for (int i = 0; i < 500000; i++) {
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			stack.pop();
		}
		System.out.println("Time taken with Stack: " + (System.currentTimeMillis() - now) + "ms");
	}
}

public class App {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(10);// same as add method
		queue.peek();
		queue.poll();// same as remove method

		Queue<String> names = new LinkedList<>();
		names.offer("Abhishek");
		names.offer("Bishal");
		names.offer("Nasim");
		names.offer("Kushal");
		while (!names.isEmpty()) {
			System.out.println("Name is " + names.poll());
		}

		// Priority queue is based on priority heap
		// Each item has its priority
		// the elements of the priority queue are ordered according to their natural
		// ordering defined by the comparable interface
		// add() method add in the queue
		// peek() method retrieves the head of the queue
		// poll() method return the head else null

		Queue<Person> persons = new PriorityQueue<>();
		persons.add(new Person("Abhishek", 24));
		persons.add(new Person("Bishal", 25));
		persons.add(new Person("Nasim", 25));
		persons.add(new Person("Kushal", 23));
		while (!persons.isEmpty()) {
			System.out.println(persons.poll());
		}

		persons = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));// We can add our
																								// comparator
																								// implemention here as
																								// well
		persons.add(new Person("Abhishek", 24));
		persons.add(new Person("Bishal", 25));
		persons.add(new Person("Nasim", 25));
		persons.add(new Person("Kushal", 23));
		while (!persons.isEmpty()) {
			System.out.println(persons.poll());
		}

		// Double ended queue
		Deque<Integer> deque = new ArrayDeque<>();
		deque.addFirst(10);
		deque.addLast(12);
		deque.removeFirst();
		deque.removeLast();

		// stack by Deque
		Deque<Integer> stackByQueue = new ArrayDeque<>();
		stackByQueue.push(1);
		stackByQueue.push(2);
		stackByQueue.push(3);
		stackByQueue.push(4);
		stackByQueue.push(5);
		while (stackByQueue.isEmpty()) {
			System.out.println("item is " + stackByQueue.pop());
		}
		Factory.checkTimeWithDeque(new ArrayDeque<>());// Time taken with ArrayDeque: 23ms
		Factory.checkTimeWithStack(new Stack<>());// Time taken with Stack: 51ms as all methods are synchronized as
													// everytime when we want to access any method there is lock
	}
}
