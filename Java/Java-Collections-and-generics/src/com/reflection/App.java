package com.reflection;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Logging {
	public String type();

	public static final String WARNING = "WARNING";
	public static final String INFO = "INFO";
}

class Person extends Thread implements Comparable<Person>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3123937727503616788L;
	public static final String className = "Person";
	private String fullName;
	private int age;

	public Person(String fullName, int age) {
		super();
		this.fullName = fullName;
		this.age = age;
	}

	@SuppressWarnings("unused")
	private Person() {
		this.fullName = "Default";
		this.age = 0;
		// throw new RuntimeException("dont instantiate from reflection also");
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Logging(type = Logging.INFO)
	public void print(Logger logger) {
		logger.log(logger.getLevel(), toString());
	}

	@Override
	public String toString() {
		return "Person [fullName=" + fullName + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Person o) {
		return this.getFullName().compareTo(o.getFullName());
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " : Person thread is running");
	}

}

class Factory {
	public static void checkFields(Field[] fields, String identifier) {
		System.out.println(identifier + " : " + fields.length);
		for (Field field : fields) {
			System.out.println(field.getName() + " return type : " + field.getType() + ", modifier type : "
					+ Modifier.toString(field.getModifiers()));
		}
	}

	public static void checkMethods(Method[] methods, String identifier) {
		System.out.println(identifier + " : " + methods.length);
		for (Method method : methods) {
			System.out.println(method.getName() + " return type : " + method.getReturnType() + ", modifier type : "
					+ Modifier.toString(method.getModifiers()));
		}
	}

	public <T> List<T> toList(T[] array) {
		List<T> list = new ArrayList<>();
		for (T obj : array) {
			list.add(obj);
		}
		return list;
	}

	public static void setFieldsToPublic(Field[] declaredFields, String identifier) {
		for (Field field : declaredFields) {
			if (Modifier.isPrivate(field.getModifiers())) {
				System.out.println("Field name : " + field.getName() + ", modifier type "
						+ Modifier.toString(field.getModifiers()));
				field.setAccessible(true);
			}
		}
	}

	public static void checkConstructor(Constructor<?>[] constructors, String string)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor.getName() + " constructor with " + constructor.getParameterCount()
					+ " parameters, is accessible :" + Modifier.isPrivate(constructor.getModifiers()));
			if (constructor.getParameterCount() == 0) {
				constructor.setAccessible(true);
				System.out.println(constructor.newInstance(null).toString());
			}
		}

	}

	public static void checkInterfaces(Class<?>[] interfaces, String string) {
		for (Class<?> interfaceName : interfaces) {
			System.out.println("Interface name is " + interfaceName.getCanonicalName());
		}
	}

	public static void checkSuperClass(Class<? super Person> superclass, String string) {
		System.out.println("superclass name is " + superclass.getCanonicalName());
	}

	public static void checkAnnotation(Person person, Class<Person> personClass, String string) {
		try {
			Method printMethod = personClass.getMethod("print",Logger.class);
			if (printMethod.isAnnotationPresent(Logging.class)) {
				Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
				Logging logging = printMethod.getAnnotation(Logging.class);
				if (logging.type().equalsIgnoreCase(Logging.INFO)) {
					logger.setLevel(Level.INFO);
				} else {
					logger.setLevel(Level.WARNING);
				}
				person.print(logger);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}
}

public class App {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		try {
			Class<?> className = Class.forName("com.github.typicalitguy.reflection.Person");
			System.out.println(className.getCanonicalName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class<Person> personClass = Person.class;// we can get via this method
		System.out.println(personClass.getCanonicalName());
		System.out.println(personClass.getPackageName());
		Factory.checkFields(personClass.getFields(), "personClass.getFields()");
		Factory.checkFields(personClass.getDeclaredFields(), "personClass.getDeclaredFields()");
		Factory.checkMethods(personClass.getMethods(), "personClass.getMethods()");
		Factory.checkMethods(personClass.getDeclaredMethods(), "personClass.getDeclaredMethods()");
		Factory.setFieldsToPublic(personClass.getDeclaredFields(), "this fields will be public");
		Factory.checkConstructor(personClass.getDeclaredConstructors(), "personClass.getDeclaredConstructors()");
		Factory.checkInterfaces(personClass.getInterfaces(), "personClass.getInterfaces()");
		Factory.checkSuperClass(personClass.getSuperclass(), "personClass.getSuperclass()");
		Factory.checkAnnotation(new Person("Abhishek Ghosh", 24), personClass, "Custom Annotations");
	}

}
