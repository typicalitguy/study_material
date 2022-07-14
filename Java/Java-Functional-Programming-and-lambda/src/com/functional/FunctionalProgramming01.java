package com.functional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalProgramming01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		//printAllNumbers(numbers);
		//sumOfAllNumber(numbers);
		//sortedList(numbers);
		//squareRootList(numbers);
	}

	public static List<Double> squareRootList(List<Integer> numbers) {
		return numbers
		.stream()
		.map(Math::sqrt)
		.collect(Collectors.toList());
	}

	public static void sortedList(List<Integer> numbers) {
		numbers
		.stream()
		.distinct()
		.sorted(Comparator.naturalOrder())
		//.sorted(Comparator.reverseOrder())
		//.sorted(Comparator.comparing(num->num))
		.forEach(System.out::println);;
	}

	public static void sumOfAllNumber(List<Integer> numbers) {
		numbers
		.stream()
		.reduce(0, (a, b) -> a + b);
	}

	public static void printAllNumbers(List<Integer> numbers) {
		numbers
		.stream()
		.filter(num -> num % 2 == 0)
		.map(num -> num * 2)
		.forEach(System.out::println);
	}

}
