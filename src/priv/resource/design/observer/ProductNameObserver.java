package priv.resource.design.observer;

public class ProductNameObserver implements Observer {

	//实现观察者必须实现的update方法
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			System.out.println("产品名称已变更为：" + arg);
		}
	}

}
