package com.resource.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ************************反射机制************************
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法；
 * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 * 
 * Java反射机制主要提供了以下功能： 
 * 在运行时判断任意一个对象所属的类；
 * 在运行时构造任意一个类的对象；
 * 在运行时判断任意一个类所具有的成员变量和方法；
 * 在运行时调用任意一个对象的方法；
 * 生成动态代理。
 */
public class ReflectionTest {

	public static void main(String args[]) throws Exception {

		ReflectionTest reflectionTest = new ReflectionTest();

		Animal animal = new Animal();
		animal.setName("zhuzh");
		animal.setAge("11");
		animal.setNick("hardy");

		// 得到某个对象的属性
		String fieldName = "nick";
		Object property = reflectionTest.getProperty(animal, fieldName);
		System.out.println(property);

		// 得到某个类的静态属性
		String className = "com.resource.reflection.Animal";
		fieldName = "sex";
		property = reflectionTest.getStaticProperty(className, fieldName);
		System.out.println(property);

		// 执行某对象的方法
		String[] argsStrings = { "zhu", "25" };
		Object returnVallObject = reflectionTest.invokeMethod(animal, "getInfo", argsStrings);
		System.out.println(returnVallObject);

		// 新建实例
		String[] argsStrings2 = { "zhu", "25", "hardy" };
		Animal animal2 = (Animal) reflectionTest.newInstance(className, argsStrings2);
		System.out.println("Name:" + animal2.getName() + ",Age:" + animal2.getAge() + ",Nick:" + animal2.getNick());

		// 判断是否为某个类的实例
		Boolean isInstanceBoolean = reflectionTest.isInstance(animal2, Class.forName(className));
		System.out.println(isInstanceBoolean);

	}

	/**
	 * 得到某个对象的属性
	 * 
	 * @param owner
	 * @param fieldName
	 * @return
	 */
	public Object getProperty(Object owner, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

		Class ownerClass = owner.getClass();

		Field field = ownerClass.getField(fieldName);
		Object property = field.get(owner);

		return property;
	}

	/**
	 * 得到某个类的静态属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 */
	public Object getStaticProperty(String className, String fieldName) throws Exception {

		Class ownerClass = Class.forName(className);
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(ownerClass);

		return property;
	}

	/**
	 * 执行某对象的方法
	 * 
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 */
	public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {

		Class ownerClass = owner.getClass();

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(owner, args);
	}

	/**
	 * 执行某个类的静态方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 */
	public Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
		Class ownerClass = Class.forName(className);

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(null, args);
	}

	/**
	 * 新建实例
	 * 
	 * @param className
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Object newInstance(String className, Object[] args) throws Exception {
		Class newoneClass = Class.forName(className);

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Constructor cons = newoneClass.getConstructor(argsClass);

		return cons.newInstance(args);

	}

	/**
	 * 判断是否为某个类的实例
	 * 
	 * @param obj
	 * @param cls
	 * @return
	 */
	public boolean isInstance(Object obj, Class cls) {
		return cls.isInstance(obj);
	}

	
	/**
	 * 得到数组中的某个元素
	 * @param array
	 * @param index
	 * @return
	 */
	public Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}
}

class Animal {

	private String name;
	private String age;
	public String nick;
	public static String sex = "female";

	public Animal() {
		this.name = "zhuzh";
		this.age = "25";
		this.nick = "hardy";
	}

	public Animal(String name, String age, String nick) {
		this.name = name;
		this.age = age;
		this.nick = nick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public static String getSex() {
		return sex;
	}

	public static void setSex(String sex) {
		Animal.sex = sex;
	}

	public String getInfo(String name, String age) {
		return "Name:" + name + ",Age:" + age;
	}
}