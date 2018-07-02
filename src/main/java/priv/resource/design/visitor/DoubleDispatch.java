package priv.resource.design.visitor;

/**
 * 用双分派实现动态绑定
 * @author zhuzh
 *
 */
interface IVisitor1 {
	public void visit(Father father);
	public void visit(Son1 son1);
	public void visit(Son2 son2);
}

abstract class People {
	
}

class Visitor1 implements IVisitor1 {

	@Override
	public void visit(Father father) {
		father.show();
	}

	@Override
	public void visit(Son1 son1) {
		son1.show();
	}

	@Override
	public void visit(Son2 son2) {
		son2.show();
	}
	
}

class Father extends People {
	public void accept(IVisitor1 visitor) {
		visitor.visit(this);
	}
	public void show() {
		System.out.println("this is father!");
	}
}

class Son1 extends Father {
	public void accept(IVisitor1 visitor) {
		visitor.visit(this);
	}
	public void show() {
		System.out.println("this is son1!");
	}
}

class Son2 extends Father {
	public void accept(IVisitor1 visitor) {
		visitor.visit(this);
	}
	public void show() {
		System.out.println("this is son2!");
	}
}

public class DoubleDispatch {
	
	public static void main (String[] args) {
		Father father = new Father();
		Father son1 = new Son1();
		Father son2 = new Son2();
		
		IVisitor1 vistor = new Visitor1();
		father.accept(vistor);
		son1.accept(vistor);
		son2.accept(vistor);
	}
}
