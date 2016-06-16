package priv.resource.structure.stack;

public class TestSpeed {
	
	public static void main(String[] args) {
		TestSpeed t = new TestSpeed();
		t.testArrayStackSpeed();
		t.testLinkedStackSpeed();
	}
	
	public void testArrayStackSpeed() {
		MyStack<String> stack = new MyArrayStack<String>();
		int num = 10000000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			stack.push("AAAAA");
		}
		long temp = System.currentTimeMillis();
		System.out.println("MyArrayStack:push time: " + (temp - start));
		while (stack.pop() != null)
			;
		System.out.println("MyArrayStack:pop time: " + (System.currentTimeMillis() - temp));
	}
	
	public void testLinkedStackSpeed() {
		MyStack<String> stack = new MyLinkedStack<String>();
		int num = 10000000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			stack.push("AAAAA");
		}
		long temp = System.currentTimeMillis();
		System.out.println("MyLinkedStack:push time: " + (temp - start));
		while (stack.pop() != null)
			;
		System.out.println("MyLinkedStack:pop time: " + (System.currentTimeMillis() - temp));
	}
}
