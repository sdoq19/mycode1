package priv.resource.design.visitor;

/**
 * 用双分派实现动态绑定
 * @author zhuzh
 *
 */
interface IVisitor2 {
	public void visit(Father1 father);
	public void visit(Son11 son1);
	public void visit(Son22 son2);
	public void visit(People1 people);
}

class Visitor2 implements IVisitor2 {

	@Override
	public void visit(Father1 father) {
		father.show();
	}

	@Override
	public void visit(Son11 son1) {
		son1.show();
	}

	@Override
	public void visit(Son22 son2) {
		son2.show();
	}

	@Override
	public void visit(People1 people) {
		people.show();
	}
	
}

class People1 {
	public void accept(IVisitor2 visitor) {
		visitor.visit(this);
	}
	
	public void show() {
		System.out.println("this is People1!");
	}
}

class Father1 extends People1 {

	public void show() {
		System.out.println("this is father!");
	}
}

class Son11 extends Father1 {
	
	public void show() {
		System.out.println("this is son1!");
	}
}

class Son22 extends Father1 {

	public void show() {
		System.out.println("this is son2!");
	}
}

public class DoubleDispatch1 {
	
	public static void main (String[] args) {
		Father1 father = new Father1();
		Father1 son1 = new Son11();
		Father1 son2 = new Son22();
		People1 people = new People1();
		
		IVisitor2 vistor = new Visitor2();
		father.accept(vistor);
		son1.accept(vistor);
		son2.accept(vistor);
		people.accept(vistor);
	}
}
