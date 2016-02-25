package com.resource.arithmetic.sort;

public class InsertSortII {
	public static void main (String[] args) {
		int[] v = {
				(int)(Math.random() * 100), (int)(Math.random() * 100),
				(int)(Math.random() * 100), (int)(Math.random() * 100),
				(int)(Math.random() * 100), (int)(Math.random() * 100),
				(int)(Math.random() * 100), (int)(Math.random() * 100),
				(int)(Math.random() * 100), (int)(Math.random() * 100),
		};
		for (int a:v) {
			System.out.print(a + " ");
		}
		System.out.println();
		sort(v);
		for (int a:v) {
			System.out.print(a + " ");
		}
	}
	
	public static void sort(int[] v) {
		for (int i=1; i<v.length; i++) {
			insert(v, i);
		}
	}
	
	public static void insert(int[] v, int i) {
		int key = v[i];
		while (i>=1 && key < v[i-1]) {
			i --;
			v[i+1] = v[i];
			
		}
		v[i] = key;
	}
}
