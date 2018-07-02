package priv.resource.arithmetic.sort;

/**
 * 冒泡排序
 * 
 * @author zhuzh
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] v = { (int)(Math.random() * 10),(int)(Math.random() * 10),
					(int)(Math.random() * 10),(int)(Math.random() * 10),
					(int)(Math.random() * 10),(int)(Math.random() * 10),
					(int)(Math.random() * 10),(int)(Math.random() * 10),
					(int)(Math.random() * 10),(int)(Math.random() * 10)
		};
		for (int a:v) {
			System.out.print(a + " ");
		}
		bubbleSort(v);
		System.out.println();
		for (int a:v) {
			System.out.print(a + " ");
		}
	}
	
	public static void bubbleSort(int[] v) {
		for (int i=0; i<v.length; i++) 
			for (int j=i+1; j<v.length; j++) {
				if (v[i] > v[j]) {
					int temp = v[i];
					v[i] = v[j];
					v[j] = temp;
				}
			}
	}
}
