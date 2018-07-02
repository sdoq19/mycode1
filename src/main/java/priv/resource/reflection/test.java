package priv.resource.reflection;

import java.lang.reflect.Field;

public class test {
	
	public static void main(String[] mainStrings) throws Exception {
		Field field = String.class.getDeclaredField("value");
		field.setAccessible(true);
		field.set("hello!", "cheers".toCharArray());
		System.out.println("hello");
		
		Integer iiInteger = 42;
		Field field1 = iiInteger.getClass().getDeclaredField("value");
		field1.setAccessible(true);
		field1.set(42, 43);
		System.out.println(iiInteger);
	}
}
