package priv.resource.design.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 封装某些作用于某种数据结构中各元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。
 * 【行为类模式】
 *	 抽象访问者：抽象类或者接口，声明访问者可以访问哪些元素，具体到程序中就是visit方法中的参数定义哪些对象是可以被访问的。
 *	访问者：实现抽象访问者所声明的方法，它影响到访问者访问到一个类后该干什么，要做什么事情。
 *	抽象元素类：接口或者抽象类，声明接受哪一类访问者访问，程序上是通过accept方法中的参数来定义的。抽象元素一般有两类方法，一部分是本身的业务逻辑，另外就是允许接收哪类访问者来访问。
 *	元素类：实现抽象元素类所声明的accept方法，通常都是visitor.visit(this)，基本上已经形成一种定式了。
 *	结构对象：一个元素的容器，一般包含一个容纳多个不同类、不同接口的容器，如List、Set、Map等，在项目中一般很少抽象出这个角色。
 */

/** -------------------------------------
class A {
	public void a1() {
		System.out.println("this is A:A1 method!");
	}
	public void a2() {
		B b = new B();
		b.B1(this);
	}
}

class B {
	public void B1(A a) {
		a.a1();
	}
}

class VisitorTest {
	public static void main (String[] args) {
		A a = new A();
		a.a2();
	}
}
 -------------------------------------**/

abstract class Element {
	public abstract void doSomething();
	public abstract void accept(IVisitor visitor);
}

interface IVisitor {
	public void visitor(ConcreteElement1 e);
	public void visitor(ConcreteElement2 e);
}

class ConcreteElement1 extends Element {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visitor(this);
	}

	@Override
	public void doSomething() {
		System.out.println("this is ConcreteElement1 method!");
	}
}

class ConcreteElement2 extends Element {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visitor(this);
	}

	@Override
	public void doSomething() {
		System.out.println("this is ConcreteElement2 method!");
	}
}

class Visitor implements IVisitor {

	public void visitor(ConcreteElement1 e) {
		e.doSomething();
	}

	public void visitor(ConcreteElement2 e) {
		e.doSomething();
	}
}

class ObjectStruture {
	
	public static List<Element> getList() {
		List<Element> list = new ArrayList<Element>();
		Random random = new Random(System.currentTimeMillis());
		for (int i=0; i<100; i++) {
			int randomInt = random.nextInt(100);
			if (randomInt <50) {
				list.add(new ConcreteElement1());
			} else {
				list.add(new ConcreteElement2());
			}
		}
		return list;
	}
}

public class VisitorTest {
	public static void main(String[] args) {
		List<Element> list = ObjectStruture.getList();
		for (Element e:list) {
			e.accept(new Visitor());
		}
	}
}

