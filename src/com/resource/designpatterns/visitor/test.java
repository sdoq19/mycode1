package com.resource.designpatterns.visitor;


class F {
	public void show() {
		System.out.println("this is F method");
	}
}

class S extends F {
	public void show() {
		System.out.println("this is S method");
	}
}

class A {
	public void method(F f) {
		System.out.println("this is F");
		f.show();
	}
	public void method(S s) {
		System.out.println("this is S");
		s.show();
	}
}

public class test {
	public static void main(String[] args) {
		A a = new A();
		F f = new F();
		F s = new S();
		a.method(f);
		a.method(s);
	}
}

/**
-------print-------
this is F
this is F method
this is F
this is S method

重写：动态绑定，重载：静态绑定
*/