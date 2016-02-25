package com.resource.designpatterns.observer;

public class ProductPriceObserver implements Observer {

	//实现观察者必须实现的update方法
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Double) {
			System.out.println("产品价格已变更为：" + ((Product)o).getPrice());
		}
	}

}
