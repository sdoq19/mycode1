package priv.resource.design.singleton;


/**
 * 设计模式--单例模式
 * 
 * @author zhuzh
 * 
 */
public class Singleton {
	public static void main(String[] args) {
		// 创建Singleton对象不能通过构造器，只能通过getInstance方法
		SingletonTest st1 = SingletonTest.getInstance();
		SingletonTest st2 = SingletonTest.getInstance();
		// 将输出true
		System.out.println(st1 == st2);
	}
}

/** 懒汉式单例 */
class SingletonTest {
	// 使用一个变量来缓存曾经创建的实例
	private static SingletonTest instance;

	// 将构造器使用private修饰，隐藏该构造器
	private SingletonTest() {
	}

	// 提供一个静态方法，用于返回Singleton实例
	// 该方法可以加入自定义的控制，保证只产生一个Singleton对象
	public static synchronized SingletonTest getInstance() {
		// 如果instance为null，表明还不曾创建Singleton对象
		// 如果instance不为null，则表明已经创建了Singleton对象，将不会执行该方法
		if (instance == null) {
			// 创建一个Singleton对象，并将其缓存起来
			instance = new SingletonTest();
		}	
		return instance;
	}
}

/** 饿汉式单例 在静态初始化器（static initializer）实例化，本身为线程安全 */
class SingletonTest2 {
	
	private static SingletonTest2 instance = new SingletonTest2();
	
	private SingletonTest2() {
	}
	
	private static SingletonTest2 getInstance() {
		return instance;
	}
	
}