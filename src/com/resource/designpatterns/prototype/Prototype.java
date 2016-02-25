package com.resource.designpatterns.prototype;

import com.utils.LoggerUtil;

class PrototypeBase implements Cloneable {
	public PrototypeBase clone() {
		PrototypeBase pBase = null;
		try {
			pBase = (PrototypeBase) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return pBase;
	}
}

class ConcretePrototype extends PrototypeBase {
	public void show() {
		//System.out.println("原型模式实现类");
		LoggerUtil.info(getClass(), "lala");
	}
}

public class Prototype {
	public static void main(String[] args) {
		
		ConcretePrototype cp = new ConcretePrototype();
		Long time1 = System.currentTimeMillis();
		for (int i=0; i<1000000; i++) {
			ConcretePrototype cpClone =  (ConcretePrototype)cp.clone();
			cpClone.show();
		}
		Long time2 = System.currentTimeMillis();
		
		Long time3 = System.currentTimeMillis();
		for (int i=0; i<1000000; i++) {
			ConcretePrototype cp1 = new ConcretePrototype();
			cp1.show();
		}
		Long time4 = System.currentTimeMillis();
		
		Long spendTime = time2 - time1;
		System.out.println("clone method spend time:" + spendTime);
		Long spendTime1 = time4 - time3;
		System.out.println("new method spend time:" + spendTime1);
	}
}
