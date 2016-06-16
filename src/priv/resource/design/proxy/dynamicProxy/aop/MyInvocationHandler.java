package priv.resource.design.proxy.dynamicProxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

	private AopIface aop; // 定义了切入时调用的方法
	private Object proxyObj; // 代理对象
	private String methodName; // 指定要切入的方法名

	public Object bind(Object obj) {
		this.proxyObj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//System.out.println(method.getName());
		if (this.aop == null) {
			throw new NullPointerException("aop is null");
		}
		if (this.methodName == null) {
			throw new NullPointerException("method is null");
		}

		Object temp = null;
		// 如果指定了要拦截方法名,并且调用的方法和指定的方法名相同,则进行拦截处理
		// 否则当正常方法处理
		if (this.methodName != null && method.toString().indexOf(this.methodName) != -1) {
			// 指定方法调用前的处理
			aop.before(proxyObj);
			// 利用反射机制调用原方法
			temp = method.invoke(this.proxyObj, args);
			// 指定方法调用后的处理
			aop.end(proxyObj);
		} else {
			System.out.println("调用方法---->" + method);
			if (args != null) {
				StringBuffer sb = new StringBuffer("[");
				for (int i = 0; i < args.length; i++) {
					sb.append(args[i] + ",");
				}
				sb.append("]");
				System.out.println("方法有[" + args.length + "]个参数:" + sb.toString());
			}
			// 利用反射机制调用原方法
			temp = method.invoke(this.proxyObj, args);
		}
		return temp;

	}

	public AopIface getAop() {
		return aop;
	}

	public void setAop(AopIface aop) {
		this.aop = aop;
	}

	public Object getObj() {
		return proxyObj;
	}

	public void setObj(Object obj) {
		this.proxyObj = obj;
	}

	public String getMethod() {
		return methodName;
	}

	public void setMethod(String method) {
		this.methodName = method;
	}
}
