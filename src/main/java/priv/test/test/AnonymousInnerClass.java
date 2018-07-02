package priv.test.test;

public class AnonymousInnerClass {
	
	// 方法内部类
	public void A() {
		class Inner_1 {
			public Inner_1() {
				System.out.println("inner_1 method ...");
			}
		}
		new Inner_1();
	}
	
	public static void main (String[] args) {
		AnonymousInnerClass anonymousInnerClass = new AnonymousInnerClass();
		anonymousInnerClass.A();
		
		new Class1("hello world!") {
			public void print() { 
				System.out.println("Hahahaha~");
			}
		}.print();
	}
}

class Class1 {
	
	private String str1 = null;
	
	public Class1(String str) {
		this.str1 = str + " ";
		System.out.println(str1);
	}
}