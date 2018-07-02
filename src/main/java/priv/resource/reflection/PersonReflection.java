package priv.resource.reflection;

import java.lang.reflect.Constructor;

public class PersonReflection {

	public static void main(String[] args) throws Exception {
		Person p = new Person();
		p.setName("zhuzh");
		p.setAge(25);
		System.out.println("name:" + p.getName() + ",age:" + p.getAge());
		
		Class personClass = p.getClass();
		Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
		constructor.newInstance("luo", 46);
		
		personClass = Person.class;
		constructor = personClass.getConstructor();
		constructor.newInstance();
		
		personClass = Class.forName("com.resource.reflection.PersonReflection"); // 失败，因为Person为非public
		constructor = personClass.getConstructor(String.class, int.class);
		constructor.newInstance("zhu", 48);
	}

}

class Person {

	private String name = "zhuzh";
	public int age = 25;

	public Person() {
		System.out.println("name:" + this.name + ",age:" + this.age);
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("name:" + this.name + ",age:" + this.age);
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
}