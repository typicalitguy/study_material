package com.functional;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.functional.entity.Course;

@SuppressWarnings("all")
public class FunctionalProgramming {

	
	public static void main(String[] args) {
		
		//integerStreams();
		//customClasses();
		//otherStreams();
	}
	public static void integerStreams() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		numbers
		.stream()
		.filter(num -> num % 2 == 0)
		.map(num -> num * 2)
		.forEach(System.out::println);
		
		System.out.println(
				numbers
				.stream()
				.reduce(0, (a, b) -> a + b)
				);
		numbers
		.stream()
		.distinct()
		.sorted(Comparator.naturalOrder())
		//.sorted(Comparator.reverseOrder())
		//.sorted(Comparator.comparing(num->num))
		.forEach(System.out::println);
		
		System.out.println(
				numbers
				.stream()
				.map(Math::sqrt)
				.collect(Collectors.toList())
				);
	}
	
	public static void otherStreams() {
		Stream.of(1,2,3,4,5,6); // java.util.stream.ReferencePipeline$Head@29ee9faa
		Arrays.stream(new int[]{1,2,3,4,5,6}); //java.util.stream.IntPipeline$Head@14acaea5
		System.out.println(
				IntStream
				.range(1, 10)
				.sum()
				);
		System.out.println(
				IntStream
				.rangeClosed(1, 10)
				.sum()
				);
		System.out.println(
				IntStream
				.iterate(0, e->e+1)
				.limit(10)
				.sum()
				);
		System.out.println(
				IntStream
				.iterate(0, e->e+1)
				.limit(10)
				.peek(System.out::println)
				.sum()
				);
		System.out.println(
				IntStream
				.iterate(0, e->e+1)
				.limit(10)
				.boxed()
				.collect(Collectors.toList())
				);
		System.out.println(
				IntStream
				.rangeClosed(1, 10)
				.mapToObj(BigInteger::valueOf)
				.reduce(BigInteger.ONE,BigInteger::multiply)
				);
	}

	private static void customClasses() {
		List<Course> courses = Course.courses();
		
		System.out.println(
				courses
				.stream()
				.allMatch(course -> Course.isReviewScoreGreaterThan(course,95))
				);
		System.out.println(
				courses
				.stream()
				.noneMatch(course ->Course.isReviewScoreGreaterThan(course,95))
				);
		System.out.println(
				courses
				.stream()
				.anyMatch(course ->Course.isReviewScoreGreaterThan(course,95))
				);
		System.out.println(
				courses
				.stream()
				.sorted(Comparator.comparing(Course::getNoOfStudents)
						.thenComparing(Course::getName))
				.collect(Collectors.toList())
				);
		System.out.println(
				courses
				.stream()
				.sorted(Comparator.comparing(Course::getNoOfStudents).reversed())
				.collect(Collectors.toList())
				);
		System.out.println(
				courses
				.stream()
				.sorted(Comparator.comparingInt(Course::getNoOfStudents))
				.limit(2)
				.collect(Collectors.toList())
				);
		System.out.println(
				courses
				.stream()
				.sorted(Comparator.comparingInt(Course::getNoOfStudents))
				.skip(2)
				.collect(Collectors.toList())
				);
		System.out.println(
				courses
				.stream()
				.sorted(Comparator.comparingInt(Course::getNoOfStudents))
				.limit(1)
				.skip(2)
				.collect(Collectors.toList())
				);
		System.out.println(
				courses
				.stream()
				.sorted(Comparator.comparingInt(Course::getReviewScore))
				.takeWhile(course -> Course.isReviewScoreGreaterThan(course,95))
				.collect(Collectors.toList())
				);
		System.out.println(
				courses
				.stream()
				.sorted(Comparator.comparingInt(Course::getReviewScore))
				.dropWhile(course -> Course.isReviewScoreGreaterThan(course,95))
				.collect(Collectors.toList())
				);
		System.out.println(
				courses
				.stream()
				.max(Comparator.comparingInt(Course::getReviewScore))
				.orElse(new Course("Kubernetes","Cloud",95,21000))
				);
		
		System.out.println(
				courses
				.stream()
				.mapToInt(Course::getNoOfStudents)
				.max()
				);
		System.out.println(
				courses
				.stream()
				.mapToInt(Course::getNoOfStudents)
				.min()
				);
		System.out.println(
				courses
				.stream()
				.mapToInt(Course::getNoOfStudents)
				.sum()
				);
		System.out.println(
				courses
				.stream()
				.mapToInt(Course::getNoOfStudents)
				.average()
				);
		
		System.out.println(
				courses
				.stream()
				.collect(Collectors.groupingBy(Course::getCategory))
				);
		System.out.println(
				courses
				.stream()
				.collect(Collectors.groupingBy(Course::getCategory,Collectors.counting()))
				);
		System.out.println(
				courses
				.stream()
				.collect(Collectors.groupingBy(
							Course::getCategory,
							Collectors.maxBy(Comparator.comparing(Course::getReviewScore))
						))
				);
		System.out.println(
				courses
				.stream()
				.collect(Collectors.groupingBy(
							Course::getCategory,
							Collectors.mapping(Course::getName,Collectors.toList())
						))
				);
	}

}
