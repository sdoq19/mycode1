package com.test.superTest;

class base {
	public base() {
		System.out.println("调用父类的构造函数~ --> base()");
	}

	public base(String str) {
		System.out.println("调用父类的构造函数~ --> base(str)");
		System.out.println(str);
	}
}

public class SuperTest extends base {

	public SuperTest() {
		 super();
		// super("haha");
		System.out.println("调用子类的构造函数~ --> SuperTest()");
		// super();
	}

	public SuperTest(String str) {
		System.out.println("调用子类的构造函数~ --> SuperTest(str)");
		System.out.println(str);
	}

	public static void main(String[] args) {
		SuperTest st = new SuperTest();
	}
}
