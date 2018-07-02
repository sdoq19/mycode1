package priv.resource.design.proxy.staticProxy;

public class test {

	public static void main(String[] args) {
		HelloWorld h = new HelloWorldImp();
		StaticProxy sp = new StaticProxy(h);
		sp.print();
	}
}
