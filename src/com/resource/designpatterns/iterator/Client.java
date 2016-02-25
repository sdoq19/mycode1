package com.resource.designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;

interface Iterator {
	public Object next();
	public boolean hasNext();
}

class ConcreteIterator implements Iterator {

	private List<Object> list = new ArrayList<Object>();
	private int cursor = 0;
	
	public ConcreteIterator(List<Object> list) {
		this.list = list;
	}
	
	@Override
	public boolean hasNext() {
		if (cursor == list.size()) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		Object obj = null;
		if (this.hasNext()) {
			obj = this.list.get(cursor ++);
		}
		return obj;
	}
}

interface Aggregate {
	public void add(Object obj);
	public void remove(Object obj);
	public Iterator iterator();
}

class ConcreteAggregate implements Aggregate {
	private List<Object> list = new ArrayList<Object>();
	
	@Override
	public void add(Object obj) {
		this.list.add(obj);
	}

	@Override
	public void remove(Object obj) {
		this.list.remove(obj);
		
	}
	
	@Override
	public Iterator iterator() {
		return new ConcreteIterator(this.list);
	}
	
}


public class Client {
	public static void main (String[] args) {
		Aggregate aggregate = new ConcreteAggregate();
		aggregate.add("小明");
		aggregate.add("小红");
		aggregate.add("小朱");
		Iterator iterator = aggregate.iterator();
		while(iterator.hasNext()) {
			Object obj = iterator.next();
			System.out.println(obj);
		}
	}
}
