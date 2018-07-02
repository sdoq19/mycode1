package priv.resource.design.proxy.dynamicProxy.adder;

public class AdderImpl implements AdderIface {
	@Override
	public int add(int a, int b) {
		return a + b;
	}
}