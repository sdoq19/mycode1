package priv.test.test;


public class GenericTest {
	
	public GenericClass getGenericClass() {
		GenericClass<String> gc = new GenericClass<String>();
		gc.setName("zhuzh");
		gc.setAge(11);
		gc.setData("data");
		return gc;
	}
}

class GenericClass<T> {
	private String name;
	private int age;
	private T data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
