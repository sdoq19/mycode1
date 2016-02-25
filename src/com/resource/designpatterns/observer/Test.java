package com.resource.designpatterns.observer;

public class Test {
	public static void main (String[] args) {
		
		//创建一个被观察者对象
		Product p = new Product("ObservableObj", 1.00);
		System.out.println(p.getName() + "'s price : " + p.getPrice());
		
		//创建两个观察者对象
		ProductNameObserver pno = new ProductNameObserver();
		ProductPriceObserver ppo = new ProductPriceObserver();
		
		//向被观察对象上注册两个观察者对象
		p.registObserver(pno);
		p.registObserver(ppo);
		
		//程序调用setter方法来改变Product的name和price属性
		p.setName("a");
		p.setPrice(0.99);
		
		//p.deleteObserver(pno);
		p.deleteObserver(ppo);
		
		p.setName("b");
		p.setPrice(1.11);
	}
}
