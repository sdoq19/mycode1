package priv.resource.reflection.exercise;

import java.lang.reflect.Method;

public class Test1 {
	
	private String name;
	private int age;

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
	
	public static void main(String[] args) {
		Test1 t1 = new Test1();
		Method[] methods = t1.getClass().getMethods();
		for (Method m : methods) {
			System.out.println(m.getName());
		}
	}
}
