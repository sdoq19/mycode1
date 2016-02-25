package com.test;

/**
 * 普通内部类，需要先实例化外部类，再实例化内部类 静态内部类，可直接通过外部类的应用
 * 
 * @author zhuzh
 * 
 */
public class InnerStaticClass {
	
	public InnerStaticClass() {
		System.out.println("InnerStaticClass init !");
	}

	public class ClassA {
		public ClassA() {
			System.out.println("ClassA~" + this);
		}
	}

	public static class ClassB {
		public ClassB() {
			System.out.println("ClassB~" + this);
		}
	}

	public static void main(String[] args) {
		InnerStaticClass isc = new InnerStaticClass();
		ClassA ca = isc.new ClassA();

		ClassB cb = new InnerStaticClass.ClassB();
		ClassB cb1 = new InnerStaticClass.ClassB();
	}
}
