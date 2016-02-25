package com.resource.arithmetic.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插入排序
 * 基本思路：在已排序的i条记录中插入一条新记录，得到有序的i+1条记录。
 * 特别提示：可以牺牲数组０的空间来作为插入的中间变量
 * 
 * @author zhuzh
 */
public class InsertSort {
	
	// 对一个数组进行排序，从1-n,数组大小为n+1,第0索引空闲
	public static void sort(Integer a[], int n) {
		Integer j;
		for (j=2; j<=n; j++) {
			insert(a[j], a, j-1);
		}
	}
	
	// 插入操作，用a[0]存放当前的插入值，简化while循环对i<1的判断
	public static void insert(Integer e, Integer a[], Integer i) {
		a[0] = e;
		while (e < a[i]) {
			a[i+1] = a[i];
			i--;
		}
		a[i+1] = e;
	}
	
	public static void main (String[] args) {
		Integer[] a = {91, 71, 11, 31, 21, 41, 61, 81, 51};
		Integer[] d = new Integer[a.length + 1];
		List b = Arrays.asList(a); // 数组转集合
		List c = new ArrayList(b); // 若没这步，则集合操作会报java.lang.UnsupportedOperationException错误
		c.add(0, 0); 
		System.out.println(c);
		c.toArray(d);
		
		sort(d, d.length-1);
		for (Integer e : d) {
			System.out.print(e + " ");
		}
	}
}

