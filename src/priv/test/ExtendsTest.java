package priv.test;

public class ExtendsTest extends BaseClass {
	private Long current_time = System.currentTimeMillis();
	private Integer temp = 456;
	
	public ExtendsTest() {
		System.out.println("ExtendsTest current_time:" + current_time);
	}
	
	public void printExtendsClassCurrentTime() {
		System.out.println("ExtendsTest current_time:" + current_time);
	}
	
//	public void printTemp() {
//		System.out.println("ExtendsTest temp value:" + this.temp);
//	}
	
	public static void main (String[] args) {
		ExtendsTest test = new ExtendsTest();
		test.cleanCurrentTime();
		test.printBaseClassCurrentTime();
		test.printExtendsClassCurrentTime();
		test.printTemp();
		
	}
}

class BaseClass {
	private Long current_time = System.currentTimeMillis();
	private Integer temp = 123;
	
	public BaseClass() {
		System.out.println("baseClass current_time:" + current_time);
	}
	
	public void cleanCurrentTime() {
		current_time = null;
	}
	
	public void printBaseClassCurrentTime() {
		System.out.println("baseClass current_time:" + current_time);
	}
	
	public void printTemp() {
		System.out.println("BaseClass temp value:" + this.temp);
	}
}